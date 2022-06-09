package wasteland.items;

import wasteland.world.*;
import wasteland.entity.*;

public interface Item {
    // An Item is the interface for anything which
    // exists in the world, can be picked up and used.

    // Info
    String getName();

    // Movement
    void move(int x, int y);

    // Position
    int getX();
    int getY();
    void changeWorld(World world);

    // Rendering
    String getCharacter();
    String getColor();

    // Collisions
    boolean isSolid();
    void setSolid(boolean isSolid);

    // Interaction
    boolean isInteractable();
    void setInteractable(boolean isInteractable);
    void interact(Player player);
    void use(Player player);
}
