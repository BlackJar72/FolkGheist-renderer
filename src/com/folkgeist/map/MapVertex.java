//Copyright JaredBlackburn (c) 2014
package com.folkgeist.map;

import com.folkgeist.util.math.Vec3f;

/**
 *
 * @author JaredBGreat (Jared Blackburn)
 */

public class MapVertex extends Vec3f {
    
    
    MapVertex(float x, float y, float z) {
        super(x, y, z);
    }
    

    public float getX() {
        return x;
    }
    

    public void setX(float x) {
        this.x = x;
    }
    

    public float getY() {
        return y;
    }
    

    public void setY(float y) {
        this.y = y;
    }
    

    public float getZ() {
        return z;
    }
    

    public void setZ(float z) {
        this.z = z;
    }
    
    
}
