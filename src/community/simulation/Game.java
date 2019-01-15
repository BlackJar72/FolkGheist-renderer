package community.simulation;

import java.util.ArrayList;
/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */
public class Game {
    ArrayList<Simulation> cities = new ArrayList<Simulation>();
    Simulation city = new Simulation();
    
    
    public Game() {
        cities.add(city);
    }

    
    public ArrayList<Simulation> getCities() {
        return cities;
    }
    

    public Simulation getCity() {
        return city;
    }
    
    
}
