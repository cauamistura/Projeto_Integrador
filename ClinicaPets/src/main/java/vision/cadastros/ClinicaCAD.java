
package vision.cadastros;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import control.DAOCidade;
import control.DAOClinica;
import control.DAODadosUser;
import control.DAOEndereco;
import control.DAOEstado;
import model.Cidade;
import model.Clinica;
import model.Endereco;
import model.Estado;
import net.miginfocom.swing.MigLayout;
import vision.Login;
import vision.Menu;
import vision.padrao.CEPTextField;
import vision.padrao.CNPJTextFiel;
import vision.padrao.PanelComBackgroundImage;
import vision.padrao.RoundButton;
import vision.padrao.RoundJTextField;
import java.awt.Font;

public class ClinicaCAD extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DAOClinica FDAOTClinica = new DAOClinica();
	public DAOEndereco FDAOTEndereco = new DAOEndereco();
	public DAOEstado FDAOTEstado = new DAOEstado();
	public DAOCidade FDAOTCidade = new DAOCidade();
	public DAODadosUser FDAOTDadosUser = new DAODadosUser();
	ArrayList<Clinica> TListClinica = new ArrayList<>();
	ArrayList<Estado> TListEstado = new ArrayList<>();
	ArrayList<Endereco> TListEndereco = new ArrayList<>();
	ArrayList<Cidade> TListCidade = new ArrayList<>();
	private Menu menu = new Menu();
	private JPanel contentPane;
	private RoundJTextField edCidade;
	private CNPJTextFiel edCnpj;
	private RoundJTextField edDescricao;
	private RoundJTextField edNomeFan;
	private RoundJTextField edNome;
	private CEPTextField edCep;
	private RoundJTextField edBairro;
	private RoundJTextField edSenha;
	private JComboBox cbUF;
	private RoundButton btnDelet;
	private RoundButton btnConf;
	private JOptionPane optionPane;
	private JDialog dialog;
	private Timer timer;

	/**
	 * Create the frame.
	 */
	public ClinicaCAD() {

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				preencheCampos();
			}
		});
		setBounds(100, 100, 848, 524);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		BufferedImage bg = null;
		;
		try {
			bg = ImageIO.read(new File("src\\main\\resources\\BGLogin.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setTitle("Cadastro Clinica");
		setBounds(100, 100, 900, 592);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(158, 174, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(158, 174, 255));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[50px][800px,grow][50px]", "[50px][500px,grow][50px]"));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(158, 174, 255));
		panel.add(panel_1, "cell 1 1,alignx center");
		panel_1.setLayout(
				new MigLayout("", "[50px][50px][500px,grow][100px,grow][50px]", "[30px,grow][300px,grow][][30px]"));

		JPanel panel_2 = new PanelComBackgroundImage(bg);
		panel_2.setBackground(new Color(158, 174, 255));
		panel_1.add(panel_2, "cell 2 1,alignx center");
		panel_2.setLayout(
				new MigLayout("", "[100px][200px,grow][200px,grow][100px]", "[80px][][][][][][][50px][80px]"));

		JLabel lbNome = new JLabel("Nome:");
		lbNome.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		panel_2.add(lbNome, "flowy,cell 1 2");

		edNome = new RoundJTextField();
		edNome.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		edNome.setBackground(new Color(255, 255, 255));
		panel_2.add(edNome, "cell 1 2,growx");
		edNome.setColumns(10);

		JLabel lbNomeFan = new JLabel("Nome Fantasia:");
		lbNomeFan.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		panel_2.add(lbNomeFan, "flowy,cell 2 2");

		edNomeFan = new RoundJTextField();
		edNomeFan.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		edNomeFan.setBackground(new Color(255, 255, 255));
		panel_2.add(edNomeFan, "cell 2 2,growx");
		edNomeFan.setColumns(10);

		JLabel lbCnpj = new JLabel("CNPJ:");
		lbCnpj.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		panel_2.add(lbCnpj, "flowy,cell 1 3");

		edCnpj = new CNPJTextFiel();
		edCnpj.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		edCnpj.setBackground(new Color(255, 255, 255));
		panel_2.add(edCnpj, "cell 1 3,growx");
		edCnpj.setColumns(10);

		edCep = new CEPTextField();
		edCep.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		edCep.setBackground(new Color(255, 255, 255));
		edCep.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

				if (edCep.getCEP() != null) {
					if (edCep.getCEPExiste(Integer.valueOf(edCep.getCEP()), edBairro, edCidade, edDescricao, cbUF)) {

						edDescricao.setEditable(false);
						edBairro.setEditable(false);
						edCidade.setEditable(false);
						cbUF.setEnabled(false);
					} else {
						edBairro.setText("");
						edCidade.setText("");
						edDescricao.setText("");
						cbUF.setSelectedIndex(0);

						edDescricao.setEditable(true);
						edBairro.setEditable(true);
						edCidade.setEditable(true);
						cbUF.setEnabled(true);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Cep invalido");

				}
			}
		});

		JLabel lbCep = new JLabel("CEP:");
		lbCep.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		panel_2.add(lbCep, "flowy,cell 2 3");
		edCep.setColumns(10);
		edCep.setBounds(104, 11, 156, 20);
		panel_2.add(edCep, "cell 2 3,growx");

		JLabel lbBairro = new JLabel("Bairro:");
		lbBairro.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		panel_2.add(lbBairro, "cell 1 5,alignx left");

		JLabel lbUf = new JLabel("UF:  ");
		lbUf.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		panel_2.add(lbUf, "flowx,cell 2 5");

		edBairro = new RoundJTextField();
		edBairro.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		edBairro.setBackground(new Color(255, 255, 255));
		edBairro.setBorder(new EmptyBorder(3, 3, 3, 3));
		panel_2.add(edBairro, "cell 1 6,growx");
		edBairro.setColumns(10);

		TListEstado = FDAOTEstado.ListTEstado(FDAOTEstado);
		cbUF = new JComboBox<Estado>();
		for (Estado mtEstado : TListEstado) {
			cbUF.addItem(mtEstado);
		}
		panel_2.add(cbUF, "flowx,cell 2 6");

		edCidade = new RoundJTextField();
		edCidade.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		edCidade.setBackground(new Color(255, 255, 255));
		panel_2.add(edCidade, "cell 2 6,growx");
		edCidade.setColumns(10);

		JLabel lbCidade = new JLabel("Cidade:");
		lbCidade.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		panel_2.add(lbCidade, "cell 2 5");

		JLabel lbDesc = new JLabel("Descricao:");
		lbDesc.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		panel_2.add(lbDesc, "flowy,cell 1 7");

		edDescricao = new RoundJTextField();
		edDescricao.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		edDescricao.setBackground(new Color(255, 255, 255));
		panel_2.add(edDescricao, "cell 1 7,growx");
		edDescricao.setColumns(10);

		JLabel lbSenha = new JLabel("Senha:");
		lbSenha.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		panel_2.add(lbSenha, "flowy,cell 2 7");

		edSenha = new RoundJTextField();
		edSenha.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		edSenha.setBackground(new Color(255, 255, 255));
		edSenha.setBorder(new EmptyBorder(3, 3, 3, 3));
		panel_2.add(edSenha, "cell 2 7,growx");
		edSenha.setColumns(10);

		JPanel panel_3 = new PanelComBackgroundImage(bg);
		panel_3.setBackground(new Color(158, 174, 255));
		panel_1.add(panel_3, "cell 3 1,grow");
		panel_3.setLayout(new MigLayout("", "[10px][20px,grow][]", "[grow][grow][][][][][][grow][][]"));

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(125, 137, 245));
		panel_3.add(panel_4, "cell 1 0,grow");
		panel_4.setLayout(new MigLayout("", "[][][][]", "[][][][]"));

		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon("C:\\Git\\Projeto_Integrador\\ClinicaPets\\src\\main\\resources\\clinica.png"));
		panel_4.add(lblNewLabel_8, "cell 1 2,alignx center");

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(125, 137, 245));
		panel_3.add(panel_5, "cell 1 1,grow");
		panel_5.setLayout(new BorderLayout(0, 0));

		btnConf = new RoundButton("Confirmar");
		btnConf.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnConf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!edCep.getCEPExiste(Integer.valueOf(edCep.getCEP()), edBairro, edCidade, edDescricao, cbUF)) {

					// validação do cep com o componente,
					FDAOTCidade.setBDIDCIDADE(FDAOTCidade.getChaveID("tcidades", "BDIDCIDADE"));
					FDAOTCidade.setBDNOMECID(edCidade.getText());
					FDAOTCidade.setBDDESCCID(edDescricao.getText());
					FDAOTCidade.setBDIDUF(edCep.achaIdUf(cbUF));

					FDAOTCidade.inserir(FDAOTCidade);

					FDAOTEndereco.setBDIDCIDADE(FDAOTCidade.getBDIDCIDADE());
					FDAOTEndereco.setBDBAIRRO(edBairro.getText());
					FDAOTEndereco.setBDCEP(Integer.valueOf(edCep.getCEP()));

					FDAOTEndereco.inserir(FDAOTEndereco);

				}

				if (edCnpj.existeCnpjClinica(FDAOTClinica)) {
					FDAOTClinica.setBDIDCLINICA(edCnpj.IdClinica());
				} else {
					FDAOTClinica.setBDIDCLINICA(FDAOTClinica.getChaveID("tclinica", "BDIDCLINICA"));
				}

				FDAOTClinica.setBDCNPJ(edCnpj.getText());
				FDAOTClinica.setBDNOME(edNome.getText());
				FDAOTClinica.setBDNOMEFANTASIA(edNomeFan.getText());
				FDAOTClinica.setBDSENHA(edSenha.getText());
				FDAOTClinica.setBDIDCEP(Integer.valueOf(edCep.getCEP()));

				if (edCnpj.existeCnpjClinica(FDAOTClinica)) {
					FDAOTClinica.alterar(FDAOTClinica);

				} else {
					FDAOTClinica.inserir(FDAOTClinica);

					try {
						FDAOTDadosUser.ListTDadosUser(FDAOTDadosUser);

					} catch (Exception e2) {
						int resposta = JOptionPane.showConfirmDialog(null,
								"Nenhuma Usuario cadastrado\nDeseja Cadastrar um?", "ATENÇÃO!!",
								JOptionPane.YES_NO_OPTION);
						if (resposta == JOptionPane.YES_OPTION) {
							UserCAD user = new UserCAD();
							user.setVisible(true);
							dispose();
						}
					}

				}

				menu.AtualizaDadosLogin("", edNome.getText());

				optionPane = new JOptionPane("Salvo com sucesso", JOptionPane.INFORMATION_MESSAGE,
						JOptionPane.DEFAULT_OPTION, null, new Object[] {}, null);
				dialog = optionPane.createDialog("");

				timer = new Timer(800, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dialog.dispose();
					}
				});
				timer.setRepeats(false);
				timer.start();

				dialog.setVisible(true);

				if (!edCnpj.existeCnpjClinica(FDAOTClinica)) {

					Login clinica = new Login();
					clinica.setVisible(true);
					dispose();
				}

			}

		});
		panel_3.add(btnConf, "cell 1 3,growx");

		btnDelet = new RoundButton("Deletar");
		btnDelet.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnDelet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int resposta = JOptionPane.showConfirmDialog(null,
						"Ao deletar a clinica, todos os Dados que estão ligadas a ela serão Excluidas\nDeseja confirmar essa ação?",
						"ATENÇÃO!!", JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
					FDAOTClinica.setBDIDCLINICA(edCnpj.IdClinica());
					FDAOTClinica.deletar(FDAOTClinica);

					JOptionPane.showInternalMessageDialog(null, "Excluido com sucesso!");
				} else {
					JOptionPane.showInternalMessageDialog(null, "Clinica não foi Excluida!");
				}

			}
		});
		panel_3.add(btnDelet, "cell 1 6,growx");

	}

	public void preencheCampos() {
		TListClinica = FDAOTClinica.ListTClinica(FDAOTClinica);
		TListEndereco = FDAOTEndereco.ListTEndereco(FDAOTEndereco);

		for (Clinica mtClinica : TListClinica) {

			if (!TListClinica.isEmpty()) {

				edCnpj.setEditable(false);

				edCnpj.setText(mtClinica.getBDCNPJ());
				edNome.setText(mtClinica.getBDNOME());
				edNomeFan.setText(mtClinica.getBDNOMEFANTASIA());
				edSenha.setText(mtClinica.getBDSENHA());
				edCep.setText(String.valueOf(mtClinica.getBDIDCEP()));

				edCep.getCEPExiste(Integer.valueOf(edCep.getCEP()), edBairro, edCidade, edDescricao, cbUF);
				edDescricao.setEditable(false);
				edBairro.setEditable(false);
				edCidade.setEditable(false);
				cbUF.setEnabled(false);
			} else {
				JOptionPane.showMessageDialog(null, "Cadastre um clinica");
			}
		}
	}
}