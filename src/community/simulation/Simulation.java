package community.simulation;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import community.simulation.citizens.Citizen;
import community.simulation.locales.Locale;
import community.simulation.locales.Places;
import community.simulation.map.CityMap;


/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */
public class Simulation {
    public CityMap map;
    public Populous people = new Populous();
    public Places   places = new Places();
    private int counter = 1;
    
    //TODO:
    //Except user import
    //Translate and apply imput to game
    //Check if it time to update the simulation and run Tick.tick() if so
    //Call render updates
    //repeat
    //Perhaps other stuff later?  
    
    
   public void tick() {
        //for(Citizen person: people) person.tick(); 
       Tick.tick(this);
   }
   
   
   public void frame() {      
        //Iterator it = simulation.places.iterator();
        for(Locale  loc:    places) loc.draw();
        for(Citizen person: people) person.frame();
        
        // For testing purposes; this WILL NOT stay!
        counter++;
        if(counter % 3 == 0) {
            counter = 1;
            tick();
        }
   }
    

    
}
