package vision.atendimentos;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.DAOTDadosUser;
import control.DAOTPet;
import control.DAOTUser;
import model.MTDadosUser;
import model.MTPet;
import model.interfaces.InterfaceConsPet;
import model.interfaces.InterfaceConsUser;
import vision.cadastros.VUserCad;
import vision.consultas.VPetCON;
import vision.consultas.VUserCON;
import vision.padrao.*;		
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

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
	private RoundJTextFieldNum edNumEntrada;
	
	//Objetos do usuario
	private DAOTUser FDAOTUser = new DAOTUser();
	private DAOTDadosUser FDAOUserDados = new DAOTDadosUser();
	private VUserCON FVUserCON;
	
	//Obejtos do Pet
	private DAOTPet FDAOTPet = new DAOTPet();
	private VPetCON FVPetCON;	

	public VEntradaATE() {
		setTitle("Atendimento de Entrada");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 494, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbUser = new JLabel("Usuario:");
		lbUser.setHorizontalAlignment(SwingConstants.RIGHT);
		lbUser.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbUser.setBounds(75, 38, 67, 14);
		contentPane.add(lbUser);
		
		edCpf = new CPFTextField();
		edCpf.setBounds(152, 38, 105, 20);
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
		edNomeUser.setBounds(325, 39, 99, 20);
		edNomeUser.setEnabled(false);
		contentPane.add(edNomeUser);
		edNomeUser.setColumns(10);
		
		JLabel lbNumero = new JLabel("Número de Entrada:");
		lbNumero.setHorizontalAlignment(SwingConstants.RIGHT);
		lbNumero.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbNumero.setBounds(10, 11, 132, 14);
		contentPane.add(lbNumero);
		
		edNumEntrada = new RoundJTextFieldNum(8);
		edNumEntrada.setBounds(152, 11, 105, 20);
		contentPane.add(edNumEntrada);
		edNumEntrada.setColumns(10);
		
		JLabel lblPet = new JLabel("Pet:");
		lblPet.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPet.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPet.setBounds(75, 67, 67, 14);
		contentPane.add(lblPet);
		
	    edNomePet = new RoundJTextField();
		edNomePet.setToolTipText("Aperte F9 para consultar.");
		edNomePet.setColumns(10);
		edNomePet.setBounds(152, 67, 105, 20);
		contentPane.add(edNomePet);
		
	    edRacaPet = new RoundJTextField();
		edRacaPet.setEnabled(false);
		edRacaPet.setColumns(10);
		edRacaPet.setBounds(325, 68, 99, 20);
		contentPane.add(edRacaPet);
		
		lupaButton btnConPet = new lupaButton("");
		btnConPet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chamaConPet();
			}
		});
		btnConPet.setBounds(267, 67, 53, 23);
		contentPane.add(btnConPet);
		
		lupaButton btnConUser = new lupaButton("");
		btnConUser.setBounds(267, 38, 53, 23);
		btnConUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chamaConUser();
			}
		});
		contentPane.add(btnConUser);
	}
	
	private void chamaConUser() {
		ArrayList<MTDadosUser> list = new ArrayList<>();
		list = FDAOUserDados.ListConsulta(FDAOUserDados);
		
		FVUserCON = new VUserCON(list, this);
		FVUserCON.desabilitaExcluir();
		FVUserCON.setVisible(true);
	}
	
	private void chamaConPet() {
		if (!edCpf.existeCpfUsuario(FDAOTUser)) {
			JOptionPane.showInternalMessageDialog(null, "Usuário informado não existe!\nInforme um usuário valido");
			edCpf.requestFocus();
			return;
		}
	
		ArrayList<MTPet> list = new ArrayList<>();
		
		FDAOTUser.setBDCPF(edCpf.getText());
		FDAOTPet.setBDIDUSER(FDAOTUser.getIDUser(FDAOTUser));
		list = FDAOTPet.listTPetFiltradoUser(FDAOTPet);
		
		FVPetCON = new VPetCON(list, this);
		
		FVPetCON.desabilitaExcluir();
		FVPetCON.setVisible(true);
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
