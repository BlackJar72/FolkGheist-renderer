package community.simulation.locales;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import community.simulation.map.MapLocale;
import community.simulation.map.Location;
import community.simulation.Simulation;
import community.simulation.citizens.Citizen;
import community.simulation.citizens.enums.Interests;
import java.util.EnumSet;


/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */
public abstract class Locale extends MapLocale {
    //Possible functions, in simple alphabetical order; same locale may have
    //Multiple functions.  Pure decoration = null for all.
    protected Employer   job;
    protected Home       house;
    protected Recreation fun;
    protected boolean changed, complete; 
    // TODO: Build cost and build time; build cost depends on economic model
    // Const type will depend on if I use money, resource, both, or niether(!!)
    protected EnumSet<Interests> fulfillment;
    
    
    public Locale(Simulation where) {
        super(where);
        
    }
    
    
    public Locale(Location centre, byte[] dims, Simulation where) {
        super(centre, dims, where);
        
    }
    
    
    public Locale(int x, int y, int z, byte xDim, byte yDim, byte zDim, 
            Simulation where) {
        super(x, y, z, xDim, yDim, zDim, where);
        
    }
    
    
    public void tick() {
        if(!changed) return;
        //TODO: *EVERYTHING!!!*
    }
    
    
    public void draw() {
        if(!changed) return;
        //TODO: *EVERYTHING!!!*
    }
    
    
    public boolean hasHousing() {
        if(house == null) return false;
        return house.hasRoom;
    }
    
    
    public boolean hasJob() {
        if(job == null) return false;
        return !job.isFull();
    }
    
    
    public boolean hasRecreation() {
        return fun != null;
        // TODO: Recreation population check
    }
    
    
    public abstract void beVisited(Citizen visitor);
    
    
    
    
    
    
    /////////////////////////////////////////////
    /*          GETTERS & SETTERS              */
    /////////////////////////////////////////////
    

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public EnumSet<Interests> getFulfillment() {
        return fulfillment;
    }

    public void setFulfillment(EnumSet<Interests> fulfillment) {
        this.fulfillment = fulfillment;
    }

    public Recreation getFun() {
        return fun;
    }

/*    public void setFun(Recreation fun) {
        this.fun = fun;
    }*/

    public Home getHouse() {
        return house;
    }

/*    public void setHouse(Home house) {
        this.house = house;
    }*/

    public Employer getJob() {
        return job;
    }

/*    public void setJob(Employer job) {
        this.job = job;
    }*/
    
    
}
