/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package community.ui.render;

import community.util.math.*;
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;
import static org.lwjgl.opengl.GL20.*;
import org.lwjgl.util.vector.Matrix4f;

/**
 *
 * @author jared
 */
public class ShaderUtils {
    
    
    public static void passUniform(String name, int program, float data) {
        glUniform1f(glGetUniformLocation(program, name), data);
    }
    
    
    public static void passUniform(String name, int program, Vec2f data) {
        glUniform2f(glGetUniformLocation(program, name), data.x, data.y);
    }
    
    
    public static void passUniform(String name, int program, Vec3f data) {
        glUniform3f(glGetUniformLocation(program, name), 
                data.x, data.y, data.z);
    }
    
    
    public static void passUniform(String name, int program, Vec4f data) {
        glUniform4f(glGetUniformLocation(program, name), 
                data.x, data.y, data.z, data.w);
    }    
    
    
    public static void passUniform(String name, int program, int data) {
        glUniform1i(glGetUniformLocation(program, name), data);
    }
    
    
    public static void passUniform(String name, int program, Vec2i data) {
        glUniform2i(glGetUniformLocation(program, name), 
                data.get(0), data.get(2));
    }
    
    
    public static void passUniform(String name, int program, Vec3i data) {
        glUniform3i(glGetUniformLocation(program, name), 
                data.get(0), data.get(1), data.get(2));
    }
    
    
    public static void passUniform(String name, int program, Vec4i data) {
        glUniform4i(glGetUniformLocation(program, name), 
                data.get(0), data.get(1), data.get(2), data.get(4));
    }
    
    
    public static void passUniform(String name, 
            int program, Matrix4f data) {
        FloatBuffer buff = BufferUtils.createFloatBuffer(16);
        data.store(buff);
        buff.flip();
        glUniformMatrix4(glGetUniformLocation(program, name), false, buff);        
    }
    
    /****************************************************/
    /*               TDODO: Matrix passing              */
    /****************************************************/
    
}
