package community.ui.models;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import community.util.math.Vec3i;

/**
 *
 * @author jared
 */
class Face {
    protected Vec3i vertices;
    protected Vec3i uvcoords;
    protected Vec3i normals;
    
    protected Face(Vec3i verts, Vec3i uv, Vec3i norms) {
        vertices = verts;
        uvcoords = uv;
        normals  = norms;
    }
}
