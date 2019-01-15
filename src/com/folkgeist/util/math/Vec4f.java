//Copyright (C) Jared Blackburn, Oct 16, 2013
package com.folkgeist.util.math;

import java.lang.Math;
import static com.folkgeist.util.HouseKeeping.*;
import org.lwjgl.util.vector.Vector4f;


/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */

public class Vec4f {
    public float x, y, z, w;
    
    
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
        return (this.x * b.x) 
             + (this.y * b.y) 
             + (this.z * b.z) 
             + (this.w * b.w);
    }
    
    
    public float dot(Vec4i b) {
        return (this.x * b.get(0)) 
             + (this.y * b.get(1)) 
             + (this.z * b.get(2)) 
             + (this.w * b.get(3));
    }
    

    private float length() {
        return (float) Math.sqrt((x * x) + (y * y) + (z * z) + (w * w));
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
    
    
    public Vec3f trunc3d() {
        return new Vec3f(this.x, this.y, this.z);
    }
    
    
    public Vec3f real3d() {
        return new Vec3f(this.x / this.w, this.y / this.w, this.z / this.w);
    }
    
    
    public void makeNearPlane(float n) {
        x = y = 0;
        z = -1;
        w =  n;
    }
    
    
    public void makeFarPlane(float f) {
        x = y = 0;
        z = -1;
        w = -f;
    }
    
    
    public void makeLeftPlane(float e) {
        x =  e / (float)Math.sqrt((e * e) + 1);
        z = -1 / (float)Math.sqrt((e * e) + 1);        
        w = y = 0;
    }
    
    
    public void makeRightPlane(float e) {
        x = -e / (float)Math.sqrt((e * e) + 1);
        z = -1 / (float)Math.sqrt((e * e) + 1);        
        w = y = 0;
    }
    
    
    public void makeBottomPlane(float e, float a) {
        y =  e / (float)Math.sqrt((e * e) + (a * a));
        z = -a / (float)Math.sqrt((e * e) + (a * a));        
        w = x = 0;
    }
    
    
    public void makeTopPlane(float e, float a) {
        y = -e / (float)Math.sqrt((e * e) + (a * a));
        z = -a / (float)Math.sqrt((e * e) + (a * a));        
        w = x = 0;
    }
    
    
    public static Vec4f[] frustrumPlanes(float a, float fov, float n, float f) {
        // Yes, I could use a loop, but this is more effient!
        float s = (float) Math.tan(fov / 2);
        Vec4f[] out = new Vec4f[6];
        out[0] = new Vec4f();
        out[0].makeNearPlane(n);
        out[1] = new Vec4f();
        out[1].makeFarPlane(f);
        out[2] = new Vec4f();
        out[2].makeLeftPlane(s);
        out[3] = new Vec4f();
        out[3].makeRightPlane(s);
        out[4] = new Vec4f();
        out[4].makeBottomPlane(s, a);
        out[5] = new Vec4f();
        out[5].makeTopPlane(s, a);
        return out;
    }
    
    
    public boolean equals(Vec4f other) {
        return((this.x == other.x) 
            && (this.y == other.y)
            && (this.z == other.z)
            && (this.w == other.w));
    }
    
    
    @Override
    public String toString() {
        return ("[" + this.x + ", " + this.y + ", " + this.z 
                + ", " + this.w + "]");
    }

    public Vec4f mul(Mat4f m) {
        Vec4f out = new Vec4f();
        out.x = x*m.m[0][0] + y*m.m[1][0] + z*m.m[2][0] + w*m.m[3][0];
        out.y = x*m.m[0][1] + y*m.m[1][1] + z*m.m[2][1] + w*m.m[3][1];
        out.z = x*m.m[0][2] + y*m.m[1][2] + z*m.m[2][2] + w*m.m[3][2];
        out.w = x*m.m[0][3] + y*m.m[1][3] + z*m.m[2][3] + w*m.m[3][3];
        return out;
    }
    
}
