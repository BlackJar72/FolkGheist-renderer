//Copyright (C) Jared Blackburn, 2014
package com.folkgeist.logic.place;

import com.folkgeist.util.math.Location;
import java.awt.geom.Rectangle2D;

/**
 * 
 * @author JaredBGreat (Jared Blackburn)
 */
public class Locale {    
    private int i, j;
    
    Building buiding;
    Location loc;    
    
    float[] bounds = new float[6];
    Rectangle2D.Float footprint;
    
    int population;
    int workers;
    int visitors;
    int families;
    
    boolean full;
    boolean staffed;
    boolean fullFamilies;
    
    
    
}
