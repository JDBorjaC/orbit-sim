
package utils;

import physics.Engine.Method;

/*
 * Recopilación de datos que afectan los cálculos físicos,
 * la visualización del mundo y la frecuencia del hilo principal.
 * 
 * Para cambiar las posiciones y velocidades iniciales de los 
 * cuerpos, ver el main.
*/
public class Constants {
    //0: no, 1: cuadrantes, 2: resaltar cuerpos EN el QuadTree
    public static final int DEBUG_DRAW = 0;
    public static final boolean BARNES_HUT = true;     
    public static final int N_BODIES = 3000;
    
    public static final int MAX_QTCELL_CAPACITY = 4; 
    public static final double THETA = 1;
    
    
    public static final Method INTEGRATION_METHOD = Method.VERLET;
    public static final double UA = 1.496e11;
    public static final double G = 6.6748e-11f;
    
    public static final int WIDTH = 640;
    public static final int HEIGHT = 640;
    public static final Vector2D CENTER = new Vector2D(WIDTH/2, HEIGHT/2);
    public static final double SCALE = 25.0f / UA; 
    
    public static final float DT = 3600*24;
    public static final int TARGET_FPS = 60;
}
