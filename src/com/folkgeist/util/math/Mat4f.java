//Copyright (C) Jared Blackburn, Oct 16, 2013
package com.folkgeist.util.math;

import com.folkgeist.util.HouseKeeping;

/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */
public class Mat4f {
    final float[][] m = new float[4][4];
    
       
    
    public float get(int x, int y) {
        return m[x][y];
    }
    
    
    public void set(int x, int y, float value) {
        m[x][y] = value;
    }
    
    
    public Mat4f add(Mat4f b) {
        Mat4f c = new Mat4f();
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++) 
                c.set(i, j, m[i][j] + b.get(i, j));
        return c;
    }
    
    
    public Mat4f sub(Mat4f b) {
        Mat4f c = new Mat4f();
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++) 
                c.set(i, j, m[i][j] - b.get(i, j));
        return c;
    }
    
    
    public Mat4f mul(Mat4f b) {
        Mat4f c = new Mat4f();
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++) 
                c.m[i][j] = (m[i][0] * b.m[0][j]) +
                            (m[i][1] * b.m[1][j]) +
                            (m[i][2] * b.m[2][j]) +
                            (m[i][3] * b.m[3][j]);
        return c;
    }
    
    
    public Vec4f mul(Vec4f b) {
        Vec4f out = new Vec4f();
        out.x = b.x*m[0][0] + b.y*m[0][1] + b.z*m[0][2] + b.w*m[0][3];
        out.y = b.x*m[1][0] + b.y*m[1][1] + b.z*m[1][2] + b.w*m[1][3];
        out.z = b.x*m[2][0] + b.y*m[2][1] + b.z*m[2][2] + b.w*m[2][3];
        out.w = b.x*m[3][0] + b.y*m[3][1] + b.z*m[3][2] + b.w*m[3][3];
        return out;
    }
    
    
    //This allows 3d positions vections to be used with homogenous matrices 
    //by providing a w value.
    public Vec4f mul(Vec3f b, float w) {
        Vec4f out = new Vec4f();
        out.x = b.x*m[0][1] + b.y*m[0][1] + b.z*m[0][2] + w*m[0][3];
        out.x = b.x*m[1][1] + b.y*m[1][1] + b.z*m[1][2] + w*m[1][3];
        out.x = b.x*m[2][1] + b.y*m[2][1] + b.z*m[2][2] + w*m[2][3];
        out.x = b.x*m[3][1] + b.y*m[3][1] + b.z*m[3][2] + w*m[3][3];
        return out;
    }
    
        
    public void initIdentity() {
        m[0][0] = 1f; m[1][0] = 0f; m[2][0] = 0f; m[3][0] = 0f;
        m[0][1] = 0f; m[1][1] = 1f; m[2][1] = 0f; m[3][1] = 0f;
        m[0][2] = 0f; m[1][2] = 0f; m[2][2] = 1f; m[3][2] = 0f;
        m[0][3] = 0f; m[1][3] = 0f; m[2][3] = 0f; m[3][3] = 1f; 
    }
    
    
    public void setIdentity() {
        m[0][0] = 1f; m[1][0] = 0f; m[2][0] = 0f; m[3][0] = 0f;
        m[0][1] = 0f; m[1][1] = 1f; m[2][1] = 0f; m[3][1] = 0f;
        m[0][2] = 0f; m[1][2] = 0f; m[2][2] = 1f; m[3][2] = 0f;
        m[0][3] = 0f; m[1][3] = 0f; m[2][3] = 0f; m[3][3] = 1f; 
    }
    
    
    public void setTranslation(float x, float y, float z) {
        m[0][0] = 1f; m[1][0] = 0f; m[2][0] = 0f; m[3][0] = 0;
        m[0][1] = 0f; m[1][1] = 1f; m[2][1] = 0f; m[3][1] = 0;
        m[0][2] = 0f; m[1][2] = 0f; m[2][2] = 1f; m[3][2] = 0;
        m[0][3] = x;  m[1][3] = y;  m[2][3] = z;  m[3][3] = 1f; 
    }
    
    
    public void setScale(float x, float y, float z) {
        m[0][0] = x;  m[1][0] = 0f; m[2][0] = 0f; m[3][0] = 0f;
        m[0][1] = 0f; m[1][1] = y;  m[2][1] = 0f; m[3][1] = 0f;
        m[0][2] = 0f; m[1][2] = 0f; m[2][2] = z;  m[3][2] = 0f;
        m[0][3] = 0f; m[1][3] = 0f; m[2][3] = 0f; m[3][3] = 1f; 
    }
    
    
    public void setScale(float s) {
        m[0][0] = s;  m[1][0] = 0f; m[2][0] = 0f; m[3][0] = 0f;
        m[0][1] = 0f; m[1][1] = s;  m[2][1] = 0f; m[3][1] = 0f;
        m[0][2] = 0f; m[1][2] = 0f; m[2][2] = s;  m[3][2] = 0f;
        m[0][3] = 0f; m[1][3] = 0f; m[2][3] = 0f; m[3][3] = 1f; 
    }
    
    
    /**
     * This will create a rotation matrix represent a turn of r degrees about
     * an arbitrary vector defined by x, y, and z;
     * 
     * @param r
     * @param x
     * @param y
     * @param z 
     */
    public void setRotaion(float r, float x, float y, float z) {
        float sinr = (float)Math.sin(Math.toRadians(r));
        float cosr = (float)Math.cos(Math.toRadians(r));
        m[0][0] = (x*x*(1-cosr))+cosr;        m[1][0] = (x*y*(1-cosr))+z*sinr;  
        m[2][0] = (x*z*(1-cosr))-y*sinr;      m[3][0] = 0f;
        
        m[0][1] = (x*y*(1-cosr))-z*sinr;      m[1][1] = (y*y*(1-cosr))+cosr;
        m[2][1] = (y*z*(1-cosr))+x*sinr;      m[3][1] = 0f;
        
        m[0][2] = (x*z*(1-cosr))+y*sinr;      m[1][2] = (y*z*(1-cosr))-x*sinr;
        m[2][2] = (z*z*(1-cosr))+cosr;        m[3][2] = 0f;
        
        m[0][3] = 0f;   m[1][3] = 0f;   m[2][3] = 0f;   m[3][3] = 1f;        
    }
    
    
    /**
     * This will produce a rotation matrix for a turn of r degrees about the 
     * y-axis in local (model or camera) space and parallel to the y-axis in
     * world space. 
     * 
     * @param r 
     */
    public void setRotation(float r) {
        float sinr = (float)Math.sin(Math.toRadians(r));
        float cosr = (float)Math.cos(Math.toRadians(r));
        m[0][0] = cosr;   m[1][0] = 0f; m[2][0] = -sinr; m[3][0] = 0f;
        m[0][1] = 0f;     m[1][1] = 1f; m[2][1] = 0f;    m[3][1] = 0f;
        m[0][2] = sinr;   m[1][2] = 0f; m[2][2] = cosr; m[3][2] = 0f;
        m[0][3] = 0f;     m[1][3] = 0f; m[2][3] = 0f;    m[3][3] = 1f; 
    }
    
    
    public void setPerspective(float height, float width, float fov, 
            float near, float far) {
        float a = width / height;
        float s = (float) Math.tan(fov / 2);
        float r = near - far;
        m[0][0] = 1f / (a * s); m[1][0] = 0f;     m[2][0] = 0f;                    m[3][0] = 0f;
        m[0][1] = 0f;           m[1][1] = 1f / s; m[2][1] = 0f;                    m[3][1] = 0f;
        m[0][2] = 0f;           m[1][2] = 0f;     m[2][2] = -(near + far) / r;     m[3][2] = -1f;
        m[0][3] = 0f;           m[1][3] = 0f;     m[2][3] = -(2f * near * far) / r; m[3][3] = 0f; 
    }
    
    
    public void setPerspective(float a, float fov, float near, float far) {
        float s = (float) Math.tan(fov / 2);
        float r = near - far;
        m[0][0] = 1f / (a * s); m[1][0] = 0f;     m[2][0] = 0f;                     m[3][0] = 0f;
        m[0][1] = 0f;           m[1][1] = 1f / s; m[2][1] = 0f;                     m[3][1] = 0f;
        m[0][2] = 0f;           m[1][2] = 0f;     m[2][2] = -(near + far) / r;      m[3][2] = -1f;
        m[0][3] = 0f;           m[1][3] = 0f;     m[2][3] = -(2f * near * far) / r;  m[3][3] = 0f; 
    }
    
    
    public Mat4f copy() {
        Mat4f other = new Mat4f();
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                other.set(i, j, m[i][j]);
        return other;
    }
    
    
    public static Mat4f giveIdentity() {
        Mat4f out = new Mat4f();
        out.initIdentity();
        return out;
    }
    
    
    public static Mat4f giveTranslation(float x, float y, float z) {
        Mat4f out = new Mat4f();
        out.setTranslation(x, y, z);
        return out;
    }
    
    
    public static Mat4f giveScale(float x, float y, float z) {
        Mat4f out = new Mat4f();
        out.setScale(x, y, z);
        return out;
    }
    
    
    public static Mat4f giveScale(float s) {
        Mat4f out = new Mat4f();
        out.setScale(s);
        return out;
    }
    
    
    public static Mat4f giveRotation(float r, float x, float y, float z) {
        Mat4f out = new Mat4f();
        out.setRotaion(r, x, y, z);
        return out;
    }
    
    
    public static Mat4f giveRotation(float r) {
        Mat4f out = new Mat4f();
        out.setRotation(r);
        return out;
    }
    
    
    public static Mat4f givePerspective(float height, float width, float fov, 
            float near, float far) {
        Mat4f out = new Mat4f();
        out.setPerspective(height, width, fov, near, far);
        return out;
    }
    
    
    public static Mat4f givePerspective(float a, float fov, 
            float near, float far) {
        Mat4f out = new Mat4f();
        out.setPerspective(a, fov, near, far);
        return out;
    }
    
    
    public static Mat4f invertProjection(Mat4f proj) {
        Mat4f out = new Mat4f();
        out.set(0, 0,  1f / proj.get(0, 0));
        out.set(1, 1,  1f / proj.get(1, 1));
        out.set(2, 2,  1f / proj.get(3, 3));
        out.set(3, 2, -1f);
        out.set(2, 3, -proj.get(2, 2) / proj.get(3, 3));
        return out;
    }
    
    
    public void setTransLoc(Location loc) {
        float sinr = (float)Math.sin(Math.toRadians(loc.r));
        float cosr = (float)Math.cos(Math.toRadians(loc.r));
        m[0][0] = cosr;    m[1][0] = 0f;    m[2][0] = -sinr;  m[3][0] = 0f;
        m[0][1] = 0f;      m[1][1] = 1f;    m[2][1] = 0f;     m[3][1] = 0f;
        m[0][2] = sinr;    m[1][2] = 0f;    m[2][2] = cosr;   m[3][2] = 0f;
        m[0][3] = loc.x;   m[1][3] = loc.y; m[2][3] = loc.z;  m[3][3] = 1f; 
    }
    
    
    public static Mat4f giveTransLoc(Location loc) {
        Mat4f out = new Mat4f();
        out.setTransLoc(loc);
        return out;
    }
    
    
    @Override
    public String toString() {
        return("\n"  + super.toString() + 
               "\n|" + m[0][0] + " \t " + m[1][0] + " \t "+ m[2][0] + " \t "+ m[3][0] + "|" +
               "\n|" + m[0][1] + " \t " + m[1][1] + " \t "+ m[2][1] + " \t "+ m[3][1] + "|" +
               "\n|" + m[0][2] + " \t " + m[1][2] + " \t "+ m[2][2] + " \t "+ m[3][2] + "|" + 
               "\n|" + m[0][3] + " \t " + m[1][3] + " \t "+ m[2][3] + " \t "+ m[3][3] + "|\n");
    }
    
    
    public boolean equals(Mat4f b) {
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                if(m[i][j] != b.m[i][j]) return false;
        return true;
    }
}
