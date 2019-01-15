//Copyright (C) Jared Blackburn, 2014
package com.folkgeist.ui.models;

import java.util.HashMap;
import static org.lwjgl.opengl.GL11.glGenTextures;

/**
 *
 * @author JaredBGreat (Jared Blackburn)
 */
public class TextureRegistry extends HashMap<String, Integer> {
    
    public int add(String name, String path) {
        int glID = glGenTextures();
        TextureLoader.loadImage(path, glID);
        super.put(name, Integer.valueOf(glID));
        return glID;
    }
    
    
    public int getID(String name) {
        return super.get(name).intValue();
    }
}
