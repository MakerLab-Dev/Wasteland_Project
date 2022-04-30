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

        // TODO: Add a menu
        Game.startGame();
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
