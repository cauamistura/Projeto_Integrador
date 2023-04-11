package vision.cadastros;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import control.DAOTCidade;
import control.DAOTDadosUser;
import control.DAOTEndereco;
import control.DAOTEstado;
import control.DAOTPermicao;
import control.DAOTUser;
import model.MTDadosUser;
import model.MTEndereco;
import model.MTEstado;
import model.MTPermicao;
import vision.VMenu;
import vision.consultas.VUserCON;
import vision.padrao.CEPTextField;
import vision.padrao.CPFTextField;
import vision.padrao.DateTextField;
import vision.padrao.RoundButton;
import vision.padrao.RoundJTextField;
import vision.padrao.TelefoneTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VUserCad extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField edNome;
	private JTextField edCidade;
	private JTextField edBairro;
	private JTextField edEmail;
	private JTextField edSenha;
	private CPFTextField edCpf;
	private CEPTextField edCep;
	private DateTextField edDataNascimento;
	private TelefoneTextField edTelefone;

	private JComboBox<MTEstado> cbUF;
	private JComboBox<MTPermicao> cbPermissao;
	private JComboBox<String> cbGenero;

	// Declarações dos Objetos
	private DAOTUser FDAOTUser = new DAOTUser();
	private DAOTEstado FDAOUF = new DAOTEstado();
	private ArrayList<MTEstado> ListEstado;

	private DAOTEndereco FDAOTEndereco = new DAOTEndereco();
	private DAOTCidade FDAOTCidade = new DAOTCidade();
	private DAOTDadosUser FDAOTDadosUser = new DAOTDadosUser();
	private DAOTPermicao FDAOTPermicao = new DAOTPermicao();

	/**
	 * Create the frame.
	 */
	public VUserCad() {
		setBackground(new Color(255, 255, 255));
		setTitle("Cadastro de Usuário");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 714, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel Endereco = new JPanel();
		Endereco.setLayout(null);
		Endereco.setBorder(new EmptyBorder(5, 5, 5, 5));
		Endereco.setBounds(13, 174, 640, 116);
		contentPane.add(Endereco);

		edCep = new CEPTextField();
		edCep.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (edCep.getCEP() != null) {
					if (getCEPExiste(Integer.valueOf(edCep.getCEP()))) {
						JOptionPane.showMessageDialog(null, "já existe");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Cep invalido");
					edCep.requestFocus();
				}
			}
		});
		edCep.setColumns(10);
		edCep.setBounds(104, 11, 156, 20);
		edCep.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
		Endereco.add(edCep);

		JLabel lbCep = new JLabel("Cep:");
		lbCep.setHorizontalAlignment(SwingConstants.RIGHT);
		lbCep.setBounds(48, 11, 46, 14);
		Endereco.add(lbCep);

		edCidade = new RoundJTextField();
		edCidade.setBorder(new EmptyBorder(3, 3, 3, 3));
		edCidade.setColumns(10);
		edCidade.setBounds(104, 39, 156, 20);
		Endereco.add(edCidade);

		edBairro = new RoundJTextField();
		edBairro.setBorder(new EmptyBorder(3, 3, 3, 3));
		edBairro.setColumns(10);
		edBairro.setBounds(104, 70, 156, 20);
		Endereco.add(edBairro);

		cbUF = new JComboBox<>();
		cbUF.setBounds(316, 38, 46, 22);
		Endereco.add(cbUF);
		ListEstado = new ArrayList<>();
		ListEstado = FDAOUF.ListTEstado(FDAOUF);
		for (MTEstado mtEstado : ListEstado) {
			cbUF.addItem(mtEstado);
		}
		
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
		User.setBounds(13, 11, 354, 152);
		contentPane.add(User);

		JLabel lbStatus = new JLabel("Status: Aguardando");
		lbStatus.setBounds(13, 351, 110, 14);
		contentPane.add(lbStatus);

		edCpf = new CPFTextField();
		edCpf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (edCpf.existeCpfUsuario(FDAOTUser)) {
					lbStatus.setText("Status: Alterando");
				} else {
					lbStatus.setText("Status: Inserindo");
				}
			}
		});

		VUserCad local = this;
		edCpf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F9) {
					abreConsulta(local);
				}
			}
		});
		edCpf.setColumns(10);
		edCpf.setBounds(104, 11, 156, 20);
		edCpf.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
		edCpf.setToolTipText("Aperte F9 para consultar");
		User.add(edCpf);

		JLabel lbCpf = new JLabel("Cpf:");
		lbCpf.setHorizontalAlignment(SwingConstants.RIGHT);
		lbCpf.setBounds(48, 11, 46, 14);
		User.add(lbCpf);

		edEmail = new RoundJTextField();
		edEmail.setBorder(new EmptyBorder(3, 3, 3, 3));
		edEmail.setColumns(10);
		edEmail.setBounds(104, 39, 156, 20);
		User.add(edEmail);

		edSenha = new RoundJTextField();
		edSenha.setBorder(new EmptyBorder(3, 3, 3, 3));
		edSenha.setColumns(10);
		edSenha.setBounds(104, 70, 156, 20);
		User.add(edSenha);
		
		cbPermissao = new JComboBox<>();
		cbPermissao.setBounds(104, 101, 156, 22);
		ArrayList<MTPermicao> listPermicao = new ArrayList<>();
		listPermicao = FDAOTPermicao.ListTEstado(FDAOTPermicao);
		for (MTPermicao mtPermicao : listPermicao) {
			cbPermissao.addItem(mtPermicao);
		}
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
		DadosUser.setBounds(377, 11, 311, 152);
		contentPane.add(DadosUser);
		DadosUser.setLayout(null);
		DadosUser.setBorder(new EmptyBorder(5, 5, 5, 5));

		edNome = new RoundJTextField();
		edNome.setBorder(new EmptyBorder(3, 3, 3, 3));
		edNome.setColumns(10);
		edNome.setBounds(104, 11, 156, 20);
		DadosUser.add(edNome);

		JLabel lbNome = new JLabel("Nome:");
		lbNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lbNome.setBounds(48, 11, 46, 14);
		DadosUser.add(lbNome);

		edTelefone = new TelefoneTextField();
		edTelefone.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
		edTelefone.setColumns(10);
		edTelefone.setBounds(104, 39, 156, 20);
		DadosUser.add(edTelefone);

		edDataNascimento = new DateTextField();
		edDataNascimento.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
		edDataNascimento.setColumns(10);
		edDataNascimento.setBounds(104, 70, 94, 20);
		DadosUser.add(edDataNascimento);

		cbGenero = new JComboBox<String>();
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

		RoundButton btnLimpar = new RoundButton("LIMPAR");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpaCampos(true);
			}
		});
		btnLimpar.setBounds(542, 301, 89, 23);
		contentPane.add(btnLimpar);

		RoundButton btnExcluir = new RoundButton("EXCLUIR");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnExcluir.setBounds(542, 335, 89, 23);
		contentPane.add(btnExcluir);

		RoundButton btnConsulta = new RoundButton("CONFIRMAR");
		btnConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abreConsulta(local);
			}
		});
		btnConsulta.setText("Consultar");
		btnConsulta.setBackground(Color.WHITE);
		btnConsulta.setBounds(377, 335, 132, 23);
		contentPane.add(btnConsulta);

		JButton btnCAD = new RoundButton("CONFIRMAR");
		btnCAD.setBounds(379, 301, 132, 23);
		contentPane.add(btnCAD);
		btnCAD.setBackground(new Color(255, 255, 255));
		btnCAD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!edCpf.validaCPF()) {
					JOptionPane.showMessageDialog(null, "Cpf Invalido");
					edCpf.setText("");
					edCpf.requestFocus();
					return;
				}

				if (!edDataNascimento.validaDate()) {
					JOptionPane.showMessageDialog(null, "Data Invalida");
					edDataNascimento.setText("");
					edDataNascimento.requestFocus();
					return;
				}

				try {
					Boolean existeCpf = edCpf.existeCpfUsuario(FDAOTUser);

					FDAOTUser.setBDCPF(edCpf.getText());
					if (existeCpf) {
						FDAOTUser.setBDIDUSER(FDAOTUser.getIDUser(FDAOTUser));
					} else {
						FDAOTUser.setBDIDUSER(FDAOTUser.getChaveID("TUSER", "BDIDUSER"));
					}
					FDAOTUser.setBDIDCLINICA(VMenu.FIDClinica);
					MTPermicao selectedItemP = (MTPermicao) cbPermissao.getSelectedItem();
					FDAOTUser.setBDIDPERMICAO(selectedItemP.getBDIDPERMICAO());
					FDAOTUser.setBDMAIL(edEmail.getText());
					FDAOTUser.setBDSENHA(edSenha.getText());

					if (existeCpf) {
						FDAOTUser.alterar(FDAOTUser);
					} else {
						FDAOTUser.inserir(FDAOTUser);
					}

					if (!getCEPExiste(Integer.valueOf(edCep.getCEP()))) {

						FDAOTCidade.setBDIDCIDADE(FDAOTCidade.getChaveID("TCidades", "BDIDCIDADE"));
						FDAOTCidade.setBDNOMECID(edCidade.getText());
						MTEstado selectedItem = (MTEstado) cbUF.getSelectedItem();
						FDAOTCidade.setBDIDUF(selectedItem.getBDIDUF());

						FDAOTCidade.inserir(FDAOTCidade);

						FDAOTEndereco.setBDCEP(Integer.valueOf(edCep.getCEP()));
						FDAOTEndereco.setBDIDCIDADE(FDAOTCidade.getBDIDCIDADE());
						FDAOTEndereco.setBDBAIRRO(edBairro.getText());

						FDAOTEndereco.inserir(FDAOTEndereco);
					}

					FDAOTDadosUser.setBDIDUSER(FDAOTUser.getBDIDUSER());
					FDAOTDadosUser.setBDIDCLINICA(FDAOTUser.getBDIDCLINICA());
					FDAOTDadosUser.setBDCEP(Integer.valueOf(edCep.getCEP()));
					FDAOTDadosUser.setBDNOME(edNome.getText());
					FDAOTDadosUser.setBDGENERO(cbGenero.getSelectedItem().toString());
					FDAOTDadosUser.setBDDATANASCIMENTO(edDataNascimento.getDate());
					FDAOTDadosUser.setBDTELEFONE(edTelefone.getText());

					if (existeCpf) {
						FDAOTDadosUser.alterar(FDAOTDadosUser);
					} else {
						FDAOTDadosUser.inserir(FDAOTDadosUser);
					}
					edCpf.requestFocus();
					int resposta = JOptionPane.showConfirmDialog(null, "Salvo com sucesso!\nDeseja limpar os campos?",
							"Confirmação", JOptionPane.YES_NO_OPTION);
					if (resposta == JOptionPane.YES_OPTION) {
						limpaCampos(false);
					}

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Erro ao salvar");
				}
			}
		});

	}

	private Boolean getCEPExiste(int prCEP) {
		// Valida se existe CEP
		ArrayList<MTEndereco> lEndereco = new ArrayList<>();
		lEndereco = FDAOTEndereco.ListTEnderecoCON(FDAOTEndereco);

		for (MTEndereco lista : lEndereco) {
			if (lista.getBDCEP().equals(prCEP)) {
				try {
					edCidade.setText(lista.getBDNOMECID());
					edBairro.setText(lista.getBDBAIRRO());
					cbUF.setSelectedIndex(lista.getBDIDUF() - 1);
					return true;
				} catch (Exception e) {
					return false;
				}
			}
		}
		return false;
	}

	public void preencheCampos(MTDadosUser prDadosUser) {
		if (prDadosUser != null) {
			edCpf.setText(prDadosUser.getBDCPF());
			edEmail.setText(prDadosUser.getBDMAIL());
			edSenha.setText(prDadosUser.getBDSENHA());
			cbPermissao.setSelectedItem(prDadosUser.getBDIDPERMICAO());
			edNome.setText(prDadosUser.getBDNOME());
			edTelefone.setText(prDadosUser.getBDTELEFONE());

			DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("ddMMyyyy");
			edDataNascimento.setText(prDadosUser.getBDDATANASCIMENTO().format(FOMATTER));

			if (prDadosUser.getBDGENERO().equalsIgnoreCase("Masculino")) {
				cbGenero.setSelectedIndex(0);
			} else {
				cbGenero.setSelectedIndex(1);
			}
			edCep.setText(prDadosUser.getBDCEP().toString());
			getCEPExiste(prDadosUser.getBDCEP());
		}
	}

	public void limpaCampos(Boolean prLimpaCPF) {
		if (prLimpaCPF) {
			edCpf.setText("");
		}
		edEmail.setText("");
		edSenha.setText("");
		edNome.setText("");
		edTelefone.setText("");
		edDataNascimento.setText("");
		cbGenero.setSelectedIndex(0);
		edCep.setText("");
		edBairro.setText("");
		edCidade.setText("");
		edCpf.requestFocus();
	}

	private void abreConsulta(VUserCad prSelf) {
		if (FDAOTDadosUser != null) {
			List<MTDadosUser> lista = FDAOTDadosUser.ListConsulta(FDAOTDadosUser);
			VUserCON frame = new VUserCON(lista, prSelf);
			frame.setVisible(true);
		} else {

		}
	}

	public void exluiUser(Integer prIDUSER) {
		int resposta = JOptionPane.showConfirmDialog(null,
				"Você realmente deseja excluir? Todos os dados vinculados a este usuário serão excluídos.",
				"Confirmação", JOptionPane.YES_NO_OPTION);

		if (resposta == JOptionPane.YES_OPTION) {
			FDAOTUser.deletar(prIDUSER);
			JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
			limpaCampos(true);
		}

	}
}
