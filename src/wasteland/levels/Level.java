package wasteland.levels;

import wasteland.entity.*;
import wasteland.items.*;

public interface Level {
    // A level is a 2D array of integers with
    // entities and items.

    int[][] getWorld();
    Entity[] getEntities();
    Item[] getItems();
}
