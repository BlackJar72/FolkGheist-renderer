//Copyright (C) Jared Blackburn, Sep 20, 2013
package community.simulation.locales.functions;

import community.simulation.locales.Employer;

/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */
public class GenEmployer extends Employer {
    static int maxJobs   = 16; 
    static byte QoLbonus = 0; 
    static byte payLevel = 4; 
    
    
    public GenEmployer() {
        jobs = 0;
    }
    

    public static byte getQoLbonus() {
        return QoLbonus;
    }
    

    public static int getMaxJobs() {
        return maxJobs;
    }
    

    public static byte getPayLevel() {
        return payLevel;
    }
    
    
    @Override
    public boolean incJobs() {
        jobs++;
        return jobs >= maxJobs;
    }
    
    
    
    
    
}
