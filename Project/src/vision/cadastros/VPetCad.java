package vision.cadastros;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import control.DAOTDadosUser;
import control.DAOTEspecie;
import control.DAOTPet;
import control.DAOTRaca;
import control.DAOTUser;
import model.MTDadosUser;
import model.MTEspecie;
import model.MTPet;
import model.MTRaca;
import model.interfaces.InterPet;
import net.miginfocom.swing.MigLayout;
import vision.VMenu;
import vision.consultas.VPetCON;
import vision.padrao.DateTextField;
import vision.padrao.PanelComBackgroundImage;
import vision.padrao.RoundButton;
import vision.padrao.RoundJTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class VPetCad extends JFrame implements InterPet {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	public DAOTPet FDAOTPet = new DAOTPet();
	public DAOTUser FDAOTUser = new DAOTUser();
	public DAOTEspecie FDAOTEspecie = new DAOTEspecie();
	public DAOTDadosUser FDAOTDadosUser = new DAOTDadosUser();
	public DAOTRaca FDAOTRaca = new DAOTRaca();

	private JPanel contentPane;

	ArrayList<MTRaca> TListRaca = new ArrayList<>();
	ArrayList<MTEspecie> TListEspecie = new ArrayList<>();
	ArrayList<MTDadosUser> TListUser = new ArrayList<>();

	private JTextField txtApelidoPet;
	private DateTextField txtDataNasc;
	private JTextField txtNomePet;

	JComboBox<MTEspecie> especieCb = new JComboBox<MTEspecie>();
	JComboBox<MTRaca> racaCb = new JComboBox<MTRaca>();
	JComboBox<MTDadosUser> userCb = new JComboBox<MTDadosUser>();

	/**
	 * Create the frame.
	 */
	public VPetCad() {

		BufferedImage bg = null;
		;
		try {
			bg = ImageIO.read(new File("src/vision/images/BGLogin.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setTitle("Cadastro de pets");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 800);
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
		lblNewLabel.setIcon(new ImageIcon(VPetCad.class.getResource("/vision/images/doglove 1 (1).png")));
		panel_5.add(lblNewLabel);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(125, 137, 245));
		panel_1.add(panel_3, "cell 0 1,growx");
		panel_3.setLayout(new MigLayout("", "[10px][150px,grow][10px]", "[][][][][][]"));

		JLabel lblNewLabel_7 = new JLabel("Cadastro Pet");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_3.add(lblNewLabel_7, "cell 1 0,alignx center");

		JLabel lblNewLabel_1 = new JLabel("Espécie:");
		panel_3.add(lblNewLabel_1, "flowy,cell 1 1");

		especieCb = new JComboBox<MTEspecie>();
		especieCb.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				racaCb.removeAllItems();

				racaCb.addItem(null);

				if (e.getStateChange() == ItemEvent.SELECTED) {

					TListRaca = FDAOTRaca.ListTRaca(FDAOTRaca, achaIdEspecie());

					for (MTRaca mtRaca : TListRaca) {
						racaCb.addItem(mtRaca);
					}
				}
			}
		});
		panel_3.add(especieCb, "cell 1 1,growx");
		especieCb.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblNewLabel_2 = new JLabel("Raça:");
		panel_3.add(lblNewLabel_2, "flowy,cell 1 2");

		racaCb = new JComboBox<MTRaca>();
		panel_3.add(racaCb, "cell 1 2,growx");
		racaCb.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblNewLabel_3 = new JLabel("Dono:");
		panel_3.add(lblNewLabel_3, "flowy,cell 1 3");

		userCb = new JComboBox<MTDadosUser>();
		userCb.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_3.add(userCb, "cell 1 3,growx");

		JLabel l = new JLabel("Nome:");
		panel_3.add(l, "flowy,cell 1 4");

		VPetCad local = this;
		txtNomePet = new RoundJTextField();
		txtNomePet.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F9) {
					abreConsulta(local);
				}
			}
		});
		txtNomePet.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_3.add(txtNomePet, "cell 1 4,growx");
		txtNomePet.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(125, 137, 245));
		panel_1.add(panel_2, "cell 0 2,grow");
		panel_2.setLayout(new MigLayout("", "[10px][100px,grow][10px][100px,grow][10px]", "[50px][][80px][50px]"));

		JLabel i = new JLabel("Apelido(opcional):");
		panel_2.add(i, "flowy,cell 1 0");

		txtApelidoPet = new RoundJTextField();
		txtApelidoPet.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(txtApelidoPet, "cell 1 0,growx");
		txtApelidoPet.setColumns(10);

		JLabel lblNasc = new JLabel("Data de Nasc:");
		panel_2.add(lblNasc, "flowy,cell 3 0");

		txtDataNasc = new DateTextField();
		txtDataNasc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDataNasc.setColumns(10);
		panel_2.add(txtDataNasc, "cell 3 0,growx");

		JButton btnLimpar = new RoundButton("Limpar");
		btnLimpar.setBackground((new Color(255, 199, 0)));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtApelidoPet.setText("");
				txtDataNasc.setText("");
				txtNomePet.setText("");

				racaCb.setSelectedIndex(0);
				especieCb.setSelectedIndex(0);
				userCb.setSelectedIndex(0);
			}
		});
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(btnLimpar, "flowx,cell 1 3");

		VPetCad prSelf = this;

		JButton btnNewButton = new RoundButton("Consultar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<MTPet> lista = FDAOTPet.ListTPet(FDAOTPet);
				VPetCON frame = new VPetCON(lista, prSelf);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
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

				if (userCb.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "Campo vazio: Dono");
					return;
				}

				FDAOTPet.setBDIDESPECIE(FDAOTPet.getChaveID("tespecie", "BDIDESPECIE"));
				FDAOTPet.setBDDATANASCIMENTO(txtDataNasc.getDate());
				FDAOTPet.setBDIDPET(FDAOTPet.getChaveID("tpets", "BDIDPET"));
				FDAOTPet.setBDIDRACA(achaIdRaca());
				FDAOTPet.setBDNOMEPET(txtNomePet.getText());
				FDAOTPet.setBDAPELIDO(txtApelidoPet.getText());
				FDAOTPet.setBDIDUSER(achaIdUser());

				if (!txtDataNasc.validaDate()) {
					JOptionPane.showMessageDialog(null, "Data inválida. Tente novamente.");
					return;
				}
				try {
					FDAOTPet.inserir(FDAOTPet);
					txtNomePet.setText("");
					txtApelidoPet.setText("");
					especieCb.setSelectedIndex(0);
					racaCb.setSelectedIndex(0);
					userCb.setSelectedIndex(0);
					txtDataNasc.setText("");
					JOptionPane.showMessageDialog(null, "Seu pet foi cadastrado com sucesso!");
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Não foi possível cadastrar o pet");
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(btnNewButton_1, "flowx,cell 3 3,growx");

		TListEspecie = FDAOTEspecie.ListTEspecie(FDAOTEspecie);

		for (MTEspecie mtEspecie : TListEspecie) {
			especieCb.addItem(mtEspecie);
		}

		TListUser = FDAOTDadosUser.ListTDadosUser(FDAOTDadosUser);

		for (MTDadosUser mtUser : TListUser) {
			userCb.addItem(mtUser);
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
		ArrayList<MTEspecie> TListEspecie = new ArrayList<>();
		TListEspecie = FDAOTEspecie.ListTEspecie(FDAOTEspecie);

		for (MTEspecie mtEspecie : TListEspecie) {
			if (mtEspecie.getBDNOMEESPECIE().equals(especieCb.getSelectedItem().toString())) {
				idEspecie = mtEspecie.getBDIDESPECIE();
			}
		}
		return idEspecie;
	}

	private Integer achaIdRaca() {
		Integer idRaca = 0;
		ArrayList<MTRaca> TListRaca = new ArrayList<>();
		TListRaca = FDAOTRaca.ListTRaca(FDAOTRaca, 0);

		for (MTRaca mtRaca : TListRaca) {
			if (mtRaca.getBDNOMERACA().equals(racaCb.getSelectedItem().toString())) {
				idRaca = mtRaca.getBDIDRACA();
			}
		}
		return idRaca;
	}

	private Integer achaIdUser() {
		Integer idUser = 0;
		ArrayList<MTDadosUser> TListUser = new ArrayList<>();
		TListUser = FDAOTDadosUser.ListTDadosUser(FDAOTDadosUser);

		for (MTDadosUser mtUser : TListUser) {
			if (mtUser.getBDNOMEUSER().equals(userCb.getSelectedItem().toString())) {
				idUser = mtUser.getBDIDUSER();
			}
		}
		return idUser;
	}

	public void limpaCampos() {
		txtNomePet.setText("");
		txtApelidoPet.setText("");
		txtDataNasc.setText("");
	}

	public void preencheCampos(MTPet list) {
		if (list != null) {
			txtNomePet.setText(list.getBDNOMEPET());
			System.out.println(txtNomePet.getText());
			txtApelidoPet.setText(list.getBDAPELIDO());

			DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("ddMMyyyy");
			txtDataNasc.setText(list.getBDDATANASCIMENTO().format(FOMATTER));

			especieCb.setSelectedIndex(list.getBDIDESPECIE() - 1);

		}
	}

	private void abreConsulta(VPetCad prSelf) {
		if (FDAOTPet != null) {
			List<MTPet> lista = FDAOTPet.ListTPet(FDAOTPet);
			VPetCON frame = new VPetCON(lista, prSelf);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}
	}

	@Override
	public void preencheDadosPet(MTPet listPet) {
		preencheCampos(listPet);

	}

	@Override
	public void exluiPet(Integer IdPet) {
		exluirUser(IdPet);
	}
}
