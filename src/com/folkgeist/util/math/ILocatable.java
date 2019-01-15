//Copyright (C) Jared Blackburn, Oct 18, 2013
package com.folkgeist.util.math;

import com.folkgeist.ui.controls.ISelectable;

/**
 *
 * @author JaredBGreat (Jared Blackburn)
 */
public interface ILocatable extends ISelectable { 
    public float getXfloat();    
    public float getYfloat();    
    public float getZfloat();    
    public float getRotate(); 
    public int getXint();    
    public int getYint();    
    public int getZint();    
    public int getRint();   
    public void setX(float x);
    public void setY(float y);
    public void setZ(float z);
    public void setRotate(float r);
    public void setX(int x);    
    public void setY(int y);    
    public void setZ(int z);    
    public void setR(int z); 
}
