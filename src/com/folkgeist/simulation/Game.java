//Copyright (C) Jared Blackburn, 2014
package com.folkgeist.simulation;

import com.folkgeist.map.CityMap;
import com.folkgeist.util.HouseKeeping;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class represents a running game, that is, and actual game / world
 * (not to be confused with the entire program).
 * 
 * This should be created when a new game is started or recreated from save 
 * files when a game is loaded, and then deleted when a game is finished.
 * 
 * Included are references to the map, logic loop, etc.  It might eventually be 
 * responsible for supplying the UI class with a relevant game screen, with
 * correcting starting (or loaded) camera settings.
 *
 * @author JaredBGreat (Jared Blackburn)
 */
public class Game {
    public static Game game;
    public CityMap map;
    private Simulator tickManager;
    
    
    public Game() {
        map = new CityMap();
        tickManager = new Simulator();
        //tickManager.run();
    }
    
    
    public static void startGame() {
        HouseKeeping.reportLog("Starting new game");
        game = new Game();
    }
    
    
    public static void endGame() {
        game.endThisGame();
    }
    
    
    public void endThisGame() {
        HouseKeeping.reportLog("Ending game");
        try {
            if(tickManager.getThred() != null) tickManager.getThred().join(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            HouseKeeping.reportError("Failed and game Simulator (tick manager)", 
                    3, ex, true);
        }
        HouseKeeping.reportLog("Game stopped");
    }
}
