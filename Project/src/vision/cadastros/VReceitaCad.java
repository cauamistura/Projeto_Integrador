package vision.cadastros;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import control.DAOTMedicacao;
import control.DAOTReceita;
import model.MTMedicacao;
import model.MTReceita;
import model.interfaces.InterfaceConsMed;
import net.miginfocom.swing.MigLayout;
import vision.padrao.PanelComBackgroundImage;
import vision.padrao.RoundButton;
import vision.padrao.RoundJTextField;
import vision.padrao.lupaButton;

public class VReceitaCad extends JFrame implements InterMedicamento{

	
	private DAOTReceita FDAOTReceita = new DAOTReceita();
	private DAOTMedicacao FDAOTMedicacao = new DAOTMedicacao();
	private ArrayList<MTReceita> TListReceita = new ArrayList<>();
	private JPanel contentPane;
	private Integer idmedicamento;
	private boolean mudaReceita;
	private JOptionPane optionPane;
	private  JDialog dialog;
	private Timer timer;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private lupaButton btnNewButton;
	private JTextField textField;
	private JTextField textField_1;
	private JTextPane textPane;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VReceitaCad frame = new VReceitaCad();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public VReceitaCad() {
		BufferedImage bg = null;
		;
		try {
			bg = ImageIO.read(new File("src/vision/images/BGLogin.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		VReceitaCad rec = this;
			
		setTitle("Cadastro de Receita");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 580, 810);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setBackground(new Color(158, 174, 255));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[80px][350px,grow][80px]", "[100px][600px,grow][100px]"));
		
		panel_1 = new PanelComBackgroundImage(bg);
		panel_1.setBackground(new Color(158, 174, 255));
		panel.add(panel_1, "cell 1 1,alignx center");
		panel_1.setLayout(new MigLayout("", "[grow]", "[][80px,grow][20px,grow][300px,grow][50px,grow]"));
		
		lblNewLabel_6 = new JLabel("Cadastro Receita");
		lblNewLabel_6.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		panel_1.add(lblNewLabel_6, "cell 0 0,alignx center");
		
		panel_5 = new JPanel();
		panel_5.setBackground(new Color(125, 137, 245));
		panel_1.add(panel_5, "cell 0 2,alignx center");
		panel_5.setLayout(new MigLayout("", "[100px][][][][][][100px]", "[]"));
		
		lblNewLabel = new JLabel("Medicamento");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		panel_5.add(lblNewLabel, "cell 1 0");
		
		btnNewButton = new lupaButton("");
		panel_5.add(btnNewButton, "cell 3 0");
		
		lblNewLabel_1 = new JLabel("Paracetamol");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panel_5.add(lblNewLabel_1, "cell 5 0");
		
		panel_4 = new JPanel();
		panel_4.setBackground(new Color(125, 137, 245));
		panel_1.add(panel_4, "cell 0 1,alignx center,growy");
		panel_4.setLayout(new MigLayout("", "[100px][][100px]", "[]"));
		
		lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(VReceitaCad.class.getResource("/vision/images/medicine.png")));
		panel_4.add(lblNewLabel_5, "cell 1 0");
		
		panel_3 = new JPanel();
		panel_3.setBackground(new Color(125, 137, 245));
		panel_1.add(panel_3, "cell 0 3,alignx center");
		panel_3.setLayout(new MigLayout("", "[60px][280px,grow][60px]", "[][][100px,grow][]"));
		
		lblNewLabel_2 = new JLabel("Data Inicio:");
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panel_3.add(lblNewLabel_2, "flowy,cell 1 0");
		
		textField = new RoundJTextField();
		panel_3.add(textField, "cell 1 0,growx");
		textField.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Data Final:");
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panel_3.add(lblNewLabel_3, "flowy,cell 1 1");
		
		textField_1 = new RoundJTextField();
		textField_1.setColumns(10);
		panel_3.add(textField_1, "cell 1 1,growx");
		
		lblNewLabel_7 = new JLabel("Statu: Insirindo Receita");
		lblNewLabel_7.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panel_3.add(lblNewLabel_7, "cell 1 3");
		
		lblNewLabel_4 = new JLabel("Descrição:");
		lblNewLabel_4.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panel_3.add(lblNewLabel_4, "flowy,cell 1 2");
		
		textPane = new JTextPane();
		panel_3.add(textPane, "cell 1 2,grow");
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(125, 137, 245));
		panel_1.add(panel_2, "cell 0 4,alignx center");
		panel_2.setLayout(new MigLayout("", "[20px][40px][10px][40px][10px][50px][20px]", "[]"));
		
		btnNewButton_2 = new RoundButton("Comfirmar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_2.add(btnNewButton_2, "cell 1 0");
		
		btnNewButton_1 = new RoundButton("Limpar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_2.add(btnNewButton_1, "cell 3 0");
		
		btnNewButton_3 = new RoundButton("Deletar");
		panel_2.add(btnNewButton_3, "cell 5 0");
	
	}
	private void eventConfirmar() {
		
//		if (edDataInicio.getDate().isBefore(edDataFinal.getDate())) {
//			FDAOTReceita.setBDIDRECEITA(FDAOTReceita.getChaveID("treceita", "BDIDRECEITA"));
//			FDAOTReceita.setBDIDMEDICACAO(2);
//			FDAOTReceita.setBDINICIORECEITA(edDataInicio.getDate());
//			FDAOTReceita.setBDFINALRECEITA(edDataFinal.getDate());
//			FDAOTReceita.setBDDESCRICAO(textPane.getText());
//			
//		}else {
//			JOptionPane.showMessageDialog(null, "Data Errada seu otario");
//            
//            edDataFinal.setText("");
//            edDataInicio.setText(getName());
//		}
			
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
	public void med(MTMedicacao med) {
		
//		idmedicamento = med.getBDIDMEDICACAO();
//		lblmedicamento.setText(med.getBDNOMEMEDICACAO());
//		textPane.setText("\nDescrição do medicamento:\n - "+med.getBDDESCRICAO());
		
	}
	
//	public void limparDados() {
//		edDataFinal.setText("");
//		edDataInicio.setText("");
//
//		textPane.setText("");
//
//		FDAOTReceita.setBDIDRECEITA(null);
//		lblmedicamento.setText("");
//		lblStatus.setText("Status: Inserindo Receita");
//	}

	@Override
	public void preencheMedicamento(MTMedicacao dados) {
		med(dados);
		

	}
	
	


}
