//Copyright (C) Jared Blackburn, Oct 18, 2013
package community.simulation;

/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */
public interface Selectable {
    public void select();
    public void deselect();
    public void setSelected(boolean s);
    public boolean isSelected();
}
