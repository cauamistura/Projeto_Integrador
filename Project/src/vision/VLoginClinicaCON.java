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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import control.DAOTClinica;
import model.MTClinica;
import net.miginfocom.swing.MigLayout;
import vision.padrao.PanelComBackgroundImage;
import vision.padrao.RoundButton;
import vision.padrao.RoundJTextField;

public class VLoginClinicaCON extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DAOTClinica FDAOTClinica = new DAOTClinica();
	private VMenu menu;
	private JTextField edSenha;
	private JTextField edCNPJ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VLoginClinicaCON frame = new VLoginClinicaCON();
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
	public VLoginClinicaCON() {
		
		BufferedImage bg = null;
		;
		try {
			bg = ImageIO.read(new File("src/vision/images/BG.png"));

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
		lblNewLabel_2.setIcon(new ImageIcon(VLoginClinicaCON.class.getResource("/vision/images/Group (2).png")));
		panel_3.add(lblNewLabel_2, "cell 1 1,alignx center");
		
		JLabel lbCnpj = new JLabel("CNPJ:");
		panel_2.add(lbCnpj, "flowy,cell 1 1");
		
		edCNPJ = new RoundJTextField(8);
		edCNPJ.setText("cnpj");
		panel_2.add(edCNPJ, "cell 1 1,growx");
		edCNPJ.setColumns(10);
		
		JLabel lbSenha = new JLabel("Senha:");
		panel_2.add(lbSenha, "flowy,cell 1 2");
		
		edSenha = new RoundJTextField(8);
		edSenha.setText("1");
		panel_2.add(edSenha, "cell 1 2,growx");
		edSenha.setColumns(10);

		JLabel lbAlerta = new JLabel("<Aguardando>");
		panel_2.add(lbAlerta, "cell 1 5,alignx center");
		
		JButton btnLogin = new  RoundButton("Login");
		btnLogin.setBackground((new Color(255, 199, 0)));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<MTClinica> TListClinica = new ArrayList<>();
				TListClinica = FDAOTClinica.ListTClinica(FDAOTClinica);
				if (getExiste(TListClinica)) {
					menu.setVisible(true);
					dispose();
				} else {
					lbAlerta.setText("CNPJ ou senha incorreto!");
				}
			}
				
				
			
		});
		panel_2.add(btnLogin, "cell 1 3,growx");
		
		
		
		
	}
	private Boolean getExiste(ArrayList<MTClinica> prList) {
		Boolean wValida = false;
		for (MTClinica l : prList) {
			if(l.getBDCNPJ().equals(edCNPJ.getText()) && l.getBDSENHA().equalsIgnoreCase(edSenha.getText())) {
				wValida = true;
				menu = new VMenu();
				VMenu.FIDClinica  = l.getBDIDCLINICA();
				VMenu.FNOMEClinica= l.getBDNOME();
				VMenu.FCNPJClinica= l.getBDCNPJ();
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
