//Copyright (C) Jared Blackburn, Sep 13, 2013
package community.ui;

import community.util.math.SelectorTrigHelper;
import community.util.math.Matrix4x4f;
import community.simulation.map.Location;
import java.awt.geom.Line2D;
import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;
import community.*;
import community.simulation.map.CityMap;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import community.util.math.SelectorTrigHelper;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;
import static community.ui.UI.*;

/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */
public class Camera {
    private float x, y, z;      // Position on each axis
    private float ry, rv;       // Rotations
    private float fov;          // Field of View
    private float aspectRatio;  // Width / Height
    private float nearClip;     // Minimum view distance
    private float farClip;      // Maximum view distance
    private float spd;          // Camera movement rate
    private boolean pitchVel;   // Does forawrd movement change height w/ pitch
    private boolean FPSgravity; // Are we in fist person mode?
    private boolean hovFreeze;  // Are we hovering with release mouse
    private boolean[] toggleable = {false, false, false};
    private static FloatBuffer perspectiveMatrix = BufferUtils.createFloatBuffer(16);
    private static FloatBuffer orthoMatrix       = BufferUtils.createFloatBuffer(16);
    private boolean perspectiveMode = true;    
    private float chx = 0, chy = 0;
    private SelectorTrigHelper pix2deg = new SelectorTrigHelper();
    
    
    
    public Camera(float x, float y, float z, float rx, float ry, float rz, 
            float rv, float xc, float yc,
            float fov, float aspectRatio, float nearClip, float farClip) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.ry = ry;
        this.rv = rv;
        this.fov = fov;
        this.aspectRatio = aspectRatio;
        this.nearClip = nearClip;
        this.farClip = farClip;
        pitchVel = false;
        init3D();
    }
    
    public Camera(float x, float y, float z, float ry, float rv, 
            float fov, float nearClip, float farClip, float speed) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.ry = ry;
        this.rv = rv;
        this.fov = fov;
        this.aspectRatio = xSize / ySize;
        this.nearClip = nearClip;
        this.farClip = farClip;
        this.spd = speed;
        pitchVel = false;        
        init3D();
    }
    
    public Camera() {
        x = z = ry = 0f;
        y = -32.0f;
        rv = 60f;
        fov = 70f;
        aspectRatio = (float)((double)(xSize) / ((double)(ySize)));
        nearClip = 0.05f;
        farClip = 2048f;
        spd = 1.0f;
        pitchVel = false;
        init3D();
    }
    
    
    public void init3D() {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        gluPerspective(fov, aspectRatio, nearClip, farClip);
        
        glGetFloat(GL_PROJECTION_MATRIX, perspectiveMatrix);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, xSize, 0, ySize, -1, 1);
        glGetFloat(GL_PROJECTION_MATRIX, orthoMatrix);
        glLoadMatrix(perspectiveMatrix);
        
        glMatrixMode(GL_MODELVIEW);
        glClearColor(0.04f, 0.0f, 0.12f, 0.1f);
        glEnable(GL_DEPTH_TEST);
        
    }
    
    
    public void positionView() {
        glMatrixMode(GL_MODELVIEW); // Redundant now, but perhaps not in other context
        glLoadIdentity();
        glRotatef(ry, 0, 1, 0);
        glRotatef(rv, (float) (Math.cos(Math.toRadians(ry))), 0, 
                (float) (Math.sin(Math.toRadians(ry))));
        glTranslatef(x, y, z);
                
    }
    
    
    public void keyForward(float speed) {
        if(hovFreeze && !FPSgravity) {
            rv -= (speed * 5);
            if(rv > 85f) rv = 85f;
            else if(rv < -90f) rv = -90f;
        }
        else moveForward(speed);
    }
    
    
    public void moveForward(float speed) {
        // To go backward, just use negative speed
        if(pitchVel && !FPSgravity && !hovFreeze) {
            y += spd * speed * Math.sin(Math.toRadians(rv));
            z += spd * speed * Math.sin(Math.toRadians(ry + 90f)) 
                    * Math.cos(Math.toRadians(rv));
            x += spd * speed * Math.cos(Math.toRadians(ry + 90f)) 
                    * Math.cos(Math.toRadians(rv));
        } else {
            z += spd * speed * Math.sin(Math.toRadians(ry + 90f));
            x += spd * speed * Math.cos(Math.toRadians(ry + 90f));
        }
        // Later, in a map with varied height this with will groud leve + 0.5f;
        if(y > 0.6f || FPSgravity) y = 0.6f;
    }
    
    
    public void keyStrafe(float speed) {
        if(hovFreeze && !FPSgravity) {            
            ry += (speed * -5);
            if(ry > 360f) ry -= 360f;
            else if(ry < -360f) ry += 360f;
        }
        else strafe(speed);
            
    }
    
    
    public void strafe(float speed) {
        // To go backward, just use negative speed
        z += spd * speed * Math.sin(Math.toRadians(ry));
        x += spd * speed * Math.cos(Math.toRadians(ry));
    }
    
    
    public void moveVerticle(float speed) { 
        //if(hovFreeze) return;       
        if(!FPSgravity) y -= spd * speed;
        if(y > 0.6f || FPSgravity) y = 0.6f;
    }
    
    
    public void rotateY(float angVel)  {
        if(hovFreeze && !FPSgravity) {
            moveCrossHairs(angVel, 0);
            //chx += angVel;
            return;
        }
        ry += (angVel / 10);
        if(ry > 360f) ry -= 360f;
        else if(ry < -360f) ry += 360f;
    }
    
    
    public void rotateVerticle(float angVel) {
        if(hovFreeze && !FPSgravity) {
            moveCrossHairs(0, angVel);
            //chy += angVel;
            return;
        }
        rv -= (angVel / 10);
        if(rv > 85f) rv = 85f;
        else if(rv < -90f) rv = -90f;
    }
    
    
    public void changeSpeed(float accel) {
        if(hovFreeze) return;
        spd += accel;
        if(spd < 0.05) spd = 0.1f;
        else if(spd > 10.00) spd = 5.00f;
    }
    
    
    public void resetSpeed() {
        if(hovFreeze) return;
        spd = 1.0f;
    }
    
    
    public void toggleFPSgravity() {
        if(toggleable[1]) {
            FPSgravity = !FPSgravity;
            toggleable[1] = false;
        }  
        // Later, y = ground level + 0.5f, still later, tansport view to
        // selected possition.
        resetCrossHairs();
        if(FPSgravity) {
            y = 0.6f;
            spd = 0.2f;
            rv = 0f;
        } else {
            y = -32.0f;
            spd = 1.0f;
            rv = 60f;
        }
    }
    
    
    public void toggleHovFreeze() {
        if(toggleable[2]) {
            hovFreeze = !hovFreeze;
            toggleable[2] = false;
        }
        if(!hovFreeze) resetCrossHairs();
        if(FPSgravity) toggleFPSgravity();
    }
    
    
    public void resetToggle() {
        if(!Keyboard.isKeyDown(Keyboard.KEY_TAB))toggleable[0] = true;
        if(!Keyboard.isKeyDown(Keyboard.KEY_G))toggleable[1] = true;
        if(!Keyboard.isKeyDown(Keyboard.KEY_H))toggleable[2] = true;
    }
    
    
    public void switchDim() {
        if(perspectiveMode) {
            glMatrixMode(GL_PROJECTION);
            glLoadMatrix(orthoMatrix);
            glMatrixMode(GL_MODELVIEW);
            //glDisable(GL_DEPTH_TEST);
            perspectiveMode = false;
        } else {
            glMatrixMode(GL_PROJECTION);
            glLoadMatrix(perspectiveMatrix);
            glMatrixMode(GL_MODELVIEW);
            //glEnable(GL_DEPTH_TEST);
            perspectiveMode = true;
        }
    }
    
    
    protected void crosshairs() {
        glPushMatrix();
            glLoadIdentity();
            glColor4f(0.75f, 0.75f, 0.75f, 1.00f);
            glBegin(GL_LINES);
                glVertex3f((xSize * 0.48f + chx), (ySize / 2f + chy), 0f);
                glVertex3f((xSize * 0.52f + chx), (ySize / 2f + chy), 0f);
                glVertex3f((xSize / 2f + chx), (ySize * 0.474f + chy), 0f);
                glVertex3f((xSize / 2f + chx), (ySize * 0.526f + chy), 0f);
            glEnd();
            glPopMatrix();
    }
    
    
    protected void moveCrossHairs(float x, float y) {
        chx += x;
        chy += y;
        if(chx >   (xSize / 2f))  {
            chx =  (xSize / 2f);
            strafe(-(x / 10f));
        }
        if(chx <  -(xSize / 2f)) {
            chx = -(xSize / 2f);
            strafe(-(x / 10f));
        }
        if(chy >   (ySize / 2f))  {
            chy =  (ySize / 2f);
            moveForward(y / 10f);
        }
        if(chy <  -(ySize / 2)) {
            chy = -(ySize / 2);
            moveForward(y / 10f);
        }
    }
    
    
    protected void resetCrossHairs() {
        chx = chy = 0;
    }
    
    
    public Matrix4x4f SelectionRay() {
        float ang1 = pix2deg.getDegs(chx);
        float ang2 = pix2deg.getDegs(chy);        
        float vy = (float) Math.sin(Math.toRadians(rv + ang2));        
        float vz = (float) (Math.sin(Math.toRadians(ry + 90f + ang1)) 
                    * Math.cos(Math.toRadians(rv)));
        float vx = (float) (Math.cos(Math.toRadians(ry + 90f + ang1)) 
                    * Math.cos(Math.toRadians(rv)));
        Matrix4x4f ray = new Matrix4x4f();
        // Forth Row (Row 3): x, y, z components of vector trhough 
        // cursor / pointer / crosshairs
        ray.set(0, 3, vx);
        ray.set(1, 3, vy);
        ray.set(2, 3, vz);        
        // First Row (Row 0): ratios of vector to vx
        ray.set(0, 0, 1f);
        ray.set(1, 0, ((vx != 0) ? (vy / vx) : (float) 0x7FFFFFFFFFFFFFFFL));
        ray.set(2, 0, ((vx != 0) ? (vz / vx) : (float) 0x7FFFFFFFFFFFFFFFL));
        // Second Row (Row 1): ratios of vector to vy
        ray.set(0, 1, ((vy != 0) ? (vx / vy) : (float) 0x7FFFFFFFFFFFFFFFL));
        ray.set(1, 1, 1f);
        ray.set(2, 1, ((vy != 0) ? (vz / vy) : (float) 0x7FFFFFFFFFFFFFFFL));
        // Third Row (Row 2): ratios of vector to vz
        ray.set(0, 2, ((vz != 0) ? (vx / vz) : (float) 0x7FFFFFFFFFFFFFFFL));
        ray.set(1, 2, ((vz != 0) ? (vy / vz) : (float) 0x7FFFFFFFFFFFFFFFL));
        ray.set(2, 2, 1f);
        return ray;
    }
    
    
    public void SelectionRay(Matrix4x4f ray) {
        float ang1 = pix2deg.getDegs(chx);
        float ang2 = pix2deg.getDegs(chy);        
        float vy = (float) Math.sin(Math.toRadians(rv + ang2)); 
        // Need a way to correct for effects of pitch on ay vectors without
        // effecting the effect of cursor movement in hover mode.  This I 
        // have now isn't at all working.
        float vz = (float) ((Math.sin(Math.toRadians(ry + 90f)) //);
                   * Math.cos(Math.toRadians(rv))) 
                   + (Math.sin(Math.toRadians(ry + 90f + ang1)) 
                   - (Math.sin(Math.toRadians(ry + 90f))))); 
        
        float vx = (float) ((Math.cos(Math.toRadians(ry + 90f)) //);
                   * Math.cos(Math.toRadians(rv))) 
                   + (Math.cos(Math.toRadians(ry + 90f + ang1)) 
                   - (Math.cos(Math.toRadians(ry + 90f))))); 
        // Forth Row (Row 3): x, y, z components of vector trhough 
        // cursor / pointer / crosshairs
        ray.set(0, 3, vx);
        ray.set(1, 3, vy);
        ray.set(2, 3, vz);        
        // First Row (Row 0): ratios of vector to vx
        ray.set(0, 0, 1f);
        ray.set(1, 0, ((vx != 0) ? -(vy / vx) : Float.MAX_VALUE));
        ray.set(2, 0, ((vx != 0) ? -(vz / vx) : Float.MAX_VALUE));
        // Second Row (Row 1): ratios of vector to vy
        ray.set(0, 1, ((vy != 0) ? -(vx / vy) : Float.MAX_VALUE));
        ray.set(1, 1, 1f);
        ray.set(2, 1, ((vy != 0) ? -(vz / vy) : (float) Float.MAX_VALUE));
        // Third Row (Row 2): ratios of vector to vz
        ray.set(0, 2, ((vz != 0) ? -(vx / vz) : (float) Float.MAX_VALUE));
        ray.set(1, 2, ((vz != 0) ? -(vy / vz) : (float) Float.MAX_VALUE));
        ray.set(2, 2, 1f);
    }
    
    
    
    //TODO?: Getters and setters?  Well, probably not for this; best self-contained.

    public boolean isFPSgravity() {
        return FPSgravity;
    }

    public float getAspectRatio() {
        return aspectRatio;
    }

    public float getChx() {
        return chx;
    }

    public float getChy() {
        return chy;
    }

    public float getFarClip() {
        return farClip;
    }

    public float getFov() {
        return fov;
    }

    public boolean isHovFreeze() {
        return hovFreeze;
    }

    public float getNearClip() {
        return nearClip;
    }

    public float getRv() {
        return rv;
    }

    public float getRy() {
        return ry;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }
}
