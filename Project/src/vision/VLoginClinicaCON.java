package vision;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.DAOTClinica;
import model.MTClinica;
import net.miginfocom.swing.MigLayout;
import vision.padrao.PanelComBackgroundImage;
import vision.padrao.RoundButton;
import vision.padrao.RoundJTextField;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 433, 589);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(158, 174, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(158, 174, 255));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[33.00px:n:50px][304.00px,grow][88.00px:n:50px]", "[50px][450px,grow][50px]"));
		
		
		BufferedImage bg = null;
		;
		try {
			bg = ImageIO.read(new File("src/vision/images/BG.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		JPanel panel_1 = new PanelComBackgroundImage(bg);
		//JPanel panel_1 = new JPanel();

		panel_1.setBackground(new Color(0, 81, 81));
		panel_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_1.setBackground(new Color(158, 174, 255));
		
		panel_1.setBackground(new Color(158, 174, 255));
		panel.add(panel_1, "cell 1 1,alignx center");
		panel_1.setLayout(new MigLayout("", "[65.00px:n:50px][210.00px:n:200px,grow][39.00px:n:50px]", "[156.00][15.00px][50px:n:50px][-12.00px][70.00px][43.00px:n:50px][50px:n:50px]"));
		
		JLabel lbCnpj = new JLabel("CNPJ:");
		lbCnpj.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_1.add(lbCnpj, "flowy,cell 1 2");
		
		
		edCNPJ = new RoundJTextField(10);
		edCNPJ.setText("cnpj");
		panel_1.add(edCNPJ, "cell 1 2,growx");
		edCNPJ.setColumns(10);
		
		JLabel lbSenha = new JLabel("Senha:");
		lbSenha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_1.add(lbSenha, "flowy,cell 1 4");
		
		
		
		edSenha = new RoundJTextField(10);
		edSenha.setText("1");
		panel_1.add(edSenha, "cell 1 4,growx");
		edSenha.setColumns(10);
		
		JLabel lbAlerta = new JLabel("<Aguardando>");
		panel_1.add(lbAlerta, "cell 1 6,alignx center");
		lbAlerta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton btnLogin = new  RoundButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<MTClinica> TListClinica = new ArrayList<>();
				TListClinica = FDAOTClinica.ListTClinica(FDAOTClinica);
				if (getExiste(TListClinica)) {
					menu.setVisible(true);
					dispose();
				} else {
					lbAlerta.setText("User não cadastrado!");
				}
			}
				
			
		});
		panel_1.add(btnLogin, "cell 1 5,growx");
		
	
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(125, 137, 245));
		panel_1.add(panel_2, "cell 1 0,growx");
		panel_2.setLayout(new MigLayout("", "[][100px][][][100px][100px][]", "[][100px][][][][][100px][100px]"));
		
		JLabel lblNewLabel_2 = new JLabel("");
		panel_2.add(lblNewLabel_2, "cell 4 4");
		lblNewLabel_2.setIcon(new ImageIcon(VLoginClinicaCON.class.getResource("/vision/images/Group (2).png")));
		
		
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
