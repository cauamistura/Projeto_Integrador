package vision;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.DAOClinica;
import control.DAODadosUser;
import model.DadosUser;
import net.miginfocom.swing.MigLayout;
import vision.cadastros.ClinicaCAD;
import vision.cadastros.UserCAD;
import vision.padrao.CPFTextField;
import vision.padrao.PanelComBackgroundImage;
import vision.padrao.RoundButton;
import vision.padrao.RoundJPasswordField;

public class Login extends JFrame {
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DAODadosUser FDAODadosUser = new DAODadosUser();
	private DAOClinica FDAOTClinica = new DAOClinica();
	private Menu menu;
	private RoundJPasswordField edSenha;
	private CPFTextField edCNPJ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {

		BufferedImage bg = null;
		;
		try {
			bg = ImageIO.read(new File("src/vision/images/BGLogin.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Login");
		setBounds(100, 100, 561, 652);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(158, 174, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(158, 174, 255));
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new MigLayout("", "[100px][300px,grow][100px]", "[100px][380px,grow][100px]"));

		JPanel panel_2 = new PanelComBackgroundImage(bg);
		panel_2.setBackground(new Color(158, 174, 255));
		panel_1.add(panel_2, "cell 1 1,alignx center");
		panel_2.setLayout(new MigLayout("", "[25px][200px,grow][25px]", "[50px][60px][60px][][][30px]"));

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(125, 137, 245));
		panel_2.add(panel_3, "cell 1 0,alignx center");
		panel_3.setLayout(new MigLayout("", "[50px][50px][50px]", "[25px][25px][25px]"));

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Login.class.getResource("/vision/images/Group (2).png")));
		panel_3.add(lblNewLabel_2, "cell 1 1,alignx center");

		JLabel lbCnpj = new JLabel("CPF:");
		panel_2.add(lbCnpj, "flowy,cell 1 1");

		edCNPJ = new CPFTextField();
		edCNPJ.setText("123.456.789-10");
		panel_2.add(edCNPJ, "cell 1 1,growx");
		edCNPJ.setColumns(10);

		JLabel lbSenha = new JLabel("Senha:");
		panel_2.add(lbSenha, "flowy,cell 1 2");
		
		edSenha = new RoundJPasswordField();
		edSenha.setForeground(new Color(0, 0, 0));
		edSenha.setText("senha123");
		edSenha.setColumns(10);
		panel_2.add(edSenha, "cell 1 2,growx");

		JLabel lbAlerta = new JLabel("<Aguardando>");
		panel_2.add(lbAlerta, "cell 1 5,alignx center");

		JButton btnLogin = new RoundButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<DadosUser> TListClinica = new ArrayList<>();
				TListClinica = FDAODadosUser.listLogin(FDAODadosUser);

				try {
					FDAOTClinica.ListTClinica(FDAOTClinica);
					if (validaUser(TListClinica)) {
						menu.setVisible(true);
						menu.setLocationRelativeTo(null);
						dispose();
					} else {
						TListClinica = FDAODadosUser.ListTDadosUser(FDAODadosUser);

						if (TListClinica.isEmpty()) {
							int resposta = JOptionPane.showConfirmDialog(null,
									"Nenhuma Usuario cadastrado\nDeseja Cadastrar um?", "ATENÇÃO!!",
									JOptionPane.YES_NO_OPTION);
							if (resposta == JOptionPane.YES_OPTION) {
								UserCAD user = new UserCAD();
								user.setVisible(true);
								dispose();
							}
						} else {
							lbAlerta.setText("CPF ou senha incorreto!");
						}

					}
				} catch (Exception e2) {
					int resposta = JOptionPane.showConfirmDialog(null,
							"Nenhuma Clinica cadastrada\nDeseja Cadastrar uma?", "ATENÇÃO!!",
							JOptionPane.YES_NO_OPTION);
					if (resposta == JOptionPane.YES_OPTION) {
						ClinicaCAD clinica = new ClinicaCAD();
						clinica.setVisible(true);
						dispose();
					}
				}
			}
		});
		panel_2.add(btnLogin, "cell 1 3,growx");
	}

	@SuppressWarnings("deprecation")
	private Boolean validaUser(ArrayList<DadosUser> prList) {
		Boolean wValida = false;
		for (DadosUser l : prList) {
			if (l.getBDCPF().equals(edCNPJ.getText()) && l.getBDSENHA().equalsIgnoreCase(edSenha.getText())) {
				wValida = true;
				menu = new Menu();
				Menu.FIDClinica = l.getBDIDCLINICA();
				Menu.FCNPJClinica = l.getBDCNPJ();
				Menu.FIDUSER = l.getBDIDUSER();
				Menu.FPERMICAO = l.getBDIDPERMICAO();
				Menu.FCPFUSER = l.getBDCPF();

				menu.AtualizaDadosLogin(l.getBDNOMEUSER(), l.getBDNOME());

				break;
			}
		}
		if (wValida) {
			return true;
		} else {
			return false;
		}
	}
}
