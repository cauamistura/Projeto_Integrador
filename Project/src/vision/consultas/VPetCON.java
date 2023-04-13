package vision.consultas;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import control.DAOTDadosUser;
import control.DAOTRaca;
import model.MTDadosUser;
import model.MTPet;
import model.MTRaca;
import vision.cadastros.VPetCad;
import vision.cadastros.VUserCad;

import javax.swing.JTable;
import javax.swing.JScrollPane;

public class VPetCON extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private DAOTRaca FDAOTRaca = new DAOTRaca();
	public DAOTDadosUser FDAOTDadosUser = new DAOTDadosUser();

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
		scrollPane.setBounds(10, 11, 414, 239);
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
