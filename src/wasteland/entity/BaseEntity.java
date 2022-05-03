package wasteland.entity;

import wasteland.renderer.*;
import wasteland.world.*;

abstract class BaseEntity implements Entity {

    private String name;
    private int x, y;
    private int health;
    private int maxHealth;
    private int attack;
    private boolean isSolid;
    private World world;

    public BaseEntity(String name, int x, int y, int maxHealth, int attack, boolean isSolid, World world) {

        this.name = name;
        this.x = x;
        this.y = y;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.attack = attack;
        this.isSolid = isSolid;
        this.world = world;

        if (world != null) {
            world.addEntity(this);
        }

    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public String getColor() {
        return ColouredSysOut.ANSI_PURPLE;
    }

    public String getCharacter() {
        return "??";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;

        // if collitions(shit happens) {}
    }

    public void up() {
        this.move(this.x, this.y - 1);
    }

    public void down() {
        this.move(this.x, this.y + 1);
    }

    public void left() {
        this.move(this.x - 1, this.y);
    }

    public void right() {
        this.move(this.x + 1, this.y);
    }

    public void changeWorld(World world) {
        if (this.world != null) {
            this.world.removeEntity(this);
        }

        this.world = world;
        world.addEntity(this);
    }

    public void damage(int damage) {
        
        if (damage < 0) {
            damage = 0;
        }
        
        this.health -= damage;

        if (this.health <= 0) {
            this.die();
        }

    }

    public void heal(int heal) {
        
        if (health + heal > maxHealth) {
            health = maxHealth;
        } else {
        this.health += heal;
        }

    }

    public void die() {
        this.world.removeEntity(this);

    }

    public boolean isSolid() {
        return isSolid;
    }

    public void setSolid(boolean isSolid) {
        this.isSolid = isSolid;
    }



}
