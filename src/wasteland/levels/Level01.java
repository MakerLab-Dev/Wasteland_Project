package wasteland.levels;

//MiniMarket Level

import wasteland.entity.*;
import wasteland.entity.Enemies.*;
import wasteland.items.*;

public class Level01 extends BaseLevel {
    public Level01() {
        this.world = new int[][] {
            {2,2,2,2,1,1,1,1,1,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {2,0,2,2,1,0,3,0,0,0,0,0,0,1,2,2,1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,2,2,1,1,1,1,1,0,0,0,0,0,1,1,1,1,1},
            {2,0,2,2,1,0,1,0,0,7,0,7,0,1,2,2,1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,2,2,1,1,1,1,1,0,0,0,0,0,1,1,1,1,1},
            {2,0,2,2,1,0,1,0,0,7,0,7,0,1,2,2,1,0,0,0,0,0,3,0,0,0,0,0,4,0,0,0,0,0,1,2,2,1,1,1,1,1,0,0,0,0,0,1,1,1,1,1},
            {2,0,2,2,1,0,1,0,0,7,0,7,0,1,2,2,1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,2,2,1,1,1,1,1,0,0,0,0,0,1,1,1,1,1},
            {2,0,2,2,1,0,1,0,0,7,0,7,0,1,2,2,1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,2,2,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1},
            {2,0,2,2,1,0,1,0,0,0,0,7,0,1,2,2,1,1,1,3,1,1,1,0,0,0,0,0,1,1,1,4,1,1,1,2,2,1,1,1,0,0,0,0,0,0,0,0,0,1,1,1},
            {2,0,2,2,1,0,1,0,0,0,0,7,0,1,2,2,1,0,0,0,0,0,0,0,7,7,7,0,0,0,0,0,0,0,1,2,2,1,1,1,0,0,0,0,0,0,0,0,0,1,1,1},
            {2,0,2,2,1,0,1,0,0,0,0,0,0,1,2,2,1,0,0,0,0,0,0,0,7,5,7,0,0,0,0,0,0,0,1,2,2,1,1,1,0,0,0,0,0,0,0,0,0,1,1,1},
            {2,0,2,2,1,1,1,1,0,0,1,1,1,1,2,2,1,0,0,0,0,0,0,0,7,5,7,0,0,0,0,0,0,0,1,2,2,1,1,1,0,0,0,0,0,0,0,0,0,1,1,1},
            {2,0,2,2,2,2,2,2,0,0,2,2,2,2,2,2,1,0,0,0,0,0,0,0,7,7,7,0,0,0,0,0,0,0,1,2,2,1,1,1,0,0,0,0,0,0,0,0,0,1,1,1},
            {2,0,2,2,2,2,2,2,0,0,2,2,2,2,2,2,1,1,1,4,1,1,1,0,0,0,0,0,1,1,1,4,1,1,1,2,2,1,1,1,0,0,0,0,0,0,0,0,0,1,1,1},
            {2,0,2,2,2,2,2,2,0,0,2,2,2,2,2,2,1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,2,2,1,1,1,0,0,0,0,0,0,0,0,0,1,1,1},
            {2,0,2,2,2,2,2,2,0,0,2,2,2,2,2,2,1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,2,2,1,1,1,0,0,0,0,0,0,0,0,0,1,1,1},
            {2,0,0,0,0,0,0,0,0,0,2,2,2,2,2,2,1,0,0,0,0,0,4,0,0,0,0,0,4,0,0,0,0,0,1,2,2,1,1,1,0,0,0,0,0,0,0,0,0,1,1,1},
            {2,0,0,0,0,0,0,0,0,0,2,2,2,2,2,2,1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,2,2,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1},
            {2,2,2,2,2,2,2,2,0,0,2,2,2,2,2,2,1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,2,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {2,1,1,1,1,1,1,2,0,0,2,2,2,2,2,2,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,1,1,2,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {2,1,0,0,0,0,1,2,0,0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0,0,0,2,2,2,2,2,2,2,2,2,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {2,1,0,0,0,0,0,0,0,0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0,0,0,2,2,2,2,2,2,2,2,2,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {2,1,0,0,0,0,0,0,0,0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0,0,0,2,2,5,5,5,5,5,5,2,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {2,1,0,0,0,0,1,2,0,0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0,0,0,2,2,5,5,5,5,5,5,2,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {2,1,1,1,1,1,1,2,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,2,2,5,5,5,5,5,5,2,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {2,2,2,2,2,2,2,2,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,2,2,5,5,5,5,5,5,2,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {2,1,1,1,1,1,1,2,0,0,2,0,0,7,7,7,7,7,7,7,7,0,0,2,0,0,0,2,2,5,5,5,5,5,5,2,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {2,1,0,0,0,0,1,2,0,0,2,0,0,7,5,5,5,5,5,5,7,0,0,2,0,0,0,2,2,5,5,5,5,5,5,2,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {2,1,0,0,0,0,0,0,0,0,0,0,0,7,5,5,5,5,5,5,7,0,0,0,0,0,0,2,2,5,5,5,5,5,5,2,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {2,1,0,0,0,0,0,0,0,0,0,0,0,7,5,5,5,5,5,5,7,0,0,0,0,0,0,2,2,5,5,5,5,5,5,2,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {2,1,0,0,0,0,1,2,0,0,0,0,0,7,5,5,5,5,5,5,7,0,0,0,0,0,0,2,2,5,5,5,5,5,5,2,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {2,1,1,1,1,1,1,2,0,0,2,0,0,7,5,5,5,5,5,5,7,0,0,2,0,0,0,2,2,5,5,5,5,5,5,2,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {2,2,2,2,2,2,2,2,0,0,2,0,0,7,7,7,7,7,7,7,7,0,0,2,0,0,0,2,2,5,5,5,5,5,5,2,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {2,1,1,1,1,1,1,2,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,2,2,5,5,5,5,5,5,2,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {2,1,0,0,0,0,1,2,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,2,2,5,5,5,5,5,5,2,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {2,1,0,0,0,0,0,0,0,0,2,2,2,2,2,2,0,0,2,2,2,2,2,2,0,0,0,2,2,5,5,5,5,5,5,2,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {2,1,0,0,0,0,0,0,0,0,2,2,2,2,2,2,0,0,2,2,2,2,2,2,0,0,0,2,2,5,5,5,5,5,5,2,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {2,1,0,0,0,0,1,2,0,0,2,2,2,2,2,2,0,0,2,2,2,2,2,2,0,0,0,2,2,5,5,5,5,5,5,2,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {2,1,1,1,1,1,1,2,0,0,2,2,2,2,2,2,0,0,2,2,2,2,2,2,0,0,0,2,2,5,5,5,5,5,5,2,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {2,2,2,2,2,2,2,2,0,0,2,2,2,2,2,2,0,0,2,2,2,2,2,2,0,0,0,2,2,5,5,5,5,5,5,2,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {2,1,1,1,1,1,1,2,0,0,2,2,2,2,2,2,0,0,2,2,2,2,2,2,0,0,0,2,2,2,2,2,2,2,2,2,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {2,1,0,0,0,0,1,2,0,0,2,2,2,2,2,2,0,0,2,2,2,2,2,2,0,0,0,2,2,2,2,2,2,2,2,2,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {2,1,0,0,0,0,1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {2,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };

        this.entities = new Entity[] {
            new Zombie("Zombie", 5, 5, 100, 5, true, null, 1, 1, 2),
            new Zombie("Zombie", 3, 19, 100, 5, true, null, 1, 1, 2),
            new Zombie("Zombie", 3, 40, 100, 5, true, null, 1, 1, 2),
            new Zombie("Zombie", 19, 3, 100, 5, true, null, 1, 1, 2),
            new Zombie("Zombie", 46, 19, 100, 5, true, null, 1, 1, 2),
            new Zombie("Zombie", 40, 40, 100, 5, true, null, 1, 1, 2),
            new Zombie("Zombie", 37, 21, 100, 5, true, null, 1, 1, 2),

            new Bandit("Bandit", 19, 31, 100, 5, true, null, 2, 1, 1),
            new Bandit("Bandit", 21, 23, 100, 5, true, null, 2, 1, 1),
            new Bandit("Bandit", 18, 2, 100, 5, true, null, 2, 1, 1),
            new Bandit("Bandit", 20, 4, 100, 5, true, null, 2, 1, 1),
            new Bandit("Bandit", 30, 8, 100, 5, true, null, 2, 1, 1),
            new Bandit("Bandit", 25, 2, 100, 5, true, null, 2, 1, 1),
            new Bandit("Bandit", 47, 46, 100, 5, true, null, 2, 1, 1),

            new Boss("Mother Zombie", 42, 10, 250, 15, true, null, 3, 1, 1),
        };

        this.items = new Item[] {
            new Water(18, 2, null, 2),
            new Water(21, 1, null, 1),
            new Water(17, 12, null, 1),
            new Water(2, 39, null, 1),
            new Water(38, 46, null, 3),
            new Water(5, 8, null, 2),
            new Water(43, 2, null, 5),

            new Food(4, 39, null, 1),
            new Food(31, 13, null, 1),
            new Food(25, 12, null, 1),
            new Food(12, 23, null, 2),
            new Food(27, 42, null, 2),
            new Food(5, 7, null, 3),
            new Food(43, 2, null, 5),

            new Key(12, 8, null),
            new Key(31, 2, null),
            new Key(37, 16, null),
            new Key(33, 1, null),
            new Key(46, 6, null),

            new MedKit(3, 42, null),
            new MedKit(42, 2, null),

            new Exit(1, 1, null)
        };
    }
}
