//Copyright (C) Jared Blackburn, Sep 18, 2013
package community.ui.test;

import community.*;
import community.simulation.map.CityMap;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.*;
import org.lwjgl.opengl.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.nio.FloatBuffer;

/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */
public class LearningGL {
        
    static int tmp = 0;
    static int vboidv, vboidc, vboidn, vboidp;
    static public int numVerts, vertSize, colorSize, numSegs, numSides;
    static FloatBuffer vertData, colorData, normData, peepsData;
    static int numTowers, numPeeps;
    static float[][] vbolocs;
    static float[][][] peeplocs;
    
    
    
    public static void initExperiments() {
        makeVBO();
    }
    
    
    public static void renderExperiments() {
            drawSquares3d();
            cube();
            //drawFlatLand();
            drawVBO();          
    }
    
    
    public static void cleanUpExperiments() {
        cleanVBO();    
    }
    
        
    /*******************************************************/
    /*  EVERTYING BELOW HERE IS LEARNING / EXPERIMENTING;  */ 
    /*  not part of the real game!                         */  
    /*******************************************************/
    
    
    public static void drawSquares() {
        glPushMatrix(); // Very important to prevent inter-drawing contamination
        glLoadIdentity();
        long t = System.currentTimeMillis();
        glTranslatef(((float)(Math.sin(Math.toRadians((t / 7) % 360)) * 400)), 
                ((float)(Math.sin(Math.toRadians((t / 13) % 360)) * 320)), 0);
        glRotatef((float)((360 - t / 10) % 360), 0, 0, 1);
        glBegin(GL_QUADS);
            glColor3f(0.5f, 0.25f, 0.75f);
            glVertex2f(-16,  16);
            glVertex2f(16,   16);
            glVertex2f(16,  -16);
            glVertex2f(-16, -16);
            
            glColor3f(0.25f, 0.75f, 0.5f);
            glVertex2f(50,  100);
            glVertex2f(100, 100);
            glVertex2f(100, 150);
            glVertex2f(50,  150);
            
            glColor3f(0.75f, 0.25f, 0.5f);
            glVertex2f(250, 200);
            glVertex2f(300, 200);
            glVertex2f(300, 250);
            glVertex2f(250, 250); 
            
            glColor3f(0.75f, 0.25f, 0.5f);
            glVertex2f(128, 384);
            glColor3f(0.5f, 0.75f, 0.5f);
            glVertex2f(128, 448);
            glColor3f(0.25f, 0.5f, 0.75f);
            glVertex2f(196, 448);
            glColor3f(0.5f, 0.25f, 0.75f);
            glVertex2f(196, 384); 
        glEnd();
        glPopMatrix(); // Needed with glPushMatrix()
    }
    
        public static void drawSquares3d() {
        glPushMatrix(); // Very important to prevent inter-drawing contamination
        long t = System.currentTimeMillis();
        glTranslatef(((float)(Math.sin(Math.toRadians((t / 7) % 360)) * 400)), 
                ((float)(Math.sin(Math.toRadians((t / 13) % 360)) * 320)),
                -512 - ((float)(Math.sin(Math.toRadians((t / 17) % 360)) * 256)));
        glRotatef((float)((360 - t / 10) % 360), 0, 0, 1);
        glBegin(GL_QUADS);
            glColor3f(0.5f, 0.25f, 0.75f);
            glVertex3f(-16,  16, -5);
            glVertex3f(16,   16, -5);
            glVertex3f(16,  -16, -5);
            glVertex3f(-16, -16, -5);
            
            glColor3f(0.25f, 0.75f, 0.5f);
            glVertex3f(50,  100, 0);
            glVertex3f(100, 100, 0);
            glVertex3f(100, 150, 0);
            glVertex3f(50,  150, 0);
            
            glColor3f(0.75f, 0.25f, 0.5f);
            glVertex3f(250, 200, 0);
            glVertex3f(300, 200, 0);
            glVertex3f(300, 250, 0);
            glVertex3f(250, 250, 0); 
            
            glColor3f(0.75f, 0.25f, 0.5f);
            glVertex3f(128, 384, 0);
            glColor3f(0.5f, 0.75f, 0.5f);
            glVertex3f(128, 448, 0);
            glColor3f(0.25f, 0.5f, 0.75f);
            glVertex3f(196, 448, 0);
            glColor3f(0.5f, 0.25f, 0.75f);
            glVertex3f(196, 384, 0); 
        glEnd();
        glPopMatrix(); // Needed with glPushMatrix()
    }
    
        
    public static void orangeSquare() {
            glPushMatrix();
            glColor3f(0.75f, 0.5f, 0.1f);
            glTranslatef(0f, 0f, -10f);
            glRotatef((float) tmp, 3, 3, 1);
            glBegin(GL_QUADS);
                glVertex3f(-1, -1, -1);
                glVertex3f(-1, 1, -1);
                glVertex3f(1, 1, -1);
                glVertex3f(1, -1, -1);
            glEnd();
            glPopMatrix();
            tmp += 3;
            tmp %= 360;
    }
    
    
    public static void cube() {
        glPushMatrix();
        //glTranslatef(0f, 0f, -10f + (float)(5 * Math.sin(Math.toRadians((double) tmp))));
        glTranslatef(0f, 3f, 0f);
        glRotatef((float) tmp, 1, 1, 0);
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
        tmp += 3;
        tmp %= 360;
    }
        
     
    //Called during init();
    public static void makeVBO() {
        glLoadIdentity();
        numVerts = 3;
        vertSize = 3;
        colorSize = 4;
        numSides = 24;
        
        vertData = BufferUtils.createFloatBuffer(numVerts * vertSize * numSides);
        vertData.put(new float[]  {//Base
                                   -3f, -1f, -3f, 
                                   -3f,  3f, -3f,
                                    3f, -1f, -3f,
                                    
                                   -3f,  3f, -3f,
                                    3f,  3f, -3f,
                                    3f, -1f, -3f,
        
                                   -3f, -1f,  3f, 
                                   -3f,  3f,  3f,
                                    3f, -1f,  3f,
                                    
                                   -3f,  3f,  3f,
                                    3f,  3f,  3f,
                                    3f, -1f,  3f,
        
                                   -3f, -1f, -3f,
                                   -3f,  3f, -3f,
                                   -3f, -1f,  3f,
        
                                   -3f,  3f, -3f,
                                   -3f,  3f,  3f,
                                   -3f, -1f,  3f,
        
                                    3f, -1f, -3f,
                                    3f,  3f, -3f,
                                    3f, -1f,  3f,
        
                                    3f,  3f, -3f,
                                    3f,  3f,  3f,
                                    3f, -1f,  3f,
                                   
                                    3f,  3f,  3f,
                                   -3f,  3f,  3f,
                                   -3f,  3f, -3f,
        
                                   -3f,  3f, -3f,
                                    3f,  3f, -3f,
                                    3f,  3f,  3f,
                                   
                                    3f, -1f,  3f,
                                   -3f, -1f,  3f,
                                   -3f, -1f, -3f,
        
                                   -3f, -1f, -3f,
                                    3f, -1f, -3f,
                                    3f, -1f,  3f,
        
                                   //Tower
                                   -2f,  3f, -2f, 
                                   -2f, 28f, -2f,
                                    2f,  3f, -2f,
                                    
                                   -2f, 28f, -2f,
                                    2f, 28f, -2f,
                                    2f,  3f, -2f,
        
                                   -2f,  3f,  2f, 
                                   -2f, 28f,  2f,
                                    2f,  3f,  2f,
                                    
                                   -2f, 28f,  2f,
                                    2f, 28f,  2f,
                                    2f,  3f,  2f,
        
                                   -2f,  3f, -2f,
                                   -2f, 28f, -2f,
                                   -2f,  3f,  2f,
        
                                   -2f, 28f, -2f,
                                   -2f, 28f,  2f,
                                   -2f,  3f,  2f,
        
                                    2f,  3f, -2f,
                                    2f, 28f, -2f,
                                    2f,  3f,  2f,
        
                                    2f, 28f, -2f,
                                    2f, 28f,  2f,
                                    2f,  3f,  2f,
        
                                   //Top
                                   -2f, 28f, -2f,
                                    0f, 36f,  0f,
                                    2f, 28f, -2f,
                                    
                                   -2f, 28f,  2f,
                                    0f, 36f,  0f,
                                   -2f, 28f, -2f,
                                    
                                   -2f, 28f,  2f,
                                    0f, 36f,  0f,
                                    2f, 28f,  2f,
                                    
                                    2f, 28f,  2f,
                                    0f, 36f,  0f,
                                    2f, 28f, -2f});        
        vertData.flip();        
        
        colorData = BufferUtils.createFloatBuffer(numVerts * colorSize * numSides);
        colorData.put(new float[]  {//Base
                                    0.5f, 0.5f, 0.6f, 1.0f,
                                    0.5f, 0.5f, 0.6f, 1.0f, 
                                    0.5f, 0.5f, 0.6f, 1.0f, 
                                    
                                    0.5f, 0.5f, 0.6f, 1.0f, 
                                    0.5f, 0.5f, 0.6f, 1.0f, 
                                    0.5f, 0.5f, 0.6f, 1.0f,
                                    
                                    0.5f, 0.5f, 0.6f, 1.0f, 
                                    0.5f, 0.5f, 0.6f, 1.0f, 
                                    0.5f, 0.5f, 0.6f, 1.0f,
                                    
                                    0.5f, 0.5f, 0.6f, 1.0f, 
                                    0.5f, 0.5f, 0.6f, 1.0f, 
                                    0.5f, 0.5f, 0.6f, 1.0f,
                                    
                                    0.5f, 0.5f, 0.6f, 1.0f, 
                                    0.5f, 0.5f, 0.6f, 1.0f, 
                                    0.5f, 0.5f, 0.6f, 1.0f,
                                    
                                    0.5f, 0.5f, 0.6f, 1.0f, 
                                    0.5f, 0.5f, 0.6f, 1.0f, 
                                    0.5f, 0.5f, 0.6f, 1.0f,
                                    
                                    0.5f, 0.5f, 0.6f, 1.0f, 
                                    0.5f, 0.5f, 0.6f, 1.0f, 
                                    0.5f, 0.5f, 0.6f, 1.0f,
                                    
                                    0.5f, 0.5f, 0.6f, 1.0f, 
                                    0.5f, 0.5f, 0.6f, 1.0f, 
                                    0.5f, 0.5f, 0.6f, 1.0f,
                                    
                                    0.5f, 0.5f, 0.6f, 1.0f, 
                                    0.5f, 0.5f, 0.6f, 1.0f, 
                                    0.5f, 0.5f, 0.6f, 1.0f,
                                    
                                    0.5f, 0.5f, 0.6f, 1.0f, 
                                    0.5f, 0.5f, 0.6f, 1.0f, 
                                    0.5f, 0.5f, 0.6f, 1.0f,
                                    
                                    0.5f, 0.5f, 0.6f, 1.0f, 
                                    0.5f, 0.5f, 0.6f, 1.0f, 
                                    0.5f, 0.5f, 0.6f, 1.0f,
                                    
                                    0.5f, 0.5f, 0.6f, 1.0f, 
                                    0.5f, 0.5f, 0.6f, 1.0f, 
                                    0.5f, 0.5f, 0.6f, 1.0f,
        
                                   //Tower                                    
                                    0.55f, 0.55f, 0.6f, 1.0f, 
                                    0.55f, 0.55f, 0.6f, 1.0f, 
                                    0.55f, 0.55f, 0.6f, 1.0f,
                                                                       
                                    0.55f, 0.55f, 0.6f, 1.0f, 
                                    0.55f, 0.55f, 0.6f, 1.0f, 
                                    0.55f, 0.55f, 0.6f, 1.0f,
                                                                       
                                    0.55f, 0.55f, 0.6f, 1.0f, 
                                    0.55f, 0.55f, 0.6f, 1.0f, 
                                    0.55f, 0.55f, 0.6f, 1.0f,
                                                                       
                                    0.55f, 0.55f, 0.6f, 1.0f, 
                                    0.55f, 0.55f, 0.6f, 1.0f, 
                                    0.55f, 0.55f, 0.6f, 1.0f,
                                                                       
                                    0.55f, 0.55f, 0.6f, 1.0f, 
                                    0.55f, 0.55f, 0.6f, 1.0f, 
                                    0.55f, 0.55f, 0.6f, 1.0f,
                                                                       
                                    0.55f, 0.55f, 0.6f, 1.0f, 
                                    0.55f, 0.55f, 0.6f, 1.0f, 
                                    0.55f, 0.55f, 0.6f, 1.0f,
                                                                       
                                    0.55f, 0.55f, 0.6f, 1.0f, 
                                    0.55f, 0.55f, 0.6f, 1.0f, 
                                    0.55f, 0.55f, 0.6f, 1.0f,
                                                                       
                                    0.55f, 0.55f, 0.6f, 1.0f, 
                                    0.55f, 0.55f, 0.6f, 1.0f, 
                                    0.55f, 0.55f, 0.6f, 1.0f,
        
                                   //Top
                                    0.6f, 0.6f, 0.8f, 1.0f, 
                                    0.6f, 0.6f, 0.8f, 1.0f,
                                    0.6f, 0.6f, 0.8f, 1.0f,
                                    
                                    0.6f, 0.6f, 0.8f, 1.0f, 
                                    0.6f, 0.6f, 0.8f, 1.0f,
                                    0.6f, 0.6f, 0.8f, 1.0f,
                                    
                                    0.6f, 0.6f, 0.8f, 1.0f, 
                                    0.6f, 0.6f, 0.8f, 1.0f,
                                    0.6f, 0.6f, 0.8f, 1.0f,
                                    
                                    0.6f, 0.6f, 0.8f, 1.0f, 
                                    0.6f, 0.6f, 0.8f, 1.0f,
                                    0.6f, 0.6f, 0.8f, 1.0f});
        colorData.flip();
        
        vboidv = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboidv);
        glBufferData(GL_ARRAY_BUFFER, vertData, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        
        vboidc = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboidc);
        glBufferData(GL_ARRAY_BUFFER, colorData, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        
        numTowers = (int) ((Math.random() * 4) + (Math.random() * 4) + (Math.random() * 4));
        vbolocs = new float[numTowers][3];
        for(int i = 0; i < numTowers; i++){
            vbolocs[i][0] = (((float)(Math.random()) * ((48 * i) +384) / 2) - ((96 + (12 * i))));
            vbolocs[i][2] = (((float)(Math.random()) * ((48 * i) +384) / 2) - ((96 + (12 * i)))); 
        }
        
        vboidp = glGenBuffers();
        peepsData = BufferUtils.createFloatBuffer(252);
        peepsData.put(new float[]  {-0.10f, 0.01f, -0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                    -0.10f, 0.60f, -0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                     0.10f, 0.01f, -0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                     
                                    -0.10f, 0.60f, -0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                     0.10f, 0.60f, -0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                     0.10f, 0.01f, -0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
        
                                    -0.10f, 0.01f,  0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                    -0.10f, 0.60f,  0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                     0.10f, 0.01f,  0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                     
                                    -0.10f, 0.60f,  0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                     0.10f, 0.60f,  0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                     0.10f, 0.01f,  0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
        
                                    -0.10f, 0.01f, -0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                    -0.10f, 0.60f, -0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                    -0.10f, 0.01f,  0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
        
                                    -0.10f, 0.60f, -0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                    -0.10f, 0.60f,  0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                    -0.10f, 0.01f,  0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
        
                                     0.10f, 0.01f, -0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                     0.10f, 0.60f, -0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                     0.10f, 0.01f,  0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
        
                                     0.10f, 0.60f, -0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                     0.10f, 0.60f,  0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                     0.10f, 0.01f,  0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
        
                                    -0.10f, 0.60f, -0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                    -0.10f, 0.60f,  0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                     0.10f, 0.60f,  0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
        
                                     0.10f, 0.60f,  0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                     0.10f, 0.60f, -0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                    -0.10f, 0.60f, -0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
        
                                    -0.10f, 0.01f, -0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                    -0.10f, 0.01f,  0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                     0.10f, 0.01f,  0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
        
                                     0.10f, 0.01f,  0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                     0.10f, 0.01f, -0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                    -0.10f, 0.01f, -0.06f, 1.0f, 1.0f, 1.0f, 1.0f});
        peepsData.flip();
        
        glBindBuffer(GL_ARRAY_BUFFER, vboidp);
        glBufferData(GL_ARRAY_BUFFER, peepsData, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        
        numPeeps = (int) ((Math.random() * 6) + (Math.random() * 6) + (Math.random() * 6)) + 30;
        peeplocs = new float[numPeeps][12][3];
        for(int j = 0; j < 12; j++) {
            for(int i = 0; i < numPeeps; i++){
                peeplocs[i][j][0] = (((float)(Math.random()) * i * 8) - (i * 4));
                peeplocs[i][j][2] = (((float)(Math.random()) * i * 8) - (i * 4));
            }
        } 
    }
    
    // Called in loop();
    public static void drawVBO() {
        for(int i = 0; i < numTowers; i++) {
            glPushMatrix();                 
                glTranslatef(vbolocs[i][0], 0, vbolocs[i][2]);
                glEnableClientState(GL_VERTEX_ARRAY);
                glBindBuffer(GL_ARRAY_BUFFER, vboidv);
                glVertexPointer(vertSize, GL_FLOAT, 0, 0L);

                glEnableClientState(GL_COLOR_ARRAY);
                glBindBuffer(GL_ARRAY_BUFFER, vboidc);
                glColorPointer(colorSize, GL_FLOAT, 0, 0L);

                glDrawArrays(GL_TRIANGLES, 0, numVerts * numSides);
                glDisableClientState(GL_COLOR_ARRAY);
                glDisableClientState(GL_VERTEX_ARRAY);
            glPopMatrix();
        }
//        for(int j = 0; j < 12; j++) {
//            for(int i = 0; i < numPeeps; i++) {
//                glPushMatrix();                 
//                    glTranslatef(peeplocs[i][j][0], -1, peeplocs[i][j][2]);
//                    glEnableClientState(GL_VERTEX_ARRAY);
//                    glBindBuffer(GL_ARRAY_BUFFER, vboidp);
//                    glVertexPointer(vertSize, GL_FLOAT, 28, 0L);
//
//                    glEnableClientState(GL_COLOR_ARRAY);
//                    glBindBuffer(GL_ARRAY_BUFFER, vboidp);
//                    glColorPointer(colorSize, GL_FLOAT, 28, 12L);
//
//                    glDrawArrays(GL_TRIANGLES, 0, 36);
//                    glDisableClientState(GL_COLOR_ARRAY);
//                    glDisableClientState(GL_VERTEX_ARRAY);
//                glPopMatrix();
//            }
//        }
    }
    
    
    //Called in cleanUp();
    public static void cleanVBO() {
        glDeleteBuffers(vboidv);
        glDeleteBuffers(vboidc);
    }
    
    
    // Draw a flat green landscape in immediate mode.
    public static void drawFlatLand() {
        glDisable(GL_DEPTH_TEST);
        glPushMatrix();
        glTranslatef(0f, -1f, 0f);
        glRotatef(0f, 0f, 0f, 0f);
        glColor3f(0.2f, 0.8f, 0.3f);
        glBegin(GL_TRIANGLES);
            glVertex3f(CityMap.minX, 0f, CityMap.minY);
            glVertex3f(CityMap.minX, 0f, CityMap.maxY);
            glVertex3f(CityMap.maxX, 0f, CityMap.maxY);            

            glVertex3f(CityMap.maxX, 0f, CityMap.maxY);
            glVertex3f(CityMap.maxX, 0f, CityMap.minY); 
            glVertex3f(CityMap.minX, 0f, CityMap.minY);            
        glEnd();
        glPopMatrix();
        glEnable(GL_DEPTH_TEST);
    }
    
    
}
