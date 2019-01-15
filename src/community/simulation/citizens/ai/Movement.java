/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package community.simulation.citizens.ai;

import community.simulation.map.Location;

/**
 *
 * @author jared
 */
public interface Movement {
 
    public float vX();
//  public float vY();
    public float vZ();
    public float getAngle();
    public boolean update(Location curLoc);
    public boolean isValid();
    public void tick();
    
}
