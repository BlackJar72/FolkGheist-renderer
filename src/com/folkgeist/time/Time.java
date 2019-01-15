//Copyright (C) Jared Blackburn, 2014
package com.folkgeist.time;

import org.lwjgl.Sys;
import static com.folkgeist.time.Time.gameSpeed.*;

/**
 *
 * @author JaredBGreat (Jared Blackburn)
 */
public class Time {
    private static float lastSysTime;
    private static float delta;
    private long gameticks;
    private long gametime;
    private int  gameday;
    private int  timeOfDay;
    
    public static enum gameSpeed {
        CRAWL   (5,   0.25f, 1440), // 24 minute day
        SLOW    (10,  0.5f,   720), // 12 minute day
        NORMAL  (20,  1.0f,   360), // Six minute day
        QUICK   (40,  2.0f,   180), // Three minute day
        FAST    (60,  3.0f,   120), // Two minute day
        EXTREME (120, 6.0f,    60); // One minute day        
        public final int   ticks;
        public final float factor;        
        public final int   secs;        
        gameSpeed(int ticks, float factor, int secs) {
            this.ticks  = ticks;
            this.factor = factor;
            this.secs   = secs;
        }
    }
    
    
    public static final class GameClock{
        static long lasttime;
        static long thistime;
        static long waittime;
        public static void init() {
            lasttime = thistime = System.currentTimeMillis();
            waittime = 0;
        }
    }
    
    private gameSpeed      speed;
    private static boolean paused; // Not sure this is the right place for this
    
    
    public Time() {
        gameday  = 0;
        gameticks = gametime = 0L;
        paused   = false;
        speed    = NORMAL;
    }
    
   
    public void bump() {
        gameticks++;
        if((gameticks % speed.ticks) == 0) gametime++;
        timeOfDay = (int) (gametime % (speed.secs)) * 4;
        if(timeOfDay == 0) gameday++;
    }
    

    public long getGameticks() {
        return gameticks;
    }
    

    public long getGametime() {
        return gametime;
    }
    

    public int getGameday() {
        return gameday;
    }
    
    
    public int getWeekday() {
        return gameday / 7;
    }
    

    public int getTimeOfDay() {
        return timeOfDay;
    }
    
    
    public int getHour() {
        return timeOfDay / 60;
    }
    
    
    public float getDayFraction() {
        return ((float)timeOfDay) / 1440f;
    }
    
    
    public float getDayRadians() {
        // This will might change to make the day and night have
        // different lengths.
        float out = getDayFraction();
        out *= (2 * Math.PI);
        return out;
    }
    

    public gameSpeed getSpeed() {
        return speed;
    }
    

    public static boolean isPaused() {
        return paused;
    }
    
    
    public static long getSysTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }
    
    
    private static void updateDelta() {
        long now = getSysTime();
        //FIXME: I don't remember how I got 16; need to know how / why / what...
        delta = ((float)(now - lastSysTime)) / 16;
        lastSysTime = now;
    }
    
    
    public static float getDelta() {
        updateDelta();
        return delta;
    }
    
    
    public static float getLastDelta() {
        return delta;
    }
    
    
    public long tock() {
        GameClock.thistime = getSysTime();
        GameClock.waittime = (1000 / getSpeed().ticks) 
                - (GameClock.thistime - GameClock.lasttime);
        if(GameClock.waittime < 1) GameClock.waittime = 1;
        GameClock.lasttime = GameClock.thistime;
        return GameClock.waittime;
    }
    
}
