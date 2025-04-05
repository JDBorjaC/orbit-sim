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
        
        Body2D mars = new Body2D(
                new Vector2D(-1.524*Constants.UA, 0),
                new Vector2D(0, 24.077*1000),
                6.419e23,
                new Circle(10, Color.RED)
        );
        
        Body2D mercury = new Body2D(
                new Vector2D(0.387*Constants.UA, 0),
                new Vector2D(0, -47.4*1000),
                3.30e23,
                new Circle(10, Color.GRAY)
        );
        
        Body2D venus = new Body2D(
                new Vector2D(0.723*Constants.UA, 0),
                new Vector2D(0, -35.02*1000),
                4.869e24,
                new Circle(10, Color.BLUE)
        );
        
        engine.add(sun);
        engine.add(earth);
        engine.add(mars);
        engine.add(mercury);
        engine.add(venus);
        
        // -------------------------------------------------------
        
        
        
        Renderer renderer = new Renderer(engine.bodies);
        frame.add(renderer);
        frame.pack(); //Adjust to renderer's desired size
        frame.setVisible(true);
        
        Gameloop gameloop = new Gameloop(engine, renderer);
        gameloop.startMainThread();
    }
}