package vision.cadastros;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.DAOTCidade;
import control.DAOTClinica;
import control.DAOTDadosUser;
import control.DAOTEndereco;
import control.DAOTEstado;
import control.DAOTUser;
import model.MTCidade;
import model.MTEndereco;
import model.MTEstado;
import vision.VMenu;
import vision.padrao.TelefoneTextField;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

import java.util.ArrayList;

import javax.swing.JButton;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VUserCad extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField edNome;
	private TelefoneTextField edTelefone;
	private JTextField edDataNascimento;
	private JTextField edCep;
	private JTextField edCidade;
	private JTextField edBairro;
	private JTextField edCpf;
	private JTextField edEmail;
	private JTextField edSenha;
	private JComboBox<String> cbUF;

	// Declarações dos Objetos
	private DAOTUser FDAOTUser = new DAOTUser();
	private DAOTEstado FDAOUF = new DAOTEstado();
	private ArrayList<MTEstado> ListEstado = new ArrayList<>();

	private DAOTEndereco FDAOTEndereco = new DAOTEndereco();
	private DAOTCidade FDAOTCidade = new DAOTCidade();
	private DAOTDadosUser FDAOTDadosUser = new DAOTDadosUser();

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
		edCep.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (getCEPExiste(Integer.valueOf(edCep.getText()))) {
					JOptionPane.showMessageDialog(null, "já existe");
				}
			}
		});
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

		edBairro = new JTextField();
		edBairro.setColumns(10);
		edBairro.setBounds(104, 70, 156, 20);
		Endereco.add(edBairro);

		JComboBox<String> cbUF = new JComboBox<String>();
		cbUF.setBounds(316, 38, 46, 22);
		Endereco.add(cbUF);

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

		edCpf = new JTextField();
		edCpf.setColumns(10);
		edCpf.setBounds(104, 11, 156, 20);
		User.add(edCpf);

		JLabel lbCpf = new JLabel("Cpf:");
		lbCpf.setHorizontalAlignment(SwingConstants.RIGHT);
		lbCpf.setBounds(48, 11, 46, 14);
		User.add(lbCpf);

		edEmail = new JTextField();
		edEmail.setColumns(10);
		edEmail.setBounds(104, 39, 156, 20);
		User.add(edEmail);

		edSenha = new JTextField();
		edSenha.setColumns(10);
		edSenha.setBounds(104, 70, 156, 20);
		User.add(edSenha);

		JComboBox<String> cbPermissao = new JComboBox<String>();
		cbPermissao.setBounds(104, 101, 156, 22);
		User.add(cbPermissao);

		JLabel lbMail = new JLabel("Email:");
		lbMail.setHorizontalAlignment(SwingConstants.RIGHT);
		lbMail.setBounds(48, 39, 46, 14);
		User.add(lbMail);

		JLabel lbSenha = new JLabel("Senha:");
		lbSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		lbSenha.setBounds(48, 70, 46, 14);
		User.add(lbSenha);

		JLabel lbPermissao = new JLabel("Permissão:");
		lbPermissao.setHorizontalAlignment(SwingConstants.RIGHT);
		lbPermissao.setBounds(22, 102, 72, 14);
		User.add(lbPermissao);

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

		edTelefone = new TelefoneTextField();
		edTelefone.setColumns(10);
		edTelefone.setBounds(104, 39, 156, 20);
		DadosUser.add(edTelefone);

		edDataNascimento = new JTextField();
		edDataNascimento.setColumns(10);
		edDataNascimento.setBounds(104, 70, 156, 20);
		DadosUser.add(edDataNascimento);

		JComboBox<String> cbGenero = new JComboBox<String>();
		cbGenero.addItem("Masculino");
		cbGenero.addItem("Feminino");
		cbGenero.setBounds(104, 101, 156, 22);
		DadosUser.add(cbGenero);

		JLabel lbTelefone = new JLabel("Telefone:");
		lbTelefone.setHorizontalAlignment(SwingConstants.RIGHT);
		lbTelefone.setBounds(48, 39, 46, 14);
		DadosUser.add(lbTelefone);

		JLabel lbDataNascimento = new JLabel("Data Nascimento:");
		lbDataNascimento.setHorizontalAlignment(SwingConstants.RIGHT);
		lbDataNascimento.setBounds(0, 70, 94, 14);
		DadosUser.add(lbDataNascimento);

		JLabel lbGenero = new JLabel("Sexo:");
		lbGenero.setHorizontalAlignment(SwingConstants.RIGHT);
		lbGenero.setBounds(22, 102, 72, 14);
		DadosUser.add(lbGenero);

		JButton btnCAD = new JButton("CADASTRAR");
		btnCAD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
//					if(FDAOTUser.getExsisteUSER(FDAOTUser,edCpf.getText())) {
					if(true) {
						
						FDAOTUser.setBDIDUSER    (FDAOTUser.getChaveID("TUSER", "BDIDUSER"));
						FDAOTUser.setBDIDCLINICA (VMenu.FIDClinica);
						FDAOTUser.setBDIDPERMICAO(1);
						FDAOTUser.setBDMAIL		 (edEmail.getText());
						FDAOTUser.setBDCPF		 (edCpf.getText());
						FDAOTUser.setBDSENHA	 (edSenha.getText());
						FDAOTUser.inserir		 (FDAOTUser);
						
						
						if(!getCEPExiste(Integer.valueOf(edCep.getText()))) {
							FDAOTCidade.setBDIDCIDADE(FDAOTCidade.getChaveID("TCidade", "BDIDCIDADE"));
							FDAOTCidade.setBDNOMECID(edCidade.getText());
							FDAOTCidade.setBDIDUF(1);
							FDAOTCidade.inserir(FDAOTCidade);
							
							FDAOTEndereco.setBDCEP(Integer.valueOf(edCep.getText()));
							FDAOTEndereco.setBDIDCIDADE(FDAOTCidade.getBDIDCIDADE());
							FDAOTEndereco.setBDBAIRRO(edBairro.getText());
							FDAOTEndereco.inserir(FDAOTEndereco);
						}
						
						FDAOTDadosUser.setBDIDUSER(FDAOTUser.getBDIDUSER());
						FDAOTDadosUser.setBDIDCLINICA(FDAOTUser.getBDIDCLINICA());
						FDAOTDadosUser.setBDCEP(Integer.valueOf(edCep.getText()));
						FDAOTDadosUser.setBDNOME(edNome.getText());
						FDAOTDadosUser.setBDGENERO(cbGenero.getSelectedItem().toString());
						FDAOTDadosUser.setBDDATANASCIMENTO(LocalDate.parse("2023-02-01"));
						FDAOTDadosUser.setBDTELEFONE(edTelefone.getText());
						FDAOTDadosUser.inserir(FDAOTDadosUser);
						
						JOptionPane.showMessageDialog(null, "Salvo com sucesso");
					} else {
						JOptionPane.showMessageDialog(null, "CPF já cadastrado");
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Erro ao salvar");
				}
			}
		});
		btnCAD.setBounds(288, 437, 132, 23);
		contentPane.add(btnCAD);

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				ListEstado = FDAOUF.ListTEstado(FDAOUF);
				for (MTEstado l : ListEstado) {
					cbUF.addItem(l.getBDSIGLAUF());
				}
			}
		});
	}

	private Boolean getCEPExiste(int prCEP) {
		// Valida se existe CEP
		ArrayList<MTEndereco> lEndereco = new ArrayList<>();
		lEndereco = FDAOTEndereco.ListTEndereco(FDAOTEndereco);
		for (MTEndereco l : lEndereco) {

			if (l.getBDCEP() == prCEP) {
				edBairro.setText(l.getBDBAIRRO());

				// Procura Cidade Vinculada
				ArrayList<MTCidade> lCidade = new ArrayList<>();
				lCidade = FDAOTCidade.ListTCidade(FDAOTCidade);
				for (MTCidade lc : lCidade) {
					if (l.getBDIDCIDADE() == lc.getBDIDCIDADE()) {
						edCidade.setText(lc.getBDNOMECID());

						// Procura Estado vinculado
						for (MTEstado le : ListEstado) {
							if (lc.getBDIDUF() == le.getBDIDUF()) {
//								cbUF.set();;
							}
						}
					}
				}
				return true;
			}
		}
		return false;
	}
}
