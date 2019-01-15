//Copyright (C) Jared Blackburn, 2014
package com.folkgeist.ui.models;


import com.folkgeist.ui.UI;
import static com.folkgeist.ui.shaders.ShaderUtils.passUniform;
import com.folkgeist.util.math.Location;
import com.folkgeist.util.math.Mat4f;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glUniform1i;
import static org.lwjgl.opengl.GL20.glUseProgram;

/**
 * A complete model, including a mesh and its texture.  This class contains no
 * vertex data and technically so does not store the mesh per se, but instead 
 * its VBO ID.  Likewise, the texture components are actually TBO IDs.  In 
 * addition, instruction as to its default and selected shaders are included.
 * Thus, the class data consists of a small number of int variables.
 * 
 * @author JaredBGreat (Jared Blackburn)
 */
public class Model {
    int mesh;    
    int size;     // Number of faces in the mesh
    int texture;  // ID of GL texture object
    int normals;  // ID of GL normal map
    int specular; // ID of GL specular map
    private int shaderUsed;
    static int primaryShader = 0;
    static int secondaryShader = 0;
    
    
    public Model(Mesh mesh, int texture) {
        this.mesh = mesh.vboid;
        this.texture = texture;
        size = mesh.faces.size() * 3; 
        normals  = 0;
        specular = 0;
    }
    
    
    public Model(Mesh mesh, int texture, int nm) {
        this.mesh = mesh.vboid;
        this.texture = texture;
        this.normals = nm;
        size = mesh.faces.size() * 3; 
        specular = 0;
    }
    
    
    public Model(Mesh mesh, int texture, int nm, int spec) {
        this.mesh = mesh.vboid;
        this.texture = texture;
        this.normals = nm;
        this.specular = spec;
        size = mesh.faces.size() * 3; 
    }
    
    
    public void draw(Location loc) {
        //if(loc.isSelected()) glUseProgram(secondaryShader);
        //else glUseProgram(primaryShader);
        glUseProgram(primaryShader);
        Mat4f mat = Mat4f.giveRotation(loc.r);        
        passUniform("normRot", primaryShader, mat);
        //mat = (RenderUtils.proj).mul(Mat4f.giveTransLoc(loc));  
        mat = (UI.getRenderTranform()).mul(Mat4f.giveTransLoc(loc));       
        passUniform("trans", primaryShader, mat);
        glPushMatrix();
            // This must change to use new systems
            //glTranslatef(loc.x, loc.y, loc.z);
            //glRotatef(loc.r, 0, 1, 0);
            
            glBindBuffer(GL_ARRAY_BUFFER, mesh);
            
            glEnableClientState(GL_VERTEX_ARRAY);
            glVertexPointer(3, GL_FLOAT, 32, 0L);

            glEnableClientState(GL_NORMAL_ARRAY);
            glNormalPointer(GL_FLOAT, 32, 12L);

            glEnableClientState(GL_TEXTURE_COORD_ARRAY);
            glTexCoordPointer(2, GL_FLOAT, 32, 24L);
            
            glActiveTexture(GL_TEXTURE0);
            glBindTexture(GL_TEXTURE_2D, texture);
            glUniform1i(glGetUniformLocation(primaryShader, "img"), 0);
            glActiveTexture(GL_TEXTURE1);
            glBindTexture(GL_TEXTURE_2D, normals);
            glUniform1i(glGetUniformLocation(primaryShader, "nmap"), 1);
            glActiveTexture(GL_TEXTURE2);
            glBindTexture(GL_TEXTURE_2D, specular);
            glUniform1i(glGetUniformLocation(primaryShader, "spmap"), 2);

            glDrawArrays(GL_TRIANGLES, 0, size);
            
            glDisableClientState(GL_TEXTURE_COORD_ARRAY);
            glDisableClientState(GL_NORMAL_ARRAY);
            glDisableClientState(GL_VERTEX_ARRAY); 
            glActiveTexture(GL_TEXTURE0);
            glBindTexture(GL_TEXTURE_2D, 0);
            glActiveTexture(GL_TEXTURE1);
            glBindTexture(GL_TEXTURE_2D, 0);
            glActiveTexture(GL_TEXTURE2);
            glBindTexture(GL_TEXTURE_2D, 0);
            
            glBindBuffer(GL_ARRAY_BUFFER, 0);
        glPopMatrix();
        glUseProgram(0);
    }
    
    
    public void draw(Mat4f transform, Mat4f normRotation) {
        //if(loc.isSelected()) glUseProgram(secondaryShader);
        //else glUseProgram(primaryShader);
        glUseProgram(primaryShader);     
        passUniform("normRot", primaryShader, normRotation);
        passUniform("trans", primaryShader, (UI.getRenderTranform()).mul(transform));
        glPushMatrix();
            // This must change to use new systems
            //glTranslatef(loc.x, loc.y, loc.z);
            //glRotatef(loc.r, 0, 1, 0);
            
            glBindBuffer(GL_ARRAY_BUFFER, mesh);
            
            glEnableClientState(GL_VERTEX_ARRAY);
            glVertexPointer(3, GL_FLOAT, 32, 0L);

            glEnableClientState(GL_NORMAL_ARRAY);
            glNormalPointer(GL_FLOAT, 32, 12L);

            glEnableClientState(GL_TEXTURE_COORD_ARRAY);
            glTexCoordPointer(2, GL_FLOAT, 32, 24L);
            
            glActiveTexture(GL_TEXTURE0);
            glBindTexture(GL_TEXTURE_2D, texture);
            glUniform1i(glGetUniformLocation(primaryShader, "img"), 0);
            glActiveTexture(GL_TEXTURE1);
            glBindTexture(GL_TEXTURE_2D, normals);
            glUniform1i(glGetUniformLocation(primaryShader, "nmap"), 1);
            glActiveTexture(GL_TEXTURE2);
            glBindTexture(GL_TEXTURE_2D, specular);
            glUniform1i(glGetUniformLocation(primaryShader, "spmap"), 2);

            glDrawArrays(GL_TRIANGLES, 0, size);
            
            glDisableClientState(GL_TEXTURE_COORD_ARRAY);
            glDisableClientState(GL_NORMAL_ARRAY);
            glDisableClientState(GL_VERTEX_ARRAY); 
            glActiveTexture(GL_TEXTURE0);
            glBindTexture(GL_TEXTURE_2D, 0);
            glActiveTexture(GL_TEXTURE1);
            glBindTexture(GL_TEXTURE_2D, 0);
            glActiveTexture(GL_TEXTURE2);
            glBindTexture(GL_TEXTURE_2D, 0);
            
            glBindBuffer(GL_ARRAY_BUFFER, 0);
        glPopMatrix();
        glUseProgram(0);
    }
    
    
    public void draw(Location loc, int shader) {        
        glUseProgram(shader);
        Mat4f mat = Mat4f.giveRotation(loc.r);        
        passUniform("normRot", primaryShader, mat);
        //mat = (RenderUtils.proj).mul(Mat4f.giveTransLoc(loc));  
        mat = (UI.getRenderTranform()).mul(Mat4f.giveTransLoc(loc));       
        passUniform("trans", primaryShader, mat);
        glPushMatrix();
        glPushMatrix();
            // This must change to use new systems
            //glTranslatef(loc.x, loc.y, loc.z);
            //glRotatef(loc.r, 0, 1, 0);
            
            glBindBuffer(GL_ARRAY_BUFFER, mesh);
            
            glEnableClientState(GL_VERTEX_ARRAY);
            glVertexPointer(3, GL_FLOAT, 32, 0L);

            glEnableClientState(GL_NORMAL_ARRAY);
            glNormalPointer(GL_FLOAT, 32, 12L);

            glEnableClientState(GL_TEXTURE_COORD_ARRAY);
            glTexCoordPointer(2, GL_FLOAT, 32, 24L);
            
            glActiveTexture(GL_TEXTURE0);
            glBindTexture(GL_TEXTURE_2D, texture);
            glUniform1i(glGetUniformLocation(primaryShader, "img"), 0);
            glActiveTexture(GL_TEXTURE1);
            glBindTexture(GL_TEXTURE_2D, normals);
            glUniform1i(glGetUniformLocation(primaryShader, "nmap"), 1);
            glActiveTexture(GL_TEXTURE2);
            glBindTexture(GL_TEXTURE_2D, specular);
            glUniform1i(glGetUniformLocation(primaryShader, "spmap"), 2);

            glDrawArrays(GL_TRIANGLES, 0, size);
            
            glDisableClientState(GL_TEXTURE_COORD_ARRAY);
            glDisableClientState(GL_NORMAL_ARRAY);
            glDisableClientState(GL_VERTEX_ARRAY); 
            glActiveTexture(GL_TEXTURE0);
            glBindTexture(GL_TEXTURE_2D, 0);
            glActiveTexture(GL_TEXTURE1);
            glBindTexture(GL_TEXTURE_2D, 0);
            glActiveTexture(GL_TEXTURE2);
            glBindTexture(GL_TEXTURE_2D, 0);
            
            glBindBuffer(GL_ARRAY_BUFFER, 0);
        glPopMatrix();
        glUseProgram(0);
    }
    
    
    public static void setPrimaryShader(int shader) {
        primaryShader = shader;
    }
    
    
    public static void setSecondaryShader(int shader) {
        secondaryShader = shader;
    }
    
}
