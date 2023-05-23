package vision.cadastros;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import model.Agendamento;
import vision.padrao.DateTextField;
import vision.padrao.TableSimples;

public class AgendamentoCAD extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DateTextField textField;
	private TableSimples table;

	public AgendamentoCAD() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 627, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new DateTextField();
		textField.setBounds(174, 10, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		table = new TableSimples(new Object[][] {}, new String[] { "Id", "Comorbidade", "Descrição" });
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	public void atualizarTabela(List<Agendamento> age) {
		table.limparTabela();

		for (Agendamento agendamento : age) {
			Object[][] rowData = {{ agendamento.getId(), agendamento.getDateAgendamento(), agendamento.getHora() }};
			table.preencherTabela(rowData);
		}
			
	}
}
