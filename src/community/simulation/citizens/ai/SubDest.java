//Copyright (C) Jared Blackburn, Sep 24, 2013
package community.simulation.citizens.ai;

import community.simulation.map.Location;
import static community.util.math.Util.*;

/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */
public class SubDest {
    Location start;
    Location finish;
    //Location currentLoc;
    private float speed;
    private float angle;        // for setting model rotation
    private float vx, vz;       // Vector components
    private float fullDistance; // from stat to end
    private float newDistance;  // from current location to end
    private boolean end;        // Is this the actually the final destination?
    
    
    public SubDest(Location start, Location goal, float speed, boolean end) {
        this.start = start;
        this.finish = goal;
        this.end = end;
        //currentLoc = start.copy();
        fullDistance = distance(start, goal); 
        newDistance = fullDistance; // The are the same at the start
        vx = ((goal.getXfloat() - start.getXfloat()) / fullDistance) * speed;
        vz = ((goal.getZfloat() - start.getZfloat()) / fullDistance) * speed;
        this.speed = speed;
        angle = (float) Math.toDegrees(Math.atan2((double) -vz, (double) vx)) + 90f;
    }

    public float getAngle() {
        return angle;
    }

    public Location getDestination() {
        return finish;
    }

    public float getFullDistance() {
        return fullDistance;
    }

    public float getNewDistance() {
        return newDistance;
    }

    public float getSpeed() {
        return speed;
    }

    public float getVx() {
        return vx;
    }

    public float getVz() {
        return vz;
    }
    
    
    public boolean isEnd() {
        return end;
    }
    
    
    public void setEnd(boolean end) {
        this.end = end;
    }

    
    public void setAngle(float angle) {
        this.angle = angle;
    }

    
    public void setDestination(Location destination) {
        this.finish = destination;
    }

    
    public void setFullDistance(float fullDistance) {
        this.fullDistance = fullDistance;
    }

    
    public void setNewDistance(float newDistance) {
        this.newDistance = newDistance;
    }

    
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    
    public void setVx(float vx) {
        this.vx = vx;
    }
    

    public void setVz(float vz) {
        this.vz = vz;
    }
    
    
    public boolean update(Location current) {
        newDistance = distance(current, finish);
        return (newDistance <= speed);        
    } 
    
    
    
    
    
    
}
