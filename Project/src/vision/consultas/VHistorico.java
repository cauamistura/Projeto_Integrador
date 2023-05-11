package vision.consultas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import control.DAOHistorico;
import control.DAOTDadosUser;
import control.DAOTPet;
import control.DAOTUser;
import model.MHistorico;
import model.MTAtendimenoEntrada;
import model.MTDadosUser;
import model.MTPet;
import model.MTUser;
import model.interfaces.InterPet;
import model.interfaces.InterUsuario;
import net.miginfocom.swing.MigLayout;
import vision.atendimentos.VEntradaATE;
import vision.padrao.CPFTextField;
import vision.padrao.PanelComBackgroundImage;
import vision.padrao.RoundButton;
import vision.padrao.RoundJTextField;
import vision.padrao.TableSimples;
import vision.padrao.lupaButton;

public class VHistorico extends JFrame implements InterUsuario, InterPet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DAOHistorico FDAOHistorico = new DAOHistorico();
	private ArrayList<MHistorico> Lista = new ArrayList<>();
	
	private TableSimples table;
	private JPanel contentPane;
	private JPanel panel;
	private JPanel container_card;
	private JPanel card;
	private JPanel container_content;
	private JPanel container_buttons;
	private JLabel lbTitle;
	private JLabel lbTipo;
	private JLabel lbUser;
	private RoundButton btnlimpar;
	private CPFTextField edCpf;
	private lupaButton btnConUser;
	private JLabel lblPet;
	private RoundJTextField edNomePet;
	private RoundJTextField edNomeUser;
	private RoundJTextField edNomeRaca;
	private lupaButton btnConPet;
	private JComboBox<String> cbTipo;
	
	// Objetos do usuario
	private DAOTUser FDAOTUser = new DAOTUser();
	private DAOTDadosUser FDAOUserDados = new DAOTDadosUser();
	private VUserCON FVUserCON;
	
	// Obejtos do Pet
	private ArrayList<MTPet> listPet = new ArrayList<>();
	private DAOTPet FDAOTPet = new DAOTPet();
	private VPetCON FVPetCON;
	
	/**
	 * Create the frame.
	 */
	public VHistorico() {
		BufferedImage bg = null;
		;
		try {
			bg = ImageIO.read(new File("src/vision/images/BGLogin.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		setTitle("Historico");
		Lista = FDAOHistorico.List(FDAOHistorico);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 654, 743);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		panel.setBackground(new Color(158, 174, 255));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[150px][700px,grow][150px]", "[50px][600px,grow][50px]"));

		container_card = new PanelComBackgroundImage(bg);
		container_card.setBackground(new Color(158, 174, 255));
		panel.add(container_card, "cell 1 1,alignx center");
		container_card.setLayout(new MigLayout("", "[478.00px,grow 600]", "[600px,grow]"));

		card = new JPanel();
		card.setBackground(new Color(125, 137, 245));
		container_card.add(card, "cell 0 0,grow");
		card.setLayout(new MigLayout("", "[grow]", "[][0px,grow 0][280px,grow][10px,grow 10]"));

		lbTitle = new JLabel("Histórico");
		card.add(lbTitle, "cell 0 0,alignx center");
		lbTitle.setForeground(new Color(0, 0, 0));
		lbTitle.setFont(new Font("Dialog", Font.BOLD, 20));

		container_content = new JPanel();
		container_content.setBackground(new Color(125, 137, 245));
		card.add(container_content, "cell 0 2,grow");
		container_content.setLayout(new MigLayout("", "[grow 600]", "[][][][][][][][][350px][50px]"));

		JScrollPane scrollPane_1 = new JScrollPane();
		container_content.add(scrollPane_1, "cell 0 8,growy");

		table = new TableSimples(new Object[][] {}, new String[] { "Número", "Nome Usuário", "Nome Pet", "Raça", "Data", "Comorbidade"});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getColumnModel().getColumn(1).setPreferredWidth(800);
		scrollPane_1.setViewportView(table);

		lbUser = new JLabel("Usuario:");
		lbUser.setHorizontalAlignment(SwingConstants.RIGHT);
		lbUser.setFont(new Font("Tahoma", Font.PLAIN, 12));
		container_content.add(lbUser, "flowx,cell 0 3");

		edCpf = new CPFTextField();
		edCpf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F9) {
					chamaConUser();
				}
			}
		});
		edCpf.setToolTipText("Aperte F9 para consultar.");
		edCpf.setColumns(10);
		container_content.add(edCpf, "cell 0 3");

		btnConUser = new lupaButton("");
		btnConUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chamaConUser();
			}
		});
		container_content.add(btnConUser, "cell 0 3");

		edNomeUser = new RoundJTextField();
		edNomeUser.setEnabled(false);
		edNomeUser.setToolTipText("Aperte F9 para consultar.");
		edNomeUser.setColumns(10);
		container_content.add(edNomeUser, "cell 0 3");

		lblPet = new JLabel("Pet:");
		lblPet.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPet.setFont(new Font("Tahoma", Font.PLAIN, 12));
		container_content.add(lblPet, "flowx,cell 0 4");

		edNomePet = new RoundJTextField();
		edNomePet.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F9) {
					chamaConPet();
				}
			}
		});
		edNomePet.setToolTipText("Aperte F9 para consultar.");
		edNomePet.setColumns(10);
		container_content.add(edNomePet, "cell 0 4");

		btnConPet = new lupaButton("");
		btnConPet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chamaConPet();
			}
		});
		container_content.add(btnConPet, "cell 0 4");

		edNomeRaca = new RoundJTextField();
		edNomeRaca.setToolTipText("Aperte F9 para consultar.");
		edNomeRaca.setEnabled(false);
		edNomeRaca.setColumns(10);
		container_content.add(edNomeRaca, "cell 0 4");

		lbTipo = new JLabel("Tipo:");
		lbTipo.setHorizontalAlignment(SwingConstants.RIGHT);
		lbTipo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		container_content.add(lbTipo, "flowx,cell 0 6");

		cbTipo = new JComboBox<String>();
		cbTipo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				atualizatabela();
			}
		});
		cbTipo.addItem("Entrada");
		cbTipo.addItem("Saída");
		container_content.add(cbTipo, "cell 0 6");

		container_buttons = new JPanel();
		container_buttons.setBackground(new Color(125, 137, 245));
		card.add(container_buttons, "cell 0 3,grow");
		container_buttons.setLayout(new MigLayout("", "[100px][][100px][100px][][100px][100px][100px]", "[][][][]"));

		btnlimpar = new RoundButton("Limpar");
		btnlimpar.setText("Confirmar");
		btnlimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MHistorico dado = null;
				int[] selectedRows = table.getSelectedRows();
				for (int i = 0; i < selectedRows.length; i++) {
					int modelIndex = table.convertRowIndexToModel(selectedRows[i]);
					dado = Lista.get(modelIndex);
				}
				
				if (cbTipo.getSelectedIndex() == 0) {
					buscaENTCAD(dado);
				}
				
				table.clearSelection();
			}
		});
		btnlimpar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		container_buttons.add(btnlimpar, "cell 4 0");

		atualizatabela();
	}

	private void atualizatabela() {
		DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Lista = FDAOHistorico.List(FDAOHistorico);
		table.limparTabela();

		if (cbTipo.getSelectedIndex() == 0) {
			for (MHistorico com : Lista) {
				if (com.getTipo().equalsIgnoreCase("entrada")) {
					Object[][] rowData = {
					{ com.getBDIDENTRADA(), com.getBDNOMEUSER(), com.getBDNOMEPET(), com.getBDNOMERACA(), com.getBDDATAENT().format(FOMATTER), com.getBDCOMORBIDADE()} };
					table.preencherTabela(rowData);	
				}
			}
		} else {
			for (MHistorico com : Lista) {
				if (com.getTipo().equalsIgnoreCase("saida")) {
					Object[][] rowData = {
					{ com.getBDIDENTRADA(), com.getBDNOMEUSER(), com.getBDNOMEPET(), com.getBDNOMERACA(), com.getBDDATASAIDA().format(FOMATTER), com.getBDIDCOMORBIDADE()} };
					table.preencherTabela(rowData);	
				}
			}	
		}	
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
	
	private void buscaENTCAD(MHistorico list) {
		MTAtendimenoEntrada ent = new MTAtendimenoEntrada();
		
		ent.setBDIDENTRADA(list.getBDIDENTRADA());
		ent.setBDDESC(list.getBDDESC());
		ent.setBDCOMORBIDADE(list.getBDCOMORBIDADE());
		ent.setBDDATAENT(list.getBDDATAENT());
		ent.setBDCPF(getCPF(list.getBDIDUSER()));
		ent.setBDNOMEUSER(list.getBDNOMEUSER());
		ent.setBDNOMEPET(list.getBDNOMEPET());
		ent.setBDNOMERACA(list.getBDNOMERACA());
		ent.setBDIDPET(list.getBDIDPET());
		
		VEntradaATE self = new VEntradaATE();
		self.preencheAtendimeno(ent);
		self.setVisible(true);
	}
	
	private String getCPF(Integer I) {
		ArrayList<MTUser> l = new ArrayList<>();
		try {
			l = FDAOTUser.ListTUser(FDAOTUser);
			for (MTUser i : l) {
				if (i.getBDIDUSER() == I) {
					return i.getBDCPF();
				}
			}
			return null;	
		} finally {
			l = null;
		}
	}

	@Override
	public void preencheDadosUser(MTDadosUser listUser) {
		edCpf.setText(listUser.getBDCPF());
		edNomeUser.setText(listUser.getBDNOMEUSER());
		FDAOHistorico.setBDIDUSER(listUser.getBDIDUSER());
		
		FDAOHistorico.setBDIDPET(null);
		edNomePet.setText("");
		edNomeRaca.setText("");
		
		atualizatabela();
	}

	@Override
	public void exluiUser(Integer bdiduser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preencheDadosPet(MTPet dado) {
		edNomePet.setText(dado.getBDNOMEPET());
		edNomeRaca.setText(dado.getBDNOMERACA());
		FDAOHistorico.setBDIDPET(dado.getBDIDPET());
		atualizatabela();
	}

	@Override
	public void exluiPet(Integer IdPet) {
		// TODO Auto-generated method stub
	}

}
