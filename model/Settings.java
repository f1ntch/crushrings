package crushrings.model;

/**
 * @author Jael Romero
 * @author Yoerick Van Audenrode
 * @version 1.x on 8-02-2019
 */



public class Settings {
    static public Ring draggedRing;
    static public String playerName;
    static public int gameScore;

    // levels of the game
    static public int[] levels = {
            0,   // level 0 white, blue, green
            15, //  level 1 white, blue, green, pink
            25, //  level 2 white, blue, green, pink, purple
            35, //  level 3 white, blue, green, pink, purple, red
            185 //  level 4 white, blue, green, pink, purple, red, yellow
    };
}
