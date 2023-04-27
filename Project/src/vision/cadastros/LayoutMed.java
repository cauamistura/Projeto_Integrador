package vision.cadastros;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;

import net.miginfocom.swing.MigLayout;
import vision.padrao.PanelComBackgroundImage;
import vision.padrao.RoundButton;
import vision.padrao.RoundJTextField;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class LayoutMed extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

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
		BufferedImage bg = null;
		;
		try {
			bg = ImageIO.read(new File("src/vision/images/BGLogin.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(158, 174, 255));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[80px][1000px,grow][80px]", "[100px][600px,grow][100px]"));
		
		JPanel panel_1 = new PanelComBackgroundImage(bg);
		panel_1.setBackground(new Color(158, 174, 255));
		panel.add(panel_1, "cell 1 1,alignx center");
		panel_1.setLayout(new MigLayout("", "[350px,grow][520px,grow]", "[500px,grow]"));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(125, 137, 245));
		panel_1.add(panel_3, "cell 0 0,grow");
		panel_3.setLayout(new MigLayout("", "[grow]", "[80px,grow][110px,grow][70px,grow]"));
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(125, 137, 245));
		panel_3.add(panel_6, "cell 0 0,grow");
		panel_6.setLayout(new MigLayout("", "[100px][][100px]", "[]"));
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(LayoutMed.class.getResource("/vision/images/med.png")));
		panel_6.add(lblNewLabel_2, "cell 1 0,alignx center,aligny center");
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(125, 137, 245));
		panel_3.add(panel_5, "cell 0 1,grow");
		panel_5.setLayout(new MigLayout("", "[grow]", "[][][][][][50px]"));
		
		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		panel_5.add(lblNewLabel_1, "flowy,cell 0 1");
		
		textField_1 =  new RoundJTextField();
		textField_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		textField_1.setColumns(10);
		panel_5.add(textField_1, "cell 0 1,growx");
		
		JLabel lblNewLabel = new JLabel("Descrição:");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		panel_5.add(lblNewLabel, "flowy,cell 0 2");
		
		textField =  new RoundJTextField();
		textField.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panel_5.add(textField, "cell 0 2,growx");
		textField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Status: Inserindo Medicamento");
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		panel_5.add(lblNewLabel_3, "cell 0 5");
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(125, 137, 245));
		panel_3.add(panel_4, "cell 0 2,grow");
		panel_4.setLayout(new MigLayout("", "[100px][][100px][][100px][][100px]", "[][]"));
		
		JButton btnNewButton = new RoundButton("Comfirmar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		panel_4.add(btnNewButton, "cell 1 1");
		
		JButton btnNewButton_1 = new RoundButton("Limpar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		panel_4.add(btnNewButton_1, "cell 3 1");
		
		JButton btnNewButton_2 = new RoundButton("Deletar");
		btnNewButton_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		panel_4.add(btnNewButton_2, "cell 5 1");
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(125, 137, 245));
		panel_1.add(panel_2, "cell 1 0,grow");
		panel_2.setLayout(new MigLayout("", "[][grow][]", "[40px][180px][100px][250px,grow][100px][100px]"));
		
		JLabel lblNewLabel_4 = new JLabel("Cadastro Medicamento");
		lblNewLabel_4.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		panel_2.add(lblNewLabel_4, "cell 1 1,alignx center,aligny top");
		
		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane, "cell 1 3,grow");
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}

}
