/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package community.simulation.citizens;

/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */
public class SES {
    private static final int numVar = 4;
    private static final int reprieve = 3;
    private byte[] ses = new byte[numVar];
    private boolean employed;
    private int unemployment;
    
    
    public SES() {
        ses[0] = 5; // stand-in until other portions are done
        ses[1] = 3; // stand-in until other portions are done
        ses[2] = 3; // stand-in until other portions are done
        ses[3] = 0; // stand-in until other portions are done
        employed = false;
        unemployment = reprieve + 2; //Special break for new comers
    }
    
    
    public byte getIncome() {
        return ses[0];        
    }
    
    public void setIncome(byte val) {
        ses[0] = val;
    }
    
    
    public byte getStatus() {
        return ses[1];
    }
    
    public void setStatus(byte val) {
        ses[1] = val;
    }
    
    
    public byte getEducation() {
        return ses[2];
    }
    
    public void setEducation(byte val) {
        ses[2] = val;
    }
    
    public void educate(byte boost, byte max) {
        ses[2] += boost; // This should usually be 1, but not demanding it.
        if(ses[2] > max) ses[2] = max;
    }
    
    
    public byte getReputation() {
        return ses[3];
    }
    
    public void setReputation(byte val) {
        ses[3] = val;
    }
    
    
    public boolean jobCheck() {
        if(employed) {
            unemployment = reprieve;
            return true;
        }
        if(unemployment < 1) {
            ses[0] = 0;
            return false;
        }
        unemployment--;
        return false;        
    }
    
    
    public boolean canAfford(byte cost) {
        return (cost <= ses[1]);
    }
}
