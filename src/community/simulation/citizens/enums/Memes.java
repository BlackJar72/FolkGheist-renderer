package community.simulation.citizens.enums;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.EnumSet;


/**
 *
 * @author jared
 */
public enum Memes {
    
    GOD (0),
    FAITH (1),
    ATHEIST (2),
    SKEPTIC (3),
    INDIVIDUALIST (4),
    COLLECTIVIST(5),
    CAPITALIST (6),
    SOCIALIST (7),
    COMMUNAL (8),
    CONSUMERIST (9),
    COUNTERCULTURE (10),
    AUTHORITY (11),
    LIBERTY (12),
    EQUALITY (13),
    NATURE (14),
    SIMPLICITY (15),
    PROGRESS (16),
    HELPFUL (17),           // Kindness or humanitarian
    HONOR (18),
    MANERS (19),
    KNOWLEDGE (20),
    WISDOM (21),
    ART (22),
    CUTTHROAT (23),
    WORK (24),
    PRACTICAL (25),
    LUXURY (26),
    HEDONIST (27),
    PASIFICIST (28),
    REBEL (29),
    CONFORMITY (30),
    PRUDE (31),
    FUCKIT (32),
    //TODO: More???
    last (255); //Placeholder, not used! (Delete when done!)
    
    
    
    public final int id;
    
    public static final EnumSet<Memes> MEMES = EnumSet.range(GOD, FUCKIT);
    
    Memes(int id) {
        this.id = id;        
    }
}
