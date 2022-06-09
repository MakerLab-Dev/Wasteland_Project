package wasteland.entity;

import wasteland.items.Item;
import wasteland.renderer.*;
import wasteland.world.*;

public abstract class Collisions {
    boolean checkCollision(int x, int y, World world, Entity entity) {
        // Check world bounds
        if (x < 0 || y < 0 || x >= world.getWorldWidth() || y >= world.getWorldHeight()) {
            return true;
        }

        // Check door collision
        if (world.getTile(x, y) == Tile.DoorClosed) {
            if (entity instanceof Player) {
                Item key = ((Player) entity).getInventory().getItemByName("🔑 Key");
                if (key != null) {
                    ((Player) entity).getInventory().removeItem(key);
                    world.setTile(x, y, Tile.DoorOpen);
                    return false;
                }
            }
            return true;
        }
        // Check tile collision
        if (TileChars.getTileCollider(world.getTile(x, y))) {
            return true;
        }

        // Check entity collision
        Entity ent = world.getEntity(x, y);
        if (ent != null) {
            // If the player collides with an entity,
            // interact with it.
            if (entity instanceof Player) {
                ent.interact(entity);
            }
            if (ent.isSolid()) {
                return true;
            }
        }

        // Check item collision
        Item item = world.getItem(x, y);
        if (item != null) {
            // If the player collides with an item,
            // interact with it.
            if (entity instanceof Player) {
                item.interact((Player) entity);
            }
            if (item.isSolid()) {
                return true;
            }
        }

        // No collision
        return false;
    }
}
