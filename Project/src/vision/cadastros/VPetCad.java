package vision.cadastros;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import control.DAOTDadosUser;
import control.DAOTPet;
import control.DAOTPetUser;
import control.DAOTUser;
import model.MTDadosUser;
import model.MTEstado;
import model.MTRaca;
import model.MTUser;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
	public DAOTUser FDAOTUser = new DAOTUser();
	public DAOTDadosUser FDAOTDadosUser = new DAOTDadosUser();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtApelidoPet;
	private JTextField txtNomePet;
	private JTextField txtDataNasc;

	
	
	/**
	 * Create the frame.
	 */
	public VPetCad() {
		setTitle("Cadastro de pets");

		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 319, 288);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNomePet = new JTextField();
		txtNomePet.setBounds(158, 24, 86, 20);
		contentPane.add(txtNomePet);
		
		
		txtApelidoPet = new JTextField();
		txtApelidoPet.setBounds(158, 74, 86, 20);
		contentPane.add(txtApelidoPet);
		txtApelidoPet.setColumns(10);
		txtApelidoPet.setColumns(10);
		
		try {
			txtDataNasc = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e2) {
			JOptionPane.showMessageDialog(null, "Data inválida");
			e2.printStackTrace();
		}
		txtDataNasc.setBounds(158, 126, 86, 20);
		contentPane.add(txtDataNasc);
		txtDataNasc.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(102, 27, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblApelidoopcional = new JLabel("Apelido (opcional):");
		lblApelidoopcional.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApelidoopcional.setBounds(36, 77, 114, 14);
		contentPane.add(lblApelidoopcional);
		
		JLabel lblDataDeNasc = new JLabel("Data de nasc:");
		lblDataDeNasc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataDeNasc.setBounds(48, 129, 100, 14);
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
				
				try {
					FDAOTPet.setBDDATANASCIMENTO(LocalDate.parse(txtDataNasc.getText(), formatter));
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "Data inválida. Tente novamente.");
					return;
				}
				
				
				try {
					FDAOTPet.inserir(FDAOTPet);
					txtNomePet.setText("");
					txtApelidoPet.setText("");
					txtDataNasc.setText("");
					JOptionPane.showMessageDialog(null, "Seu pet foi cadastrado com sucesso!");
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Não foi possível cadastrar o pet");
				}
				
			}
		});
		btnNewButton.setBounds(68, 193, 174, 31);
		contentPane.add(btnNewButton);
		
	}
}
