//Copyright (C) Jared Blackburn, 2014
package com.folkgeist.logic.place;

import com.folkgeist.ui.models.Model;
import com.folkgeist.ui.models.Models;
import static com.folkgeist.ui.models.Models.*;

/**
 * 
 * @author JaredBGreat (Jared Blackburn)
 */
public class Building {
    Model model;
    
    float width;  // x dimension
    float depth;  // z dimension
    float height; // y dimension
    
    int maxPopulation;
    int maxJobs;
    int maxVisitors;
    int maxFamilies;
    
    int payLevel;
    int QoL; // Quality of Life
    int visitCost;
    int residenceCost;
    

    public Building(Model model, float width, float depth, float height, int maxPopulation, 
            int maxJobs, int maxVisitors, int maxFamilies, int payLevel, 
            int QoL, int visitCost, int residenceCost) {        
        this.width = width;
        this.depth = depth;
        this.height = height;
        this.maxPopulation = maxPopulation;
        this.maxJobs = maxJobs;
        this.maxVisitors = maxVisitors;
        this.maxFamilies = maxFamilies;
        this.payLevel = payLevel;
        this.QoL = QoL;
        this.visitCost = visitCost;
        this.residenceCost = residenceCost;
    }
    
    
    /****************************************************************
    **                       Actual buildings                      **
    *************************************************************** */
    public static final Building basicHome = new Building(getModel("house1"), 6f, 6f, 2f, 4, 0,   0, 1, 0, 3, 0, 2);
    //public static final Building basicEmpl = new Building(8f, 8f, 3f, 16, 16, 0, 0, 3, 0, 0, 0);
    //public static final Building basicRec  = new Building(6f, 6f, 3f, 10, 2,  8, 0, 2, 4, 2, 0);
    
    
    
    
    
    
    /****************************************************************
    **                      Utility Functions                      **
    *************************************************************** */
    
    
    boolean isEmployer() {
        return maxJobs > 0;
    }
    
    
    boolean isRecreation() {
        return maxVisitors > 0;
    }
    
    
    boolean isHome() {
        return (maxJobs + maxVisitors) < maxPopulation;
    }
        
}
