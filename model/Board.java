package crushrings.model;

/**
 * @author Jael Romero
 * @author Yoerick Van Audenrode
 * @version 1.x on 8-3-2019
 */

/* This class determines the max values ​​for board and spot */

public class Board {
    public static final int RING_SPOT_MAX_SIZE = 3;
    public static final int BOARD_SIZE = 9;
    private Ring[] rings;

    public Board() {
        rings = new Ring[BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            rings[i] = new Ring();
        }
    }

    public Ring getRing(int i) {
        if (i < 0) throw new RuntimeException("Ring index can't be negative!");
        if (i >= BOARD_SIZE) throw new RuntimeException("Total Ring count is " + BOARD_SIZE);
        return rings[i];
    }
}
