package vision.cadastros;

import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

public class VPetCad extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtApelidoPet;
	private JTextField txtNomePet;
	private JTextField textField_1;

	
	
	/**
	 * Create the frame.
	 */
	public VPetCad() {

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 714, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String mask;
		txtNomePet = new JTextField();
		txtNomePet.setBounds(124, 75, 86, 20);
		contentPane.add(txtNomePet);
		
		
		txtApelidoPet = new JTextField();
		txtApelidoPet.setBounds(124, 125, 86, 20);
		contentPane.add(txtApelidoPet);
		txtApelidoPet.setColumns(10);
		txtApelidoPet.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(124, 177, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
	}
}
