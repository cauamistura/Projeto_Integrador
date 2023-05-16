package vision.atendimentos;

import java.awt.Color;
import java.awt.Font;
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

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import control.DAOAtendimentoEntrada;
import control.DAOAtendimentoSaida;
import control.DAOTComorbidade;
import control.DAOTDadosUser;
import control.DAOTPet;
import control.DAOTUser;
import model.MTAtendimenoEntrada;
import model.MTAtendimentoSaida;
import model.MTComorbidade;
import model.MTDadosUser;
import model.MTPet;
import model.interfaces.InterComorbidade;
import model.interfaces.InterEntrada;
import model.interfaces.InterPet;
import model.interfaces.InterUsuario;
import net.miginfocom.swing.MigLayout;
import vision.cadastros.VComorbidadeCad;
import vision.cadastros.VPetCad;
import vision.cadastros.VUserCad;
import vision.consultas.VComCon;
import vision.consultas.VEntradaCON;
import vision.consultas.VPetCON;
import vision.consultas.VUserCON;
import vision.padrao.CPFTextField;
import vision.padrao.DateTextField;
import vision.padrao.PanelComBackgroundImage;
import vision.padrao.RoundButton;
import vision.padrao.RoundJTextField;
import vision.padrao.RoundJTextFieldNum;
import vision.padrao.lupaButton;

public class VEntradaATE extends JFrame implements InterUsuario, InterPet, InterComorbidade, InterEntrada {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	// Objetos do Atendimeno
	private DAOAtendimentoEntrada FDAOEntrada = new DAOAtendimentoEntrada();
	private VEntradaCON FEntradaCON;

	// Objetos do usuario
	private VUserCad TelaUser;
	private DAOTUser FDAOTUser = new DAOTUser();
	private DAOTDadosUser FDAOUserDados = new DAOTDadosUser();
	private VUserCON FVUserCON;

	// Obejtos do Pet
	private ArrayList<MTPet> listPet = new ArrayList<>();
	private VPetCad TelaPet;
	private DAOTPet FDAOTPet = new DAOTPet();
	private VPetCON FVPetCON;

	// Obejtos das Comorbidades
	private DAOTComorbidade FDAOTComorbidade = new DAOTComorbidade();;
	private VComorbidadeCad TelaComorbidade;
	private VComCon FConsultaComorbidade;

	// Objetos do Atendimeno saída
	private DAOAtendimentoSaida FDAOAtendimentoSaida;

	private RoundJTextFieldNum edNumEntrada;
	private DateTextField edDataEntrada;
	private CPFTextField edCpf;
	private JTextField edNomePet;
	private JTextField edComorbidade;
	private JTextField edNomeUser;
	private JTextField edNomeRaca;
	private JTextPane pnDesc;
	private JLabel lbStatus;

	public VEntradaATE() {
		BufferedImage bg = null;
		;
		try {
			bg = ImageIO.read(new File("src/vision/images/BGLogin.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setTitle("Atendimento de Entrada");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 618, 583);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(158, 174, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[150px][600px,grow][150px]", "[50px][600px,grow][50px]"));

		JPanel content = new PanelComBackgroundImage(bg);
		content.setBackground(new Color(158, 174, 255));
		contentPane.add(content, "cell 1 1,alignx center");
		content.setLayout(new MigLayout("", "[450px,grow]", "[][200px,grow][grow]"));

		JLabel lbTitle = new JLabel("Atendimento Entrada");
		lbTitle.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		content.add(lbTitle, "cell 0 0,alignx center");

		JPanel card = new JPanel();
		card.setBackground(new Color(125, 137, 245));
		content.add(card, "cell 0 1,grow");
		card.setLayout(new MigLayout("", "[60px][200px,grow][100px][200px,grow][50px]", "[][][][][][][][]"));

		JLabel lbNumero = new JLabel("Numero de entrada:");
		lbNumero.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		card.add(lbNumero, "flowy,cell 1 2");

		JButton btnConUser = new lupaButton("");
		btnConUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chamaConUser();
			}
		});
		card.add(btnConUser, "cell 2 4,alignx center,aligny bottom");

		JLabel lbUser = new JLabel("Usuario:");
		lbUser.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		card.add(lbUser, "flowy,cell 1 4");

		edCpf = new CPFTextField();
		edCpf.setToolTipText("Aperte F9 para consultar.");
		edCpf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F9) {
					chamaConUser();
				}
				if (e.getKeyCode() == KeyEvent.VK_F4) {
					if (TelaUser == null) {
						TelaUser = new VUserCad();
					}
					TelaUser.setVisible(true);
				}
			}
		});
		edCpf.setColumns(10);
		card.add(edCpf, "cell 1 4,growx");

		edNomeUser = new RoundJTextField();
		edNomeUser.setEnabled(false);
		card.add(edNomeUser, "cell 3 4,growx,aligny bottom");
		edNomeUser.setColumns(10);

		JButton btnConPet = new lupaButton("");
		btnConPet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chamaConPet();
			}
		});
		card.add(btnConPet, "cell 2 5,alignx center,aligny bottom");

		JLabel lblPet = new JLabel("Pet:");
		lblPet.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		card.add(lblPet, "flowy,cell 1 5");

		edNomeRaca = new RoundJTextField();
		edNomeRaca.setEnabled(false);
		card.add(edNomeRaca, "cell 3 5,growx,aligny bottom");
		edNomeRaca.setColumns(10);

		JLabel lbComo = new JLabel("Comorbidade:");
		lbComo.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		card.add(lbComo, "flowy,cell 1 6");

		edComorbidade = new RoundJTextField();
		edComorbidade.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F9) {
					chamaConComorbidade();
				}
				if (e.getKeyCode() == KeyEvent.VK_F4) {
					if (TelaComorbidade == null) {
						TelaComorbidade = new VComorbidadeCad();
					}
					TelaComorbidade.setVisible(true);
				}
			}
		});
		edComorbidade.setColumns(10);
		card.add(edComorbidade, "cell 1 6,growx");

		JButton btnConComorbidade = new lupaButton("");
		btnConComorbidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chamaConComorbidade();
			}
		});
		card.add(btnConComorbidade, "cell 2 6,alignx center,aligny bottom");

		JLabel lbData = new JLabel("Entrada:");
		lbData.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		card.add(lbData, "flowy,cell 3 6");

		edDataEntrada = new DateTextField();
		edDataEntrada.setColumns(10);
		card.add(edDataEntrada, "cell 3 6,growx");

		edNomePet = new RoundJTextField();
		edNomePet.setToolTipText("Aperte F9 para consultar.");
		edNomePet.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F9) {
					chamaConPet();
				}
				if (e.getKeyCode() == KeyEvent.VK_F4) {
					if (TelaPet == null) {
						TelaPet = new VPetCad();
					}
					TelaPet.setVisible(true);
				}
			}
		});
		edNomePet.setToolTipText("Aperte F9 para consultar.");
		edNomePet.setColumns(10);
		card.add(edNomePet, "cell 1 5,growx");

		edNumEntrada = new RoundJTextFieldNum(8);
		edNumEntrada.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F9) {
					chamaConAtendimeno();
				}
			}
		});
		edNumEntrada.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!edNumEntrada.getText().isEmpty()) {
					FDAOEntrada.setBDIDENTRADA(Integer.valueOf(edNumEntrada.getText()));
				}
				if (FDAOEntrada.existeAtendimento(FDAOEntrada)) {
					lbStatus.setText("Status: Alterando");
				} else {
					lbStatus.setText("Status: Inserindo");
				}
			}
		});
		edNumEntrada.setToolTipText("Aperte F9 para consultar.");
		edNumEntrada.setColumns(10);
		card.add(edNumEntrada, "cell 1 2");

		JPanel desc_card = new JPanel();
		desc_card.setBackground(new Color(125, 137, 245));
		content.add(desc_card, "cell 0 2,grow");
		desc_card.setLayout(new MigLayout("", "[300px,grow][grow]", "[160px,grow]"));

		JPanel desc_content = new JPanel();
		desc_content.setBackground(new Color(125, 137, 245));
		desc_card.add(desc_content, "cell 0 0,grow");
		desc_content.setLayout(new MigLayout("", "[100px][300px,grow][]", "[grow][]"));

		JLabel lbDesc = new JLabel("Descrição:");
		lbDesc.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		desc_content.add(lbDesc, "flowy,cell 1 0");

		pnDesc = new JTextPane();
		desc_content.add(pnDesc, "cell 1 0,grow");

		lbStatus = new JLabel("Status: Aguardando");
		lbStatus.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		desc_content.add(lbStatus, "cell 1 1");

		JPanel buttons_content = new JPanel();
		buttons_content.setBackground(new Color(125, 137, 245));
		desc_card.add(buttons_content, "cell 1 0,grow");
		buttons_content.setLayout(new MigLayout("", "[][][][]", "[100px][][][][70px]"));

		JButton btnExcluir = new RoundButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!edNumEntrada.getText().isEmpty()) {
					excluirAtendimento(Integer.valueOf(edNumEntrada.getText()));
				}
			}
		});
		btnExcluir.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
		buttons_content.add(btnExcluir, "cell 1 1,growx");

		RoundButton btnLimpar = new RoundButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpaCampos();
			}
		});
		btnLimpar.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
		buttons_content.add(btnLimpar, "cell 3 1,growx");

		JButton btnConsulta = new RoundButton("Consultar");
		btnConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chamaConAtendimeno();
			}
		});
		btnConsulta.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
		buttons_content.add(btnConsulta, "cell 1 3,growx");

		JButton btnConfirmar = new RoundButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acaoConfirma();
			}
		});
		btnConfirmar.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
		buttons_content.add(btnConfirmar, "cell 3 3,growx");
	}

	private void chamaConComorbidade() {
		ArrayList<MTComorbidade> list = new ArrayList<>();
		list = FDAOTComorbidade.ListTComorbidade(FDAOTComorbidade);

		FConsultaComorbidade = new VComCon(list, this);
		FConsultaComorbidade.setVisible(true);
	}

	public void preencheAtendimeno(MTAtendimenoEntrada dado) {
		edNumEntrada.setText(String.valueOf(dado.getBDIDENTRADA()));
		pnDesc.setText(dado.getBDDESC());

		edComorbidade.setText(achaComorbidade(dado.getBDCOMORBIDADE()));
		FDAOEntrada.setBDCOMORBIDADE(dado.getBDCOMORBIDADE());

		DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("ddMMyyyy");
		edDataEntrada.setText(dado.getBDDATAENT().format(FOMATTER));

		// Usuario
		edCpf.setText(dado.getBDCPF());
		edNomeUser.setText(dado.getBDNOMEUSER());
		FDAOEntrada.setBDIDUSER(dado.getBDIDUSER());

		// Pet
		edNomePet.setText(dado.getBDNOMEPET());
		edNomeRaca.setText(dado.getBDNOMERACA());
		FDAOEntrada.setBDIDPET(dado.getBDIDPET());
	}

	public void preencheUser(MTDadosUser list) {
		edCpf.setText(list.getBDCPF());
		edNomeUser.setText(list.getBDNOMEUSER());
	}

	private void preenchePet(MTPet list) {
		edNomePet.setText(list.getBDNOMEPET());
		edNomeRaca.setText(list.getBDNOMERACA());
	}

	private void chamaConUser() {
		ArrayList<MTDadosUser> list = new ArrayList<>();
		list = FDAOUserDados.ListConsulta(FDAOUserDados);

		FVUserCON = new VUserCON(list, this);
		FVUserCON.desabilitaExcluir();
		FVUserCON.setVisible(true);
	}

	private void chamaConPet() {
		if (!edCpf.existeCpfUsuario(FDAOTUser)) {
			JOptionPane.showInternalMessageDialog(null,
					"Usuário informado não existe!\nInforme um usuário valido ou aperte F4 para cadastrar.");
			edCpf.requestFocus();
			return;
		}
		FDAOTUser.setBDCPF(edCpf.getText());
		FDAOTPet.setBDIDUSER(FDAOTUser.getIDUser(FDAOTUser));
		listPet = FDAOTPet.listTPetFiltradoUser(FDAOTPet);

		if (listPet.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Este usuario não tem Pet(s) cadastrados!\nAperte F4 para cadastrar.");
			return;
		}
		FVPetCON = new VPetCON(listPet, this);

		FVPetCON.desExcluir();
		FVPetCON.setVisible(true);
	}

	public void limpaCampos() {

		edNumEntrada.setText("");
		edCpf.setText("");
		edDataEntrada.setText("");
		edNomePet.setText("");
		edNomeUser.setText("");
		edNomeRaca.setText("");
		pnDesc.setText("");
		edComorbidade.setText("");

		FDAOEntrada.setBDIDENTRADA(null);
		FDAOEntrada.setBDIDUSER(null);
		FDAOEntrada.setBDIDPET(null);
		FDAOEntrada.setBDCOMORBIDADE(null);

		lbStatus.setText("Status: Aguardando");

		edNumEntrada.requestFocus();
	}

	private String achaComorbidade(Integer prID) {
		ArrayList<MTComorbidade> listCom = FDAOTComorbidade.ListTComorbidade(FDAOTComorbidade);
		try {
			for (MTComorbidade com : listCom) {
				if (com.getBDIDCOMORBIDADE() == prID) {
					return com.getBDNOMECOMORBIDADE();
				}
			}
		} finally {
			listCom = null;
		}
		return null;
	}

	private Integer achaComorbidadeID(String prCOM) {
		ArrayList<MTComorbidade> listCom = new ArrayList<>();
		try {
			listCom = FDAOTComorbidade.ListTComorbidade(FDAOTComorbidade);
			for (MTComorbidade com : listCom) {
				if (com.getBDNOMECOMORBIDADE().equalsIgnoreCase(prCOM)) {
					return com.getBDIDCOMORBIDADE();
				}
			}
		} finally {
			listCom = null;
		}
		return null;
	}

	private void excluirAtendimento(Integer prID) {
		if (prID == null) {
			JOptionPane.showMessageDialog(null, "Número de atendimento invalido!");
			edNumEntrada.requestFocus();
			return;
		}

		int resposta = JOptionPane.showConfirmDialog(null,
				"Você realmente deseja excluir?\nTodos os dados vinculados a esta entrada serão excluídos.",
				"Confirmação", JOptionPane.YES_NO_OPTION);

		if (resposta == JOptionPane.YES_OPTION) {
			try {
				FDAOEntrada.deletar(prID);
				JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro ao excluir.");
			}
		}
	}

	private Boolean validaSaida() {
		if (FDAOAtendimentoSaida == null) {
			FDAOAtendimentoSaida = new DAOAtendimentoSaida();
		}
		ArrayList<MTAtendimentoSaida> list = new ArrayList<>();
		try {
			list = FDAOAtendimentoSaida.ListT(FDAOAtendimentoSaida);
			for (MTAtendimentoSaida dado : list) {
				if ((dado.getBDIDPET() == FDAOEntrada.getBDIDPET())
						&& (dado.getBDIDENTRADA() == FDAOEntrada.getBDIDENTRADA())) {
					return false;
				}
			}
		} finally {
			list = null;
		}

		return true;
	}

	@Override
	public void preencheDadosUser(MTDadosUser listUser) {
		preencheUser(listUser);
	}

	@Override
	public void exluiUser(Integer bdiduser) {
		// TODO Auto-generated method stub
	}

	@Override
	public void preencheDadosPet(MTPet dado) {
		FDAOTPet.setBDIDPET(dado.getBDIDPET());
		preenchePet(dado);
	}

	@Override
	public void exluiPet(Integer IdPet) {
		// TODO Auto-generated method stub
	}

	private void acaoConfirma() {

		if (edNumEntrada.getText().isEmpty()) {
			int resposta = JOptionPane.showConfirmDialog(null,
					"Número do atendimento não informado.\nDeseja prencher automaticamente?", "Confirmação",
					JOptionPane.YES_NO_OPTION);

			if (resposta == JOptionPane.YES_OPTION) {
				FDAOEntrada.setBDIDENTRADA(FDAOEntrada.getChaveID("tatendimento_entrada", "BDIDENTRADA"));
			} else {
				edNumEntrada.requestFocus();
				return;
			}
		} else {
			FDAOEntrada.setBDIDENTRADA(Integer.valueOf(edNumEntrada.getText()));
		}

		if (FDAOTPet.getBDIDPET() == null) {
			JOptionPane.showInternalMessageDialog(null, "Pet invalido!\nConsulte e tente novamente");
			edNomePet.requestFocus();
			return;
		}

		if (achaComorbidadeID(edComorbidade.getText()) == null) {
			JOptionPane.showInternalMessageDialog(null, "Comorbidade invalida!\nConsulte e tente novamente");
			edComorbidade.requestFocus();
			return;
		}

		if (!edDataEntrada.validaDate()) {
			JOptionPane.showInternalMessageDialog(null, "Data invalida.");
			edDataEntrada.requestFocus();
			return;
		}

		FDAOEntrada.setBDIDPET(FDAOTPet.getBDIDPET());
		FDAOEntrada.setBDCOMORBIDADE(achaComorbidadeID(edComorbidade.getText()));
		FDAOEntrada.setBDDATAENT(edDataEntrada.getDate());
		FDAOEntrada.setBDDESC(pnDesc.getText());

		if (!validaSaida()) {
			JOptionPane.showInternalMessageDialog(null,
					"Não é possivel a alteração do pet.\nEntrada já vinculada com uma saída.");
			return;
		}

		try {
			if (FDAOEntrada.existeAtendimento(FDAOEntrada)) {
				FDAOEntrada.alterar(FDAOEntrada);
			} else {
				FDAOEntrada.inserir(FDAOEntrada);
			}
			JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao salvar!");
		}

		limpaCampos();
	}

	private void chamaConAtendimeno() {
		ArrayList<MTAtendimenoEntrada> list = new ArrayList<>();
		list = FDAOEntrada.ListConsulta(FDAOEntrada);

		FEntradaCON = new VEntradaCON(list, this);
		FEntradaCON.setVisible(true);
	}

	@Override
	public void preencheCom(MTComorbidade dado) {
		FDAOTComorbidade.setBDIDCOMORBIDADE(dado.getBDIDCOMORBIDADE());
		FDAOTComorbidade.setBDNOMECOMORBIDADE(dado.getBDNOMECOMORBIDADE());
		edComorbidade.setText(FDAOTComorbidade.getBDNOMECOMORBIDADE());
	}

	@Override
	public void preencheDadosEntrada(MTAtendimenoEntrada listAtendimento) {
		preencheAtendimeno(listAtendimento);
	}

	@Override
	public void exluirAtendimento(Integer numAtendimento) {
		excluirAtendimento(numAtendimento);
	}
}
