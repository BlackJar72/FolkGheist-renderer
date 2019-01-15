//Copyright (C) Jared Blackburn, 2014
package com.folkgeist.ui.models;

import java.util.HashMap;

/**
 *
 * @author JaredBGreat (Jared Blackburn)
 */
public class ModelRegistry extends HashMap<String, Model> {
    
    protected void add(String name, Model model) {
        super.put(name, model);
    }
    
}
