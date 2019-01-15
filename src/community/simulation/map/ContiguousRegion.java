//Copyright (C) Jared Blackburn, Sep 20, 2013
package community.simulation.map;

import java.util.ArrayList;

/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */
public class ContiguousRegion extends ArrayList<MapTile>{
    

    void addRegion(MapSector sector, int x, int z, int id) {
        MapTile tile = sector.getTile(x, z);
        if(tile.inRegion()) return;
        add(tile);
        tile.setRegion(id);
        if(x > 0)
            addTile(sector, x - 1, z, id, tile.getHeight());
        if(x < (MapSector.SIZEI - 1)) 
            addTile(sector, x + 1, z, id, tile.getHeight());
        if(z > 0) 
            addTile(sector, x, z - 1, id, tile.getHeight());
        if(z < (MapSector.SIZEI - 1)) 
            addTile(sector, x, z + 1, id, tile.getHeight());
    }
    
    
    private void addTile(MapSector sector, int x, int z, int id, float height) {
        MapTile tile = sector.getTile(x, z);
        if(tile.inRegion() || (tile.getHeight() != height)) return;
        add(tile);
        tile.setRegion(id);
        if(x > 0)
            addTile(sector, x - 1, z, id, height);
        if(x < (MapSector.SIZEI - 1)) 
            addTile(sector, x + 1, z, id, height);
        if(z > 0) 
            addTile(sector, x, z - 1, id, height);
        if(z < (MapSector.SIZEI - 1)) 
            addTile(sector, x, z + 1, id, height);
    }
    
    
    public static ArrayList<MapVertex> resolveSquares(MapSector sector) {        
        ArrayList<MapVertex> output = new ArrayList<MapVertex>(6);
        int numTiles = MapSector.SIZEI * MapSector.SIZEI;
        ArrayList<MapTile> source = new ArrayList<MapTile>(numTiles);
        for(int i = 0; i < MapSector.SIZEI; i++)
            for(int j = 0; j < MapSector.SIZEI; j++)
                source.add(sector.getTile(i, j));
        while(!source.isEmpty()) 
            output.addAll(resolveSquare(sector, source, source.get(0)));
        return output;
    }
    
    
    // This should find contiguous squares that can be used
    // as the basis of rendering.
    public static ArrayList<MapVertex> resolveSquare(MapSector sector, 
            ArrayList<MapTile> source, MapTile start) {
        
        ArrayList<MapVertex> output = new ArrayList<MapVertex>(6);
        
        boolean goUp    = true;
        boolean goDown  = true;
        boolean goRight = true;
        boolean goLeft  = true;
        int sx, minX, maxX, sz, minZ, maxZ, tmp;
        sx = minX = maxX = start.getSectX();
        sz = minZ = maxZ = start.getSectZ();
        float height = sector.getTile(sx, sz).getHeight();
        
        while(goLeft) {
            if(goLeft) {
                tmp = minX - 1;
                if(tmp < 0) {
                    goLeft = false;
                    break;
                }
                else if(!source.contains(sector.getTile(tmp, sz))) 
                    goLeft = false;
                else if(sector.getTile(tmp, sz).getHeight() 
                        != height) goLeft = false;
                else minX = tmp;
            }
        } while(goRight) {
            if(goRight) {
                tmp = maxX + 1;
                if(tmp >= MapSector.SIZEI) {
                    goRight = false;
                    break;
                }
                else if(!source.contains(sector.getTile(tmp, sz))) 
                    goRight = false;
                else if(sector.getTile(tmp, sz).getHeight() 
                        != height) goRight = false;
                else maxX = tmp;
                
            }
        }
        
        while(goUp) {
            if(goUp) {
                tmp = minZ - 1;
                boolean more = true;
                if(tmp < 0) goUp = false;
                else {
                    for(int i = minX; i <= maxX; i++) {
                        more = more && source.contains(sector.getTile(i, tmp));
                        if(!more) {
                            goUp = false;
                            break;
                        }
                    }
                    for(int i = minX; i <= maxX; i++) {
                        more = more && (sector.getTile(i, tmp).getHeight() 
                            == height);
                        if(!more) {
                            goUp = false;
                            break;
                        }
                    }
                    minZ = tmp;
                }
            }
            
        } while(goDown) {            
            if(goDown) {
                tmp = maxZ + 1;
                boolean more = true;
                if(tmp >= MapSector.SIZEI) goDown = false;
                else {
                    for(int i = minX; i <= maxX; i++) {
                        more = more && source.contains(sector.getTile(i, tmp));
                        if(!more) {
                            goDown = false;
                            break;
                        }
                    }
                    for(int i = minX; i <= maxX; i++) {
                        more = more && (sector.getTile(i, tmp).getHeight() 
                            == height);
                        if(!more) {
                            goDown = false;
                            break;
                        }
                    }
                    maxZ = tmp;
                }
            }
        }
        
        for(int i = minX; i <= maxX; i++)
            for(int j = minZ; j <= maxZ; j++)
                source.remove(sector.getTile(i, j));
        
        MapVertex[] tl = sector.getTile(minX, minZ).getVertices();
        MapVertex[] tr = sector.getTile(maxX, minZ).getVertices();
        MapVertex[] bl = sector.getTile(minX, maxZ).getVertices();
        MapVertex[] br = sector.getTile(maxX, maxZ).getVertices();
        
        output.add(0, tl[2]);
        output.add(1, bl[3]);
        output.add(2, br[0]);
        output.add(3, br[0]);
        output.add(4, tr[1]);
        output.add(5, tl[2]);
        
        return output;
    }
}
    

