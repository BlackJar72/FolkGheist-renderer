//Copyright (C) Jared Blackburn, Sep 21, 2013
package community.ui.test;

import java.util.ArrayList;
import community.simulation.map.MapLocale;
import community.simulation.citizens.Citizen;
import community.simulation.locales.buildings.*;
import community.simulation.locales.Locale;
import community.simulation.Simulation;
import community.simulation.map.Location;
import community.ui.models.Mesh;
import org.lwjgl.opengl.GL11.*;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Collections;
import org.lwjgl.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */
public class TestingStuff {
    private static final int numModels = 9;
    //private static int[] modelIDs = new int[numModels];
    private static IntBuffer modelIDs;
    //private static int peepMID;
    private static FloatBuffer[] buildBuffer = new FloatBuffer[numModels];
    private static Simulation simulation;
    public static ArrayList<Location> tmpTiles = new ArrayList<Location>();
    
    
    public static void testingInit(Simulation s) {
        simulation = s;
        fillTmpTiles();
        modelIDs = BufferUtils.createIntBuffer(numModels);
        glGenBuffers(modelIDs);
        makePeep();
        makeGenHouse();
        makeGenEmployer();
        makeGenRec();
        makeDebugBlock();
        int j = 0;
        for(int i = 0; i < 64; i++) simulation.places.add((Locale) 
                (new GenericHome(simulation, tmpTiles.get(j++))));
//        for(int i = 0; i <  8; i++) simulation.places.add((Locale) 
//                (new GenericEmployer(simulation, tmpTiles.get(j++))));
//        for(int i = 0; i <  8; i++) simulation.places.add((Locale) 
//                (new GenericRec(simulation, tmpTiles.get(j++))));
//        for(int i = 0; i < 256; i++) simulation.people.add(new Citizen(s));
//           simulation.people.add(new Citizen(s));
        for(int i = 0; i <  4; i++) simulation.places.add((Locale) 
                (new DumbSkyscraper1(simulation, tmpTiles.get(j++))));
        ioTests();
    }
    
    
    public static void testingRender() {
        //Iterator it = simulation.places.iterator();
        for(Locale loc: simulation.places) loc.draw();
    }
    
    
    public static void testingCleanUp() {
        //glDeleteBuffers(peepMID);
        for(int i = 0; i < numModels; i++) glDeleteBuffers(modelIDs.get(i));
    }
    
    
    private static void ioTests() {
//        Model model = OBJLoader.loadModel("res/Building1.obj");
//        TextureLoader.loadImage("res/Building11.jpg", glGenTextures());
    }  
    
    
    public static void fillTmpTiles() {
        for(int i = 8; i >= -8; i--) {
            for(int j = 8; j >= -8; j--)
                tmpTiles.add(new Location((float)(i * 8), 0.0f, 
                        (float)(j * 8), ((int)(Math.random() * 4)) * 90f));
        }
        Collections.shuffle(tmpTiles);
    }  
    
    
    
    /*************************************
     *            SPECIFIC               *
     *************************************/
    
    
    private static void makePeep() {
        glLoadIdentity();
        buildBuffer[0] = BufferUtils.createFloatBuffer(12 * 3 * 7);
        buildBuffer[0].put(new float[]{-0.10f, 0.01f, -0.06f, 0.8f, 0.8f, 0.8f, 1.0f,
                                    -0.10f, 0.60f, -0.06f, 0.8f, 0.8f, 0.8f, 1.0f,
                                     0.10f, 0.01f, -0.06f, 0.8f, 0.8f, 0.8f, 1.0f,
                                     
                                    -0.10f, 0.60f, -0.06f, 0.8f, 0.8f, 0.8f, 1.0f,
                                     0.10f, 0.60f, -0.06f, 0.8f, 0.8f, 0.8f, 1.0f,
                                     0.10f, 0.01f, -0.06f, 0.8f, 0.8f, 0.8f, 1.0f,
        
                                    -0.10f, 0.01f,  0.06f, 0.8f, 0.8f, 0.8f, 1.0f,
                                    -0.10f, 0.60f,  0.06f, 0.5f, 0.7f, 1.0f, 1.0f,
                                     0.10f, 0.01f,  0.06f, 0.8f, 0.8f, 0.8f, 1.0f,
                                     
                                    -0.10f, 0.60f,  0.06f, 0.5f, 0.7f, 1.0f, 1.0f,
                                     0.10f, 0.60f,  0.06f, 1.0f, 0.7f, 0.5f, 1.0f,
                                     0.10f, 0.01f,  0.06f, 0.8f, 0.8f, 0.8f, 1.0f,
        
                                    -0.10f, 0.01f, -0.06f, 0.8f, 0.8f, 0.8f, 1.0f,
                                    -0.10f, 0.60f, -0.06f, 0.8f, 0.8f, 0.8f, 1.0f,
                                    -0.10f, 0.01f,  0.06f, 0.8f, 0.8f, 0.8f, 1.0f,
        
                                    -0.10f, 0.60f, -0.06f, 0.8f, 0.8f, 0.8f, 1.0f,
                                    -0.10f, 0.60f,  0.06f, 0.5f, 0.7f, 1.0f, 1.0f,
                                    -0.10f, 0.01f,  0.06f, 0.8f, 0.8f, 0.8f, 1.0f,
        
                                     0.10f, 0.01f, -0.06f, 0.8f, 0.8f, 0.8f, 1.0f,
                                     0.10f, 0.60f, -0.06f, 0.8f, 0.8f, 0.8f, 1.0f,
                                     0.10f, 0.01f,  0.06f, 0.8f, 0.8f, 0.8f, 1.0f,
        
                                     0.10f, 0.60f, -0.06f, 0.8f, 0.8f, 0.8f, 1.0f,
                                     0.10f, 0.60f,  0.06f, 1.0f, 0.7f, 0.5f, 1.0f,
                                     0.10f, 0.01f,  0.06f, 0.8f, 0.8f, 0.8f, 1.0f,
        
                                    -0.10f, 0.60f, -0.06f, 0.8f, 0.8f, 0.8f, 1.0f,
                                    -0.10f, 0.60f,  0.06f, 0.5f, 0.7f, 1.0f, 1.0f,
                                     0.10f, 0.60f,  0.06f, 1.0f, 0.7f, 0.5f, 1.0f,
        
                                     0.10f, 0.60f,  0.06f, 1.0f, 0.7f, 0.5f, 1.0f,
                                     0.10f, 0.60f, -0.06f, 0.8f, 0.8f, 0.8f, 1.0f,
                                    -0.10f, 0.60f, -0.06f, 0.8f, 0.8f, 0.8f, 1.0f,
        
                                    -0.10f, 0.01f, -0.06f, 0.8f, 0.8f, 0.8f, 1.0f,
                                    -0.10f, 0.01f,  0.06f, 0.8f, 0.8f, 0.8f, 1.0f,
                                     0.10f, 0.01f,  0.06f, 0.8f, 0.8f, 0.8f, 1.0f,
        
                                     0.10f, 0.01f,  0.06f, 0.8f, 0.8f, 0.8f, 1.0f,
                                     0.10f, 0.01f, -0.06f, 0.8f, 0.8f, 0.8f, 1.0f,
                                    -0.10f, 0.01f, -0.06f, 0.8f, 0.8f, 0.8f, 1.0f,});
        buildBuffer[0].flip();
        
        glBindBuffer(GL_ARRAY_BUFFER, modelIDs.get(0));
        glBufferData(GL_ARRAY_BUFFER, buildBuffer[0], GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        
        glLoadIdentity();
        buildBuffer[1] = BufferUtils.createFloatBuffer(12 * 3 * 7);
        buildBuffer[1].put(new float[]{-0.10f, 0.01f, -0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                    -0.10f, 0.60f, -0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                     0.10f, 0.01f, -0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                     
                                    -0.10f, 0.60f, -0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                     0.10f, 0.60f, -0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                     0.10f, 0.01f, -0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
        
                                    -0.10f, 0.01f,  0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                    -0.10f, 0.60f,  0.06f, 0.5f, 0.7f, 1.0f, 1.0f,
                                     0.10f, 0.01f,  0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                     
                                    -0.10f, 0.60f,  0.06f, 0.5f, 0.7f, 1.0f, 1.0f,
                                     0.10f, 0.60f,  0.06f, 1.0f, 0.7f, 0.5f, 1.0f,
                                     0.10f, 0.01f,  0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
        
                                    -0.10f, 0.01f, -0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                    -0.10f, 0.60f, -0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                    -0.10f, 0.01f,  0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
        
                                    -0.10f, 0.60f, -0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                    -0.10f, 0.60f,  0.06f, 0.5f, 0.7f, 1.0f, 1.0f,
                                    -0.10f, 0.01f,  0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
        
                                     0.10f, 0.01f, -0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                     0.10f, 0.60f, -0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                     0.10f, 0.01f,  0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
        
                                     0.10f, 0.60f, -0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                     0.10f, 0.60f,  0.06f, 1.0f, 0.7f, 0.5f, 1.0f,
                                     0.10f, 0.01f,  0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
        
                                    -0.10f, 0.60f, -0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                    -0.10f, 0.60f,  0.06f, 0.5f, 0.7f, 1.0f, 1.0f,
                                     0.10f, 0.60f,  0.06f, 1.0f, 0.7f, 0.5f, 1.0f,
        
                                     0.10f, 0.60f,  0.06f, 1.0f, 0.7f, 0.5f, 1.0f,
                                     0.10f, 0.60f, -0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                    -0.10f, 0.60f, -0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
        
                                    -0.10f, 0.01f, -0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                    -0.10f, 0.01f,  0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                     0.10f, 0.01f,  0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
        
                                     0.10f, 0.01f,  0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                     0.10f, 0.01f, -0.06f, 1.0f, 1.0f, 1.0f, 1.0f,
                                    -0.10f, 0.01f, -0.06f, 1.0f, 1.0f, 1.0f, 1.0f});
        buildBuffer[1].flip();
        
        glBindBuffer(GL_ARRAY_BUFFER, modelIDs.get(1));
        glBufferData(GL_ARRAY_BUFFER, buildBuffer[1], GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 1);
    }
    
    
    public static void drawPeep(Location loc) {
        glPushMatrix();                 
            glTranslatef(loc.getXfloat(), loc.getYfloat() -1f, loc.getZfloat());
            glRotatef(loc.getRotate(), 0, 1, 0);
            glEnableClientState(GL_VERTEX_ARRAY);
            glBindBuffer(GL_ARRAY_BUFFER, modelIDs.get(0));
            glVertexPointer(3, GL_FLOAT, 28, 0L);

            glEnableClientState(GL_COLOR_ARRAY);
            glBindBuffer(GL_ARRAY_BUFFER, modelIDs.get(0));
            glColorPointer(4, GL_FLOAT, 28, 12L);

            glDrawArrays(GL_TRIANGLES, 0, 36);
            glDisableClientState(GL_COLOR_ARRAY);
            glDisableClientState(GL_VERTEX_ARRAY);
        glPopMatrix();
    }

    
    private static void makeGenHouse() {
        glLoadIdentity();
        buildBuffer[2] = BufferUtils.createFloatBuffer(24 * 7);
        buildBuffer[2].put(new float[]    {-2.5f,    0f, -1.5f, 0.45f, 0.20f, 0.05f, 1.0f,
                                        -2.5f, 1.75f, -1.5f, 0.45f, 0.20f, 0.05f, 1.0f,
                                         2.5f, 1.75f, -1.5f, 0.45f, 0.20f, 0.05f, 1.0f,
                                         2.5f,    0f, -1.5f, 0.45f, 0.20f, 0.05f, 1.0f,
        
                                        -2.5f,    0f,  1.5f, 0.45f, 0.20f, 0.05f, 1.0f, 
                                        -2.5f, 1.75f,  1.5f, 0.45f, 0.20f, 0.05f, 1.0f,
                                         2.5f, 1.75f,  1.5f, 0.45f, 0.20f, 0.05f, 1.0f,
                                         2.5f,    0f,  1.5f, 0.45f, 0.20f, 0.05f, 1.0f,
        
                                        -2.5f,    0f, -1.5f, 0.45f, 0.20f, 0.05f, 1.0f,
                                        -2.5f, 1.75f, -1.5f, 0.45f, 0.20f, 0.05f, 1.0f,
                                        -2.5f, 1.75f,  1.5f, 0.45f, 0.20f, 0.05f, 1.0f,
                                        -2.5f,    0f,  1.5f, 0.45f, 0.20f, 0.05f, 1.0f,
        
                                         2.5f,    0f, -1.5f, 0.45f, 0.20f, 0.05f, 1.0f,
                                         2.5f, 1.75f, -1.5f, 0.45f, 0.20f, 0.05f, 1.0f,
                                         2.5f, 1.75f,  1.5f, 0.45f, 0.20f, 0.05f, 1.0f,
                                         2.5f,    0f,  1.5f, 0.45f, 0.20f, 0.05f, 1.0f,
        
                                        -2.5f, 1.75f, -1.5f, 0.35f, 0.30f, 0.35f, 1.0f,
                                        -2.5f, 1.75f,  1.5f, 0.35f, 0.30f, 0.35f, 1.0f,
                                         2.5f, 1.75f,  1.5f, 0.35f, 0.30f, 0.35f, 1.0f,
                                         2.5f, 1.75f, -1.5f, 0.35f, 0.30f, 0.35f, 1.0f,
        
                                        -2.5f, 0.01f, -1.5f, 0.45f, 0.50f, 0.85f, 1.0f,
                                        -2.5f, 0.01f,  1.5f, 0.45f, 0.50f, 0.85f, 1.0f,
                                         2.5f, 0.01f,  1.5f, 0.45f, 0.50f, 0.85f, 1.0f,
                                         2.5f, 0.01f, -1.5f, 0.45f, 0.50f, 0.85f, 1.0f});
        buildBuffer[2].flip();
        glBindBuffer(GL_ARRAY_BUFFER, modelIDs.get(2));
        glBufferData(GL_ARRAY_BUFFER, buildBuffer[2], GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        
        glLoadIdentity();
        buildBuffer[3] = BufferUtils.createFloatBuffer(24 * 7);
        buildBuffer[3].put(new float[]    {-2.5f,    0f, -1.5f, 0.65f, 0.40f, 0.25f, 1.0f,
                                        -2.5f, 1.75f, -1.5f, 0.65f, 0.40f, 0.25f, 1.0f,
                                         2.5f, 1.75f, -1.5f, 0.65f, 0.40f, 0.25f, 1.0f,
                                         2.5f,    0f, -1.5f, 0.65f, 0.40f, 0.25f, 1.0f,
        
                                        -2.5f,    0f,  1.5f, 0.65f, 0.40f, 0.25f, 1.0f, 
                                        -2.5f, 1.75f,  1.5f, 0.65f, 0.40f, 0.25f, 1.0f,
                                         2.5f, 1.75f,  1.5f, 0.65f, 0.40f, 0.25f, 1.0f,
                                         2.5f,    0f,  1.5f, 0.65f, 0.40f, 0.25f, 1.0f,
        
                                        -2.5f,    0f, -1.5f, 0.65f, 0.40f, 0.25f, 1.0f,
                                        -2.5f, 1.75f, -1.5f, 0.65f, 0.40f, 0.25f, 1.0f,
                                        -2.5f, 1.75f,  1.5f, 0.65f, 0.40f, 0.25f, 1.0f,
                                        -2.5f,    0f,  1.5f, 0.65f, 0.40f, 0.25f, 1.0f,
        
                                         2.5f,    0f, -1.5f, 0.65f, 0.40f, 0.25f, 1.0f,
                                         2.5f, 1.75f, -1.5f, 0.65f, 0.40f, 0.25f, 1.0f,
                                         2.5f, 1.75f,  1.5f, 0.65f, 0.40f, 0.25f, 1.0f,
                                         2.5f,    0f,  1.5f, 0.65f, 0.40f, 0.25f, 1.0f,
        
                                        -2.5f, 1.75f, -1.5f, 0.55f, 0.50f, 0.55f, 1.0f,
                                        -2.5f, 1.75f,  1.5f, 0.55f, 0.50f, 0.55f, 1.0f,
                                         2.5f, 1.75f,  1.5f, 0.55f, 0.50f, 0.55f, 1.0f,
                                         2.5f, 1.75f, -1.5f, 0.55f, 0.50f, 0.55f, 1.0f,
        
                                        -2.5f, 0.01f, -1.5f, 0.65f, 0.70f, 1.00f, 1.0f,
                                        -2.5f, 0.01f,  1.5f, 0.65f, 0.70f, 1.00f, 1.0f,
                                         2.5f, 0.01f,  1.5f, 0.65f, 0.70f, 1.00f, 1.0f,
                                         2.5f, 0.01f, -1.5f, 0.65f, 0.70f, 1.00f, 1.0f});
        buildBuffer[3].flip();
        glBindBuffer(GL_ARRAY_BUFFER, modelIDs.get(3));
        glBufferData(GL_ARRAY_BUFFER, buildBuffer[3], GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    
    }
    
    
    public static void drawGenHouse(MapLocale loc) {
        int index = 2;
        if(loc.isSelected()) index++;
        glPushMatrix();
            //glTranslatef(loc.getXfloat(), loc.getYfloat(), loc.getZfloat());
            glTranslatef(loc.getXfloat(), loc.getYfloat() -1, loc.getZfloat());
            glRotatef(loc.getRotate(), 0, 1, 0);
            glScalef(1f, 0.5f, 1f);
            
            glEnableClientState(GL_VERTEX_ARRAY);
            glBindBuffer(GL_ARRAY_BUFFER, modelIDs.get(index));
            glVertexPointer(3, GL_FLOAT, 28, 0L);

            glEnableClientState(GL_COLOR_ARRAY);
            glBindBuffer(GL_ARRAY_BUFFER, modelIDs.get(index));
            glColorPointer(4, GL_FLOAT, 28, 12L);

            glDrawArrays(GL_QUADS, 0, 24);
            glDisableClientState(GL_COLOR_ARRAY);
            glDisableClientState(GL_VERTEX_ARRAY);            
        glPopMatrix();
//        glPushMatrix();    
//            glBegin(GL_QUADS);
//                    glColor4f(0.85f, 0.95f, 0.85f, 0.5f);   
//                    glVertex3f(loc.getCorner(3).getXfloat(),  0.11f, loc.getCorner(3).getZfloat());                 
//                    glVertex3f(loc.getCorner(2).getXfloat(),  0.11f, loc.getCorner(2).getZfloat());                 
//                    glVertex3f(loc.getCorner(1).getXfloat(),  0.11f, loc.getCorner(1).getZfloat());                 
//                    glVertex3f(loc.getCorner(0).getXfloat(),  0.11f, loc.getCorner(0).getZfloat());
//                    
//                    glVertex3f(loc.footprint.x + loc.footprint.width,   0.11f, loc.footprint.y - loc.footprint.height);                 
//                    glVertex3f(loc.footprint.x,                         0.11f, loc.footprint.y - loc.footprint.height);                 
//                    glVertex3f(loc.footprint.x,                         0.11f, loc.footprint.y);                 
//                    glVertex3f(loc.footprint.x + loc.footprint.width ,  0.11f, loc.footprint.y);
//            glEnd();            
//        glPopMatrix();
    }
    

    private static void makeGenEmployer() {
        glLoadIdentity();
        buildBuffer[4] = BufferUtils.createFloatBuffer(24 * 7);
        buildBuffer[4].put(new float[] {-3.5f,    0f, -3.5f, 0.40f, 0.45f, 0.40f, 1.0f,
                                        -3.5f, 2.15f, -3.5f, 0.40f, 0.45f, 0.40f, 1.0f,
                                         3.5f, 2.15f, -3.5f, 0.40f, 0.45f, 0.40f, 1.0f,
                                         3.5f,    0f, -3.5f, 0.40f, 0.45f, 0.40f, 1.0f,
        
                                        -3.5f,    0f,  3.5f, 0.40f, 0.45f, 0.40f, 1.0f,
                                        -3.5f, 2.15f,  3.5f, 0.40f, 0.45f, 0.40f, 1.0f,
                                         3.5f, 2.15f,  3.5f, 0.40f, 0.45f, 0.40f, 1.0f,
                                         3.5f,    0f,  3.5f, 0.40f, 0.45f, 0.40f, 1.0f,
        
                                        -3.5f,    0f, -3.5f, 0.40f, 0.45f, 0.40f, 1.0f,
                                        -3.5f, 2.15f, -3.5f, 0.40f, 0.45f, 0.40f, 1.0f,
                                        -3.5f, 2.15f,  3.5f, 0.40f, 0.45f, 0.40f, 1.0f,
                                        -3.5f,    0f,  3.5f, 0.40f, 0.45f, 0.40f, 1.0f,
        
                                         3.5f,    0f, -3.5f, 0.40f, 0.45f, 0.40f, 1.0f,
                                         3.5f, 2.15f, -3.5f, 0.40f, 0.45f, 0.40f, 1.0f,
                                         3.5f, 2.15f,  3.5f, 0.40f, 0.45f, 0.40f, 1.0f,
                                         3.5f,    0f,  3.5f, 0.40f, 0.45f, 0.40f, 1.0f,
        
                                        -3.5f, 2.15f, -3.5f, 0.45f, 0.55f, 0.45f, 1.0f,
                                        -3.5f, 2.15f,  3.5f, 0.45f, 0.55f, 0.45f, 1.0f,
                                         3.5f, 2.15f,  3.5f, 0.45f, 0.55f, 0.45f, 1.0f,
                                         3.5f, 2.15f, -3.5f, 0.45f, 0.55f, 0.45f, 1.0f,
        
                                        -3.5f, 0.01f, -3.5f, 0.55f, 0.45f, 0.60f, 1.0f,
                                        -3.5f, 0.01f,  3.5f, 0.55f, 0.45f, 0.60f, 1.0f,
                                         3.5f, 0.01f,  3.5f, 0.55f, 0.45f, 0.60f, 1.0f,
                                         3.5f, 0.01f, -3.5f, 0.55f, 0.45f, 0.60f, 1.0f});
        buildBuffer[4].flip();
        glBindBuffer(GL_ARRAY_BUFFER, modelIDs.get(4));
        glBufferData(GL_ARRAY_BUFFER, buildBuffer[4], GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        
        glLoadIdentity();
        buildBuffer[5] = BufferUtils.createFloatBuffer(24 * 7);
        buildBuffer[5].put(new float[] {-3.5f,    0f, -3.5f, 0.60f, 0.65f, 0.60f, 1.0f,
                                        -3.5f, 2.15f, -3.5f, 0.60f, 0.65f, 0.60f, 1.0f,
                                         3.5f, 2.15f, -3.5f, 0.60f, 0.65f, 0.60f, 1.0f,
                                         3.5f,    0f, -3.5f, 0.60f, 0.65f, 0.60f, 1.0f,
        
                                        -3.5f,    0f,  3.5f, 0.60f, 0.65f, 0.60f, 1.0f,
                                        -3.5f, 2.15f,  3.5f, 0.60f, 0.65f, 0.60f, 1.0f,
                                         3.5f, 2.15f,  3.5f, 0.60f, 0.65f, 0.60f, 1.0f,
                                         3.5f,    0f,  3.5f, 0.60f, 0.65f, 0.60f, 1.0f,
        
                                        -3.5f,    0f, -3.5f, 0.60f, 0.65f, 0.60f, 1.0f,
                                        -3.5f, 2.15f, -3.5f, 0.60f, 0.65f, 0.60f, 1.0f,
                                        -3.5f, 2.15f,  3.5f, 0.60f, 0.65f, 0.60f, 1.0f,
                                        -3.5f,    0f,  3.5f, 0.60f, 0.65f, 0.60f, 1.0f,
        
                                         3.5f,    0f, -3.5f, 0.60f, 0.65f, 0.60f, 1.0f,
                                         3.5f, 2.15f, -3.5f, 0.60f, 0.65f, 0.60f, 1.0f,
                                         3.5f, 2.15f,  3.5f, 0.60f, 0.65f, 0.60f, 1.0f,
                                         3.5f,    0f,  3.5f, 0.60f, 0.65f, 0.60f, 1.0f,
        
                                        -3.5f, 2.15f, -3.5f, 0.65f, 0.75f, 0.65f, 1.0f,
                                        -3.5f, 2.15f,  3.5f, 0.65f, 0.75f, 0.65f, 1.0f,
                                         3.5f, 2.15f,  3.5f, 0.65f, 0.75f, 0.65f, 1.0f,
                                         3.5f, 2.15f, -3.5f, 0.65f, 0.75f, 0.65f, 1.0f,
        
                                        -3.5f, 0.01f, -3.5f, 0.75f, 0.65f, 0.80f, 1.0f,
                                        -3.5f, 0.01f,  3.5f, 0.75f, 0.65f, 0.80f, 1.0f,
                                         3.5f, 0.01f,  3.5f, 0.75f, 0.65f, 0.80f, 1.0f,
                                         3.5f, 0.01f, -3.5f, 0.75f, 0.65f, 0.80f, 1.0f});
        buildBuffer[5].flip();
        glBindBuffer(GL_ARRAY_BUFFER, modelIDs.get(5));
        glBufferData(GL_ARRAY_BUFFER, buildBuffer[5], GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }
    
    
    public static void drawGenEmployer(MapLocale loc) {
        int index = 4;
        if(loc.isSelected()) index++;
        glPushMatrix();
            //glTranslatef(loc.getXfloat(), loc.getYfloat(), loc.getZfloat());
            glTranslatef(loc.getXfloat(), loc.getYfloat() -1, loc.getZfloat());
            glRotatef(loc.getRotate(), 0, 1, 0);
            
            glEnableClientState(GL_VERTEX_ARRAY);
            glBindBuffer(GL_ARRAY_BUFFER, modelIDs.get(index));
            glVertexPointer(3, GL_FLOAT, 28, 0L);

            glEnableClientState(GL_COLOR_ARRAY);
            glBindBuffer(GL_ARRAY_BUFFER, modelIDs.get(index));
            glColorPointer(4, GL_FLOAT, 28, 12L);

            glDrawArrays(GL_QUADS, 0, 24);
            glDisableClientState(GL_COLOR_ARRAY);
            glDisableClientState(GL_VERTEX_ARRAY);            
        glPopMatrix();
//        glPushMatrix();    
//            glBegin(GL_QUADS);
//                    glColor4f(0.75f, 0.75f, 0.8f, 0.5f);   
//                    glVertex3f(loc.getCorner(3).getXfloat(),  0.11f, loc.getCorner(3).getZfloat());                 
//                    glVertex3f(loc.getCorner(2).getXfloat(),  0.11f, loc.getCorner(2).getZfloat());                 
//                    glVertex3f(loc.getCorner(1).getXfloat(),  0.11f, loc.getCorner(1).getZfloat());                 
//                    glVertex3f(loc.getCorner(0).getXfloat(),  0.11f, loc.getCorner(0).getZfloat());
//                    
//                    glVertex3f(loc.footprint.x + loc.footprint.width,   0.11f, loc.footprint.y - loc.footprint.height);                 
//                    glVertex3f(loc.footprint.x,                         0.11f, loc.footprint.y - loc.footprint.height);                 
//                    glVertex3f(loc.footprint.x,                         0.11f, loc.footprint.y);                 
//                    glVertex3f(loc.footprint.x + loc.footprint.width ,  0.11f, loc.footprint.y);
//            glEnd();            
//        glPopMatrix();
    }
    

    private static void makeGenRec() {
        glLoadIdentity();
        buildBuffer[6] = BufferUtils.createFloatBuffer(6 * 4 * 7);
        buildBuffer[6].put(new float[] {-3.0f,    0f, -3.0f, 0.60f, 0.40f, 0.75f, 1.0f,
                                        -3.0f, 2.00f, -3.0f, 0.60f, 0.40f, 0.75f, 1.0f,
                                         3.0f, 2.00f, -3.0f, 0.60f, 0.40f, 0.75f, 1.0f,
                                         3.0f,    0f, -3.0f, 0.60f, 0.40f, 0.75f, 1.0f,
        
                                        -3.0f,    0f,  3.0f, 0.60f, 0.40f, 0.75f, 1.0f,
                                        -3.0f, 2.00f,  3.0f, 0.60f, 0.40f, 0.75f, 1.0f,
                                         3.0f, 2.00f,  3.0f, 0.60f, 0.40f, 0.75f, 1.0f,
                                         3.0f,    0f,  3.0f, 0.60f, 0.40f, 0.75f, 1.0f,
        
                                        -3.0f,    0f, -3.0f, 0.60f, 0.40f, 0.75f, 1.0f,
                                        -3.0f, 2.00f, -3.0f, 0.60f, 0.40f, 0.75f, 1.0f,
                                        -3.0f, 2.00f,  3.0f, 0.60f, 0.40f, 0.75f, 1.0f,
                                        -3.0f,    0f,  3.0f, 0.60f, 0.40f, 0.75f, 1.0f,
        
                                         3.0f,    0f, -3.0f, 0.60f, 0.40f, 0.75f, 1.0f,
                                         3.0f, 2.00f, -3.0f, 0.60f, 0.40f, 0.75f, 1.0f,
                                         3.0f, 2.00f,  3.0f, 0.60f, 0.40f, 0.75f, 1.0f,
                                         3.0f,    0f,  3.0f, 0.60f, 0.40f, 0.75f, 1.0f,
        
                                        -3.0f, 2.00f, -3.0f, 0.65f, 0.45f, 0.75f, 1.0f,
                                        -3.0f, 2.00f,  3.0f, 0.65f, 0.45f, 0.75f, 1.0f,
                                         3.0f, 2.00f,  3.0f, 0.65f, 0.45f, 0.75f, 1.0f,
                                         3.0f, 2.00f, -3.0f, 0.65f, 0.45f, 0.75f, 1.0f,
        
                                        -3.0f, 0.01f, -3.0f, 0.60f, 0.85f, 0.55f, 1.0f,
                                        -3.0f, 0.01f,  3.0f, 0.60f, 0.85f, 0.55f, 1.0f,
                                         3.0f, 0.01f,  3.0f, 0.60f, 0.85f, 0.55f, 1.0f,
                                         3.0f, 0.01f, -3.0f, 0.60f, 0.85f, 0.55f, 1.0f});
        buildBuffer[6].flip();
        glBindBuffer(GL_ARRAY_BUFFER, modelIDs.get(6));
        glBufferData(GL_ARRAY_BUFFER, buildBuffer[6], GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    
        glLoadIdentity();
        buildBuffer[7] = BufferUtils.createFloatBuffer(6 * 4 * 7);
        buildBuffer[7].put(new float[] {-3.0f,    0f, -3.0f, 0.80f, 0.60f, 0.95f, 1.0f,
                                        -3.0f, 2.00f, -3.0f, 0.80f, 0.60f, 0.95f, 1.0f,
                                         3.0f, 2.00f, -3.0f, 0.80f, 0.60f, 0.95f, 1.0f,
                                         3.0f,    0f, -3.0f, 0.80f, 0.60f, 0.95f, 1.0f,
        
                                        -3.0f,    0f,  3.0f, 0.80f, 0.60f, 0.95f, 1.0f,
                                        -3.0f, 2.00f,  3.0f, 0.80f, 0.60f, 0.95f, 1.0f,
                                         3.0f, 2.00f,  3.0f, 0.80f, 0.60f, 0.95f, 1.0f,
                                         3.0f,    0f,  3.0f, 0.80f, 0.60f, 0.95f, 1.0f,
        
                                        -3.0f,    0f, -3.0f, 0.80f, 0.60f, 0.95f, 1.0f,
                                        -3.0f, 2.00f, -3.0f, 0.80f, 0.60f, 0.95f, 1.0f,
                                        -3.0f, 2.00f,  3.0f, 0.80f, 0.60f, 0.95f, 1.0f,
                                        -3.0f,    0f,  3.0f, 0.80f, 0.60f, 0.95f, 1.0f,
        
                                         3.0f,    0f, -3.0f, 0.80f, 0.60f, 0.95f, 1.0f,
                                         3.0f, 2.00f, -3.0f, 0.80f, 0.60f, 0.95f, 1.0f,
                                         3.0f, 2.00f,  3.0f, 0.80f, 0.60f, 0.95f, 1.0f,
                                         3.0f,    0f,  3.0f, 0.80f, 0.60f, 0.95f, 1.0f,
        
                                        -3.0f, 2.00f, -3.0f, 0.85f, 0.65f, 0.95f, 1.0f,
                                        -3.0f, 2.00f,  3.0f, 0.85f, 0.65f, 0.95f, 1.0f,
                                         3.0f, 2.00f,  3.0f, 0.85f, 0.65f, 0.95f, 1.0f,
                                         3.0f, 2.00f, -3.0f, 0.85f, 0.65f, 0.95f, 1.0f,
        
                                        -3.0f, 0.01f, -3.0f, 0.80f, 1.00f, 0.75f, 1.0f,
                                        -3.0f, 0.01f,  3.0f, 0.80f, 1.00f, 0.75f, 1.0f,
                                         3.0f, 0.01f,  3.0f, 0.80f, 1.00f, 0.75f, 1.0f,
                                         3.0f, 0.01f, -3.0f, 0.80f, 1.00f, 0.75f, 1.0f});
        buildBuffer[7].flip();
        glBindBuffer(GL_ARRAY_BUFFER, modelIDs.get(7));
        glBufferData(GL_ARRAY_BUFFER, buildBuffer[7], GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }
    
    
    public static void drawGenRec(MapLocale loc) {
        int index = 6;
        if(loc.isSelected()) index++;
        glPushMatrix();
            //glTranslatef(loc.getXfloat(), loc.getYfloat(), loc.getZfloat());
            glTranslatef(loc.getXfloat(), loc.getYfloat() -1, loc.getZfloat());
            glRotatef(loc.getRotate(), 0, 1, 0);
            
            glEnableClientState(GL_VERTEX_ARRAY);
            glBindBuffer(GL_ARRAY_BUFFER, modelIDs.get(index));
            glVertexPointer(3, GL_FLOAT, 28, 0L);

            glEnableClientState(GL_COLOR_ARRAY);
            glBindBuffer(GL_ARRAY_BUFFER, modelIDs.get(index));
            glColorPointer(4, GL_FLOAT, 28, 12L);

            glDrawArrays(GL_QUADS, 0, 24);
            glDisableClientState(GL_COLOR_ARRAY);
            glDisableClientState(GL_VERTEX_ARRAY);             
        glPopMatrix(); 
//        glPushMatrix();    
//            glBegin(GL_QUADS);
//                    glColor4f(0.9f, 0.75f, 0.9f, 0.75f);  
//                    glVertex3f(loc.getCorner(3).getXfloat(),  0.11f, loc.getCorner(3).getZfloat());                 
//                    glVertex3f(loc.getCorner(2).getXfloat(),  0.11f, loc.getCorner(2).getZfloat());                 
//                    glVertex3f(loc.getCorner(1).getXfloat(),  0.11f, loc.getCorner(1).getZfloat());                 
//                    glVertex3f(loc.getCorner(0).getXfloat(),  0.11f, loc.getCorner(0).getZfloat());
//                    
//                    glVertex3f(loc.footprint.x + loc.footprint.width,   0.11f, loc.footprint.y - loc.footprint.height);                 
//                    glVertex3f(loc.footprint.x,                         0.11f, loc.footprint.y - loc.footprint.height);                 
//                    glVertex3f(loc.footprint.x,                         0.11f, loc.footprint.y);                 
//                    glVertex3f(loc.footprint.x + loc.footprint.width ,  0.11f, loc.footprint.y);
//            glEnd();            
//        glPopMatrix();
    }
    

    private static void makeDebugBlock() {
        glLoadIdentity();
        buildBuffer[8] = BufferUtils.createFloatBuffer(6 * 4 * 7);
        buildBuffer[8].put(new float[] {-0.5f, -0.5f, -0.5f, 1.0f, 1.0f, 1.0f, 1.0f,
                                        -0.5f,  0.5f, -0.5f, 1.0f, 1.0f, 1.0f, 1.0f,
                                         0.5f,  0.5f, -0.5f, 1.0f, 1.0f, 1.0f, 1.0f,
                                         0.5f,  0.5f, -0.5f, 1.0f, 1.0f, 1.0f, 1.0f,
        
                                        -0.5f, -0.5f,  0.5f, 1.0f, 1.0f, 1.0f, 1.0f,
                                        -0.5f,  0.5f,  0.5f, 1.0f, 1.0f, 1.0f, 1.0f,
                                         0.5f,  0.5f,  0.5f, 1.0f, 1.0f, 1.0f, 1.0f,
                                         0.5f, -0.5f,  0.5f, 1.0f, 1.0f, 1.0f, 1.0f,
        
                                        -0.5f, -0.5f, -0.5f, 1.0f, 1.0f, 1.0f, 1.0f,
                                        -0.5f,  0.5f, -0.5f, 1.0f, 1.0f, 1.0f, 1.0f,
                                        -0.5f,  0.5f,  0.5f, 1.0f, 1.0f, 1.0f, 1.0f,
                                        -0.5f, -0.5f,  0.5f, 1.0f, 1.0f, 1.0f, 1.0f,
        
                                         0.5f, -0.5f,  0.5f, 1.0f, 1.0f, 1.0f, 1.0f,
                                         0.5f,  0.5f, -0.5f, 1.0f, 1.0f, 1.0f, 1.0f,
                                         0.5f,  0.5f,  0.5f, 1.0f, 1.0f, 1.0f, 1.0f,
                                         0.5f, -0.5f,  0.5f, 1.0f, 1.0f, 1.0f, 1.0f,
        
                                        -0.5f,  0.5f, -0.5f, 1.0f, 1.0f, 1.0f, 1.0f,
                                        -0.5f,  0.5f,  0.5f, 1.0f, 1.0f, 1.0f, 1.0f,
                                         0.5f,  0.5f,  0.5f, 1.0f, 1.0f, 1.0f, 1.0f,
                                         0.5f,  0.5f, -0.5f, 1.0f, 1.0f, 1.0f, 1.0f,
        
                                        -0.5f, -0.5f, -0.5f, 1.0f, 1.0f, 1.0f, 1.0f,
                                        -0.5f, -0.5f,  0.5f, 1.0f, 1.0f, 1.0f, 1.0f,
                                         0.5f, -0.5f,  0.5f, 1.0f, 1.0f, 1.0f, 1.0f,
                                         0.5f, -0.5f, -0.5f, 1.0f, 1.0f, 1.0f, 1.0f});
        buildBuffer[8].flip();
        glBindBuffer(GL_ARRAY_BUFFER, modelIDs.get(8));
        glBufferData(GL_ARRAY_BUFFER, buildBuffer[8], GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }
    
    
    public static void drawDebugBlock(Location loc) {
        int index = 8;
        glPushMatrix();
            //glTranslatef(loc.getXfloat(), loc.getYfloat(), loc.getZfloat());
            glTranslatef(loc.getXfloat(), loc.getYfloat(), loc.getZfloat());
            glRotatef(loc.getRotate(), 0, 1, 0);
            
            glEnableClientState(GL_VERTEX_ARRAY);
            glBindBuffer(GL_ARRAY_BUFFER, modelIDs.get(index));
            glVertexPointer(3, GL_FLOAT, 28, 0L);

            glEnableClientState(GL_COLOR_ARRAY);
            glBindBuffer(GL_ARRAY_BUFFER, modelIDs.get(index));
            glColorPointer(4, GL_FLOAT, 28, 12L);

            glDrawArrays(GL_QUADS, 0, 24);
            glDisableClientState(GL_COLOR_ARRAY);
            glDisableClientState(GL_VERTEX_ARRAY);             
        glPopMatrix(); 
//        glPushMatrix();    
//            glBegin(GL_QUADS);
//                    glColor4f(0.9f, 0.75f, 0.9f, 0.75f);  
//                    glVertex3f(loc.getCorner(3).getXfloat(),  0.11f, loc.getCorner(3).getZfloat());                 
//                    glVertex3f(loc.getCorner(2).getXfloat(),  0.11f, loc.getCorner(2).getZfloat());                 
//                    glVertex3f(loc.getCorner(1).getXfloat(),  0.11f, loc.getCorner(1).getZfloat());                 
//                    glVertex3f(loc.getCorner(0).getXfloat(),  0.11f, loc.getCorner(0).getZfloat());
//                    
//                    glVertex3f(loc.footprint.x + loc.footprint.width,   0.11f, loc.footprint.y - loc.footprint.height);                 
//                    glVertex3f(loc.footprint.x,                         0.11f, loc.footprint.y - loc.footprint.height);                 
//                    glVertex3f(loc.footprint.x,                         0.11f, loc.footprint.y);                 
//                    glVertex3f(loc.footprint.x + loc.footprint.width ,  0.11f, loc.footprint.y);
//            glEnd();            
//        glPopMatrix();
    }
}
