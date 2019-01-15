//Copyright (C) Jared Blackburn, Sep 25, 2013
package community.simulation.citizens.ai;

import community.util.HouseKeeping;
import community.simulation.map.Location;
import community.simulation.map.MapLocale;
import community.simulation.citizens.Citizen;
import community.simulation.locales.Locale;
import java.awt.geom.Line2D;
import java.util.ArrayList;

/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */
public class Route implements Movement {    
    private static final int maxSteps = 32;
    private Citizen owner;
    protected int step, orientation;
    protected SubDest[] steps = new SubDest[maxSteps];
    protected int size;
    
    
    public Route(Citizen owner) {
        this.owner = owner; 
        step = 0;
        size = 0;
    }
    
    
    public Route(Citizen owner, SubDest[] route) {
        this.owner = owner; 
        steps = route;
        step = 0;
        size = route.length;
    }
    
    
    public Route(Citizen owner, Location[] route, int size) {
        this.owner = owner; 
        this.size = size;
        for(int i = 0; i < size - 1; i++) {
            steps[i] = new SubDest(route[i], route[i + 1], 
                    owner.getSpeed(), ((i + 2) == size));
        }
        step = 0;
    }
    
    
    public Route(Citizen owner, SubDest nextStep) {
        this.owner = owner; 
        steps[0] = nextStep;
        step = 0;
        size = 1;
    }
    
    
    public boolean add(Location start, Location goal, boolean end) {
        steps[size] = new SubDest(start, goal, owner.getSpeed(), end);
        steps[size].setEnd(end);
        size++;
        if((size >= maxSteps) && !end) return false;
        else return true;
    }
    
    
    public boolean add(SubDest subDest) {
        steps[size] = subDest;
        steps[size].setEnd(subDest.isEnd());
        size++;
        if((size >= maxSteps) && !subDest.isEnd()) return false;
        else return true;
    }
    
    
    public void reset() {
        step = 0;
    }
    
    
    public SubDest getCurrentSubDest() {
        return steps[step];
    }
    
    
    public SubDest getSubDest(int index) {
        return steps[index];
    }
    
    
    @Override
    public boolean update(Location curLoc) {
        if(steps[step].update(curLoc)) {
            if(steps[step].isEnd()) return true;
            else step++;
        } 
        return false;        
    }

    
    @Override
    public float vX() {
        return steps[step].getVx();
    }

    
    @Override
    public float vZ() {
        return steps[step].getVz();
    }

    
    @Override
    public float getAngle() {
        return getCurrentSubDest().getAngle();
    }
    
    
    @Override
    public boolean isValid() {
        return steps[step] != null;
    }
    
    
    public boolean append(Route route) {
        int j = route.getSize();
        for(int i = 0; i < j; i++) {
            if(!add(route.getSubDest(i))) return false;
        }
        return true;
    }
    
    
    
    
    
    /////////////////////////////////////////////
    /*           GETTERS AND SETTERS           */
    /////////////////////////////////////////////
    

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public Citizen getOwner() {
        return owner;
    }

    public void setOwner(Citizen owner) {
        this.owner = owner;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getSize() {
        return step;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public SubDest[] getSteps() {
        return steps;
    }

    public void setSteps(SubDest[] steps) {
        this.steps = steps;
    }

    @Override
    public void tick() {}
}
