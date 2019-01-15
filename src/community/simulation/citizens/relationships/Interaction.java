package community.simulation.citizens.relationships;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import community.util.BonusHelper;
import community.simulation.citizens.*;
import community.simulation.citizens.enums.*;


/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */

//Should this be a separate, method-holding class, or part of relationship?
public class Interaction {
    
    
    boolean meet(Citizen c1, Citizen c2) {
        if(c1 == null || c2 == null) {
            System.err.println("Encounter with null citizen!");
            return false;
        } else if(c1 == c2) {
            System.err.println("Citizen is having an encounter with self!");
            return false;
        }
        if(c1.getID() > c2.getID()) {
            Citizen c3 = c1;
            c1 = c2;
            c2 = c3;
        }
        if(c1.relationships.knows(c2)) 
            greet(c1.relationships.getRelationship(c2));
        else firstMeet(c1, c2);
        return true;        
    }
    
    
    boolean firstMeet(Citizen c1, Citizen c2) {
        if(c1 == null || c2 == null) {
            System.err.println("Trying to form a relationship with null!");
            return false;
        } else if(c1 == c2) {
            System.err.println("Citizen trying to meet and start relationship "
                    + "with self!");
            return false;
        }
        //Proper insertation of new relationship is done their constructor;
        //Here they only need to be created.
        if(c1.getID() < c2.getID()) new Relationship(c1, c2);
        else new Relationship(c2, c1);
        return true;
    }    
    
    
    boolean greet(Relationship rel) {
        if(rel == null) {
            System.err.println("Interaction based on null relationship!");
            return false;
        }
        rel.per1.incFamiliarity();
        rel.per2.incFamiliarity();
        int x = rel.per1.object.personality.similarity(rel.per2.object.personality);
        x = (BonusHelper.get((byte)x) / 2) + 1;
        rel.per1.modDisposition(x);
        rel.per2.modDisposition(x);
        rel.per1.subject.state.modSocial((byte) x, true);
        rel.per2.subject.state.modSocial((byte) x, true);
        return true;
    }
    
    
    boolean talk(Relationship rel, boolean lowSpeaker) {
        // This needs to be re-written from scratch to differentiate 
        // the speaker from the listen in each round of converstion.
        if(rel == null) {
            System.err.println("Interaction based on null relationship!");
            return false;
        }
        int x = BonusHelper.get(rel.per1.subject.personality
                .similarity(rel.per2.object.personality));
        x += BonusHelper.get(rel.per1.subject.personality.getExtro());
        x += BonusHelper.get(rel.per1.subject.personality.getAgreeable());
        x += BonusHelper.get(rel.per2.subject.personality.getExtro());
        x += BonusHelper.get(rel.per2.subject.personality.getAgreeable()); 
        x = (int) (Math.random() * (double) (x + 16) - 8);
        if(lowSpeaker) {
            x += BonusHelper.get(rel.per1.subject.personality.getExtro());
            x += BonusHelper.get(rel.per2.subject.personality.getAgreeable());
            //TODO: Topic Effects; probably a separate method
            x /= 4;
            rel.per1.modDisposition(x);
            rel.per1.subject.state.modSocial((byte) x, true);
        } else {
            x += BonusHelper.get(rel.per2.subject.personality.getExtro());
            x += BonusHelper.get(rel.per1.subject.personality.getAgreeable());
            //TODO: Topic Effects; probably a separate method
            x /= 4;
            rel.per2.modDisposition(x);
            rel.per2.subject.state.modSocial((byte) x, true);
        }
        return true; 
    }
    
    
    boolean listen(Relationship rel, boolean lowSpeaker) {
        // This needs to be re-written from scratch to differentiate 
        // the speaker from the listen in each round of converstion.
        if(rel == null) {
            System.err.println("Interaction based on null relationship!");
            return false;
        }
        int x = BonusHelper.get(rel.per1.subject.personality
                .similarity(rel.per2.object.personality));
        x += BonusHelper.get(rel.per1.subject.personality.getExtro());
        x += BonusHelper.get(rel.per1.subject.personality.getAgreeable());
        x += BonusHelper.get(rel.per2.subject.personality.getExtro());
        x += BonusHelper.get(rel.per2.subject.personality.getAgreeable()); 
        x = (int) (Math.random() * (double) (x + 16) - 8);
        //TODO: Topic Effects; probably a separate method
        if(lowSpeaker) {
            rel.per2.modDisposition(x / 4);            
            rel.per2.subject.state.modSocial((byte) (x / 6), true);
        }
        else {
            rel.per1.modDisposition(x / 4);
            rel.per1.subject.state.modSocial((byte) (x / 6), true);
        }          
        return true; 
    }
    
/*
    // There's a good chance I don't need this.  A better way is to have
    // citizens in the same place itterate through with chnace of conversing.
        
    boolean play(Relationship rel) {
        if(rel == null) {
            System.err.println("Interaction based on null relationship!");
            return false;
        }
        int x = (int) (Math.random() * 24.0);
        x += (BonusHelper.get(rel.per1.subject.personality
                .similarity(rel.per2.object.personality)) * 2);
        x += BonusHelper.get(rel.per1.subject.personality.getExtro());
        x += (BonusHelper.get(rel.per1.subject.personality.getAgreeable()) / 2);
        x += BonusHelper.get(rel.per2.subject.personality.getExtro());
        x += (BonusHelper.get(rel.per2.subject.personality.getAgreeable()) / 2); 
        int y = x / 2;
        y += BonusHelper.get(rel.per1.subject.personality.getExtro());
        y += (BonusHelper.get(rel.per1.subject.personality.getAgreeable()) / 2);
        y /= 4;
        rel.per2.modDisposition(x); 
        rel.per2.subject.state.modSocial((byte) x, true);
        y = x / 2;
        y += BonusHelper.get(rel.per2.subject.personality.getExtro());
        y += (BonusHelper.get(rel.per2.subject.personality.getAgreeable()) / 2);
        y /= 4;
        rel.per1.modDisposition(x);
        rel.per1.subject.state.modSocial((byte) x, true);
        return true; 
    }
    
    
    boolean cowork(Relationship rel) {
        if(rel == null) {
            System.err.println("Interaction based on null relationship!");
            return false;
        }
        int x = (int) (Math.random() * 16.0) - 6;
        x += (BonusHelper.get(rel.per1.subject.personality.similarity(rel.per2.object.personality)) * 3);
        int y = x;
        y += (BonusHelper.get(rel.per1.subject.personality.getExtro()) / 2);
        y += (BonusHelper.get(rel.per1.subject.personality.getConscience()) / 2);
        y += BonusHelper.get(rel.per1.subject.personality.getAgreeable()); 
        y += (BonusHelper.get(rel.per2.subject.personality.getAgreeable()) / 2); 
        y /= 4;
        rel.per2.modDisposition(x);
        rel.per2.subject.state.modSocial((byte) (x /2), true);
        y = x;
        y += (BonusHelper.get(rel.per2.subject.personality.getExtro()) / 2);
        y += (BonusHelper.get(rel.per2.subject.personality.getConscience()) / 2);
        y += BonusHelper.get(rel.per2.subject.personality.getAgreeable()); 
        y += (BonusHelper.get(rel.per1.subject.personality.getAgreeable()) / 2); 
        y /= 4;
        rel.per1.modDisposition(x);
        rel.per1.subject.state.modSocial((byte) (x /2), true);
        return true; 
    }
    
    
    boolean argue (Relationship rel) {
        if(rel == null) {
            System.err.println("Interaction based on null relationship!");
            return false;
        }
        int x = (int) (Math.random() * 16.0) - 24;
        x += BonusHelper.get(rel.per1.subject.personality.similarity(rel.per2.object.personality));
        int y = x;
        y -= (BonusHelper.get(rel.per2.subject.personality.getNeurotic()) / 2);
        y += BonusHelper.get(rel.per2.subject.personality.getAgreeable()); 
        y /= 4;
        rel.per2.modDisposition(y);
        rel.per2.subject.state.modSocial((byte) y, true);
        y = x;
        y -= (BonusHelper.get(rel.per1.subject.personality.getNeurotic()) / 2);
        y += BonusHelper.get(rel.per1.subject.personality.getAgreeable());  
        y /= 4;
        rel.per1.modDisposition(y);
        rel.per1.subject.state.modSocial((byte) y, true);
        return true; 
    }
*/
    
    // Conflictual rations, including victimization and arrests
    boolean fight(Relationship rel) {
        if(rel == null) {
            System.err.println("Interaction based on null relationship!");
            return false;
        }
        int x = (int) (Math.random() * 16.0) - 24;
        x += BonusHelper.get(rel.per1.subject.personality.similarity(rel.per2.object.personality));
        int y = x;
        y -= (BonusHelper.get(rel.per2.subject.personality.getNeurotic()) / 2);
        y += BonusHelper.get(rel.per2.subject.personality.getAgreeable()); 
        y /= 2;
        rel.per2.modDisposition(y);
        rel.per1.subject.state.modHealth((byte) y, false);
        rel.per2.subject.state.modSocial((byte) y, true);
        y = x;
        y -= (BonusHelper.get(rel.per1.subject.personality.getNeurotic()) / 2);
        y += BonusHelper.get(rel.per1.subject.personality.getAgreeable());  
        y /= 2;
        rel.per1.modDisposition(y);
        rel.per1.subject.state.modHealth((byte) y, false);
        rel.per1.subject.state.modSocial((byte) y, true);
        return true; 
    }
    
}
