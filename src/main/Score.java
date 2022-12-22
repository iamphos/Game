package main;

public class Score {
    private int score = 0;
    private final int points = 50;

    public Score(int score) {

        this.score = score;
    }

    public void addScore() {
        this.score += points;
    }
    public int getScore() {
        return score;
    }

    public void resetScore() {
        this.score = 0;
    }
}
