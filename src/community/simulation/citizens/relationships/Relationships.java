package community.simulation.citizens.relationships;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import community.simulation.citizens.Citizen;
import community.simulation.citizens.enums.Relation;
import java.util.HashMap;


/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */
public class Relationships extends HashMap<Integer,Relationship> {
    private Citizen holder;
    private int holderID; // For quick access
    private boolean romantic;
    private boolean married;
    private Relation family;
    
    
    public Relationships(Citizen holder) {
        this.holder = holder;
        holderID = holder.getID();
    }
    
    
    public Relating getRelation(int other) {
        Relationship rel;
        rel = get(other);
        if(other < holderID) {
            return rel.per2;
        } else if(other > holderID) {
            return rel.per1;
        } else {
            System.err.println("Error: Citizen " + other + " requests " 
                    + " relationship status with self!");
            return null;
        }
    }
    
    
    boolean knows(Citizen other) {
        return containsKey(other.getID());
    }
    
    
    public Relating getRelating(Citizen other) {
        if(knows(other)) return getRelation(other.getID());
        else {
            System.err.println("Trying to get relationship when none exists! " 
                    + " Returning null.");
            return null;
        }
    }
    
    
    public Relationship getRelationship(Citizen other) {
        return get(other.getID());
    }
    
    
}
