//Copyright JaredBlackburn (c) 2014
package com.folkgeist.map;

import com.folkgeist.globals.PerGameSettings;
import com.folkgeist.ui.render.Camera;
import com.folkgeist.ui.render.IViewer;
import com.folkgeist.util.HouseKeeping;
import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */
public class CityMap {
    public static final int   SSIZE  = 8;
    public static final int   SHALF  = SSIZE / 2;
    public static final int   WSIZEI =  SSIZE * MapSector.SIZEI;
    public static final float WSIZEF =  (float)WSIZEI;
    public static final int   HALF   =  WSIZEI / 2;
    public static final float minX   = -(WSIZEF / 2f);
    public static final float maxX   =  (WSIZEF / 2f);
    public static final float minY   = -(WSIZEF / 2f);
    public static final float maxY   =  (WSIZEF / 2f);
    
    private final MapSector[][] secs = new MapSector[SSIZE][SSIZE];
    
    public final long mapSeed;
    protected final Random random;
    
    
    public CityMap() {
        HouseKeeping.reportLog("Generating city map");
        mapSeed = PerGameSettings.getSeed();
        random = new Random(mapSeed);
        for(int i = 0; i < SSIZE; i++)
            for(int j = 0; j < SSIZE; j++) {
                secs[i][j] = new MapSector(((i - SHALF) * 64) + MapSector.HALF, 
                        ((j - SHALF) * 64) + MapSector.HALF);
            }
    }
    
    
    public void draw(IViewer cam) {
        for(int i = 0; i < SSIZE; i++)
            for(int j = 0; j < SSIZE; j++) {
                secs[i][j].draw(cam);
            }
    }
    
}
