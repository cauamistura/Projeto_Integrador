package vision.consultas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.MTPet;
import model.interfaces.InterfaceConsPet;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import vision.padrao.RoundButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

public class VPetCON extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private RoundButton btnFiltro;
	private JTextField edFiltro;
	private JButton btnConf;
	private JButton bnExc;

	/**
	 * Create the frame.
	 */
	public VPetCON(List<MTPet> dados, InterfaceConsPet ev) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setLocationRelativeTo(null);
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
			Object[] rowData = {dado.getBDNOMEUSER(), dado.getBDNOMERACA(), dado.getBDNOMEPET(), achaApelido(dado.getBDAPELIDO()) };
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
		btnConf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = table.getSelectedRows();
				for (int i = 0; i < selectedRows.length; i++) {
					int modelIndex = table.convertRowIndexToModel(selectedRows[i]);
					MTPet dado = dados.get(modelIndex);
					ev.preencheDadosPet(dado);
					dispose();
				}
			}
		});
		panel.add(btnConf);
		
		bnExc = new JButton("Excluir");
		bnExc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = table.getSelectedRows();
				for (int i = 0; i < selectedRows.length; i++) {
					int modelIndex = table.convertRowIndexToModel(selectedRows[i]);
					MTPet dado = dados.get(modelIndex);
					ev.exluiPet(dado.getBDIDPET());
					dispose();
				}
			}
		});
		panel.add(bnExc);
		
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
	        if (prFiltro && !pet.getBDNOMEUSER().toLowerCase().contains(edFiltro.getText().toLowerCase())) {
	            continue; 
	        }
	        Object[] linha = { pet.getBDNOMEUSER(), pet.getBDNOMERACA(), pet.getBDNOMEPET(), achaApelido(pet.getBDAPELIDO()) };
	        model.addRow(linha);
	    }
	}
	
	public void desabilitaExcluir() {
		bnExc.setVisible(false);
	}
}
