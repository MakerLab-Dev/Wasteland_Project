package wasteland.entity;

import wasteland.game.Game;
import wasteland.world.*;

public abstract class BaseEnemy extends BaseEntity {

    // A BaseEnemy is the abstract class for any
    // enemy that moves around the world.
    protected int attackCooldown;
    // Cooldown is measured in ticks.
    protected int attackCooldownMax;
    protected int attackDistance;
    protected int movementDelay;
    protected int movementCooldown;

    // Constructs a new player with the
    // specified parameters.
    public BaseEnemy(String name, int x, int y, int maxHealth, int attack, boolean isSolid, World world,
            int attackCooldownMax, int attackDistance, int movementDelay) {
        super(name, x, y, maxHealth, attack, isSolid, world);
        this.attackCooldown = attackCooldownMax;
        this.attackCooldownMax = attackCooldownMax;
        this.attackDistance = attackDistance;
        this.movementDelay = movementDelay;
    }

    // Attacks an entity.
    public void attack(Entity entity) {
        // Attack the player.
        if (this.attackCooldown == 0) {
            this.attackCooldown = this.attackCooldownMax;
            Game.setMessage(
                    this.getName() + " attacks " + entity.getName() + " and does " + this.getAttack() + " damage!");
            entity.damage(this.getAttack());
        }
    }

    // interact is called when the player
    // interacts with the entity.
    public void interact(Entity entity) {
        Game.setMessage("You attack " + this.getName() + " and do " + entity.getAttack() + " damage! "
                + this.getHealth() + "/" + this.getMaxHealth());
        this.damage(entity.getAttack());
    }

    @Override
    // Die kills the entity.
    public void die() {
        Game.setMessage(this.getName() + " has died!");
        this.world.removeEntity(this);
    }

    // tick updates the entity.
    public void tick(Player player) {
        // Update the attack cooldown.
        if (this.attackCooldown != 0) {
            this.attackCooldown--;
        }
        // Update the movement cooldown.
        if (this.movementCooldown != 0) {
            this.movementCooldown--;
        }
        // If the player is in the attack distance, attack the player.
        int xDistance = (this.getX() - player.getX()) * (this.getX() - player.getX());
        int yDistance = (this.getY() - player.getY()) * (this.getY() - player.getY());
        if (Math.sqrt(xDistance + yDistance) <= attackDistance) {
            this.attack(player);
        } else {
            if (this.movementCooldown == 0) {
                // Otherwise, move towards the player.
                if (player.getX() - this.getX() > 0) {
                    this.right();
                } else if (player.getX() - this.getX() < 0) {
                    this.left();
                }
                if (player.getY() - this.getY() > 0) {
                    this.down();
                } else if (player.getY() - this.getY() < 0) {
                    this.up();
                }
                this.movementCooldown = this.movementDelay;
            }
        }
    }
}
