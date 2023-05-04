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

import control.*;
import model.*;
import vision.padrao.*;
import model.interfaces.InterComorbidade;

public class VComCon extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public DAOTComorbidade FDAOTComorbidade = new DAOTComorbidade();
	private ArrayList<MTComorbidade> TListComorbidade = new ArrayList<>();
	private JPanel contentPane;
	private TableSimples table;
	private JLabel lbFiltro;
	private RoundJTextField edFiltro;
	private RoundButton btnConfirmar;
	private RoundButton btnExcluir;
	private RoundButton btnFiltro;

	public VComCon(List<MTComorbidade> dados ,InterComorbidade event) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 616, 372);
		setTitle("Consulta de Comorbidade");
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);

		edFiltro = new RoundJTextField();
		edFiltro.setColumns(10);

		lbFiltro = new JLabel("Nome: ");

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new TableSimples(new Object[][] {}, new String[] { "Id", "Comorbidade", "Descrição" });
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		atualizarTabela(dados, false);
		scrollPane.setViewportView(table);
		
		btnConfirmar = new RoundButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = table.getSelectedRows();
				for (int i = 0; i < selectedRows.length; i++) {
					int modelIndex = table.convertRowIndexToModel(selectedRows[i]);	
					MTComorbidade dado = dados.get(modelIndex);
					event.preencheCom(dado);
					dispose();
				}
				table.setCellSelectionEnabled(true);
			}
		});

		btnExcluir = new RoundButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Adicionar evento de exclusão
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
	
	public void atualizarTabela(List<MTComorbidade> com, Boolean prFiltro) {
		table.limparTabela();

		for (MTComorbidade comorbidade: com) {
			if (prFiltro && !comorbidade.getBDNOMECOMORBIDADE().toLowerCase().contains(edFiltro.getText().toLowerCase())) {
				continue;
			}
			Object[][] rowData = {{ comorbidade.getBDIDCOMORBIDADE(), comorbidade.getBDNOMECOMORBIDADE(), comorbidade.getBDDESCCOMORBIDADE() }};
			table.preencherTabela(rowData);	
		}
	}
	
}
