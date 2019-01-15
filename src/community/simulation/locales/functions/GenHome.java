//Copyright (C) Jared Blackburn, Sep 20, 2013
package community.simulation.locales.functions;

import community.simulation.locales.Home;

/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */
public class GenHome extends Home {
    protected static final int maxPop     = 4;
    protected static final int maxFam     = 1;
    protected static final byte QoLbonus  = 3;  
    protected static final byte costLevel = 2; 
    
    
    public GenHome() {
        int population = 0;
    }    
       
    
    @Override
    public boolean incPop() {
        population++;
        return population >= maxPop;
    }    
       
    
    @Override
    public boolean incFam() {
        families++;
        return families >= maxFam;
    }
    
    
}
