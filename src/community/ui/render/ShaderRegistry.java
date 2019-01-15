package community.ui.render;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import community.util.io.TextLoader;
import community.ui.models.Model;
import java.util.HashMap;

/**
 *
 * @author jared
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
                "special(not)", 2);
        shaders.loadShader("ghostVerts.glvs", "ghostFrags.glfs", 
                "special(not)", 3);
        // Set models to use these 
        Model.setPrimaryShader(primary);
        Model.setSecondaryShader(secondary);
        //Any additional shaders below, using rand of 0
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
