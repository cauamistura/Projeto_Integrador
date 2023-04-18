package vision.consultas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.MTDadosUser;
import vision.VMenu;
import vision.cadastros.VUserCad;

public class VUserCON extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private JButton btnConfirmar;
	private JButton btnExcluir;
	private JTextField edFiltro;
	private JButton btnFiltro;

	public VUserCON(List<MTDadosUser> dados, VUserCad local) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		setTitle("Consulta de Usuario");
		setLocale(null);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);

		edFiltro = new JTextField();
		btnFiltro = new JButton("Filtro");
		
		JPanel filterPanel = new JPanel();
		filterPanel.add(edFiltro);
		filterPanel.add(btnFiltro);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		scrollPane.setViewportView(table);

		model = new DefaultTableModel();
		model.addColumn("CPF");
		model.addColumn("Nome");
		model.addColumn("Email");

		for (MTDadosUser dado : dados) {
			Object[] rowData = { dado.getBDCPF(), dado.getBDNOMEUSER(), dado.getBDMAIL() };
			model.addRow(rowData);
		}

		table.setModel(model);

		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = table.getSelectedRows();
				for (int i = 0; i < selectedRows.length; i++) {
					int modelIndex = table.convertRowIndexToModel(selectedRows[i]);
					MTDadosUser dado = dados.get(modelIndex);

					if (dado.getBDCPF().equals(VMenu.FCPFUSER)) {
						local.desabilitaBotoes(false);
					} else {
						local.habilitaBotoes(false);
					}

					local.preencheCampos(dado);
					dispose();
				}
			}
		});

		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = table.getSelectedRows();
				for (int i = 0; i < selectedRows.length; i++) {
					int modelIndex = table.convertRowIndexToModel(selectedRows[i]);
					MTDadosUser dado = dados.get(modelIndex);
					if (dado.getBDCPF().equals(VMenu.FCPFUSER)) {
						JOptionPane.showInternalMessageDialog(null,
								"Este usuário não pode ser excluido!\nEsta logado no sistema");
						return;
					}
					local.exluiUser(dado.getBDIDUSER());
					dispose();
				}
			}
		});
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(filterPanel);
		buttonsPanel.add(btnConfirmar);
		buttonsPanel.add(btnExcluir);

		contentPane.add(buttonsPanel, BorderLayout.SOUTH);


	}

	public void desabilitaBotoes() {
		btnConfirmar.setEnabled(false);
		btnConfirmar.setVisible(false);

		btnExcluir.setEnabled(false);
		btnExcluir.setVisible(false);
	}
}
