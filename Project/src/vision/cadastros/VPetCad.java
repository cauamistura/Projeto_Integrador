package vision.cadastros;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import control.DAOTPet;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class VPetCad extends JFrame {

	/**
	 * 
	 */
	public DAOTPet FDAOTPet = new DAOTPet();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtApelidoPet;
	private JTextField txtNomePet;
	private JTextField txtDataNasc;

	
	
	/**
	 * Create the frame.
	 */
	public VPetCad() {
		setTitle("Cadastro de pets");

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 319, 380);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNomePet = new JTextField();
		txtNomePet.setBounds(147, 63, 86, 20);
		contentPane.add(txtNomePet);
		
		
		txtApelidoPet = new JTextField();
		txtApelidoPet.setBounds(147, 113, 86, 20);
		contentPane.add(txtApelidoPet);
		txtApelidoPet.setColumns(10);
		txtApelidoPet.setColumns(10);
		
		txtDataNasc = new JTextField();
		txtDataNasc.setBounds(147, 165, 86, 20);
		contentPane.add(txtDataNasc);
		txtDataNasc.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(91, 66, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblApelidoopcional = new JLabel("Apelido (opcional):");
		lblApelidoopcional.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApelidoopcional.setBounds(25, 116, 114, 14);
		contentPane.add(lblApelidoopcional);
		
		JLabel lblDataDeNasc = new JLabel("Data de nasc:");
		lblDataDeNasc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataDeNasc.setBounds(37, 168, 100, 14);
		contentPane.add(lblDataDeNasc);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				
				if(txtNomePet.getText().isEmpty() || txtNomePet.getText() == null) {
					JOptionPane.showMessageDialog(null, "Campo vazio: Nome");
					return;
				}
				
				if(txtApelidoPet.getText().isEmpty() || txtApelidoPet.getText() == null) {
					JOptionPane.showMessageDialog(null, "Campo vazio: Apelido");
					return;
				}
				
				if(txtDataNasc.getText().isEmpty() || txtDataNasc.getText() == null) {
					JOptionPane.showMessageDialog(null, "Campo vazio: Data de nascimento");
					return;
				}
				
				FDAOTPet.setBDIDPET(FDAOTPet.getChaveID("TPets", "BDIDPET"));
				FDAOTPet.setBDIDRACA(FDAOTPet.getChaveID("TPets", "BDIDRACA"));
				FDAOTPet.setBDNOMEPET(txtNomePet.getText());
				FDAOTPet.setBDAPELIDO(txtApelidoPet.getText());
				FDAOTPet.setBDDATANASCIMENTO(LocalDate.parse(txtDataNasc.getText(), formatter));
				
				try {
					FDAOTPet.inserir(FDAOTPet);
					JOptionPane.showMessageDialog(null, "Seu pet foi cadastrado com sucesso!");
				} catch (Exception e1) {
					e1.printStackTrace();
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Não foi possível cadastrar o pet");
				}
				
			}
		});
		btnNewButton.setBounds(59, 239, 174, 53);
		contentPane.add(btnNewButton);
	}
}
