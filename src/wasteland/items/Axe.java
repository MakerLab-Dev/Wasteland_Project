package wasteland.items;

import wasteland.entity.*;
import wasteland.renderer.*;
import wasteland.world.*;

public class Axe extends BaseItem {

    // Constructs a new axe item with the
    // specified parameters.
    public Axe(int x, int y, World world) {
        super("🪓 Axe", x, y, true, false, world);
    }

    // Returns the string to render the item.
    public String getCharacter() {
        return "🪓";
    }

    // Returns the color of the item.
    public String getColor() {
        return ColouredSysOut.ANSI_RED;
    }

    public void use(Player player) {
        // This item cannot be used.
        // It will be handled by the attack system
    }
}
