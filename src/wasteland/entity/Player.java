package wasteland.entity;

import wasteland.game.Game;
import wasteland.items.*;
import wasteland.world.*;

public class Player extends BaseEntity {

    // A Player is an entity which is controlled by
    // a real-life player.
    private int hunger, thirst;
    private int maxHunger, maxThirst;
    private boolean isInfected;
    private int viewDistance;
    private Inventory inventory;

    // Constructs a new player with the
    // specified parameters.
    public Player(String name, int x, int y, int maxHealth, int maxHunger, int maxThirst, int attack, boolean isSolid,
            World world, int viewDistance) {
        super(name, x, y, maxHealth, attack, isSolid, world);
        this.hunger = maxHunger;
        this.thirst = maxThirst;
        this.maxHunger = maxHunger;
        this.maxThirst = maxThirst;
        this.isInfected = false;
        this.viewDistance = viewDistance;
        this.inventory = new Inventory(10, 10, 10);
    }

    // Returns the maximum hunger of the player.
    public int getMaxHunger() {
        return this.maxHunger;
    }

    // Returns the maximum thirst of the player.
    public int getMaxThirst() {
        return this.maxThirst;
    }

    // Returns the hunger of the player.
    public int getHunger() {
        return this.hunger;
    }

    // Returns the thirst of the player.
    public int getThirst() {
        return this.thirst;
    }

    // Returns whether the player is infected.
    public boolean isInfected() {
        return this.isInfected;
    }

    // Returns the view distance of the player.
    public int getViewDistance() {
        return this.viewDistance;
    }

    // Sets the view distance of the player.
    public void setViewDistance(int viewDistance) {
        this.viewDistance = viewDistance;
    }

    // Character returns a smiley face character to
    // render the player.
    @Override
    public String getCharacter() {
        return "🤠";
    }

    // Color returns the color of the player.
    @Override
    public String getColor() {
        return "\u001B[94m";
    }

    // interact is used to interact with entities.
    // The player is not interactable.
    @Override
    public void interact(Entity entity) {
        // Do Nothing
    }

    // tick is used to update entities but
    // playerTick is used instead in the player class.
    @Override
    public void tick(Player player) {
        // Do Nothing
    }

    // playerTick updates the player.
    public void playerTick(int multiplier) {
        // If the player is infected, decrease health.
        if (this.isInfected) {
            this.damage(2 * multiplier);
        }
        // If the player is too hungry, decrease health.
        if (this.hunger <= 0) {
            this.damage(1 * multiplier);
        } else {
            // Decrease hunger.
            this.hunger -= 1 * multiplier;
            if (this.hunger < 0) {
                this.damage(this.hunger * -1);
                this.hunger = 0;
            }
        }
        // If player is too thirsty, decrease health.
        if (this.thirst <= 0) {
            this.damage(2 * multiplier);
        } else {
            // Decrease thirst.
            this.thirst -= 2 * multiplier;
            if (this.thirst < 0) {
                this.damage(this.thirst * -1);
                this.thirst = 0;
            }
        }
        // Slowly heal the player if they are not infected
        // and not too hungry or too thirsty.
        if (this.hunger > 0 && this.thirst > 0 && this.isInfected == false) {
            this.heal(2 * multiplier);
        }
    }

    // Adds the specified food to the player's inventory.
    // Returns true if the food was added, false otherwise.
    public boolean pickUpFood(Food food) {
        // Add the food to the inventory.
        return this.inventory.addFood(food.getAmount());
    }

    // Adds the specified water to the player's inventory.
    // Returns true if the water was added, false otherwise.
    public boolean pickUpWater(Water water) {
        // Add the water to the inventory.
        return this.inventory.addWater(water.getAmount());
    }

    // Adds the specified item to the player's inventory.
    // Returns true if the item was added, false otherwise.
    public boolean pickUpItem(Item item) {
        // Add the item to the inventory.
        return this.inventory.addItem(item);
    }

    // Returns the amount of food in the player's inventory.
    public int getFood() {
        return this.inventory.getFood();
    }

    // Returns the max amount of food in the player's inventory.
    public int getMaxFood() {
        return this.inventory.getMaxFood();
    }

    // Returns the amount of water in the player's inventory.
    public int getWater() {
        return this.inventory.getWater();
    }

    // Returns the max amount of water in the player's inventory.
    public int getMaxWater() {
        return this.inventory.getMaxWater();
    }

    // Returns the items in the player's inventory.
    public Item[] getItems() {
        return this.inventory.getItems();
    }

    // Consumes the specified amount of food.
    public void consumeFood(int amount) {
        if (this.inventory.getFood() >= amount && this.hunger < this.maxHunger) {
            this.inventory.removeFood(amount);
            this.hunger += 10;
            this.thirst += 2;
            if (this.hunger > maxHunger)
                this.hunger = maxHunger;
            if (this.thirst > maxThirst)
                this.thirst = maxThirst;
        }
    }

    // Consumes the specified amount of water.
    public void consumeWater(int amount) {
        if (this.inventory.getWater() >= amount && this.thirst < this.maxThirst) {
            this.inventory.removeWater(amount);
            this.thirst += 10;
            if (this.thirst > maxThirst)
                this.thirst = maxThirst;
        }
    }

    // Cure the player from infection.
    public void cureInfection() {
        Item medkit = this.inventory.getItemByName("💉 Medkit");
        if (this.isInfected && medkit != null) {
            this.inventory.removeItem(medkit);
            this.isInfected = false;
        }
    }

    @Override
    public void die() {
        Game.endGame(1);
    }
}
