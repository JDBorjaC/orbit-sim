
package main;

import javax.swing.JFrame;
import physics.Engine;
import physics.HeavenlyBody;
import runtime.Gameloop;
import runtime.Renderer;
import utils.Vector2D;
import java.awt.Color;
import physics.Circle;
import utils.Constants;

public class main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Ley de gravitación universal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        //frame.setLocationRelativeTo(null); //Center the frame
        
        Renderer renderer = new Renderer();
        frame.add(renderer);
        frame.pack(); //Adjust to renderer's desired size
        frame.setVisible(true);
        
        Engine engine = new Engine();
        
        //Orbital bodies instantiation
        HeavenlyBody sun = new HeavenlyBody(
                new Vector2D(0,0), 
                1.9890e30f,
                new Circle(50, Color.ORANGE)
        );
        
        HeavenlyBody earth = new HeavenlyBody(
                new Vector2D(Constants.UA, 0), 
                new Vector2D(0, 29.783e3f),
                5.9742e24f,
                new Circle(5, Color.GREEN)
        );
        
        engine.add(sun);
        engine.add(earth);
        
        Gameloop gameloop = new Gameloop(engine, renderer);
        gameloop.startMainThread();
    }
}
