package vision.atendimentos;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.DAOTDadosUser;
import model.MTDadosUser;
import model.interfaces.InterfaceConsUser;
import vision.cadastros.VUserCad;
import vision.consultas.VUserCON;
import vision.padrao.*;
		
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VEntradaATE extends JFrame implements InterfaceConsUser{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CPFTextField edCpf;
	private RoundJTextField edNomeUser;
	
	//Objetos do usuario
	private VUserCad FVUserCad;
	private VUserCON FVUserCON;
	private DAOTDadosUser FDAOUser;
	

	public VEntradaATE() {
		setTitle("Atendimento de Entrada");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lbUser = new JLabel("Usuario:");
		contentPane.add(lbUser);
		
		edCpf = new CPFTextField();
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
		
		RoundButton btnConUser = new RoundButton("lupa");
		btnConUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chamaConUser();
			}
		});
		contentPane.add(btnConUser);
		
		edNomeUser = new RoundJTextField();
		edNomeUser.setEnabled(false);
		contentPane.add(edNomeUser);
		edNomeUser.setColumns(10);
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
	
	public void preencheUser(MTDadosUser list) {
		edCpf.setText(list.getBDCPF());
		edNomeUser.setText(list.getBDNOMEUSER());
	}

	@Override
	public void preencheUserCad(MTDadosUser listUser) {
		preencheUser(listUser);
		
	}

	@Override
	public void desabilitaBotoes(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void habilitaBotoes(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exluiUser(Integer bdiduser) {
		// TODO Auto-generated method stub
		
	}
}
