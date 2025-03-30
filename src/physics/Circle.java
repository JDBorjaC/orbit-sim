package physics;

import java.awt.Color;
import java.awt.Graphics;
import utils.Vector2D;

public class Circle {

    public float radius;
    public Color color;

    public Circle(float radius, Color color) {
        this.radius = radius;
        this.color = color;
    }
    
    public void draw(Graphics g, Vector2D position) {
        g.setColor(color);
        g.fillOval(
                (int) (position.x - radius),
                (int) (position.y - radius),
                (int) (radius*2),
                (int) (radius*2)
        );
    }

}
