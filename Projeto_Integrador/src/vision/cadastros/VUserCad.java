package vision.cadastros;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.DAOTUser;
import vision.VMenu;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VUserCad extends JFrame {

	private JPanel contentPane;
	private JTextField edCpf;
	private JTextField edEmail;
	private JTextField edSenha;
	private DAOTUser FDAOTUser = new DAOTUser();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VUserCad frame = new VUserCad();
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
	public VUserCad() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 714, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		edCpf = new JTextField();
		edCpf.setBounds(120, 33, 156, 20);
		contentPane.add(edCpf);
		edCpf.setColumns(10);
		
		JLabel lbCpf = new JLabel("Cpf:");
		lbCpf.setHorizontalAlignment(SwingConstants.RIGHT);
		lbCpf.setBounds(51, 36, 46, 14);
		contentPane.add(lbCpf);
		
		edEmail = new JTextField();
		edEmail.setColumns(10);
		edEmail.setBounds(120, 64, 156, 20);
		contentPane.add(edEmail);
		
		edSenha = new JTextField();
		edSenha.setColumns(10);
		edSenha.setBounds(120, 92, 156, 20);
		contentPane.add(edSenha);
		
		JComboBox<String> cbPermissao = new JComboBox<String>();
		cbPermissao.addItem("Permissão");
		cbPermissao.setBounds(120, 123, 156, 22);
		contentPane.add(cbPermissao);
		
		JLabel lbMail = new JLabel("Email:");
		lbMail.setHorizontalAlignment(SwingConstants.RIGHT);
		lbMail.setBounds(51, 67, 46, 14);
		contentPane.add(lbMail);
		
		JLabel lbSenha = new JLabel("Senha:");
		lbSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		lbSenha.setBounds(51, 95, 46, 14);
		contentPane.add(lbSenha);
		
		JLabel lbPermissao = new JLabel("Permissão:");
		lbPermissao.setHorizontalAlignment(SwingConstants.RIGHT);
		lbPermissao.setBounds(25, 127, 72, 14);
		contentPane.add(lbPermissao);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FDAOTUser.setBDIDUSER	(FDAOTUser.getChaveID("TUser", "BDIDUSER"));
				FDAOTUser.setBDIDCLINICA(VMenu.FIDClinica);
				FDAOTUser.setBDCPF		(edCpf.getText());
				FDAOTUser.setBDMAIL		(edEmail.getText());
				FDAOTUser.setBDSENHA	(edSenha.getText());
				FDAOTUser.setBDIDPERMICAO(1);
				
				FDAOTUser.inserir(FDAOTUser);
				JOptionPane.showMessageDialog(null, "foi hehe");
			}
		});
		btnNewButton.setBounds(120, 173, 89, 23);
		contentPane.add(btnNewButton);
	}
}
