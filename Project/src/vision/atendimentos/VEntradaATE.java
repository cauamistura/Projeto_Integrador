package vision.atendimentos;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.DAOTDadosUser;
import model.MTDadosUser;
import model.MTPet;
import model.interfaces.InterfaceConsPet;
import model.interfaces.InterfaceConsUser;
import vision.cadastros.VUserCad;
import vision.consultas.VUserCON;
import vision.padrao.*;		
import java.util.ArrayList;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VEntradaATE extends JFrame implements InterfaceConsUser, InterfaceConsPet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CPFTextField edCpf;
	private RoundJTextField edNomeUser;
	private RoundJTextField edRacaPet;
	private RoundJTextField edNomePet;
	
	//Objetos do usuario
	private VUserCad FVUserCad;
	private VUserCON FVUserCON;
	private DAOTDadosUser FDAOUser;
	private RoundJTextFieldNum edNumEntrada;
	

	public VEntradaATE() {
		setTitle("Atendimento de Entrada");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbUser = new JLabel("Usuario:");
		lbUser.setBounds(75, 38, 40, 14);
		contentPane.add(lbUser);
		
		edCpf = new CPFTextField();
		edCpf.setBounds(120, 35, 86, 20);
		edCpf.setToolTipText("Aperte F9 para consultar.");
		edCpf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F9) {
					chamaConUser();
				}
			}
		});
		contentPane.add(edCpf);
		edCpf.setColumns(10);
		
		edNomeUser = new RoundJTextField();
		edNomeUser.setBounds(269, 35, 86, 20);
		edNomeUser.setEnabled(false);
		contentPane.add(edNomeUser);
		edNomeUser.setColumns(10);
		
		JLabel lbNumero = new JLabel("Número de Entrada:");
		lbNumero.setBounds(10, 11, 105, 14);
		contentPane.add(lbNumero);
		
		edNumEntrada = new RoundJTextFieldNum(8);
		edNumEntrada.setBounds(120, 8, 86, 20);
		contentPane.add(edNumEntrada);
		edNumEntrada.setColumns(10);
		
		JLabel lblPet = new JLabel("Pet:");
		lblPet.setBounds(75, 67, 40, 14);
		contentPane.add(lblPet);
		
	    edNomePet = new RoundJTextField();
		edNomePet.setToolTipText("Aperte F9 para consultar.");
		edNomePet.setColumns(10);
		edNomePet.setBounds(120, 64, 86, 20);
		contentPane.add(edNomePet);
		
	    edRacaPet = new RoundJTextField();
		edRacaPet.setEnabled(false);
		edRacaPet.setColumns(10);
		edRacaPet.setBounds(269, 64, 86, 20);
		contentPane.add(edRacaPet);
		
		lupaButton btnConPet = new lupaButton("");
		btnConPet.setBounds(211, 63, 53, 23);
		contentPane.add(btnConPet);
		
		lupaButton btnConUser = new lupaButton("");
		btnConUser.setBounds(211, 34, 53, 23);
		btnConUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chamaConUser();
			}
		});
		contentPane.add(btnConUser);
	}
	
	private void chamaConUser() {
		if (FVUserCad == null){
			FVUserCad = new VUserCad();
		}
		if (FDAOUser != null && FVUserCON != null ) {
			FDAOUser = null;
			FVUserCON = null;
		}
		FDAOUser = new DAOTDadosUser();
		ArrayList<MTDadosUser> list = new ArrayList<>();
		list = FDAOUser.ListConsulta(FDAOUser);
		
		FVUserCON = new VUserCON(list, this);
		FVUserCON.desabilitaExcluir();
		FVUserCON.setVisible(true);
	}
	
	private void preencheUser(MTDadosUser list) {
		edCpf.setText(list.getBDCPF());
		edNomeUser.setText(list.getBDNOMEUSER());
	}
	
	private void preenchePet(MTPet list) {
		edNomePet.setText(list.getBDNOMEPET());
		edRacaPet.setText(list.getBDNOMERACA());
	}

	@Override
	public void preencheDadosUser(MTDadosUser listUser) {
		preencheUser(listUser);
	}

	@Override
	public void exluiUser(Integer bdiduser) {
		// TODO Auto-generated method stub
	}

	@Override
	public void preencheDadosPet(MTPet listPet) {
		preenchePet(listPet);
	}

	@Override
	public void exluiPet(Integer IdPet) {
		// TODO Auto-generated method stub
	}
}
