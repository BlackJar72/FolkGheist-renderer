package community.ui.render;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static community.ui.UI.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;



/**
 *
 * @author jared
 */
public class RednerUtils {
    
    
    public static void graphicsInit() throws LWJGLException {        
            goFullScreen();
            //goWindowed();
            Display.create();   
            glClearColor(0f, 0f, 0f, 1f);
            
            glShadeModel(GL_SMOOTH);
            glEnable(GL_BLEND);
            glEnable(GL_TEXTURE_2D);            
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            
            Display.setVSyncEnabled(true);
            
            glEnable(GL_CULL_FACE);
            glEnable(GL_CW);
            glCullFace(GL_BACK);
            glEnable(GL_DEPTH_TEST);
            
            //glEnable(GL_FRAMEBUFFER_SRGB);
    }
    
    
    public static void goFullScreen() throws LWJGLException {
        DisplayMode mode = Display.getDesktopDisplayMode();
        System.out.println(mode.getWidth() + "x" + mode.getHeight() + "x" 
                + mode.getBitsPerPixel() + " " + mode.getFrequency() + "Hz");
        xSize = mode.getWidth();
        ySize = mode.getHeight();
        Display.setDisplayMode(mode);
        fullScreen = true;
        Display.setFullscreen(fullScreen);
    }
    
    
    public static void goWindowed() throws LWJGLException {
        xSize = 800;
        ySize = 600;
        fullScreen = false;
        Display.setFullscreen(fullScreen);
        Display.setDisplayMode(new DisplayMode(xSize, ySize));
    }
    
    
    public static void clearScreen() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }
    
}
