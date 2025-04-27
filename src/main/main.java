package main;

import javax.swing.JFrame;
import physics.Engine;
import physics.Body2D;
import runtime.Gameloop;
import runtime.Renderer;
import utils.Vector2D;
import java.awt.Color;
import java.util.Random;
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
        
        double ua = Constants.UA;
        
        Body2D sun = new Body2D(
                new Vector2D(0,0), 
                1.9890e30,
                new Circle(9, Color.ORANGE)
        );
        
        Body2D earth = new Body2D(
                new Vector2D(ua, 0), 
                new Vector2D(0, 29.783e3), //v0
                5.9742e24,
                new Circle(5, Color.GREEN)
        );
        
        Body2D mercury = new Body2D(
                new Vector2D(0.387*ua, 0),
                new Vector2D(0, -47.4*1000),
                3.30e23,
                new Circle(5, Color.GRAY)
        );
        
        engine.add(sun);
        
        Random random = new Random(); 
        for (int i = 0; i < 1000; i++) {
            
            double angle = random.nextDouble()*2*Math.PI; //0 a 360 grados
            double radius = 0.387*ua + (12-0.387)*ua*random.nextDouble();
            
            double v0 = Math.sqrt((Constants.G*sun.mass/radius));
            
            double mass = mercury.mass + (earth.mass - mercury.mass)*random.nextDouble();
            
            engine.add( new Body2D(
                    new Vector2D(radius*Math.cos(angle),radius*Math.sin(angle)), //Distancia r separada en componentes
                    new Vector2D(-v0*Math.sin(angle), v0*Math.cos(angle)), // Vector velocidad perpendicular a la distancia al sol
                    mass,
                    new Circle(1, Color.WHITE)
            ));
        }
        
        // -------------------------------------------------------
        
        
        
        Renderer renderer = new Renderer(engine.bodies);
        frame.add(renderer);
        frame.pack(); //Adjust to renderer's desired size
        frame.setVisible(true);
        
        Gameloop gameloop = new Gameloop(engine, renderer);
        gameloop.startMainThread();
    }
}