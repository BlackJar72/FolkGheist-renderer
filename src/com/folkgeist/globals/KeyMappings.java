package com.folkgeist.globals;

import org.lwjgl.input.Keyboard;

/**
 * This is to contain mappings for controls, so that keys can be remapped as 
 * well as having preset defaults.
 * 
 * (This may later be moved to com.folkgeist.ui.controls, not sure...?)
 * 
 * @author JaredBGreat (Jared Blackburn)
 */
public class KeyMappings {
    // FIXME: Better names for typically mouse functions; non-keyboard names, names that tell controller type, better class name...!
    // Defaults
    public static final int DEF_FORWARD = Keyboard.KEY_W;
    public static final int DEF_BACK    = Keyboard.KEY_S;
    public static final int DEF_RIGHT   = Keyboard.KEY_D;
    public static final int DEF_LEFT    = Keyboard.KEY_A;
    public static final int DEF_UP      = Keyboard.KEY_SPACE;
    public static final int DEF_DOWN    = Keyboard.KEY_LSHIFT;
    public static final int DEF_HOVER   = Keyboard.KEY_H;
    public static final int DEF_GROUND  = Keyboard.KEY_G;
    public static final int DEF_MENU    = Keyboard.KEY_ESCAPE; 
    public static final int DEF_EXITNOW = Keyboard.KEY_RCONTROL; //Only for now
    
    public static final int DEF_RCLICK  = Keyboard.CHAR_NONE;
    public static final int DEF_LCLICK  = Keyboard.CHAR_NONE;
    public static final int DEF_WCLICK  = Keyboard.CHAR_NONE;
    
    
    // Modifiable versions used in game
    public static int forward = DEF_FORWARD;
    public static int back    = DEF_BACK;
    public static int right   = DEF_RIGHT;
    public static int left    = DEF_LEFT;
    public static int up      = DEF_UP;
    public static int down    = DEF_DOWN;
    public static int hover   = DEF_HOVER;
    public static int ground  = DEF_GROUND;
    public static int menu    = DEF_MENU;
    public static int exitnow = DEF_EXITNOW;
    
    public static final int rclick = DEF_RCLICK;
    public static final int lclick = DEF_LCLICK;
    public static final int wclick = DEF_WCLICK;
    
}
