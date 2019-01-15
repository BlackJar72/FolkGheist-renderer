//Copyright (C) Jared Blackburn, Sep 20, 2013
package community.simulation.citizens;

import community.simulation.map.CityMap;
import community.simulation.locales.Locale;
import community.simulation.map.MapLocale;
import community.simulation.citizens.enums.GoalPhase;
import static community.util.math.Util.*;

/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */
public class AI {
    
    
    
    public static Locale chooseDest(Citizen self) {        
        self.nextCylce(); 
        return chooseDestOnly(self);
    }
    
    
    public static Locale chooseDestOnly(Citizen self) {
        if(self.getHome() == null) {
            return findHome(self);
        }
        
        else if(self.getEmployer() == null) {
            return findJob(self);
        }
        
        if(self.getCycle() == GoalPhase.HOME) return self.getHome();
        if(self.getCycle() == GoalPhase.WORK) return self.getEmployer();
        return chooseRec(self);
    }
    
    
    public static Locale chooseRec(Citizen self) {
        Locale basis;
        if(Math.random() < 0.5) basis = self.getLocale();
        else basis = self.getHome();
        Locale choice = null; // This should not stay a default
        float oldDist = CityMap.WSIZEF;
        float newDist = CityMap.WSIZEF;
        for(Locale loc: self.city.places) {
            if((loc.hasRecreation() 
                    && loc != self.getHome() 
                    && loc != self.getEmployer() 
                    && loc != self.getLocale()))
            {
                newDist = distance(basis, loc);
                if(newDist < oldDist) {
                    oldDist = newDist;
                    choice = loc;
                }
            }
        }        
            // TODO: Much better choise algorithm, considering more factors
            return choice;
    }
    
    
    public static Locale findHome(Citizen self) {
        Locale choice = null; 
        float oldDist = CityMap.WSIZEF;
        float newDist = CityMap.WSIZEF;
        for(Locale loc: self.city.places) {
            if(loc.hasHousing())
            {
                newDist = distance(self.getLocation(), loc);
                if(newDist < oldDist) {
                    oldDist = newDist;
                    choice = loc;
                }
            }
        }        
            // TODO: Much better choise algorithm, considering more factors
            return choice;
    }
    
    
    public static Locale findJob(Citizen self) {
        if(self.getHome().getJob() != null) return self.getHome();
        Locale choice = null; // This should not stay a default
        float oldDist = CityMap.WSIZEF;
        float newDist = CityMap.WSIZEF;
        for(Locale loc: self.city.places) {
            if((loc.hasJob()))
            {
                newDist = distance(self.getLocation(), loc);
                if(newDist < oldDist) {
                    oldDist = newDist;
                    choice = loc;
                }
            }
        }        
            // TODO: Much better choise algorithm, considering more factors
            return choice;
    }
}
