package wasteland.game;

// Import libraries
import java.util.concurrent.*;
import java.io.IOException;

import wasteland.world.*;
import wasteland.entity.*;
import wasteland.global.*;
import wasteland.journal.*;
import wasteland.levels.*;
import wasteland.renderer.*;
import wasteland.items.*;

public class Game {

    // Game variables
    private static boolean gameRunning;
    private static World world;
    private static Player player;
    private static Thread rendererThread;
    private static RenderLayers mainLayer;
    private static RenderLayers overlayLayer;
    private static Thread inputThread;
    private static BlockingQueue<Integer> queue;
    private static Journal journal;
    private static String message = "";

    public static void startGame() {
        // Create a new Player
        player = new Player("Timmy", 1, 1, 100, 100, 100, 10, true, world, 7);

        // Create a new Journal
        journal = new Journal();
        journal.nextDay();

        // Create a renderer
        mainLayer = new RenderLayers(RenderLayers.Journal);
        overlayLayer = new RenderLayers(RenderLayers.None);
        rendererThread = new Thread(new Renderer(world, journal, player, mainLayer, overlayLayer));
        rendererThread.start();

        // Create a new Input
        queue = new LinkedBlockingQueue<Integer>();
        inputThread = new Thread(new Input(queue));
        inputThread.start();

        // Start the game loop
        gameRunning = true;
        gameLoop();

        // Exit the game
        System.exit(0);
    }

    private static void gameLoop() {
        // Run the game loop until the game is stopped
        while (gameRunning) {
            try {
                playerInput();
                // Sleep for a bit
                Thread.sleep(1000 / Constants.fps);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void playerInput() {
        // Get input
        Integer keyCode;
        if ((keyCode = queue.poll()) != null) {
            // Global input
            switch (keyCode) {
                case 113: // Q
                    inputThread.interrupt();
                    rendererThread.interrupt();
                    gameRunning = false;
                    break;
                case 104: // H
                    if (overlayLayer.getRenderLayer() == RenderLayers.Help) {
                        overlayLayer.setRenderLayer(RenderLayers.None);
                    } else {
                        overlayLayer.setRenderLayer(RenderLayers.Help);
                    }
                    break;
                case 105: // I
                    if (overlayLayer.getRenderLayer() == RenderLayers.Inventory) {
                        overlayLayer.setRenderLayer(RenderLayers.None);
                    } else {
                        overlayLayer.setRenderLayer(RenderLayers.Inventory);
                    }
                    break;

                default: // From this point on, the input is dependant on the main layer
                    if (mainLayer.getRenderLayer() == RenderLayers.World) {
                        worldInput(keyCode);
                    } else if (mainLayer.getRenderLayer() == RenderLayers.Journal) {
                        journalInput(keyCode);
                    }
                    break;
            }
        }
    }

    // Journal navigation
    private static void journalInput(int keyCode) {
        if (journal.getTab() == 1) {
            journal.proccessInput(keyCode);
        }
        switch (keyCode) {
            case 101: // E
                journal.nextTab();
                break;
            case 102: // F
                if (journal.getTab() == 3) {
                    // Check if the player has been rescued
                    if (journal.getDay() == journal.rescueDay()) {
                        Game.endGame(0);
                    }
                    // Go on a expedition
                    // Load a random level
                    Level level = new LevelSelector().getRandom();
                    loadLevel(level, 0, 0);
                    mainLayer.setRenderLayer(RenderLayers.World);
                    player.playerTick(10);
                }
                break;
            case 103: // G
                if (journal.getTab() == 3) {
                    // Rest
                    journal.nextDay();
                    player.playerTick(10);
                }
                break;
            case 49: // 1
                if (journal.getTab() == 2) {
                    // Eat food
                    player.consumeFood(1);
                }
                break;
            case 50: // 2
                if (journal.getTab() == 2) {
                    // Drink water
                    player.consumeWater(1);
                }
                break;
            case 51: // 3
                if (journal.getTab() == 2) {
                    // Cure the player if infected
                    player.cureInfection();
                }
                break;

            default:
                break;
        }
    }

    // Player movement
    private static void worldInput(int direction) {
        // Move the player if the player is not in a menu
        if (overlayLayer.getRenderLayer() == RenderLayers.None) {
            switch (direction) {
                case 119: // W
                    player.up();
                    break;
                case 115: // S
                    player.down();
                    break;
                case 100: // D
                    player.right();
                    break;
                case 97: // A
                    player.left();
                    break;
                default:
                    break;
            }
        }
    }

    // Loads/Changes the current level
    private static void loadLevel(Level level, int x, int y) {
        // Create a new World with the given level and load entities and items.
        world = new World(level.getWorld(), level.getEntities(), level.getItems());

        if (rendererThread != null) {
            // Stop the current renderer
            rendererThread.interrupt();
            // Create a new renderer
            rendererThread = new Thread(new Renderer(world, journal, player, mainLayer, overlayLayer));
            rendererThread.start();
        }

        if (player != null) {
            // Change the player's world
            player.changeWorld(world);
            // Set the player's position to the spawn point
            player.move(x, y);
        }
    }

    // End the game
    public static void endGame(int exitCode) {
        // End the game
        inputThread.interrupt();
        overlayLayer.setRenderLayer(RenderLayers.None);
        mainLayer.setRenderLayer(RenderLayers.None);
        rendererThread.interrupt();
        System.out.println("\033[H\033[2J"); // Clear the screen.
        switch (exitCode) {
            case 0: // Player was rescued
                System.out.println("You were rescued!");
                System.out.println("You survived " + journal.getDay() + " days.");
                System.out.println("Here are your stats:");
                break;
            case 1: // Player is dead
                System.out.println("You died!");
                System.out.println("You survived " + journal.getDay() + " days.");
                System.out.println("Here are your stats:");
                break;
            default: // Unknown exit code
                System.out.println("Unknown exit code");
                System.out.println("Ending the game anyway...");
                break;
        }
        // Print the game's statistics
        printStats();
        gameRunning = false;
    }

    // Game stats
    private static void printStats() {
        System.out.println("Player stats:");
        System.out.println("Name: " + player.getName());
        System.out.println("Health: " + player.getHealth());
        System.out.println("Hunger: " + player.getHunger());
        System.out.println("Thirst: " + player.getThirst());
        System.out.println("Infection: " + player.isInfected());
        System.out.println("");
        // Print player's inventory
        System.out.println("Inventory:");
        System.out.println("🥫: " + player.getFood());
        System.out.println("🍶: " + player.getWater());
        for (Item item : player.getItems()) {
            if (item != null) {
                System.out.println(item.getName());
            }
        }
        System.out.println("");
        // Pause the game until the user presses enter
        System.out.println("Press enter to exit...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Set a message to be displayed
    public static void setMessage(String message) {
        Game.message = message;
    }

    // Get the message
    public static String getMessage() {
        return message;
    }

    // Get main layer
    public static RenderLayers getMainLayer() {
        return mainLayer;
    }

    // Get Journal
    public static Journal getJournal() {
        return journal;
    }

    // Get the player
    public static Player getPlayer() {
        return player;
    }
}
