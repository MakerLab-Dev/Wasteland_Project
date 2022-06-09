package wasteland.items;

import wasteland.game.Game;
import wasteland.entity.*;
import wasteland.world.*;

public abstract class BaseItem implements Item {
    // A BaseItem is the abstract class for anything which
    // exists in the world, can be picked up and used.
    private String name;
    private int X, Y;
    protected boolean isInteractable;
    private boolean isSolid;
    protected World world;

    // Constructs a new BaseItem with the
    // specified parameters.
    public BaseItem(String name, int x, int y, boolean isInteractable, boolean isSolid, World world) {
        this.name = name;
        this.X = x;
        this.Y = y;
        this.isInteractable = isInteractable;
        this.isSolid = isSolid;
        this.world = world;

        // Add the item to the world.
        if (world != null) {
            world.addItem(this);
        }
    }

    // Returns the name of the item.
    public String getName() {
        return this.name;
    }

    // Returns the X coordinate of the item.
    public int getX() {
        return this.X;
    }

    // Returns the Y coordinate of the item.
    public int getY() {
        return this.Y;
    }

    // getCharacter returns the string to
    // render the item.
    public abstract String getCharacter();

    // getColor returns the color of the item.
    public abstract String getColor();

    // Returns whether the item is interactable.
    public boolean isInteractable() {
        return this.isInteractable;
    }

    // Sets whether the item is interactable.
    public void setInteractable(boolean isInteractable) {
        this.isInteractable = isInteractable;
    }

    // Returns whether the item is solid.
    public boolean isSolid() {
        return this.isSolid;
    }

    // Sets whether the item is solid.
    public void setSolid(boolean isSolid) {
        this.isSolid = isSolid;
    }

    // changeWorld changes the world of the item.
    public void changeWorld(World world) {
        // Remove the item from the old world.
        if (this.world != null) {
            this.world.removeItem(this);
        }

        // Set the new world.
        this.world = world;

        // Add the item to the new world.
        world.addItem(this);
    }

    // Move moves the item to the specified
    // coordinates.
    public void move(int x, int y) {
        this.X = x;
        this.Y = y;
    }

    // Interact is called when the player interacts
    // with the item.
    public void interact(Player player) {
        // By default, pick up the item.
        if (this.isInteractable) {
            if (player.pickUpItem(this)) {
                Game.setMessage("You picked up a " + this.name + ".");
                this.world.removeItem(this);
                this.world = null;
            }
        }
    }

    // Use is called when the player uses the item.
    public abstract void use(Player player);
}
