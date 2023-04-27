package vision.cadastros;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import control.DAOTMedicacao;
import control.DAOTReceita;
import model.MTMedicacao;
import model.MTReceita;
import model.interfaces.InterfaceConsMed;
import vision.consultas.VMedCON;
import vision.padrao.DateTextField;
import vision.padrao.RoundButton;
import vision.padrao.lupaButton;

public class VReceitaCad extends JFrame implements InterfaceConsMed{

	
	private DAOTReceita FDAOTReceita = new DAOTReceita();
	private DAOTMedicacao FDAOTMedicacao = new DAOTMedicacao();
	private ArrayList<MTReceita> TListReceita = new ArrayList<>();
	private JPanel contentPane;
	private DateTextField edDataFinal;
	private DateTextField edDataInicio;
	private RoundButton btnLimpar;
	private RoundButton btnConf;
	private RoundButton btnPesqMed;
	private RoundButton btnDelete;
	private JTextPane textPane;
	private JLabel lblStatus;
	private JLabel lblmedicamento;
	private Integer idmedicamento;
	private boolean mudaReceita;
	private JOptionPane optionPane;
	private  JDialog dialog;
	private Timer timer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VReceitaCad frame = new VReceitaCad();
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
	public VReceitaCad() {
		
		VReceitaCad rec = this;
			
		setTitle("Cadastro de Receita");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		edDataFinal = new DateTextField();
		edDataFinal.setBounds(109, 75, 114, 19);
		contentPane.add(edDataFinal);
		edDataFinal.setColumns(10);
		
		btnDelete = new RoundButton("Deletar");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventDeletar();
				limparDados();
			}
		});
		btnDelete.setBounds(49, 143, 117, 25);
		contentPane.add(btnDelete);
		
		textPane = new JTextPane();
		textPane.setBounds(243, 69, 183, 119);
		contentPane.add(textPane);
		
		edDataInicio = new DateTextField();
		edDataInicio.setBounds(109, 44, 114, 19);
		contentPane.add(edDataInicio);
		edDataInicio.setColumns(10);
	
		JLabel lblMedicamento = new JLabel("Medicamento");
		lblMedicamento.setBounds(24, 15, 70, 15);
		contentPane.add(lblMedicamento);
		
		JLabel lblDataInicio = new JLabel("Data Inicio");
		lblDataInicio.setBounds(21, 46, 70, 15);
		contentPane.add(lblDataInicio);
		
		JLabel lblDataFinal = new JLabel("Data Final");
		lblDataFinal.setBounds(21, 75, 70, 15);
		contentPane.add(lblDataFinal);
		
		lblStatus = new JLabel("Status: Inserindo Receita");
		lblStatus.setBounds(34, 236, 211, 15);
		contentPane.add(lblStatus);
		
		JLabel lblDesc = new JLabel("Descrição: ");
		lblDesc.setBounds(245, 46, 125, 15);
		contentPane.add(lblDesc);
		
		btnLimpar = new RoundButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparDados();
			}
		});
		btnLimpar.setBounds(49, 180, 117, 25);
		contentPane.add(btnLimpar);
		
		btnConf = new RoundButton("Confirmar");
		btnConf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventConfirmar();
				limparDados();
			}	
		});
		btnConf.setBounds(49, 106, 117, 25);
		contentPane.add(btnConf);
		
		btnPesqMed = new lupaButton("");
		btnPesqMed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abreConsulta(rec);
			}
		});
		btnPesqMed.setBounds(106, 10, 45, 25);
		contentPane.add(btnPesqMed);
		
		lblmedicamento = new JLabel("");
		lblmedicamento.setBounds(169, 15, 192, 15);
		contentPane.add(lblmedicamento);
	
	}
	private void eventConfirmar() {
		
		if (edDataInicio.getDate().isBefore(edDataFinal.getDate())) {
			FDAOTReceita.setBDIDRECEITA(FDAOTReceita.getChaveID("treceita", "BDIDRECEITA"));
			FDAOTReceita.setBDIDMEDICACAO(2);
			FDAOTReceita.setBDINICIORECEITA(edDataInicio.getDate());
			FDAOTReceita.setBDFINALRECEITA(edDataFinal.getDate());
			FDAOTReceita.setBDDESCRICAO(textPane.getText());
			
		}else {
			JOptionPane.showMessageDialog(null, "Data Errada seu otario");
            
            edDataFinal.setText("");
            edDataInicio.setText(getName());
		}
			
		FDAOTReceita.inserir(FDAOTReceita);
		
		optionPane = new JOptionPane("Salvo com sucesso", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
        dialog = optionPane.createDialog("");

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        timer.setRepeats(false);
        timer.start();

        dialog.setVisible(true);
	
	}
	private void eventDeletar() {
			
		FDAOTReceita.setBDIDRECEITA(FDAOTReceita.getBDIDRECEITA());
		FDAOTReceita.deletar(FDAOTReceita);
	}
	private void abreConsulta(VReceitaCad rec) {
		if (FDAOTReceita != null) {
			VMedCON frame = new VMedCON(FDAOTMedicacao.ListTMedicacao(FDAOTMedicacao), rec);
			frame.setVisible(true);
		} else {

		}
	}
	public void med(MTMedicacao med) {
		
		idmedicamento = med.getBDIDMEDICACAO();
		lblmedicamento.setText(med.getBDNOMEMEDICACAO());
		textPane.setText("\nDescrição do medicamento:\n - "+med.getBDDESCRICAO());
		
	}
	
	public void limparDados() {
		edDataFinal.setText("");
		edDataInicio.setText("");

		textPane.setText("");

		FDAOTReceita.setBDIDRECEITA(null);
		lblmedicamento.setText("");
		lblStatus.setText("Status: Inserindo Receita");
	}

	@Override
	public void preencheMedicamento(MTMedicacao dados) {
		med(dados);
		

	}
	
	


}
