package vision.cadastros;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.DAOTReceita;
import model.MTReceita;
import model.interfaces.InterfaceConsMed;
import vision.padrao.DateTextField;
import vision.padrao.RoundButton;
import vision.padrao.lupaButton;

import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class VReceitaCad extends JFrame implements InterfaceConsMed{

	
	private DAOTReceita FDAOTReceita = new DAOTReceita();
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
		btnLimpar.setBounds(49, 180, 117, 25);
		contentPane.add(btnLimpar);
		
		btnConf = new RoundButton("Confirmar");
		btnConf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventConfirmar();
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
	private void eventDeletar() {
			
		FDAOTReceita.setBDIDRECEITA(FDAOTReceita.getBDIDRECEITA());
		FDAOTReceita.deletar(FDAOTReceita);
	}
	private void abreConsulta(VReceitaCad rec) {
		if (FDAOTReceita != null) {
			mudaReceita = true;
			VMedicamentoCad frame = new VMedicamentoCad(mudaReceita,this);
			frame.setVisible(true);
		} else {

		}
	}
	public void med(Integer id, String nomeMed,String desc) {
		
		idmedicamento = id;
		lblmedicamento.setText(nomeMed);
		textPane.setText("\nDescrição do medicamento:\n - "+desc);
		
	}

	@Override
	public void preencheMedicamento(Integer id, String nomeMed, String desc) {
		med(id,nomeMed,desc);
	
	}

}
