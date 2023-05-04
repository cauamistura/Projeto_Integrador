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
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import control.DAOTMedicacao;
import control.DAOTReceita;
import model.MTMedicacao;
import model.MTReceita;
import model.interfaces.InterReceita;
import net.miginfocom.swing.MigLayout;
import vision.consultas.VMedicamentoCON;
import vision.padrao.DateTextField;
import vision.padrao.PanelComBackgroundImage;
import vision.padrao.RoundButton;
import vision.padrao.lupaButton;

public class VReceitaCad extends JFrame implements InterReceita{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private JLabel lblMedicamento;
	private JLabel lblmedicamento;
	private lupaButton btnLupa;
	private DateTextField edDataInicio;
	private DateTextField edDataFinal;
	private JTextPane textPane;
	private JLabel lblDateInicio;
	private JLabel lblDateFinal;
	private JLabel lbldesc;
	private JButton btnLimpar;
	private JButton btnConf;
	private JButton btnDeletar;
	private JLabel lblNewLabel_5;
	private JLabel title;
	private JLabel lblStatus;
	
	public VReceitaCad() {
		BufferedImage bg = null;
		;
		try {
			bg = ImageIO.read(new File("src/vision/images/BGLogin.png"));

		} catch (IOException e) {
			//TODO Auto-generated catch block
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
		
		title = new JLabel("Cadastro Receita");
		title.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		panel_1.add(title, "cell 0 0,alignx center");
		
		panel_5 = new JPanel();
		panel_5.setBackground(new Color(125, 137, 245));
		panel_1.add(panel_5, "cell 0 2,alignx center");
		panel_5.setLayout(new MigLayout("", "[100px][][][][][][100px]", "[]"));
		
		lblMedicamento= new JLabel("Medicamento");
		lblMedicamento.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		panel_5.add(lblMedicamento, "cell 1 0");
		
		btnLupa = new lupaButton("");
		btnLupa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VMedicamentoCON med = new VMedicamentoCON(FDAOTMedicacao.ListTMedicacao(FDAOTMedicacao), rec);
				med.setVisible(true);
			}
		});
		panel_5.add(btnLupa, "cell 3 0");
		
		lblmedicamento = new JLabel("");
		lblmedicamento.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panel_5.add(lblmedicamento, "cell 5 0");
		
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
		
		lblDateInicio = new JLabel("Data Inicio:");
		lblDateInicio.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panel_3.add(lblDateInicio, "flowy,cell 1 0");
		
		edDataInicio = new DateTextField();
		panel_3.add(edDataInicio, "cell 1 0,growx");
		edDataInicio.setColumns(10);
		
		lblDateFinal = new JLabel("Data Final:");
		lblDateFinal.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panel_3.add(lblDateFinal, "flowy,cell 1 1");
		
		edDataFinal = new DateTextField();
		edDataFinal.setColumns(10);
		panel_3.add(edDataFinal, "cell 1 1,growx");
		
		lblStatus = new JLabel("Status: Aguardando");
		lblStatus.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panel_3.add(lblStatus, "cell 1 3");
		
		lbldesc = new JLabel("Descrição:");
		lbldesc.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panel_3.add(lbldesc, "flowy,cell 1 2");
		
		textPane = new JTextPane();
		panel_3.add(textPane, "cell 1 2,grow");
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(125, 137, 245));
		panel_1.add(panel_2, "cell 0 4,alignx center");
		panel_2.setLayout(new MigLayout("", "[20px][40px][10px][40px][10px][50px][20px]", "[]"));
		
		btnConf = new RoundButton("Confirmar");
		btnConf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventConfirmar();
			}
		});
		panel_2.add(btnConf, "cell 1 0");
		
		btnLimpar = new RoundButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparDados();
			}
		});
		panel_2.add(btnLimpar, "cell 3 0");
		
		btnDeletar= new RoundButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 eventDeletar();
			}
		});
		panel_2.add(btnDeletar, "cell 5 0");
	
	}
	private void eventConfirmar() {
		
		try {
			if (edDataInicio.getDate().isBefore(edDataFinal.getDate()) || edDataFinal.getText().equals(edDataFinal.getText())) {
				FDAOTReceita.setBDIDRECEITA(FDAOTReceita.getChaveID("treceita", "BDIDRECEITA"));
				FDAOTReceita.setBDIDMEDICACAO(2);
				FDAOTReceita.setBDINICIORECEITA(edDataInicio.getDate());
				FDAOTReceita.setBDFINALRECEITA(edDataFinal.getDate());
				FDAOTReceita.setBDDESCRICAO(textPane.getText());
				FDAOTReceita.inserir(FDAOTReceita);
				
			}else {
				JOptionPane.showMessageDialog(null, "Data Inicial menor que a Data final");
	            
	            edDataFinal.setText("");
	            edDataInicio.setText(getName());
			}
				
			
			
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
		} catch (Exception e) {
			
			
			
			JOptionPane.showMessageDialog(null, "Existem itens em Branco!!\n"
					+ "Preencha todos os itens e tente novamente.");
		}
		
		
	
	}
	private void eventDeletar() {
			
		FDAOTReceita.setBDIDRECEITA(FDAOTReceita.getBDIDRECEITA());
		FDAOTReceita.deletar(FDAOTReceita);
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
