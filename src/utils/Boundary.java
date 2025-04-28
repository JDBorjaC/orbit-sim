
package utils;

public class Boundary {

    double x0, x1, y0, y1;

    public Boundary(double x0, double y0, double x1, double y1) {
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
