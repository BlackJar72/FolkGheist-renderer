package community.ui.render;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import community.util.HouseKeeping;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

/**
 *
 * @author jared
 */
public class Shader {
    private int program;
    private int vertprog, fragprog;
    
    public Shader(String vertcode, String fragcode, String name) {
        program = glCreateProgram();
        vertprog = glCreateShader(GL_VERTEX_SHADER);
        fragprog = glCreateShader(GL_FRAGMENT_SHADER);
        if((program == 0) || (vertprog == 0) || (fragprog == 0)) {
            HouseKeeping.reportError("Failded to create memory for shader " 
                    + name , 1, true);
        }
        compile(vertprog, vertcode, "(vertex) " + name);
        compile(fragprog, fragcode, "(fragment) " + name);
        glLinkProgram(program);
        if(glGetShader(program, GL_LINK_STATUS) == GL_FALSE) {
            HouseKeeping.reportError("\nShader " + name + " failed to link\n"
                    + glGetShaderInfoLog(program, 1024) + "\n", 
                    1, true);
        }
        glValidateProgram(program);        
    }
    
    
    private void compile(int id, String code, String name) {
        glShaderSource(id, code);
        glCompileShader(id);
        if(glGetShader(id, GL_COMPILE_STATUS) == GL_FALSE) {
            //System.err.println(code);
            HouseKeeping.reportError("Shader " + name + " failed to compile\n"
                    + glGetShaderInfoLog(id, 1024) + "\nCODE:\n" + code + "\n" , 
                    1, true);
        }
        glAttachShader(program, id);
    }
    
    
    public int getId() {
        return program;
    }
    
    
    @Override
    protected void finalize() throws Throwable {
        glDeleteProgram(program);
        glDeleteShader(vertprog);
        glDeleteShader(fragprog);
        super.finalize();        
    }
    
    
}
