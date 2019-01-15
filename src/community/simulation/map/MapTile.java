/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package community.simulation.map;

/**
 *
 * @author jared
 */
public class MapTile {
    int region = -1; // Initializing and not in any region
    int sectX, sectZ;
    float height;
    boolean buildable;
    final MapVertex[] vertices = new MapVertex[4];
    
    
    MapTile(int x, int y, int z){
        height = (float)y;
        vertices[0] = new MapVertex((float)x + 0.5f, (float)y, (float)z + 0.5f);
        vertices[1] = new MapVertex((float)x + 0.5f, (float)y, (float)z - 0.5f);
        vertices[2] = new MapVertex((float)x - 0.5f, (float)y, (float)z - 0.5f);
        vertices[3] = new MapVertex((float)x - 0.5f, (float)y, (float)z + 0.5f);
        sectX = x + MapSector.HALF;
        sectZ = z + MapSector.HALF;
    }
    
    
    MapTile(int x, int z, int y0, int y1, int y2, int y3){
        height = ((float)y0 + (float)y1 + (float)y2 + (float)y3) / 4f;
        vertices[0] = new MapVertex((float)x + 0.5f, (float)y0, (float)z + 0.5f);
        vertices[1] = new MapVertex((float)x + 0.5f, (float)y1, (float)z - 0.5f);
        vertices[2] = new MapVertex((float)x - 0.5f, (float)y2, (float)z - 0.5f);
        vertices[3] = new MapVertex((float)x - 0.5f, (float)y3, (float)z + 0.5f);
        sectX = x + MapSector.HALF;
        sectZ = z + MapSector.HALF;
    }

    
    public void setSectZ(int sectZ) {
        this.sectZ = sectZ;
    }
    
    
    public boolean equals(MapTile other) {
//        boolean out = (vertices[0] == other.vertices[0]) 
//                   && (vertices[1] == other.vertices[1]) 
//                   && (vertices[2] == other.vertices[2]) 
//                   && (vertices[3] == other.vertices[3]);
        boolean out = (sectX == other.sectX) && (sectZ == other.sectZ);
        return out;
    }
    
    
    
    
    
    
    //////////////////////////////////////////////////////////////
    //                  GETTERS & SETTERS                       //
    //////////////////////////////////////////////////////////////
    

    public float getHeight() {
        return height;
    }
    
    
    public void setHeight(float height) {
        this.height = height;
        // TODO:  Needs to adjust vertices and re-create sector vbo
    }

    
    public boolean isBuildable() {
        return buildable;
    }
    
    
    public void setBuildable(boolean buildable) {
        this.buildable = buildable;
    }
    

    public MapVertex[] getVertices() {
        return vertices;
    }   
    

    public MapVertex getVertex(int vert) {
        return vertices[vert];
    } 
    
    
    public int getRegion() {
        return region;
    }
    
    
    public void setRegion(int region) {
        this.region = region;
    }
    
    
    public boolean inRegion() {
        return (region >= 0);
    }
    
    
    public boolean inRegion(int region) {
        return (this.region == region);
    }
    
    
    public boolean inRegion(ContiguousRegion region) {
        return (region.contains(this));
    }
    

    public int getSectX() {
        return sectX;
    }
    

    public void setSectX(int sectX) {
        this.sectX = sectX;
    }
    

    public int getSectZ() {
        return sectZ;
    }
}
