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
import vision.padrao.DateTextField;
import vision.padrao.PanelComBackgroundImage;
import vision.padrao.RoundButton;
import vision.padrao.RoundJTextField;
import vision.padrao.Util;
import vision.padrao.lupaButton;
import java.awt.BorderLayout;
import java.awt.Color;

import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;

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
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_6;
	private JTextField textField_7;
	
	public SaidaATE() {
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
		
		JPanel background = new PanelComBackgroundImage(bg);
		background.setBackground(new Color(158, 174, 255));
		contentPane.add(background, "cell 1 1,alignx center");
		background.setLayout(new MigLayout("", "[grow][200px,grow][grow]", "[grow]"));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(125, 137, 245));
		background.add(panel_2, "cell 0 0,grow");
		panel_2.setLayout(new MigLayout("", "[grow]", "[grow][grow][]"));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(125, 137, 245));
		panel_2.add(panel_4, "cell 0 0,growx");
		panel_4.setLayout(new MigLayout("", "[grow]", "[][][][][][][]"));
		
		JLabel lblNewLabel_9 = new JLabel("Antendimento Saida");
		panel_4.add(lblNewLabel_9, "cell 0 0");
		
		JLabel lblNewLabel = new JLabel("Entrada:");
		panel_4.add(lblNewLabel, "flowx,cell 0 2");
		
		JButton btnNewButton = new lupaButton("");
		panel_4.add(btnNewButton, "cell 0 2");
		
		JLabel lblNewLabel_1 = new JLabel("Numero de entrada:");
		panel_4.add(lblNewLabel_1, "flowy,cell 0 4");
		
		textField = new RoundJTextField();
		panel_4.add(textField, "cell 0 4,growx");
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Entrada:");
		panel_4.add(lblNewLabel_2, "flowy,cell 0 5");
		
		textField_1 = new DateTextField();
		textField_1.setColumns(10);
		panel_4.add(textField_1, "cell 0 5,growx");
		
		JLabel lblNewLabel_3 = new JLabel("Saida:");
		panel_4.add(lblNewLabel_3, "flowy,cell 0 6");
		
		textField_2 = new DateTextField();
		textField_2.setColumns(10);
		panel_4.add(textField_2, "cell 0 6,growx");
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(125, 137, 245));
		panel_2.add(panel_3, "cell 0 1,growx");
		panel_3.setLayout(new MigLayout("", "[100px][30px][100px][0px]", "[][][][][]"));
		
		JButton btnNewButton_1_1 = new RoundButton("Confirmar");
		panel_3.add(btnNewButton_1_1, "cell 0 0,growx");
		
		JButton btnNewButton_1 = new RoundButton("Limpar");
		panel_3.add(btnNewButton_1, "cell 2 0,growx");
		
		JButton btnNewButton_1_2 = new RoundButton("Excluir");
		panel_3.add(btnNewButton_1_2, "cell 0 2,growx");
		
		JButton btnNewButton_1_3 = new RoundButton("Consultar");
		panel_3.add(btnNewButton_1_3, "cell 2 2,growx");
		
		JLabel lblNewLabel_8 = new JLabel("Status: Inserindo uma Saida");
		panel_2.add(lblNewLabel_8, "cell 0 2");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(125, 137, 245));
		background.add(panel_1, "cell 1 0,alignx center");
		panel_1.setLayout(new MigLayout("", "[200px,grow]", "[60px][15px][][][][][150px,grow][50px]"));
		
		JLabel lblNewLabel_7 = new JLabel("Receita:");
		panel_1.add(lblNewLabel_7, "flowx,cell 0 2");
		
		JButton btnNewButton_2 = new lupaButton("");
		panel_1.add(btnNewButton_2, "cell 0 2");
		
		JLabel lblNewLabel_4 = new JLabel("Pet:");
		panel_1.add(lblNewLabel_4, "flowy,cell 0 3");
		
		textField_3 = new RoundJTextField();
		textField_3.setColumns(10);
		panel_1.add(textField_3, "cell 0 3,growx");
		
		JLabel lblNewLabel_5 = new JLabel("Usuario:");
		panel_1.add(lblNewLabel_5, "flowy,cell 0 4");
		
		textField_4 = new CPFTextField();
		textField_4.setColumns(10);
		panel_1.add(textField_4, "cell 0 4,growx");
		
		JLabel lblNewLabel_6 = new JLabel("Descrição:");
		panel_1.add(lblNewLabel_6, "flowy,cell 0 5");
		
		JTextPane textPane = new JTextPane();
		panel_1.add(textPane, "cell 0 6,grow");
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(125, 137, 245));
		background.add(panel, "cell 2 0,growx");
		panel.setLayout(new MigLayout("", "[grow]", "[30px][][][13px][][120px]"));
		
		textField_7 = new RoundJTextField();
		textField_7.setColumns(10);
		panel.add(textField_7, "cell 0 2,growx");
		
		textField_6 = new RoundJTextField();
		textField_6.setColumns(10);
		panel.add(textField_6, "cell 0 4,growx");
	}
	
	protected void chamaEntrada() {
		EntradaATE e = new EntradaATE();
		e.setVisible(true);
		
	}
	
	private void chamaReceitaCad() {
		
		if(EntradaSelecionada) {

				ReceitaCAD v = new ReceitaCAD(this, FDAOReceita);
				v.setLocationRelativeTo(null);
				v.setVisible(true);
			
		}else {
			JOptionPane.showMessageDialog(null, "Selecione uma entrada antes de emitir uma receita");
		}	
	}

	private void preecheDados(AtenimentoEntrada atendimentos) {
		eventLimpar();
		
		DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("ddMMyyyy");
		
		//Setando text
		/*
		 * edCpfUser.setText(atendimentos.getBDCPF());
		 * edNomeUser.setText(atendimentos.getBDNOMEUSER());
		 * edNumEntrada.setText(String.valueOf(atendimentos.getBDIDENTRADA()));
		 * edNomePet.setText(atendimentos.getBDNOMEPET());
		 * edRaca.setText(atendimentos.getBDNOMERACA());
		 * edCpfUser.setText(atendimentos.getBDCPF());
		 * edDataEntrada.setText(atendimentos.getBDDATAENT().format(FOMATTER));
		 */
			
		//Setando DAOSaida
		FDAOSaida.setBDCOMORBIDADE(atendimentos.getBDCOMORBIDADE());
		FDAOSaida.setBDIDENTRADA(atendimentos.getBDIDENTRADA());
		FDAOSaida.setBDIDPET(atendimentos.getBDIDPET());
			
		/*
		 * edDataSaida.setEditable(true); DescSaida.setEditable(true);
		 */
			
		EntradaSelecionada = true;
		/* btnExcluir.setEnabled(false); */
				
	}
	public void travaCliente() {
		/*
		 * btnEntrada.setEnabled(false); btnReceita.setEnabled(false);
		 * 
		 * btnExcluir.setVisible(false); btnLimpar.setVisible(false);
		 * btnConsultar.setVisible(false);
		 * 
		 * edNumEntrada.setEnabled(false); edCpfUser.setEnabled(false);
		 * 
		 * edDataEntrada.setEnabled(false); edDataSaida.setEnabled(false);
		 * edNomePet.setEnabled(false); DescSaida.setEnabled(false);
		 * 
		 * lblStatus.setVisible(false);
		 */
	}
	
	private void preecheDadosSaida(AtendimentoSaida atendimentos) {
		
		eventLimpar();
		DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("ddMMyyyy");
		
		//Setando text
		/*
		 * edNumEntrada.setText(String.valueOf(atendimentos.getBDIDENTRADA()));
		 * edCpfUser.setText(atendimentos.getBDCPF());
		 * edNomeUser.setText(atendimentos.getBDNOMEUSER());
		 * edNomePet.setText(atendimentos.getBDNOMEPET());
		 * edRaca.setText(atendimentos.getBDNOMERACA());
		 * edDataEntrada.setText(atendimentos.getBDDATAENT().format(FOMATTER));
		 * edDataSaida.setText(atendimentos.getBDDATASAIDA().format(FOMATTER));
		 * DescSaida.setText(atendimentos.getBDDESC());
		 */
		//Setando DAOSaida
		FDAOSaida.setBDIDRECEITA(atendimentos.getBDIDRECEITA());
		FDAOSaida.setBDCOMORBIDADE(atendimentos.getBDCOMORBIDADE());
		FDAOSaida.setBDIDENTRADA(atendimentos.getBDIDENTRADA());
		FDAOSaida.setBDIDPET(atendimentos.getBDIDPET());
		eventDadosReceita(FDAOReceita.retornaReceita(atendimentos.getBDIDENTRADA()));
		
		EntradaSelecionada = true;
		existeSaida = true;
			
		/*
		 * edDataSaida.setEditable(true); DescSaida.setEditable(true);
		 */
	}
	
	private void eventLimpar() {
		/*
		 * edCpfUser.setText(""); edDataEntrada.setText(""); edDataSaida.setText("");
		 * edNomePet.setText(""); edNomeUser.setText(""); edNumEntrada.setText("");
		 * DescSaida.setText(""); edRaca.setText("");
		 */
		FDAOSaida.setBDIDENTRADA(null);
		FDAOReceita.setBDIDRECEITA(null);;
		
		/*
		 * edDataSaida.setEditable(false); DescSaida.setEditable(false);
		 */
		
		EntradaSelecionada = false;
		existeSaida = false;
		
		
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
			int resposta = JOptionPane.showConfirmDialog(null,
					"Você realmente deseja excluir?\nTodos os dados vinculados a esta saida serão excluídos.",
					"Confirmação", JOptionPane.YES_NO_OPTION);

			if (resposta == JOptionPane.YES_OPTION) {
				FDAOSaida.deletar(numEntrada);
				eventLimpar();
				
				JOptionPane.showMessageDialog(null, "Exclusão Confirmada");
			}else {
				JOptionPane.showMessageDialog(null, "Exclusão cancelada");
			}
			
			
		} else {
			JOptionPane.showMessageDialog(null, "Nenhum atedimento selecionado para realizar a exclusão");
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
