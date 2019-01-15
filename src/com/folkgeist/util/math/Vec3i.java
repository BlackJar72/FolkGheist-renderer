//Copyright (C) Jared Blackburn, Oct 16, 2013
package com.folkgeist.util.math;

import java.lang.Math;
import static com.folkgeist.util.HouseKeeping.*;


/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */

public class Vec3i {
    private int[] vec = new int[3];
    
    
    public Vec3i(int x, int y, int z) {
        vec[0] = x;
        vec[1] = y;
        vec[2] = z;
    }
    
    
    public float length() {
        return (float) Math.sqrt((vec[0] * vec[0]) 
                               + (vec[1] * vec[1]) 
                               + (vec[2] * vec[2]));
    }
    
    
    public int intLength() {
        return (int) Math.sqrt((vec[0] * vec[0]) 
                               + (vec[1] * vec[1]) 
                               + (vec[2] * vec[2]));
    }
    
    
    public float dot(Vec3f b) {
        return (vec[0] * b.get(0)) 
             + (vec[1] * b.get(1)) 
             + (vec[2] * b.get(2));
    }
    
    
    public int dot(Vec3i b) {
        return (vec[0] * b.get(0)) 
             + (vec[1] * b.get(1)) 
             + (vec[2] * b.get(2));
    }
    
    
    public Vec3i add(Vec3i b) {
        return new Vec3i(vec[0] + b.get(0), 
                         vec[1] + b.get(1), 
                         vec[2] + b.get(2));
    }
    
    
    public Vec3i sub(Vec3i b) {
        return new Vec3i(vec[0] - b.get(0), 
                         vec[1] - b.get(1), 
                         vec[2] - b.get(2));
    }
    
    
    public Vec3i mul(int b) {
        return new Vec3i(vec[0] * b, vec[1] * b, vec[2] * b);
    }
    
    
    public Vec3i div(int b) {
        return new Vec3i(vec[0] / b, vec[1] / b, vec[2] / b);
    }
    
    
    public Vec3i cross(Vec3i b) {
        return new Vec3i(((vec[1] * b.get(2)) - (vec[2] * b.get(1))),
                         ((vec[2] * b.get(0)) - (vec[0] * b.get(2))),
                         ((vec[0] * b.get(1)) - (vec[1] * b.get(0))));
    }
    
    
    public void set(int index, int value) {
        if(index > 2)  reportError("Tried to pass an invalid index to Vec3i.", 
                1, new Exception(), true);
        vec[index] = value;
    }
    
    
    public int get(int index) {
        if((index > 2) || (index < 0))  reportError("Tried to retrieve an invalid index from"
                + " Vec3i.", 1, new Exception(), true);
        return vec[index];
    }
    
    
    public Vec3i copy() {
        return new Vec3i(vec[0], vec[1], vec[2]);
    }
    
    
    public boolean equals(Vec3i other) {
        return((vec[0] == other.get(0)) 
            && (vec[1] == other.get(1))
            && (vec[2] == other.get(2)));
    }
    
    
    @Override
    public String toString() {
        return ("[" + vec[0] + ", " + vec[1] + ", " + vec[2] + "]");
    }
    
}
