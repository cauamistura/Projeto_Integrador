package vision.cadastros;

import java.awt.EventQueue;

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
import vision.padrao.RoundButton;

import javax.swing.JTextField;
import javax.swing.JButton;
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
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class VMedCad extends JFrame {

	
	public DAOTMedicacao FDAOTMedicacao = new DAOTMedicacao(); 
	ArrayList<MTMedicacao> TListMedicacao = new ArrayList<>();
	private JPanel contentPane;
	private JTextField edNomeMed;
	private JTextField edDescMed;
	private RoundButton btnConsultar;
	private RoundButton btnlimpar;
	private RoundButton btnConf;
	private RoundButton btnDelete;
	private RoundButton btnAlterar;
	private JScrollPane scrollPane;
	private JTable table;
    private DefaultTableModel model;
    private String id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VMedCad frame = new VMedCad();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VMedCad() {
		
		TListMedicacao = FDAOTMedicacao.ListTMedicacao(FDAOTMedicacao);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[grow]", "[][][][][][grow]"));
		
		edNomeMed = new JTextField();
		panel.add(edNomeMed, "cell 0 1,growx");
		edNomeMed.setColumns(10);
		
		edDescMed = new JTextField();
		panel.add(edDescMed, "cell 0 3,growx");
		edDescMed.setColumns(10);
		
		btnConf = new RoundButton("Confirmar");
		btnConf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnConf.setBackground(new Color(255, 255, 255));
		panel.add(btnConf, "flowx,cell 0 4");
		
		btnlimpar = new RoundButton("Limpar");
		btnlimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				edDescMed.setText("");
				edNomeMed.setText("");
			}
		});
		btnlimpar.setBackground(new Color(255, 255, 255));
		panel.add(btnlimpar, "cell 0 4");
		
		btnAlterar = new RoundButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAlterar.setBackground(Color.WHITE);
		panel.add(btnAlterar, "cell 0 4");
		
	
		btnConsultar = new RoundButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnConsultar.setBackground(Color.WHITE);
		panel.add(btnConsultar, "cell 0 4");
		
		btnDelete = new RoundButton("Deletar");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDelete.setBackground(new Color(255, 255, 255));
		panel.add(btnDelete, "cell 0 4");
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, "cell 0 5,grow");

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Id");
		model.addColumn("Medicamento");
		model.addColumn("Descrição");

		
		for (MTMedicacao mtMed: TListMedicacao) {
			 Object[] rowData = { mtMed.getBDIDMEDICACAO(), mtMed.getBDNOMEMEDICACAO(), mtMed.getBDDESCRICAO()};
	            model.addRow(rowData);
	    }
		
		table = new JTable(model);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int row = table.getSelectedRow();
		        if (row >= 0) {
		            // Preencha os campos correspondentes com os dados da linha selecionada
		            String nome = table.getValueAt(row, 1).toString();
		            String desc = table.getValueAt(row, 2).toString();
		            id = table.getValueAt(row, 0).toString();
		            edNomeMed.setText(nome);
		            edDescMed.setText(desc);
		            FDAOTMedicacao.setBDIDMEDICACAO(Integer.valueOf(id)) ;
					 
		            
		            
		        }
				
			}
		});
		
		scrollPane.setViewportView(table);

	
	}

}
