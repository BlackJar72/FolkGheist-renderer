//Copyright (C) Jared Blackburn, 2014
package com.folkgeist.ui.controls;

import static com.folkgeist.globals.DisplaySettings.xSize;
import static com.folkgeist.globals.DisplaySettings.ySize;
import com.folkgeist.util.HouseKeeping;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;

/**
 *
 * @author JaredBGreat (Jared Blackburn)
 */
public class Mousie {
    
    
    public static void init() {
        try {
            HouseKeeping.reportLog("Setting up mouse");
            Mouse.create();
        } catch (LWJGLException ex) {
            Logger.getLogger(Mousie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
   public static int[] getData() {
       int[] out = new int[5];
       Mouse.poll();
       out[0] = Mouse.getX();
       out[1] = Mouse.getDX();
       out[2] = Mouse.getY();
       out[3] = Mouse.getDY();
       out[4] = Mouse.getDWheel();       
       return out;
   }
    
    
    public static void end() {
        HouseKeeping.reportLog("Disconnecting mouse");
        Mouse.destroy();
    }
    
    
    public static void grab() {
        Mouse.setGrabbed(true);
        Mouse.setCursorPosition(xSize / 2, ySize / 2);
    }
    
    
    public static void release() {
        Mouse.setGrabbed(false);
    }
}
