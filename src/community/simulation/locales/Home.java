/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package community.simulation.locales;

/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */
public abstract class Home {
    protected int population;        //Number of residents
    protected static int maxPop;     //Maximum number of residents
    protected int families;          //Number of nuclear families (couples)
    protected static int maxFam;     //Maximum number of families / couples
    // Citizen[] residents = new Citizen[maxPop]; // Probably not needed.
    protected static byte QoLbonus;  //Effect on wellbeing
    protected static byte costLevel; // Affordability to citizens; based on SES
    protected boolean full = false;
    protected boolean famFull = false;
    protected boolean hasRoom = true;

    
    
    public static byte getQoLbonus() {
        return QoLbonus;
    }
    

    public static byte getCostLevel() {
        return costLevel;
    }
    

    public int getFamilies() {
        return families;
    }
    

    public static int getMaxFam() {
        return maxFam;
    }
    

    public static int getMaxPop() {
        return maxPop;
    }
    

    public int getPopulation() {
        return population;
    } 
    
    
    public void updateAvailability() {
        hasRoom = (!full && ! famFull);
    }
    
    
    public boolean getHasRoom() {
        return hasRoom;
    }
    
    
    public boolean isFull() {
        return full;
    }
    
    
    public void setFull(boolean isFull) {
        full = isFull;
    }
    
    
    public boolean isFamFull() {
        return famFull;
    }
    
    
    public void setFamFull(boolean isFull) {
        famFull = isFull;
    }
    
    
    
    
    public abstract boolean incPop();
    public abstract boolean incFam();
    
    
    
}
