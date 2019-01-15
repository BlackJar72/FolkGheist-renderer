package community.simulation.citizens;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author jared
 */
public class Personality {
    private static final int numTraits = 5;
    private static final double distFactor = 0.445466667393;
    private byte[] traits = new byte[numTraits]; 
    
    
    public Personality() {
        for(int i = 0; i < numTraits; i++) {
            traits[i] = (byte) (Math.random() * 64 +
                                Math.random() * 64 +
                                Math.random() * 64 +
                                Math.random() * 64 - 128d);
        }
    }
    
    
    public Personality(byte n, byte e, byte o, byte c, byte a) {
        traits[0] = n;
        traits[1] = e;
        traits[2] = o;
        traits[3] = c;
        traits[4] = a;
    }
    
    public Personality(byte[] traits) {
        System.arraycopy(traits, 0, this.traits, 0, numTraits);
    } 
    
    
    public byte getNeurotic() {
        return traits[0];
    }
    
    public byte getExtro() {
        return traits[1];
    }
    
    public byte getOpen() {
        return traits[2];
    }
    
    public byte getConscience() {
        return traits[3];
    }
    
    public byte getAgreeable() {
        return traits[4];
    }
    
    
    public void setNeurotic(byte val) {
        traits[0] = val;
    }
    
    public void setExtro(byte val) {
        traits[1] = val;
    }
    
    public void setOpen(byte val) {
        traits[2] = val;
    }
    
    public void setConscience(byte val) {
        traits[3] = val;
    }

    
    public void setAgreeable(byte val) {
        traits[5] = val;
    }
    
    
    public byte similarity(Personality other) {
        double x = 0.0;
        int y = 0;
        for(int i = 0; i < numTraits; i++) {
            y = (traits[i] - other.traits[i]);
            x += (y * y);
        }
            x = (Math.sqrt(x) * distFactor);
            return (byte) (127 - Math.round(x));
    }
            
            
}
