package wasteland.entity;

import wasteland.world.*;

public abstract class BaseEntity extends Collisions implements Entity {
    // A BaseEntity is the abstract class for anything which
    // exists in the world and can move around.
    private String name;
    private int X, Y;
    private int health;
    private int maxHealth;
    protected int attack;
    private boolean isSolid;
    protected World world;

    // Constructs a new BaseEntity with the
    // specified parameters.
    public BaseEntity(String name, int x, int y, int maxHealth, int attack, boolean isSolid, World world) {
        this.name = name;
        this.X = x;
        this.Y = y;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.attack = attack;
        this.isSolid = isSolid;
        this.world = world;

        // Add the entity to the world.
        if (world != null) {
            world.addEntity(this);
        }
    }

    // Returns the name of the entity.
    public String getName() {
        return this.name;
    }

    // Changes the name of the entity.
    public void setName(String name) {
        this.name = name;
    }

    // Returns the X coordinate of the entity.
    public int getX() {
        return this.X;
    }

    // Returns the Y coordinate of the entity.
    public int getY() {
        return this.Y;
    }

    // Returns the maximum health of the entity.
    public int getMaxHealth() {
        return this.maxHealth;
    }

    // Returns the health of the entity.
    public int getHealth() {
        return this.health;
    }

    // Returns the attack damage of the entity.
    public int getAttack() {
        return this.attack;
    }

    // Character returns a string to
    // render the entity.
    public abstract String getCharacter();

    // Color returns the color of the entity.
    public abstract String getColor();

    // Returns whether the entity is solid.
    public boolean isSolid() {
        return this.isSolid;
    }

    // Sets whether the entity is solid.
    public void setSolid(boolean isSolid) {
        this.isSolid = isSolid;
    }

    // changeWorld changes the world of the entity.
    public void changeWorld(World world) {
        // Remove the entity from the old world.
        if (this.world != null) {
            this.world.removeEntity(this);
        }

        // Set the new world.
        this.world = world;

        // Add the entity to the new world.
        world.addEntity(this);
    }

    // Move moves the entity to the specified
    // coordinates.
    public void move(int x, int y) {
        if (!this.checkCollision(x, y, this.world, this)) {
            this.X = x;
            this.Y = y;
        }
    }

    // Up moves an entity one position upwards (negative Y).
    public void up() {
        this.move(this.X, this.Y - 1);
    }

    // Down moves an entity one position downwards (positive Y).
    public void down() {
        this.move(this.X, this.Y + 1);
    }

    // Left moves an entity one position to the left (negative X).
    public void left() {
        this.move(this.X - 1, this.Y);
    }

    // Right moves an entity one position to the right (positive X).
    public void right() {
        this.move(this.X + 1, this.Y);
    }

    // Damage damages the entity by the specified amount.
    public void damage(int amount) {
        this.health -= amount;

        if (this.health <= 0) {
            this.die();
        }
    }

    // Heal heals the entity by the specified amount.
    public void heal(int heal) {
        if (this.health + heal > this.maxHealth) {
            this.health = this.maxHealth;
        } else {
            this.health += heal;
        }
    }

    // Die kills the entity.
    public void die() {
        this.world.removeEntity(this);
    }

    // Interact interacts with the entity.
    public abstract void interact(Entity entity);

    // Tick updates the entity.
    public abstract void tick(Player player);
}
