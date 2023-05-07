package vision.atendimentos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import control.DAOAtendimentoEntrada;
import control.DAOAtendimentoSaida;
import control.DAOTReceita;
import model.MTAtendimenoEntrada;
import model.MTReceita;
import model.interfaces.InterEntrada;
import model.interfaces.InterReceita;
import vision.cadastros.VReceitaCad;
import vision.consultas.VEntradaCON;
import vision.padrao.CPFTextField;
import vision.padrao.DateTextField;
import vision.padrao.RoundJTextField;
import vision.padrao.lupaButton;

public class VSaidaATE extends JFrame implements InterEntrada, InterReceita{

	private JPanel contentPane;
	private RoundJTextField edNumEntrada;
	private RoundJTextField edNomePet;
	private CPFTextField edCpfUser;
	private DateTextField edDataSaida;
	private DateTextField edDataEntrada;
	private RoundJTextField edNomeUser;
	private JTextPane DescSaida;
	private JButton btnConf;
	private JButton btnLimpar;
	private JButton btnExcluir;
	private lupaButton btnEntrada;
	private lupaButton btReceita;
	private JLabel lblNumEntrada;
	private Boolean ExisteReceita = true;
	private Boolean EntradaSelecionada = false;
	
	private DAOAtendimentoEntrada FDAOEntrada = new DAOAtendimentoEntrada();
	private DAOTReceita FDAOReceita = new DAOTReceita();
	private VEntradaCON FEntradaCON; 
	
	private DAOAtendimentoSaida FDAOSaida = new DAOAtendimentoSaida();
	
	public VSaidaATE() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btReceita = new lupaButton("Receita");
		btReceita.setText("");
		btReceita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chamaReceitaCad();
			}			
		});
		btReceita.setBounds(353, 0, 49, 25);
		contentPane.add(btReceita);
		
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
	}
	
	private void chamaEntradaCon() {
		
			ArrayList<MTAtendimenoEntrada> list = new ArrayList<>();
			list = FDAOEntrada.ListConsulta(FDAOEntrada);
			FEntradaCON = new VEntradaCON(list, this);
			FEntradaCON.setVisible(true);
		
	}
	
	private void chamaReceitaCad() {
		
		//fazer com que a tela volte a ser true qaundo o usuario não cadastrar uma receita
		if(EntradaSelecionada) {
			if (ExisteReceita) {
				VReceitaCad v = new VReceitaCad(this, false);
				v.setLocationRelativeTo(null);
				v.setVisible(true);
				
				ExisteReceita = false;
				
			}else {
				VReceitaCad v = new VReceitaCad(this, true);
				v.setLocationRelativeTo(null);
				v.setVisible(true);
			}
		}else {
			JOptionPane.showMessageDialog(null, "Selecione uma entrada antes de emitir uma receita");
		}
			
			
	}


	private void preecheDados(MTAtendimenoEntrada atendimentos) {
		DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("ddMMyyyy");
		if(!FDAOSaida.retornaIdReceita(atendimentos.getBDIDENTRADA())) {
			edCpfUser.setText(atendimentos.getBDCPF());
			edNomeUser.setText(atendimentos.getBDNOMEUSER());
			edNumEntrada.setText(String.valueOf(atendimentos.getBDIDENTRADA()));
			edDataEntrada.setText(atendimentos.getBDDATAENT().format(FOMATTER));
			edNomePet.setText(atendimentos.getBDNOMEPET());
			
			FDAOSaida.setBDIDCOMORBIDADE(atendimentos.getBDCOMORBIDADE());
			FDAOSaida.setBDIDENTRADA(atendimentos.getBDIDENTRADA());
			FDAOSaida.setBDIDPET(atendimentos.getBDIDPET());
			
			EntradaSelecionada = true;
			
			edDataSaida.setEditable(true);
			DescSaida.setEditable(true);
			
		}else {
			JOptionPane.showMessageDialog(null, "Entrada já possui uma saida");
			EntradaSelecionada = false;
			
		}
			
			
	}
	
	private void eventConfirmar() {
		
		FDAOSaida.setBDDESC(DescSaida.getText());
		FDAOSaida.setBDDATASAIDA(edDataSaida.getDate());
		
		FDAOSaida.inserir(FDAOSaida);
	}
	
	private void eventLimpar() {
		edCpfUser.setText("");
		edDataEntrada.setText("");
		edDataSaida.setText("");
		edNomePet.setText("");
		edNomeUser.setText("");
		edNumEntrada.setText("");
		ExisteReceita = true;
		
	}
	
	private void eventDadosReceita(MTReceita listReceita) {
		FDAOSaida.setBDIDRECEITA(listReceita.getBDIDRECEITA());
	}
	
	
	@Override
	public void preencheDadosEntrada(MTAtendimenoEntrada listAtendimento) {
		preecheDados(listAtendimento);
	}
	
	@Override
	public void preecherReceita(MTReceita dado) {
		eventDadosReceita(dado);
	}

	@Override
	public void exluirAtendimento(Integer numAtendimento) {
		// TODO Auto-generated method stub
	}

}
