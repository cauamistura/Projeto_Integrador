package vision.cadastros;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
import net.miginfocom.swing.MigLayout;
import vision.VMenu;
import vision.consultas.VUserCON;
import vision.padrao.CEPTextField;
import vision.padrao.CPFTextField;
import vision.padrao.DateTextField;
import vision.padrao.PanelComBackgroundImage;
import vision.padrao.RoundButton;
import vision.padrao.RoundJTextField;
import vision.padrao.TelefoneTextField;
import java.awt.Font;

public class VUserCad extends JFrame {

	/**
	 * 
	 */


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnImg;
	private DateTextField edDataNascimento;
	private JTextField edSenha;
	private TelefoneTextField edTelefone;
	private JTextField edNome;
	private JTextField edCidade;
	private CPFTextField edCpf;
	private JTextField edBairro;
	private CEPTextField edCep;
	private JTextField edEmail;

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
		BufferedImage bg = null;
		;
		try {
			bg = ImageIO.read(new File("src/vision/images/BGuser.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setBackground(new Color(255, 255, 255));
		setTitle("Cadastro de Usuário");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 873, 855);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel pnMain = new JPanel();
		pnMain.setBackground(new Color(158, 174, 255));
		contentPane.add(pnMain, BorderLayout.CENTER);
		pnMain.setLayout(new MigLayout("", "[150px][500px,grow][150px]", "[100px][600px,grow][100px]"));

		JPanel pnCard = new PanelComBackgroundImage(bg);
		pnCard.setBackground(new Color(158, 174, 255));
		pnMain.add(pnCard, "cell 1 1,alignx center");
		pnCard.setLayout(new MigLayout("", "[50.00px][500.00,grow][50px,grow]", "[grow][400px,grow]"));

		pnImg = new JPanel();
		pnImg.setBackground(new Color(125, 137, 245));

		pnCard.add(pnImg, "cell 1 0,alignx center");
		pnImg.setLayout(new MigLayout("", "[][][][][][][][][]", "[]"));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VUserCad.class.getResource("/vision/images/person.png")));
		pnImg.add(lblNewLabel, "cell 5 0,alignx center");

		JPanel pnContent = new JPanel();
		pnContent.setBackground(new Color(125, 137, 245));
		pnCard.add(pnContent, "cell 1 1,grow");
		pnContent.setLayout(
				new MigLayout("", "[50px][150px,grow][50px][150px,grow][50px]", "[][][][][][][][][25px][][30px]"));

		JLabel lblNewLabel_1 = new JLabel("Nome:");
		pnContent.add(lblNewLabel_1, "flowy,cell 1 2");

		edNome = new RoundJTextField();
		edNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		edNome.setColumns(10);
		pnContent.add(edNome, "cell 1 2,growx");

		JLabel lblNewLabel_7 = new JLabel("Email:");
		pnContent.add(lblNewLabel_7, "flowy,cell 3 2");

		edEmail = new RoundJTextField();
		edEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		edEmail.setColumns(10);
		pnContent.add(edEmail, "cell 3 2,growx");

		JLabel lblNewLabel_2 = new JLabel("Telefone:");
		pnContent.add(lblNewLabel_2, "flowy,cell 1 3");

		edTelefone = new TelefoneTextField();
		edTelefone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		edTelefone.setColumns(10);
		pnContent.add(edTelefone, "cell 1 3,growx");

		JLabel lblNewLabel_8 = new JLabel("CEP:");
		pnContent.add(lblNewLabel_8, "flowy,cell 3 3");

		edCep = new CEPTextField();
		edCep.setFont(new Font("Tahoma", Font.PLAIN, 12));
		edCep.setColumns(10);
		pnContent.add(edCep, "cell 3 3,growx");

		JLabel lblNewLabel_3 = new JLabel("Senha:");
		pnContent.add(lblNewLabel_3, "flowy,cell 1 4");

		edSenha = new RoundJTextField();
		edSenha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		edSenha.setColumns(10);
		pnContent.add(edSenha, "cell 1 4,growx");

		JLabel lblNewLabel_9 = new JLabel("Bairro:");
		pnContent.add(lblNewLabel_9, "flowy,cell 3 4");

		edBairro = new RoundJTextField();
		edBairro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		edBairro.setColumns(10);
		pnContent.add(edBairro, "cell 3 4,growx");

		JLabel lblNewLabel_4 = new JLabel("Data de Nascimento:");
		pnContent.add(lblNewLabel_4, "flowy,cell 1 5");

		edDataNascimento = new DateTextField();
		edDataNascimento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		edDataNascimento.setColumns(10);
		pnContent.add(edDataNascimento, "cell 1 5");

		JLabel lblNewLabel_10 = new JLabel("CPF:");
		pnContent.add(lblNewLabel_10, "flowy,cell 3 5");

		JLabel lbStatus = new JLabel("Status: Aguardando");
		pnContent.add(lbStatus, "cell 1 10");
		edCpf = new CPFTextField();
		edCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		edCpf.setColumns(10);
		pnContent.add(edCpf, "cell 3 5,growx");
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

		JLabel lblNewLabel_5 = new JLabel("Sexo:");
		pnContent.add(lblNewLabel_5, "flowy,cell 1 6");

		JComboBox<String> cbGenero = new JComboBox();
		cbGenero.addItem("Masculino");
		cbGenero.addItem("Feminino");
		pnContent.add(cbGenero, "cell 1 6");

		JLabel lblNewLabel_11 = new JLabel("Cidade:");
		pnContent.add(lblNewLabel_11, "flowy,cell 3 6");

		edCidade = new RoundJTextField();
		edCidade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		edCidade.setColumns(10);
		pnContent.add(edCidade, "cell 3 6,growx");

		JLabel lblNewLabel_6 = new JLabel("Permissão:");
		pnContent.add(lblNewLabel_6, "flowy,cell 1 7");

		JComboBox cbPermissao = new JComboBox();
		pnContent.add(cbPermissao, "cell 1 7");

		JLabel lblNewLabel_12 = new JLabel("UF:");
		pnContent.add(lblNewLabel_12, "flowy,cell 3 7");

		JComboBox cbUF = new JComboBox();
		pnContent.add(cbUF, "cell 3 7");

		JButton btnExcluir = new RoundButton("Excluir");
		btnExcluir.setBackground((new Color(255, 199, 0)));

		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(edCpf.validaCPF() && edCpf.existeCpfUsuario(FDAOTUser)) {
					FDAOTUser.setBDCPF(edCpf.getText());
					exluiUser(FDAOTUser.getIDUser(FDAOTUser));
				} else {
					JOptionPane.showMessageDialog(null, "Cpf não cadastrado");
				}
			}
		});
		pnContent.add(btnExcluir, "flowx,cell 1 9,growx");

		JButton btnConsulta = new RoundButton("Login");
		btnConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abreConsulta(local);
			}
		});
		btnConsulta.setText("Consultar");
		btnConsulta.setBackground((new Color(255, 199, 0)));

		pnContent.add(btnConsulta, "flowx,cell 3 9,growx");

		JButton btnLimpar = new RoundButton("Login");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpaCampos(true);
			}
		});
		btnLimpar.setText("Limpar");
		btnLimpar.setBackground((new Color(255, 199, 0)));

		pnContent.add(btnLimpar, "cell 1 9,growx");

		JButton btnCAD = new RoundButton("Login");
		btnCAD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			{

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
					FDAOTDadosUser.setBDNOMEUSER(edNome.getText());
					FDAOTDadosUser.setBDGENERO(cbGenero.getSelectedItem().toString());
					FDAOTDadosUser.setBDDATANASCIMENTO(edDataNascimento.getDate());
					FDAOTDadosUser.setBDTELEFONE(edTelefone.getText());

						if (existeCpf) {
							FDAOTDadosUser.alterar(FDAOTDadosUser);
						} else {
							FDAOTDadosUser.inserir(FDAOTDadosUser);
						}
						edCpf.requestFocus();
						int resposta = JOptionPane.showConfirmDialog(null,
								"Salvo com sucesso!\nDeseja limpar os campos?", "Confirmação",
								JOptionPane.YES_NO_OPTION);
						if (resposta == JOptionPane.YES_OPTION) {
							limpaCampos(false);
						}

					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Erro ao salvar");
					}

				}
			}
		});
		btnCAD.setText("COMFIRMAR");
		;
		btnCAD.setBackground((new Color(255, 199, 0)));

		pnContent.add(btnCAD, "cell 3 9,growx");
		
		
		
		ListEstado = new ArrayList<>();
		ListEstado = FDAOUF.ListTEstado(FDAOUF); 
		for (MTEstado mtEstado : ListEstado) {
			cbUF.addItem(mtEstado);
		}

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
			cbPermissao.setSelectedItem(prDadosUser.getBDIDPERMICAO()-1);
			edNome.setText(prDadosUser.getBDNOMEUSER());
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

