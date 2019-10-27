package crushrings.model;

/**
 * @author Jael Romero
 * @author Yoerick Van Audenrode
 * @version 1.x on 8-02-2019
 */

/*This class resets the board data for a new game and gets the infomation of player in game session*/

public class CrushRings {
    private Board board;
    private Spawn spawn;
    private Score score;

    //maak een leeg board aan voor de start van een nieuw spel
    public CrushRings() {
        restart();
    }

    public void restart() {
        board = new Board();
        spawn = new Spawn();
        score = new Score();
    }

    public Board getBoard() {
        return board;
    }

    public Spawn getSpawn() {
        return spawn;
    }

    public Score getScore() {
        return score;
    }
}
