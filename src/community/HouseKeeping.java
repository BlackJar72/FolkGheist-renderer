package community;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.logging.Level;
import java.util.logging.Logger;


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
        Logger.getLogger(msg);
    }    
    
    public static void reportError(String msg, int lvl, 
            boolean exit, Throwable e) {
        System.err.println(msg);
        System.err.println(e.getMessage());
        e.printStackTrace();
        reportError(msg, lvl, exit);
    }
    
    
    
}
