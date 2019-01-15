//Copyright (C) Jared Blackburn, Sep 13, 2013
package community.ui;

import community.Globals;
import static community.ui.UI.view;
import java.util.EnumSet;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

/**
 *
 * @author jared
 */
public enum Controls {
    MOVEFOWARD (0),
    MOVEBACKWARD (1),
    MOVERIGHT (2),
    MOVELEFT (3),
    MOVEUP (4),
    MOVEDOWN (5),
    SHUTOFF (6),
    OPENMENU (7),  // Not yet implemented
    HOVERMODE (8),
    GROUNDMODE (9),
    DEBUGPROFILE (10);  
    
    public final int idx;
    
    Controls(int index) {
        idx = index;        
    }
    
    private static final int[] controls = new int[Controls.values().length];
    private static final int[] CONTROLS = { Keyboard.KEY_W,
                                            Keyboard.KEY_S,
                                            Keyboard.KEY_D,
                                            Keyboard.KEY_A,
                                            Keyboard.KEY_SPACE,
                                            Keyboard.KEY_LSHIFT,
                                            Keyboard.KEY_ESCAPE,
                                            Keyboard.KEY_TAB,
                                            Keyboard.KEY_H,
                                            Keyboard.KEY_G,
                                            Keyboard.KEY_F3};
    
    public static void initControls() throws LWJGLException {
        Keyboard.create();
        Keyboard.enableRepeatEvents(true);
        Mouse.create();
        Mouse.setGrabbed(true);
        for(int i = 0; i < controls.length; i++)
            controls[i] = CONTROLS[i];
    }
    
    
    public static void updateControls() {
        //TODO: Much later!
    }
    
    
    public static void loadControls() {
        // TODO: Load from a config gile (much later)
    }
    
    
    public int get() {
        return controls[idx];
    }
}
    
    
  