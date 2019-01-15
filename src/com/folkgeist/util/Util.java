//Copyright (C) Jared Blackburn, 2014
package com.folkgeist.util;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;

/**
 *
 * @author JaredBGreat (Jared Blackburn)
 */
public class Util {
    
    
    public static FloatBuffer makeFoatBuffer(float[] data) {
        FloatBuffer out = BufferUtils.createFloatBuffer(data.length);
        out.put(data);
        out.flip();
        return out;        
    }
    
    
    public static IntBuffer makeIntBuffer(int[] data) {
        IntBuffer out = BufferUtils.createIntBuffer(data.length);
        out.put(data);
        out.flip();
        return out;        
    }
    
    
    public static ByteBuffer makeByteBuffer(byte[] data) {
        ByteBuffer out = BufferUtils.createByteBuffer(data.length);
        out.put(data);
        out.flip();
        return out;        
    }
    
}
