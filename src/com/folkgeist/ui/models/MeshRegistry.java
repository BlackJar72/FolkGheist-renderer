//Copyright (C) Jared Blackburn, 2014
package com.folkgeist.ui.models;

import java.nio.IntBuffer;
import java.util.HashMap;
import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL15.*;


/**
 *
 * @author JaredBGreat (Jared Blackburn)
 */
public class MeshRegistry extends HashMap<String, Mesh> {
    
    public Mesh add(String name, String path, float scale) {
        IntBuffer buffer = BufferUtils.createIntBuffer(1);
        glGenBuffers(buffer);
        int vboid = buffer.get(0);
        Mesh model = OBJLoader.loadModel(path, scale);
        model.makeVBO(vboid);
        super.put(name, model);
        return model;
    }
    
}
