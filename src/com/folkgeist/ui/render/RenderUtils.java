//Copyright (C) Jared Blackburn, 2014
package com.folkgeist.ui.render;

import static com.folkgeist.globals.DisplaySettings.*;
import static com.folkgeist.globals.Constants.*;
import com.folkgeist.util.HouseKeeping;
import com.folkgeist.util.math.Mat4f;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;
//import static org.lwjgl.opengl.GL30.*;



/**
 * This class contains rendering utilities, especially, but may not be limited 
 * to those for setting and opening the display.
 *  
 * @author JaredBGreat (Jared Blackburn)
 */
public class RenderUtils {
    private static float t1 = 0, t2 = 0, t3 = 0, s1 = 1, s2 = 1, s3 =-1;
    //public  static final Mat4f proj = Mat4f.givePerspective(xSize/ySize, 70f, 0.0256f, 1024f);
    
    /**
     * A method for initializing the graphics system and setting up the display;
     * this should only be used once at start up.
     * 
     * @throws LWJGLException 
     */
    public static void graphicsInit() throws LWJGLException { 
            HouseKeeping.reportLog("Starting graphics initializtion " 
                    + "/ display creation");
            if(fullscreen) goFullScreen();
            else goWindowed(); 
            Display.create();
            Display.setResizable(false);
            glClearColor(0f, 0f, 0f, 1f);
            
            glShadeModel(GL_SMOOTH);
            glEnable(GL_BLEND);
            glEnable(GL_TEXTURE_2D);            
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            
            Display.setVSyncEnabled(true);
            
            glEnable(GL_CULL_FACE);
            glCullFace(GL_BACK);
            glEnable(GL_DEPTH_TEST);
            
            HouseKeeping.reportLog("OpenGL version " + glGetString(GL_VERSION));
    }
    
    
    /**
     * A utility method to enter full screen mode.
     * 
     * @throws LWJGLException 
     */
    public static void goFullScreen() throws LWJGLException {
        DisplayMode mode = Display.getDesktopDisplayMode();
        HouseKeeping.reportLog("Full screen mode set");
        HouseKeeping.reportLog("Display mode " 
                + mode.getWidth() + "x" + mode.getHeight() + "x" 
                + mode.getBitsPerPixel() + ", " + mode.getFrequency() + " Hz");
        xSize = mode.getWidth();
        ySize = mode.getHeight();
        Display.setDisplayMode(mode);
        fullscreen = true;
        Display.setFullscreen(fullscreen);
    }
    
    
    /**
     * A utility method to enter windowed mode.
     * 
     * @throws LWJGLException 
     */
    public static void goWindowed() throws LWJGLException {
        xSize = xWinSize;
        ySize = yWinSize;
        fullscreen = false;
        Display.setFullscreen(fullscreen);
        Display.setDisplayMode(new DisplayMode(xSize, ySize));
        Display.setTitle(gametitle);
    }
    
    
    /**
     * A utility method for clearing the screen, this simply wraps glClear with
     * standardized settings.
     */
    public static void clearScreen() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }
    
    
    /**
     * This is a cleanup function to close the graphics when the game is 
     * shutdown.
     */    
    public static void closeGraphics() {
        HouseKeeping.reportLog("Shutting down display");
        Display.destroy();
        //HouseKeeping.reportLog("Display destroyed");
    }
    
    
    @Deprecated
    public static void setPerspective() {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        gluPerspective(70, xSize/ySize, 0.0256f, 1024);
        glMatrixMode(GL_MODELVIEW);
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        glEnable(GL_DEPTH_TEST); 
    }
    
    
    @Deprecated
    public static void setOrtho() {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, xSize, 0, ySize, -1, 1);
        glMatrixMode(GL_MODELVIEW);
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        glEnable(GL_DEPTH_TEST); 
    }
    
    
    
    
    /**
     * This is purely a lame test function.  This should never normally be
     * called and should be removed when and if the code is ever mature.
     */
    @Deprecated
    public static void cube() {
        glMatrixMode(GL_MODELVIEW);
        glPushMatrix();
        //glTranslatef(0f, 0f, -10f + (float)(5 * Math.sin(Math.toRadians((double) tmp))));
        glTranslatef(0f, 0f, -16f);
        glRotatef((float) 0, 1, 1, 0);
        glBegin(GL_QUADS);
            glColor3f(0.85f, 0.2f, 0.1f);
            glVertex3f(-1, -1, -1);
            glVertex3f(-1, 1, -1);
            glVertex3f(1, 1, -1);
            glVertex3f(1, -1, -1);
            glVertex3f(-1, -1, 1);
            glVertex3f(-1, 1, 1);
            glVertex3f(1, 1, 1);
            glVertex3f(1, -1, 1);
            glColor3f(0.2f, 0.85f, 0.1f);
            glVertex3f(-1, -1, -1);
            glVertex3f(-1, -1, 1);
            glVertex3f(1, -1, 1);
            glVertex3f(1, -1, -1);
            glVertex3f(-1, 1, -1);
            glVertex3f(-1, 1, 1);
            glVertex3f(1, 1, 1);
            glVertex3f(1, 1, -1);
            glColor3f(0.1f, 0.2f, 0.85f);
            glVertex3f(-1, -1, -1);
            glVertex3f(-1, -1, 1);
            glVertex3f(-1, 1, 1);
            glVertex3f(-1, 1, -1);
            glVertex3f(1, -1, -1);
            glVertex3f(1, -1, 1);
            glVertex3f(1, 1, 1);
            glVertex3f(1, 1, -1);
        glEnd();        
        glPopMatrix();
    }
    
    /**
     * This is purely a lame test function.  This should never normally be
     * called and should be removed when and if the code is ever mature.
     */
    @Deprecated    
    public static void orangeSquare() {
            glMatrixMode(GL_MODELVIEW);
            glPushMatrix();
            glColor3f(0.75f, 0.5f, -0.1f);
            glTranslatef(t1, t2, t3 -64);
            glRotatef((float) 0, 3, 3, 1);
            glBegin(GL_QUADS);
                glVertex3f(1, -1, -1);
                glVertex3f(1, 1, -1);
                glVertex3f(-1, 1, -1);
                glVertex3f(-1, -1, -1);
            glEnd();
            glPopMatrix();
            t1 += 0.03*s1;
            t2 += 0.05*s2;
            t3 += 0.07*s3;
            if(t1 >  1) s1 = -1;
            if(t1 < -1) s1 =  1;
            if(t2 >  1) s2 = -1;
            if(t2 < -1) s2 =  1;
            if(t3 >  1) s2 = -1;
            if(t3 < -1) s3 =  1;
    }
    
}
