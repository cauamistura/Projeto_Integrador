package vision.atendimentos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
import vision.padrao.RoundJTextField;
import vision.padrao.lupaButton;

public class SaidaATE extends JFrame implements InterEntrada, InterReceita,InterSaida{


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private RoundJTextField edNumEntrada;
	private RoundJTextField edNomePet;
	private CPFTextField edCpfUser;
	private DateTextField edDataSaida;
	private DateTextField edDataEntrada;
	private RoundJTextField edNomeUser;
	private RoundJTextField edRaca;
	private JTextPane DescSaida;
	private JButton btnConf;
	private JButton btnLimpar;
	private JButton btnExcluir;
	private JButton btnConsultar;
	private lupaButton btnEntrada;
	private lupaButton btnReceita;
	private JLabel lblNumEntrada;
	private Boolean EntradaSelecionada = false;
	private JLabel lblStatus;
	
	
	private DAOAtendimentoSaida FDAOSaida = new DAOAtendimentoSaida();
	private DAOAtendimentoEntrada FDAOEntrada = new DAOAtendimentoEntrada();
	ArrayList<AtenimentoEntrada> list = new ArrayList<>();
	private DAOReceita FDAOReceita = new DAOReceita();
	private EntradaCON FEntradaCON;
	private boolean existeSaida;
	private boolean existeReceita; 
	
	public SaidaATE() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 482, 343);
		setTitle("Atendimento de Saida");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnReceita = new lupaButton("Receita");
		btnReceita.setText("");
		btnReceita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chamaReceitaCad();
			}			
		});
		btnReceita.setBounds(353, 0, 49, 25);
		contentPane.add(btnReceita);
		
		btnEntrada = new lupaButton("");
		btnEntrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chamaEntradaCon();
			}
		});
		btnEntrada.setBounds(117, 0, 49, 25);
		contentPane.add(btnEntrada);
		
		DescSaida = new JTextPane();
		DescSaida.setEditable(false);
		DescSaida.setBounds(255, 128, 166, 88);
		contentPane.add(DescSaida);
		
		btnConf = new JButton("Confirmar");
		btnConf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventConfirmar();
			}
		});
		btnConf.setBounds(68, 162, 117, 25);
		contentPane.add(btnConf);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventLimpar();
			}
		});
		btnLimpar.setBounds(68, 199, 117, 25);
		contentPane.add(btnLimpar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventDelete(FDAOSaida.getBDIDENTRADA());
			}
		});
		btnExcluir.setBounds(68, 236, 117, 25);
		contentPane.add(btnExcluir);
		
		edNumEntrada = new RoundJTextField();
		edNumEntrada.setEditable(false);
		edNumEntrada.setColumns(10);
		edNumEntrada.setBounds(117, 35, 114, 19);
		contentPane.add(edNumEntrada);
		
		edNomePet = new RoundJTextField();
		edNomePet.setEditable(false);
		edNomePet.setColumns(10);
		edNomePet.setBounds(117, 58, 114, 19);
		contentPane.add(edNomePet);
		
		edCpfUser = new CPFTextField();
		edCpfUser.setEditable(false);
		edCpfUser.setColumns(10);
		edCpfUser.setBounds(117, 89, 114, 19);
		contentPane.add(edCpfUser);
		
		edDataSaida = new DateTextField();
		edDataSaida.setEditable(false);
		edDataSaida.setColumns(10);
		edDataSaida.setBounds(117, 137, 114, 19);
		contentPane.add(edDataSaida);
		
		lblNumEntrada = new JLabel("N° Entrada");
		lblNumEntrada.setBounds(29, 37, 70, 15);
		contentPane.add(lblNumEntrada);
		
		JLabel lblNomePet = new JLabel("Pet");
		lblNomePet.setBounds(29, 60, 70, 15);
		contentPane.add(lblNomePet);
		
		JLabel lblUser = new JLabel("Ususario");
		lblUser.setBounds(29, 91, 70, 15);
		contentPane.add(lblUser);
		
		JLabel lblSaida = new JLabel("Saida");
		lblSaida.setBounds(29, 139, 70, 15);
		contentPane.add(lblSaida);
		
		JLabel lblReceita = new JLabel("Receita");
		lblReceita.setBounds(265, 5, 70, 15);
		contentPane.add(lblReceita);
		
		JLabel lblEntrada = new JLabel("Entrada");
		lblEntrada.setBounds(29, 5, 70, 15);
		contentPane.add(lblEntrada);
		
		edNomeUser = new RoundJTextField();
		edNomeUser.setEditable(false);
		edNomeUser.setColumns(10);
		edNomeUser.setBounds(255, 89, 114, 19);
		contentPane.add(edNomeUser);
		
		edDataEntrada = new DateTextField();
		edDataEntrada.setEditable(false);
		edDataEntrada.setColumns(10);
		edDataEntrada.setBounds(117, 116, 114, 19);
		contentPane.add(edDataEntrada);
		
		JLabel lblDataEntrada = new JLabel("Entrada");
		lblDataEntrada.setBounds(29, 118, 70, 15);
		contentPane.add(lblDataEntrada);
		
		lblStatus = new JLabel("Status: Inserindo uma Saida");
		lblStatus.setBounds(29, 283, 135, 13);
		contentPane.add(lblStatus);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chamaSaida();
			}
		});
		btnConsultar.setBounds(284, 238, 85, 21);
		contentPane.add(btnConsultar);
		
		edRaca = new RoundJTextField();
		edRaca.setEditable(false);
		edRaca.setColumns(10);
		edRaca.setBounds(255, 58, 114, 19);
		contentPane.add(edRaca);
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
			JOptionPane.showMessageDialog(null, "Selecione uma entrada antes de emitir uma receita");
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
		
		lblStatus.setVisible(false);
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
					
					JOptionPane.showMessageDialog(null, "Dados alterados com SUCESSO!!");
					
				}else {
					FDAOReceita.inserir(FDAOReceita);
					FDAOSaida.inserir(FDAOSaida);
					
					btnExcluir.setEnabled(true);
					
					JOptionPane.showMessageDialog(null, "Dados inseridos com SUCESSO!!");
				}
				
				int resposta = JOptionPane.showConfirmDialog(null,
						"Deseja deletar os dados presentes na Tela?",
						"Confirmação", JOptionPane.YES_NO_OPTION);

				if (resposta == JOptionPane.YES_OPTION) {
					eventLimpar();
				}
			}else {
				JOptionPane.showMessageDialog(null, "Cadastre uma receita antes de cadastrar uma saida");
				chamaReceitaCad();
			}
			
			
		
		}else {
			JOptionPane.showMessageDialog(null, "Selecione um  atendimento antes de confirmar a ação");
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
