package vision;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import vision.cadastros.*;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.SwingConstants;

public class VMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Informações globais da Clinica
	public static Integer FIDClinica;
	public static String FNOMEClinica;
	public static String FCNPJClinica;
	
	//Informações Globais do Uuario
	public static Integer FPERMICAO;
	public static Integer FIDUSER;
	public static String  FNomeUser;
	
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mmCad;
	private JMenuItem miUser;
	private JMenuItem miPet;
	private JMenuItem miRaca;
	private JMenu mmCON;
	private JMenu mmATE;
	private JLabel descricao;
	private JLabel lblNewLabel;
	private JMenuItem miLogout;
	private JMenuItem Clinica;
	private JMenu mmConfiguracao;
	private JMenu mmSair;

	/**
	 * 
	 */

	public VMenu() {

//		setExtendedState(MAXIMIZED_BOTH);
		setTitle("Menu");
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 771, 300);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mmConfiguracao = new JMenu("Configurações");
		mmConfiguracao.setEnabled(false);
		menuBar.add(mmConfiguracao);
		
		Clinica = new JMenuItem("Clinica...");
		mmConfiguracao.add(Clinica);
		Clinica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VClinicaCad clinica = new VClinicaCad();
				clinica.setVisible(true);
			}
		});

		mmCad = new JMenu("Cadastrar");
		mmCad.setEnabled(false);
		mmCad.setBackground(new Color(255, 255, 255));
		menuBar.add(mmCad);

		miUser = new JMenuItem("Usuário...");
		miUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VUserCad uc = new VUserCad();
				uc.setLocationRelativeTo(null);
				uc.setVisible(true);
			}
		});
		miUser.setBackground(new Color(255, 255, 255));
		mmCad.add(miUser);

		miPet = new JMenuItem("Pet...");
		miPet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VPetCad uc = new VPetCad();
				uc.setLocationRelativeTo(null);
				uc.setVisible(true);
			}
		});
		miPet.setBackground(new Color(255, 255, 255));
		mmCad.add(miPet);

		miRaca = new JMenuItem("Raça...");
		miRaca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VRacaCad uc = new VRacaCad();
				uc.setLocationRelativeTo(null);
				uc.setVisible(true);
			}
		});
		miRaca.setBackground(new Color(255, 255, 255));
		mmCad.add(miRaca);

		mmCON = new JMenu("Consultar");
		mmCON.setEnabled(false);
		menuBar.add(mmCON);

		mmATE = new JMenu("Atendimento");
		mmATE.setEnabled(false);
		menuBar.add(mmATE);
		
		mmSair = new JMenu("Sair");
		mmSair.setEnabled(false);
		menuBar.add(mmSair);
		
		miLogout = new JMenuItem("Logout...");
		mmSair.add(miLogout);
		miLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(null,
						"Você realmente deseja realizar o logout do sistema?", "Confirmação",
						JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
					VLogin l = new VLogin();
					l.setLocationRelativeTo(null);
					l.setVisible(true);
					dispose();
				}
			}
		});
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		descricao = new JLabel("New label");
		descricao.setEnabled(false);
		descricao.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(descricao, BorderLayout.SOUTH);

		lblNewLabel = new JLabel("");
		contentPane.add(lblNewLabel, BorderLayout.CENTER);
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				descricao.setText("Usuario: "+ FNomeUser +" | " + "Clinica: "+ FNOMEClinica);
				habilitaCampos(FPERMICAO);
			}

			private void habilitaCampos(Integer prPERMICAO) {
				if(prPERMICAO == 1) {
					mmCad.setEnabled(true); 
					mmCON.setEnabled(true);
					mmATE.setEnabled(true);
					mmConfiguracao.setEnabled(true);
					mmSair.setEnabled(true);
				} else {
					mmConfiguracao.setVisible(false);
				}
				
			}
		});
	}
}
