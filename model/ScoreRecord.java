package crushrings.model;

/**
 * @author Jael Romero
 * @author Yoerick Van Audenrode
 * @version 1.x on 9-02-2019
 */

/* This class takes care of the players name and score during a game  session*/

public class ScoreRecord {
    String player;
    int score;

    public ScoreRecord(String player, int score) {
        this.player = player;
        this.score = score;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
