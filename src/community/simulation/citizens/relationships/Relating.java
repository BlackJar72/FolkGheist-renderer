package community.simulation.citizens.relationships;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import community.simulation.citizens.Citizen;



/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */
public class Relating {
    //Main data fields
    protected Citizen subject, object; // Point of view of subject on object
    public int subjID; // Should not change, but public for efficiency
    private int familiarity; // How well object is known
    private int disposition; // Subject's opionion of object
    
    //Reationship constants used here
    private static final int maxFam = 1023;
    private static final int minFam = 0;
    private static final int famInc = 16;
    private static final int maxDis = 255;
    private static final int minDis = -maxDis;
    
    
    public Relating(Citizen subject, Citizen object) {
        this.subject = subject;
        this.object = object;
        familiarity = famInc;
        disposition = 0;
        subjID = subject.getID();
    }
    
    
    int getFamiliarity() {
        return familiarity;        
    }
    
    int getDisposition() {
        return disposition;
    }
    
    void incFamiliarity() {
        //Familiarity is always incriment for all encounter and
        //always increases by they same amount.
        familiarity += famInc;
        if(familiarity > maxFam) familiarity = maxFam;
    }
    
    void decFamiliarity() {
        //Familiarity is always decremented by one each day, but can naver be
        //negative.  A value of 0 means, essentially, a stranger.
        familiarity--;
        if(familiarity < minFam) familiarity = minFam;
    }
    
    void modDisposition(int val) {
        //Change in disoposition is determined by interaton;
        //disposition can be positive (like) or negative (dislike).
        disposition += val;
    }
    
    boolean setFamiliarity(int val) {
        //This should probably not be needed or used, but including just
        //in case.
        if((val < maxFam) && (val > minFam)) {
            familiarity = val;
            return true;
        } else {
            System.err.println("Attempted to set familiarity to invalid value "
                    + "of " + val);
            return false;
        }
    }
    
    boolean setDisposition(int val) {
        //This should probably not be needed or used, but including just
        //in case.
        if((val < maxDis) && (val > minDis)) {
            disposition = val;
            return true;
        } else {
            System.err.println("Attempted to set disposition to invalid value "
                    + "of " + val);
            return false;
        }
    }
    
}
