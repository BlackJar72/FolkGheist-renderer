//Copyright (C) Jared Blackburn, 2014
package com.folkgeist.globals;

import static com.folkgeist.globals.Settings.*;

/**
 * This class is for holding game state information that is readily toggleable 
 * in game.  These variable will alway start as default.
 * 
 * @author JaredBGreat (Jared Blackburn)
 */
public class State {  
    public static boolean running;
    public static boolean playing;
    // Should picking be performed?
    public static final boolean SELECT_MODE = true;
    public static boolean selectMode = SELECT_MODE;
    
    // Are we in ground / first-person tour mode? (Else in build / normal mode.)
    public static final boolean GRAVITY_ON = false;
    public static boolean gravityOn  = GRAVITY_ON;
    
    // Are we in hover mode?  That is, the traditional city-builder style
    // controls.  (Else we use typical first person controls.)
    public static boolean hoverMode  = hoverModeDefault;
    
}
