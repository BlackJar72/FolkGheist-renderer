// Copyright Jared Blackburn (c) 2014
package community.simulation.locales.buildings;


import community.simulation.locales.Locale;
import java.util.HashMap;


public class LocaleRegistry extends HashMap<String, Class> {
    private static final LocaleRegistry registry = new LocaleRegistry();
    
    
    public static void add(String name, Class building) {
        registry.put(name, building);
    }
    
    
    public static void add(String name, Locale building) {
        registry.put(name, building.getClass());
    }
    
    
    public static Class getLocale(String name) {
        return registry.get(name);
    }
    
    
    public static void initLocales() {
        // TODO: Register all building types here
    }
    
    
    
}
