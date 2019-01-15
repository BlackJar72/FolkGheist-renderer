//Copyright (C) Jared Blackburn, 2014
package com.folkgeist.simulation;

import static com.folkgeist.globals.State.*;
import com.folkgeist.time.Time;
import static com.folkgeist.time.Time.*;
import com.folkgeist.util.HouseKeeping;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The over-arching class for game logic; this will not do much of the actual
 * logic per se, but will time it and call other appropriate logic per tick.
 * 
 * This runs on its own thread separating logic execution from graphics 
 * rendering (and potentially client from server is a multiplayer version
 * were ever created).  On the one hand this is desirable, and if a server
 * version were made would be essential.  On the other hand this means that
 * any interaction between render and logic most be careful and concurrency 
 * safe, and should probably be done through and abstraction layer or socket.
 *
 * @author JaredBGreat (Jared Blackburn)
 */
public class Simulator implements Runnable {
    private long waittime;
    private final Time clock = new Time();
    private final Thread simulator;
    
    
    public Simulator() {
        HouseKeeping.reportLog("Creating a new simulator (tick manager)");
        playing = true;
        Time.GameClock.init();
        waittime = 0;
        simulator = new Thread(this);
        simulator.start();
    }
    
    
    @Override
    public final void run() {
        while(playing) {
            if(!isPaused()) tick();
            waittime = clock.tock();
            try {
                if(waittime > 0) {
                    sleep(waittime);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Simulator.class.getName())
                        .log(Level.SEVERE, null, ex);
                HouseKeeping.reportError("Error in makeing Simulator.run() " 
                        + "sleep", 1, false);
            }
        }
        HouseKeeping.reportLog("Ending game logic loop (tick manager / " 
                + "Simulator class)");
    }
    
    
    public void tick() {
        
    }
    
    
    protected Thread getThred() {
        return simulator;
    }
    
}
