//Copyright (C) Jared Blackburn, 2014
package com.folkgeist.util.math;

/*
 // Not sure if this should actually exent Vec3f (to allow 3d opperations) or
 // Vec4f (to allow use with homogeneous transform matrices) -- or if Vec4f 
 // should "cheat" and and allow cross products directly by ignoring the w
 // component (which can already be done, less efficiently, by converting the
 // Vec4f to a Vec3f), i.e., allowing the same variable to represent both 
 // 3d and 4d vectors for the same position.
 //
 // However, r, though store along side the vector components will never be 
 // treated as though it were one; this is really a poistion vector with a 
 // rotation, not a vector containing position and rotaion.  The use of 
 // inheritence is simply to allow direct treatment of the vector.
 */

/**
 * This represents locations in space as a position vector plus a rotation.
 * 
 * This class extends Vec3f so as to allow use in vector calculation of with 
 * the position, while adding a r value for the rotation, allowing for the
 * rotation to be stored with position while preserving 3d calculation in which
 * the rotation should be ignored as not a true vector component 
 * (e.g., cross products).
 * 
 * @author JaredBGreat (Jared Blackburn)
 */
public class Location extends Vec3f implements ILocatable {
    public float r; // the rotation about the y-axis
    private boolean selected = false;
    private Mat4f normRotation;
    private Mat4f transform;

    
    public Location(float x, float y, float z, float r) {
        super(x, y, z);
        //this.r = (float)Math.toRadians(r);
        this.r = r;
        normRotation = Mat4f.giveRotation(r);
        transform    = Mat4f.giveTransLoc(this);
    }
    
    
    public void move(Vec3f change) {
        x += change.x;
        y += change.y;
        z += change.z;        
        transform    = Mat4f.giveTransLoc(this);
    }
    
    
    public void move(float dx, float dy, float dz) {
        x += dx;
        y += dy;
        z += dz;        
        transform    = Mat4f.giveTransLoc(this);
    }
    
    
    public void move(float dx, float dy, float dz, float dr) {
        x += dx;
        y += dy;
        z += dz;
        r += dr;
        normRotation = Mat4f.giveRotation(r);
        transform    = Mat4f.giveTransLoc(this);
    }
    
    
    public void turn(float r) {
        this.r += r;
        normRotation = Mat4f.giveRotation(r);
        transform    = Mat4f.giveTransLoc(this);        
    }
    
    
    public void set(float x, float y, float z, float r) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.r = r;
        normRotation = Mat4f.giveRotation(r);
        transform    = Mat4f.giveTransLoc(this); 
    }

    
    @Override
    public float getXfloat() {
        return x;
    }

    
    @Override
    public float getYfloat() {
        return y;
    }

    
    @Override
    public float getZfloat() {
        return z;
    }

    
    @Override
    public float getRotate() {
        return r;
    }

    
    @Override
    public int getXint() {
        return (int) x;
    }

    
    @Override
    public int getYint() {
        return (int) y;
    }

    
    @Override
    public int getZint() {
        return (int) z;
    }

    
    @Override
    public int getRint() {
        return (int) r;
    }

    
    @Override
    public void setX(float x) {
        this.x = x;
        transform    = Mat4f.giveTransLoc(this);
    }

    
    public Mat4f getTransform() {
        return transform;
    }
    

    @Override
    public void setY(float y) {
        this.y = y;
        transform    = Mat4f.giveTransLoc(this);
    }

    
    @Override
    public void setZ(float z) {
        this.z = z;
        transform    = Mat4f.giveTransLoc(this);
    }

    
    @Override
    public void setRotate(float r) {
        this.r = r;
        normRotation = Mat4f.giveRotation(r);
        transform    = Mat4f.giveTransLoc(this);
    }

    
    @Override
    public void setX(int x) {
        this.x = (float) x;
        transform    = Mat4f.giveTransLoc(this);
    }

    
    @Override
    public void setY(int y) {
        this.y = (float) y;
        transform    = Mat4f.giveTransLoc(this);
    }

    
    @Override
    public void setZ(int z) {
        this.z = (float) z;
        transform    = Mat4f.giveTransLoc(this);
    }

    
    @Override
    public void setR(int z) {
        this.r = (float) r;
        normRotation = Mat4f.giveRotation(r);
        transform    = Mat4f.giveTransLoc(this);
    }

    
    @Override
    public void select() {
        selected = true;
    }

    
    @Override
    public void deselect() {
        selected = false;
    }

    
    @Override
    public void setSelected(boolean s) {
        this.selected = s;
    }

    
    @Override
    public boolean isSelected() {
        return selected;
    }

    
    public float getRotation() {
        return r;
    }

    
    public Mat4f getRotationMatrix() {
        return normRotation;
    }
    
}
