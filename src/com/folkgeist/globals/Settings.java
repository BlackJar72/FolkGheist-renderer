//Copyright (C) Jared Blackburn, 2014
package com.folkgeist.globals;

/**
 * This is to contain general games settings, specifically that deal with the 
 * overall setup of the game on a specific system.  Setting that vary per game
 * should be stored in com.folkgeist.globals.PerGameSettings, not here.
 * 
 * @author JaredBGreat (Jared Blackburn)
 */
public class Settings {
    // Should we start in hover mode?  That is, the traditional city-builder 
    // style controls.  (Else we use typical first-person controls.)
    public static final boolean HOVER_MODE_DEFAULT = false;
    public static boolean hoverModeDefault  = HOVER_MODE_DEFAULT;
}
