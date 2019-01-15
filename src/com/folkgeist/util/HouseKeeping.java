//Copyright (C) Jared Blackburn, 2014
package com.folkgeist.util;

import static com.folkgeist.globals.Constants.*;

/**
 * Some basic utilities, including error reporting / exception handling, among other things.
 * 
 * @author JaredBGreat (Jared Blackburn)
 */
public class HouseKeeping {
      private static final com.folkgeist.util.Logger 
              logger = new com.folkgeist.util.Logger();
//    private static final Logger logger 
//            = java.util.logging.Logger.getLogger("GameLog");
//    private static final Logger error  
//            = java.util.logging.Logger.getLogger("ErrprLog");
        
    
    public static void reportLog(String msg) {
        logger.report(logtitle + msg);
    }
    
    
    public static void reportError(String msg) {
        logger.reportError(logtitle + msg);
    }
    
    
    public static void reportError(String msg, int lvl, boolean exit) {
        reportError(logtitle + "Error " + lvl +": " + msg);
        if(exit) System.exit(1);
    }
    
    
    public static void reportError(String msg, int lvl, 
            Exception e, boolean exit) {
        logger.reportError(e.getMessage());
        reportError(logtitle + "Error " + lvl +": " + msg);
        if(exit) System.exit(lvl);
    }  
    
    
    public static void reportError(String msg, Exception e) {
        logger.reportError(e.getMessage());
        logger.reportError(logtitle + "Error " + msg);
    }
    
}
