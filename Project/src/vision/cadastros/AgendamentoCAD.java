package vision.cadastros;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import control.DAOAgendamento;
import control.DAODadosUser;
import control.DAOPet;
import control.DAOUser;
import model.Agendamento;
import model.DadosUser;
import model.Pet;
import model.interfaces.InterPet;
import model.interfaces.InterUsuario;
import net.miginfocom.swing.MigLayout;
import vision.consultas.PetCON;
import vision.consultas.UserCON;
import vision.padrao.CPFTextField;
import vision.padrao.DateTextField;
import vision.padrao.PanelComBackgroundImage;
import vision.padrao.RoundButton;
import vision.padrao.RoundJTextField;
import vision.padrao.RoundJTextFieldNum;
import vision.padrao.TableSimples;
import vision.padrao.lupaButton;

public class AgendamentoCAD extends JFrame implements InterUsuario, InterPet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DAOAgendamento fDAOAgendamento = new DAOAgendamento();
	private ArrayList<Agendamento> Lista = new ArrayList<>();

	private TableSimples table;
	private JPanel contentPane;
	private JPanel panel;
	private JPanel container_card;
	private JPanel card;
	private JPanel container_content;
	private JPanel container_buttons;
	private JLabel lbTitle;
	private JLabel lbUser;
	private RoundButton btnlimpar;
	private CPFTextField edCpf;
	private lupaButton btnConUser;
	private JLabel lblPet;
	private RoundJTextField edNomePet;
	private RoundJTextField edNomeUser;
	private RoundJTextField edNomeRaca;
	private lupaButton btnConPet;
	private RoundJTextFieldNum edNumAtendimento;
	private JLabel lblNmeroAgendamento;
	private DateTextField textField;
	private JLabel lbData;

	// Objetos do usuario
	private DAOUser FDAOTUser = new DAOUser();
	private DAODadosUser FDAOUserDados = new DAODadosUser();
	private UserCON FVUserCON;

	// Obejtos do Pet
	private ArrayList<Pet> listPet = new ArrayList<>();
	private DAOPet FDAOTPet = new DAOPet();
	private PetCON FVPetCON;

	/**
	 */
	public AgendamentoCAD() {
		BufferedImage bg = null;
		;
		try {
			bg = ImageIO.read(new File("src/vision/images/BGLogin.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		setTitle("Agendamento");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 455, 706);
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
		container_card.setLayout(new MigLayout("", "[750.00px,grow 600]", "[600px,grow]"));

		card = new JPanel();
		card.setBackground(new Color(125, 137, 245));
		container_card.add(card, "cell 0 0,grow");
		card.setLayout(new MigLayout("", "[grow]", "[][0px,grow 0][280px,grow][10px,grow 10]"));

		lbTitle = new JLabel("Agendamento");
		card.add(lbTitle, "cell 0 0,alignx center");
		lbTitle.setForeground(new Color(0, 0, 0));
		lbTitle.setFont(new Font("Dialog", Font.BOLD, 20));

		container_content = new JPanel();
		container_content.setBackground(new Color(125, 137, 245));
		card.add(container_content, "cell 0 2,grow");
		container_content.setLayout(new MigLayout("", "[grow 600]", "[][][][][][][][][][][350px][50px]"));
		
		lblNmeroAgendamento = new JLabel("Número agendamento:");
		lblNmeroAgendamento.setHorizontalAlignment(SwingConstants.RIGHT);
		container_content.add(lblNmeroAgendamento, "flowx,cell 0 1");
		
		edNumAtendimento = new RoundJTextFieldNum(8);
		edNumAtendimento.setToolTipText("Aperte F9 para consultar.");
		edNumAtendimento.setColumns(10);
		container_content.add(edNumAtendimento, "cell 0 1");
		
		lbData = new JLabel("Data:");
		lbData.setHorizontalAlignment(SwingConstants.RIGHT);
		container_content.add(lbData, "flowx,cell 0 2");
		
		textField = new DateTextField();
		textField.setColumns(10);
		container_content.add(textField, "cell 0 2");

		JScrollPane scrollPane_1 = new JScrollPane();
		container_content.add(scrollPane_1, "cell 0 10,grow");

		table = new TableSimples(new Object[][] {},
				new String[] { "Hora", "Disponibilidade" });
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getColumnModel().getColumn(1).setPreferredWidth(800);
		scrollPane_1.setViewportView(table);

		lbUser = new JLabel("Usuario:");
		lbUser.setHorizontalAlignment(SwingConstants.RIGHT);
		lbUser.setFont(new Font("Tahoma", Font.PLAIN, 12));
		container_content.add(lbUser, "flowx,cell 0 5");

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
		container_content.add(edCpf, "cell 0 5");

		btnConUser = new lupaButton("");
		btnConUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chamaConUser();
			}
		});
		container_content.add(btnConUser, "cell 0 5");

		edNomeUser = new RoundJTextField();
		edNomeUser.setEnabled(false);
		edNomeUser.setToolTipText("Aperte F9 para consultar.");
		edNomeUser.setColumns(10);
		container_content.add(edNomeUser, "cell 0 5");

		lblPet = new JLabel("Pet:");
		lblPet.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPet.setFont(new Font("Tahoma", Font.PLAIN, 12));
		container_content.add(lblPet, "flowx,cell 0 6");

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
		container_content.add(edNomePet, "cell 0 6");

		btnConPet = new lupaButton("");
		btnConPet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chamaConPet();
			}
		});
		container_content.add(btnConPet, "cell 0 6");

		edNomeRaca = new RoundJTextField();
		edNomeRaca.setToolTipText("Aperte F9 para consultar.");
		edNomeRaca.setEnabled(false);
		edNomeRaca.setColumns(10);
		container_content.add(edNomeRaca, "cell 0 6");

		container_buttons = new JPanel();
		container_buttons.setBackground(new Color(125, 137, 245));
		card.add(container_buttons, "cell 0 3,grow");
		container_buttons.setLayout(new MigLayout("", "[100px][][100px][100px][][100px][100px][100px]", "[][][][]"));

		btnlimpar = new RoundButton("Limpar");
		btnlimpar.setText("Confirmar");
		btnlimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnlimpar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		container_buttons.add(btnlimpar, "cell 4 0");

		atualizatabela();
	}

	private void atualizatabela() {
		Lista = fDAOAgendamento.ListT(fDAOAgendamento);
		table.limparTabela();
		
	}

	private void chamaConUser() {
		ArrayList<DadosUser> list = new ArrayList<>();
		list = FDAOUserDados.ListConsulta(FDAOUserDados);

		FVUserCON = new UserCON(list, this);
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
		FVPetCON = new PetCON(listPet, this);

		FVPetCON.desExcluir();
		FVPetCON.setVisible(true);
	}

	@Override
	public void preencheDadosUser(DadosUser listUser) {
		edCpf.setText(listUser.getBDCPF());
		edNomeUser.setText(listUser.getBDNOMEUSER());

		edNomePet.setText("");
		edNomeRaca.setText("");

		atualizatabela();
	}

	@Override
	public void exluiUser(Integer bdiduser) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preencheDadosPet(Pet dado) {
		edNomePet.setText(dado.getBDNOMEPET());
		edNomeRaca.setText(dado.getBDNOMERACA());
		atualizatabela();
	}

	@Override
	public void exluiPet(Integer IdPet) {
		// TODO Auto-generated method stub
	}

}
