//Copyright (C) Jared Blackburn, Oct 18, 2013
package community.ui;

import community.simulation.map.CityMap;
import community.simulation.map.Locatable;
import community.simulation.map.Location;
import community.simulation.map.MapLocale;
import community.simulation.Simulation;
import community.simulation.citizens.Citizen;
import community.util.math.Matrix4x4f;
import java.util.ArrayList;
import static community.util.math.Util.*;

/**
 * Supposed to select the building or person the cursor/pointer is dirrectly 
 * over.  I have no idea why it doesn't work.
 * 
 * 
 * @author jared = JaredBGreat (Jared Blackburn)
 */
public class Selector {
    private Matrix4x4f ray;
    private Camera view;
    private Simulation city;
    private float x, y, z, dx1, dx2, dy1, dy2, dz1, dz2;
    
    
    public Selector(Matrix4x4f ray, Camera view, Simulation city) {
        this.ray = ray;
        this.view = view;
        this.city = city;
    }
    
    
    public Locatable select() {
        ArrayList<Locatable> options = new ArrayList<Locatable>();
        for(MapLocale loc: city.places) {
            if(checkPlace(loc)) options.add(loc);
        } 
        for(Citizen per: city.people) {
            
        }       
        return findClosest(options);
    }
    
    
    private boolean checkPlace(MapLocale loc)  {
        boolean result = false;
        dx1 = loc.getBounds(0) - view.getX();
        dx2 = loc.getBounds(1) - view.getX();
        dy1 = loc.getBounds(2) - view.getY();
        dy2 = loc.getBounds(3) - view.getY();
        dz1 = loc.getBounds(4) - view.getZ();
        dz2 = loc.getBounds(5) - view.getZ();
//        if(dx1 * ray.get(0, 3) > 0) {
//            y = (dx1 * ray.get(1, 0)) - view.getY();
//            z = (dx1 * ray.get(2, 0)) - view.getZ();
//            if(    (y < loc.getBounds(2)) 
//                && (y > loc.getBounds(3))
//                && (z < loc.getBounds(4))
//                && (z > loc.getBounds(5))) result = true;
//        } 
//        if(dx2 * ray.get(0, 3) > 0) {
//            y = (dx2  * ray.get(1, 0)) - view.getY();
//            z = (dx2  * ray.get(2, 0)) - view.getZ();
//            if(    (y < loc.getBounds(2)) 
//                && (y > loc.getBounds(3))
//                && (z < loc.getBounds(4))
//                && (z > loc.getBounds(5))) result = true;
//        }        
        if(dy1 * ray.get(1, 3) > 0) {
            x = (dy1  * ray.get(0, 1)) - view.getX();
            z = (dy1  * ray.get(2, 1)) - view.getZ();
            if(    (x < loc.getBounds(0)) 
                && (x > loc.getBounds(1))
                && (z < loc.getBounds(4))
                && (z > loc.getBounds(5))) result = true;
        } 
//        if(dy2 * ray.get(1, 3) > 0) {
//            x = (dy2 * ray.get(0, 1)) - view.getX();
//            z = (dy2 * ray.get(2, 1)) - view.getZ();
//            if(    (x < loc.getBounds(0)) 
//                && (x > loc.getBounds(1))
//                && (z < loc.getBounds(4))
//                && (z > loc.getBounds(5))) result = true;  
//        }       
//        if(dz1 * ray.get(2, 3) > 0) {
//            x =  (dz1 * ray.get(0, 2))  - view.getX();
//            y = - (dz1 * ray.get(1, 2)) - view.getY();
//            if(    (x < loc.getBounds(0)) 
//                && (x > loc.getBounds(1))
//                && (y < loc.getBounds(2))
//                && (y > loc.getBounds(3))) return true;
//        } 
//        if(dz2 * ray.get(2, 3) > 0) {
//            x =  (dz2 * ray.get(0, 2))  - view.getX();
//            y = - (dz2 * ray.get(1, 2)) - view.getY();
//            if(    (x < loc.getBounds(0)) 
//                && (x > loc.getBounds(1))
//                && (y < loc.getBounds(2))
//                && (y > loc.getBounds(3))) return true;  
//        }                
        return result;
    }
    
    
    private boolean checkPerson(MapLocale per)  {
                
                
                
        return false;
    }
    
    
    private Locatable findClosest(ArrayList<Locatable> list) {
        if(list.isEmpty()) return null;
        Locatable closest = null;
        Location start = new Location(view.getX(), view.getY(), 
                view.getZ(), view.getRy());
        float newDist = CityMap.WSIZEF;
        float shortest = newDist;
        for(Locatable loc: list) {
            newDist = distance(start, loc);
            if(newDist < shortest) {
                closest = loc;
                shortest = newDist;
            }
        }
        return closest;
    }
    
    
    public Location getDebugBlock() {        
        float dy = 0 - view.getY();        
        if(dy * ray.get(1, 3) > 0) {
        x = (dy  * ray.get(0, 1)) - view.getX();
        z = (dy  * ray.get(2, 1)) - view.getZ();
        return new Location(x, 0f, z, 0f);
        }
        return null;
    }
    
    
}
