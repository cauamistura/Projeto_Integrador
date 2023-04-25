package vision.padrao;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class lupaButton extends RoundButton{
	
	private static final long serialVersionUID = 1L;

	public lupaButton(String label) {
		super(label);
		
		ImageIcon icon = new ImageIcon("src/vision/images/teste.png");
		
		// Define a imagem como o ícone do botão
		setIcon(icon);
		
		// Define a posição do texto vertical e horizontal
		setVerticalTextPosition(SwingConstants.BOTTOM);
		setHorizontalTextPosition(SwingConstants.CENTER);
	}
	
}
