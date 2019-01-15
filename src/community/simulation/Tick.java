package community.simulation;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import community.simulation.citizens.*;
import community.simulation.locales.Locale;


/**
 *
 * @author jared
 */
public class Tick {
    private static int x, i;
    Game       game; // loops
    Simulation simulate;

    //TODO:
    //Iterate Places and apply changes (if any), probably Places.tick()
    //Iterate Populous and call Citizen.tick() for each
    //Repeat
    
    
    public static void tick(Simulation simulate) {
        //Player imput taken and processed elsewhere before tick() is called;
        //player imput is processed every frame, game tick() logic is not.
        //x = simulate.places.size();
        //for(i = 0; i < x; i++) simulate.places.get(i).tick();
        //x = simulate.people.size();
        //for(i = 0; i < x; i++) simulate.people.get(i).tick();
        for(Locale loc: simulate.places) loc.tick();
        for(Citizen person: simulate.people) person.tick();
    }
    
}
