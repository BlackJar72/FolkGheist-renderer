//Copyright (C) Jared Blackburn, Oct 18, 2013
package community.simulation.map;

import community.simulation.Selectable;

/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */
public interface Locatable extends Selectable { 
    public float getXfloat();    
    public float getYfloat();    
    public float getZfloat();    
    public float getRotate(); 
    public int getXint();    
    public int getYint();    
    public int getZint();    
    public int getRint();   
    public boolean setX(float x);
    public boolean setY(float y);
    public boolean setZ(float z);
    public void setRotate(float r);
    public void setX(int x);    
    public void setY(int y);    
    public void setZ(int z);    
    public void setR(int z); 
}
