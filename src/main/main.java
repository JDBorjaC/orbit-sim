package main;

import javax.swing.JFrame;
import physics.Engine;
import physics.Body2D;
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
        
        Engine engine = new Engine();
        
        
        // -------------------CUERPOS CELESTES--------------------
        
        Body2D sun = new Body2D(
                new Vector2D(0,0), 
                1.9890e30,
                new Circle(50, Color.ORANGE)
        );
        
        Body2D earth = new Body2D(
                new Vector2D(Constants.UA, 0), 
                new Vector2D(0, 29.783e3), //v0
                5.9742e24,
                new Circle(10, Color.GREEN)
        );
        
        engine.add(sun);
        engine.add(earth);
        
        // -------------------------------------------------------
        
        
        
        Renderer renderer = new Renderer(engine.bodies);
        frame.add(renderer);
        frame.pack(); //Adjust to renderer's desired size
        frame.setVisible(true);
        
        Gameloop gameloop = new Gameloop(engine, renderer);
        gameloop.startMainThread();
    }
}