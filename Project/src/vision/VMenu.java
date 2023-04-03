package vision;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import vision.cadastros.*;
import vision.VLoginClinicaCON;
import vision.padrao.PanelComBackgroundImage;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Frame;
import javax.swing.SwingConstants;

public class VMenu extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static Integer FIDClinica;
	public static String  FNOMEClinica;
	public static String  FCNPJClinica;
	private JPanel contentPane;
	private JPanel panel_2;
	private JPanel panel_1;
	private JPanel panel_3;
	private JMenuBar menuBar;
	private JMenu mmCad;
	private JMenuItem miUser;
	private JMenuItem miPet;
	private JMenuItem miRaca;
	private JMenuItem miCLinica;
	private JMenu mmCON;
	private JMenuItem mntmNewMenuItem;
	private JMenu mmREL;
	private JMenuItem mntmNewMenuItem_1;
	private JMenu mmATE;
	private JMenuItem miLogout;
	private JTextField textField;
	private JLabel descricao;

	/**
	 * FCNPJClinica + " - " + FNOMEClinica;
	 */
	
	public VMenu() {
		
//		setExtendedState(Frame.MAXIMIZED_BOTH);
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				descricao.setText(FCNPJClinica + " - " + FNOMEClinica);
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 771, 300);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new MigLayout("", "[1,grow 1][245.00,grow][][]", "[137.00,grow,top][]"));
		
		panel_2 = new JPanel();
		panel.add(panel_2, "cell 0 0,aligny center");
		panel_2.setLayout(new MigLayout("", "[]", "[]"));
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(VLoginClinicaCON.class.getResource("/vision/images/Group (2).png")));
		panel_2.add(lblNewLabel_2, "cell 0 0,alignx center");
		
		panel_1 = new JPanel();
		panel.add(panel_1, "cell 1 0,growx,aligny top");
		panel_1.setLayout(new MigLayout("", "[grow]", "[59.00][grow]"));
		
		panel_3 = new JPanel();
		panel_1.add(panel_3, "cell 0 0,grow");
		panel_3.setLayout(new BorderLayout(0, 0));
		
		menuBar = new JMenuBar();
		panel_3.add(menuBar, BorderLayout.CENTER);
		
		mmCad = new JMenu("Cadastrar");
		mmCad.setBackground(new Color(125, 137, 245));
		menuBar.add(mmCad);
		
		miUser = new JMenuItem("User");
		miUser.setBackground(new Color(125, 137, 245));
		miUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VUserCad uc = new VUserCad();
				uc.setLocationRelativeTo(null);
				uc.setVisible(true);
			}
		});
		mmCad.add(miUser);
		
		
		miPet = new JMenuItem("Pet");
		miPet.setBackground(new Color(125, 137, 245));
		miPet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VPetCad uc = new VPetCad();
				uc.setLocationRelativeTo(null);
				uc.setVisible(true);
			}
		});
		mmCad.add(miPet);
		
		miRaca = new JMenuItem("Raça");
		miRaca.setBackground(new Color(125, 137, 245));
		miRaca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VRacaCad uc = new VRacaCad();
				uc.setLocationRelativeTo(null);
				uc.setVisible(true);
			}
		});
		mmCad.add(miRaca);
		
		miCLinica = new JMenuItem("Clinica");
		miCLinica.setBackground(new Color(125, 137, 245));
		miCLinica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VClinicaCad vc = new VClinicaCad();
				vc.setLocationRelativeTo(null);
				vc.setVisible(true);
			}
		});
		mmCad.add(miCLinica);
		
		mmCON = new JMenu("Consultar");
		menuBar.add(mmCON);
		
		mntmNewMenuItem = new JMenuItem("New menu item");
		mmCON.add(mntmNewMenuItem);
		
		mmREL = new JMenu("Relatorio");
		menuBar.add(mmREL);
		
		mntmNewMenuItem_1 = new JMenuItem("New menu item");
		mmREL.add(mntmNewMenuItem_1);
		
		mmATE = new JMenu("Atendimento");
		menuBar.add(mmATE);
		
		miLogout = new JMenuItem("Logout");
		miLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VLoginClinicaCON l = new VLoginClinicaCON();
				l.setLocationRelativeTo(null);
				l.setVisible(true);
				dispose();
			}
		});
		mmATE.add(miLogout);
		
		descricao = new JLabel("New label");
		descricao.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(Box.createHorizontalGlue()); // adiciona espaçamento elástico para empurrar a descrição para a direita
		menuBar.add(descricao);
		
	
		
	
	}

}
