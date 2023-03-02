package vision;

import java.awt.EventQueue; 

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import control.DAOTClinica;
import model.MTClinica;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;

public class VLoginClinicaCON extends JFrame {

	private JPanel contentPane;
	private JTextField edCNPJ;
	private JTextField edSenha;
	private DAOTClinica FDAOTClinica = new DAOTClinica();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VLoginClinicaCON frame = new VLoginClinicaCON();
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
	public VLoginClinicaCON() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(158, 174, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		edCNPJ = new RoundJTextField(15);
		edCNPJ.setBounds(78, 232, 284, 31);
		contentPane.add(edCNPJ);
		edCNPJ.setColumns(10);
		
		edSenha = new RoundJTextField(15);
		edSenha.setBounds(78, 317, 284, 31);
		contentPane.add(edSenha);
		edSenha.setColumns(10);
		
		JLabel lbCnpj = new JLabel("CNPJ:");
		lbCnpj.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbCnpj.setBounds(78, 209, 46, 14);
		contentPane.add(lbCnpj);
		
		JLabel lbSenha = new JLabel("Senha:");
		lbSenha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbSenha.setBounds(78, 294, 56, 14);
		contentPane.add(lbSenha);
		
		JLabel lbAlerta = new JLabel("<Aguardando>");
		lbAlerta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbAlerta.setHorizontalAlignment(SwingConstants.CENTER);
		lbAlerta.setBounds(131, 488, 176, 26);
		contentPane.add(lbAlerta);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setIcon(null);
		btnLogin.setForeground(new Color(0, 0, 0));
		btnLogin.setBackground(new Color(255, 199, 0));
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 12));
	
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<MTClinica> TListClinica = new ArrayList<>();
				TListClinica = FDAOTClinica.ListTClinica(FDAOTClinica);
				if (getExiste(TListClinica)) {
					lbAlerta.setText("User Cadastrado!");
				} else {
					lbAlerta.setText("User não cadastrado!");
				}
			}
		});
		btnLogin.setBounds(78, 395, 284, 37);
		contentPane.add(btnLogin);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(VLoginClinicaCON.class.getResource("/vision/imagen/Group (2).png")));
		lblNewLabel_1.setBounds(155, 47, 135, 145);
		contentPane.add(lblNewLabel_1);
		
		JLabel fundo = new JLabel("");
		fundo.setIcon(new ImageIcon(VLoginClinicaCON.class.getResource("/vision/imagen/BG (1).png")));
		fundo.setBounds(64, 24, 311, 514);
		contentPane.add(fundo);
	}
	private Boolean getExiste(ArrayList<MTClinica> prList) {
		Boolean wValida = false;
		for (MTClinica l : prList) {
			if(l.getBDCNPJ().equals(edCNPJ.getText()) && l.getBDSENHA().equalsIgnoreCase(edSenha.getText())) {
				wValida = true;
				break;
			}
		}
		if (wValida) {
			return true;
		} else {
			return false;
		}
	}
}
