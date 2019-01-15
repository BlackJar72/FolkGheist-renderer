//Copyright (C) Jared Blackburn, Sep 27, 2013
package community.util.math;

import community.simulation.map.CityMap;
import community.simulation.map.Locatable;
import community.simulation.map.Location;
import community.simulation.map.MapLocale;

/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */
public class Util {
    
    
    public static float distance(Locatable start, Locatable end) {
        return (float) Math.sqrt(((start.getXfloat() - end.getXfloat()) 
                                * (start.getXfloat() - end.getXfloat()))
                                + ((start.getZfloat() - end.getZfloat())
                                * (start.getZfloat() - end.getZfloat())));
                
    }  
    
    
    public static float distance(float x1, float y1, float x2, float y2) {
        return (float) Math.sqrt(((x1 - x2) * (x1 - x2)) +
                                 ((y1 - y2) * (y1 - y2)));
                
    }  
    
    
    public static float distance(double x1, double y1, double x2, double y2) {
        return (float) Math.sqrt(((x1 - x2) * (x1 - x2)) +
                                 ((y1 - y2) * (y1 - y2)));
                
    }
    
    
    public static float distance(int x1, int y1, int x2, int y2) {
        return (float) Math.sqrt(((x1 - x2) * (x1 - x2)) +
                                 ((y1 - y2) * (y1 - y2)));
                
    } 
    
    
    public static int orienter(Location start, float x, float z) {
        if(start.getXfloat() < x) {
            if(start.getZfloat() < z) return 0;
            else return 1;
        } else {
            if(start.getZfloat() > z) return 2;
            else return 3;
        }
    } 
    
    
    public static int orienter(Location start, Location other) {
        if(start.getZfloat() < other.getZfloat()) {
            if(start.getXfloat() < other.getXfloat()) return 0;
            else return 1;
        } else {
            if(start.getXfloat() > other.getXfloat()) return 2;
            else return 3;
        }
    }
    
    
    public static boolean testOverlap(MapLocale first, MapLocale second) {
        return first.footprint.intersects(second.footprint);
    }
    
    
    public static boolean pathIntersects1(MapLocale obstacle, 
            Location p1, Location p2) {
        boolean xOut = (((obstacle.bounds[1] < p1.getXfloat()) && 
                        (obstacle.bounds[1] < p2.getXfloat())) ||
                       ((obstacle.bounds[0] > p1.getXfloat()) && 
                        (obstacle.bounds[0] > p2.getXfloat()))); 
        boolean zOut = (((obstacle.bounds[5] < p1.getZfloat()) && 
                        (obstacle.bounds[5] < p2.getZfloat())) || 
                       ((obstacle.bounds[4] > p1.getZfloat()) && 
                        (obstacle.bounds[4] > p2.getZfloat())));
        
        float value;
        float v1 = p1.getXfloat() - p2.getXfloat();
        float v2 = p1.getZfloat() - p2.getZfloat();
        if((v2 == 0f) && (!zOut) && xOut) return true;
        //if((v2 == 0f)) return true;
        value = ((( v1 / v2 ) * obstacle.bounds[4]) + v1);
        if((value < obstacle.bounds[0]) && (value > obstacle.bounds[1])) 
            return true;
        value = (((v1 / v2) * obstacle.bounds[5]) + v1);
        if((value < obstacle.bounds[0]) && (value > obstacle.bounds[1])) 
            return true;
        
        if((v1 == 0f) && (!xOut) && zOut) return true;
        //if((v1 == 0f)) return true;
        value = (((v2 / v1) * obstacle.bounds[0]) + v2);
        if((value < obstacle.bounds[4]) && (value > obstacle.bounds[5])) 
            return true;
        value = (((v2 / v1) * obstacle.bounds[1]) + v2);
        if((value < obstacle.bounds[4]) && (value > obstacle.bounds[5])) 
            return true;
        
        return false;
    }
    
    
    public static boolean pathIntersects(MapLocale obstacle, 
            Location p1, Location p2) {
        if(((obstacle.bounds[1] < p1.getXfloat()) && 
                        (obstacle.bounds[1] < p2.getXfloat())) ||
                       ((obstacle.bounds[0] > p1.getXfloat())  && 
                        (obstacle.bounds[0] > p2.getXfloat())) ||
                       ((obstacle.bounds[5] < p1.getZfloat())  && 
                        (obstacle.bounds[5] < p2.getZfloat())) || 
                       ((obstacle.bounds[4] > p1.getZfloat())  && 
                        (obstacle.bounds[4] > p2.getZfloat()))) return false;
        
        float value;
        float v1 = p1.getXfloat() - p2.getXfloat();
        float v2 = p1.getZfloat() - p2.getZfloat();
        
        if((v2 == 0f)) return true;
        
        value = ((( v1 / v2 ) * obstacle.bounds[4]) + v1);
        if((value < obstacle.bounds[0]) && (value > obstacle.bounds[1])) 
            return true;
        value = (((v1 / v2) * obstacle.bounds[5]) + v1);
        if((value < obstacle.bounds[0]) && (value > obstacle.bounds[1])) 
            return true;
        
        if((v1 == 0f)) return true;
        value = (((v2 / v1) * obstacle.bounds[0]) + v2);
        if((value < obstacle.bounds[4]) && (value > obstacle.bounds[5])) 
            return true;
        value = (((v2 / v1) * obstacle.bounds[1]) + v2);
        if((value < obstacle.bounds[4]) && (value > obstacle.bounds[5])) 
            return true;
        
        return false;
    }
	

    public static boolean pathIntersects2(MapLocale obstacle, 
            Location p1, Location p2)
    {
        return segmentsIntersect(p1, p2, 
                new Location(obstacle.bounds[0], 0, obstacle.bounds[4], 0), 
                new Location(obstacle.bounds[0], 0, obstacle.bounds[5], 0)) ||
               segmentsIntersect(p1, p2, 
                new Location(obstacle.bounds[0], 0, obstacle.bounds[5], 0), 
                new Location(obstacle.bounds[1], 0, obstacle.bounds[5], 0)) ||
               segmentsIntersect(p1, p2, 
                new Location(obstacle.bounds[1], 0, obstacle.bounds[5], 0), 
                new Location(obstacle.bounds[1], 0, obstacle.bounds[4], 0)) ||
               segmentsIntersect(p1, p2, 
                new Location(obstacle.bounds[1], 0, obstacle.bounds[4], 0), 
                new Location(obstacle.bounds[0], 0, obstacle.bounds[4], 0));
    }
    
    
    public static boolean segmentsIntersect(Location la1, Location la2, 
            Location lb1, Location lb2) {
        float d = (la1.getYfloat() - lb1.getYfloat()) 
                * (lb2.getXfloat() - la1.getXfloat()) 
                - (la1.getXfloat() - lb1.getXfloat()) 
                * (lb2.getYfloat() - lb1.getYfloat());
        float q = (la2.getXfloat() - la1.getXfloat()) 
                * (lb2.getYfloat() - lb1.getYfloat()) 
                - (lb2.getYfloat() - la1.getYfloat()) 
                * (lb2.getXfloat() - la1.getXfloat());
        if(d == 0f) return false;    
        float r = q / d;
        q = (la1.getYfloat() - lb1.getYfloat()) 
                * (la2.getXfloat() - la1.getXfloat()) 
                - (la1.getXfloat() - lb1.getXfloat()) 
                * (la2.getYfloat() - la1.getYfloat());
       float s = q / d;
       if((r < 0) || (r > 1) || (s < 0) || (s > 1)) return false;
       return true;        
    }
    
    
}
