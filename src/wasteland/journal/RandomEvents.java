package wasteland.journal;

import java.util.Random;

public class RandomEvents {

    private final EventBase[] events = {
        new VisitEvent(),
        new SicknessEvent(),
        new RobberEvent()
    };

    private Random rand = new Random();
    private int totalSum = 0;

    public RandomEvents() {
        for (EventBase event : events) {
            totalSum = totalSum + event.getRelativeProbability();
        }
    }

    public EventBase getRandom() {
        int index = rand.nextInt(totalSum);
        int sum = 0;
        int i = 0;
        while (sum < index) {
            sum = sum + events[i++].getRelativeProbability();
        }
        return events[(Math.max(0, i - 1))];
    }

}
