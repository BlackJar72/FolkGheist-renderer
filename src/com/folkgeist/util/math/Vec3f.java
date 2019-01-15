//Copyright (C) Jared Blackburn, Oct 16, 2013
package com.folkgeist.util.math;

import java.lang.Math;
import static com.folkgeist.util.HouseKeeping.*;
import org.lwjgl.util.vector.Vector3f;


/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */

public class Vec3f {
    public float x, y, z;
    
    
    public Vec3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    
    public float dot(Vec3f b) {
        return (this.x * b.x) 
             + (this.y * b.y) 
             + (this.z * b.z);
    }
    
    
    public float dot(Vec3i b) {
        return (this.x * b.get(0)) 
             + (this.y * b.get(1)) 
             + (this.z * b.get(2));
    }
    

    private float length() {
        return (float) Math.sqrt((x * x) + (y * y) + (z * z));
    }
    
    
    public void normalize() {
        float l = this.length();
        this.x /= l;
        this.y /= l;
        this.z /= l;
    }
    
    
    public Vec3f add(Vec3f b) {
        return new Vec3f(this.x + b.get(0), 
                         this.y + b.get(1), 
                         this.z + b.get(2));
    }
    
    
    public Vec3f sub(Vec3f b) {
        return new Vec3f(this.x - b.get(0), 
                         this.y - b.get(1), 
                         this.z - b.get(2));
    }
    
    
    public Vec3f add(float b) {
        Vec3f dir = this.copy();
        dir.normalize();
        return new Vec3f(this.x + (b * dir.get(0)), 
                         this.y + (b * dir.get(1)), 
                         this.z + (b * dir.get(2)));
    }
    
    
    public Vec3f sub(float b) {
        Vec3f dir = this.copy();
        dir.normalize();
        return new Vec3f(this.x + (b * dir.get(0)), 
                         this.y - (b * dir.get(1)), 
                         this.z - (b * dir.get(2)));
    }
    
    
    public Vec3f mul(float b) {
        return new Vec3f(this.x * b, this.y * b, this.z * b);
    }
    
    
    public Vec3f div(float b) {
        return new Vec3f(this.x / b, this.y / b, this.z / b);
    }
    
    
    public Vec3f cross(Vec3f b) {
        return new Vec3f(((this.y * b.get(2)) - (this.z * b.get(1))),
                         ((this.z * b.get(0)) - (this.x * b.get(2))),
                         ((this.x * b.get(1)) - (this.y * b.get(0))));
    }
    
    
    public void set(int index, float value) {
        if((index > 2) || (index < 0))  
            reportError("Tried to pass an invalid index to Vec3f.", 
                1, new Exception(), true);
        switch(index) {
            case 0:
                x = value;
                return;
            case 1:
                y = value;
                return;
            case 2:
                z = value;
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
                return z;
            default:
                reportError("Tried to retrieve an invalid index from"
                    + " Vec3f.", 1, new Exception(), true);
                return 0;
        }
    }
    
    
    public Vec3f copy() {
        return new Vec3f(this.x, this.y, this.z);
    }
    
    
    public Vec4f to4d() {
        return new Vec4f(this.x, this.y, this.z, 1f);
    }
    
    
    public boolean equals(Vec3f other) {
        return((this.x == other.get(0)) 
            && (this.y == other.get(1))
            && (this.z == other.get(2)));
    }
    
    
    @Override
    public String toString() {
        return ("[" + this.x + ", " + this.y + ", " + this.z + "]");
    }
    
}
