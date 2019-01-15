//Copyright (C) Jared Blackburn, 2014
package com.folkgeist.ui;

import static com.folkgeist.globals.State.*;
import com.folkgeist.ui.render.IViewer;
import static com.folkgeist.ui.render.RenderUtils.clearScreen;
import static com.folkgeist.ui.render.RenderUtils.closeGraphics;
import static com.folkgeist.ui.render.RenderUtils.graphicsInit;
import static com.folkgeist.ui.shaders.ShaderRegistry.initShaderData;
import static com.folkgeist.util.HouseKeeping.reportError;
import com.folkgeist.util.math.Mat4f;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

/**
 * This starts the user interface and runs the main game loop, though most
 * specific functions are allocated to other classes.
 * 
 * @author JaredBGreat (Jared Blackburn)
 */
public class UI {
    static IScreen     currentScreen;  // References the currently active screen
    static GameScreen  gameScreen;     // Created with game and passed here
    static TitleScreen titleScreen = new TitleScreen();
    static MenuScreen  menuScreen  = new MenuScreen();
    
    protected static class WaitKeys {
        public static boolean menu = true;
    }
    
    
    public enum Screens {
        GAME,
        TITLE,
        MENU;
    }
    
    
    public static void start() {
        try {
            graphicsInit();
        } catch (LWJGLException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
            reportError("ERORR! Failed to start graphics; " 
                    + "CRASHING NOW!", 3, ex, true);
        }
        initShaderData();
        currentScreen = titleScreen;
        
        //These are temporary things until I can get new versions set up
        //setPerspective();
        gameScreen = new GameScreen();
    }
    
    
    public static void loop() {
        while(running) {
            clearScreen();
            currentScreen.frame(); 
            Display.update();
            Display.sync(60);
            if(running) running = !Display.isCloseRequested();
            if(playing) playing = running;
        }
    }
    
    
    public static void end() {
        closeGraphics();
    }
    
    
    public static void switchScreen(Screens which) {
        switch(which) {
            case GAME:
                currentScreen = gameScreen;
                break;
            case TITLE:
                currentScreen = titleScreen;
                break;
            case MENU:
                currentScreen = menuScreen;
                break;
            default:
                throw new AssertionError(which.name());
        }
        currentScreen.set();
    }
    
    
    public static Mat4f getRenderTranform() {
        return currentScreen.getRenderTransform();
    }  
    
    
    public static IViewer getCamera() {
        return currentScreen.getCamera();
    }  
    
}
