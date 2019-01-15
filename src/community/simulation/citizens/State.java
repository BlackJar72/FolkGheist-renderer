/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package community.simulation.citizens;

import community.util.BonusHelper;

/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */
public class State {
    private Citizen holder;
    private int i, x; // Temp storage
    private byte[] wellbeing = new byte[5];
    private byte[] emotions  = new byte[5]; // Not yet sure if I'll use this model
    //Other state aspects later
    
    
    
    
    
    // WELLBEING STUFF
    public byte getMood() {
        return wellbeing[0];
    }
    
    public byte getQoL() {  // TODO: Rename?
        return wellbeing[1];
    }
    
    public byte getFun() {  // TODO: Rename?
        return wellbeing[2];
    }
    
    public byte getSocial() {// TODO: Rename?
        return wellbeing[3];
    }
    
    public byte getHealth() {// TODO: Rename?
        return wellbeing[4];
    }
    
    
    public void setMood(byte val) {
        wellbeing[0] = val;
    }
    
    public void setQoL(byte val) {  // TODO: Rename?
        wellbeing[1] = val;
    }
    
    public void setFun(byte val) {  // TODO: Rename?
        wellbeing[2] = val;
    }
    
    public void setSocial(byte val) {// TODO: Rename?
        wellbeing[3] = val;
    }
    
    public void setHeatlhl(byte val) {// TODO: Rename?
        wellbeing[4] = val;
    }
    
    
    public void modMood(byte val, boolean recalc) {
        x = wellbeing[0] + val;
        if(x > 127) x = 127;
        if(x < -128) x = -128;
        wellbeing[0] = (byte) x;
        if(recalc) calcMood();
    }
    
    public void modQoL(byte val, boolean recalc) {  // TODO: Rename?
        x = wellbeing[1] + val;
        if(x > 127) x = 127;
        if(x < -128) x = -128;
        wellbeing[1] = (byte) x;
        if(recalc) calcMood();
    }
    
    public void modFun(byte val, boolean recalc) {  // TODO: Rename?
        x = wellbeing[2] + val;
        if(x > 127) x = 127;
        if(x < -128) x = -128;
        wellbeing[2] = (byte) x;
        if(recalc) calcMood();
    }
    
    public void modSocial(byte val, boolean recalc) {// TODO: Rename?
        x = wellbeing[3] + val;
        if(x > 127) x = 127;
        if(x < -128) x = -128;
        wellbeing[3] = (byte) x;
        if(recalc) calcMood();
    }
    
    public void modHealth(byte val, boolean recalc) {// TODO: Rename?
        x = wellbeing[4] + val;
        if(x > 127) x = 127;
        if(x < -128) x = -128;
        wellbeing[4] = (byte) x;
        if(recalc) calcMood();
    }
    
    
    public void calcMood() {
        x = 0;
        for(i = 1; i < 5; i++) x += wellbeing[i];
        x += emotions[0];
        x -= (BonusHelper.get(emotions[1]) 
                * BonusHelper.get(holder.personality.getNeurotic()));
        x += (BonusHelper.get(emotions[3]) 
                * BonusHelper.get(holder.personality.getNeurotic()));
        x /= 5;
        if(x > 127) x = 127;
        if(x < -128) x = -128;
        wellbeing[3] = (byte) x;
    }
    
    
    
    // EMOTIONAL STUFF
    
    byte getJoy() {
        return emotions[0];
    }
    
    byte getFear() {
        return emotions[1];        
    }
    
    byte getFriendly() {
        return emotions[2];
    }
    
    byte getAnger() {
        return emotions[3];        
    }
    
    
    void setJoy(byte val) {
        emotions[0] = val;
    }
    
    void setFear(byte val) {
        emotions[1] = val;
    }
    
    void setFriendly(byte val) {
        emotions[2] = val;
    }
    
    void setAnger(byte val) {
        emotions[3] = val;
    }
    
    void setSurprised(byte val) {
        emotions[4] = val;
    }
    
    
    void modJoy(byte val, boolean recalc) {
        x = emotions[0] + val;
        if(x > 127) x = 127;
        if(x < -128) x = -128;
        emotions[0] = (byte) x;
        if(recalc) calcMood();
    }
    
    void modFear(byte val, boolean recalc) {
        x = emotions[1] + val;
        if(x > 127) x = 127;
        if(x < -128) x = -128;
        emotions[1] = (byte) x;
        if(recalc) calcMood();
    }
    
    void modFriendly(byte val, boolean recalc) {
        x = emotions[2] + val;
        if(x > 127) x = 127;
        if(x < -128) x = -128;
        emotions[2] = (byte) x;
        if(recalc) calcMood();
    }
    
    void modAnger(byte val, boolean recalc) {
        x = emotions[3] + val;
        if(x > 127) x = 127;
        if(x < -128) x = -128;
        emotions[3] = (byte) x;
        if(recalc) calcMood();
    }
    
    void modSurprised(byte val, boolean recalc) {
        x = emotions[4] + val;
        if(x > 127) x = 127;
        if(x < -128) x = -128;
        emotions[4] = (byte) x;
        if(recalc) calcMood();
    }
}
