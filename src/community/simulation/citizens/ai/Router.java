//Copyright (C) Jared Blackburn, Sep 27, 2013
package community.simulation.citizens.ai;

import community.simulation.map.Location;
import community.simulation.map.CityMap;
import community.simulation.map.MapLocale;
import community.simulation.citizens.Citizen;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import static community.util.math.Util.*;

/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */
public class Router {
    static Citizen citizen;
    static int size, reached;
    static Location[] steps;
    static Location[] options;
    static ArrayList<MapLocale> obstacles;


////////////////////////////////////////////////////////////////////////////////
/*                                                                            */
/*                                  FIXME!!!                                  */
/*                                                                            */
////////////////////////////////////////////////////////////////////////////////


    public static Movement makeRoute(Location start, Location finish, Citizen traveler) {
        citizen = traveler;  
        steps = new Location[32];
        steps[0] = start;
        steps[1] = finish;
        options = new Location[2];
        obstacles = new ArrayList<MapLocale>(32);
        size = 2;
        reached = 1;
        
        while(reached < size) {
            
//            System.out.println("Creating route for citizen #" + citizen.getId() +
//                ", at setp " + reached + ", from X=" + steps[reached-1].getXint() +
//                ", Z=" + steps[reached-1].getZint() + " to " + " X=" + steps[reached].getXint() + 
//                ", Z=" + steps[reached].getZint() +", with " + WSIZEF + " total steps " +
//                " so far.");
            
            if(testRoute(steps[reached - 1], steps[reached])) {
                reached++;
            } else {
                size++;
                if(size >= steps.length) {
                    //citizen.setStayTimer(3000);
                    //return null;
                    //return new Wandering(citizen);
                    return makeLameRoute(start, finish, traveler);
                } 
                for(int i = size - 1; i >= reached; i--) {
                    steps[i + 1] = steps[i];
                }
                circumvent(steps[reached - 1], findClosest(steps[reached - 1]));
                
//                    System.out.println("               options[0] is X=" 
//                            + options[0].getXint() + ", Z=" + options[0].getZint()
//                            + ";  options[1] is X=" 
//                            + options[1].getXint() + ", Z=" + options[1].getZint()
//                            + ".");
        
                if((options[0] == null) && (options[1] == null)) {
                    //citizen.setStayTimer(3000);
                    //return null;
                    //return new Wandering(citizen);
                    return makeLameRoute(start, finish, traveler);
                } 
                if (options[0] == null) {
                    steps[reached] = options[1];
                } else if(options[1] == null) {
                    steps[reached] = options[0];
                } else if((distance(steps[reached - 1], options[0]) + distance(options[0], steps[reached + 1]))
                    <= (distance(steps[reached - 1], options[1]) + distance(options[1], steps[reached + 1]))) {
                    steps[reached] = options[0];
                } else { // distance to optionsp[1] < distance to optionsp[0]
                    steps[reached] = options[1];
                }
            }
        }
    return new Route(citizen, steps, size);
    }
    
    
    public static Movement makeLameRoute(Location start, Location finish, Citizen traveler) {
        citizen = traveler;  
        steps = new Location[32];
        steps[0] = start;
        steps[1] = finish;
        options = new Location[2];
        obstacles = new ArrayList<MapLocale>(32);
        size = 2;
        reached = 1;
        if(Math.random() <= 0.10d) return new Wandering(citizen);
        return new Route(citizen, new SubDest(start, finish, citizen.getSpeed(), true));
   }


    private static boolean testRoute(Location begin, Location end) {
        obstacles.clear();
        //Line2D.Float path = new Line2D.Float(begin.getXfloat(), begin.getZfloat(),
        //                                end.getXfloat(), end.getZfloat());
        for(MapLocale obstacle: citizen.city.places) {
            //if((path.intersects(obstacle.footprint))) obstacles.add(obstacle);
            if((pathIntersects(obstacle, begin, end))) obstacles.add(obstacle);
        }
        //obstacles.remove(citizen.getHome());
        obstacles.remove(citizen.getLocale());
        obstacles.remove(citizen.getDestination());
        
//        for(Location loc: obstacles) {
//            System.out.println("     Obstacle is " + loc.toString() + ", at X=" 
//                    + loc.getXint() + ", Z=" +loc.getZint() + ".");
//        }
        
        if(obstacles.isEmpty()) return true;        
        else return false;
    }


    public static MapLocale findClosest(Location start) {
        int closest = -1;
        float shortest = CityMap.WSIZEF;
        for(int i = 0; i < obstacles.size(); i++) {
            float newDist = distance(start, obstacles.get(i));
            if(newDist < shortest) {
                shortest = newDist;
                closest = i;
            }
        } 
//            System.out.println("          Closesst is " + obstacles.get(closest).toString() + ", at X=" 
//                    + obstacles.get(closest).getXint() + ", Z=" + obstacles.get(closest).getZint() + ".");
//            System.out.println("               Corner 0: " + obstacles.get(closest).getCorner(0).getXint() 
//                    + ", " + obstacles.get(closest).getCorner(0).getZint() + ";  Corner 1: " +
//                    + obstacles.get(closest).getCorner(1).getXint() 
//                    + ", " + obstacles.get(closest).getCorner(1).getZint() + ";");
//            System.out.println("               Corner 2: " + obstacles.get(closest).getCorner(2).getXint() 
//                    + ", " + obstacles.get(closest).getCorner(2).getZint() + ";  Corner 3: " +
//                    + obstacles.get(closest).getCorner(3).getXint() 
//                    + ", " + obstacles.get(closest).getCorner(3).getZint() + ".");
//            
//            System.out.println("               Footprint: " + obstacles.get(closest).footprint.x 
//                    + ", " + obstacles.get(closest).footprint.y + " to  " +
//                    + (obstacles.get(closest).footprint.x + obstacles.get(closest).footprint.width)
//                    + ", " + (obstacles.get(closest).footprint.y - obstacles.get(closest).footprint.height) + ".");
        
                return obstacles.get(closest);
    }


    public static int circumvent(Location start, MapLocale obstacle) {
        int midOrientation = orienter(start, obstacle);
        Location tmp;
        switch (midOrientation) {
            case 0:
                tmp = obstacle.getCorner(3);
                if(orienter(start, tmp) == midOrientation) {
                        tmp.change(1.0f, 0.0f, -1.0f);
                        options[0] = tmp;
                    } else {
                        tmp = obstacle.getCorner(2);
                        tmp.change(-1.0f, 0.0f, -1.0f);
                        options[0] = tmp;
                }
                tmp = obstacle.getCorner(1);
                if(orienter(start, tmp) == midOrientation) {
                        tmp.change(-1.0f, 0.0f, 1.0f);
                        options[1] = tmp;
                    } else {
                        tmp = obstacle.getCorner(2);
                        tmp.change(-1.0f, 0.0f, -1.0f);
                        options[1] = tmp;
                }
                return midOrientation;
            case 1:
                tmp = obstacle.getCorner(0);
                if(orienter(start, tmp) == midOrientation) {
                        tmp.change(1.0f, 0.0f, 1.0f);
                        options[0] = tmp;
                    } else {
                        tmp = obstacle.getCorner(3);
                        tmp.change(1.0f, 0.0f, -1.0f);
                        options[0] = tmp;
                }
                tmp = obstacle.getCorner(2);
                if(orienter(start, tmp) == midOrientation) {
                        tmp.change(-1.0f, 0.0f, -1.0f);
                        options[1] = tmp;
                    } else {
                        tmp = obstacle.getCorner(3);
                        tmp.change(1.0f, 0.0f, -1.0f);
                        options[1] = tmp;
                }
                return midOrientation;
            case 2:
                tmp = obstacle.getCorner(1);
                if(orienter(start, tmp) == midOrientation) {
                        tmp.change(-1.0f, 0.0f, 1.0f);
                        options[0] = tmp;
                    } else {
                        tmp = obstacle.getCorner(0);
                        tmp.change(1.0f, 0.0f, 1.0f);
                        options[0] = tmp;
                }
                tmp = obstacle.getCorner(3);
                if(orienter(start, tmp) == midOrientation) {
                        tmp.change(1.0f, 0.0f, -1.0f);
                        options[1] = tmp;
                    } else {
                        tmp = obstacle.getCorner(0);
                        tmp.change(1.0f, 0.0f, 1.0f);
                        options[1] = tmp;
                }
                return midOrientation;
            case 3:
                tmp = obstacle.getCorner(2);
                if(orienter(start, tmp) == midOrientation) {
                        tmp.change(-1.0f, 0.0f, -1.0f);
                        options[0] = tmp;
                    } else {
                        tmp = obstacle.getCorner(1);
                        tmp.change(-1.0f, 0.0f, 1.0f);
                        options[0] = tmp;
                }
                tmp = obstacle.getCorner(0);
                if(orienter(start, tmp) == midOrientation) {
                        tmp.change(1.0f, 0.0f, 1.0f);
                        options[1] = tmp;
                    } else {
                        tmp = obstacle.getCorner(1);
                        tmp.change(-1.0f, 0.0f, 1.0f);
                        options[1] = tmp;
                }
                return midOrientation;
            default:
                community.util.HouseKeeping.reportError("Orienter gave invalid "
                        + " result to circumvent in router.", 1, true);
                return midOrientation;
        }
    }
    
    

    //////////////////////////////////////
    /*         GETTERS & SETTERS        */
    //////////////////////////////////////

    
    
    public Citizen getCitizen() {
        return citizen;
    }
    

    public ArrayList<MapLocale> getObstacles() {
        return obstacles;
    }
    

    public Location[] getOptions() {
        return options;
    }
    

    public int getReached() {
        return reached;
    }
    

    public int getSize() {
        return size;
    }    
    

    public Location[] getSteps() {
        return steps;
    }
    
    
    

}
