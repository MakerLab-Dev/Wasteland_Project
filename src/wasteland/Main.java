package wasteland;

import wasteland.game.Game;
import wasteland.renderer.ColouredSysOut;

public class Main {
    public static void main(String[] args) throws Exception {
        // Initial setup
        // Set non-canonical mode
        changeTerminalMode();

        // Hook to shutdown to reset terminal mode before exiting
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                // Set background to black and foreground to red
                System.out.println(ColouredSysOut.ANSI_BG_BLACK + ColouredSysOut.ANSI_RED);
                System.out.println("\033[H\033[2J"); // Clear the screen.
                System.out.println("Exiting...");
                resetTerminalMode();
                System.out.println("Thank you for playing Wasteland Project!");
                System.out.println(ColouredSysOut.ANSI_RESET); // Reset terminal
            }
        });

        // Main menu
        System.out.println("\033[H\033[2J"); // Clear the screen.
        System.out.println("""
        ░█░█░█▀█░█▀▀░▀█▀░█▀▀░█░░░█▀█░█▀█░█▀▄░░░█▀█░█▀▄░█▀█░▀▀█░█▀▀░█▀▀░▀█▀
        ░█▄█░█▀█░▀▀█░░█░░█▀▀░█░░░█▀█░█░█░█░█░░░█▀▀░█▀▄░█░█░░░█░█▀▀░█░░░░█░
        ░▀░▀░▀░▀░▀▀▀░░▀░░▀▀▀░▀▀▀░▀░▀░▀░▀░▀▀░░░░▀░░░▀░▀░▀▀▀░▀▀░░▀▀▀░▀▀▀░░▀░
        """);
        System.out.println("Wasteland Project - A terminal-based game");
        System.out.println("Copyright (C) 2022  European University of the Atlantic");
        System.out.println("Check out the project on GitHub: https://github.com/MakerLab-Dev/Wasteland_Project");
        System.out.println("\n");

        // Main menu
        System.out.println("Please select an option:");
        System.out.println("1. Start a new game");
        System.out.println("2. Exit");

        // Wait for user input
        int input = -1;
        while (input < 1 || input > 3) {
            try {
                input = Integer.parseInt(System.console().readLine());
                if (input < 1 || input > 3) throw new Exception();
            } catch (Exception e) {
                System.out.println("Please enter a valid option.");
            }
        }

        // Start a new game
        if (input == 1) {
            System.out.println("\nStarting a new game...");
        Game.startGame();
        }
    }

    // Changes terminal mode to non-canonical mode
    // This is needed to get input without waiting for a newline
    private static void changeTerminalMode() {
        try {
            // Change terminal mode to non-canonical mode
            Process p = Runtime.getRuntime().exec(new String[] {
                    "sh",
                    "-c",
                    "stty -icanon < /dev/tty"
            });
            p.waitFor();
        } catch (Exception e) {
            System.out.println("Error changing terminal mode");
            // Exit the program
            System.exit(1);
        }
    }

    // Changes terminal mode to canonical mode
    // This is called after the program is finished to return the terminal to normal
    private static void resetTerminalMode() {
        try {
            // Change terminal mode to canonical mode
            Process p = Runtime.getRuntime().exec(new String[] {
                    "sh",
                    "-c",
                    "stty icanon < /dev/tty"
            });
            p.waitFor();
        } catch (Exception e) {
            System.out.println("Error resetting terminal mode");
            System.out.println("Execute 'stty icanon' manually");
        }
    }
}
