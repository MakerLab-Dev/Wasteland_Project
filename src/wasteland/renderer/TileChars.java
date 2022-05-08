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
            put(Tile.Air, new String[] { ColouredSysOut.ANSI_BG_BLACK, ColouredSysOut.ANSI_WHITE });
            put(Tile.Grass, new String[] { ColouredSysOut.ANSI_BG_GREEN, ColouredSysOut.ANSI_BRIGHT_GREEN });
            put(Tile.Stone, new String[] { ColouredSysOut.ANSI_BRIGHT_BG_BLACK, ColouredSysOut.ANSI_WHITE });
            put(Tile.Water, new String[] { ColouredSysOut.ANSI_BG_BLUE, ColouredSysOut.ANSI_BRIGHT_BLUE });
            put(Tile.Wall1, new String[] { ColouredSysOut.ANSI_BRIGHT_BG_BLACK, ColouredSysOut.ANSI_WHITE });
            put(Tile.Wall2, new String[] { ColouredSysOut.ANSI_BRIGHT_BG_BLACK, ColouredSysOut.ANSI_BLACK });
            put(Tile.DoorClosed, new String[] { ColouredSysOut.ANSI_BRIGHT_BG_BLACK, ColouredSysOut.ANSI_WHITE });
            put(Tile.DoorOpen, new String[] { ColouredSysOut.ANSI_BRIGHT_BG_BLACK, ColouredSysOut.ANSI_WHITE });
        }
    };
    private static final HashMap<Integer, String> tileChars = new HashMap<Integer, String>() {
        {
            put(Tile.Air, "  ");
            put(Tile.Grass, ";;");
            put(Tile.Stone, "..");
            put(Tile.Water, "~~");
            put(Tile.Wall1, "##");
            put(Tile.Wall2, "▓▓");
            put(Tile.DoorClosed, "🚪");
            put(Tile.DoorOpen, "[]");
        }
    };
    private static final HashMap<Integer, Boolean> tileColliders = new HashMap<Integer, Boolean>() {
        {
            put(Tile.Air, false);
            put(Tile.Grass, false);
            put(Tile.Stone, false);
            put(Tile.Water, true);
            put(Tile.Wall1, true);
            put(Tile.Wall2, true);
            put(Tile.DoorClosed, true);
            put(Tile.DoorOpen, false);
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
