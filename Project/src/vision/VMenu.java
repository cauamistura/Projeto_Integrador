package vision;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import vision.cadastros.VClinicaCad;
import vision.cadastros.VPetCad;
import vision.cadastros.VRacaCad;
import vision.cadastros.VUserCad;
import vision.padrao.PanelComBackgroundImage;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Frame;

public class VMenu extends JFrame {
	
	public static Integer FIDClinica;
	public static String  FNOMEClinica;
	public static String  FCNPJClinica;
	private JPanel contentPane;
	private JTextField edDescricao;

	/**
	 * Create the frame.
	 */
	
	public VMenu() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				edDescricao.setText(FCNPJClinica + " - " + FNOMEClinica);
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		BufferedImage bg = null;
		;
		try {
			bg = ImageIO.read(new File("src/vision/images/teste.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new PanelComBackgroundImage(bg);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[50,grow 50][300,grow][50,grow 50]", "[138.00][35.00,grow]"));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(125, 137, 245));
		panel.add(panel_1, "cell 1 1,growx,aligny top");
		panel_1.setLayout(new MigLayout("", "[][228.00,grow][]", "[][][26.00]"));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(125, 137, 245));
		panel_1.add(panel_2, "cell 1 1,alignx center,aligny center");
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(0, 10, 0, 10));
		menuBar.setLayout(new FlowLayout());
		menuBar.setBackground(new Color(125, 137, 245));
		panel_2.add(menuBar, BorderLayout.CENTER);
		
		JMenu mmCad = new JMenu("Cadastrar");
		mmCad.setBackground(new Color(125, 137, 245));
		menuBar.add(mmCad);
		
		JMenuItem miUser = new JMenuItem("User");
		miUser.setBackground(new Color(125, 137, 245));
		miUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VUserCad uc = new VUserCad();
				uc.setLocationRelativeTo(null);
				uc.setVisible(true);
			}
		});
		mmCad.add(miUser);
		
		JMenuItem miPet = new JMenuItem("Pet");
		miPet.setBackground(new Color(125, 137, 245));
		miPet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VPetCad uc = new VPetCad();
				uc.setLocationRelativeTo(null);
				uc.setVisible(true);
			}
		});
		mmCad.add(miPet);
		
		
		
		JMenuItem miRaca = new JMenuItem("Raça");
		miRaca.setBackground(new Color(125, 137, 245));
		miRaca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VRacaCad uc = new VRacaCad();
				uc.setLocationRelativeTo(null);
				uc.setVisible(true);
			}
		});
		mmCad.add(miRaca);
		
		JMenuItem miCLinica = new JMenuItem("Clinica");
		miCLinica.setBackground(new Color(125, 137, 245));
		miCLinica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VClinicaCad vc = new VClinicaCad();
				vc.setLocationRelativeTo(null);
				vc.setVisible(true);
			}
		});
		mmCad.add(miCLinica);
		
		JMenu mnNewMenu = new JMenu("Consultar");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_1 = new JMenu("Relatorio");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("New menu item");
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_2 = new JMenu("Atendimento");
		menuBar.add(mnNewMenu_2);
		
		edDescricao = new JTextField();
		edDescricao.setEnabled(false);
		contentPane.add(edDescricao, BorderLayout.SOUTH);
		edDescricao.setColumns(10);
	}

}
