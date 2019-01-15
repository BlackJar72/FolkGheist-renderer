//Copyright (C) Jared Blackburn, 2014
package com.folkgeist;

import static com.folkgeist.util.HouseKeeping.*;
import static com.folkgeist.globals.Constants.*;
import static com.folkgeist.globals.State.*;
import com.folkgeist.simulation.Game;
import com.folkgeist.ui.UI;
import com.folkgeist.ui.controls.KeyFunc;
import com.folkgeist.ui.controls.Mousie;
import com.folkgeist.ui.models.Models;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JaredBGreat (Jared Blackburn)
 */

public class Folkgeist {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        starting();
        running();
        stopping();
    }
    
    
    private static void starting() {        
        reportLog("Starting to run " + gametitle);
        running = true;
        UI.start();
        KeyFunc.init(); 
        Mousie.init();
        Models.initModelData();
    }
    
    
    private static void running() {
        //TODO: Move game stop and start elsewhere
        Game.startGame();
        UI.loop();
        Game.endGame();
    }
    
    
    private static void stopping() {
        reportLog("Shutting down " + gametitle);
        //TODO: Clean-up code here
        Mousie.end();
        KeyFunc.end();
        UI.end();
        reportLog("Terminating");
    }
    
}
