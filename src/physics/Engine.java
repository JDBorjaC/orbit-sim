package physics;

import java.util.List;
import java.util.ArrayList;
import utils.Boundary;
import utils.Constants;
import utils.QuadTree;
import utils.Vector2D;

public class Engine {

    public enum Method {
        EULER,
        VERLET
    }

    private Method method = Constants.INTEGRATION_METHOD;

    public List<Body2D> bodies;
    public QuadTree partitions;

    public Engine() {
        this.bodies = new ArrayList();

        //Mitad del ancho y alto del mundo físico visible
        double hWidth = Constants.WIDTH / (2 * Constants.SCALE);
        double hHeight = Constants.HEIGHT / (2 * Constants.SCALE);

        this.partitions = new QuadTree(
                -hWidth,
                -hHeight,
                hWidth,
                hHeight
        );
    }

    public void step(double dt) {

        //Crear QuadTree
        this.partitions.reset();
        for (Body2D body : bodies) {
            this.partitions.insert(body);
        }

        //Cálculos Físicos
        if (method == Method.EULER) {
            Euler(dt);
        } else {
            Verlet(dt);
        }

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

    public Vector2D gravityBarnesHut(Body2D body, QuadTree cell) {
        
        //Caso 1: estamos en una celda hoja: calcular cuerpo a cuerpo
        if( cell.nw == null ){
            Vector2D sum = new Vector2D();
            for (Body2D other : cell.bodies) {
                if(body != other){
                    sum = sum.add(body.gravityForce(other.position, other.mass));
                }
            }
            return sum;
        }
        
        double d = body.position.distanceTo(cell.getCenterOfMass());
        double s = cell.x1 - cell.x0; //Asumiendo ancho = alto
        
        //Caso dos: esta celda es lo suficientemente lejana para estimar
        //con el cdm
        if (s / d < Constants.THETA) {
            return body.gravityForce(cell.getCenterOfMass(), cell.mass);
        }
        
        //Caso 3: Lo suficientemente cerca para bajar un nivel en el árbol
        Vector2D sum = new Vector2D();
        
        sum = sum.add(gravityBarnesHut(body,cell.ne));
        sum = sum.add(gravityBarnesHut(body,cell.nw));
        sum = sum.add(gravityBarnesHut(body,cell.se));
        sum = sum.add(gravityBarnesHut(body,cell.sw));
        
        return sum;
    }
    
    public Vector2D gravityNSquare(Body2D body){
        Vector2D sum = new Vector2D();
        for (Body2D other : bodies) {
            if (body != other) {
                sum = sum.add(
                        body.gravityForce(other.position, other.mass)
                );
            }
        }
        return sum;
    }

    public Vector2D calcAcceleration(Body2D body) {
        Vector2D netForce = Constants.BARNES_HUT 
                ? gravityBarnesHut(body, partitions)
                : gravityNSquare(body);
        return netForce.scale(1 / body.mass); // a = F/m
    }

    public void add(Body2D entity) {
        if (!bodies.contains(entity)) {
            bodies.add(entity);
        }
    }
}
