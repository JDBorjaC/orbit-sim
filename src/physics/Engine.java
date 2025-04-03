package physics;

import java.util.List;
import java.util.ArrayList;
import utils.Constants;
import utils.Vector2D;

public class Engine {

    public enum Method {
        EULER,
        VERLET
    }

    private Method method = Constants.integrationMethod;

    public List<Body2D> bodies;

    public Engine() {
        this.bodies = new ArrayList();
    }

    public void step(double dt) {
        
        if (method == Method.EULER)
            Euler(dt);
        else
            Verlet(dt);
    }

    public void Euler(double dt) {
        for (Body2D body : bodies) {
            body.acceleration = calcAcceleration(body);
        }

        for (Body2D body : bodies) {
            body.eulerUpdate(dt);
        }
    }

    public void Verlet(double dt) {
        for (Body2D body : bodies) {
            body.acceleration = calcAcceleration(body);
        }

        for (Body2D body : bodies) {
            body.verletPos(dt);
        }

        // Nueva aceleración
        List<Vector2D> newAccs = new ArrayList<>();
        for (Body2D body : bodies) {
            newAccs.add(calcAcceleration(body));
        }

        //Velocidades
        for (int i = 0; i < bodies.size(); i++) {
            Body2D body = bodies.get(i);
            Vector2D newAcc = newAccs.get(i);

            body.verletVel(dt, newAcc);
        }
    }

    public Vector2D calcAcceleration(Body2D body) {
        Vector2D sum = new Vector2D();
        for (Body2D other : bodies) {
            if (body != other)
                sum = sum.add(body.gravityForce(other));
        }
        return sum.scale(1/body.mass); // a = F/m
    }

    public void add(Body2D entity) {
        if (!bodies.contains(entity)) {
            bodies.add(entity);
        }
    }
}
