//Copyright (C) Jared Blackburn, Oct 16, 2013
package com.folkgeist.util.math;

import java.lang.Math;
import static com.folkgeist.util.HouseKeeping.*;

/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */
public class Quaternion {
    private float[] vec = new float[4];
    
    
    public Quaternion() {
        vec[0] = vec[1] = vec[2] = vec[3] = 0f;
    }
    
    
    public Quaternion(float x, float y, float z, float w) {
        vec[0] = x;
        vec[1] = y;
        vec[2] = z;
        vec[3] = w;
    }
    
    
    public Quaternion conjugate() {
        return new Quaternion(-vec[0], -vec[1], -vec[2], vec[3]);
    }
    
    
    public Quaternion mul(Quaternion b) {  
        return new Quaternion(
                vec[0] * b.get(3) + vec[3] * b.get(0) + vec[1] * b.get(2) - vec[2] * b.get(1),
                vec[1] * b.get(3) + vec[3] * b.get(1) + vec[2] * b.get(0) - vec[0] * b.get(2),
                vec[2] * b.get(3) + vec[3] * b.get(2) + vec[0] * b.get(1) - vec[1] * b.get(0),
                vec[3] * b.get(3) - vec[0] * b.get(0) - vec[1] * b.get(1) - vec[2] * b.get(2)
                );
    }
    
    
    public Quaternion mul(Vec4f b) {  
        return new Quaternion(
                vec[0] * b.get(3) + vec[3] * b.get(0) + vec[1] * b.get(2) - vec[2] * b.get(1),
                vec[1] * b.get(3) + vec[3] * b.get(1) + vec[2] * b.get(0) - vec[0] * b.get(2),
                vec[2] * b.get(3) + vec[3] * b.get(2) + vec[0] * b.get(1) - vec[1] * b.get(0),
                vec[3] * b.get(3) - vec[0] * b.get(0) - vec[1] * b.get(1) - vec[2] * b.get(2)
                );
    }
    
    
    public Quaternion mul(Vec3f b) {  
        return new Quaternion(
                vec[3] * b.get(0) + vec[1] * b.get(2) - vec[2] * b.get(1),
                vec[3] * b.get(1) + vec[2] * b.get(0) - vec[0] * b.get(2),
                vec[3] * b.get(2) + vec[0] * b.get(1) - vec[1] * b.get(0),
                vec[0] * b.get(0) - vec[1] * b.get(1) - vec[2] * b.get(2)
                );
    }
    
    
    public float length() {
        return (float) Math.sqrt((vec[0] * vec[0]) 
                               + (vec[1] * vec[1]) 
                               + (vec[2] * vec[2]) 
                               + (vec[3] * vec[3]));
    }
    
    
    public void normalize() {
        float l = this.length();
        vec[0] /= l;
        vec[1] /= l;
        vec[2] /= l;
        vec[3] /= l;
    }
    
    
//    public float dot(Vec4i b) {
//        return (vec[3] * b.get(3)) 
//             - (vec[1] * b.get(1)) 
//             - (vec[2] * b.get(2)) 
//             - (vec[0] * b.get(0));
//    }
    
    
    public Quaternion add(Quaternion b) {
        return new Quaternion(vec[0] + b.get(0), 
                              vec[1] + b.get(1), 
                              vec[2] + b.get(2), 
                              vec[3] + b.get(3));
    }
    
    
    public Quaternion sub(Quaternion b) {
        return new Quaternion(vec[0] - b.get(0), 
                              vec[1] - b.get(1), 
                              vec[2] - b.get(2), 
                              vec[3] - b.get(3));
    }
    
    
    public Quaternion add(float b) {
        Quaternion dir = this.copy();
        dir.normalize();
        return new Quaternion(vec[0] + (b * dir.get(0)), 
                              vec[1] + (b * dir.get(1)), 
                              vec[2] + (b * dir.get(2)), 
                              vec[3] + (b * dir.get(3)));
    }
    
    
    public Quaternion sub(float b) {
        Quaternion dir = this.copy();
        dir.normalize();
        return new Quaternion(vec[0] + (b * dir.get(0)), 
                              vec[1] - (b * dir.get(1)), 
                              vec[2] - (b * dir.get(2)), 
                              vec[3] - (b * dir.get(3)));
    }
    
    
    public Vec4f mul(float b) {
        return new Vec4f(vec[0] * b, vec[1] * b, vec[2] * b, vec[3] * b);
    }
    
    
    public Vec4f div(float b) {
        return new Vec4f(vec[0] / b, vec[1] / b, vec[2] / b, vec[3] / b);
    }
    
    
    public void set(int index, float value) {
        if(index > 3)  reportError("Tried to pass an invalid index to Quaternion.", 
                1, new Exception(), true);
        vec[index] = value;
    }
    
    
    public float get(int index) {
        if(index > 1)  reportError("Tried to retrieve an invalid index from"
                + " Quaternion.", 1, new Exception(), true);
        return vec[index];
    }
    
    
    public Quaternion copy() {
        return new Quaternion(vec[0], vec[1], vec[2], vec[3]);
    }
    
    
    public boolean equals(Quaternion other) {
        return((vec[0] == other.get(0)) 
            && (vec[1] == other.get(1))
            && (vec[2] == other.get(2))
            && (vec[3] == other.get(3)));
    }
    
    
    @Override
    public String toString() {
        return ("<" + vec[0] + "i, " + vec[1] + "i, " + vec[2] 
                + "i, " + vec[3] + ">");
    }
    
    
}
