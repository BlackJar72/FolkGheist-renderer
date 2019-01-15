//Copyright (C) Jared Blackburn, Sep 20, 2013
package community.simulation.locales.buildings;


import community.simulation.map.Location;
import community.simulation.Simulation;
import java.awt.geom.Rectangle2D;
import community.simulation.citizens.Citizen;
import java.awt.Rectangle;
import community.simulation.locales.functions.GenEmployer;
import community.simulation.locales.Locale;
import community.util.math.Util;
import static community.ui.test.TestingStuff.*;


/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */
public final class GenericEmployer extends Locale {
    
    
    public GenericEmployer(Simulation where, Location tile) {
        super(where);
        job     = new GenEmployer();
        house   = null;
        fun     = null;
        
        //To be changed!
        complete    = true;
        changed     = false;
        fulfillment = null;
        //Details; random (for now? this building may not stay, but if so...)
        GenEmpMake(tile);
    }
    
    
    public GenericEmployer(Simulation where) {
        super(where);
        job     = new GenEmployer();
        house   = null;
        fun     = null;
        
        //To be changed!
        complete    = true;
        changed     = false;
        fulfillment = null;
        //Details; random (for now? this building may not stay, but if so...)
        GenEmpMake();
    }
    
    
    public void GenEmpMake() {
//        boolean goodLocation = true;
//         {
            x = (float) ((int)(Math.random() * 128f)) - 64;
            y = 0.0f;
            z = (float) ((int)(Math.random() * 128f)) - 64;
    //        x = (float) ((int)(Math.random() * 512f)) - 256;
    //        y = 0.0f;
    //        z = (float) ((int)(Math.random() * 512f)) - 256;
            r = ((int)(Math.random() * 4)) * 90f;
            dimentions = new float[]{8f, 3f, 8f};
            // This below should be in its own function under MapLocale.
            i = j = 0;
            bounds[j++] = x + (((float) dimentions[i]) / 2f);
            bounds[j++] = x - (((float) dimentions[i]) / 2f);
            i++;
            bounds[j++] = y + (((float) dimentions[i]) / 2f);
            bounds[j++] = y - (((float) dimentions[i]) / 2f);
            i++;
            bounds[j++] = z + (((float) dimentions[i]) / 2f);
            bounds[j]   = z - (((float) dimentions[i]) / 2f);    
            footprint = new Rectangle2D.Float(bounds[1], 
                bounds[4], dimentions[0], dimentions[2]);
//            for(MapLocale loc: this.city.places) {
//                if(goodLocation && (loc != this)) 
//                    goodLocation = !Util.testOverlap(this, loc);
//            }
//        } while(!goodLocation);
    }
    
    
    public void GenEmpMake(Location tile) {
//        boolean goodLocation = true;
//         {
            x = tile.getXfloat();
            y = 0.0f;
            z = tile.getZfloat();
    //        x = (float) ((int)(Math.random() * 512f)) - 256;
    //        y = 0.0f;
    //        z = (float) ((int)(Math.random() * 512f)) - 256;
            r = ((int)(Math.random() * 4)) * 90f;
            dimentions = new float[]{8f, 3f, 8f};
            // This below should be in its own function under MapLocale.
            i = j = 0;
            bounds[j++] = x + (((float) dimentions[i]) / 2f);
            bounds[j++] = x - (((float) dimentions[i]) / 2f);
            i++;
            bounds[j++] = y + (((float) dimentions[i]) / 2f);
            bounds[j++] = y - (((float) dimentions[i]) / 2f);
            i++;
            bounds[j++] = z + (((float) dimentions[i]) / 2f);
            bounds[j]   = z - (((float) dimentions[i]) / 2f);    
            footprint = new Rectangle2D.Float(bounds[1], 
                bounds[4], dimentions[0], dimentions[2]);
//            for(MapLocale loc: this.city.places) {
//                if(goodLocation && (loc != this)) 
//                    goodLocation = !Util.testOverlap(this, loc);
//            }
//        } while(!goodLocation);
    }
    
    
    @Override
    public void beVisited(Citizen visitor) {
        if(!job.isFull() && visitor.getEmployer() == null) {
            visitor.setEmployer(this);
            visitor.getSes().setIncome(job.getPayLevel());
            job.setFull(job.incJobs());
        }
//        if(visitor.getEmployer() == this) {
//            visitor.getState().modQoL(job.getQoLbonus(), true);
//        }
    }
    
    
    @Override
    public void tick() {
        if(!changed) return;
        //TDOD: Stuff?
        return;        
    }
    
    
    @Override
    public void draw() {
        drawGenEmployer(this);        
    }
    
    
    

    
}
