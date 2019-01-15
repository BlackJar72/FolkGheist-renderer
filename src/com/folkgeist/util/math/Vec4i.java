//Copyright (C) Jared Blackburn, Oct 16, 2013
package com.folkgeist.util.math;

import java.lang.Math;
import static com.folkgeist.util.HouseKeeping.*;


/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */

public class Vec4i {
    private int[] vec = new int[4];
    
    
    public Vec4i(int x, int y, int z, int w) {
        vec[0] = x;
        vec[1] = y;
        vec[2] = z;
        vec[3] = w;
    }
    
    
    public float length() {
        return (float) Math.sqrt((vec[0] * vec[0]) 
                               + (vec[1] * vec[1]) 
                               + (vec[2] * vec[2]) 
                               + (vec[3] * vec[3]));
    }
    
    
    public float intLength() {
        return (int) Math.sqrt((vec[0] * vec[0]) 
                               + (vec[1] * vec[1]) 
                               + (vec[2] * vec[2]) 
                               + (vec[3] * vec[3]));
    }
    
    
    public float dot(Vec3f b) {
        return (vec[0] * b.get(0)) 
             + (vec[1] * b.get(1)) 
             + (vec[2] * b.get(2)) 
             + (vec[3] * b.get(3));
    }
    
    
    public int dot(Vec3i b) {
        return (vec[0] * b.get(0)) 
             + (vec[1] * b.get(1)) 
             + (vec[2] * b.get(2)) 
             + (vec[3] * b.get(3));
    }
    
    
    public Vec4i add(Vec4i b) {
        return new Vec4i(vec[0] + b.get(0), 
                         vec[1] + b.get(1), 
                         vec[2] + b.get(2), 
                         vec[3] + b.get(3));
    }
    
    
    public Vec4i sub(Vec4i b) {
        return new Vec4i(vec[0] - b.get(0), 
                         vec[1] - b.get(1), 
                         vec[2] - b.get(2), 
                         vec[3] - b.get(3));
    }
    
    
    public void set(int index, int value) {
        if((index > 3) || (index < 0)) reportError("Tried to pass an invalid index to Vec4i.", 
                1, new Exception(), true);
        vec[index] = value;
    }
    
    
    public int get(int index) {
        if(index > 3)  reportError("Tried to retrieve an invalid index from"
                + " Vec4i.", 1, new Exception(), true);
        return vec[index];
    }
    
    
    public Vec4i copy() {
        return new Vec4i(vec[0], vec[1], vec[2], vec[3]);
    }
    
    
    public boolean equals(Vec4i other) {
        return((vec[0] == other.get(0)) 
            && (vec[1] == other.get(1))
            && (vec[2] == other.get(2))
            && (vec[3] == other.get(3)));
    }
    
    
    @Override
    public String toString() {
        return ("[" + vec[0] + ", " + vec[1] + ", " + vec[2] 
                + ", " + vec[3] + "]");
    }
    
}
