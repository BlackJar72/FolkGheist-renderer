//Copyright (C) Jared Blackburn, 2014
package com.folkgeist.ui;

import com.folkgeist.ui.controls.Mousie;
import com.folkgeist.ui.render.IViewer;
import com.folkgeist.util.math.Mat4f;

/**
 * A class that represents the main menus;
 * 
 * @author JaredBGreat (Jared Blackburn)
 */
public class MenuScreen implements IScreen {
    IViewer camera;

    @Override
    public void frame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void set() {
        Mousie.release();
    }

    @Override
    public Mat4f getRenderTransform() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IViewer getCamera() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
