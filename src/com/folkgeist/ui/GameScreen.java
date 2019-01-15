//Copyright (C) Jared Blackburn, 2014
package com.folkgeist.ui;

import static com.folkgeist.globals.State.running;
import com.folkgeist.simulation.Game;
import com.folkgeist.ui.controls.KeyFunc;
import com.folkgeist.ui.controls.Mousie;
import com.folkgeist.ui.models.Models;
import com.folkgeist.ui.render.Camera;
import com.folkgeist.ui.render.IViewer;
import com.folkgeist.util.math.Location;
import com.folkgeist.util.math.Mat4f;
import java.util.EnumSet;
import static org.lwjgl.opengl.GL11.glClearColor;

/**
 * A class to represent the game UI, including camera and controls, during 
 * actual play.  Specifically, it holds a reference to and controls the 3D 
 * camera, the main game controls, and the 3D picker / ray-caster (which may be
 * attached to the camera).  This class does not deal with game logic, which 
 * will be handled separately by by the Game class.
 * 
 * @author JaredBGreat (Jared Blackburn)
 */
public class GameScreen implements IScreen {
    static IViewer camera;
    //static com.folkgeist.ui.render.old.Camera  kam;
    static int tmp;
    static final Mat4f tmpRot   = Mat4f.giveRotation(0);
    static final Mat4f tmpTrans = Mat4f.giveTransLoc(new Location(5, -1, 32, 0));
    
    
    private static class WaitKeys {
        static boolean hover;
        static boolean gravity;
        static boolean rclick;
        static boolean lclick;
    }
    
    
    public GameScreen() {           
        //kam = new Camera(); 
        camera = new Camera();
    }
    

    @Override
    public void frame() {
        glClearColor(0.04f, 0.01f, 0.08f, 1.0f);
        Game.game.map.draw(camera);
        //kam.positionView();
        Models.getModel("house1").draw(new Location(3, -1, -10, tmp));
        Models.getModel("building1").draw(new Location(-5, -1, -32, -tmp));
        Models.getModel("building1").draw(tmpTrans, tmpRot);
        //show2D();
        updatetmp();
        controls(); 
    }
    
    
    private void controls() {
        EnumSet<KeyFunc> keyed = KeyFunc.getKeys();
        int[] squeak = Mousie.getData();
        camera.input(keyed, squeak);
        //oldControls(keyed, squeak);
        if(keyed.contains(KeyFunc.EXITNOW)) running = false;
        if(keyed.contains(KeyFunc.MENU)) {
            if(UI.WaitKeys.menu) {
                UI.WaitKeys.menu = false;
                UI.switchScreen(UI.Screens.TITLE);                
            }
        } else UI.WaitKeys.menu = true;
    }
    
    
//    @Deprecated
//    private void oldControls(EnumSet<KeyFunc> keyed, int[] squeak) {
//            float delta = 1f;
//            if(keyed.contains(KeyFunc.FORWARD)) 
//                kam.keyForward(0.256f * delta);
//            if(keyed.contains(KeyFunc.BACK)) 
//                kam.keyForward(-0.256f * delta);
//            if(keyed.contains(KeyFunc.LEFT)) 
//                kam.keyStrafe(0.256f * delta);
//            if(keyed.contains(KeyFunc.RIGHT))
//                kam.keyStrafe(-0.256f * delta);
//            if(keyed.contains(KeyFunc.UP))
//                kam.moveVerticle(0.256f * delta);
//            if(keyed.contains(KeyFunc.DOWN))
//                kam.moveVerticle(-0.256f * delta); 
//            
//            if(keyed.contains(KeyFunc.HOVER)) {
//                if(WaitKeys.hover) {
//                    WaitKeys.hover = false;
//                    kam.toggleHovFreeze();
//                }
//            } else WaitKeys.hover = true;
//            
//            if(keyed.contains(KeyFunc.GROUND)) {
//                if(WaitKeys.gravity) {
//                    WaitKeys.gravity = false;
//                    kam.toggleFPSgravity();
//                }
//            } else WaitKeys.gravity = true;
//            
//            if((squeak[1] != 0f)) kam.rotateY(squeak[1]);
//            if((squeak[3] != 0f)) kam.rotateVerticle(squeak[3]);
//            if(squeak[4] != 0) kam.changeSpeed(((float) squeak[4]) / 1024);
//            if(keyed.contains(KeyFunc.WCLICK)) kam.resetSpeed();
//    }
//    
//    
//    @Deprecated
//    private static void show2D() {        
//        kam.switchDim();
//            kam.crosshairs();
//        kam.switchDim();
//    }
    
    private void updatetmp() {
        tmp++;
        if(tmp > 360) tmp -= 360;
    }
    

    @Override
    public void set() {
        Mousie.grab();
        camera.update();
    }
    

    @Override
    public Mat4f getRenderTransform() {
        return camera.getTransform();
    }
    

    @Override
    public IViewer getCamera() {
        return camera;
    }
    
}
