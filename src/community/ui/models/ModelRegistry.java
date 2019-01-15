/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package community.ui.models;

import java.util.HashMap;

/**
 *
 * @author jared
 */
public class ModelRegistry extends HashMap<String, Model> {
    
    protected void add(String name, Model model) {
        super.put(name, model);
    }
    
}
