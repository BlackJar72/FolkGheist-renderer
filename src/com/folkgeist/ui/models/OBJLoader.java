//Copyright (C) Jared Blackburn, 2014
package com.folkgeist.ui.models;

import com.folkgeist.util.HouseKeeping;
import com.folkgeist.util.math.Vec2f;
import com.folkgeist.util.math.Vec3f;
import com.folkgeist.util.math.Vec3i;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * A utility class for loading meshes.  This does not convert them to models.
 * 
 * @author JaredBGreat (Jared Blackburn)
 */
public class OBJLoader {
    
    
    public static Mesh loadModel(String file) {              
        return loadModel(file, 1f);        
    }
    
    
    public static Mesh loadModel(String name, float scale) {
        float xf, yf, zf;
        int   xi, yi, zi;
        Mesh model = new Mesh();
        File file = new File("res" + File.separator 
                    + "models" + File.separator + name);
        //System.out.println("Loading mesh " + file);
        String line;
        try {
            HouseKeeping.reportLog("Loading mesh from " + file.getPath());
            BufferedReader in = new BufferedReader(new FileReader(file));            
            while((line = in.readLine()) != null) {
                //System.out.println(line);
                if(line.startsWith("v ")) {
                    xf = Float.valueOf(line.split(" ")[1]) * scale;
                    yf = Float.valueOf(line.split(" ")[2]) * scale;
                    zf = Float.valueOf(line.split(" ")[3]) * scale;
                    model.vertices.add(new Vec3f(xf, yf, zf));
                } else if(line.startsWith("vn ")) {
                    xf = Float.valueOf(line.split(" ")[1]);
                    yf = Float.valueOf(line.split(" ")[2]);
                    zf = Float.valueOf(line.split(" ")[3]);
                    model.normals.add(new Vec3f(xf, yf, zf));
                } else if(line.startsWith("vt ")) {
                    xf = Float.valueOf(line.split(" ")[1]);
                    yf = Float.valueOf(line.split(" ")[2]);
                    model.uvcoords.add(new Vec2f(xf, yf));
                } else if(line.startsWith("f ")) {
                    Vec3i vertIdx = new Vec3i(
                            Integer.valueOf(line.split(" ")[1].split("/")[0]), 
                            Integer.valueOf(line.split(" ")[2].split("/")[0]),
                            Integer.valueOf(line.split(" ")[3].split("/")[0]));
                    Vec3i uvIdx   = new Vec3i(
                            Integer.valueOf(line.split(" ")[1].split("/")[1]), 
                            Integer.valueOf(line.split(" ")[2].split("/")[1]),
                            Integer.valueOf(line.split(" ")[3].split("/")[1]));
                    Vec3i normIdx = new Vec3i(
                            Integer.valueOf(line.split(" ")[1].split("/")[2]), 
                            Integer.valueOf(line.split(" ")[2].split("/")[2]),
                            Integer.valueOf(line.split(" ")[3].split("/")[2]));
                    model.faces.add(new Face(vertIdx, uvIdx, normIdx));
                }
            }            
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(OBJLoader.class.getName()).log(Level.SEVERE, null, ex);
            HouseKeeping.reportError("Model " + name 
                    + " was not read correctly", 2, ex, true);
        }        
        return model;        
    }    
}
