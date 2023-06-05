package vision.cadastros;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
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

import control.DAODadosUser;
import control.DAOEspecie;
import control.DAOPet;
import control.DAORaca;
import control.DAOUser;
import model.DadosUser;
import model.Especie;
import model.Pet;
import model.Raca;
import model.interfaces.InterPet;
import model.interfaces.InterUsuario;
import net.miginfocom.swing.MigLayout;
import vision.consultas.PetCON;
import vision.consultas.UserCON;
import vision.padrao.DateTextField;
import vision.padrao.PanelComBackgroundImage;
import vision.padrao.RoundButton;
import vision.padrao.RoundJTextField;
import vision.padrao.Util;
import vision.padrao.CPFTextField;
import vision.padrao.lupaButton;

public class PetCAD extends JFrame implements InterPet, InterUsuario {
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	private CPFTextField edCpf;
	private RoundJTextField edNomeUser;

	public DAOPet FDAOTPet = new DAOPet();
	public DAOUser FDAOTUser = new DAOUser();
	public DAOEspecie FDAOTEspecie = new DAOEspecie();
	public DAODadosUser FDAOTDadosUser = new DAODadosUser();
	public DAORaca FDAOTRaca = new DAORaca();

	private JPanel contentPane;

	ArrayList<Raca> TListRaca = new ArrayList<>();
	ArrayList<Especie> TListEspecie = new ArrayList<>();
	ArrayList<DadosUser> TListUser = new ArrayList<>();

	private JTextField txtApelidoPet;
	private DateTextField txtDataNasc;
	private JTextField txtNomePet;

	// Objetos do usuario
	private UserCAD TelaUser;
	private DAODadosUser FDAOUserDados = new DAODadosUser();
	private UserCON FVUserCON;

	JComboBox<Especie> especieCb = new JComboBox<Especie>();
	JComboBox<Raca> racaCb = new JComboBox<Raca>();

	Integer idPet = 0;

	/**
	 * Create the frame.
	 */
	public PetCAD() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Util.getCaminhoIMG("logo.png")));

		BufferedImage bg = null;
		;
		try {
			bg = ImageIO.read(new File(Util.getCaminhoIMG("BGLogin.png")));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setTitle("Cadastro de pets");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 560, 705);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(158, 174, 255));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[100px][500px,grow][100px]", "[100px][600px,grow][100px]"));

		JPanel panel_1 = new PanelComBackgroundImage(bg);
		panel_1.setBackground(new Color(158, 174, 255));
		panel.add(panel_1, "cell 1 1,alignx center");
		panel_1.setLayout(new MigLayout("", "[grow]", "[140.00,grow][20px,grow][95px,grow]"));

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(125, 137, 245));
		panel_1.add(panel_5, "cell 0 0,alignx center,growy");

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Util.getCaminhoIMG("doglove.png")));
		panel_5.add(lblNewLabel);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(125, 137, 245));
		panel_1.add(panel_3, "cell 0 1,growx");
		panel_3.setLayout(new MigLayout("", "[10px][150px,grow][10px]", "[][][grow][100px][][]"));

		JLabel lblNewLabel_1 = new JLabel("Espécie:");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panel_3.add(lblNewLabel_1, "flowy,cell 1 0");

		especieCb = new JComboBox<Especie>();
		especieCb.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				racaCb.removeAllItems();

				if (e.getStateChange() == ItemEvent.SELECTED) {

					TListRaca = FDAOTRaca.ListTRaca(FDAOTRaca, achaIdEspecie());

					for (Raca mtRaca : TListRaca) {
						racaCb.addItem(mtRaca);
					}
				}
			}
		});
		panel_3.add(especieCb, "cell 1 0,growx");
		especieCb.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));

		JLabel lblNewLabel_2 = new JLabel("Raça:");
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panel_3.add(lblNewLabel_2, "flowy,cell 1 1");

		racaCb = new JComboBox<Raca>();
		panel_3.add(racaCb, "cell 1 1,growx");
		racaCb.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(125, 137, 245));
		panel_3.add(panel_4, "cell 1 2,growx");
		panel_4.setLayout(new MigLayout("", "[250px][80px][250px]", "[][][]"));

		JLabel lblNewLabel_3 = new JLabel("Dono:");
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panel_4.add(lblNewLabel_3, "cell 0 0");

		edCpf = new CPFTextField();
		edCpf.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panel_4.add(edCpf, "cell 0 1,growx");
		edCpf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F9) {
					chamaConUser();
				}
				if (e.getKeyCode() == KeyEvent.VK_F4) {
					if (TelaUser == null) {
						TelaUser = new UserCAD();
					}
					TelaUser.setVisible(true);
				}
			}
		});
		edCpf.setToolTipText("Aperte F9 para consultar.");
		edCpf.setColumns(10);

		lupaButton btnConUser = new lupaButton("");
		btnConUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chamaConUser();
			}
		});
		btnConUser.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panel_4.add(btnConUser, "cell 1 1,aligny center");

		edNomeUser = new RoundJTextField();
		edNomeUser.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panel_4.add(edNomeUser, "cell 2 1,growx");
		edNomeUser.setEnabled(false);
		edNomeUser.setColumns(10);

		JLabel l = new JLabel("Nome:");
		l.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panel_3.add(l, "flowy,cell 1 4");

		PetCAD local = this;
		txtNomePet = new RoundJTextField();
		txtNomePet.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F9) {
					abreConsulta(local);
				}
			}
		});
		txtNomePet.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panel_3.add(txtNomePet, "cell 1 4,growx");
		txtNomePet.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(125, 137, 245));
		panel_1.add(panel_2, "cell 0 2,grow");
		panel_2.setLayout(new MigLayout("", "[10px][100px,grow][10px][100px,grow][10px]", "[50px][][80px][50px]"));

		JLabel i = new JLabel("Apelido(opcional):");
		i.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panel_2.add(i, "flowy,cell 1 0");

		txtApelidoPet = new RoundJTextField();
		txtApelidoPet.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panel_2.add(txtApelidoPet, "cell 1 0,growx");
		txtApelidoPet.setColumns(10);

		JLabel lblNasc = new JLabel("Data de Nasc:");
		lblNasc.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panel_2.add(lblNasc, "flowy,cell 3 0");

		txtDataNasc = new DateTextField();
		txtDataNasc.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		txtDataNasc.setColumns(10);
		panel_2.add(txtDataNasc, "cell 3 0,growx");

		JButton btnLimpar = new RoundButton("Limpar");
		btnLimpar.setBackground((new Color(255, 199, 0)));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpaCampos();
			}
		});
		btnLimpar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		panel_2.add(btnLimpar, "flowx,cell 1 3");

		PetCAD prSelf = this;

		JButton btnNewButton = new RoundButton("Consultar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Pet> lista = FDAOTPet.ListTPet(FDAOTPet);
				PetCON frame = new PetCON(lista, prSelf);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		panel_2.add(btnNewButton, "cell 1 3,growx");

		JButton btnNewButton_1 = new RoundButton("Cadastrar");
		btnNewButton_1.setText("Confirmar");
		btnNewButton_1.setBackground((new Color(255, 199, 0)));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtNomePet.getText().isEmpty() || txtNomePet.getText() == null) {
					JOptionPane.showMessageDialog(null, "Campo vazio: Nome");
					return;
				}

				if (lblNasc.getText().isEmpty() || lblNasc.getText() == null) {
					JOptionPane.showMessageDialog(null, "Campo vazio: Data de nascimento");
					return;
				}

				if (especieCb.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "Campo vazio: Espécie");
					return;
				}

				if (racaCb.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "Campo vazio: Raça");
					return;
				}

				FDAOTPet.setBDIDESPECIE(FDAOTPet.getChaveID("tespecie", "BDIDESPECIE"));

				if (idPet == 0) {
					FDAOTPet.setBDIDPET(FDAOTPet.getChaveID("tpets", "BDIDPET"));
				} else {
					FDAOTPet.setBDIDPET(idPet);
				}

				FDAOTPet.setBDDATANASCIMENTO(txtDataNasc.getDate());

				FDAOTPet.setBDIDRACA(achaIdRaca());
				FDAOTPet.setBDNOMEPET(txtNomePet.getText());
				FDAOTPet.setBDAPELIDO(txtApelidoPet.getText());

				if (FDAOTUser.getBDIDUSER() == null) {
					JOptionPane.showMessageDialog(null, "Usuario invalido, consulte e tente novamente.");
					return;
				}

				FDAOTPet.setBDIDUSER(FDAOTUser.getBDIDUSER());

				if (!txtDataNasc.validaDate()) {
					JOptionPane.showMessageDialog(null, "Data inválida. Tente novamente.");
					return;
				}
				try {
					if (FDAOTPet.existePet(FDAOTPet, idPet)) {
						FDAOTPet.alterar(FDAOTPet);
						JOptionPane.showMessageDialog(null, "Seu pet foi alterado com sucesso!");
					} else {
						FDAOTPet.inserir(FDAOTPet);
						JOptionPane.showMessageDialog(null, "Seu pet foi cadastrado com sucesso!");
					}

					limpaCampos();

				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Não foi possível cadastrar o pet");
				}
			}
		});
		btnNewButton_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		panel_2.add(btnNewButton_1, "flowx,cell 3 3,growx");

		TListEspecie = FDAOTEspecie.ListTEspecie(FDAOTEspecie);

		for (Especie mtEspecie : TListEspecie) {
			especieCb.addItem(mtEspecie);
		}
	}

	public void exluirUser(Integer prIDPET) {
		int resposta = JOptionPane.showConfirmDialog(null,
				"Você realmente deseja excluir? Todos os dados vinculados a este pet serão excluídos.", "Confirmação",
				JOptionPane.YES_NO_OPTION);

		if (resposta == JOptionPane.YES_OPTION) {
			FDAOTPet.deletar(prIDPET);
			JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
			limpaCampos();
		}

	}

	public Integer achaIdEspecie() {
		Integer idEspecie = 0;
		ArrayList<Especie> TListEspecie = new ArrayList<>();
		TListEspecie = FDAOTEspecie.ListTEspecie(FDAOTEspecie);

		for (Especie mtEspecie : TListEspecie) {
			if (mtEspecie.getBDNOMEESPECIE().equals(especieCb.getSelectedItem().toString())) {
				idEspecie = mtEspecie.getBDIDESPECIE();
			}
		}
		return idEspecie;
	}

	private Integer achaIdRaca() {
		Integer idRaca = 0;
		ArrayList<Raca> TListRaca = new ArrayList<>();
		TListRaca = FDAOTRaca.ListTRaca(FDAOTRaca, 0);

		for (Raca mtRaca : TListRaca) {
			if (mtRaca.getBDNOMERACA().equals(racaCb.getSelectedItem().toString())) {
				idRaca = mtRaca.getBDIDRACA();
			}
		}
		return idRaca;
	}

	public void limpaCampos() {
		txtApelidoPet.setText("");
		txtDataNasc.setText("");
		txtNomePet.setText("");

		racaCb.setSelectedIndex(0);
		especieCb.setSelectedIndex(0);

		edCpf.setText("");
		edNomeUser.setText("");

		FDAOTUser.setBDIDUSER(null);
	}

	public void preencheCampos(Pet list) {
		if (list != null) {
			idPet = list.getBDIDPET();

			txtNomePet.setText(list.getBDNOMEPET());
			txtApelidoPet.setText(list.getBDAPELIDO());

			DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("ddMMyyyy");
			txtDataNasc.setText(list.getBDDATANASCIMENTO().format(FOMATTER));

			for (int i = 0; i < especieCb.getItemCount(); i++) {

				if (especieCb.getItemAt(i).getBDNOMEESPECIE().equals(list.getBDNOMEESPECIE())) {
					especieCb.setSelectedIndex(i);
					break;
				}
			}

			for (int i = 0; i < racaCb.getItemCount(); i++) {

				if (racaCb.getItemAt(i).getBDNOMERACA().equals(list.getBDNOMERACA())) {
					racaCb.setSelectedIndex(i);
					break;
				}

			}

			edCpf.setText(list.getBDCPF());
			edNomeUser.setText(list.getBDNOMEUSER());

			FDAOTUser.setBDIDUSER(list.getBDIDUSER());
		}
	}

	private void abreConsulta(PetCAD prSelf) {
		if (FDAOTPet != null) {
			List<Pet> lista = FDAOTPet.ListTPet(FDAOTPet);
			PetCON frame = new PetCON(lista, prSelf);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}
	}

	private void chamaConUser() {
		ArrayList<DadosUser> list = new ArrayList<>();
		list = FDAOUserDados.ListConsulta(FDAOUserDados);

		FVUserCON = new UserCON(list, this);
		FVUserCON.desabilitaExcluir();
		FVUserCON.setVisible(true);
	}

	@Override
	public void preencheDadosPet(Pet listPet) {
		preencheCampos(listPet);

	}

	@Override
	public void exluiPet(Integer IdPet) {
		exluirUser(IdPet);
	}

	@Override
	public void preencheDadosUser(DadosUser listUser) {
		edCpf.setText(listUser.getBDCPF());
		edNomeUser.setText(listUser.getBDNOMEUSER());
		FDAOTUser.setBDIDUSER(listUser.getBDIDUSER());
	}

	@Override
	public void exluiUser(Integer bdiduser) {
		// TODO Auto-generated method stub

	}
}
