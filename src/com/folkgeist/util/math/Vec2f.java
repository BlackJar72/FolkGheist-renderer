//Copyright (C) Jared Blackburn, Oct 16, 2013
package com.folkgeist.util.math;

import java.lang.Math;
import static com.folkgeist.util.HouseKeeping.*;
import org.lwjgl.util.vector.Vector2f;


/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */

public class Vec2f {
    public float x, y;
    
    
    public Vec2f(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    
    public float dot(Vec2f b) {
        return (this.x * b.get(0)) + (this.y * b.get(1));
    }
    
    
    public float dot(Vec2i b) {
        return (this.x * b.get(0)) + (this.y * b.get(1));
    }
    

    private float length() {
        return (float) Math.sqrt((x * x) + (y * y));
    }
    
    
    public void normalize() {
        float l = this.length();
        this.x /= l;
        this.y /= l;
    }
    
    
    public Vec2f add(Vec2f b) {
        return new Vec2f(this.x + b.get(0), this.y + b.get(1));
    }
    
    
    public Vec2f sub(Vec2f b) {
        return new Vec2f(this.x - b.get(0), this.y - b.get(1));
    }
    
    
    public Vec2f add(float b) {
        Vec2f dir = this.copy();
        dir.normalize();
        return new Vec2f(this.x + (b * dir.get(0)), this.y + (b * dir.get(1)));
    }
    
    
    public Vec2f sub(float b) {
        Vec2f dir = this.copy();
        dir.normalize();
        return new Vec2f(this.x + (b * dir.get(0)), this.y - (b * dir.get(1)));
    }
    
    
    public Vec2f mul(float b) {
        return new Vec2f(this.x * b, this.y * b);
    }
    
    
    public Vec2f div(float b) {
        return new Vec2f(this.x / b, this.y / b);
    }
    
    
    public void set(int index, float value) {
        if((index > 1) || (index < 0))  
            reportError("Tried to pass an invalid index to Vec2f.", 
                1, new Exception(), true);
        switch(index) {
            case 0:
                x = value;
                return;
            case 1:
                y = value;
                return;
        }
    }
    
    
    public float get(int index) {
        switch(index) {
            case 0:
                return x;
            case 1:
                return y;
            case 2:
            default:
                reportError("Tried to retrieve an invalid index from"
                    + " Vec2f.", 1, new Exception(), true);
                return 0;
        }
    }
    
    
    public Vec2f rotate(float angle) {
        double theta = Math.toRadians(angle);
        double sin   = Math.sin(theta);
        double cos   = Math.cos(theta);
        return new Vec2f((float)((this.x * cos) - (this.y * sin)), 
                         (float)((this.x * sin) - (this.y) * cos));
    }
    
    
    public Vec2f copy() {
        return new Vec2f(this.x, this.y);
    }
    
    
    public boolean equals(Vec2f other) {
        return((this.x == other.get(0)) 
            && (this.y == other.get(1)));
    }
    
    
    @Override
    public String toString() {
        return ("[" + this.x + ", " + this.y + "]");
    }
    
}
