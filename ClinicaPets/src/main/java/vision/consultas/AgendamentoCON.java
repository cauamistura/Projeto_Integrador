package vision.consultas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import control.DAOAgendamento;
import control.DAODadosUser;
import model.Agendamento;
import model.DadosUser;
import model.interfaces.InterAgendamento;
import vision.padrao.DateTextField;
import vision.padrao.RoundButton;
import vision.padrao.TableSimples;

public class AgendamentoCON extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public DAOAgendamento FDAOTAgendamento = new DAOAgendamento();
	ArrayList<Agendamento> dados;
	private JPanel contentPane;
	private TableSimples table;
	private JLabel lbFiltro;
	private DateTextField edData;
	private RoundButton btnConfirmar;
	private RoundButton btnExcluir;
	private RoundButton btnFiltro;
	
	
	private DAODadosUser user = new DAODadosUser();
	private ArrayList<DadosUser> listUser = user.ListTDadosUser(user);
	
	public AgendamentoCON(InterAgendamento event) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 616, 372);
		setTitle("Consulta de Agendamento");
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);

		edData = new DateTextField();
		edData.setColumns(10);

		lbFiltro = new JLabel("Data:");

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new TableSimples(new Object[][] {}, new String[] { "Número", "Dono", "Pet", "Data", "Hora" });
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollPane.setViewportView(table);
		
		btnConfirmar = new RoundButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = table.getSelectedRows();
				for (int i = 0; i < selectedRows.length; i++) {
					int modelIndex = table.convertRowIndexToModel(selectedRows[i]);	
					Agendamento dado = dados.get(modelIndex);
					event.preencheAge(dado);
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
				if(!edData.validaDate()) {
					JOptionPane.showMessageDialog(null, "Informe uma data valida!");
					edData.requestFocus();
					return;
				}
				
				dados = FDAOTAgendamento.ListCon(edData.getDate());
				atualizarTabela(dados);
			
				table.setRowSelectionInterval(0, 0);
			}	
			
		});

		JPanel filterPanel = new JPanel();
		filterPanel.add(lbFiltro);
		filterPanel.add(edData);
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
	
	public void atualizarTabela(List<Agendamento> com) {
		table.limparTabela();
		DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		for (Agendamento agendamento: com) {			
			Object[][] rowData = {{ agendamento.getId(), getUser(agendamento.getBDIDUSER()), agendamento.getBDNOMEPET(), agendamento.getDateAgendamento().format(FOMATTER), agendamento.getHora() }};
			table.preencherTabela(rowData);	
		}
	}
	
	private String getUser(Integer prId) {
		for (DadosUser user : listUser) {
			if(user.getBDIDUSER() == prId) {
				return user.getBDNOMEUSER();
			}
		}
		return "Não encontrado";
	}
	
}
