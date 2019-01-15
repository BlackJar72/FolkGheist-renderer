package community.simulation.citizens;

/**
 *
 * @author jared
 */

/*
 * Each place a meme adds a modifier to a specific game mechanic will be 
 * repressented as a number in a 2D array (rows = memes, columns = effects);
 * a Citizen has a change to their meme set a 1D array of composite effects
 * in the Citizen will up date.  When the effect of (a) meme(s) might come
 * into play the Citizen's array will be accessed by index=meme.id and applied;
 * if the Citizen carries no relevant meme this will still happen but the value
 * will be zero and thus have no effect.  This should be far more efficient 
 * than alternatives that use logic to query Citizens for specific memes and 
 * apply effects through if-then statements.  only very special memes (if any)
 * should have there own special functions. 
 */


public class MemeEffect {
    private static final int numMemes = 24;
    private static final int numMods  = 2; //"2" is a dummy variable for now
    protected byte[][] modifiers = new byte[numMemes][numMods];
    
    //TODO: Make array public static final, and define the modifiers.
    
    /*
     * Somewhere in this will also be an ArrayList<EnumSet<Memes>>, showing
     * which meme are mutually excusive with with others.  Possibly another
     * holding memes that go well together (but there's a good chance there
     * won't be).
     */
    
}
