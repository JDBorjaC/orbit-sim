package physics;

import java.awt.Graphics;
import utils.Constants;
import utils.Vector2D;

public class HeavenlyBody extends Entity {

    private Vector2D center = new Vector2D(Constants.WIDTH / 2, Constants.HEIGHT / 2);

    public HeavenlyBody(Vector2D position, float mass, Circle shape) {
        super(position, mass, shape);
    }
    public HeavenlyBody(Vector2D position, Vector2D velocity, float mass, Circle shape) {
        super(position, velocity, mass, shape);
    }

    public Vector2D gravityForce(HeavenlyBody other) {

        Vector2D dir = other.position.subtract(this.position);
        float dist = dir.length();
        
        if (dist == 0) 
            return new Vector2D(0, 0);
        
        float F = (Constants.G * this.mass * other.mass) / (dist * dist);
        
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
