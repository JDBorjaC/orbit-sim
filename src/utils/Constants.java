
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
    public static final Method integrationMethod = Method.VERLET;
    public static final double UA = 1.496e11;
    public static final double G = 6.6748e-11f;
    
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static final Vector2D CENTER = new Vector2D(WIDTH/2, HEIGHT/2);
    public static final double SCALE = 40.0f / UA; 
    
    public static final float DT = 3600*24;
    public static final int TARGET_FPS = 60;
}
