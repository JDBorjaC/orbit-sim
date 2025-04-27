package utils;

/*
 * Adaptado de https://gist.github.com/AbhijeetMajumdar/c7b4f10df1b87f974ef4#file-quadtree-java
 */
import java.util.ArrayList;
import java.util.List;
import physics.Body2D;

class Boundary {

    int x0, x1, y0, y1;

    public Boundary(int x0, int y0, int x1, int y1) {
        this.x0 = x0;
        this.x1 = x1;
        this.y0 = y0;
        this.y1 = y1;
    }

    public boolean contains(Vector2D coord) {
        return (coord.x >= x0
                && coord.x <= x1
                && coord.y >= y0
                && coord.y <= y1);
    }
}

public class QuadTree {

    List<Body2D> bodies;
    QuadTree parent, nw, ne, sw, se; //northwest,northeast,southwest,southeast
    Boundary boundary;

    Vector2D centerOfMass;
    double mass;

    public QuadTree(Boundary boundary) {
        bodies = new ArrayList<>();
        this.boundary = boundary;
    }

    void split() {
        int xOffset = boundary.x0 + (boundary.x1 - boundary.x0) / 2;
        int yOffset = boundary.y1 + (boundary.y1 - boundary.y0) / 2;

        nw = new QuadTree(new Boundary(
                boundary.x0, boundary.y0, xOffset, yOffset
        ));
        ne = new QuadTree(new Boundary(
                xOffset, boundary.y0, xOffset, yOffset
        ));
        sw = new QuadTree(new Boundary(
                boundary.x0, yOffset, xOffset, boundary.y1
        ));
        se = new QuadTree(new Boundary(
                xOffset, yOffset, boundary.x1, boundary.y1
        ));

    }

    void insert(Body2D body) {

        if (!this.boundary.contains(body.position)) {
            return;
        }

        if (bodies.size() < 4) {
            bodies.add(body);
            return;
        }

        // Si aún no hay particiones hijas, hacerlas
        if (nw == null) {
            split();
        }

        // Averiguar en qué partición seguir con la inserción
        if (this.nw.boundary.contains(body.position)) {
            this.nw.insert(body);
        } else if (this.ne.boundary.contains(body.position)) {
            this.ne.insert(body);
        } else if (this.sw.boundary.contains(body.position)) {
            this.sw.insert(body);
        } else if (this.se.boundary.contains(body.position)) {
            this.se.insert(body);
        } else {
            System.err.printf("ERROR: Cuerpo ubicado en (%f, %f) no insertado al QuadTree!\n", body.position.x, body.position.y);
        }
    }

    public double[] calcCenterOfMass() {
        /*
         * INFORMACIÓN FÍSICA DE LA CELDA DEL ÁRBOL
         * [0]: masa
         * [1]: posición x del cdm (sumatoria de m_i*x_i)
         * [2]: posición y del cdm (...)
         */
        double[] data = new double[3];

        for (Body2D body : this.bodies) {
            data[0] += body.mass;
            data[1] += body.mass * body.position.x;
            data[2] += body.mass * body.position.y;
        }

        /*
         * Por como está definida la creación de cuadrantes,
         * La no-nulidad de northwest valida todos los demás
         * cuadrantes
         */
        if (this.nw == null) {
            this.mass = data[0];
            if (this.mass != 0) {
                this.centerOfMass = new Vector2D(data[1]/data[0],data[2]/data[0]);
            } else {
                this.centerOfMass = new Vector2D(0, 0);
            }
        }

        updateData(nw, data);
        updateData(ne, data);
        updateData(sw, data);
        updateData(se, data);

        this.mass = data[0];
        this.centerOfMass = new Vector2D(data[1], data[2]);
        return data;
    }

    public void updateData(QuadTree cell, double[] data) {
        double[] temp = cell.calcCenterOfMass();

        //Tener en cuenta que la referencia a data se
        //mantiene luego de modificarlo
        data[0] += temp[0];
        data[1] += temp[1];
        data[2] += temp[2];
    }

}
