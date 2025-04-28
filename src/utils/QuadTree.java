package utils;

/*
 * Adaptado de https://gist.github.com/AbhijeetMajumdar/c7b4f10df1b87f974ef4#file-quadtree-java
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import physics.Body2D;

public class QuadTree {

    public double x0, y0, x1, y1;
    public List<Body2D> bodies;
    public QuadTree nw, ne, sw, se; //northwest,northeast,southwest,southeast

    public Vector2D massPositionSum;
    public double mass;

    public QuadTree(double x0, double y0, double x1, double y1) {
        this.x0 = x0;
        this.x1 = x1;
        this.y0 = y0;
        this.y1 = y1;
        this.massPositionSum = new Vector2D();
        bodies = new ArrayList<>();
    }

    public boolean contains(Vector2D coord) {
        return (coord.x >= x0
                && coord.x <= x1
                && coord.y >= y0
                && coord.y <= y1);
    }

    public void reset() {
        this.bodies.clear();

        this.ne = null;
        this.nw = null;
        this.se = null;
        this.sw = null;
    }

    public void split() {
        double xMid = (x1 + x0) / 2;
        double yMid = (y1 + y0) / 2;

        nw = new QuadTree(x0, y0, xMid, yMid);
        ne = new QuadTree(xMid, y0, x1, yMid);
        sw = new QuadTree(x0, yMid, xMid, y1);
        se = new QuadTree(xMid, yMid, x1, y1);
    }

    public void insert(Body2D body) {

        if (!contains(body.position)) {
            return;
        }
        
        this.updateCenterOfMass(body);
      
        // Si ya existen cuadrantes hijos,
        if (nw != null) {
            // Insertar en el próximo cuadrante
            insertInChild(body);
            return;
        }

        //Tratar de ingresar en esta celda
        if (bodies.size() < 4) {
            bodies.add(body);
            return;
        }
        
        //Crear cuadrantes hijos 
        split();
        //Pasar los cuerpos al próximo nivel
        bodies.add(body);
        for (Body2D oldBody : bodies) {
            insertInChild(oldBody);
        }
        //Limpiar la lista
        bodies.clear();
    }

    public void insertInChild(Body2D body) {
        if (nw.contains(body.position)) {
            nw.insert(body);
        } else if (ne.contains(body.position)) {
            ne.insert(body);
        } else if (sw.contains(body.position)) {
            sw.insert(body);
        } else if (se.contains(body.position)) {
            se.insert(body);
        } else {
            System.err.printf("(%f, %f) no insertado al QuadTree!\n", body.position.x, body.position.y);
        }
    }
    
    public void updateCenterOfMass(Body2D body){
        massPositionSum = massPositionSum.add(body.position.scale(body.mass));
        mass += body.mass;
    }
    public Vector2D getCenterOfMass(){
        return mass == 0 ? massPositionSum : massPositionSum.scale(1/mass);
    }

    public void draw(Graphics2D g) {

        double scale = Constants.SCALE;
        double centerX = Constants.CENTER.x;
        double centerY = Constants.CENTER.y;

        g.setColor(Color.GREEN);

        double x = centerX + x0 * scale;
        double y = centerY + y0 * scale;
        double width = (x1 - x0) * scale;
        double height = (y1 - y0) * scale;

        g.drawRect((int) x, (int) y, (int) width, (int) height);

        //Malo para performance: solo usar para verificar
        if (Constants.DEBUG_DRAW == 2) {
            for (Body2D body : bodies) {
                body.shape.color = Color.MAGENTA;
            }
        }
        if (nw != null) {
            nw.draw(g);
        }
        if (ne != null) {
            ne.draw(g);
        }
        if (sw != null) {
            sw.draw(g);
        }
        if (se != null) {
            se.draw(g);
        }

    }
}
