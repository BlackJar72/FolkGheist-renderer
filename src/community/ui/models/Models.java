package community.ui.models;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jared
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
    
}
