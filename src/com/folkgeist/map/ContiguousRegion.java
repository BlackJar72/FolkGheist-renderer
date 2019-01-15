//Copyright (C) Jared Blackburn, Sep 20, 2013
package com.folkgeist.map;

import java.util.ArrayList;

/**
 *
 * @author JaredBGreat (Jared Blackburn)
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
        ArrayList<MapVertex> output = new ArrayList<MapVertex>(18);
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
    // FIXME:  This needs to work more like the RoomSeed from Doomlike Dungeons:
    // Expand along only one dimension then expand along the other when the 
    // first has no more room for expansion.  This way we keep squares.  This
    // is more of a flood-fill and totally wrong!
    // (Also, make sure to retrieve next seed/start tiles from a list of those
    // not yet used.)
    public static ArrayList<MapVertex> resolveSquare(MapSector sector, 
            ArrayList<MapTile> source, MapTile start) {
        //System.out.println("Resolving a square");
        ArrayList<MapVertex> output = new ArrayList<MapVertex>(32);
        
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
                else if(!source.contains(sector.getTile(tmp, sz))) {                    
                    goLeft = false;
                    break;
                }
                else if(sector.getTile(tmp, sz).getHeight() 
                        != height) {
                    goLeft = false;
                    break;
                }
                else minX = tmp;
            }
        } while(goRight) {
            if(goRight) {
                tmp = maxX + 1;
                if(tmp >= MapSector.SIZEI) {
                    goRight = false;
                    break;
                }
                else if(!source.contains(sector.getTile(tmp, sz))) {
                    goRight = false;
                    break;
                }
                else if(sector.getTile(tmp, sz).getHeight() 
                        != height) {
                    goRight = false;
                    break;
                }
                else maxX = tmp;
                
            }
        }
        
        while(goUp) {
            tmp = minZ;
            boolean more = true;
            if(goUp) {
                tmp--;
                if(tmp < 0) goUp = false;
                else {
                    for(int i = minX; i <= maxX; i++) {
                        more = more && source.contains(sector.getTile(i, tmp));
                        if(!more) {
                            goUp = false;
                            tmp++;
                            break;
                        }
                    }
                    for(int i = minX; i <= maxX; i++) {
                        more = more && (sector.getTile(i, tmp).getHeight() 
                            == height);
                        if(!more) {
                            goUp = false;
                            tmp++;
                            break;
                        }
                    }
                }
                if(goUp) minZ = tmp;
            }
            
        } while(goDown) {  
            tmp = maxZ;
            boolean more = true;          
            if(goDown) {
                tmp++;
                if(tmp >= MapSector.SIZEI) goDown = false;
                else {
                    for(int i = minX; i <= maxX; i++) {
                        more = more && source.contains(sector.getTile(i, tmp));
                        if(!more) {
                            goDown = false;
                            tmp--;
                            break;
                        }
                    }
                    for(int i = minX; i <= maxX; i++) {
                        more = more && (sector.getTile(i, tmp).getHeight() 
                            == height);
                        if(!more) {
                            goDown = false;
                            tmp--;
                            break;
                        }
                    }
                }
                if(goDown) maxZ = tmp;
            }
        }
        
        for(int i = minX; i <= maxX; i++)
            for(int j = minZ; j <= maxZ; j++)
                source.remove(sector.getTile(i, j));
        
        MapVertex[] tl = sector.getTile(minX, minZ).getVertices();
        MapVertex[] tr = sector.getTile(maxX, minZ).getVertices();
        MapVertex[] bl = sector.getTile(minX, maxZ).getVertices();
        MapVertex[] br = sector.getTile(maxX, maxZ).getVertices();
        
        MapVertex[] tln = sector.getTile(minX, minZ).getNormals();
        MapVertex[] trn = sector.getTile(maxX, minZ).getNormals();
        MapVertex[] bln = sector.getTile(minX, maxZ).getNormals();
        MapVertex[] brn = sector.getTile(maxX, maxZ).getNormals();
        
        MapVertex[] tlt = sector.getTile(minX, minZ).getTcoords();
        MapVertex[] trt = sector.getTile(maxX, minZ).getTcoords();
        MapVertex[] blt = sector.getTile(minX, maxZ).getTcoords();
        MapVertex[] brt = sector.getTile(maxX, maxZ).getTcoords();
        
        output.add(tl[2]);    
        output.add(tln[2]);
        //output.add(tlt[2]);
        
        output.add(bl[3]);
        output.add(bln[3]);
        //output.add(blt[3]);
        
        output.add(br[0]);
        output.add(brn[0]);
        //output.add(brt[0]);
        
        output.add(br[0]);
        output.add(brn[0]);
        //output.add(brt[0]);
        
        output.add(tr[1]);
        output.add(trn[1]);
        //output.add(trt[1]);
        
        output.add(tl[2]);
        output.add(tln[2]);
        //output.add(tlt[2]);
        
        return output;
    }
}
    

