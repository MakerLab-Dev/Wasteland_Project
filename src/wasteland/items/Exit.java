package wasteland.items;

import wasteland.entity.*;
import wasteland.game.Game;
import wasteland.renderer.*;
import wasteland.world.*;

public class Exit extends BaseItem {
    
    // Constructs a new exit with the
    // specified parameters.
    public Exit(int x, int y, World world) {
        super("Exit", x, y, true, false, world);
    }

    // Returns the string to render the exit.
    public String getCharacter() {
        return "🌀";
    }

    // Returns the color of the item.
    public String getColor() {
        return ColouredSysOut.ANSI_BLUE;
    }

    @Override
    public void interact(Player player) {
        // Clear last message.
        Game.setMessage("");
        // End the expedition.
        Game.getMainLayer().setRenderLayer(RenderLayers.Journal);
        Game.getJournal().nextDay();
    }

    public void use(Player player) {
        // This item cannot be used.
    }
}
