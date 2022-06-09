package wasteland.levels;

import java.util.Random;

public class LevelSelector {

    private final Level[] levels = {
        new Level01(),
    };

    public Level getRandom() {
        Random rand = new Random();
        int index = rand.nextInt(levels.length);

        return levels[index];
    }
}
