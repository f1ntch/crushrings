package crushrings.model;

/**
 * @author Jael Romero
 * @author Yoerick Van Audenrode
 * @version 1.x on 8-02-2019
 */



public class Spawn {
    public static final int SPAWN_SIZE = 3;
    private Ring[] rings;


    public Spawn() {

        // de ring combinatie in spawn worden het gegenereerd
        rings = new Ring[SPAWN_SIZE];
        for (int i = 0; i < SPAWN_SIZE; i++) {
            rings[i] = new Ring();
            rings[i].randomise();
        }


    }

    public Ring getRing(int i) {
        if (i < 0) throw new RuntimeException("Ring index can't be negative!");
        if (i >= SPAWN_SIZE) throw new RuntimeException("Total Ring count is " + SPAWN_SIZE);
        return rings[i];
    }
}
