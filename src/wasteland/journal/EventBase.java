package wasteland.journal;

abstract class EventBase implements Event {
    private int relativeProbability;
    protected String entry;

    public EventBase(int relativeProbability) {
        this.relativeProbability = relativeProbability;
    }

    public int getRelativeProbability() {
        return relativeProbability;
    }

    public String getEntry() {
        return entry;
    }

    abstract void interaction();
    abstract void inputProccessor(int keyCode);
}
