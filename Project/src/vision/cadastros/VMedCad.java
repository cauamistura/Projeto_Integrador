package vision.cadastros;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.DAOTMedicacao;
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

public class VMedCad extends JFrame {

	
	public DAOTMedicacao FDAOTMedicacao = new DAOTMedicacao(); 
	ArrayList<MTMedicacao> TListMedicacao = new ArrayList<>();
	private JPanel contentPane;
	private JTextField edNomeMed;
	private JTextField edDescMed;

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
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[grow]", "[][][][][grow]"));
		
		JLabel lblNomeMed = new JLabel("         Medicamento:");
		panel.add(lblNomeMed, "flowx,cell 0 1,alignx right");
		
		edNomeMed = new JTextField();
		panel.add(edNomeMed, "cell 0 1,growx");
		edNomeMed.setColumns(10);
		
		JLabel lblDesMedicamento = new JLabel("Des. Medicamento: ");
		panel.add(lblDesMedicamento, "flowx,cell 0 2");
		
		edDescMed = new JTextField();
		panel.add(edDescMed, "cell 0 2,growx");
		edDescMed.setColumns(10);
		
		RoundButton btnConf = new RoundButton("Confirmar");
		btnConf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FDAOTMedicacao.setBDIDMEDICACAO(FDAOTMedicacao.getChaveID("tmedicacao", "BDIDMEDICACAO"));
				FDAOTMedicacao.setBDNOMEMEDICACAO(edNomeMed.getText());
				FDAOTMedicacao.setBDDESCRICAO(edDescMed.getText());
				
				if() {
					FDAOTMedicacao.inserir(FDAOTMedicacao);
				}
				else {
					FDAOTMedicacao.alterar(FDAOTMedicacao);
				}
			
			}
		});
		btnConf.setBackground(new Color(255, 255, 255));
		panel.add(btnConf, "flowx,cell 0 4");
		
		RoundButton btnDelete = new RoundButton("Deltetar");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDelete.setText("Deletar");
		btnDelete.setBackground(new Color(255, 255, 255));
		panel.add(btnDelete, "cell 0 4");
		
		RoundButton btnlimpar = new RoundButton("Limpar");
		btnlimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnlimpar.setBackground(new Color(255, 255, 255));
		panel.add(btnlimpar, "cell 0 4");
		
		RoundButton btnConsultar = new RoundButton("Consultar");
		panel.add(btnlimpar, "cell 0 4");
		
		RoundButton btnCons = new RoundButton("Limpar");
		btnCons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCons.setText("Consultar");
		btnCons.setBackground(Color.WHITE);
		panel.add(btnCons, "cell 0 4");
		
	}

}
