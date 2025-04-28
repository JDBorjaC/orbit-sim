
package runtime;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import physics.Body2D;
import physics.Engine;
import utils.Constants;

public class Renderer extends JPanel {
    private Engine engine;
    
    public Renderer(Engine engine){
        this.engine = engine;
        setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        setBackground(Color.BLACK);
        setDoubleBuffered(true); //Allegedly better for performance
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        //Antialiasing to ease the edges
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        for (Body2D body : this.engine.bodies) {
            body.draw(g2d);
        }
        
        if (Constants.DEBUG_DRAW == 1 || Constants.DEBUG_DRAW == 2)
            this.engine.partitions.draw(g2d);
        
        g2d.dispose();
    }
    
    public void render() {
        repaint();
    }

    
}
