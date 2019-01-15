//Copyright (C) Jared Blackburn, Sep 20, 2013
package community.simulation.citizens.enums;

/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */
public enum LifeStage {
    INFANT  (0,  0,       7),
    CHILD   (1,  7,      64),
    ADULT   (2,  64,    200),
    OLD     (3,  200,   255),
    DEAD    (-1, 255,    -1);
    
    private final int id;
    private final int begin;
    private final int end;
    
    LifeStage(int id, int begin, int end) {
        this.id = id;
        this.begin = begin;
        this.end = end;
    }
    
}
