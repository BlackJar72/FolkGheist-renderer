//Copyright (C) Jared Blackburn, 2014
package com.folkgeist.ui;

import com.folkgeist.ui.render.IViewer;
import com.folkgeist.util.math.Mat4f;

/**
 * This interface abstracts generic screens, allowing different view and control
 * classes to be used through a common interface.
 * 
 * @author JaredBGreat (Jared Blackburn)
 */
public interface IScreen {
        
    public void frame();    
    public void set();
    public Mat4f getRenderTransform();
    public IViewer getCamera();    
    
}
