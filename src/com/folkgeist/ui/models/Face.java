//Copyright (C) Jared Blackburn, 2014
package com.folkgeist.ui.models;

import com.folkgeist.util.math.Vec3i;

/**
 *
 * @author JaredBGreat (Jared Blackburn)
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
