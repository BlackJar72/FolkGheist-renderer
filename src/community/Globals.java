package community;

/**
 * This class is for holding static variables used for controlling
 * game states / run states, and controlling similar game-wide events.
 * 
 * @author jared = JaredBGreat (Jared Blackburn)
 */


public class Globals {
    public static boolean running = true, playing = true; // Control some loops
    //public static int exitState = 0;  // The variable for System.exit;
    public static enum State {
        INTRO,
        MENUS,
        PLAYING,
        PAUSED,
        MPAUSE,
        EXITING,
        CRASHING;
    }
    
    public static enum Menus {
        MAIN;
    }
    
    public static State state = State.INTRO;
    public static Menus menu  = Menus.MAIN;
    
    
}
