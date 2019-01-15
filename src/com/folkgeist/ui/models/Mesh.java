//Copyright (C) Jared Blackburn, 2014
package com.folkgeist.ui.models;

import com.folkgeist.util.math.Vec2f;
import com.folkgeist.util.math.Vec3f;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL15.*;

/**
 * A class representing a model's mesh, this is designed to take the mesh data 
 * and convert it to a VBO.
 * 
 * @author JaredBGreat (Jared Blackburn)
 */
public class Mesh {
    protected List<Vec3f> vertices = new ArrayList<Vec3f>();
    protected List<Vec2f> uvcoords = new ArrayList<Vec2f>();
    protected List<Vec3f> normals  = new ArrayList<Vec3f>();
    protected List<Face>  faces    = new ArrayList<Face>();
    protected int vboid = 0;
        
   
    /**
     * This will take a VBO ID and create a VBO of the mesh with that ID.
     * 
     * @param vboid 
     */
    
    public void makeVBO(int vboid) {
        this.vboid = vboid;
        int size = faces.size() 
                * (3 + 2 + 3 + 4) * 3 ;
        int size2 = faces.size() 
                * 2 * 3 ;
        FloatBuffer buildBuffer = BufferUtils.createFloatBuffer(size);
        float[] a1 = new float[size];
        int[]   a3 = new int[faces.size() * 3];
                
        int index = 0;
        for(Face face: faces) {  
            for(int i = 0; i < 3; i++) {
                a1[index++] = vertices.get(face.vertices.get(i) - 1).get(0);            
                a1[index++] = vertices.get(face.vertices.get(i) - 1).get(1);            
                a1[index++] = vertices.get(face.vertices.get(i) - 1).get(2);            
                a1[index++] = normals.get(face.normals.get(i) - 1).get(0);            
                a1[index++] = normals.get(face.normals.get(i) - 1).get(1);            
                a1[index++] = normals.get(face.normals.get(i) - 1).get(2);            
                a1[index++] = uvcoords.get(face.uvcoords.get(i) - 1).get(1);            
                a1[index++] = uvcoords.get(face.uvcoords.get(i) - 1).get(0); 
            }
        }
        
//        for(int i = 0; i < array.length; i++)
//         System.out.println(array[i]);
        
        
        buildBuffer.put(a1);
        buildBuffer.flip();
        glBindBuffer(GL_ARRAY_BUFFER, vboid);
        glBufferData(GL_ARRAY_BUFFER, buildBuffer, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        
//        glBindBuffer(GL_ARRAY_BUFFER, vboid);
//        glBufferData(GL_ARRAY_BUFFER, buildBuffer2, GL_STATIC_DRAW);
//        glBindBuffer(GL_ARRAY_BUFFER, 0);
        //glLoadIdentity();
    }    
}
