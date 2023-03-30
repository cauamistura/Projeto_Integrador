package vision.cadastros;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import control.DAOTCidade;
import control.DAOTDadosUser;
import control.DAOTEndereco;
import control.DAOTEstado;
import control.DAOTUser;
import model.MTCidade;
import model.MTDadosUser;
import model.MTEndereco;
import model.MTEstado;
import model.MTUser;
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

	// Declarações dos Objetos
	private DAOTUser FDAOTUser = new DAOTUser();
	private DAOTEstado FDAOUF = new DAOTEstado();
	private ArrayList<MTEstado> ListEstado;

	private DAOTEndereco FDAOTEndereco = new DAOTEndereco();
	private DAOTCidade FDAOTCidade = new DAOTCidade();
	private DAOTDadosUser FDAOTDadosUser = new DAOTDadosUser();

	/**
	 * Create the frame.
	 */
	public VUserCad() {
		setTitle("Cadastro de Usuário");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		edCep.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 3, 0, 0));
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
		User.setBounds(13, 11, 354, 233);
		contentPane.add(User);
		
		JLabel lbStatus = new JLabel("Status: Aguardando");
		lbStatus.setBounds(13, 498, 110, 14);
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
		edCpf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F9) {;
			        List<MTDadosUser> lista = FDAOTDadosUser.ListConsulta(FDAOTDadosUser);			   
			        VUserCON frame = new VUserCON(lista);
			        frame.setVisible(true);
		        }
			}
		});
		edCpf.setColumns(10);
		edCpf.setBounds(104, 11, 156, 20);
		edCpf.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 3, 0, 0));
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
		edTelefone.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 3, 0, 0));
		edTelefone.setColumns(10);
		edTelefone.setBounds(104, 39, 156, 20);
		DadosUser.add(edTelefone);

		edDataNascimento = new DateTextField();
		edDataNascimento.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 3, 0, 0));
		edDataNascimento.setColumns(10);
		edDataNascimento.setBounds(104, 70, 94, 20);
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

		JButton btnCAD = new RoundButton("CONFIRMAR");
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
						FDAOTUser.setBDIDUSER(edCpf.getIDUser(FDAOTUser));
					} else {
						FDAOTUser.setBDIDUSER(FDAOTUser.getChaveID("TUSER", "BDIDUSER"));
					}
					FDAOTUser.setBDIDCLINICA(VMenu.FIDClinica);
					FDAOTUser.setBDIDPERMICAO(1);
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
					FDAOTDadosUser.setBDTELEFONE(edTelefone.getTelefone());

					if (existeCpf) {
						FDAOTDadosUser.alterar(FDAOTDadosUser);
					} else {
						FDAOTDadosUser.inserir(FDAOTDadosUser);
					}
					
					JOptionPane.showMessageDialog(null, "Salvo com sucesso");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Erro ao salvar");
				}
			}
		});
		btnCAD.setBounds(288, 437, 132, 23);
		contentPane.add(btnCAD);
		
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
								cbUF.setSelectedIndex(lc.getBDIDUF()-1);
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
