/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package community.ui.models;

import community.HouseKeeping;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL12;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;
import static org.lwjgl.opengl.GL13.GL_CLAMP_TO_BORDER;

/**
 *
 * @author jared
 */
public class TextureLoader {
    
    public static void loadImage(String name, int glID) {
        try {
            // Get image data
            int pixel;
            File file = new File("res" + File.separator 
                    + "textures" + File.separator + name);
            BufferedImage img = ImageIO.read(file);
            //System.out.println("Loading texure " + file);
            ByteBuffer bytes = BufferUtils.createByteBuffer(img.getWidth() 
                            * img.getHeight() * 4);            
            for(int i = 0; i < img.getWidth(); i++)
                for(int j = img.getHeight() - 1; j >= 0 ; j--) {
                    pixel = img.getRGB(i, j);
                    bytes.put((byte)((pixel >> 16) & 0xff));
                    bytes.put((byte)((pixel >> 8) & 0xff));
                    bytes.put((byte)(pixel & 0xff));
                    bytes.put((byte)((pixel >> 24) & 0xff));
                }
            bytes.flip(); 
            // Prepare image and send to OpenGL
            glBindTexture(GL_TEXTURE_2D, glID);  
            
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, img.getWidth(), 
                    img.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, bytes);
            
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);        
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR); 
            
            glBindTexture( GL_TEXTURE_2D, 0);
            
        } catch (IOException ex) {
            Logger.getLogger(TextureLoader.class.getName()).log(Level.SEVERE, null, ex);
            HouseKeeping.reportError("Failed to load image " + name, 2, true, ex);
        }  
    }
    
}
