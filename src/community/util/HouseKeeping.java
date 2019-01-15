package community.util;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;


/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */
public class HouseKeeping {
    
    
    public static void reportError(String msg, int lvl, boolean exit) {
        reportError(lvl +": " + msg);
        if(exit) System.exit(lvl);
    }  
    
    
    public static void reportError(String msg) {
        System.err.println(msg);
        //Logger.getLogger(msg);
    }
    
    
    public static void reportError(String msg, int lvl, 
            Exception e, boolean exit) {
        e.printStackTrace();
        reportError(lvl +": " + msg);
        if(exit) System.exit(lvl);
    }  
    
    
    public static void reportError(String msg, Exception e) {
        e.printStackTrace();
        System.err.println(msg);
        //Logger.getLogger(msg);
    }
    
}
