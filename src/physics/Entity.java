
package physics;

import java.awt.Graphics;
import utils.Vector2D;

/*
 * Game engine-like template class for handling objects as rigid bodies.
 * Inspired by Richard Marxer's Física library for Processing.
 */
 
//Got

public abstract class Entity {
    public Vector2D position;
    public Vector2D velocity;
    public Vector2D acceleration;
    public float mass;
    public Circle  shape;

    public Entity(float mass, Circle shape) {
        this.position = new Vector2D();
        this.velocity = new Vector2D();
        this.acceleration = new Vector2D();
        this.mass = mass;
        this.shape = shape;
    }

    public Entity(Vector2D position, float mass, Circle shape) {
        this.position = position;
        this.velocity = new Vector2D();
        this.acceleration = new Vector2D();
        this.mass = mass;
        this.shape = shape;
    }

    public Entity(Vector2D position, Vector2D velocity, float mass, Circle shape) {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = new Vector2D();
        this.mass = mass;
        this.shape = shape;
    }
    
    
    
    public void applyForce(Vector2D force) {
        Vector2D forceAcceleration = force.scale(1/mass); //a = F/m;
        acceleration = acceleration.add(forceAcceleration);
    }
    
    public void applyImpulse(Vector2D impulse) {
        Vector2D impulseVelocity = impulse.scale(1/mass); //v = J/m = (F*deltaTime)/m
        velocity = velocity.add(impulseVelocity);
    }
    
    public void update(float deltaTime) {
        position = position.add(velocity.scale(deltaTime));
        velocity = velocity.add(acceleration.scale(deltaTime));
    }

    public void adjustPosition(Vector2D offset) {
        this.position = position.add(offset);
    }
    
    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public void adjustVelocity(Vector2D offset) {
        this.velocity = velocity.add(offset);
    }
    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }


    public void setAcceleration(Vector2D acceleration) {
        this.acceleration = acceleration;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }
    
    
    
    
    public abstract void draw(Graphics g);

}
