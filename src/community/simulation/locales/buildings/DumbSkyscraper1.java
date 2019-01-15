package community.simulation.locales.buildings;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import community.simulation.map.Location;
import community.simulation.Simulation;
import community.simulation.citizens.Citizen;
import community.simulation.locales.Locale;
import community.ui.models.Model;
import community.ui.models.Models;
import java.awt.geom.Rectangle2D;


/**
 *
 * @author jared
 */
public class DumbSkyscraper1 extends Locale {
    Model model;
    
    
    public DumbSkyscraper1(Simulation where, Location loc) {
        super(where);
        model = Models.getModel("building1");
        x = loc.getXfloat();
        y = loc.getYfloat();
        z = loc.getZfloat();
        r = loc.getRotate();
        dimentions = new float[]{7f, 28f, 7f};
        makeRect();
    }

    
    @Override
    public void beVisited(Citizen visitor) {
        // Do nothing now
    }
    
    
    @Override
    public void draw() {
        setSelected(false);
        model.draw(this);
    }
    
}
