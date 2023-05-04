package vision.atendimentos;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import control.DAOAtendimentoEntrada;
import control.DAOTComorbidade;
import control.DAOTDadosUser;
import control.DAOTPet;
import control.DAOTUser;
import model.MTAtendimenoEntrada;
import model.MTComorbidade;
import model.MTDadosUser;
import model.MTPet;
import model.interfaces.InterComorbidade;
import model.interfaces.InterEntrada;
import model.interfaces.InterPet;
import model.interfaces.InterUsuario;
import vision.cadastros.VComorbidadeCad;
import vision.cadastros.VPetCad;
import vision.cadastros.VUserCad;
import vision.consultas.VComCon;
import vision.consultas.VEntradaCON;
import vision.consultas.VPetCON;
import vision.consultas.VUserCON;
import vision.padrao.CPFTextField;
import vision.padrao.DateTextField;
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
	private CPFTextField edCpf;
	private RoundJTextField edNomePet;
	private RoundJTextFieldNum edNumEntrada;
	private RoundJTextField edComorbidade;
	private DateTextField edDataEntrada;
	private JTextPane txtNomeUser;
	private JTextPane txtNomeRaca;
	private JTextPane pnDesc;
	private JLabel lbStatus;

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
	private DAOTComorbidade FDAOTComorbidade = new DAOTComorbidade();
	private VComorbidadeCad TelaComorbidade;
	private VComCon FConsultaComorbidade;

	public VEntradaATE() {
		setTitle("Atendimento de Entrada");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 494, 337);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbUser = new JLabel("Usuario:");
		lbUser.setHorizontalAlignment(SwingConstants.RIGHT);
		lbUser.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbUser.setBounds(75, 40, 67, 14);
		contentPane.add(lbUser);

		edCpf = new CPFTextField();
		edCpf.setBounds(152, 37, 105, 20);
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
		contentPane.add(edCpf);
		edCpf.setColumns(10);

		JLabel lbNumero = new JLabel("Número de Entrada:");
		lbNumero.setHorizontalAlignment(SwingConstants.RIGHT);
		lbNumero.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbNumero.setBounds(10, 11, 132, 14);
		contentPane.add(lbNumero);

		edNumEntrada = new RoundJTextFieldNum(8);
		edNumEntrada.setToolTipText("Aperte F9 para consultar.");
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
		edNumEntrada.setBounds(152, 11, 105, 20);
		contentPane.add(edNumEntrada);
		edNumEntrada.setColumns(10);

		JLabel lblPet = new JLabel("Pet:");
		lblPet.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPet.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPet.setBounds(75, 67, 67, 14);
		contentPane.add(lblPet);

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
		edNomePet.setBounds(152, 64, 105, 20);
		contentPane.add(edNomePet);

		lupaButton btnConPet = new lupaButton("");
		btnConPet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chamaConPet();
			}
		});
		btnConPet.setBounds(267, 63, 53, 23);
		contentPane.add(btnConPet);

		lupaButton btnConUser = new lupaButton("");
		btnConUser.setBounds(267, 36, 53, 23);
		btnConUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chamaConUser();
			}
		});
		contentPane.add(btnConUser);

		RoundButton btnExcluir = new RoundButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!edNumEntrada.getText().isEmpty()) {
					excluirAtendimento(Integer.valueOf(edNumEntrada.getText()));
				}
			}
		});
		btnExcluir.setBounds(57, 230, 86, 23);
		contentPane.add(btnExcluir);

		RoundButton btnLimpar = new RoundButton("Login");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpaCampos();
			}
		});
		btnLimpar.setText("Limpar");
		btnLimpar.setBounds(147, 230, 79, 23);
		contentPane.add(btnLimpar);

		RoundButton btnConsulta = new RoundButton("Login");
		btnConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chamaConAtendimeno();
			}
		});
		btnConsulta.setText("Consultar");
		btnConsulta.setBounds(236, 230, 89, 23);
		contentPane.add(btnConsulta);

		RoundButton btnConfirmar = new RoundButton("Login");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acaoConfirma();
			}
		});
		btnConfirmar.setText("Confirmar");
		btnConfirmar.setBounds(329, 230, 95, 23);
		contentPane.add(btnConfirmar);

		edDataEntrada = new DateTextField();
		edDataEntrada.setBounds(152, 92, 105, 20);
		contentPane.add(edDataEntrada);
		edDataEntrada.setColumns(10);

		JLabel lbData = new JLabel("Entrada:");
		lbData.setHorizontalAlignment(SwingConstants.RIGHT);
		lbData.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbData.setBounds(75, 95, 67, 14);
		contentPane.add(lbData);

		txtNomeRaca = new JTextPane();
		txtNomeRaca.setEnabled(false);
		txtNomeRaca.setBounds(330, 64, 127, 20);
		contentPane.add(txtNomeRaca);

		txtNomeUser = new JTextPane();
		txtNomeUser.setEnabled(false);
		txtNomeUser.setBounds(330, 37, 127, 20);
		contentPane.add(txtNomeUser);

		JLabel lbDescricao = new JLabel("Descrição:");
		lbDescricao.setHorizontalAlignment(SwingConstants.RIGHT);
		lbDescricao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbDescricao.setBounds(75, 149, 67, 14);
		contentPane.add(lbDescricao);

		JScrollPane pnPane = new JScrollPane();
		pnPane.setBounds(152, 149, 272, 73);
		contentPane.add(pnPane);

		pnDesc = new JTextPane();
		pnPane.setViewportView(pnDesc);

		JLabel lbComo = new JLabel("Comorbidade:");
		lbComo.setHorizontalAlignment(SwingConstants.RIGHT);
		lbComo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbComo.setBounds(37, 122, 105, 14);
		contentPane.add(lbComo);

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
		edComorbidade.setToolTipText("Aperte F9 para consultar.");
		edComorbidade.setColumns(10);
		edComorbidade.setBounds(152, 119, 105, 20);
		contentPane.add(edComorbidade);

		lupaButton btnConComorbidade = new lupaButton("");
		btnConComorbidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chamaConComorbidade();
			}
		});
		btnConComorbidade.setBounds(267, 118, 53, 23);
		contentPane.add(btnConComorbidade);

		lbStatus = new JLabel("Status: Aguardando");
		lbStatus.setHorizontalAlignment(SwingConstants.LEFT);
		lbStatus.setBounds(25, 264, 180, 14);
		contentPane.add(lbStatus);
	}
	
	private void chamaConAtendimeno() { 
		ArrayList<MTAtendimenoEntrada> list = new ArrayList<>();
		list = FDAOEntrada.ListConsulta(FDAOEntrada);

		FEntradaCON = new VEntradaCON(list, this);
		FEntradaCON.setVisible(true);
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
	
	private void chamaConComorbidade() {
		ArrayList<MTComorbidade> list = new ArrayList<>();
		list = FDAOTComorbidade.ListTComorbidade(FDAOTComorbidade);

		FConsultaComorbidade = new VComCon(list, this);
		FConsultaComorbidade.setVisible(true);
	}
	
	private void preencheAtendimeno(MTAtendimenoEntrada dado) {
		edNumEntrada.setText(String.valueOf(dado.getBDIDENTRADA()));
		pnDesc.setText(dado.getBDDESC());
		
		edComorbidade.setText(achaComorbidade(dado.getBDCOMORBIDADE()));
		FDAOEntrada.setBDCOMORBIDADE(dado.getBDCOMORBIDADE());
		
		DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("ddMMyyyy");
		edDataEntrada.setText(dado.getBDDATAENT().format(FOMATTER));
		
		//Usuario 
		edCpf.setText(dado.getBDCPF());
		txtNomeUser.setText(dado.getBDNOMEUSER());
		FDAOEntrada.setBDIDUSER(dado.getBDIDUSER());
		
		//Pet 
		edNomePet.setText(dado.getBDNOMEPET());
		txtNomeRaca.setText(dado.getBDNOMERACA());
		FDAOEntrada.setBDIDPET(dado.getBDIDPET());
	}

	private void preencheUser(MTDadosUser list) {
		edCpf.setText(list.getBDCPF());
		txtNomeUser.setText(list.getBDNOMEUSER());
	}

	private void preenchePet(MTPet list) {
		edNomePet.setText(list.getBDNOMEPET());
		txtNomeRaca.setText(list.getBDNOMERACA());
	}

	public void limpaCampos() {
		edNumEntrada.setText("");
		edCpf.setText("");
		edDataEntrada.setText("");
		edNomePet.setText("");
		txtNomeRaca.setText("");
		txtNomeUser.setText("");
		pnDesc.setText("");
		edComorbidade.setText("");
		
		FDAOEntrada.setBDIDENTRADA(null);
		FDAOEntrada.setBDIDUSER(null);
		FDAOEntrada.setBDIDPET(null);
		FDAOEntrada.setBDCOMORBIDADE(null);
		
		lbStatus.setText("Status: Aguardando");
	}
	
	private String achaComorbidade(Integer prID) {
		ArrayList<MTComorbidade> listCom = new ArrayList<>();
		try {
			listCom = FDAOTComorbidade.ListTComorbidade(FDAOTComorbidade);
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

		if (FDAOTComorbidade.getBDIDCOMORBIDADE() == null) {
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
		FDAOEntrada.setBDCOMORBIDADE(FDAOTComorbidade.getBDIDCOMORBIDADE());
		FDAOEntrada.setBDDATAENT(edDataEntrada.getDate());
		FDAOEntrada.setBDDESC(pnDesc.getText());
		
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
