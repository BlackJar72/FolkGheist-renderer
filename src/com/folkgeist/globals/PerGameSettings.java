//Copyright (C) Jared Blackburn, 2014
package com.folkgeist.globals;

/**
 * This class is for general game setting that should be per game / per world 
 * and saved / loaded with a particular game / world.
 * 
 * @author JaredBGreat (Jared Blackburn)
 */
public class PerGameSettings {
    public static final boolean ALLOW_CHEATS = false;
    public static boolean allowCheats = ALLOW_CHEATS;
    
    public static final boolean ALLOW_DIFFICULTY_CHANGES = true;
    public static boolean allowDifficultyChanges = ALLOW_DIFFICULTY_CHANGES;
    
    private static long seed;
    
    
    public static long getSeed() {
        return seed;        
    }
    
    
    public static void setSeed(long seed) {
        if(seed == 0) PerGameSettings.seed = System.currentTimeMillis();
        else PerGameSettings.seed = seed;
    }
}
