package wasteland.journal;

import wasteland.game.Game;

public class SicknessEvent extends EventBase {

    public SicknessEvent() {
        super(10);
        this.entry = "It appears that the food you ate was contaminated with a deadly virus, you should look for a MedKit to heal yourself.";
    }

    public void interaction() {
        Game.getPlayer().infect();
    }

    // Not needed for this type of event
    public void inputProccessor(int keyCode) {}

}
