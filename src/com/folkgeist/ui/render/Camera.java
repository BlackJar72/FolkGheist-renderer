//Copyright (C) Jared Blackburn, 2014
package com.folkgeist.ui.render;

import static com.folkgeist.globals.DisplaySettings.*;
import com.folkgeist.ui.controls.KeyFunc;
import com.folkgeist.util.math.Mat4f;
import com.folkgeist.util.math.Vec4f;
import java.util.EnumSet;


/**
 * This will be the main camera class for viewing the world.
 * 
 * @author JaredBGreat (Jared Blackburn)
 */
public class Camera implements IViewer {
    Mat4f perspective, inverse, affine;
    Vec4f[] planes;
    float aspect, fov, far, near;
    float x, y, z, ry, rv;
    
    
    public Camera(float aspect, float fov, float far, float near) {
        this.aspect = aspect;
        this.fov    = fov;
        this.far    = far;
        this.near   = near;
        this.planes = Vec4f.frustrumPlanes(aspect, fov, near, far);
        this.perspective = Mat4f.givePerspective(aspect, fov, near, far);        
        this.inverse     = Mat4f.invertProjection(perspective);
        this.affine      = Mat4f.giveIdentity();
        this.x = this.y = this.z = this.ry = this.rv = 0;
    }
    
    
    public Camera(float xsize, float ysize, float fov, float far, float near) {
        this(xsize/ysize, fov, far, near);
    }
    
    
    public Camera() {
        this(((float)xSize)/((float)ySize), 70, 0.0256f, 1024f);
    }
    
    
    protected void setFrustum(float aspect, float fov, float far, float near) {        
        this.aspect = aspect;
        this.fov    = fov;
        this.far    = far;
        this.near   = near;
        this.perspective = Mat4f.givePerspective(aspect, fov, near, far);
        this.inverse     = Mat4f.invertProjection(this.perspective);
        this.planes      = Vec4f.frustrumPlanes(aspect, fov, near, far);
        this.planes[0] = this.planes[0].mul(affine);
        this.planes[1] = this.planes[1].mul(affine);
        this.planes[2] = this.planes[2].mul(affine);
        this.planes[3] = this.planes[3].mul(affine);
        this.planes[4] = this.planes[4].mul(affine);
        this.planes[5] = this.planes[5].mul(affine);
//        this.planes[0] = affine.mul(this.planes[0]);
//        this.planes[1] = affine.mul(this.planes[1]);
//        this.planes[2] = affine.mul(this.planes[2]);
//        this.planes[3] = affine.mul(this.planes[3]);
//        this.planes[4] = affine.mul(this.planes[4]);
//        this.planes[5] = affine.mul(this.planes[5]);
    }
    
    
    protected void resetFrustum() {
        setFrustum(((float)xSize)/((float)ySize), fov, far, near);
    }
    
    
    @Override
    public boolean cull(Vec4f q, float r) {
        boolean out = true;
        byte counter = 0;
        while(out && (counter < 6)) {
            out = out && (q.dot(planes[counter++]) >= -r);
        }        
        return !out;
    }
    

    @Override
    public void update() {
        updateAffine();
    }
    
    
    /**
     * This handles camera controlling input and calls other methods to 
     * implement the resulting actions.
     * 
     * This should be an improvement over the old system in which the UI class 
     * interpreted such instructions and called a separate public message for
     * each.  In this way all camera-orient instructions can be handled by the
     * camera itself, allowing for great abstraction and better organization of
     * function and corresponding code.
     * 
     * @param keyed EnumSet<KeyFunc> data from the keyboard and mouse buttons.
     * @param squeak An int[] array containing mouse movements and position.
     */
    @Override    
    public void input(EnumSet<KeyFunc> keyed, int[] squeak) {
        if(keyed.contains(KeyFunc.FORWARD)) forward(0.256f);
        if(keyed.contains(KeyFunc.BACK))    forward(-0.256f);
        if(keyed.contains(KeyFunc.RIGHT))   sideways(-0.256f);
        if(keyed.contains(KeyFunc.LEFT))    sideways(0.256f);
        if(keyed.contains(KeyFunc.UP))      vertical(0.256f);
        if(keyed.contains(KeyFunc.DOWN))    vertical(-0.256f);
        mousex((float)squeak[1]);
        mousey((float)squeak[3]);
        updateAffine();
    }
    

    @Override
    public Mat4f getTransform() {        
        return perspective.mul(affine);
    }
    
    
    private void forward(float velocity) {
        x += velocity * (float)Math.cos(Math.toRadians(ry + 90f));
        z += velocity * (float)Math.sin(Math.toRadians(ry + 90f));
    }
    
    
    private void sideways(float velocity) {
        x += velocity * (float)Math.cos(Math.toRadians(ry));
        z += velocity * (float)Math.sin(Math.toRadians(ry));
    }
    
    
    private void vertical(float velocity) {
        y -= velocity;
    }
    
    
    private void mousex(float velocity) {
        ry += (velocity / 10);
        if(ry > 360f) ry -= 360f;
        else if(ry < -360f) ry += 360f; 
    }
    
    
    private void mousey(float velocity) {
        rv -= (velocity / 10);
        if(rv > 85f) rv = 85f;
        else if(rv < -90f) rv = -90f;        
    }
    
    
    private void updateAffine() {
        affine = Mat4f.giveRotation(ry)
                .mul(Mat4f.giveRotation(rv, (float)(Math.cos(Math.toRadians(ry))), 
                                        0, (float)(Math.sin(Math.toRadians(ry)))))
                .mul(Mat4f.giveTranslation(x, y, z));    
        resetFrustum();    
    }
    
}
