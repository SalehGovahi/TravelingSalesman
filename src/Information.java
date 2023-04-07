public class Information {
    int turn = 1;
    int Founded_treasure = 0;
    String is_turn = "Player" + turn;
    long timeElapsed;

    public void setFounded_treasure(int founded_treasure) {
        Founded_treasure = founded_treasure;
    }

    public int getFounded_treasure() {
        return Founded_treasure;
    }

    public long getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(long timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    public int getTurn() {
        return turn;
    }
}
