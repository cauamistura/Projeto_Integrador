package vision.consultas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import control.DAOTMedicacao;
import model.MTMedicacao;
import model.interfaces.InterMedicamento;
import vision.padrao.RoundButton;
import vision.padrao.RoundJTextField;
import vision.padrao.TableSimples;

public class VMedicamentoCON extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public DAOTMedicacao FDAOTMedicacao = new DAOTMedicacao();
	private ArrayList<MTMedicacao> TListMedicacao = new ArrayList<>();
	private DefaultTableModel model;
	private JPanel contentPane;
	private TableSimples table;
	private JLabel lbFiltro;
	private RoundJTextField edFiltro;
	private RoundButton btnConfirmar;
	private RoundButton btnExcluir;
	private RoundButton btnFiltro;

	public VMedicamentoCON(List<MTMedicacao> dados ,InterMedicamento event) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		setTitle("Consulta de Usuario");
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);

		edFiltro = new RoundJTextField();
		edFiltro.setColumns(10);

		lbFiltro = new JLabel("Nome: ");

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new TableSimples(new Object[][] {}, new String[] { "Id", "Medicamento", "Descrição" });
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);

		TListMedicacao = FDAOTMedicacao.ListTMedicacao(FDAOTMedicacao);
		for (MTMedicacao mtMed : TListMedicacao) {
			Object[][] rowData = {{ mtMed.getBDIDMEDICACAO(), mtMed.getBDNOMEMEDICACAO(), mtMed.getBDDESCRICAO() }};
			table.preencherTabela(rowData);	
		}		

		btnConfirmar = new RoundButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = table.getSelectedRows();
				for (int i = 0; i < selectedRows.length; i++) {
					int modelIndex = table.convertRowIndexToModel(selectedRows[i]);	
					MTMedicacao dado = dados.get(modelIndex);
					event.preencheMedicamento(dado);
					dispose();
				}
				table.setCellSelectionEnabled(true);
			}
		});

		btnExcluir = new RoundButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		btnFiltro = new RoundButton("Filtro");
		btnFiltro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(edFiltro.getText().isEmpty()) {
					atualizarTabela(dados, false);
				}else {
					atualizarTabela(dados, true);	
				}

				
				table.setRowSelectionInterval(0, 0);
					
			}	
			
		});

		JPanel filterPanel = new JPanel();
		filterPanel.add(lbFiltro);
		filterPanel.add(edFiltro);
		filterPanel.add(btnFiltro);

		JPanel botoes = new JPanel();
		botoes.add(btnConfirmar);
		botoes.add(btnExcluir);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(2, 1));
		buttonsPanel.add(filterPanel);
		buttonsPanel.add(botoes);

		contentPane.add(buttonsPanel, BorderLayout.SOUTH);

	}
	public void atualizarTabela(List<MTMedicacao> med, Boolean prFiltro) {
		table.limparTabela();

		for (MTMedicacao medicamento : med) {
			if (prFiltro && !medicamento.getBDNOMEMEDICACAO().toLowerCase().contains(edFiltro.getText().toLowerCase())) {
				continue;
			}
			Object[][] rowData = {{ medicamento.getBDIDMEDICACAO(), medicamento.getBDNOMEMEDICACAO(), medicamento.getBDDESCRICAO() }};
			table.preencherTabela(rowData);	
		}
	}
	
}
