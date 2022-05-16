package wasteland.items;

import wasteland.game.Game;
import wasteland.entity.*;
import wasteland.renderer.*;
import wasteland.world.*;

public class Water extends BaseItem {

    // The amount of water in the item
    private int amount;

    // Constructs a new water item with the
    // specified parameters.
    public Water(int x, int y, World world, int amount) {
        super("Water", x, y, true, false, world);
        this.amount = amount;
    }

    // Returns the amount of water in the item.
    public int getAmount() {
        return this.amount;
    }

    // Returns the string to render the item.
    public String getCharacter() {
        return "🍶";
    }

    // Returns the color of the item.
    public String getColor() {
        return ColouredSysOut.ANSI_BLUE;
    }

    @Override
    public void interact(Player player) {
        if (player.pickUpWater(this)) {
            Game.setMessage("You picked up " + this.amount + " units of water.");
            this.world.removeItem(this);
            this.world = null;
        }
    }

    public void use(Player player) {
        // This item cannot be used.
    }
}
