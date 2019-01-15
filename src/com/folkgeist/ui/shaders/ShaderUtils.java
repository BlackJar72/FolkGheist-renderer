//Copyright (C) Jared Blackburn, 2014
package com.folkgeist.ui.shaders;

import com.folkgeist.util.math.Mat4f;
import com.folkgeist.util.math.Vec2f;
import com.folkgeist.util.math.Vec2i;
import com.folkgeist.util.math.Vec3f;
import com.folkgeist.util.math.Vec3i;
import com.folkgeist.util.math.Vec4f;
import com.folkgeist.util.math.Vec4i;
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL20.*;
import org.lwjgl.util.vector.Matrix4f;

/**
 *
 * @author JaredBGreat (Jared Blackburn)
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
    
    
    public static void passUniform(String name, 
            int program, Mat4f data) {
        FloatBuffer buff = BufferUtils.createFloatBuffer(16);
        float[] array = new float[16];
        //Not sure if this is the right order, but, for now...
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                array[i + (4*j)] = data.get(i, j);
        buff.put(array);
        buff.flip();
        glUniformMatrix4(glGetUniformLocation(program, name), false, buff);        
    }
    
    /****************************************************/
    /*               TDODO: Matrix passing              */
    /****************************************************/
    
}
