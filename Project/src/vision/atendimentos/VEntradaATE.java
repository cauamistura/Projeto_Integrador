package vision.atendimentos;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.DAOAtendimentoEntrada;
import control.DAOTDadosUser;
import control.DAOTPet;
import control.DAOTUser;
import model.MTDadosUser;
import model.MTPet;
import model.interfaces.InterfaceConsPet;
import model.interfaces.InterfaceConsUser;
import vision.cadastros.VPetCad;
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
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;

public class VEntradaATE extends JFrame implements InterfaceConsUser, InterfaceConsPet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CPFTextField edCpf;
	private RoundJTextField edNomePet;
	private RoundJTextFieldNum edNumEntrada;
	private DateTextField edDataEntrada;
	private JTextPane txtNomeUser;
	private JTextPane txtNomeRaca;
	private JTextPane pnDesc;
	
	//Objetos do Atendimeno
	private DAOAtendimentoEntrada FDAOEntrada = new DAOAtendimentoEntrada();
	
	//Objetos do usuario
	private VUserCad TelaUser;
	private DAOTUser FDAOTUser = new DAOTUser();
	private DAOTDadosUser FDAOUserDados = new DAOTDadosUser();
	private VUserCON FVUserCON;
	
	//Obejtos do Pet
	private ArrayList<MTPet> listPet = new ArrayList<>();
	private VPetCad TelaPet;
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
		lbUser.setBounds(75, 40, 67, 14);
		contentPane.add(lbUser);
		
		edCpf = new CPFTextField();
		edCpf.setBounds(152, 37, 105, 20);
		edCpf.setToolTipText("Aperte F9 para consultar.");
		edCpf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F9) {
					chamaConUser();
				}
				if (e.getKeyCode() == KeyEvent.VK_F4) {
					if (TelaUser == null) {
						TelaUser = new VUserCad();
					}
					TelaUser.setVisible(true);
				}
			}
		});
		contentPane.add(edCpf);
		edCpf.setColumns(10);
		
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
	    edNomePet.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F9) {
					chamaConPet();
				}
				if (e.getKeyCode() == KeyEvent.VK_F4) {
					if (TelaPet == null) {
						TelaPet = new VPetCad();
					}
					TelaPet.setVisible(true);
				}
			}
		});
		edNomePet.setToolTipText("Aperte F9 para consultar.");
		edNomePet.setColumns(10);
		edNomePet.setBounds(152, 64, 105, 20);
		contentPane.add(edNomePet);
		
		lupaButton btnConPet = new lupaButton("");
		btnConPet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chamaConPet();
			}
		});
		btnConPet.setBounds(267, 63, 53, 23);
		contentPane.add(btnConPet);
		
		lupaButton btnConUser = new lupaButton("");
		btnConUser.setBounds(267, 36, 53, 23);
		btnConUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chamaConUser();
			}
		});
		contentPane.add(btnConUser);
		
		RoundButton btnExcluir = new RoundButton("Excluir");
		btnExcluir.setBounds(55, 207, 86, 23);
		contentPane.add(btnExcluir);
		
		RoundButton btnLimpar = new RoundButton("Login");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpaCampos();
			}
		});
		btnLimpar.setText("Limpar");
		btnLimpar.setBounds(145, 207, 79, 23);
		contentPane.add(btnLimpar);
		
		RoundButton btnConsulta = new RoundButton("Login");
		btnConsulta.setText("Consultar");
		btnConsulta.setBounds(234, 207, 89, 23);
		contentPane.add(btnConsulta);
		
		RoundButton btnConfirmar = new RoundButton("Login");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acaoConfirma();
			}
		});
		btnConfirmar.setText("Confirmar");
		btnConfirmar.setBounds(327, 207, 95, 23);
		contentPane.add(btnConfirmar);
		
		edDataEntrada = new DateTextField();
		edDataEntrada.setBounds(152, 92, 105, 20);
		contentPane.add(edDataEntrada);
		edDataEntrada.setColumns(10);
		
		JLabel lbData = new JLabel("Entrada:");
		lbData.setHorizontalAlignment(SwingConstants.RIGHT);
		lbData.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbData.setBounds(75, 95, 67, 14);
		contentPane.add(lbData);
		
		txtNomeRaca = new JTextPane();
		txtNomeRaca.setEnabled(false);
		txtNomeRaca.setBounds(330, 64, 127, 20);
		contentPane.add(txtNomeRaca);
		
		txtNomeUser = new JTextPane();
		txtNomeUser.setEnabled(false);
		txtNomeUser.setBounds(330, 37, 127, 20);
		contentPane.add(txtNomeUser);
		
		JLabel lbDescricao = new JLabel("Descrição:");
		lbDescricao.setHorizontalAlignment(SwingConstants.RIGHT);
		lbDescricao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbDescricao.setBounds(75, 125, 67, 14);
		contentPane.add(lbDescricao);
		
		JScrollPane pnPane = new JScrollPane();
		pnPane.setBounds(152, 123, 272, 73);
		contentPane.add(pnPane);
		
		pnDesc = new JTextPane();
		pnPane.setViewportView(pnDesc);
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
			JOptionPane.showInternalMessageDialog(null, "Usuário informado não existe!\nInforme um usuário valido ou aperte F4 para cadastrar.");
			edCpf.requestFocus();
			return;
		}
		FDAOTUser.setBDCPF(edCpf.getText());
		FDAOTPet.setBDIDUSER(FDAOTUser.getIDUser(FDAOTUser));
		listPet = FDAOTPet.listTPetFiltradoUser(FDAOTPet);
		
		if (listPet.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Este usuario não tem Pet(s) cadastrados!\nAperte F4 para cadastrar.");
			return;
		}
		FVPetCON = new VPetCON(listPet, this);
		
		FVPetCON.desExcluir();
		FVPetCON.setVisible(true);
	}
	
	private void preencheUser(MTDadosUser list) {
		edCpf.setText(list.getBDCPF());
		txtNomeUser.setText(list.getBDNOMEUSER());
	}
	
	private void preenchePet(MTPet list) {
		edNomePet.setText(list.getBDNOMEPET());
		txtNomeRaca.setText(list.getBDNOMERACA());
	}
	
	public void limpaCampos() {
		edNumEntrada.setText("");
		edCpf.setText("");
		edDataEntrada.setText("");
		edNomePet.setText("");
		txtNomeRaca.setText("");
		txtNomeUser.setText("");
		pnDesc.setText("");
	}
	
	private void acaoConfirma() {
		if(edNumEntrada.getText().isEmpty()) {
			JOptionPane.showInternalMessageDialog(null, "");
		}
		
		FDAOEntrada.setBDIDENTRADA(Integer.valueOf(edNumEntrada.getText()));
		FDAOEntrada.setBDIDPET(1);
		FDAOEntrada.setBDCOMORBIDADE(1);
		FDAOEntrada.setBDDATAENT(edDataEntrada.getDate());
		FDAOEntrada.setBDDESC(pnDesc.getText());
		
		FDAOEntrada.inserir(FDAOEntrada);
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
