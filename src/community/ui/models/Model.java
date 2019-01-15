/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package community.ui.models;

import community.simulation.map.MapLocale;
import community.ui.render.ShaderRegistry;
import static community.ui.render.ShaderUtils.passUniform;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glUniform1i;
import static org.lwjgl.opengl.GL20.glUseProgram;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author jared
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
    
    
    public void draw(MapLocale loc) {
        if(loc.isSelected()) glUseProgram(secondaryShader);
        else glUseProgram(primaryShader);
        Matrix4f mat = (Matrix4f)(new Matrix4f().setIdentity());
        mat.rotate((float)Math.toRadians(loc.getRotate()), new Vector3f(0, 1, 0));
        //int uid1 = glGetUniformLocation(ShaderRegistry.getPrimary(), "normRot");
        passUniform("normRot", ShaderRegistry.getPrimary(), mat);
        glPushMatrix();
            glTranslatef(loc.getXfloat(), loc.getYfloat() -1, loc.getZfloat());
            glRotatef(loc.getRotate(), 0, 1, 0);
            
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
    
    
    public void draw(MapLocale loc, int shader) {
        glUseProgram(shader);
        Matrix4f mat = (Matrix4f)(new Matrix4f().setIdentity());
        mat.rotate((float)Math.toRadians(loc.getRotate()), new Vector3f(0, 1, 0));
        //int uid1 = glGetUniformLocation(ShaderRegistry.getPrimary(), "normRot");
        passUniform("normRot", ShaderRegistry.getPrimary(), mat);
        glPushMatrix();
            glTranslatef(loc.getXfloat(), loc.getYfloat() -1, loc.getZfloat());
            glRotatef(loc.getRotate(), 0, 1, 0);
            
            glBindBuffer(GL_ARRAY_BUFFER, mesh);
            
            glEnableClientState(GL_VERTEX_ARRAY);
            glVertexPointer(3, GL_FLOAT, 48, 0L);

            glEnableClientState(GL_NORMAL_ARRAY);
            glNormalPointer(GL_FLOAT, 48, 12L);

            glEnableClientState(GL_TEXTURE_COORD_ARRAY);
            glTexCoordPointer(2, GL_FLOAT, 48, 24L);
            
            glActiveTexture(GL_TEXTURE0);
            glBindTexture(GL_TEXTURE_2D, texture);
            glUniform1i(glGetUniformLocation(primaryShader, "img"), 0);
            glActiveTexture(GL_TEXTURE1);
            glBindTexture(GL_TEXTURE_2D, normals);
            glUniform1i(glGetUniformLocation(primaryShader, "nmap"), 1);
            glActiveTexture(GL_TEXTURE2);
            glBindTexture(GL_TEXTURE_2D, specular);
            glUniform1i(glGetUniformLocation(primaryShader, "spmap"), 2);

            glEnableClientState(GL_COLOR_ARRAY);
            glColorPointer(4, GL_FLOAT, 48, 32L);

            glDrawArrays(GL_TRIANGLES, 0, size);
            
            glDisableClientState(GL_COLOR_ARRAY);
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
