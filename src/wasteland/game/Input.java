package wasteland.game;

import java.util.concurrent.*;
import wasteland.global.*;

public class Input implements Runnable {

    private BlockingQueue<Integer> queue;

    public Input(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                // Read the input from the user and puts it into the queue.
                queue.put(System.in.read());
                // Clear System.in since we only want the most recent input.
                System.in.skip(System.in.available());
                // Sleep for a bit to save system resources.
                Thread.sleep(1000 / Constants.fps);
            }
        } catch (ExceptionInInitializerError e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            // Input is interrupted.
        } catch (Exception e) {
            e.printStackTrace();
            run();
        }
    }

}
