/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package community.simulation.map;

/**
 *
 * @author jared
 */
public class Location implements Locatable {
    protected float x, y, z, r;
    private boolean selected;
    
    
    public Location(float x, float y, float z, float r) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.r = r;
    }
    
    public Location(int x, int y, int z) {
        this.x = (float) x;
        this.y = (float) y;
        this.z = (float) z;
        this.r = (float) r;
    }
    
    public Location() {
        r = x = y = z = 0.0f;
    }
    
    
    public void change(float x, float y, float z) {
        this.x += x;
        this.y += y;
        this.z += z;
    }
    
    
    public void change(float x, float y, float z, float r) {
        this.x += x;
        this.y += y;
        this.z += z;
        this.r  = r;
    }
    
    
    
    
    
    /*****************************************/
    /*        GETTERS & SETTERS              */ 
    /*****************************************/

    
    @Override
    public float getXfloat() {
        return x;
    }
    
    
    @Override
    public float getYfloat() {
        return y;        
    }
    
    
    @Override
    public float getZfloat() {
        return z;
    }
    
    
    @Override
    public float getRotate() {
        return r;
    }
    
    
    @Override
    public int getXint() {
        return Math.round(x);
    }
    
    
    @Override
    public int getYint() {
        return Math.round(y);        
    }
    
    
    @Override
    public int getZint() {
        return Math.round(z);
    }
    
    
    @Override
    public int getRint() {
        return Math.round(z);
    }
    
    
    @Override
    public boolean setX(float x) {
        if((x < 2147483647.0f) && (x > -2147483647.0f)) {
            this.x = x;
            return true;    
        }
        else {
            System.err.println("Trying to set x possion out of range! " +
                    "Requested values of" + x + ".");
            return false;
        }        
    }
    
    
    @Override
    public boolean setY(float y) {
        if((y < 2147483647.0f) && (y > -2147483647.0f)) {
            this.y = y;
            return true;    
        }
        else {
            System.err.println("Trying to set x possion out of range! " +
                    "Requested values of" + y + ".");
            return false;
        }        
    }
    
    
    @Override
    public boolean setZ(float z) {
        if((z < 2147483647.0f) && (z > -2147483647.0f)) {
            this.z = z;
            return true;    
        }
        else {
            System.err.println("Trying to set x possion out of range! " +
                    "Requested values of" + z + ".");
            return false;
        }        
    }
    
    
    @Override
    public void setRotate(float r) {
        if(r > 360f || r < -360f) this.r = (float) (((int) r) % 360 );
        else this.r = r;
    }
    
    
    @Override
    public void setX(int x) {
        this.x = x;
    }
    
    
    @Override
    public void setY(int y) {
        this.y = y;
    }
    
    
    @Override
    public void setZ(int z) {
        this.z = z;
    }
    
    
    @Override
    public void setR(int z) {
        this.r = (r % 360);
    }
    
    
    public Location copy() {
        return new Location(x, y, z, r);
    }
    

    @Override
    public void select() {
        selected = true;
    }
    

    @Override
    public void deselect() {
        selected = false;
    }
    

    @Override
    public void setSelected(boolean s) {
        selected = s;
    }
    

    @Override
    public boolean isSelected() {
        return selected;
    }
    
}
