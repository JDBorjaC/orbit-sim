package physics;

import java.awt.Graphics;
import utils.Constants;
import utils.Vector2D;

public class HeavenlyBody extends Entity {

    private Vector2D center = new Vector2D(Constants.WIDTH / 2, Constants.HEIGHT / 2);

    public HeavenlyBody(Vector2D position, double mass, Circle shape) {
        super(position, mass, shape);
    }
    public HeavenlyBody(Vector2D position, Vector2D velocity, double mass, Circle shape) {
        super(position, velocity, mass, shape);
    }

    public Vector2D gravityForce(HeavenlyBody other) {
        
        Vector2D dir = other.position.subtract(this.position);
        double dist2 = dir.dot(dir);
        
        //if (dist2 == 0) 
            //return new Vector2D(0, 0);
        
        double F = (Constants.G * this.mass * other.mass) / (dist2);
        return dir.normalize().scale(F);
    }

    @Override
    public void draw(Graphics g) {
        shape.draw(
                g,
                position.scale(Constants.SCALE).add(center)
        );
    }

}
