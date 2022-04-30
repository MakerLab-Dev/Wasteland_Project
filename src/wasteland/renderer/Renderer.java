package wasteland.renderer;

import java.util.Collections;

import wasteland.global.*;
import wasteland.world.*;
import wasteland.entity.*;

public class Renderer implements Runnable {
    // A Renderer is a class which renders
    // a world and HUD to the terminal, based on
    // the player's position.
    private World world;
    private Player player;

    // Temporary variables for the renderer to save memory.
    private int tile;
    private String bg, fg;
    private String character;
    private Entity entity;

    // Renderer constructs a new Renderer to render a
    // World and HUD to the terminal.
    public Renderer(World world, Player player) {
        this.world = world;
        this.player = player;
    }

    // Renders the HUD and the world at the specified coordinates.
    public void render(int x, int y, int viewDistance) {
        // Initialize the rendered screen with a new StringBuilder.
        StringBuilder renderedScreen = new StringBuilder(ColouredSysOut.ANSI_RESET);
        // Render the HUD.
        renderedScreen.append(drawBox(0));
        renderedScreen.append(drawTextBox("PLACEHOLDER"));
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
                        character = entity.getCharacter();
                        fg = entity.getColor();
                        // Get the background color of the title where the entity is standing.
                        bg = TileChars.getTileColour(world.getTile(i, j))[0];
                    } else { // If there is no entity, render the tile.
                        tile = world.getTile(i, j);
                        bg = TileChars.getTileColour(tile)[0];
                        fg = TileChars.getTileColour(tile)[1];
                        character = TileChars.getTileChar(tile);
                    }

                    // Render the current coordinates.
                    renderedScreen.append(fg + bg + character);
                } else {
                    renderedScreen.append(ColouredSysOut.ANSI_BG_BLACK + "..");
                }
            }
            renderedScreen.append(ColouredSysOut.ANSI_RESET + TileChars.borderSide + "\n");
        }
        renderedScreen.append(drawBox(2));
        renderedScreen.append(drawTextBox("PLACEHOLDER"));
        renderedScreen.append(drawBox(1));
        System.out.print(ColouredSysOut.ANSI_BG_BLACK + ColouredSysOut.ANSI_WHITE);

        int mode = 0;
        if (mode == 0) {
            char escCode = 0x1B;
            int row = 0;
            int column = 0;
            System.out.print(String.format("%c[%d;%df", escCode, row, column));
        } else {
            System.out.print("\033[H\033[2J"); // Clear the screen.
        }

        System.out.println(renderedScreen);
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                // Render the world and HUD at the player's coordinates.
                render(this.player.getX(), this.player.getY(), this.player.getViewDistance());
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
