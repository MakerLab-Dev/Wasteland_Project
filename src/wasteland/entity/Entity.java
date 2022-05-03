package wasteland.entity;

import wasteland.world.*;

public interface Entity {
    // An Entity is the interface for anything which
    // exists in the world and can move around.

    //info
    String getName();
    void setName(String name);

    // Movement
    void up();
    void down();
    void left();
    void right();
    void move(int x, int y);

    // Position
    int getX();
    int getY();
    void changeWorld(World world);

    // Rendering
    String getCharacter();
    String getColor();

    // Stats
    int getMaxHealth();
    int getHealth();
    int getAttack();

    // Damage
    void damage(int damage);
    void heal(int heal);
    void die();

    // Collisions
    boolean isSolid();
    void setSolid(boolean isSolid);
    
}
