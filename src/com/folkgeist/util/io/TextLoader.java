//Copyright (C) Jared Blackburn, 2014
package com.folkgeist.util.io;

import com.folkgeist.util.HouseKeeping;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * This class contains static methods for loading text files.
 * 
 * Among other things this is responsible for the IO involved in loading 
 * shaders.
 * 
 * @author JaredBGreat (Jared Blackburn)
 */
public class TextLoader {
    
    
    public static String loadText(String path) {
        StringBuilder text;
        BufferedReader reader;
        String line;        
        try {
            text = new StringBuilder();
            HouseKeeping.reportLog("Reading file: " + path);
            reader = new BufferedReader(new FileReader(path));
            while((line = reader.readLine()) != null) {
                text.append(line).append("\n");
                //System.out.println(line);
            }
            reader.close();
        } catch(Exception ex) {
            HouseKeeping.reportError("Error in reading file: " + path, 
                    1, ex, false);
            return null;
        }      
        return text.toString();
    }
    
    
    public static String loadShader(String name) {
        return loadText("res" + File.separator + "shaders" 
                + File.separator + name);
    }
   
    
}
