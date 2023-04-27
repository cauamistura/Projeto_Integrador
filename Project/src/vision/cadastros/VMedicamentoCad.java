package vision.cadastros;

import java.awt.BorderLayout; 
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import control.DAOTMedicacao;
import model.MTMedicacao;
import model.interfaces.InterfaceConsMed;
import net.miginfocom.swing.MigLayout;
import vision.padrao.RoundButton;
import vision.padrao.TableSimples;
import javax.swing.JButton;

public class VMedicamentoCad extends JFrame {
	
	private static final long serialVersionUID = 1L;
	public DAOTMedicacao FDAOTMedicacao = new DAOTMedicacao();
	private ArrayList<MTMedicacao> TListMedicacao = new ArrayList<>();
	
	private JPanel contentPane;
	private JTextField edNomeMed;
	private JTextField edDescMed;
	private RoundButton btnlimpar;
	private RoundButton btnConf;
	private RoundButton btnDelete;
	private TableSimples table;
	private boolean registroCadastro = true;
	private int row;
	private JOptionPane optionPane;
	private JDialog dialog;
	private Timer timer;
	private JLabel lbStatus;
	private JLabel lbNome;
	private JLabel lbDesc;
	private RoundButton btnReceita;
	private String nome;
	private String desc;
	private Integer id;
	

	public VMedicamentoCad(Boolean consulta, InterfaceConsMed event) {
				

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
					if (consulta == true){
						btnDelete.setVisible(false);
						btnlimpar.setVisible(false);
					}else {
						
					}
			}
		});
		setBounds(100, 100, 848, 524);
		TListMedicacao = FDAOTMedicacao.ListTMedicacao(FDAOTMedicacao);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setTitle("Cadastro de Medicamento");
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[grow]", "[][-26.00][][][grow][][-58.00]"));

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, "cell 0 4,grow");

		table = new TableSimples(new Object[][] {}, new String[] { "Id", "Medicamento", "Descrição" });
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		atualizatabela();

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				row = table.getSelectedRow();
				if (row >= 0) {

					// Preencha os campos correspondentes com os dados da linha selecionada
					nome = table.getValueAt(row, 1).toString();
					desc = table.getValueAt(row, 2).toString();
					id = Integer.valueOf(table.getValueAt(row, 0).toString());
					edNomeMed.setText(nome);
					edDescMed.setText(desc);

					registroCadastro = false;

					FDAOTMedicacao.setBDIDMEDICACAO(id);
					
					lbStatus.setText("Status: Alterando medicamento");
					
					
				}
			}
		});
		scrollPane.setViewportView(table);
		
		lbNome = new JLabel("Nome: ");
		panel.add(lbNome, "flowx,cell 0 0");

		edNomeMed = new JTextField();
		panel.add(edNomeMed, "cell 0 0,growx");
		edNomeMed.setColumns(10);
		
		lbDesc = new JLabel("Descrição: ");
		panel.add(lbDesc, "flowx,cell 0 2");

		edDescMed = new JTextField();
		panel.add(edDescMed, "cell 0 2,growx");
		edDescMed.setColumns(10);

		btnConf = new RoundButton("Confirmar");
		btnConf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.clearSelection();
				if(consulta == true) {
					event.preencheMedicamento(id,nome,desc);
					dispose();
				}else {
					eventConfirmar();
				}
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
				eventDeletar();
				table.clearSelection();
			}
		});
		panel.add(btnDelete, "cell 0 3");

		lbStatus = new JLabel("Status: Inserindo medicamento");
		panel.add(lbStatus, "cell 0 5");
	
	}

	private void atualizatabela(){
		TListMedicacao = FDAOTMedicacao.ListTMedicacao(FDAOTMedicacao);
		
		for (MTMedicacao mtMed : TListMedicacao) {
			Object[][] rowData = {{ mtMed.getBDIDMEDICACAO(), mtMed.getBDNOMEMEDICACAO(), mtMed.getBDDESCRICAO() }};
			table.preencherTabela(rowData);	
		}		
	}

	public void limparDados() {
		edDescMed.setText("");
		edNomeMed.setText("");

		registroCadastro = true;

		edNomeMed.requestFocus();

		lbStatus.setText("Status: Inserindo medicamento");
	}
	
	public void eventConfirmar() {
		
		if (edNomeMed.getText() == null || edDescMed.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Campo vazio: Medicamento", "Atenção", 0);
			edNomeMed.requestFocus();
			return;
		}
		
		FDAOTMedicacao.setBDDESCRICAO(edDescMed.getText());
		FDAOTMedicacao.setBDNOMEMEDICACAO(edNomeMed.getText());
		
		if (registroCadastro == true) {
			FDAOTMedicacao.setBDIDMEDICACAO(FDAOTMedicacao.getChaveID("tmedicacao", "BDIDMEDICACAO"));
			FDAOTMedicacao.inserir(FDAOTMedicacao);
			
		} else {
			FDAOTMedicacao.setBDIDMEDICACAO(FDAOTMedicacao.getBDIDMEDICACAO());
			FDAOTMedicacao.alterar(FDAOTMedicacao);
		}
	
		table.limparTabela();
		atualizatabela();
		limparDados();
	}
	
	public void eventDeletar() {
		int resposta = JOptionPane.showConfirmDialog(null,
				"Ao Deletar este medicamento, você não vai mais pode utiliza-lo.",
				"Atenção!", JOptionPane.YES_NO_OPTION);
		if (resposta == JOptionPane.YES_NO_OPTION) {

			FDAOTMedicacao.deletar(FDAOTMedicacao);
			table.limparTabela();
			atualizatabela();
			limparDados();

			optionPane = new JOptionPane("O medicamento foi Deletada", JOptionPane.INFORMATION_MESSAGE,
					JOptionPane.DEFAULT_OPTION, null, new Object[] {}, null);
			dialog = optionPane.createDialog("");

			timer = new Timer(800, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dialog.dispose();
				}
			});
			timer.setRepeats(false);
			timer.start();

			dialog.setVisible(true);
		} else {
			optionPane = new JOptionPane("O medicamento não foi Deletada", JOptionPane.INFORMATION_MESSAGE,
					JOptionPane.DEFAULT_OPTION, null, new Object[] {}, null);
			dialog = optionPane.createDialog("");

			timer = new Timer(800, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dialog.dispose();
				}
			});
			timer.setRepeats(false);
			timer.start();

			dialog.setVisible(true);

		}

	}
}
