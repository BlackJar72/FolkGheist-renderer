package community.simulation.map;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import community.simulation.*;
import community.simulation.citizens.*;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;


/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */
public abstract class MapLocale extends Location implements Selectable {
    protected int i, j;
    public Simulation city;
    public Rectangle2D.Float footprint;
    public float[] dimentions = new float[4];
    //Bounds could be calculated at every testing but this should be more 
    //efficient.
    public float[] bounds = new float[6];
        
    
    protected MapLocale(Simulation where) {
        city = where;
        for(i = 0; i < 4; i++) dimentions[i] = 0;
        for(i = 0; i < 6; i++) bounds[i] = 0;  
        footprint = new Rectangle2D.Float();
        
    }
    
    public MapLocale(Location centre, byte[] dims, Simulation where) {
        city = where;
        System.arraycopy(dims, 0, dimentions, 0, 4); 
        i = j = 0;
        bounds[j++] = x + (((float) dimentions[i]) / 2f);
        bounds[j++] = x - (((float) dimentions[i]) / 2f);
        i++;
        bounds[j++] = y + (((float) dimentions[i]) / 2f);
        bounds[j++] = y - (((float) dimentions[i]) / 2f);
        i++;
        bounds[j++] = z + ((float) dimentions[i]);
        bounds[j]   = z;  
        footprint = new Rectangle2D.Float(bounds[1], 
                bounds[4], dims[0], dims[2]);
    }
    
    public MapLocale(int x, int y, int z, byte xDim, byte yDim, byte zDim, 
            Simulation where) {
        city = where;
        dimentions[0] = xDim;
        dimentions[1] = yDim;
        dimentions[2] = zDim;
        j = 0;
        bounds[j++] = x + (((float) xDim) / 2f);
        bounds[j++] = x - (((float) xDim) / 2f);
        bounds[j++] = y + (((float) yDim) / 2f);
        bounds[j++] = y - (((float) yDim) / 2f);
        bounds[j++] = z + (((float) zDim) / 2f);
        bounds[j]   = z - (((float) zDim) / 2f);   
        footprint = new Rectangle2D.Float(bounds[1], 
                bounds[4], dimentions[0], dimentions[2]);
    }
    
    
    public void makeRect(int x, int y, int z, byte xDim, byte yDim, byte zDim) {
        dimentions[0] = xDim;
        dimentions[1] = yDim;
        dimentions[2] = zDim;
        i = j = 0;
        bounds[j++] = x + (((float) dimentions[i]) / 2f);
        bounds[j++] = x - (((float) dimentions[i]) / 2f);
        i++;
        bounds[j++] = y + (((float) dimentions[i]) / 2f);
        bounds[j++] = y - (((float) dimentions[i]) / 2f);
        i++;
        bounds[j++] = z + (((float) dimentions[i]) / 2f);
        bounds[j]   = z - (((float) dimentions[i]) / 2f);   
        footprint = new Rectangle2D.Float(bounds[1], 
                bounds[4], dimentions[0], dimentions[2]);
        
    }
    
    
    public void makeRect() {
        i = j = 0;
        bounds[j++] = x + (((float) dimentions[i]) / 2f);
        bounds[j++] = x - (((float) dimentions[i]) / 2f);
        i++;
        bounds[j++] = y + (((float) dimentions[i]) / 2f);
        bounds[j++] = y - (((float) dimentions[i]) / 2f);
        i++;
        bounds[j++] = z + (((float) dimentions[i]) / 2f);
        bounds[j]   = z - (((float) dimentions[i]) / 2f);   
        footprint = new Rectangle2D.Float(bounds[1], 
                bounds[4], dimentions[0], dimentions[2]);
        
    }
    
    
    public boolean inside(Location other) {
        return ((other.x < bounds[0]) && (other.x > bounds[1]) 
                 && (other.y < bounds[2]) && (other.y > bounds[3]) 
                 && (other.z < bounds[4]) && (other.z > bounds[5]));
    }
    
    
    public boolean inside2d(Location other) {
        return ((other.x < bounds[0]) && (other.x > bounds[1]) 
                && (other.z < bounds[4]) && (other.z > bounds[5]));
    }
    
    
    public boolean collide(Location other) {
        return ((other.x < (bounds[0] + 0.5f)) && (other.x > (bounds[1] - 0.5f)) 
                 && (other.y < (bounds[2] + 0.5f)) && ((other.y > bounds[3] - 0.5f)) 
                 && (other.z < (bounds[4] + 0.5f)) && ((other.z > bounds[5] - 0.5f)));
    }
    
    
    public boolean collide2d(Location other) {
        return ((other.x < (bounds[0] + 0.5f)) && (other.x > (bounds[1] - 0.5f)) 
                && (other.z < (bounds[4] + 0.5f)) && (other.z > (bounds[5]) - 0.5f));
    }
    
    
    public Rectangle2D getFootprint() {
        return footprint;
    }
    
    
    @Override
    public void change(float x, float y, float z) {
        community.util.HouseKeeping.reportError("Attempted to move MapLocale via"
                + " public void change(float x, float y, float z)!");
    }
    
    
    @Override
    public void change(float x, float y, float z, float r) {
        community.util.HouseKeeping.reportError("Attempted to move MapLocale via"
                + " public void change(float x, float y, float z, float r)!");        
    }
    
    
    public Location getLocation() {
        return new Location(x, y, z, r);
    }
    
    
    public void transLocation(Location other) {
        other.setX(x);
        other.setY(y);
        other.setZ(z);
        other.setRotate(r);        
    }
    
    
    public Location getCorner(int corner) {
        switch(corner) {
            case 0: return new Location(bounds[0], z, bounds[4], r); 
            case 1: return new Location(bounds[1], z, bounds[4], r);
            case 2: return new Location(bounds[1], z, bounds[5], r);
            case 3: return new Location(bounds[0], z, bounds[5], r);
            default:
                //new Exception().printStackTrace();
                community.util.HouseKeeping.reportError("Invald corner "
                        + "requested from MapLocale at getCorner(int corner).");
                        //, 1, true);
                return this;
                        
            }
    }
    

    public float[] getBounds() {
        return bounds;
    }
    

    public float getBounds(int index) {
        return bounds[index];
    }
    

    public void setBounds(float[] bounds) {
        this.bounds = bounds;
    }
    

    public Simulation getCity() {
        return city;
    }
    

    public void setCity(Simulation city) {
        this.city = city;
    }
    

    public float[] getDimentions() {
        return dimentions;
    }
    

    public float getDimentions(int index) {
        return dimentions[index];
    }
    

    public void setDimentions(float[] dimentions) {
        this.dimentions = dimentions;
    } 
    

    public void setFootprint(Float footprint) {
        this.footprint = footprint;
    }
    
    
    
    
}
