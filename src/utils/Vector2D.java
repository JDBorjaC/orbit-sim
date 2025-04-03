package utils;

/*
 * Representación de un vector en el plano cartesiano. 
 * Implementa adiciones, restas, producto punto, entre otras operaciones.
 */

public class Vector2D {
    public double x;
    public double y;
    
    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }
    public Vector2D(Vector2D vector2d){
        this.x = vector2d.x;
        this.y = vector2d.y;
    }
    public Vector2D(){
        this.x = 0;
        this.y = 0;
    }
    
    public Vector2D add(Vector2D other) {
        return new Vector2D(x+other.x, y+other.y);
    }
    
    public Vector2D subtract(Vector2D other) {
        return new Vector2D(x-other.x, y-other.y);
    }
    
    public Vector2D scale(double factor){
        return new Vector2D(x*factor, y*factor);
    }
    
    public double dot(Vector2D other){
        return x*other.x + y*other.y;
    }
    
    //Vector Unitario / Dirección
    public Vector2D normalize() {
        double length = length();
        if(length != 0)
            return scale(1/length);
        return new Vector2D();
    }
    
    public Vector2D reflect(){
        return scale(-1);
    }
    
    public double distanceTo(Vector2D other){
        return subtract(other).length();
    }
    
    public double length() {
        return Math.sqrt(x*x + y*y);
    }
    
    public boolean isEqualApprox(Vector2D other){
        double epsilon = 1e-6;
        return distanceTo(other) < epsilon;
    }
    
    @Override
    public String toString(){
        return "("+x+", "+y+")";
    }
}
