//Copyright JaredBlackburn (c) 2014
package com.folkgeist.map;

import com.folkgeist.globals.PerGameSettings;
import com.folkgeist.ui.UI;
import com.folkgeist.ui.models.Models;
import com.folkgeist.ui.render.IViewer;
import com.folkgeist.ui.shaders.ShaderRegistry;
import static com.folkgeist.ui.shaders.ShaderUtils.passUniform;
import com.folkgeist.util.math.Mat4f;
import com.folkgeist.util.math.Vec4f;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Random;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.GL_COLOR_ARRAY;
import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_FRONT_AND_BACK;
import static org.lwjgl.opengl.GL11.GL_LINE;
import static org.lwjgl.opengl.GL11.GL_NORMAL_ARRAY;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_VERTEX_ARRAY;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glColorPointer;
import static org.lwjgl.opengl.GL11.glDisableClientState;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glEnableClientState;
import static org.lwjgl.opengl.GL11.glNormalPointer;
import static org.lwjgl.opengl.GL11.glPolygonMode;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glVertexPointer;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glUniform1i;
import static org.lwjgl.opengl.GL20.glUseProgram;

/**
 *
 * @author JaredBGreat (Jared Blackburn)
 */
public final class MapSector {    
    public static final float SIZE   =  64f;   
    public static final float RADIUS =  SIZE * 0.75f;
    public static final int   SIZEI  =  (int)SIZE;
    public static final int   HALF   =  SIZEI / 2;  
    private final int x, z;
    private final Vec4f center;
    private ArrayList<ContiguousRegion> regions;
    private ArrayList<MapVertex> vertices;
    private int vboid;
    private int vboSize;    
    private Perlinator noise;
    private byte[][] heights;
    
    public final long localSeed;
    
    private final MapTile[][] tiles = new MapTile[SIZEI][SIZEI];
    private int texture;
    private int normals;
    private int specular;
    
    
    public MapSector(int x, int z) {
        //HouseKeeping.reportLog("Generating map sector centered at " + x + ", " +z);
        this.x = x;
        this.z = z;
        center = new Vec4f(x, 0, z, 1); // Used with RADIUS in frustum culling
        localSeed = PerGameSettings.getSeed() + (2027l * (long)x) + (1987l * (long)z);
        noise = new Perlinator(64, new Random(localSeed));
        // Need to fix Contiguous region for non-zero noise height
        heights = noise.getIntTable(0);  //FIXME: ContiguousRegion.java !!!
        for(int i = 0; i < SIZEI; i++)
            for(int j = 0; j < SIZEI; j++) {
                tiles[i][j] = new MapTile(i - HALF, heights[i][j]-1, j - HALF);
                //tiles[i][j] = new MapTile(i - HALF, ((i+j) / 16) -1, j - HALF);
                //tiles[i][j] = new MapTile(i - HALF, -1, j - HALF);
            }
        makeVertices();
        makeOptimizedVBO();
        
        //FIXME: Give the land a static texture (or texture list) and make it fit the land
        //TODO:  Strech a grass texture over the ground
        texture  = Models.getTexture("grass");
        normals  = Models.getTexture("grassNM");
        specular = Models.getTexture("grassSP");
    }
    
    
    public void makeVertices() {
        regions = new ArrayList<ContiguousRegion>();
        regions.add(new ContiguousRegion());
        for(int i = 0; i < SIZEI; i++)
            for(int j = 0; j < SIZEI; j++) {
                if(tiles[i][j].inRegion()) continue;
                ContiguousRegion next = new ContiguousRegion();
                int nextId = regions.indexOf(next);
                next.addRegion(this, i, j, nextId);
                regions.add(next);
            }
    }
    
    
    public void makeOptimizedVBO() {
        vertices = ContiguousRegion.resolveSquares(this);
        vboSize = vertices.size() * 6;
        float[] mverts = new float[vboSize];
        int idx = 0;
        int which = 0;
        for(MapVertex vert : vertices) {
            if(which == 0) {
                mverts[idx++] = vert.getX() + x;
                mverts[idx++] = vert.getY();
                mverts[idx++] = vert.getZ() + z;
                which++;
            } else if (which == 1) {
                mverts[idx++] = vert.x;
                mverts[idx++] = vert.y;
                mverts[idx++] = vert.z;
                //which++;
                which = 0;
            } else {
                mverts[idx++] = vert.x;
                mverts[idx++] = vert.y;
                which = 0;
            }
        }
        IntBuffer id = BufferUtils.createIntBuffer(1);
        glGenBuffers(id);
        vboid = id.get(0);
        FloatBuffer data = BufferUtils.createFloatBuffer(vboSize);
        data.put(mverts);
        data.flip();
        glBindBuffer(GL_ARRAY_BUFFER, vboid);
        glBufferData(GL_ARRAY_BUFFER, data, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    
    }
    
    
    public MapTile getTile(int tx, int tz) {
        return tiles[tx][tz];
    }
    
    
    public void draw(IViewer cam) {
        boolean cvar = false;
        if(cam.cull(center, RADIUS)) {
            return;
        }
        //glDisable(GL_DEPTH_TEST);
        //glDisable(GL_CULL_FACE);
        int shader = ShaderRegistry.getPrimary();
        glUseProgram(shader);
        Mat4f mat = Mat4f.giveIdentity();        
        passUniform("normRot", shader, mat);
        //mat = (RenderUtils.proj).mul(Mat4f.giveTransLoc(loc));  
        mat = (UI.getRenderTranform());       
        passUniform("trans", shader, mat);
        glPushMatrix();  
            if(cvar) {
                glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
                glColor3f(0.3f, 0.5f, 0.4f);          
            }
            glColor3f(0.2f, 0.8f, 0.3f);          
            glBindBuffer(GL_ARRAY_BUFFER, vboid);
            
            glEnableClientState(GL_VERTEX_ARRAY);
            glVertexPointer(3, GL_FLOAT, 24, 0L);

            glEnableClientState(GL_NORMAL_ARRAY);
            glNormalPointer(GL_FLOAT, 24, 12L);

            //glEnableClientState(GL_TEXTURE_COORD_ARRAY);
            //glTexCoordPointer(2, GL_FLOAT, 32, 24L);
            
            
            
            glActiveTexture(GL_TEXTURE0);
            glBindTexture(GL_TEXTURE_2D, texture);
            glUniform1i(glGetUniformLocation(shader, "img"), 0);
            //glActiveTexture(GL_TEXTURE1);
            //glBindTexture(GL_TEXTURE_2D, normals);
            //glUniform1i(glGetUniformLocation(shader, "nmap"), 1);
            //glActiveTexture(GL_TEXTURE2);
            //glBindTexture(GL_TEXTURE_2D, specular);
            //glUniform1i(glGetUniformLocation(shader, "spmap"), 2);
            
            glEnableClientState(GL_COLOR_ARRAY);
            glColorPointer(4, GL_FLOAT, 28, 12L);

            //glDrawArrays(GL_TRIANGLE_STRIP, 0, vboSize);
            glDrawArrays(GL_TRIANGLES, 0, vboSize);
            
            glDisableClientState(GL_VERTEX_ARRAY); 
            glBindBuffer(GL_ARRAY_BUFFER, 0);
            glPolygonMode(GL_FRONT_AND_BACK, GL11.GL_FILL);
        glPopMatrix();
        //glEnable(GL_DEPTH_TEST);
        //glEnable(GL_CULL_FACE);
    }
        
}
