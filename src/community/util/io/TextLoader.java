package community.util.io;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import community.util.HouseKeeping;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author jared
 */
public class TextLoader {
    
    
    public static String loadText(String path) {
        StringBuilder text;
        BufferedReader reader;
        String line;        
        try {
            text = new StringBuilder();
            System.out.println("Reading file: " + path);
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
