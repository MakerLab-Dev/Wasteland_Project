package wasteland.items;

import wasteland.entity.*;
import wasteland.renderer.*;
import wasteland.world.*;

public class Key extends BaseItem {

    // Constructs a new key item with the
    // specified parameters.
    public Key(int x, int y, World world) {
        super("🔑 Key", x, y, true, false, world);
    }

    // Returns the string to render the item.
    public String getCharacter() {
        return "🔑";
    }

    // Returns the color of the item.
    public String getColor() {
        return ColouredSysOut.ANSI_RED;
    }

    public void use(Player player) {
        // This item cannot be used.
        // It will be handled by the collisions system
    }
}
