package community.simulation.citizens.relationships;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import community.simulation.citizens.Citizen;


/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */
public class Relationship {
    //Perspectives of first and second citizen in order in the Populous 
    //ArrayList; i.e., per1 for Citizen with lower ID, per2 for higher ID.
    protected Relating per1, per2;
    
    
    public Relationship(Citizen lowID, Citizen highID) {
        per1 = new Relating(lowID, highID);
        per2 = new Relating(highID, lowID);
        per2.subject.getRelation().put(per1.object.getID(), this);
        per1.object.getRelation().put(per2.object.getID(), this);
    }
    
}
