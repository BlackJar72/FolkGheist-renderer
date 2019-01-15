package community.simulation.map;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import community.simulation.*;
import community.simulation.locales.*;
import community.simulation.citizens.*;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;


/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */
public class CityMap {
    public static final int   SSIZE  = 16;
    public static final int   SHALF  = SSIZE / 2;
    public static final int   WSIZEI =  SSIZE * MapSector.SIZEI;
    public static final float WSIZEF =  (float)WSIZEI;
    public static final int   HALF   =  WSIZEI / 2;
    public static final float minX   = -(WSIZEF / 2f);
    public static final float maxX   =  (WSIZEF / 2f);
    public static final float minY   = -(WSIZEF / 2f);
    public static final float maxY   =  (WSIZEF / 2f);
    
    private final MapSector[][] secs = new MapSector[SSIZE][SSIZE];
    
    
    public CityMap() {
        for(int i = 0; i < SSIZE; i++)
            for(int j = 0; j < SSIZE; j++) {
                secs[i][j] = new MapSector(((i - SHALF) * 64) + MapSector.HALF, 
                        ((j - SHALF) * 64) + MapSector.HALF);
            }
    }
    
    
    public void draw() {
        for(int i = 0; i < SSIZE; i++)
            for(int j = 0; j < SSIZE; j++) {
                secs[i][j].draw();
            }
    }
    
}
