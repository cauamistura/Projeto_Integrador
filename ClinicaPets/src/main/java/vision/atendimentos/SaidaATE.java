package vision.atendimentos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import control.DAOAtendimentoEntrada;
import control.DAOAtendimentoSaida;
import control.DAOReceita;
import model.AtenimentoEntrada;
import model.AtendimentoSaida;
import model.Receita;
import model.interfaces.InterEntrada;
import model.interfaces.InterReceita;
import model.interfaces.InterSaida;
import vision.cadastros.ReceitaCAD;
import vision.cadastros.UserCAD;
import vision.consultas.EntradaCON;
import vision.consultas.SaidaCON;
import vision.padrao.CPFTextField;
import vision.padrao.CustomDialog;
import vision.padrao.DateTextField;
import vision.padrao.PanelComBackgroundImage;
import vision.padrao.RoundButton;
import vision.padrao.RoundJTextField;
import vision.padrao.Util;
import vision.padrao.lupaButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;

import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import java.awt.Font;

public class SaidaATE extends JFrame implements InterEntrada, InterReceita,InterSaida{


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Boolean EntradaSelecionada = false;
	private DAOAtendimentoSaida FDAOSaida = new DAOAtendimentoSaida();
	private DAOAtendimentoEntrada FDAOEntrada = new DAOAtendimentoEntrada();
	ArrayList<AtenimentoEntrada> list = new ArrayList<>();
	private DAOReceita FDAOReceita = new DAOReceita();
	private EntradaCON FEntradaCON;
	private boolean existeSaida;
	private boolean existeReceita; 
	private RoundJTextField edNumEntrada;
	private DateTextField edDataEntrada;
	private DateTextField edDataSaida;
	private RoundJTextField edNomePet;
	private CPFTextField edCpfUser;
	private RoundJTextField edNomeUser;
	private RoundJTextField edRaca;
	private JTextPane DescSaida;
	private RoundButton btnConfirmar;
	private RoundButton btnConsultar;
	private RoundButton btnExcluir;
	private RoundButton btnLimpar;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_2;
	private PanelComBackgroundImage background;
	private lupaButton btnEntrada;
	private lupaButton btnReceita;
	private JLabel lblStatus;
	private CustomDialog dialog;
	SaidaATE saida = this;
	
	
	public SaidaATE() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Util.getCaminhoIMG("logo.png")));
		
		BufferedImage bg = null;
		;
		try {
			bg = ImageIO.read(new File(Util.getCaminhoIMG("BGLogin.png")));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 744, 521);
		setTitle("Atendimento de Saida");
		contentPane = new JPanel();
		contentPane.setBackground(new Color(158, 174, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[50px][600px,grow][50px]", "[50px][350px,grow][50px]"));
		
		background = new PanelComBackgroundImage(bg);
		background.setBackground(new Color(158, 174, 255));
		contentPane.add(background, "cell 1 1,alignx center");
		background.setLayout(new MigLayout("", "[grow][300px,grow]", "[grow]"));
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(125, 137, 245));
		background.add(panel_2, "cell 0 0,grow");
		panel_2.setLayout(new MigLayout("", "[grow]", "[grow][grow][]"));
		
		panel_4 = new JPanel();
		panel_4.setBackground(new Color(125, 137, 245));
		panel_2.add(panel_4, "cell 0 0,growx");
		panel_4.setLayout(new MigLayout("", "[grow]", "[][][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("Entrada:");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panel_4.add(lblNewLabel, "flowx,cell 0 2");
		
		JLabel lblNewLabel_1 = new JLabel("Numero de entrada:");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panel_4.add(lblNewLabel_1, "flowy,cell 0 4");
		
		edNumEntrada = new RoundJTextField();
		edNumEntrada.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		panel_4.add(edNumEntrada, "cell 0 4,growx");
		edNumEntrada.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Entrada:");
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panel_4.add(lblNewLabel_2, "flowy,cell 0 5");
		
		edDataEntrada = new DateTextField();
		edDataEntrada.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		edDataEntrada.setColumns(10);
		panel_4.add(edDataEntrada, "cell 0 5,growx");
		
		JLabel lblNewLabel_3 = new JLabel("Saida:");
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panel_4.add(lblNewLabel_3, "flowy,cell 0 6");
		
		edDataSaida = new DateTextField();
		edDataSaida.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		edDataSaida.setColumns(10);
		panel_4.add(edDataSaida, "cell 0 6,growx");
		
		panel_3 = new JPanel();
		panel_3.setBackground(new Color(125, 137, 245));
		panel_2.add(panel_3, "cell 0 1,growx");
		panel_3.setLayout(new MigLayout("", "[100px][30px][100px][0px]", "[][][][]"));
		
		btnConfirmar = new RoundButton("Confirmar");
		btnConfirmar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventConfirmar();
			}
		});
		panel_3.add(btnConfirmar, "cell 0 0,growx,aligny bottom");
		
		btnLimpar = new RoundButton("Limpar");
		btnLimpar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventLimpar();
			}
		});
		panel_3.add(btnLimpar, "cell 2 0,growx");
		
		btnExcluir = new RoundButton("Excluir");
		btnExcluir.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventDelete(FDAOSaida.getBDIDENTRADA());
			}
		});
		panel_3.add(btnExcluir, "cell 0 2,growx");
		
		btnConsultar = new RoundButton("Consultar");
		btnConsultar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chamaSaida();
			}
		});
		panel_3.add(btnConsultar, "cell 2 2,growx");
		
		btnEntrada = new lupaButton("");
		btnEntrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chamaEntradaCon();
			}
		});
		panel_4.add(btnEntrada, "cell 0 2");
		

		lblStatus = new JLabel("Status: Aguardando...");
		lblStatus.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panel_2.add(lblStatus, "cell 0 2");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(125, 137, 245));
		background.add(panel_1, "cell 1 0,alignx center");
		panel_1.setLayout(new MigLayout("", "[200px,grow][][100px]", "[][70px][][][][][][][150px,grow][][]"));
		
		btnReceita = new lupaButton("");
		btnReceita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chamaReceitaCad();
			}
		});
		
		JLabel lblNewLabel_7 = new JLabel("Receita:");
		lblNewLabel_7.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panel_1.add(lblNewLabel_7, "flowx,cell 0 1,aligny center");
		panel_1.add(btnReceita, "cell 0 1,aligny center");
		
		JLabel lblNewLabel_4 = new JLabel("Pet:");
		lblNewLabel_4.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panel_1.add(lblNewLabel_4, "cell 0 2");
		
		edNomePet = new RoundJTextField();
		edNomePet.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		edNomePet.setColumns(10);
		panel_1.add(edNomePet, "cell 0 3,growx");
		
		edRaca = new RoundJTextField();
		edRaca.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		panel_1.add(edRaca, "cell 2 3");
		edRaca.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Usuario:");
		lblNewLabel_5.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panel_1.add(lblNewLabel_5, "cell 0 4");
		
		edCpfUser = new CPFTextField();
		edCpfUser.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		edCpfUser.setColumns(10);
		panel_1.add(edCpfUser, "cell 0 5,growx");
		
		edNomeUser = new RoundJTextField();
		edNomeUser.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		panel_1.add(edNomeUser, "cell 2 5");
		edNomeUser.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Descrição:");
		lblNewLabel_6.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panel_1.add(lblNewLabel_6, "flowy,cell 0 7");
		
		DescSaida = new JTextPane();
		panel_1.add(DescSaida, "cell 0 8,grow");
	}
	
	protected void chamaEntrada() {
		EntradaATE e = new EntradaATE();
		e.setVisible(true);
		
	}

	private void chamaSaida() {
		ArrayList<AtendimentoSaida> list = new ArrayList<>();
		list = FDAOSaida.ListTSaida(FDAOSaida);
		
		SaidaCON v = new SaidaCON(list,this);
		v.setVisible(true);
	}
	
	private void chamaEntradaCon() {
			list = FDAOEntrada.ListConsulta(FDAOEntrada);
			FEntradaCON = new EntradaCON(list, this,true,this);
			FEntradaCON.setVisible(true);
	}
	
	private void chamaReceitaCad() {
		
		if(EntradaSelecionada) {

				ReceitaCAD v = new ReceitaCAD(this, FDAOReceita);
				v.setLocationRelativeTo(null);
				v.setVisible(true);
			
		}else {
			dialog = new CustomDialog("Atenção!!", "Selecione uma entrada para cadastrar uma Receita", saida, true, true);
			dialog.setVisible(true);
		}	
	}

	private void preecheDados(AtenimentoEntrada atendimentos) {
		eventLimpar();
		
		DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("ddMMyyyy");
		
		//Setando text
		edCpfUser.setText(atendimentos.getBDCPF());
		edNomeUser.setText(atendimentos.getBDNOMEUSER());
		edNumEntrada.setText(String.valueOf(atendimentos.getBDIDENTRADA()));
		edNomePet.setText(atendimentos.getBDNOMEPET());
		edRaca.setText(atendimentos.getBDNOMERACA());
		edCpfUser.setText(atendimentos.getBDCPF());
		edDataEntrada.setText(atendimentos.getBDDATAENT().format(FOMATTER));
			
		//Setando DAOSaida
		FDAOSaida.setBDCOMORBIDADE(atendimentos.getBDCOMORBIDADE());
		FDAOSaida.setBDIDENTRADA(atendimentos.getBDIDENTRADA());
		FDAOSaida.setBDIDPET(atendimentos.getBDIDPET());
			
		edDataSaida.setEditable(true);
		DescSaida.setEditable(true);
			
		EntradaSelecionada = true;
		btnExcluir.setEnabled(false);
		
		lblStatus.setText("Status: Inserindo uma Saida");
				
	}
	public void travaCliente() {
		btnEntrada.setEnabled(false);
		btnReceita.setEnabled(false);
		
		btnExcluir.setVisible(false);
		btnLimpar.setVisible(false);
		btnConsultar.setVisible(false);
		
		edNumEntrada.setEnabled(false);
		edCpfUser.setEnabled(false);
		
		edDataEntrada.setEnabled(false);
		edDataSaida.setEnabled(false);
		edNomePet.setEnabled(false);
		DescSaida.setEnabled(false);
		
	}
	
	private void preecheDadosSaida(AtendimentoSaida atendimentos) {
		
		eventLimpar();
		DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("ddMMyyyy");
		
		//Setando text
		edNumEntrada.setText(String.valueOf(atendimentos.getBDIDENTRADA()));
		edCpfUser.setText(atendimentos.getBDCPF());
		edNomeUser.setText(atendimentos.getBDNOMEUSER());
		edNomePet.setText(atendimentos.getBDNOMEPET());
		edRaca.setText(atendimentos.getBDNOMERACA());
		edDataEntrada.setText(atendimentos.getBDDATAENT().format(FOMATTER));
		edDataSaida.setText(atendimentos.getBDDATASAIDA().format(FOMATTER));
		DescSaida.setText(atendimentos.getBDDESC());
			
		//Setando DAOSaida
		FDAOSaida.setBDIDRECEITA(atendimentos.getBDIDRECEITA());
		FDAOSaida.setBDCOMORBIDADE(atendimentos.getBDCOMORBIDADE());
		FDAOSaida.setBDIDENTRADA(atendimentos.getBDIDENTRADA());
		FDAOSaida.setBDIDPET(atendimentos.getBDIDPET());
		eventDadosReceita(FDAOReceita.retornaReceita(atendimentos.getBDIDENTRADA()));
		
		EntradaSelecionada = true;
		existeSaida = true;
			
		edDataSaida.setEditable(true);
		DescSaida.setEditable(true);
		
		btnExcluir.setEnabled(true);
		lblStatus.setText("Status: Alterando uma Saida");
			
	}
	
	private void eventConfirmar() {
	
		if(EntradaSelecionada) {
			
			if(existeReceita) {
				FDAOSaida.setBDIDSAIDA(FDAOSaida.getChaveID("TAtendimento_Saida", "BDIDSAIDA"));
				FDAOSaida.setBDDESC(DescSaida.getText());
				FDAOSaida.setBDDATASAIDA(edDataSaida.getDate());

				if(existeSaida) {
					FDAOReceita.alterar(FDAOReceita);
					FDAOSaida.alterar(FDAOSaida);
					
					dialog = new CustomDialog("Confirmação",  "Dados alterados com SUCESSO!!", saida, true, false);
					dialog.setVisible(true);

					
				}else {
					FDAOReceita.inserir(FDAOReceita);
					FDAOSaida.inserir(FDAOSaida);
					
					btnExcluir.setEnabled(true);
					
					dialog = new CustomDialog("Confirmação",  "Dados inseridos com SUCESSO!!", saida, true, false);
					dialog.setVisible(true);

				}
				
				dialog = new CustomDialog("Confirmação",  "Deseja limpra os dados presentes na Tela?", saida, true, true);
				dialog.setVisible(true);
				
				if (dialog.showDialog()) {
					eventLimpar();
				}
			}else {
				
				dialog = new CustomDialog("Confirmação",  "Cadastre uma receita antes de cadastrar uma saida", saida, true, false);
				dialog.setVisible(true);
				
				chamaReceitaCad();
			}
			
			
		
		}else {
			dialog = new CustomDialog("Confirmação", "Selecione um  atendimento antes de confirmar a ação", saida, true, false);
			dialog.setVisible(true);
		}	
		
			
	}
	
	private void eventLimpar() {
		edCpfUser.setText("");
		edDataEntrada.setText("");
		edDataSaida.setText("");
		edNomePet.setText("");
		edNomeUser.setText("");
		edNumEntrada.setText("");
		DescSaida.setText("");
		edRaca.setText("");
		
		FDAOSaida.setBDIDENTRADA(null);
		FDAOReceita.setBDIDRECEITA(null);;
		
		edDataSaida.setEditable(false);
		DescSaida.setEditable(false);
		
		EntradaSelecionada = false;
		existeSaida = false;
		
		btnExcluir.setEnabled(true);
		
	}
	
	private void eventDadosReceita(Receita listReceita) {
		
		FDAOSaida.setBDIDRECEITA(listReceita.getBDIDRECEITA());
		
		FDAOReceita.setBDIDMEDICACAO(listReceita.getBDIDMEDICACAO());
		FDAOReceita.setBDIDRECEITA(listReceita.getBDIDRECEITA());
		FDAOReceita.setBDINICIORECEITA(listReceita.getBDINICIORECEITA());
		FDAOReceita.setBDFINALRECEITA(listReceita.getBDFINALRECEITA());
		FDAOReceita.setBDDESCRICAO(listReceita.getBDDESCRICAO());
		FDAOReceita.setBDNOMEMEDICACAO(listReceita.getBDNOMEMEDICACAO());
		
		existeReceita = true;
	
	}
	
	private void eventDelete(Integer numEntrada) {
		numEntrada = FDAOSaida.getBDIDENTRADA();
	
		if (!(numEntrada == null)) {

			dialog = new CustomDialog("Confirmação",  "Você realmente deseja excluir?\nTodos os dados vinculados a esta saida serão excluídos.", saida, true, true);
			dialog.setVisible(true);
			
			if (dialog.showDialog()) {
				FDAOSaida.deletar(numEntrada);
				eventLimpar();
				
				dialog = new CustomDialog("Confirmação","Exclusão Confirmada", saida, true, false);
				dialog.setVisible(true);
	
			}else {
				dialog = new CustomDialog("Confirmação","Exclusão Cancelada", saida, true, false);
				dialog.setVisible(true);
			}
			
			
		} else {
			dialog = new CustomDialog("Atenção!!", "Nenhum atedimento selecionado para realizar a exclusão", saida, true, true);
			dialog.setVisible(true);
		}
			
	}
	
	@Override
	public void preencheDadosEntrada(AtenimentoEntrada listAtendimento) {
		preecheDados(listAtendimento);
	}
	
	@Override
	public void preecherReceita(Receita dado) {
		eventDadosReceita(dado);
	}

	@Override
	public void exluirAtendimento(Integer numAtendimento) {
		// TODO Auto-generated method stub
	}

	@Override
	public void preencheDadosSaida(AtendimentoSaida listAtendimento) {
		preecheDadosSaida(listAtendimento);
		
	}

	@Override
	public void exluirAtendimentoSaida(Integer numAtendimento) {
		eventDelete(numAtendimento);
		
	}
}