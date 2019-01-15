//Copyright (C) Jared Blackburn, 2014
package com.folkgeist.ui.models;

/**
 * This class contains registries for all meshes, textures, and models (mesh /
 * texture combinations) used.  In addition, it contains a central method for
 * acquiring models and for initializing the the models (meaning, loading all
 * components, assembling the models, and registering them); this is the 
 * central controller and coordinator of the model package, as well as the
 * clearinghouse for all models.
 * 
 * @author JaredBGreat (Jared Blackburn)
 */
public class Models {
    private static final MeshRegistry meshes = new MeshRegistry();
    private static final TextureRegistry textures = new TextureRegistry();
    private static final ModelRegistry models = new ModelRegistry();
    
    
    public static void initModelData() {
        addMeshes();
        addTextures();
        addModels();
    }
    
    
    public static Model getModel(String name) {
        return models.get(name);
    }
    
    
    private static void addMeshes() {
        meshes.add("building1", "Building1b.obj", 4.0f);
        meshes.add("illegalHouse", "house1.obj", 0.5f);
    }
    
    
    private static void addTextures() {
        textures.add("grass", "grass_texture1287b.jpg");
        textures.add("grassNM", "grass_texture1287b-nm.jpg");
        textures.add("grassSP", "grass_texture1287b-spec.jpg");
        textures.add("building1aNM", "Building11-normal.jpg");
        textures.add("building1a", "Building11.png");
        textures.add("building1SP", "Building11-spec.jpg");
        textures.add("illegalHouse", "house1.png");
        textures.add("illegalHouseNM", "house1-nm.png");
        textures.add("illegalHouseSP", "house1-spec.png");
        
    }
    
    
    private static void addModels() {
        models.add("building1", new Model(meshes.get("building1"),
                textures.getID("building1a"),
                textures.getID("building1aNM"),
                textures.getID("building1SP")));
        models.add("house1", new Model(meshes.get("illegalHouse"),
                textures.getID("illegalHouse"),
                textures.getID("illegalHouseNM"),
                textures.getID("illegalHouseSP")));
    }
    
    
    public static int getTexture(String name) {
        return textures.getID(name);
    }
    
}
