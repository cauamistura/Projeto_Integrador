package vision;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.DAODadosUser;
import control.DAOPet;
import model.DadosUser;
import model.Pet;
import model.interfaces.InterPet;
import model.interfaces.InterUsuario;
import vision.atendimentos.EntradaATE;
import vision.atendimentos.SaidaATE;
import vision.cadastros.*;
import vision.consultas.UserCON;
import vision.consultas.HistoricoCON;
import vision.consultas.PetCON;

import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

public class Menu extends JFrame implements InterUsuario, InterPet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Informações globais da Clinica
	public static Integer FIDClinica;
	public static String FNOMEClinica;
	public static String FCNPJClinica;

	// Informações Globais do Uuario
	public static Integer FPERMICAO;
	public static Integer FIDUSER;
	public static String FNomeUser;
	public static String FCPFUSER;

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
	private JMenuItem miClinicaDados;
	private JMenu mmDados;
	private JMenu mmSair;
	private JMenuItem miUserCons;
	private JMenuItem miUserDados;
	private JMenuItem miMedicamento;
	private JMenuItem mmPetCons;
	private JMenuItem miComorbidade;
	private JMenuItem miEntrada;
	private JMenuItem miSaida;
	private JMenuItem miHistorico;
	private JMenuItem miEncerrar;

	/**
	 * 
	 */

	public Menu() {

//		setExtendedState(MAXIMIZED_BOTH);
		Menu local = this;
		setTitle("Menu");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 300);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mmDados = new JMenu("Dados");
		mmDados.setFont(new Font("Arial", Font.PLAIN, 12));
		menuBar.add(mmDados);

		miClinicaDados = new JMenuItem("Clinica...");
		miClinicaDados.setFont(new Font("Arial", Font.PLAIN, 12));
		mmDados.add(miClinicaDados);

		miUserDados = new JMenuItem("Usuário...");
		miUserDados.setFont(new Font("Arial", Font.PLAIN, 12));
		miUserDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAODadosUser DAO = new DAODadosUser();
				UserCAD tela = new UserCAD();

				DadosUser lista = new DadosUser();

				lista = DAO.ListConsultaUserLOG(DAO);

				tela.preencheCampos(lista);
				tela.desabilitaBotoes(true);
				tela.setVisible(true);
			}
		});
		mmDados.add(miUserDados);
		miClinicaDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClinicaCAD clinica = new ClinicaCAD();
				clinica.setVisible(true);
			}
		});

		mmCad = new JMenu("Cadastrar");
		mmCad.setFont(new Font("Arial", Font.PLAIN, 13));
		menuBar.add(mmCad);

		miUser = new JMenuItem("Usuário...");
		miUser.setFont(new Font("Arial", Font.PLAIN, 13));
		miUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserCAD uc = new UserCAD();
				uc.setVisible(true);
			}
		});
		mmCad.add(miUser);

		miPet = new JMenuItem("Pet...");
		miPet.setFont(new Font("Arial", Font.PLAIN, 13));
		miPet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PetCAD uc = new PetCAD();
				uc.setLocationRelativeTo(null);
				uc.setVisible(true);
			}
		});
		mmCad.add(miPet);

		miRaca = new JMenuItem("Raça...");
		miRaca.setFont(new Font("Arial", Font.PLAIN, 13));
		miRaca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RacaCAD uc = new RacaCAD();
				uc.setLocationRelativeTo(null);
				uc.setVisible(true);
			}
		});
		mmCad.add(miRaca);

		miMedicamento = new JMenuItem("Medicamento...");
		miMedicamento.setFont(new Font("Arial", Font.PLAIN, 13));
		miMedicamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MedicamentoCAD md = new MedicamentoCAD();
				md.setLocationRelativeTo(null);
				md.setVisible(true);

			}
		});
		mmCad.add(miMedicamento);

		miComorbidade = new JMenuItem("Comorbidade...");
		miComorbidade.setFont(new Font("Arial", Font.PLAIN, 13));
		miComorbidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ComorbidadeCAD v = new ComorbidadeCAD();
				v.setLocationRelativeTo(null);
				v.setVisible(true);
			}
		});
		mmCad.add(miComorbidade);

		mmCON = new JMenu("Consultar");
		mmCON.setFont(new Font("Arial", Font.PLAIN, 12));
		menuBar.add(mmCON);
		
		miUserCons = new JMenuItem("Usuário...");
		miUserCons.setFont(new Font("Arial", Font.PLAIN, 12));
		miUserCons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAODadosUser DAO = new DAODadosUser();
				ArrayList<DadosUser> list = new ArrayList<>();
				list = DAO.ListConsulta(DAO);

				UserCON v = new UserCON(list, local);
				v.desBotoes();
				v.setVisible(true);
			}
		});
		
		miHistorico = new JMenuItem("Histórico...");
		miHistorico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HistoricoCON v = new HistoricoCON();
				if (Menu.FPERMICAO == 3) {
					v.HistoricoUser();
				}
				v.setVisible(true);
			}
		});
		miHistorico.setFont(new Font("Arial", Font.PLAIN, 12));
		mmCON.add(miHistorico);
		mmCON.add(miUserCons);

		mmPetCons = new JMenuItem("Pet...");
		mmPetCons.setFont(new Font("Arial", Font.PLAIN, 12));
		mmPetCons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAOPet DAO = new DAOPet();
				ArrayList<Pet> list = new ArrayList<>();
				list = DAO.ListTPet(DAO);

				PetCON v = new PetCON(list, local);
				v.desBotoes();
				v.setLocationRelativeTo(null);
				v.setVisible(true);
			}
		});
		mmCON.add(mmPetCons);

		mmATE = new JMenu("Atendimento");
		mmATE.setFont(new Font("Arial", Font.PLAIN, 13));
		menuBar.add(mmATE);

		miEntrada = new JMenuItem("Entrada...");
		miEntrada.setFont(new Font("Arial", Font.PLAIN, 12));
		miEntrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EntradaATE v = new EntradaATE();
				v.setLocationRelativeTo(null);
				v.setVisible(true);
			}
		});
		mmATE.add(miEntrada);
		
		miSaida = new JMenuItem("Saida..");
		miSaida.setFont(new Font("Dialog", Font.PLAIN, 12));
		miSaida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaidaATE v = new SaidaATE();
				v.setLocationRelativeTo(null);
				v.setVisible(true);
			}
		});
		mmATE.add(miSaida);

		mmSair = new JMenu("Sair");
		mmSair.setFont(new Font("Arial", Font.PLAIN, 13));
		menuBar.add(mmSair);

		miLogout = new JMenuItem("Logout...");
		miLogout.setFont(new Font("Dialog", Font.PLAIN, 12));
		mmSair.add(miLogout);
		
		miEncerrar = new JMenuItem("Encerrar...");
		miEncerrar.setFont(new Font("Dialog", Font.PLAIN, 12));
		miEncerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(null,
						"Você realmente deseja fechar o sistema?", "Confirmação",
						JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		mmSair.add(miEncerrar);
		miLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(null,
						"Você realmente deseja realizar o logout do sistema?", "Confirmação",
						JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
					Login l = new Login();
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
		descricao.setForeground(new Color(0, 0, 0));
		descricao.setFont(new Font("Arial", Font.BOLD, 12));
		descricao.setBackground(new Color(0, 0, 0));
		descricao.setEnabled(false);
		descricao.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(descricao, BorderLayout.SOUTH);

		lblNewLabel = new JLabel("");
		contentPane.add(lblNewLabel, BorderLayout.CENTER);

		addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				habilitaCampos(FPERMICAO);
			}

			private void habilitaCampos(Integer prPERMICAO) {
				miClinicaDados.setVisible(false);
				if (prPERMICAO == 1) {
					miClinicaDados.setVisible(true);
					descricao.setText("Usuario: " + FNomeUser + " | " + "Clinica: " + FNOMEClinica + " | Admin ");
				} else if (prPERMICAO == 2) {
					descricao.setText("Usuario: " + FNomeUser + " | " + "Clinica: " + FNOMEClinica + " | Funcionario ");
				} else if (prPERMICAO == 3) {
					mmATE.setVisible(false);
					mmCad.setVisible(false);
					miUserCons.setVisible(false);
					descricao.setText("Usuario: " + FNomeUser + " | " + "Clinica: " + FNOMEClinica);
				}
			}
		});
	}

	public void AtualizaDadosLogin(String nomeuser, String nomeClinica) {
		if (nomeClinica != "") {
			FNOMEClinica = nomeClinica;
		}
		if (nomeuser != "") {
			FNomeUser = nomeuser;
		}

	}

	@Override
	public void preencheDadosUser(DadosUser listUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exluiUser(Integer bdiduser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preencheDadosPet(Pet listPet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exluiPet(Integer IdPet) {
		// TODO Auto-generated method stub
		
	}
}
