/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package community.simulation.locales;

/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */
public abstract class Employer {
    protected int jobs; //Number of residents
    protected static int maxJobs;     //Maximum number of residents
    // Citizen[] workers = new Citizen[maxJobs]; // Probably not needed.
    protected static byte QoLbonus;  //Effect on wellbeing
    protected static byte payLevel;  // Affordability to citizens; based on SES
    //TODO: "Proffit" -- Am I use money, resources, or both? 
    protected boolean full;
    
    
    

    public static byte getQoLbonus() {
        return QoLbonus;
    }
    

    public static int getMaxJobs() {
        return maxJobs;
    }
    

    public static byte getPayLevel() {
        return payLevel;
    }
    
    
    public boolean isFull() {
        return full;
    }
    
    
    public void setFull(boolean isFull) {
        full = isFull;
    }
    
    
    public abstract boolean incJobs();
    
}
