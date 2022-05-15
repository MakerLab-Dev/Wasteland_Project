package wasteland.entity;

public class Player extends BaseEntity {

    private int hunger, thirst;
    private int maxHunger, maxThirst;
    private boolean isInfected;
    private int viewDistance;
    private Inventory inventory;

    public Player (String name, int x, int y, int maxHealth, int maxHunger, int maxThirst, int attack, boolean isSolid, World world, int viewDistance) {
        
        this.name = name;
        this.x = x;
        this.y = y;
        this.maxHealth = maxHealth;
        this.maxHunger = maxHunger;
        this.maxThirst = maxThirst;
        this.viewDistance = viewDistance;
        this.health = maxHealth;
        this.attack = attack;
        this.isSolid = isSolid;
        this.world = world;
    
        if (world != null) {
            world.addEntity(this);
        }

    }

    public int getMaxHunger() {
        return maxHunger;
    }
    public int getMaxThirst() {
        return maxThirst;
    }
    public int getHunger() {
        return hunger;
    }
    public int getThirst() {
        return thirst;
    }
    public boolean isInfected() {
        return isInfected;
    }
    public int getViewDistance() {
        return viewDistance;
    }
    public void setViewDistance(int viewDistance) {
        this.viewDistance = viewDistance;
    }
    public String getCharactr() {
        return "🤠";
    }
    public String getColor() {
        return "\u001B[94m";
    }
    public void die() {
        Game.endGame(1);
    }

}
