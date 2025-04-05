package physics;

import java.awt.Graphics;
import utils.Constants;
import utils.Vector2D;

/**
 * Abstracción de una entidad física 2D en el motor de física. Representa un
 * cuerpo con posición, velocidad, aceleración y masa.
 *
 * En este programa, los cuerpos celestes en órbita se modelan como círculos.
 */
public class Body2D {

    public Vector2D position;
    public Vector2D velocity;
    public Vector2D acceleration;
    public double mass;
    public Circle shape;

    public Body2D(double mass, Circle shape) {
        this.position = new Vector2D();
        this.velocity = new Vector2D();
        this.acceleration = new Vector2D();
        this.mass = mass;
        this.shape = shape;
    }

    public Body2D(Vector2D position, double mass, Circle shape) {
        this.position = position;
        this.velocity = new Vector2D();
        this.acceleration = new Vector2D();
        this.mass = mass;
        this.shape = shape;
    }

    public Body2D(Vector2D position, Vector2D velocity, double mass, Circle shape) {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = new Vector2D();
        this.mass = mass;
        this.shape = shape;
    }

    //Agrega una fuerza específica a la neta que experimenta el cuerpo
    //en el instante. Note que en update(), se nulifica la aceleración 
    //para el próximo step del motor
    public void applyForce(Vector2D force) {
        acceleration = force.scale(1 / mass); //a = F/m;
    }

    public void eulerUpdate(double dt) {
        velocity = velocity.add(acceleration.scale(dt));
        position = position.add(velocity.scale(dt));
        acceleration = new Vector2D(); //Para el siguiente frame
    }

    public void verletPos(double dt) {
        position = position.add(velocity.scale(dt)).add(acceleration.scale(0.5*dt*dt));
    }

    public void verletVel(double dt, Vector2D newAcc) {
        velocity = velocity.add(acceleration.add(newAcc).scale(0.5*dt));
    }

    public Vector2D gravityForce(Body2D other) {

        Vector2D dir = other.position.subtract(this.position);
        double dist2 = dir.dot(dir);

        double F = (Constants.G * this.mass * other.mass) / (dist2);
        return dir.normalize().scale(F);
    }

    public void draw(Graphics g) {
        shape.draw(
                g,
                position.scale(Constants.SCALE).add(Constants.CENTER)
        );
    }

}
