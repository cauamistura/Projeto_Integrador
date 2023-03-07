package vision.cadastros;

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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DAOTUser FDAOTUser = new DAOTUser();
	private JTextField edNome;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField edCep;
	private JTextField edCidade;
	private JTextField textField_5;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_6;

	/**
	 * Create the frame.
	 */
	public VUserCad() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 714, 561);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel Endereco = new JPanel();
		Endereco.setLayout(null);
		Endereco.setBorder(new EmptyBorder(5, 5, 5, 5));
		Endereco.setBounds(31, 266, 640, 116);
		contentPane.add(Endereco);
		
		edCep = new JTextField();
		edCep.setColumns(10);
		edCep.setBounds(104, 11, 156, 20);
		Endereco.add(edCep);
		
		JLabel lbCep = new JLabel("Cep:");
		lbCep.setHorizontalAlignment(SwingConstants.RIGHT);
		lbCep.setBounds(48, 11, 46, 14);
		Endereco.add(lbCep);
		
		edCidade = new JTextField();
		edCidade.setColumns(10);
		edCidade.setBounds(104, 39, 156, 20);
		Endereco.add(edCidade);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(104, 70, 156, 20);
		Endereco.add(textField_5);
		
		JComboBox<String> cbUf = new JComboBox<String>();
		cbUf.setBounds(316, 38, 46, 22);
		Endereco.add(cbUf);
		
		JLabel lbCidade = new JLabel("Cidade:");
		lbCidade.setHorizontalAlignment(SwingConstants.RIGHT);
		lbCidade.setBounds(48, 39, 46, 14);
		Endereco.add(lbCidade);
		
		JLabel lbBairro = new JLabel("Bairro:");
		lbBairro.setHorizontalAlignment(SwingConstants.RIGHT);
		lbBairro.setBounds(48, 70, 46, 14);
		Endereco.add(lbBairro);
		
		JLabel lbEstado = new JLabel("UF:");
		lbEstado.setHorizontalAlignment(SwingConstants.RIGHT);
		lbEstado.setBounds(270, 42, 36, 14);
		Endereco.add(lbEstado);
		
		JPanel User = new JPanel();
		User.setLayout(null);
		User.setBorder(new EmptyBorder(5, 5, 5, 5));
		User.setBounds(13, 11, 354, 233);
		contentPane.add(User);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(104, 11, 156, 20);
		User.add(textField_3);
		
		JLabel lbCpf_1 = new JLabel("Cpf:");
		lbCpf_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lbCpf_1.setBounds(48, 11, 46, 14);
		User.add(lbCpf_1);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(104, 39, 156, 20);
		User.add(textField_4);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(104, 70, 156, 20);
		User.add(textField_6);
		
		JComboBox<String> cbPermissao_1 = new JComboBox<String>();
		cbPermissao_1.setBounds(104, 101, 156, 22);
		User.add(cbPermissao_1);
		
		JLabel lbMail_1 = new JLabel("Email:");
		lbMail_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lbMail_1.setBounds(48, 39, 46, 14);
		User.add(lbMail_1);
		
		JLabel lbSenha_1 = new JLabel("Senha:");
		lbSenha_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lbSenha_1.setBounds(48, 70, 46, 14);
		User.add(lbSenha_1);
		
		JLabel lbPermissao_1 = new JLabel("Permissão:");
		lbPermissao_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lbPermissao_1.setBounds(22, 102, 72, 14);
		User.add(lbPermissao_1);
		
		JPanel DadosUser = new JPanel();
		DadosUser.setBounds(377, 11, 311, 233);
		contentPane.add(DadosUser);
		DadosUser.setLayout(null);
		DadosUser.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		edNome = new JTextField();
		edNome.setColumns(10);
		edNome.setBounds(104, 11, 156, 20);
		DadosUser.add(edNome);
		
		JLabel lbNome = new JLabel("Nome:");
		lbNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lbNome.setBounds(48, 11, 46, 14);
		DadosUser.add(lbNome);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(104, 39, 156, 20);
		DadosUser.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(104, 70, 156, 20);
		DadosUser.add(textField_2);
		
		JComboBox<String> cbGenero = new JComboBox<String>();
		cbGenero.setBounds(104, 101, 156, 22);
		DadosUser.add(cbGenero);
		
		JLabel lbMail = new JLabel("Email:");
		lbMail.setHorizontalAlignment(SwingConstants.RIGHT);
		lbMail.setBounds(48, 39, 46, 14);
		DadosUser.add(lbMail);
		
		JLabel lbDataNacimento = new JLabel("Data Nacimento:");
		lbDataNacimento.setHorizontalAlignment(SwingConstants.RIGHT);
		lbDataNacimento.setBounds(0, 70, 94, 14);
		DadosUser.add(lbDataNacimento);
		
		JLabel lbGenero = new JLabel("Sexo:");
		lbGenero.setHorizontalAlignment(SwingConstants.RIGHT);
		lbGenero.setBounds(22, 102, 72, 14);
		DadosUser.add(lbGenero);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(311, 434, 89, 23);
		contentPane.add(btnNewButton_1);
	}
}
