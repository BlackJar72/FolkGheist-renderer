//Copyright (C) Jared Blackburn, Sep 20, 2013
package community.simulation.locales.buildings;

import community.simulation.map.Location;
import community.simulation.Simulation;
import java.awt.geom.Rectangle2D;
import community.simulation.citizens.Citizen;
import community.simulation.locales.Employer;
import community.simulation.locales.Locale;
import community.simulation.locales.functions.GenHome;
import community.ui.models.Model;
import community.ui.models.Models;

/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */
public final class GenericHome extends Locale {
    Model model = Models.getModel("house1");
    
    
    public GenericHome(Simulation where) {
        super(where);
        job     = null;
        house   = new GenHome();
        fun     = null;
        
        //To be changed!
        complete    = true;
        changed     = false;
        fulfillment = null;
        //Details; random (for now? this building may not stay, but if so...)
        dimentions = new float[]{6f, 2f, 6f};
    }
    
    
    public GenericHome(Simulation where, Location loc) {
        super(where);
        job     = null;
        house   = new GenHome();
        fun     = null;
        
        //To be changed!
        complete    = true;
        changed     = false;
        fulfillment = null;
        //Details; random (for now? this building may not stay, but if so...)
        dimentions = new float[]{6f, 2f, 6f};
        x = loc.getXfloat();
        y = loc.getYfloat();
        z = loc.getZfloat();
        r = loc.getRotate();
        makeRect();        
    }
    
    
    @Override
    public void tick() {
        if(!changed) return;
        //TDOD: Stuff?
        return;        
    }
    
    
    @Override
    public void draw() {
        model.draw(this);        
    }
    
    
    @Override
    public void beVisited(Citizen visitor) {
        if(!house.isFull() && visitor.getHome() == null) {
            visitor.setHome(this);
            house.setFull(house.incPop());
            house.updateAvailability();
        }
    }



}
