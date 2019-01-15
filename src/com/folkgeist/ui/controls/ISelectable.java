//Copyright (C) Jared Blackburn, Oct 18, 2013
package com.folkgeist.ui.controls;

/**
 *
 * @author JaredBGreat (Jared Blackburn)
 */
public interface ISelectable {
    public void select();
    public void deselect();
    public void setSelected(boolean s);
    public boolean isSelected();
}
