//Copyright (C) Jared Blackburn, 2014
package com.folkgeist.ui.render;

import com.folkgeist.ui.controls.KeyFunc;
import com.folkgeist.util.math.Mat4f;
import com.folkgeist.util.math.Vec4f;
import java.util.EnumSet;

/**
 * This is an abstract representation of a camera incase more than on is needed 
 * and to keep an general standard of what a camera should do separate from its implementation.
 * 
 * @author JaredBGreat (Jared Blackburn)
 */
public interface IViewer {
    
    
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
    public void input(EnumSet<KeyFunc> keyed, int[] squeak);
    
    
    /**
     * This will return the correct transform matrix for the represented 
     * camera, that is, the correct product of the projection and 
     * position (affine tranformation) matrices.
     * 
     * @return A Mat4f representation of the viewers location and perspective
     */
    public Mat4f getTransform();
    
    
    public void update();
    
    
    public boolean cull(Vec4f q, float r);
}
