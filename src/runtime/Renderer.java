
package runtime;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import physics.Entity;
import utils.Constants;

public class Renderer extends JPanel {
    private List<Entity> entities;
    
    public Renderer(){
        this.entities = new ArrayList();
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
        
        for (Entity entity : entities) {
            entity.draw(g2d);
        }
        
        g2d.dispose();
    }
    
    public void render(List<Entity> entities) {
        this.entities = entities;
        repaint();
    }

    
}
