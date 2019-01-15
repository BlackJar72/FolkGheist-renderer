//Copyright (C) Jared Blackburn, 2014
package com.folkgeist.ui;

import com.folkgeist.ui.controls.KeyFunc;
import com.folkgeist.ui.render.IViewer;
import java.util.EnumSet;
import org.lwjgl.opengl.Display;
import static com.folkgeist.globals.State.*;
import com.folkgeist.ui.controls.Mousie;
import com.folkgeist.util.HouseKeeping;
import com.folkgeist.util.math.Mat4f;
import org.lwjgl.input.Mouse;
import static org.lwjgl.opengl.GL11.glClearColor;

/**
 * A class that represents the title screen;
 * 
 * @@author JaredBGreat (Jared Blackburn)
 */
public class TitleScreen implements IScreen {
    IViewer camera;
    

    @Override
    public void frame() {
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        controls();        
    }
    
    
    private void controls() {
        EnumSet<KeyFunc> keyed = KeyFunc.getKeys();
        if(keyed.contains(KeyFunc.EXITNOW)) running = false;
        if(keyed.contains(KeyFunc.MENU)) {
            if(UI.WaitKeys.menu) {
                UI.WaitKeys.menu = false;
                UI.switchScreen(UI.Screens.GAME);                
            }
        } else UI.WaitKeys.menu = true;
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
