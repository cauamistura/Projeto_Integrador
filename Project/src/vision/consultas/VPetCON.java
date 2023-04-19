package vision.consultas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import control.DAOTDadosUser;
import control.DAOTRaca;
import model.MTDadosUser;
import model.MTPet;
import model.MTRaca;
import vision.VMenu;
import vision.cadastros.VPetCad;
import vision.cadastros.VUserCad;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import vision.padrao.RoundButton;

public class VPetCON extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private DAOTRaca FDAOTRaca = new DAOTRaca();
	public DAOTDadosUser FDAOTDadosUser = new DAOTDadosUser();
	private RoundButton btnConfirmar;
	private RoundButton btnExcluir;

	/**
	 * Create the frame.
	 */
	public VPetCON(List<MTPet> dados, VPetCad local) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 215);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		model = new DefaultTableModel();
		model.addColumn("Dono(a)");
		model.addColumn("Raça");
		model.addColumn("Nome");
		model.addColumn("Apelido");

		for (MTPet dado : dados) {
			Object[] rowData = { achaUser(dado.getBDIDUSER()), achaRaca(dado.getBDIDRACA()), dado.getBDNOMEPET(), achaApelido(dado.getBDAPELIDO()) };
			model.addRow(rowData);
		}

		table.setModel(model);
		
		btnConfirmar = new RoundButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		btnExcluir = new RoundButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		JPanel botoes = new JPanel();
		botoes.setBounds(-27, 226, 484, 33);
		contentPane.add(botoes);
		
		RoundButton btnConfirmar = new RoundButton("Confirmar");
		botoes.add(btnConfirmar);
		
		RoundButton btnExcluir = new RoundButton("Excluir");
		botoes.add(btnExcluir);
	}

	private String achaRaca(Integer id) {
		String raca = "";

		ArrayList<MTRaca> TListRaca = new ArrayList<>();
		TListRaca = FDAOTRaca.ListTRaca(FDAOTRaca, 0);

		for (MTRaca mtRaca : TListRaca) {

			if (mtRaca.getBDIDRACA().equals(id)) {
				raca = mtRaca.getBDNOMERACA();

			}

		}

		return raca;
	}

	
	
	private String achaApelido(String apelido) {

		if (apelido == null || apelido.length() == 0) {
			return "(Sem apelido)";
		}

		return apelido;

	}
	
	private String achaUser(int userId) {
		String userName = "";
		ArrayList<MTDadosUser> TListUser = new ArrayList<>();
		TListUser = FDAOTDadosUser.ListTDadosUser(FDAOTDadosUser);

		for (MTDadosUser mtUser : TListUser) {

			if (mtUser.getBDIDUSER().equals(userId)) {
				userName = mtUser.getBDNOMEUSER();
			}

		}

		return userName;
	}
}
