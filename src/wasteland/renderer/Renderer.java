package wasteland.renderer;

import java.util.Collections;

import wasteland.global.*;
import wasteland.items.*;
import wasteland.journal.*;
import wasteland.world.*;
import wasteland.entity.*;
import wasteland.game.Game;

public class Renderer implements Runnable {
    // A Renderer is a class which renders
    // a main and overlay layer to the terminal.
    private World world;
    private Journal journal;
    private Player player;
    private RenderLayers mainLayer;
    private RenderLayers overlayLayer;

    // Temporary variables for the renderer to save memory.
    private int tile;
    private String bg, fg;
    private String character;
    private Entity entity;
    private Item item;
    private int lastCleanFrame = 0;
    private int lastPlayerTick = 0;
    private int lastEntityTick = 0;
    private Entity[] tickEntities = new Entity[100];

    // Renderer constructs a new Renderer to render a
    // main and overlay layer to the terminal.
    public Renderer(World world, Journal journal, Player player, RenderLayers mainLayer, RenderLayers overlayLayer) {
        this.world = world;
        this.journal = journal;
        this.player = player;
        this.mainLayer = mainLayer;
        this.overlayLayer = overlayLayer;
    }

    // Renders the HUD and the world at the specified coordinates.
    private void renderWorld(int x, int y, int viewDistance) {
        // Entities ticker
        if (lastEntityTick >= Constants.fps) {
            lastEntityTick = 0;
        } else {
            lastEntityTick++;
        }

        // Player ticker
        lastPlayerTick++;
        if (lastPlayerTick >= 100) {
            // Tick the player
            player.playerTick(1);
            lastPlayerTick = 0;
        }

        // Initialize the rendered screen with a new StringBuilder.
        StringBuilder renderedScreen = new StringBuilder(ColouredSysOut.ANSI_RESET);
        // Render the HUD.
        renderedScreen.append(drawBox(0));
        renderedScreen.append(drawTextBox("Expedition"));
        renderedScreen.append(drawBox(2));

        // Render the world.
        // Set a 0,0 reference point from the player's position to render the world.
        int refX = x - (Constants.viewWidth / 2);
        int refY = y - (Constants.viewHeight / 2);
        // Set the maximum coordinates to render the world.
        int maxX = x + (Constants.viewWidth / 2);
        int maxY = y + (Constants.viewHeight / 2);
        // If the reference point is outside the world, render the world from the
        // closest edge.
        if (refX < 0) {
            maxX += refX * -1;
            refX = 0;
        }
        if (refY < 0) {
            maxY += refY * -1;
            refY = 0;
        }
        // If the maximum coordinates are outside the world, render the world from the
        // closest edge.
        if (maxX >= world.getWorldWidth()) {
            refX -= (maxX - (world.getWorldWidth() - 1));
            maxX = world.getWorldWidth() - 1;
            if (refX < 0) {
                refX = 0;
            }
        }
        if (maxY >= world.getWorldHeight()) {
            refY -= (maxY - (world.getWorldHeight() - 1));
            maxY = world.getWorldHeight() - 1;
            if (refY < 0) {
                refY = 0;
            }
        }

        // Loop through the world's tiles within the reference point
        // and the bounds of the view and render them.
        for (int j = refY; j <= maxY; j++) {
            renderedScreen.append(ColouredSysOut.ANSI_RESET + TileChars.borderSide);
            for (int i = refX; i <= maxX; i++) {
                // Calculate the tile's distance from the player.
                int xDistance = (x - i) * (x - i);
                int yDistance = (y - j) * (y - j);
                // Only render tiles that are within the player's view distance.
                if (Math.sqrt(xDistance + yDistance) <= viewDistance) {
                    // Check if there is an entity at the current coordinates,
                    // if so, render it.
                    if (world.getEntity(i, j) != null) {
                        entity = world.getEntity(i, j);
                        if (lastEntityTick >= Constants.fps) {
                            // Add the entity to the ticker.
                            for (int k = 0; k < tickEntities.length; k++) {
                                if (tickEntities[k] == null) {
                                    tickEntities[k] = entity;
                                    break;
                                }
                            }
                        }
                        character = entity.getCharacter();
                        fg = entity.getColor();
                        // Get the background color of the title where the entity is standing.
                        bg = TileChars.getTileColour(world.getTile(i, j))[0];
                        // Check if there is an item at the current coordinates,
                        // if so, render it.
                    } else if (world.getItem(i, j) != null) {
                        item = world.getItem(i, j);
                        character = item.getCharacter();
                        fg = item.getColor();
                        // Get the background color of the title where the item is standing.
                        bg = TileChars.getTileColour(world.getTile(i, j))[0];
                    } else { // If there is no entity or item, render the tile.
                        tile = world.getTile(i, j);
                        bg = TileChars.getTileColour(tile)[0];
                        fg = TileChars.getTileColour(tile)[1];
                        character = TileChars.getTileChar(tile);
                    }

                    // Render the current coordinates.
                    renderedScreen.append(fg + bg + character);
                } else {
                    renderedScreen.append(ColouredSysOut.ANSI_BG_BLACK + "  ");
                }
            }
            renderedScreen.append(ColouredSysOut.ANSI_RESET + TileChars.borderSide + "\n");
        }
        // Tick the entities.
        for (int k = 0; k < tickEntities.length; k++) {
            if (tickEntities[k] != null) {
                tickEntities[k].tick(player);
                tickEntities[k] = null;
            }
        }

        // Render information under the world.
        renderedScreen.append(drawBox(2));
        renderedScreen.append(drawTextBox(Game.getMessage()));
        renderedScreen.append(drawBox(2));
        renderedScreen.append(drawTextBox("Health: " + player.getHealth() + "/" + player.getMaxHealth()));
        renderedScreen.append(drawTextBox("Hunger: " + player.getHunger() + "/" + player.getMaxHunger()));
        renderedScreen.append(drawTextBox("Thirst: " + player.getThirst() + "/" + player.getMaxThirst()));
        renderedScreen.append(drawTextBox("Press f to end the expedition"));
        renderedScreen.append(drawTextBox("Press h for help"));
        renderedScreen.append(drawBox(1));

        render(renderedScreen);
    }

    // Renders the journal.
    private void renderJournal() {
        // Initialize the rendered screen with a new StringBuilder.
        StringBuilder renderedScreen = new StringBuilder(ColouredSysOut.ANSI_RESET);
        // Render the journal.
        int day = journal.getDay();
        renderedScreen.append(drawBox(0));
        renderedScreen.append(drawTextBox("Journal ┃ DAY " + day + " ┃ TAB " + journal.getTab()));
        renderedScreen.append(drawBox(2));
        if (journal.getTab() == 1) {
            renderedScreen.append(drawTextBox(journal.getEntry(day)));
        } else if (journal.getTab() == 2) {
            renderedScreen.append(drawTextBox("Player Stats:"));
            renderedScreen.append(drawTextBox("Health: " + player.getHealth() + "/" + player.getMaxHealth()));
            renderedScreen.append(drawTextBox("Hunger: " + player.getHunger() + "/" + player.getMaxHunger()));
            renderedScreen.append(drawTextBox("Thirst: " + player.getThirst() + "/" + player.getMaxThirst()));
            renderedScreen.append(drawTextBox("Infected: " + player.isInfected()));
            renderedScreen.append(drawTextBox("Inventory:"));
            renderedScreen.append(drawTextBox("Food: " + player.getFood() + "/" + player.getMaxFood()));
            renderedScreen.append(drawTextBox("Water: " + player.getWater() + "/" + player.getMaxWater()));
            renderedScreen.append(drawTextBox(journal.getEntry(day)));
            renderedScreen.append(drawBox(2));
            renderedScreen
                    .append(drawTextBox("Press 1 to eat some food, 2 to drink some water or 3 to cure an infection"));
        } else if (journal.getTab() == 3) {
            renderedScreen.append(drawTextBox(journal.getEntry(day)));
            renderedScreen.append(drawTextBox("This is your inventory:"));
            // Loop through the player's inventory and render it.
            for (int i = 0; i < player.getItems().length; i++) {
                if (player.getItems()[i] != null) {
                    renderedScreen.append(drawTextBox(player.getItems()[i].getName()));
                } else {
                    renderedScreen.append(drawTextBox("Empty Slot"));
                }
            }
            renderedScreen.append(drawBox(2));
            renderedScreen.append(drawTextBox("Press f to go on a expedition or g to rest."));
        } else {
            renderedScreen.append(drawTextBox(journal.getEntry(day)));
        }
        renderedScreen.append(drawBox(2));
        renderedScreen.append(drawTextBox("Press e to continue"));
        renderedScreen.append(drawBox(1));

        render(renderedScreen);
    }

    // Renders the player's inventory.
    private void renderInventory() {
        // Initialize the rendered screen with a new StringBuilder.
        StringBuilder renderedScreen = new StringBuilder(ColouredSysOut.ANSI_RESET);

        // Render the player's inventory.
        renderedScreen.append(drawBox(0));
        renderedScreen.append(drawTextBox("Inventory"));
        renderedScreen.append(drawBox(2));
        renderedScreen.append(drawTextBox("Player Stats:"));
        renderedScreen.append(drawTextBox("Health: " + player.getHealth() + "/" + player.getMaxHealth()));
        renderedScreen.append(drawTextBox("Hunger: " + player.getHunger() + "/" + player.getMaxHunger()));
        renderedScreen.append(drawTextBox("Thirst: " + player.getThirst() + "/" + player.getMaxThirst()));
        renderedScreen.append(drawTextBox("Inventory:"));
        renderedScreen.append(drawTextBox("Food: " + player.getFood() + "/" + player.getMaxFood()));
        renderedScreen.append(drawTextBox("Water: " + player.getWater() + "/" + player.getMaxWater()));

        // Loop through the player's inventory and render it.
        for (int i = 0; i < player.getItems().length; i++) {
            if (player.getItems()[i] != null) {
                renderedScreen.append(drawTextBox(player.getItems()[i].getName()));
            } else {
                renderedScreen.append(drawTextBox("Empty Slot"));
            }
        }

        renderedScreen.append(drawTextBox("Press I to close the inventory"));
        renderedScreen.append(drawBox(1));

        render(renderedScreen);
    }

    // Renders the help screen.
    private void renderHelp() {
        // Initialize the rendered screen with a new StringBuilder.
        StringBuilder renderedScreen = new StringBuilder(ColouredSysOut.ANSI_RESET);

        // Render the help screen.
        renderedScreen.append(drawBox(0));
        renderedScreen.append(drawTextBox("Help"));
        renderedScreen.append(drawBox(2));
        renderedScreen.append(drawTextBox("WASD to move"));
        renderedScreen.append(drawTextBox("I to open/close the inventory"));
        renderedScreen.append(drawTextBox("H to open/close this help screen"));
        renderedScreen.append(drawTextBox("Q to quit"));
        renderedScreen.append(drawTextBox(player.getName()));
        renderedScreen.append(drawBox(1));

        render(renderedScreen);
    }

    // Prints the rendered screen to the terminal.
    private void render(StringBuilder renderedScreen) {
        System.out.print(ColouredSysOut.ANSI_BG_BLACK + ColouredSysOut.ANSI_WHITE);

        if (Constants.rendererMode == 0) {
            char escCode = 0x1B;
            int row = 0;
            int column = 0;
            // ASCII escape code for moving the cursor to a specific row and column.
            System.out.print(String.format("%c[%d;%df", escCode, row, column));
        } else if (Constants.rendererMode == 1) {
            System.out.print("\033[H\033[2J"); // Clear the screen.
        } else if (Constants.rendererMode == 2) {
            System.out.print("[0;0f");
            lastCleanFrame++;
            if (lastCleanFrame > Constants.fps) {
                System.out.print("\033[H\033[2J");
                lastCleanFrame = 0;
            }
        }

        System.out.println(renderedScreen);
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                // Render overlay layer if available.
                int oLayer = this.overlayLayer.getRenderLayer();
                switch (oLayer) {
                    case RenderLayers.Help:
                        // Render the help screen.
                        renderHelp();
                        break;
                    case RenderLayers.Inventory:
                        // Render the player's inventory screen.
                        renderInventory();
                        break;
                    default:
                        // There is no overlay layer, render the main layer.
                        int mLayer = this.mainLayer.getRenderLayer();
                        switch (mLayer) {
                            case RenderLayers.World:
                                // Render the world and HUD at the player's coordinates.
                                renderWorld(this.player.getX(), this.player.getY(), this.player.getViewDistance());
                                break;
                            case RenderLayers.Journal:
                                // Render the journal.
                                renderJournal();
                                break;
                            default:
                                break;
                        }
                        break;
                }
                // Wait before rendering again.
                Thread.sleep(1000 / Constants.fps);
            }
        } catch (ExceptionInInitializerError e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            // Renderer is interrupted.
        }
    }

    // drawBox draws the specified side of a box.
    // 0 = top, 1 = bottom, 2 = intersection.
    private String drawBox(int side) {
        switch (side) {
            case 0:
                return TileChars.borderTopLeft
                        + String.join("", Collections.nCopies(Constants.viewWidth + 1, TileChars.borderTop))
                        + TileChars.borderTopRight + "\n";
            case 1:
                return TileChars.borderBottomLeft
                        + String.join("", Collections.nCopies(Constants.viewWidth + 1, TileChars.borderTop))
                        + TileChars.borderBottomRight + "\n";
            case 2:
                return TileChars.borderSideIntersectLeft
                        + String.join("", Collections.nCopies(Constants.viewWidth + 1, TileChars.borderTop))
                        + TileChars.borderSideIntersectRigth + "\n";
            default:
                return "";
        }
    }

    // drawTextBox draws the specified text to
    // make it fit in a box.
    private String drawTextBox(String text) {
        StringBuilder textBox = new StringBuilder();
        // Get the width of the box where the text will be drawn.
        // The width is added 1 to account for the border and
        // multiplied by 2 to account for the double width of the tiles.
        int boxWidth = (Constants.viewWidth + 1) * 2;
        // Calculate the number of lines the text will take up.
        int numberOfLines = (text.length() / boxWidth) + 1;
        // Loop through the number of lines and add them to the text box.
        for (int i = 0; i < numberOfLines; i++) {
            textBox.append(TileChars.borderSide);
            // If the text is longer than the box width,
            // add spaces until next line.
            for (int j = 0; j < boxWidth; j++) {
                if (i * boxWidth + j < text.length()) {
                    textBox.append(text.charAt(i * boxWidth + j));
                } else {
                    textBox.append(" ");
                }
            }
            textBox.append(TileChars.borderSide + "\n");
        }
        return textBox.toString();
    }
}
