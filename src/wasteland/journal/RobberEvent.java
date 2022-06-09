package wasteland.journal;

import java.util.Random;

import wasteland.game.Game;
import wasteland.items.*;

public class RobberEvent extends EventBase {

    public RobberEvent() {
        super(10);
        this.entry = "This morning you found the door opened, it appears that you have been robbed! You should look closely for any items you may have lost.";
    }

    public void interaction() {
        Inventory playerInv = Game.getPlayer().getInventory();
        Random rand = new Random();

        // Remove food from inventory
        if (Math.random() < 0.4) {
            playerInv.removeFood(rand.nextInt(4 - 2) + 2);
            if (playerInv.getFood() < 0) {
                playerInv.addFood(-playerInv.getFood());
            }
        }

        // Remove water from inventory
        if (Math.random() < 0.2) {
            playerInv.removeWater(rand.nextInt(4 - 2) + 2);
            if (playerInv.getWater() < 0) {
                playerInv.addWater(-playerInv.getWater());
            }
        }

        // Remove items from inventory
        for (Item item : playerInv.getItems()) {
            if (Math.random() < 0.3) {
                playerInv.removeItem(item);
            }
        }
    }

    // Not needed for this type of event
    public void inputProccessor(int keyCode) {}

}
