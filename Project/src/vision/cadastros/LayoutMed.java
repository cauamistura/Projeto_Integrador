package vision.cadastros;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class LayoutMed extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LayoutMed frame = new LayoutMed();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LayoutMed() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[100px][1100px,grow][100px]", "[100px][600px,grow][100px]"));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, "cell 1 1,grow");
		panel_1.setLayout(new MigLayout("", "[450px,grow][630px,grow]", "[grow]"));
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, "cell 0 0,grow");
		panel_3.setLayout(new MigLayout("", "[grow]", "[80px,grow][110px,grow][80px,grow]"));
		
		JPanel panel_6 = new JPanel();
		panel_3.add(panel_6, "cell 0 0,grow");
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5, "cell 0 1,grow");
		panel_5.setLayout(new MigLayout("", "[grow]", "[][][][100px]"));
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		panel_5.add(lblNewLabel_1, "flowy,cell 0 1");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		panel_5.add(textField_1, "cell 0 1,growx");
		
		JLabel lblNewLabel = new JLabel("New label");
		panel_5.add(lblNewLabel, "flowy,cell 0 2");
		
		textField = new JTextField();
		panel_5.add(textField, "cell 0 2,growx");
		textField.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4, "cell 0 2,grow");
		panel_4.setLayout(new MigLayout("", "[100px][][100px][][100px][][100px]", "[][]"));
		
		JButton btnNewButton = new JButton("New button");
		panel_4.add(btnNewButton, "cell 1 1");
		
		JButton btnNewButton_1 = new JButton("New button");
		panel_4.add(btnNewButton_1, "cell 3 1");
		
		JButton btnNewButton_2 = new JButton("New button");
		panel_4.add(btnNewButton_2, "cell 5 1");
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, "cell 1 0,grow");
	}

}
