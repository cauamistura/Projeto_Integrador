package vision.cadastros;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.DAOTPet;
import control.DAOTRaca;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class VRacaCad extends JFrame {

	public DAOTRaca FDAOTRaca = new DAOTRaca();
	private JPanel contentPane;
	private JTextField txtNomeRaca;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VRacaCad frame = new VRacaCad();
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
	public VRacaCad() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 311, 172);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNomeRaca = new JTextField();
		txtNomeRaca.setBounds(110, 30, 121, 20);
		contentPane.add(txtNomeRaca);
		txtNomeRaca.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(54, 33, 46, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				
				if(txtNomeRaca.getText().isEmpty() || txtNomeRaca.getText() == null) {
					JOptionPane.showMessageDialog(null, "Campo vazio: Nome");
					return;
				}
				
				FDAOTRaca.setBDIDRACA(1);
				FDAOTRaca.setBDNOMERACA(txtNomeRaca.getText());
				FDAOTRaca.setBDIDESPECIE(11);
	
				try {
					FDAOTRaca.inserir(FDAOTRaca);
					txtNomeRaca.setText("");
					JOptionPane.showMessageDialog(null, "Raça cadastrada com sucesso!");
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Não foi possível cadastrar a raça.");
				}
			}
		});
		btnNewButton.setBounds(71, 83, 143, 23);
		contentPane.add(btnNewButton);
	}
}
