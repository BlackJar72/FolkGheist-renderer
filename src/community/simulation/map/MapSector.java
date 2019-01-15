/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package community.simulation.map;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_FRONT_AND_BACK;
import static org.lwjgl.opengl.GL11.GL_LINE;
import static org.lwjgl.opengl.GL11.GL_POINTS;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_TRIANGLE_STRIP;
import static org.lwjgl.opengl.GL11.GL_VERTEX_ARRAY;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glDisableClientState;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnableClientState;
import static org.lwjgl.opengl.GL11.glPolygonMode;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertexPointer;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;

/**
 *
 * @author jared
 */
public final class MapSector {    
    public static final float SIZE  =  64f;
    public static final int   SIZEI =  (int)SIZE;
    public static final int   HALF  =  SIZEI / 2;  
    private final int x, z;
    private ArrayList<ContiguousRegion> regions;
    private ArrayList<MapVertex> vertices;
    private int vboid;
    private int vboSize;
    
    private final MapTile[][] tiles = new MapTile[SIZEI][SIZEI];
    
    
    public MapSector(int x, int z) {
        this.x = x;
        this.z = z;
        for(int i = 0; i < SIZEI; i++)
            for(int j = 0; j < SIZEI; j++) {
                tiles[i][j] = new MapTile(i - HALF, -1, j - HALF);
            }
        makeVertices();
        makeOptimizedVBO();
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
        vboSize = vertices.size() * 3;
        float[] mverts = new float[vboSize];
        int idx = 0;
        for(MapVertex vert : vertices) {
            //System.out.print(vert.getX() + x + ", ");
            mverts[idx++] = vert.getX() + x;
            //System.out.print(vert.getY() + ", ");
            mverts[idx++] = vert.getY();
            //System.out.println(vert.getZ() + z);
            mverts[idx++] = vert.getZ() + z;
//            mverts[idx++] = 0.2f;
//            mverts[idx++] = 0.8f;
//            mverts[idx++] = 0.3f;
//            mverts[idx++] = 1.0f;
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
    
    
    public void draw() {
        glDisable(GL_DEPTH_TEST);
        glPushMatrix();  
            //glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
            glColor3f(0.2f, 0.8f, 0.3f);          
            glBindBuffer(GL_ARRAY_BUFFER, vboid);
            
            glEnableClientState(GL_VERTEX_ARRAY);
            glVertexPointer(3, GL_FLOAT, 12, 0L);

            //glDrawArrays(GL_TRIANGLE_STRIP, 0, size);
            glDrawArrays(GL_TRIANGLES, 0, vboSize);
            
            glDisableClientState(GL_VERTEX_ARRAY); 
            glBindBuffer(GL_ARRAY_BUFFER, 0);
            //glPolygonMode(GL_FRONT_AND_BACK, GL11.GL_FILL);
        glPopMatrix();
        glEnable(GL_DEPTH_TEST);
    }
        
}
