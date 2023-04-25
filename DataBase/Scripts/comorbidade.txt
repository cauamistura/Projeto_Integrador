package vision.cadastros;

import java.awt.BorderLayout; 
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import control.DAOTComorbidade;
import model.MTComorbidade;
import net.miginfocom.swing.MigLayout;
import vision.padrao.RoundButton;
import vision.padrao.TableSimples;

public class VComorbidadeCad extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Obejetos
	public DAOTComorbidade FDAOTComorbidade = new DAOTComorbidade();
	private ArrayList<MTComorbidade> TListComorbidade = new ArrayList<>();

	private boolean registroCadastro = true;
	private int row;
	private JTextField edNomeCom;
	private JTextField edDescCom;
	private JPanel contentPane;
	private TableSimples table;
	private RoundButton btnlimpar;
	private RoundButton btnConf;
	private RoundButton btnDelete;
	private JLabel lbStatus;
	private JLabel lbNome;
	private JLabel lbDescricao;

	/**
	 * Create the frame.
	 */
	public VComorbidadeCad() {
		setTitle("Cadastro de Comorbidade");
		TListComorbidade = FDAOTComorbidade.ListTComorbidade(FDAOTComorbidade);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[grow]", "[][-26.00][][][grow][][-58.00]"));

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, "cell 0 4,grow");

		table = new TableSimples(new Object[][] {}, new String[] { "Id", "Comorbidade", "Descrição" });
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		atualizatabela();

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				row = table.getSelectedRow();
				if (row >= 0) {
					String nome = table.getValueAt(row, 1).toString();
					String desc = table.getValueAt(row, 2).toString();
					edNomeCom.setText(nome);
					edDescCom.setText(desc);

					registroCadastro = false;

					FDAOTComorbidade.setBDIDCOMORBIDADE(Integer.valueOf(table.getValueAt(row, 0).toString()));

					lbStatus.setText("Status: Alterando comorbidade");
				}
			}
		});
		scrollPane.setViewportView(table);

		lbNome = new JLabel("Nome: ");
		panel.add(lbNome, "flowx,cell 0 0");

		edNomeCom = new JTextField();
		panel.add(edNomeCom, "cell 0 0,growx");
		edNomeCom.setColumns(10);

		lbDescricao = new JLabel("Descrição: ");
		panel.add(lbDescricao, "flowx,cell 0 2");

		edDescCom = new JTextField();
		panel.add(edDescCom, "cell 0 2,growx");
		edDescCom.setColumns(10);

		btnConf = new RoundButton("Confirmar");
		btnConf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventConfirmar();
				table.clearSelection();
			}
		});
		panel.add(btnConf, "flowx,cell 0 3");

		btnlimpar = new RoundButton("Limpar");
		btnlimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparDados();
				table.clearSelection();
			}
		});
		panel.add(btnlimpar, "cell 0 3");

		btnDelete = new RoundButton("Deletar");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = FDAOTComorbidade.getBDIDCOMORBIDADE();
				eventExcluir(id);
				table.clearSelection();
			}
		});
		panel.add(btnDelete, "cell 0 3");

		lbStatus = new JLabel("Status: Inserindo comorbidade");
		panel.add(lbStatus, "cell 0 5");
	}

	private void atualizatabela() {
		TListComorbidade = FDAOTComorbidade.ListTComorbidade(FDAOTComorbidade);

		for (MTComorbidade com : TListComorbidade) {
			Object[][] rowData = {{com.getBDIDCOMORBIDADE(), com.getBDNOMECOMORBIDADE(), com.getBDDESCCOMORBIDADE() }};
			((TableSimples) table).preencherTabela(rowData);	
		}	


	}

	private void limparDados() {
		edDescCom.setText("");
		edNomeCom.setText("");

		registroCadastro = true;

		edNomeCom.requestFocus();
		lbStatus.setText("Status: Inserindo medicamento");
	}

	public void eventConfirmar() {
		
		if (edNomeCom.getText() == null || edDescCom.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Campo vazio: Comobirdade", "Atenção", 0);
			edNomeCom.requestFocus();
			return;
		}
		
		FDAOTComorbidade.setBDDESCCOMORBIDADE(edDescCom.getText());
		FDAOTComorbidade.setBDNOMECOMORBIDADE(edNomeCom.getText());

		if (registroCadastro == true) {
			FDAOTComorbidade.setBDIDCOMORBIDADE(FDAOTComorbidade.getChaveID("tcomorbidade", "BDIDCOMORBIDADE"));
			FDAOTComorbidade.inserir(FDAOTComorbidade);
		} else {
			FDAOTComorbidade.setBDIDCOMORBIDADE(FDAOTComorbidade.getBDIDCOMORBIDADE());
			FDAOTComorbidade.alterar(FDAOTComorbidade);
		}
		table.limparTabela();
		atualizatabela();
		limparDados();
	}

	public void eventExcluir(Integer prID) {
		int resposta = JOptionPane.showConfirmDialog(null,
				"Ao Deletar esta comorbidade, você não vai mais pode utiliza-la.",
				"Atenção!", JOptionPane.YES_NO_OPTION);

		if (resposta == JOptionPane.YES_NO_OPTION) {
			FDAOTComorbidade.deletar(prID);

			table.limparTabela();
			atualizatabela();
			limparDados();

			JOptionPane.showInternalMessageDialog(null, "Excluido com sucesso!");
		}
	}

}
