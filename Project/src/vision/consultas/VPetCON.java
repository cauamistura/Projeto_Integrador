package vision.consultas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.MTPet;
import model.interfaces.InterPet;
import vision.padrao.RoundButton;
import vision.padrao.TableSimples;

public class VPetCON extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private TableSimples table;
	private RoundButton btnFiltro;
	private JTextField edFiltro;
	private RoundButton btnConf;
	private RoundButton bntExc;

	/**
	 * Create the frame.
	 */
	public VPetCON(List<MTPet> dados, InterPet ev) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 434, 189);
		contentPane.add(scrollPane);

		table = new TableSimples(new Object[][] {}, new String[] { "Id", "Dono(a)", "Raça", "Nome", "Apelido" });
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		atualizarTabela(dados, false);
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		panel.setBounds(1, 190, 434, 34);
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 223, 436, 41);
		contentPane.add(panel_1);
		
		btnConf = new RoundButton("Confirmar");
		panel_1.add(btnConf);
		
		bntExc = new RoundButton("Excluir");
		panel_1.add(bntExc);
		bntExc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = table.getSelectedRows();
				for (int i = 0; i < selectedRows.length; i++) {
					int modelIndex = table.convertRowIndexToModel(selectedRows[i]);
					MTPet dado = dados.get(modelIndex);
					ev.exluiPet(dado.getBDIDPET());
				}
			}
		});
		btnConf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = table.getSelectedRows();
				for (int i = 0; i < selectedRows.length; i++) {
					int id = Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString());
					int modelIndex = table.convertRowIndexToModel(selectedRows[i]);
					MTPet dado = dados.get(modelIndex);
					ev.preencheDadosPet(dado);
					dispose();
				}
			}
		});
		
	}
	
	private String achaApelido(String apelido) {
		if (apelido == null || apelido.length() == 0) {
			return "(Sem apelido)";
		}
		return apelido;
	}
	
	public void desBotoes() {
		btnConf.setVisible(false);
		bntExc.setVisible(false);
	}
	
	public void desExcluir() {
		bntExc.setVisible(false);
	}
	
	public void atualizarTabela(List<MTPet> pets, Boolean prFiltro) {
		table.limparTabela();

	    for (MTPet pet : pets) {
	        if (prFiltro && !pet.getBDNOMEUSER().toLowerCase().contains(edFiltro.getText().toLowerCase())) {
	            continue; 
	        }
	        Object[][] rowData = {{ pet.getBDIDPET(), pet.getBDNOMEUSER(), pet.getBDNOMERACA(), pet.getBDNOMEPET(), achaApelido(pet.getBDAPELIDO()) }};
	        table.preencherTabela(rowData);
	    }
	}

}
