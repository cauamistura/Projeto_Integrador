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
		setBounds(100, 100, 450, 162);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		edCNPJ = new JTextField();
		edCNPJ.setBounds(138, 11, 86, 20);
		contentPane.add(edCNPJ);
		edCNPJ.setColumns(10);
		
		edSenha = new JTextField();
		edSenha.setBounds(138, 42, 86, 20);
		contentPane.add(edSenha);
		edSenha.setColumns(10);
		
		JLabel lbCnpj = new JLabel("CNPJ:");
		lbCnpj.setBounds(64, 14, 46, 14);
		contentPane.add(lbCnpj);
		
		JLabel lbSenha = new JLabel("Senha:");
		lbSenha.setBounds(64, 45, 46, 14);
		contentPane.add(lbSenha);
		
		JLabel lbAlerta = new JLabel("<Aguardando>");
		lbAlerta.setHorizontalAlignment(SwingConstants.CENTER);
		lbAlerta.setBounds(64, 85, 160, 14);
		contentPane.add(lbAlerta);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<MTClinica> TListClinica = new ArrayList<>();
				TListClinica = FDAOTClinica.ListTClinica();
				if (getExiste(TListClinica)) {
					lbAlerta.setText("User Cadastrado!");
				} else {
					lbAlerta.setText("User não cadastrado!");
				}
			}
		});
		btnLogin.setBounds(261, 10, 89, 23);
		contentPane.add(btnLogin);
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
