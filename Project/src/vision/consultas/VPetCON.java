package vision.consultas;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import model.interfaces.InterfaceConsPet;
import vision.cadastros.VPetCad;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import vision.padrao.RoundButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

public class VPetCON extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private DAOTRaca FDAOTRaca = new DAOTRaca();
	public DAOTDadosUser FDAOTDadosUser = new DAOTDadosUser();
	private RoundButton btnConfirmar;
	private RoundButton btnExcluir;
	private RoundButton btnFiltro;
	private JTextField edFiltro;
	private JButton btnConf;
	private JButton bnExc;

	/**
	 * Create the frame.
	 */
	public VPetCON(List<MTPet> dados, InterfaceConsPet event) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 434, 212);
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
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 210, 434, 51);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("Nome");
		panel.add(lblNewLabel);
		
		edFiltro = new JTextField();
		panel.add(edFiltro);
		edFiltro.setColumns(10);
		
		btnFiltro = new RoundButton("Filtrar");
		btnFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (edFiltro.getText().isEmpty()) {
					atualizarTabela(dados, false);
				} else {
					atualizarTabela(dados, true);
				}
			}
		});
		panel.add(btnFiltro);
		
		btnConf = new JButton("Confirmar");
		panel.add(btnConf);
		
		bnExc = new JButton("Excluir");
		panel.add(bnExc);
		
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
	
	public void atualizarTabela(List<MTPet> pets, Boolean prFiltro) {
	    model.setRowCount(0);

	    for (MTPet pet : pets) {
	        if (prFiltro && !achaUser(pet.getBDIDUSER()).toLowerCase().contains(edFiltro.getText().toLowerCase())) {
	            continue; 
	        }
	        Object[] linha = { achaUser(pet.getBDIDUSER()), achaRaca(pet.getBDIDRACA()), pet.getBDNOMEPET(), achaApelido(pet.getBDAPELIDO()) };
	        model.addRow(linha);
	    }
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
