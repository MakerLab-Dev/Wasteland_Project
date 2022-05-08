package wasteland.levels;

import wasteland.entity.*;
import wasteland.items.*;

public abstract class BaseLevel implements Level {
    protected int[][] world;
    protected Entity[] entities = new Entity[0];
    protected Item[] items = new Item[0];

    public int[][] getWorld() {
        return world;
    }

    public Entity[] getEntities() {
        return entities;
    }

    public Item[] getItems() {
        return items;
    }
}
