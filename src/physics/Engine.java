package physics;

import java.util.List;
import java.util.ArrayList;
import utils.Constants;
import utils.Vector2D;

public class Engine {

    public List<HeavenlyBody> entities;

    public Engine() {
        this.entities = new ArrayList();
    }

    public void step(float deltaTime) {  
        HeavenlyBody e1;
        HeavenlyBody e2;
        
        for (int i = 0; i < entities.size(); i++) {
            for (int j = i + 1; j < entities.size(); j++) {
                e1 = entities.get(i);
                e2 = entities.get(j);

                Vector2D F = e1.gravityForce(e2);
                
                e1.applyForce(F);
                e2.applyForce(F.reflect());
            }
        }
        
        //Update physics
        for (Entity entity : entities) {
            entity.update(Constants.DELTA);
            entity.acceleration = new Vector2D();
        }
    }
    
    public void add(HeavenlyBody entity){
        if (!entities.contains(entity))
                entities.add(entity);
    }
}

