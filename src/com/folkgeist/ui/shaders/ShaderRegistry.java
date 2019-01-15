//Copyright (C) Jared Blackburn, 2014
package com.folkgeist.ui.shaders;

import com.folkgeist.ui.models.Model;
import com.folkgeist.util.io.TextLoader;
import java.util.HashMap;

/**
 *
 * @author JaredBGreat (Jared Blackburn)
 */
public class ShaderRegistry extends HashMap<String, Shader> {
    public static final int GL_DEFAULT = 0;
    protected static int primary = 0, secondary = 0;
    public static ShaderRegistry shaders = new ShaderRegistry();
    
    
    public static void initShaderData() {        
        // Basic Shaders
        shaders.loadShader("basicVerts.glvs", "basicFrags.glfs", 
                "usual", 1);
        shaders.loadShader("selVerts.glvs", "selFrags.glfs", 
                "selected", 2);
        shaders.loadShader("ghostVerts.glvs", "ghostFrags.glfs", 
                "ghosted", 3);
        // Set models to use these 
        Model.setPrimaryShader(primary);
        Model.setSecondaryShader(secondary);
        //Any additional shaders below, using rank of 0
    }
    
    
    private void loadShader(String vert, String frag, String name, int rank) {
        String vertcode = TextLoader.loadShader(vert);
        String fragcode = TextLoader.loadShader(frag);
        add(vertcode, fragcode, name);
        switch(rank) {
            case 1:
                primary = this.getShader(name);
                return;
            case 2:
                secondary = this.getShader(name);
                return;
            default: return; 
        }
    }
    
    
    public void add(String vert, String frag, String name) {
        this.put(name, new Shader(vert, frag, name));
    }
    
    
    public static int getShader(String name) {
        return shaders.get(name).getId();
    }
    
    
    public static int getPrimary() {
        return primary;
    }
    
    
    public static int getSecondary() {
        return secondary;
    }
}
