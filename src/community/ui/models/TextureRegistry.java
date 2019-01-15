/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package community.ui.models;

import java.util.HashMap;
import static org.lwjgl.opengl.GL11.glGenTextures;

/**
 *
 * @author jared
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
