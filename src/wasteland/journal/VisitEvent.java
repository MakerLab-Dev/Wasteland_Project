package wasteland.journal;

import java.util.Random;

import wasteland.game.Game;
import wasteland.items.*;

public class VisitEvent extends EventBase {

    private boolean eventAnswered = false;

    public VisitEvent() {
        super(25);
        this.entry = "Someone is knocking at your door, do you want to open it? (y/n)";
    }

    public void interaction() {
        // Nothing at first
    }

    public void inputProccessor(int keyCode) {
        if (eventAnswered) return;
        if (keyCode == 121) { // Y
            eventAnswered = true;
            Inventory playerInv = Game.getPlayer().getInventory();
            Random rand = new Random();
            if (Math.random() > 0.5) {
                this.entry += " You open the door and see a group of bandits, they are armed with a variety of weapons and are asking for some food and water. You are forced to give them some of your supplies.";
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
            } else {
                if (playerInv.getWater() <= 0) {
                    this.entry += " You open the door and see an old man asking for some water, you don't have any so he turns around disappointed";
                } else {
                    this.entry += " You open the door and see an old man asking for some water, you give him some and he hands you a small axe, it looks very fragile but it might come in handy during an expedition";
                    Game.getPlayer().pickUpItem(new Axe(0, 0, null));
                }
            }
        } else if (keyCode == 110) { // N
            eventAnswered = true;
            this.entry += " You decide to not open the door, the person is gone.";
        }
    }

}
