package wasteland.entity.Enemies;

import wasteland.entity.*;
import wasteland.world.*;

public class Bandit extends BaseEnemy {

    // A Bandit is an entity which is controlled by
    // the CPU and can attack a player.

    // Constructs a new bandit with the
    // specified parameters.
    public Bandit(String name, int x, int y, int maxHealth, int attack, boolean isSolid, World world, int attackCooldownMax, int attackDistance, int movementDelay) {
        super(name, x, y, maxHealth, attack, isSolid, world, attackCooldownMax, attackDistance, movementDelay);
    }

    // Character returns a character to
    // render the enemy.
    @Override
    public String getCharacter() {
        return "🥷";
    }

    // Color returns the color of the enemy.
    @Override
    public String getColor() {
        return "\u001B[31m";
    }

}
