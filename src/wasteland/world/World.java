package wasteland.world;

import wasteland.entity.*;
import wasteland.items.*;

public class World {

    // A World contains a matrix of tiles. There
    // are [worldHeight * worldWidth] tiles.
    private int worldWidth;
    private int worldHeight;
    private int tiles[][];
    // Currently the max number of entities in a world is capped at 100.
    private Entity entities[] = new Entity[100];
    // Currently the max number of items in a world is capped at 100.
    private Item items[] = new Item[100];

    // World constructs a new world.
    public World(int[][] worldTiles, Entity[] entities, Item[] items) {
        // Set world constants.
        worldHeight = worldTiles.length;
        worldWidth = worldTiles[0].length;

        // Copy the world tiles into the world.
        tiles = worldTiles;

        // Copy the entities into the world.
        for (int i = 0; i < entities.length; i++) {
            this.entities[i] = entities[i];
            if (entities[i] != null) {
                entities[i].changeWorld(this);
            }
        }

        // Copy the items into the world.
        for (int i = 0; i < items.length; i++) {
            //
        }
    }

    // Returns the width of the world.
    public int getWorldWidth() {
        return worldWidth;
    }

    // Returns the height of the world.
    public int getWorldHeight() {
        return worldHeight;
    }

    // Returns the tile at the specified coordinates.
    public int getTile(int x, int y) {
        return this.tiles[y][x];
    }

    // Sets the tile at the specified coordinates.
    public void setTile(int x, int y, int tile) {
        this.tiles[y][x] = tile;
    }

    // Returns the entity at the specified coordinates.
    public Entity getEntity(int x, int y) {
        for (Entity ent : entities) {
            if (ent != null) {
                if (ent.getX() == x && ent.getY() == y) {
                    return ent;
                }
            }
        }
        return null;
    }

    // Adds an entity to the world.
    public void addEntity(Entity entity) {
        for (int i = 0; i < entities.length; i++) {
            if (entities[i] == null) {
                entities[i] = entity;
                return;
            }
        }
    }

    // Removes an entity from the world.
    // (Removes the reference to the entity, so that 
    // the garbage collector can clean it up.)
    public void removeEntity(Entity entity) {
        for (int i = 0; i < entities.length; i++) {
            if (entities[i] == entity) {
                entities[i] = null;
            }
        }
    }

    // Returns the item at the specified coordinates.
    public Item getItem(int x, int y) {
        //
    }

    // Adds a item to the world.
    public void addItem(Item item) {
        //
    }

    // Removes a item from the world.
    // (Removes the reference to the item, so that 
    // the garbage collector can clean it up.)
    public void removeItem(Item item) {
        //
    }
}
