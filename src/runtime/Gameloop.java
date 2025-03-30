
package runtime;

import java.util.ArrayList;
import physics.Engine;
import physics.Entity;


public class Gameloop implements Runnable {
    Thread mainThread;
    Renderer renderer;
    Engine engine;

    public Gameloop(Engine engine,Renderer renderer) {
        this.renderer = renderer;
        this.engine = engine;
    }
    
    
    public void startMainThread(){
        mainThread = new Thread(this);
        mainThread.start();
    }
    
    public void update(float deltaTime){
        engine.step(deltaTime);
    }
    
    public void render(){
        ArrayList<Entity> entities = new ArrayList(engine.entities);
        renderer.render(entities);
    }
    
    @Override
    public void run() {

        int TARGET_FPS = 60;
        double FRAME_TIME = 1000000000 / TARGET_FPS;

        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (mainThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / FRAME_TIME;

            lastTime = currentTime;

            if (delta >= 1) {
                update((float)delta);
                render();
                delta--;
            }
        }
    }
}
