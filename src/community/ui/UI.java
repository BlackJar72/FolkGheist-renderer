//Copyright (C) Jared Blackburn, Sep 13, 2013
package community.ui;

import community.simulation.map.Location;
import community.ui.test.LearningGL;
import community.util.math.Matrix4x4f;
import community.util.HouseKeeping;
import community.simulation.Game;
import community.simulation.Simulation;
import community.*;
import community.simulation.map.CityMap;
import community.simulation.map.Locatable;
import static community.ui.Controls.*;
import community.ui.render.*;
import static community.ui.render.RednerUtils.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.*;
import org.lwjgl.opengl.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import static community.ui.test.LearningGL.*;
import static community.ui.test.TestingStuff.*;
import community.ui.models.Models;

/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */
public class UI {
    public static Camera view;
    public static final int XSIZE = 800;
    public static final int YSIZE = 600;
    public static int xSize = XSIZE;
    public static int ySize = YSIZE;
    public static boolean fullScreen = false;
    private static int mdx = 0, mdy = 0, mdw = 0;
    private static long lasttime;
    private static float delta;
    private static long counter = 0l;
    public static Game game;
    public static Simulation simulation;
    private static int chx = 0, chy = 0;
    private static Matrix4x4f selectRay;
    private static Selector selector;
    private static Locatable target, oldTarget;
    private static Location debBlock;
    
    
    public static void init(Game theGame) {
        try {
            game = theGame;
            simulation = game.getCity(); 
            graphicsInit();
            initControls();
        } catch (LWJGLException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
            HouseKeeping.reportError(UI.class.getName() +", couldn't initialize, "
                    + "exiting (crash)", 1, true);
        }
        ShaderRegistry.initShaderData();
        Models.initModelData();  
        System.out.println(glGetString(GL_VERSION));
        view = new Camera();
        selectRay = view.SelectionRay();
        selector = new Selector(selectRay, view, simulation);
        // This is for learning, experimenting, and tutorial; to be removed 
        // when dones.
        simulation.map = new CityMap();
        testingInit(simulation); 
    }
    
    
    public static void loop() {
        lasttime = getTime();
        while(Globals.running) {
            counter++;
            updateDelta();
            //if((counter % ((int)(60 * delta) + 1)) == 0) view.resetToggle();
            input();
            clearScreen();
            view.positionView();
            show3D();
            show2D();
            view.SelectionRay(selectRay);
            target = selector.select();
            if(target != oldTarget) {
                if(oldTarget != null) oldTarget.deselect();
                if(target != null) target.select();
                oldTarget = target;
            }
            Display.update();
            Display.sync(60);
            if(Globals.running) Globals.running = !Display.isCloseRequested();
        }
        
    }
    
    
    
    
    private static void show3D() {
        // Temporary stand in until the ground can be done better
        glPushMatrix();
            //drawFlatLand();      
            simulation.map.draw();
        glPopMatrix();
        
        //This function links in the main game loop
        simulation.frame();   
    }
    
    
    private static void show2D() {        
        view.switchDim();
            view.crosshairs();
        view.switchDim();
    }
    
    
    public static void input() {
            view.resetToggle();
            if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) Globals.running = false;
            if(Keyboard.isKeyDown(MOVEFOWARD.get())) 
                view.keyForward(0.256f * delta);
            if(Keyboard.isKeyDown(MOVEBACKWARD.get())) 
                view.keyForward(-0.256f * delta);
            if(Keyboard.isKeyDown(MOVELEFT.get())) 
                view.keyStrafe(0.256f * delta);
            if(Keyboard.isKeyDown(MOVERIGHT.get()))
                view.keyStrafe(-0.256f * delta);
            if(Keyboard.isKeyDown(MOVEUP.get()))
                view.moveVerticle(0.256f * delta);
            if(Keyboard.isKeyDown(MOVEDOWN.get()))
                view.moveVerticle(-0.256f * delta);
            if(Keyboard.isKeyDown(GROUNDMODE.get()) 
                    && !Keyboard.getEventKeyState() 
                    && !Keyboard.isRepeatEvent())
                view.toggleFPSgravity();
            if(Keyboard.isKeyDown(HOVERMODE.get()) 
                    && !Keyboard.getEventKeyState() 
                    && !Keyboard.isRepeatEvent())
                view.toggleHovFreeze();
            
        mdx = Mouse.getDX();
        if((mdx != 0f)) view.rotateY(mdx);
        mdy = Mouse.getDY();
        if((mdy != 0f)) view.rotateVerticle(mdy);
        mdw = Mouse.getDWheel();
        if(mdw != 0) view.changeSpeed(((float) mdw) / 1024);
        if(Mouse.isButtonDown(2)) view.resetSpeed();
    }
    
    
    public static void cleanup() {
        // This is for cleaning up experiments, tutorials, and other learning
        // activities, not to be kept in finished version.
        LearningGL.cleanUpExperiments();
        testingCleanUp();
        
        try {
            Display.setFullscreen(false);
        } catch (LWJGLException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // This is the essential cleanUp; more may be added lateer (e.g., to 
        // clean up VBO data.
        Mouse.destroy();
        Keyboard.destroy();
        Display.destroy();
    }
    
   
    private static long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }
    
    
    private static void updateDelta() {
        long now = getTime();
        delta = ((float)(now - lasttime)) / 16;
        lasttime = now;
    }
    
    
    public static float getdelta() {
        return delta;
    } 
    
}
