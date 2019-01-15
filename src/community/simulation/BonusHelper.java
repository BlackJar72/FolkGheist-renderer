/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package community.simulation;

/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */
public final class BonusHelper {
    public static final byte[] BONUS_TABLE = { -5, -4, -4, -4, -3, 
        -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3,
        -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2,
        -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2,
        -2, -2, -2, -2, -2, -2, -1, -1, -1, -1, -1, -1, -1, -1, 
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0, 0, 
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1,
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
        1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
        2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 
        2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 
        3, 3, 3, 4, 4, 4, 4, 5 };
    
    
    //I'm not sure if this should be done this way, calculated arhithmetically
    //at run time, or looked up in a large array.  This will do for now.
    public static byte get(byte val) {
        System.err.println("Calling BonusHelper.get(byte val)"); //Debugging code
        return BONUS_TABLE[val + 128];
        /*if(val == 127) return  5;
        if(val > 123)  return  4;
        if(val > 111)  return  3;
        if(val >  79)  return  2;
        if(val >  31)  return  1;
        if(val > -32)  return  0;
        if(val >  80)  return -1;
        if(val > -112) return -2;
        if(val > -124) return -3;
        if(val > -128) return -4;
        return -5;*/
    }
    
}
