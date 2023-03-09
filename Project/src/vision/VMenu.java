package vision;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import vision.cadastros.VPetCad;
import vision.cadastros.VUserCad;

import javax.swing.JMenuBar;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import vision.cadastros.*;

public class VMenu extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static Integer FIDClinica;
	public static String  FNOMEClinica;
	public static String  FCNPJClinica;
	private JPanel contentPane;
	private JTextField edDescricao;


	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VMenu frame = new VMenu();
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
	public VMenu() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				edDescricao.setText(FCNPJClinica + " - " + FNOMEClinica);
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		setExtendedState(MAXIMIZED_BOTH);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mmCad = new JMenu("Cadastrar");
		menuBar.add(mmCad);
		
		JMenuItem miUser = new JMenuItem("User");
		miUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VUserCad uc = new VUserCad();
				uc.setLocationRelativeTo(null);
				uc.setVisible(true);
			}
		});
		mmCad.add(miUser);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		miUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VUserCad uc = new VUserCad();
				uc.setLocationRelativeTo(null);
				uc.setVisible(true);
			}
		});
		mmCad.add(miUser);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JMenuItem miPet = new JMenuItem("Pet");
		miPet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VUserCad uc = new VUserCad();
				uc.setLocationRelativeTo(null);
				uc.setVisible(true);
			}
		});
		mmCad.add(miPet);
		
		JMenuItem miCLinica = new JMenuItem("Clinica");
		miCLinica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VClinicaCad vc = new VClinicaCad();
				vc.setLocationRelativeTo(null);
				vc.setVisible(true);
			}
		});
		mmCad.add(miCLinica);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		edDescricao = new JTextField();
		edDescricao.setEnabled(false);
		contentPane.add(edDescricao, BorderLayout.SOUTH);
		edDescricao.setColumns(10);
	}
}
