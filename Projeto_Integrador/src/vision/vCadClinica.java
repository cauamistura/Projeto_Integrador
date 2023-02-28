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

public class vCadClinica extends JFrame {

	public DAOTClinica DC = new DAOTClinica();
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vCadClinica frame = new vCadClinica();
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
	public vCadClinica() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 163);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		JTextField edCPF = new JTextField();
		edCPF.setBounds(163, 13, 114, 19);
		contentPane.add(edCPF);
		edCPF.setColumns(10);
		
		JTextField edSenha = new JTextField();
		edSenha.setColumns(10);
		edSenha.setBounds(163, 42, 114, 19);
		contentPane.add(edSenha);
		
		JButton btnCadastro = new JButton("Cadastro");
		btnCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String vCpf = String.valueOf(edCPF.getText());
				String vSenha = String.valueOf(edSenha.getText());
				
				DC.setBDCNPJ(vCpf);
				DC.setBDSENHA(vSenha);
				
				DC.inserir(DC);
				
			}
		});
		btnCadastro.setBounds(166, 79, 99, 25);
		contentPane.add(btnCadastro);
		
		JLabel lbCNPJ = new JLabel("CNPJ: ");
		lbCNPJ.setBounds(93, 15, 41, 15);
		contentPane.add(lbCNPJ);
		
		JLabel lbSenha = new JLabel("Senha:");
		lbSenha.setBounds(88, 44, 50, 15);
		contentPane.add(lbSenha);
		
	}

}
