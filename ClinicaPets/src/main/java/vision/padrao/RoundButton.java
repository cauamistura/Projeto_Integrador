package vision.padrao;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

public class RoundButton extends JButton implements MouseListener {
	private static final long serialVersionUID = 1L;
	private Shape shape;
	
	public RoundButton(String label) {
		super(label);
		setOpaque(false);
		setBackground(new Color(255, 199, 0));
		setForeground(Color.BLACK);
		
		addMouseListener(this); // adiciona o ouvinte de mouse ao botão
	}
	
	protected void paintComponent(Graphics g) {
		g.setColor(getBackground());
		g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
		super.paintComponent(g);
	}

	protected void paintBorder(Graphics g) {
		g.setColor(getForeground());
		g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
	}
	
	public boolean contains(int x, int y) {
		if (shape == null || !shape.getBounds().equals(getBounds())) {
			shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 20, 20);
		}
		return shape.contains(x, y);
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		setBackground(new Color(255, 255, 0));  // muda a cor do botão quando o mouse entra nele
	}

	@Override
	public void mouseExited(MouseEvent e) {
		setBackground(new Color(255, 199, 0)); // restaura a cor original do botão quando o mouse sai dele
	}

	@Override
	public void mousePressed(MouseEvent e) {
		setBackground(new Color(255, 220, 0)); // muda a cor do botão quando ele é pressionado
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		setBackground(new Color(255, 199, 0)); // restaura a cor original do botão quando o botão é solto
	}
}