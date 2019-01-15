//Copyright (C) Jared Blackburn, Sep 20, 2013
package community.simulation.locales.functions;

import community.simulation.locales.Recreation;

/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */
public class GenRec extends Recreation {
    static int maxUsers     = 10;    //Maximum number of residents
    static byte QoLbonus    = 8;   //Effect on wellbeing
    static byte costLevel   = 3;  // Affordability to citizens; based on SES    
    
    
    public GenRec() {
        users = 0;
    }
    
}
