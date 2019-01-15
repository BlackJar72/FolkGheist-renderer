//Copyright (C) Jared Blackburn, Oct 8, 2013
package community.simulation.citizens.ai;

import community.simulation.locales.Locale;
import community.simulation.map.Location;
import community.simulation.citizens.Citizen;
import community.util.BonusHelper;
import static community.simulation.citizens.AI.*;

/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */
public class Wandering implements Movement {
    private Citizen owner;
    private float vx, vz, angle, lastAngle;
    private int duration;
    private Location curLoc;
    
    
    public Wandering(Citizen citizen) {
        owner = citizen;
        angle = lastAngle = owner.getLocation().getRotate();        
        vz =  (owner.getSpeed() * (float) Math.cos(Math.toRadians(angle)));
        vx =  (owner.getSpeed() * (float) Math.sin(Math.toRadians(angle)));
        duration = (int) (60 + (Math.random() * (100 
                - (BonusHelper.get(owner.personality.getNeurotic()) * 20 ))));
        angle = (float) (Math.random() * 360);
    }
    

    @Override
    public float vX() {
        return vx;
    }

    
    
    @Override
    public float vZ() {
        return vz;
    }

    
    
    @Override
    public float getAngle() {
        return angle;
    }

    
    
    @Override
    public boolean update(Location curLoc) {
        this.curLoc = curLoc;
        if(angle != lastAngle) {
            lastAngle = angle;
            vz =  (owner.getSpeed() * (float) Math.cos(Math.toRadians(angle)));
            vx =  (owner.getSpeed() * (float) Math.sin(Math.toRadians(angle)));
            //curLoc.setRotate(angle);
        }
        for(Locale loc: owner.city.places) {
            if(loc.collide2d(curLoc) && 
                    (loc != owner.getLocale()) &&
                    (loc != owner.getHome())) {
                owner.getLocation().change(-(vx * 2f), 0, -(vz * 2f));
                owner.setLocale(loc);
                angle = (float)((int)(angle + 180f) % 360);
            }
        }
        if(duration <= 0) {
        duration = (int) (20 + (Math.random() * (120 
                - (BonusHelper.get(owner.personality.getNeurotic()) * 25 ))));
        double die = Math.random();
        if(die > (0.1 + (BonusHelper.get(owner.personality.getConscience()) 
                * 0.02))) {
            owner.setDestination(chooseDestOnly(owner));
            owner.setRoute(Router.makeRoute(curLoc, owner.getDestination(), owner));
            //owner.setRoute(Router.makeLameRoute(curLoc, owner.getDestination(), owner));
            return false;
            } else {
                die = Math.random();
                if(die < 0.2) veer();
                else if (die < 0.3) changeDir();
                else if (die < 0.4) pause();
            } 
        }
        return false;
    }

    
    
    @Override
    public boolean isValid() {
        return true;
    }
    
    
    @Override
    public void tick() {        
        duration--;
    }
    
    
    
    private void veer() {
        angle = angle - 45f + (((float) Math.random()) * 90f);
    }
    
    
    private void changeDir() {
        owner.setStayTimer(10);
        angle = (float) (Math.random() * 360);
    }
    
    
    private void pause() {
        owner.setStayTimer((int) (40 + (Math.random() * (100 
                - (BonusHelper.get(owner.personality.getNeurotic()) * 20 )))));
        double die = Math.random();
        if(die < 0.40) veer();
        else if(die < 0.70) angle = (float) (Math.random() * 360);      
        
    }
    
}
