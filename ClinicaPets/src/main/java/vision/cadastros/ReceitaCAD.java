package vision.cadastros;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
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

import control.DAOAtendimentoSaida;
import control.DAOMedicacao;
import control.DAOReceita;
import model.Medicamento;
import model.Receita;
import model.interfaces.InterMedicamento;
import model.interfaces.InterReceita;
import net.miginfocom.swing.MigLayout;
import vision.consultas.MedicamentoCON;
import vision.padrao.DateTextField;
import vision.padrao.PanelComBackgroundImage;
import vision.padrao.RoundButton;
import vision.padrao.lupaButton;

public class ReceitaCAD extends JFrame implements InterMedicamento{

	
	private static final long serialVersionUID = 1L;
	private DAOReceita FDAOTReceita = new DAOReceita();
	private DAOMedicacao FDAOTMedicacao = new DAOMedicacao();
	private ArrayList<Receita> TListReceita = new ArrayList<>();
	private ArrayList<Medicamento> TListMedicamento = new ArrayList<>();
	private DAOAtendimentoSaida FDAOSaida = new DAOAtendimentoSaida();
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
	private JLabel lblNomemedicamento;
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
	
	public ReceitaCAD(InterReceita event,  DAOReceita receita) {
		
		if (receita.getBDIDRECEITA() != null) {
			addComponentListener(new ComponentAdapter() {
				@Override
				public void componentShown(ComponentEvent e) {
					DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("ddMMyyyy");
						edDataFinal.setText(receita.getBDFINALRECEITA().format(FOMATTER));
						edDataInicio.setText(receita.getBDINICIORECEITA().format(FOMATTER));
						FDAOTReceita.setBDIDMEDICACAO(receita.getBDIDMEDICACAO());
						lblNomemedicamento.setText(receita.getBDNOMEMEDICACAO());
						textPane.setText(receita.getBDDESCRICAO());
						
						lblStatus.setText("Status: Alterando uma Receita");
				}
			});
		}

		BufferedImage bg = null;
		;
		try {
			bg = ImageIO.read(new File("src\\main\\resources\\BGLogin.png"));

		} catch (IOException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ReceitaCAD rec = this;
			
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
				MedicamentoCON med = new MedicamentoCON(FDAOTMedicacao.ListTMedicacao(FDAOTMedicacao), rec);
				med.setVisible(true);
			}
		});
		panel_5.add(btnLupa, "cell 3 0");
		
		lblNomemedicamento = new JLabel("");
		lblNomemedicamento.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panel_5.add(lblNomemedicamento, "cell 5 0");
		
		panel_4 = new JPanel();
		panel_4.setBackground(new Color(125, 137, 245));
		panel_1.add(panel_4, "cell 0 1,alignx center,growy");
		panel_4.setLayout(new MigLayout("", "[100px][][100px]", "[]"));
		
		lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon("src\\main\\resources\\medicine.png"));
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
		
		lblStatus = new JLabel("Status: Inserindo uma Receita");
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
				event.preecherReceita(FDAOTReceita);
				dispose();
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
		
	
	}
	private void eventConfirmar() {
		
		try {
			if (edDataInicio.getDate().isBefore(edDataFinal.getDate()) || edDataFinal.getText().equals(edDataFinal.getText())) {
				
				FDAOTReceita.setBDIDRECEITA(FDAOTReceita.getChaveID("treceita", "BDIDRECEITA"));
				FDAOTReceita.setBDIDMEDICACAO(idmedicamento);
				FDAOTReceita.setBDINICIORECEITA(edDataInicio.getDate());
				FDAOTReceita.setBDFINALRECEITA(edDataFinal.getDate());
				FDAOTReceita.setBDDESCRICAO(textPane.getText());
				FDAOTReceita.setBDNOMEMEDICACAO(FDAOTMedicacao.getBDNOMEMEDICACAO());
				
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
				
			}else {
				JOptionPane.showMessageDialog(null, "Data Inicial menor que a Data final");
	            edDataFinal.setText("");
			}
				
			
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Existem itens em Branco!!\n"
					+ "Preencha todos os itens e tente novamente.");
		}
		
	
	}
	
	public void med(Medicamento med) {
		
		idmedicamento = med.getBDIDMEDICACAO();
		lblNomemedicamento.setText(med.getBDNOMEMEDICACAO());
		textPane.setText("\nDescrição do medicamento:\n - "+med.getBDDESCRICAO());
		
	}
	
	public void limparDados() {
		edDataFinal.setText("");
		edDataInicio.setText("");

		textPane.setText("");

		FDAOTReceita = null;
		lblNomemedicamento.setText("");
		lblStatus.setText("Status: Inserindo Receita");
	}

	@Override
	public void preencheMedicamento(Medicamento dados) {
		med(dados);
		
	}

}
