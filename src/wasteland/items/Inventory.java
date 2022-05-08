package wasteland.items;

public class Inventory {
    // A Inventory is the class for the player's
    // inventory.

    private int food, water;
    private int maxFood, maxWater;
    private Item items[];

    // Constructs a new inventory with the
    // specified parameters.
    public Inventory(int maxFood, int maxWater, int maxItems) {
        //
    }

    // Returns the food in the inventory.
    public int getFood() {
        //
    }

    // Returns the max food in the inventory.
    public int getMaxFood() {
        //
    }

    // Returns the water in the inventory.
    public int getWater() {
        //
    }

    // Returns the max water in the inventory.
    public int getMaxWater() {
        //
    }

    // Returns the items in the inventory.
    public Item[] getItems() {
        //
    }

    // Returns the first item that matches the
    // especified name.
    public Item getItemByName(String name) {
        //
    }

    // Adds the specified amount of food to the inventory.
    public boolean addFood(int amount) {
        //
    }

    // Adds the specified amount of water to the inventory.
    public boolean addWater(int amount) {
        //
    }

    // Adds the specified item to the inventory.
    public boolean addItem(Item item) {
        //
    }

    // Removes the specified amount of food from the inventory.
    public void removeFood(int amount) {
        //
    }

    // Removes the specified amount of water from the inventory.
    public void removeWater(int amount) {
        //
    }

    // Removes the specified item from the inventory.
    public void removeItem(Item item) {
        //
    }
}
