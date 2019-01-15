//Copyright JaredBlackburn (c) 2014
package com.folkgeist.map;

import com.folkgeist.util.math.Vec2f;

/**
 *
 * @author JaredBGreat (Jared Blackburn)
 */
public class MapTile {
    int region = -1; // Initializing and not in any region
    int sectX, sectZ;
    float height;
    boolean buildable;
    final static float hyp2 = (float) (Math.sqrt(2) / 2d);
    final static float hyp3 = (float) (Math.sqrt(3) / 3d);
    static final MapVertex up = new MapVertex(0, 1, 0);
    static final MapVertex nn = new MapVertex(0, hyp2, -hyp2);
    static final MapVertex ee = new MapVertex(-hyp2, hyp2, 0);
    static final MapVertex ss = new MapVertex(0, hyp2,  hyp2);
    static final MapVertex ww = new MapVertex(hyp2,  hyp2, 0);
    static final MapVertex ne = new MapVertex(-hyp3,  hyp3, -hyp3);
    static final MapVertex se = new MapVertex(-hyp3,  hyp3,  hyp3);
    static final MapVertex sw = new MapVertex( hyp3,  hyp3,  hyp3);
    static final MapVertex nw = new MapVertex( hyp3,  hyp3,  hyp3);
    final MapVertex[] vertices = new MapVertex[4];
    final MapVertex[] normals  = new MapVertex[4];
    final MapVertex[] tcoords  = new MapVertex[4];
    
    
    MapTile(int x, int y, int z){
        height = (float)y;
        vertices[0] = new MapVertex((float)x + 0.5f, (float)y, (float)z + 0.5f);
        vertices[1] = new MapVertex((float)x + 0.5f, (float)y, (float)z - 0.5f);
        vertices[2] = new MapVertex((float)x - 0.5f, (float)y, (float)z - 0.5f);
        vertices[3] = new MapVertex((float)x - 0.5f, (float)y, (float)z + 0.5f);
        sectX = x + MapSector.HALF;
        sectZ = z + MapSector.HALF;
        // Find a better way to do this
        defautNormals();
        texCoords(x, z);
    }
    
    
    MapTile(int x, int z, int y0, int y1, int y2, int y3){
        height = ((float)y0 + (float)y1 + (float)y2 + (float)y3) / 4f;
        vertices[0] = new MapVertex((float)x + 0.5f, (float)y0, (float)z + 0.5f);
        vertices[1] = new MapVertex((float)x + 0.5f, (float)y1, (float)z - 0.5f);
        vertices[2] = new MapVertex((float)x - 0.5f, (float)y2, (float)z - 0.5f);
        vertices[3] = new MapVertex((float)x - 0.5f, (float)y3, (float)z + 0.5f);
        sectX = x + MapSector.HALF;
        sectZ = z + MapSector.HALF;
        // Find a better way to do this
        defautNormals();
        texCoords(x, z);
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
    
    
    protected final void defautNormals() {
        //This should be down in a better way
        normals[0] = normals[1] = normals[2] = normals[3] = up;
    }
    
    
    protected final void texCoords(int x, int y) {
        tcoords[0] = new MapVertex( ((float)x)        / MapSector.SIZE, 
                                    ((float)y)        / MapSector.SIZE, 0);
        tcoords[1] = new MapVertex( ((float)x)        / MapSector.SIZE, 
                                   (((float)y) - 1)   / MapSector.SIZE, 0);
        tcoords[2] = new MapVertex((((float)x) - 1)   / MapSector.SIZE, 
                                   (((float)y) - 1)   / MapSector.SIZE, 0);
        tcoords[3] = new MapVertex((((float)x) - 1)   / MapSector.SIZE, 
                                    ((float)y)        / MapSector.SIZE, 0);
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
    

    public MapVertex[] getNormals() {
        return normals;
    }   
    

    public MapVertex getNormal(int vert) {
        return normals[vert];
    }
    

    public MapVertex[] getTcoords() {
        return tcoords;
    }   
    

    public MapVertex getTcoord(int vert) {
        return tcoords[vert];
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
