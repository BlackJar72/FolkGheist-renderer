//Copyright (C) Jared Blackburn, Oct 16, 2013
package community.util.math;

import java.lang.Math;
import community.util.*;
import static community.util.HouseKeeping.*;


/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */

public class Vec2i {
    private int[] vec = new int[2];
    
    
    public Vec2i(int x, int y) {
        vec[0] = x;
        vec[1] = y;
    }
    
    
    public float length() {
        return (float) Math.sqrt((vec[0] * vec[0]) 
                               + (vec[1] * vec[1]));
    }
    
    
    public int intLength() {
        return (int) Math.sqrt((vec[0] * vec[0]) 
                               + (vec[1] * vec[1]));
    }
    
    
    public int dot(Vec2i b) {
        return (vec[0] * b.get(0)) + (vec[1] * b.get(1));
    }
    
    
    public float dot(Vec2f b) {
        return (vec[0] * b.get(0)) + (vec[1] * b.get(1));
    }
    
    
    public Vec2i add(Vec2i b) {
        return new Vec2i(vec[0] + b.get(0), vec[1] + b.get(1));
    }
    
    
    public Vec2i sub(Vec2i b) {
        return new Vec2i(vec[0] - b.get(0), vec[1] - b.get(1));
    }
    
    
    public Vec2i mul(int b) {
        return new Vec2i(vec[0] * b, vec[1] * b);
    }
    
    
    public Vec2i div(int b) {
        return new Vec2i(vec[0] / b, vec[1] / b);
    }
    
    
    public void set(int index, int value) {
        if(index > 1)  reportError("Tried to pass an invalid index to Vec2i.", 
                1, new Exception(), true);
        vec[index] = value;
    }
    
    
    public int get(int index) {
        if((index > 1) || (index < 0))  reportError("Tried to retrieve an invalid index from"
                + " Vec2i.", 1, new Exception(), true);
        return vec[index];
    }
    
    
    public Vec2i copy() {
        return new Vec2i(vec[0], vec[1]);
    }
    
    
    public boolean equals(Vec2i other) {
        return((vec[0] == other.get(0)) 
            && (vec[1] == other.get(1)));
    }
    
    
    @Override
    public String toString() {
        return ("[" + vec[0] + ", " + vec[1] + "]");
    }
    
}
