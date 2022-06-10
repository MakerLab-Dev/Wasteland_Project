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
        this.food = 0;
        this.water = 0;
        this.maxFood = maxFood;
        this.maxWater = maxWater;
        this.items = new Item[maxItems];
    }

    // Returns the food in the inventory.
    public int getFood() {
        return food;
    }

    // Returns the max food in the inventory.
    public int getMaxFood() {
        return maxFood;
    }

    // Returns the water in the inventory.
    public int getWater() {
        return water;
    }

    // Returns the max water in the inventory.
    public int getMaxWater() {
        return maxWater;
    }

    // Returns the items in the inventory.
    public Item[] getItems() {
        return items;
    }

    // Returns the first item that matches the
    // especified name.
    public Item getItemByName(String name) {
        for (int i = 0; i < items.length; i++) {
            if (items[i]!=null && items[i].getName().equals(name)) {
                return items[i];
            }
        }
        return null;
    }

    // Adds the specified amount of food to the inventory.
    public boolean addFood(int amount) {
        if (food == maxFood) {
            return false;
        }
        food+=amount;
        if (food>maxFood) {
            food=maxFood;
        }
        return true;
    }

    // Adds the specified amount of water to the inventory.
    public boolean addWater(int amount) {
        if (water == maxWater) {
            return false;
        }
        water+=amount;
        if (water>maxWater) {
            water=maxWater;
        }
        return true;
    }

    // Adds the specified item to the inventory.
    public boolean addItem(Item item) {
        for (int i=0; i<items.length; i++) {
            if (items[i] == null) {
                items[i]=item;
                return true;
            } 
        }
        return false;
    }

    // Removes the specified amount of food from the inventory.
    public void removeFood(int amount) {
        food-=amount;
    }

    // Removes the specified amount of water from the inventory.
    public void removeWater(int amount) {
        water-=amount;
    }

    // Removes the specified item from the inventory.
    public void removeItem(Item item) {
        for (int i=0; i<items.length; i++) {
            if (items[i] == item) {
                items[i]=null;
                return;
            } 
        }
    }
}
