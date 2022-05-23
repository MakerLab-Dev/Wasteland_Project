package wasteland.journal;

import java.util.Random;

import wasteland.entity.Player;
import wasteland.game.Game;

public class Journal {
    private int day;
    private String[][] entrys;
    // The journal has 4 tabs.
    // 0 - Day overview
    // 1 - Random events
    // 2 - Player stats
    // 3 - Expeditions
    private int tab;
    private Random rand = new Random();
    private EventBase currentEvent;

    public Journal() {
        day = 0;
        // Randomly calculate the number of entrys
        // based on the day the player will
        // be rescued.
        entrys = new String[rand.nextInt(31 - 26) + 26][4];
    }

    // nextDay is called when a day passes
    // It adds a new entry to the journal,
    // Increments the day and might start a
    // random event
    public void nextDay() {
        day++;
        tab = 0;
        if (day < (entrys.length)) {
            if (day == 1) {
                // If its the first day, add a welcome entry
                entrys[day][0] = "You awoke in a strange place. You don't know where you are or how you got there.";
                entrys[day][1] = "Random events will occur on this tab.";
                entrys[day][2] = "Time to ration your food and rest.";
                entrys[day][3] = "It's time to prepare an expedition for tomorrow, do you want to do it?";
            } else if (day == (entrys.length - 1)) {
                // The day the player is rescued.
                // Add a rescue entry
                entrys[day][0] = "You have been rescued by a group of people. They have given you some food and water.";
                entrys[day][1] = "Your journey was a long and hard one, but you made it.";
                entrys[day][2] = "";
                entrys[day][3] = "The game is going to end the next day.";
            } else {
                Player player = Game.getPlayer();

                // Build a overview entry
                entrys[day][0] = "Another day has passed, ";
                if (player.getHealth() < player.getMaxHealth()/2) {
                    entrys[day][0] += "your health is getting low.";
                } else {
                    entrys[day][0] += "you feel rested.";
                }
                if (player.getHunger() < player.getMaxHunger()/2) {
                    entrys[day][0] += " You are hungry, you should eat something.";
                }
                if (player.getThirst() < player.getMaxThirst()/2) {
                    entrys[day][0] += " You are thirsty, you should drink something.";
                }
                if (player.isInfected()) {
                    entrys[day][0] += " You are feeling ill, you should probably find some medical supplies.";
                }
                if (player.getFood() == 0) {
                    entrys[day][0] += " You have no food, you should find some.";
                }
                if (player.getWater() == 0) {
                    entrys[day][0] += " You have no water, you should find some.";
                }

                // Build a random event entry
                if (Math.random() > 0.5) {
                    // A random event 
                    RandomEvents re = new RandomEvents();
                    currentEvent = re.getRandom();
                    entrys[day][1] = currentEvent.getEntry();
                    currentEvent.interaction();
                } else {
                    entrys[day][1] = "Everything seems calmed today.";
                }

                entrys[day][2] = "Time to ration your food.";
                entrys[day][3] = "It's time to prepare an expedition for tomorrow, do you want to do it?";
            }
        } else {
            // The player has been rescued.
            // End the game.
            Game.endGame(0);
        }
    }

    // Returns the current journal entry
    public String getEntry(int day) {
        return entrys[day][tab];
    }

    // Returns the current day
    public int getDay() {
        return day;
    }

    // Returns the current tab
    public int getTab() {
        return tab;
    }

    // Returns the day the player will be rescued
    public int rescueDay() {
        return entrys.length - 1;
    }

    // Changes the current tab to the next one
    public void nextTab() {
        if (tab < 3) {
            tab++;
        } else {
            tab = 0;
        }
    }

    // Processes the input from the player
    public void proccessInput(int keyCode) {
        if (currentEvent != null) {
            currentEvent.inputProccessor(keyCode);
            entrys[day][1] = currentEvent.getEntry();
        }
    }

}
