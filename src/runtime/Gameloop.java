
package runtime;

import physics.Engine;
import utils.Constants;

/*
 * Cumple el propósito de activar un hilo, manejar la lógica de actualización,
 * y en cada "tick" pide al Engine que actualice a los cuerpos físicos. Luego, 
 * activa el renderer que los muestra en el frame y repite el proceso
*/

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
    
    public void update(){
        engine.step(Constants.DT);
    }
    
    public void render(){
        renderer.render();
    }
    
    @Override
    public void run() {

        double FRAME_TIME = 1000000000 / Constants.TARGET_FPS;

        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (mainThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / FRAME_TIME;

            lastTime = currentTime;

            if (delta >= 1) {
                update();
                render();
                delta--;
            }
        }
    }
}
