/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package community.simulation.citizens.enums;

import java.util.EnumSet;

/**
 *
 * @author jared
 */
public enum Interests {
    
    // THRILLS = adventure, action, excitement, risk
    // ART = art (fine or folk) and crafts, viewing or doing / making
    // LEARNING = gathering knowledge; science, history, trivia, whatever
    // RELIGION = religious or spiritual observances and activities
    // GAME = sports (playing or spectating), gambling, video gaming, etc.
    // MUSIC = any music, performing or listing
    // ENTERTAIN = "Entertainment": TV, movies, plays, drama, reading fiction
    // OUTDOORS = any typically outdoor activity, e.g., hiking, camping
    // RELAX = "Relaxation": calm, peacful activities, chilling out, R&R (rest)
    // TECH = using / making / learning techonlogy; inventing, tinkering, etc.
    
    THRILLS   (0),
    ART       (1),
    LEARNING  (2),
    RELIGION  (3),
    GAMES     (4),
    MUSIC     (5),
    OUTDOORS  (6),
    ENTERTAIN (7),
    RELAX     (8),
    TECH      (9);
    
    private final int id;
    
    public static final EnumSet<Interests> INTERESTS = EnumSet.range(THRILLS, TECH);
    
    Interests(int id) {
        this.id = id;        
    }
    
}
