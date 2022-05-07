package wasteland.renderer;

import java.util.*;
import wasteland.world.*;

public final class TileChars {
    protected static final String borderSide = "┃";
    protected static final String borderTop = "━━";
    protected static final String borderTopLeft = "┏";
    protected static final String borderTopRight = "┓";
    protected static final String borderBottomLeft = "┗";
    protected static final String borderBottomRight = "┛";
    protected static final String borderSideIntersectLeft = "┣";
    protected static final String borderSideIntersectRigth = "┫";

    private static final HashMap<Integer, String[]> tileColours = new HashMap<Integer, String[]>() {
        {
            put(Tile.Air, new String[] { ColouredSysOut.ANSI_BG_GREEN, ColouredSysOut.ANSI_WHITE });
            put(Tile.Grass, new String[] { ColouredSysOut.ANSI_BG_GREEN, ColouredSysOut.ANSI_BRIGHT_GREEN });
            put(Tile.Water, new String[] { ColouredSysOut.ANSI_BG_BLUE, ColouredSysOut.ANSI_BRIGHT_BLUE });
            put(Tile.Sand, new String[] { ColouredSysOut.ANSI_BG_YELLOW, ColouredSysOut.ANSI_BRIGHT_YELLOW });
            put(Tile.Snow, new String[] { ColouredSysOut.ANSI_BRIGHT_BG_WHITE, ColouredSysOut.ANSI_WHITE });
            put(Tile.Wall, new String[] { ColouredSysOut.ANSI_BRIGHT_BG_YELLOW, ColouredSysOut.ANSI_BRIGHT_BLACK });
        }
    };
    private static final HashMap<Integer, String> tileChars = new HashMap<Integer, String>() {
        {
            put(Tile.Air, "  ");
            put(Tile.Grass, "::");
            put(Tile.Water, "~~");
            put(Tile.Sand, "..");
            put(Tile.Snow, "**");
            put(Tile.Wall, "  ");
        }
    };
    private static final HashMap<Integer, Boolean> tileColliders = new HashMap<Integer, Boolean>() {
        {
            put(Tile.Air, false);
            put(Tile.Grass, false);
            put(Tile.Water, false);
            put(Tile.Sand, false);
            put(Tile.Snow, false);
            put(Tile.Wall, true);
        }
    };

    // TileColour gets the colour to represent
    // the given tile type returning an array
    // with bg color and fg colour.
    public static String[] getTileColour(int tile) {
        return tileColours.getOrDefault(tile, new String[] { ColouredSysOut.ANSI_BG_PURPLE, ColouredSysOut.ANSI_BLACK });
    }

    // TileChar gets the string to represent
    // the given tile type.
    public static String getTileChar(int tile) {
        return tileChars.getOrDefault(tile, "??");
    }

    // TileCollider gets whether the given tile
    // is collidable or not.
    public static boolean getTileCollider(int tile) {
        return tileColliders.getOrDefault(tile, false);
    }
}
