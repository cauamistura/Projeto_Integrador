package vision.consultas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.MTDadosUser;
import vision.VMenu;
import vision.atendimentos.VEntradaATE;
import vision.cadastros.VUserCad;
import vision.padrao.RoundButton;
import vision.padrao.RoundJTextField;

public class VUserCON extends JFrame {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel model;
	private JPanel contentPane;
	private JTable table;
	private JLabel lbFiltro;
	private RoundJTextField edFiltro;
	private RoundButton btnConfirmar;
	private RoundButton btnExcluir;
	private RoundButton btnFiltro;

	public VUserCON(List<MTDadosUser> dados, VUserCad Usercad, VEntradaATE AteEntrada) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		setTitle("Consulta de Usuario");
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);

		edFiltro = new RoundJTextField();
		edFiltro.setColumns(10);

		btnFiltro = new RoundButton("Filtro");

		lbFiltro = new JLabel("Nome: ");

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

		btnConfirmar = new RoundButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = table.getSelectedRows();
				for (int i = 0; i < selectedRows.length; i++) {
					int modelIndex = table.convertRowIndexToModel(selectedRows[i]);
					MTDadosUser dado = dados.get(modelIndex);

					if (dado.getBDCPF().equals(VMenu.FCPFUSER)) {
						Usercad.desabilitaBotoes(false);
					} else {
						Usercad.habilitaBotoes(false);
					}

					Usercad.preencheCampos(dado);
					dispose();
				}
			}
		});

		btnExcluir = new RoundButton("Excluir");
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
					Usercad.exluiUser(dado.getBDIDUSER());
					dispose();
				}
			}
		});

		btnFiltro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (edFiltro.getText().isEmpty()) {
					atualizarTabela(dados, false);
				} else {
					atualizarTabela(dados, true);
				}
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

		addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
		        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		        String classeChamadora = stackTraceElements[3].getClassName();
 
		        if (classeChamadora.equals("VEntradaATE")) {
		            System.out.println(classeChamadora);
		        } else {
		        	System.out.println(classeChamadora);
		        }
			}
		});

	}

	public void desabilitaBotoes() {
		btnConfirmar.setEnabled(false);
		btnConfirmar.setVisible(false);

		btnExcluir.setEnabled(false);
		btnExcluir.setVisible(false);
	}

	public void atualizarTabela(List<MTDadosUser> usuarios, Boolean prFiltro) {
		model.setRowCount(0);

		for (MTDadosUser usuario : usuarios) {
			if (prFiltro && !usuario.getBDNOMEUSER().toLowerCase().contains(edFiltro.getText().toLowerCase())) {
				continue;
			}
			Object[] linha = { usuario.getBDCPF(), usuario.getBDNOMEUSER(), usuario.getBDMAIL() };
			model.addRow(linha);
		}
	}

}
