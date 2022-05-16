package wasteland.items;

import wasteland.game.Game;
import wasteland.entity.*;
import wasteland.renderer.*;
import wasteland.world.*;

public class Food extends BaseItem {

    // The amount of food in the item
    private int amount;

    // Constructs a new food item with the
    // specified parameters.
    public Food(int x, int y, World world, int amount) {
        super("Food", x, y, true, false, world);
        this.amount = amount;
    }

    // Returns the amount of food in the item.
    public int getAmount() {
        return this.amount;
    }

    // Returns the string to render the item.
    public String getCharacter() {
        return "🥫";
    }

    // Returns the color of the item.
    public String getColor() {
        return ColouredSysOut.ANSI_BRIGHT_BLACK;
    }

    @Override
    public void interact(Player player) {
        if (player.pickUpFood(this)) {
            Game.setMessage("You picked up " + this.amount + " units of food.");
            this.world.removeItem(this);
            this.world = null;
        }
    }

    public void use(Player player) {
        // This item cannot be used.
    }
}
