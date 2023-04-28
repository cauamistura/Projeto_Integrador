package vision.padrao;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPasswordField;

public class RoundJPasswordField extends JPasswordField{
	
	private static final long serialVersionUID = 1L;
	private Shape shape;
	private int radii = 10;
    Color color = new Color(45, 45 , 45);
    
   
    public RoundJPasswordField() {
        super();
        setDisabledTextColor(getForeground());
        setBackground(getBackground());
        setOpaque(false);
    }
    
    protected void paintComponent(Graphics g) {
         g.setColor(getBackground());
         g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, radii, radii);
         super.paintComponent(g);
    }
    protected void paintBorder(Graphics g) {
         g.setColor(this.color);
         g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, radii, radii);
         
    }
    public boolean contains(int x, int y) {
         if (shape == null || !shape.getBounds().equals(getBounds())) {
             shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, radii, radii);
         }
         return shape.contains(x, y);
    }
}
