package vision.cadastros;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Menu;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Table;

import control.DAOTMedicacao;
import model.MTClinica;
import model.MTDadosUser;
import model.MTMedicacao;

import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import vision.VMenu;
import vision.padrao.RoundButton;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.file.FileAlreadyExistsException;

import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class VMedCad extends JFrame {

	
	public DAOTMedicacao FDAOTMedicacao = new DAOTMedicacao(); 
	private ArrayList<MTMedicacao> TListMedicacao = new ArrayList<>();
	private VMenu menu = new VMenu();
	private JPanel contentPane;
	private JTextField edNomeMed;
	private JTextField edDescMed;
	private RoundButton btnlimpar;
	private RoundButton btnConf;
	private RoundButton btnDelete;
	private JScrollPane scrollPane;
	private JTable table;
    private DefaultTableModel model;
    private boolean registroCadastro = true;
    private int row;
	private JOptionPane optionPane;
	private  JDialog dialog;
	private Timer timer;
	private JLabel lbStatus;

	public VMedCad() {	
		TListMedicacao = FDAOTMedicacao.ListTMedicacao(FDAOTMedicacao);

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
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		atualizatabela();
		
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				row = table.getSelectedRow();
		        if (row >= 0) {
		        	
		        	
		            // Preencha os campos correspondentes com os dados da linha selecionada
		            String nome = table.getValueAt(row, 1).toString();
		            String desc = table.getValueAt(row, 2).toString();
		            edNomeMed.setText(nome);
		            edDescMed.setText(desc); 
		            
		            registroCadastro = false;
		 
		            FDAOTMedicacao.setBDIDMEDICACAO(Integer.valueOf(table.getValueAt(row, 0).toString())); 
		            
		            lbStatus.setText("Status: Alterando medicamento");
		        }
			}
		});
		scrollPane.setViewportView(table);
		
		

		edNomeMed = new JTextField();
		panel.add(edNomeMed, "cell 0 0,growx");
		edNomeMed.setColumns(10);
		
		edDescMed = new JTextField();
		panel.add(edDescMed, "cell 0 2,growx");
		edDescMed.setColumns(10);
		
		btnConf = new RoundButton("Confirmar");
		btnConf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				if(registroCadastro == true){
					FDAOTMedicacao.setBDIDMEDICACAO(FDAOTMedicacao.getChaveID("tmedicacao", "BDIDMEDICACAO"));
					FDAOTMedicacao.setBDDESCRICAO(edDescMed.getText());
					FDAOTMedicacao.setBDNOMEMEDICACAO(edNomeMed.getText());
					FDAOTMedicacao.inserir(FDAOTMedicacao);
					
					
					atualizatabela();
					//Object[] rowData = { FDAOTMedicacao.getBDIDMEDICACAO(), FDAOTMedicacao.getBDNOMEMEDICACAO(), FDAOTMedicacao.getBDDESCRICAO()};
		            //model.addRow(rowData);
		            
		            limparDados();
				}
				else {
					FDAOTMedicacao.setBDIDMEDICACAO(FDAOTMedicacao.getBDIDMEDICACAO()) ;
					FDAOTMedicacao.setBDDESCRICAO(edDescMed.getText());
					FDAOTMedicacao.setBDNOMEMEDICACAO(edNomeMed.getText());
					FDAOTMedicacao.alterar(FDAOTMedicacao);
	
					atualizatabela();
					/*
					model.setValueAt(FDAOTMedicacao.getBDIDMEDICACAO(), row, 0); // atualiza o valor da célula na linha 0 e coluna 0
					model.setValueAt(FDAOTMedicacao.getBDNOMEMEDICACAO(), row, 1); // atualiza o valor da célula na linha 0 e coluna 1
					model.setValueAt(FDAOTMedicacao.getBDDESCRICAO(), row, 2); // atualiza o valor da célula na linha 0 e coluna 2
					*/
					
					limparDados();
				}
				
			}
		});
		btnConf.setBackground(new Color(255, 255, 255));
		panel.add(btnConf, "flowx,cell 0 3");
		
		btnlimpar = new RoundButton("Limpar");
		btnlimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				limparDados();
			}
		});
		btnlimpar.setBackground(new Color(255, 255, 255));
		panel.add(btnlimpar, "cell 0 3");
		
		btnDelete = new RoundButton("Deletar");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(null,
						"ao Deletar esse medicamento, você não vai mais pode utiliza-lo em nenhuma receita, e todas as receitas ligadas e ele serão perdidass","ATENÇÂO!!",
						JOptionPane.YES_NO_OPTION);
				if(resposta == JOptionPane.YES_NO_OPTION) {
					
					FDAOTMedicacao.deletar(FDAOTMedicacao);
					
					atualizatabela();
					limparDados();
					
					optionPane = new JOptionPane("O medicamento foi Deletada", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
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
				}else {
					optionPane = new JOptionPane("O medicamento não foi Deletada", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
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
		});
		btnDelete.setBackground(new Color(255, 255, 255));
		panel.add(btnDelete, "cell 0 3");
		
		lbStatus = new JLabel("Status: Inserindo medicamento");
		panel.add(lbStatus, "cell 0 5");

	}private void atualizatabela() {
		TListMedicacao = FDAOTMedicacao.ListTMedicacao(FDAOTMedicacao);
		
		DefaultTableModel model = new DefaultTableModel(new Object[][] {}, new String[] { "Id","Medicamento","Descrição"});
		for (MTMedicacao mtMed: TListMedicacao) {
			 Object[] rowData = { mtMed.getBDIDMEDICACAO(), mtMed.getBDNOMEMEDICACAO(), mtMed.getBDDESCRICAO()};
	            model.addRow(rowData);
		}
		table.setModel(model);
		
	}

	public void limparDados() {
		edDescMed.setText("");
		edNomeMed.setText("");
		
		registroCadastro = true;
		
		edNomeMed.requestFocus();
		
		lbStatus.setText("Status: Inserindo medicamento");
	}
	
	
}
