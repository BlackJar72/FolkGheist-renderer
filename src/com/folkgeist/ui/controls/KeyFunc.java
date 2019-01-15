//Copyright (C) Jared Blackburn, 2014
package com.folkgeist.ui.controls;

import static com.folkgeist.globals.KeyMappings.*;
import com.folkgeist.util.HouseKeeping;
import java.util.EnumSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

/**
 *
 * @author JaredBGreat (Jared Blackburn)
 */
// FIXME: This should be separate from the keyboard and all controls should be use this!
public enum KeyFunc {
    
    FORWARD,
    BACK,
    RIGHT,
    LEFT,
    UP,
    DOWN,
    HOVER,
    GROUND,
    MENU,
    EXITNOW,
    RCLICK,
    LCLICK,
    WCLICK;
        
    KeyFunc() {}
    
    private static final int[]     bindings   = new int[KeyFunc.values().length];
    private static final KeyFunc[] functions  = KeyFunc.values();
    private static final EnumSet<KeyFunc> all = EnumSet.allOf(KeyFunc.class);
    
    
    public static void init() {
        HouseKeeping.reportLog("Setting up keybindings");
        bindingsInit();
        try {
            HouseKeeping.reportLog("Creating keyboard controls");
            Keyboard.create();
            Keyboard.enableRepeatEvents(true);
        } catch (LWJGLException ex) {
            Logger.getLogger(KeyFunc.class.getName()).log(Level.SEVERE, null, ex);
            HouseKeeping.reportError("ERROR: Failed to create keyboard! " 
                    + "CRASHING NOW!", 3, ex, true);
        }
    }
    
    
    private static void bindingsInit() {
        setKey(FORWARD, forward);
        setKey(BACK   ,    back);
        setKey(RIGHT  ,   right);
        setKey(LEFT   ,    left);
        setKey(UP     ,      up);
        setKey(DOWN   ,    down);
        setKey(HOVER  ,   hover);
        setKey(GROUND ,  ground);
        setKey(MENU   ,    menu);
        setKey(EXITNOW, exitnow);
        setKey(RCLICK ,  rclick);
        setKey(LCLICK ,  lclick);
        setKey(WCLICK ,  wclick);       
    }
    
    
    public static void setKey(KeyFunc function, int key) {
        bindings[function.ordinal()] = key;
    }
    
    
    public static EnumSet<KeyFunc> getKeys() {
        EnumSet<KeyFunc> out = EnumSet.noneOf(KeyFunc.class);
        for(int function = 0; function < bindings.length; function++) {
            if((Keyboard.isKeyDown(bindings[function])) 
                    && (bindings[function] != Keyboard.CHAR_NONE))
                out.add(functions[function]);
        }
        if(Mouse.isButtonDown(0)) out.add(LCLICK);
        if(Mouse.isButtonDown(1)) out.add(RCLICK);
        if(Mouse.isButtonDown(2)) out.add(WCLICK);
        return out;
    }
    
    
    public static void end( ){
        HouseKeeping.reportLog("Disconnecting keyboard controls");
        Keyboard.destroy();
        //HouseKeeping.reportLog("Keyboard destroyed");
    }
}
