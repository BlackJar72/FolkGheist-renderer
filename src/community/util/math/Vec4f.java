//Copyright (C) Jared Blackburn, Oct 16, 2013
package community.util.math;

import java.lang.Math;
import community.util.*;
import static community.util.HouseKeeping.*;
import org.lwjgl.util.vector.Vector4f;


/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */

public class Vec4f extends Vector4f {
    
    
    public Vec4f() {
        this.x = this.y = this.z = this.w = 0f;
    }
    
    
    public Vec4f(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
    
    
    public float dot(Vec4f b) {
        return (this.x * b.get(0)) 
             + (this.y * b.get(1)) 
             + (this.z * b.get(2)) 
             + (this.w * b.get(3));
    }
    
    
    public float dot(Vec4i b) {
        return (this.x * b.get(0)) 
             + (this.y * b.get(1)) 
             + (this.z * b.get(2)) 
             + (this.w * b.get(3));
    }
    
    
    public void normalize() {
        float l = this.length();
        this.x /= l;
        this.y /= l;
        this.z /= l;
        this.w /= l;
    }
    
    
    public Vec4f add(Vec4f b) {
        return new Vec4f(this.x + b.get(0), 
                         this.y + b.get(1), 
                         this.z + b.get(2), 
                         this.w + b.get(3));
    }
    
    
    public Vec4f sub(Vec4f b) {
        return new Vec4f(this.x - b.get(0), 
                         this.y - b.get(1), 
                         this.z - b.get(2), 
                         this.w - b.get(3));
    }
    
    
    public Vec4f add(float b) {
        Vec4f dir = this.copy();
        dir.normalize();
        return new Vec4f(this.x + (b * dir.get(0)), 
                         this.y + (b * dir.get(1)), 
                         this.z + (b * dir.get(2)), 
                         this.w + (b * dir.get(3)));
    }
    
    
    public Vec4f sub(float b) {
        Vec4f dir = this.copy();
        dir.normalize();
        return new Vec4f(this.x + (b * dir.get(0)), 
                         this.y - (b * dir.get(1)), 
                         this.z - (b * dir.get(2)), 
                         this.w - (b * dir.get(3)));
    }
    
    
    public Vec4f mul(float b) {
        return new Vec4f(this.x * b, this.y * b, this.z * b, this.w * b);
    }
    
    
    public Vec4f div(float b) {
        return new Vec4f(this.x / b, this.y / b, this.z / b, this.w / b);
    }
    
    
    public void set(int index, float value) {
        if((index > 3) || (index < 0))  
            reportError("Tried to pass an invalid index to Vec4f.", 
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
            case 3:
                w = value;
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
            case 3:
                return w;
            default:
                reportError("Tried to retrieve an invalid index from"
                    + " Vec4f.", 1, new Exception(), true);
                return 0;
        }
    }
    
    
    public Vec4f copy() {
        return new Vec4f(this.x, this.y, this.z, this.w);
    }
    
    
    public boolean equals(Vec4f other) {
        return((this.x == other.get(0)) 
            && (this.y == other.get(1))
            && (this.z == other.get(2))
            && (this.w == other.get(3)));
    }
    
    
    @Override
    public String toString() {
        return ("[" + this.x + ", " + this.y + ", " + this.z 
                + ", " + this.w + "]");
    }
    
}
