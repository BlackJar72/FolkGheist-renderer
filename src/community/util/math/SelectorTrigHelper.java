//Copyright (C) Jared Blackburn, Oct 16, 2013
package community.util.math;

/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */
public class SelectorTrigHelper {
    private static final float C = (float) (Math.sin(Math.toRadians(35d)) / 500d);
    
    
    public SelectorTrigHelper() {
    }
    
    
    public float getRadians(float pixels) {
        return (float) Math.asin(pixels * C);
    }
    
    
    public float getDegs(float pixels) {
        return (float) Math.toDegrees(Math.asin(pixels * C));        
    }
}
