//Copyright (C) Jared Blackburn, 2014
package com.folkgeist.ui.shaders;

import com.folkgeist.util.HouseKeeping;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

/**
 *
 * @author JaredBGreat (Jared Blackburn)
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
