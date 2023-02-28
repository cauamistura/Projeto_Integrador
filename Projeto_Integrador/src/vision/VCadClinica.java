package vision;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import control.DAOTClinica;
import javax.swing.JTabbedPane;

public class VCadClinica extends JFrame {

	public DAOTClinica FDAOTClinica = new DAOTClinica();
	private JPanel contentPane;
	private JTextField edID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VCadClinica frame = new VCadClinica();
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
	public VCadClinica() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 462, 221);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		JTextField edCPF = new JTextField();
		edCPF.setBounds(158, 60, 114, 19);
		contentPane.add(edCPF);
		edCPF.setColumns(10);
		
		JTextField edSenha = new JTextField();
		edSenha.setColumns(10);
		edSenha.setBounds(158, 89, 114, 19);
		contentPane.add(edSenha);
		
		JButton btnCadastro = new JButton("Cadastro");
		btnCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String vCpf = String.valueOf(edCPF.getText());
				String vSenha = String.valueOf(edSenha.getText());
				Integer vId = Integer.valueOf(edID.getText());
				
				FDAOTClinica.setBDCNPJ(vCpf);
				FDAOTClinica.setBDSENHA(vSenha);
				FDAOTClinica.setBDIDCLINICA(vId);
				
				
				FDAOTClinica.inserir(FDAOTClinica);
				
			}
		});
		btnCadastro.setBounds(163, 134, 99, 25);
		contentPane.add(btnCadastro);
		
		JLabel lbCNPJ = new JLabel("CNPJ: ");
		lbCNPJ.setBounds(88, 62, 41, 15);
		contentPane.add(lbCNPJ);
		
		JLabel lbSenha = new JLabel("Senha:");
		lbSenha.setBounds(83, 91, 50, 15);
		contentPane.add(lbSenha);
		
		edID = new JTextField();
		edID.setColumns(10);
		edID.setBounds(158, 21, 114, 19);
		contentPane.add(edID);
		
		JLabel lbID = new JLabel("ID: ");
		lbID.setBounds(88, 23, 41, 15);
		contentPane.add(lbID);
		
	}
}
