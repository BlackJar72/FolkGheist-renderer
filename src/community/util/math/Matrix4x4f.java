//Copyright (C) Jared Blackburn, Oct 16, 2013
package community.util.math;

/**
 *
 * @author jared = JaredBGreat (Jared Blackburn)
 */
public class Matrix4x4f {
    private float[][] m = new float[4][4];
    
       
    
    public float get(int x, int y) {
        return m[x][y];
    }
    
    
    public void set(int x, int y, float value) {
        m[x][y] = value;
    }
    
    
    public Matrix4x4f add(Matrix4x4f b) {
        Matrix4x4f c = new Matrix4x4f();
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++) 
                c.set(i, j, m[i][j] + b.get(i, j));
        return c;
    }
    
    
    public Matrix4x4f sub(Matrix4x4f b) {
        Matrix4x4f c = new Matrix4x4f();
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++) 
                c.set(i, j, m[i][j] - b.get(i, j));
        return c;
    }
    
    
    public Matrix4x4f mul(Matrix4x4f b) {
        Matrix4x4f c = new Matrix4x4f();
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++) 
                c.set(i, j, (m[i][0] * b.get(0, j)) +
                            (m[i][1] * b.get(1, j)) +
                            (m[i][2] * b.get(2, j)) +
                            (m[i][3] * b.get(3, j)));
        return c;
    }
    
    
    public void initIdentity() {
        m[0][0] = 1f; m[1][0] = 0f; m[2][0] = 0f; m[3][0] = 0f;
        m[0][1] = 0f; m[1][1] = 1f; m[2][1] = 0f; m[3][1] = 0f;
        m[0][2] = 0f; m[1][2] = 0f; m[2][2] = 1f; m[3][2] = 0f;
        m[0][3] = 0f; m[1][3] = 0f; m[2][3] = 0f; m[3][3] = 1f; 
    }
    
    
    public void setIdentity() {
        m[0][0] = 1f; m[1][0] = 0f; m[2][0] = 0f; m[3][0] = 0f;
        m[0][1] = 0f; m[1][1] = 1f; m[2][1] = 0f; m[3][1] = 0f;
        m[0][2] = 0f; m[1][2] = 0f; m[2][2] = 1f; m[3][2] = 0f;
        m[0][3] = 0f; m[1][3] = 0f; m[2][3] = 0f; m[3][3] = 1f; 
    }
    
    
    public void setTranslation(float x, float y, float z) {
        m[0][0] = 1f; m[1][0] = 0f; m[2][0] = 0f; m[3][0] = x;
        m[0][1] = 0f; m[1][1] = 1f; m[2][1] = 0f; m[3][1] = y;
        m[0][2] = 0f; m[1][2] = 0f; m[2][2] = 1f; m[3][2] = z;
        m[0][3] = 0f; m[1][3] = 0f; m[2][3] = 0f; m[3][3] = 1f; 
    }
    
    
    public void setScale(float x, float y, float z) {
        m[0][0] = x;  m[1][0] = 0f; m[2][0] = 0f; m[3][0] = 0f;
        m[0][1] = 0f; m[1][1] = y;  m[2][1] = 0f; m[3][1] = 0f;
        m[0][2] = 0f; m[1][2] = 0f; m[2][2] = z;  m[3][2] = 0f;
        m[0][3] = 0f; m[1][3] = 0f; m[2][3] = 0f; m[3][3] = 1f; 
    }
    
    
    public void setScale(float s) {
        m[0][0] = s;  m[1][0] = 0f; m[2][0] = 0f; m[3][0] = 0f;
        m[0][1] = 0f; m[1][1] = s;  m[2][1] = 0f; m[3][1] = 0f;
        m[0][2] = 0f; m[1][2] = 0f; m[2][2] = s;  m[3][2] = 0f;
        m[0][3] = 0f; m[1][3] = 0f; m[2][3] = 0f; m[3][3] = 1f; 
    }
    
    
    public void setRotation(float x, float y, float z) {
        x = (float) Math.toRadians(x);
        y = (float) Math.toRadians(y);
        z = (float) Math.toRadians(z);
        
        Matrix4x4f rx = new Matrix4x4f();
        Matrix4x4f ry = new Matrix4x4f();
        Matrix4x4f rz = new Matrix4x4f();
        
        rx.m[0][0] = (float)Math.cos(z);  rx.m[1][0] = -(float)Math.sin(z); rx.m[2][0] = 0f; rx.m[3][0] = 0f;
        rx.m[0][1] = (float)Math.sin(z);  rx.m[1][1] =  (float)Math.cos(z); rx.m[2][1] = 0f; rx.m[3][1] = 0f;
        rx.m[0][2] = 0f; rx.m[1][2] = 0f; rx.m[2][2] = 0f; rx.m[3][2] = 0f;
        rx.m[0][3] = 0f; rx.m[1][3] = 0f; rx.m[2][3] = 0f; rx.m[3][3] = 1f; 
        
        ry.m[0][0] = 0f; ry.m[1][0] = 0f; ry.m[2][0] = 0f; ry.m[3][0] = 0f;
        ry.m[0][1] = 0f; ry.m[1][1] = (float)Math.cos(x);  ry.m[2][1] = -(float)Math.sin(x); ry.m[3][1] = 0f;
        ry.m[0][2] = 0f; ry.m[1][2] = (float)Math.sin(x);  ry.m[2][2] =  (float)Math.cos(x); ry.m[3][2] = 0f;
        ry.m[0][3] = 0f; ry.m[1][3] = 0f; ry.m[2][3] = 0f; ry.m[3][3] = 1f; 
    
        rz.m[0][0] = (float)Math.cos(y);  rz.m[1][0] = 0f; rz.m[2][0] = -(float)Math.sin(y); rz.m[3][0] = 0f;
        rz.m[0][1] = 0f; rz.m[1][1] = 0f; rz.m[2][1] = 0f; rz.m[3][1] = 0f;
        rz.m[0][2] = (float)Math.sin(y);  rz.m[1][2] = 0f; rz.m[2][2] =  (float)Math.cos(y); rz.m[3][2] = 0f;
        rz.m[0][3] = 0f; rz.m[1][3] = 0f; rz.m[2][3] = 0f; rz.m[3][3] = 1f; 
        
        rx.mul(ry.mul(rx));
        
        m[0][0] = rx.m[0][0]; m[0][1] = rx.m[0][1]; m[0][2] = rx.m[0][2]; m[0][3] = rx.m[0][3]; 
        m[1][0] = rx.m[1][0]; m[1][1] = rx.m[1][1]; m[1][2] = rx.m[1][2]; m[1][3] = rx.m[1][3]; 
        m[2][0] = rx.m[3][0]; m[2][1] = rx.m[2][1]; m[2][2] = rx.m[2][2]; m[2][3] = rx.m[2][3]; 
        m[3][0] = rx.m[3][0]; m[3][1] = rx.m[3][1]; m[3][2] = rx.m[3][2]; m[3][3] = rx.m[3][3]; 
    }
    
    
    public void setRotation(float r) {
        r = (float)Math.toRadians(r);
        m[0][0] = (float)Math.cos(r);   m[1][0] = 0f; m[2][0] = -(float)Math.sin(r); m[3][0] = 0f;
        m[0][1] = 0f;                   m[1][1] = 1f; m[2][1] = 0f;                  m[3][1] = 0f;
        m[0][2] = (float)Math.sin(r);   m[1][2] = 0f; m[2][2] = -(float)Math.cos(r); m[3][2] = 0f;
        m[0][3] = 0f;                   m[1][3] = 0f; m[2][3] = 0f;                  m[3][3] = 1f; 
    }
    
    
    public void setProjection() {
        m[0][0] = 1f; m[1][0] = 0f; m[2][0] = 0f; m[3][0] = 0f;
        m[0][1] = 0f; m[1][1] = 1f; m[2][1] = 0f; m[3][1] = 0f;
        m[0][2] = 0f; m[1][2] = 0f; m[2][2] = 1f; m[3][2] = 0f;
        m[0][3] = 0f; m[1][3] = 0f; m[2][3] = 0f; m[3][3] = 1f; 
    }
    
    
    public Matrix4x4f copy() {
        Matrix4x4f other = new Matrix4x4f();
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                other.set(i, j, m[i][j]);
        return other;
    }
    
    
    public static Matrix4x4f giveIdentity() {
        Matrix4x4f out = new Matrix4x4f();
        out.initIdentity();
        return out;
    }
    
    
    public static Matrix4x4f giveTranslation(float x, float y, float z) {
        Matrix4x4f out = new Matrix4x4f();
        out.setTranslation(x, y, z);
        return out;
    }
    
    
    public static Matrix4x4f giveScale(float x, float y, float z) {
        Matrix4x4f out = new Matrix4x4f();
        out.setScale(x, y, z);
        return out;
    }
    
    
    public static Matrix4x4f giveScale(float s) {
        Matrix4x4f out = new Matrix4x4f();
        out.setScale(s);
        return out;
    }
    
    
    public static Matrix4x4f giveRotation(float r, float x, float y, float z) {
        x = (float) Math.toRadians(x);
        y = (float) Math.toRadians(y);
        z = (float) Math.toRadians(z);
        
        Matrix4x4f rx = new Matrix4x4f();
        Matrix4x4f ry = new Matrix4x4f();
        Matrix4x4f rz = new Matrix4x4f();
        
        rx.m[0][0] = (float)Math.cos(z);  rx.m[1][0] = -(float)Math.sin(z); rx.m[2][0] = 0f; rx.m[3][0] = 0f;
        rx.m[0][1] = (float)Math.sin(z);  rx.m[1][1] =  (float)Math.cos(z); rx.m[2][1] = 0f; rx.m[3][1] = 0f;
        rx.m[0][2] = 0f; rx.m[1][2] = 0f; rx.m[2][2] = 0f; rx.m[3][2] = 0f;
        rx.m[0][3] = 0f; rx.m[1][3] = 0f; rx.m[2][3] = 0f; rx.m[3][3] = 1f; 
        
        ry.m[0][0] = 0f; ry.m[1][0] = 0f; ry.m[2][0] = 0f; ry.m[3][0] = 0f;
        ry.m[0][1] = 0f; ry.m[1][1] = (float)Math.cos(x);  ry.m[2][1] = -(float)Math.sin(x); ry.m[3][1] = 0f;
        ry.m[0][2] = 0f; ry.m[1][2] = (float)Math.sin(x);  ry.m[2][2] =  (float)Math.cos(x); ry.m[3][2] = 0f;
        ry.m[0][3] = 0f; ry.m[1][3] = 0f; ry.m[2][3] = 0f; ry.m[3][3] = 1f; 
    
        rz.m[0][0] = (float)Math.cos(z);  rz.m[1][0] = 0f; rz.m[2][0] = -(float)Math.sin(z); rz.m[3][0] = 0f;
        rz.m[0][1] = 0f; rz.m[1][1] = 0f; rz.m[2][1] = 0f; rz.m[3][1] = 0f;
        rz.m[0][2] = (float)Math.sin(z);  rz.m[1][2] = 0f; rz.m[2][2] = (float)Math.cos(z); rz.m[3][2] = 0f;
        rz.m[0][3] = 0f; rz.m[1][3] = 0f; rz.m[2][3] = 0f; rz.m[3][3] = 1f; 
        
        return rz.mul(ry.mul(rx));
    }
    
    
    public static Matrix4x4f giveRotation(float r) {
        Matrix4x4f out = new Matrix4x4f();
        out.setRotation(r);
        return out;
    }
    
    
    public static Matrix4x4f giveProjection() {
        Matrix4x4f out = new Matrix4x4f();
        // TODO: All
        return out;
    }
    
    
    @Override
    public String toString() {
        return("\n"  + super.toString() + 
               "\n|" + m[0][0] + "   " + m[1][0] + "   "+ m[2][0] + "   "+ m[3][0] + "|" +
               "\n|" + m[0][1] + "   " + m[1][1] + "   "+ m[2][1] + "   "+ m[3][1] + "|" +
               "\n|" + m[0][2] + "   " + m[1][2] + "   "+ m[2][2] + "   "+ m[3][2] + "|" + 
               "\n|" + m[0][3] + "   " + m[1][3] + "   "+ m[2][3] + "   "+ m[3][3] + "|\n");
    }

}
