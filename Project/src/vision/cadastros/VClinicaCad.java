
package vision.cadastros;

import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import control.DAOTCidade;
import control.DAOTClinica;
import control.DAOTDadosUser;
import control.DAOTEndereco;
import control.DAOTEstado;
import control.DAOTUser;
import model.MTCidade;
import model.MTClinica;
import model.MTDadosUser;
import model.MTEndereco;
import model.MTEstado;
import vision.VLogin;
import vision.VMenu;
import vision.consultas.VUserCON;
import vision.padrao.CEPTextField;
import vision.padrao.CNPJTextFiel;
import vision.padrao.PanelComBackgroundImage;
import vision.padrao.RoundButton;
import vision.padrao.RoundJFormattedTextField;
import vision.padrao.RoundJTextField;

import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Component;
import java.awt.Menu;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VClinicaCad extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DAOTClinica FDAOTClinica = new DAOTClinica();
	public DAOTEndereco FDAOTEndereco = new DAOTEndereco();
	public DAOTEstado FDAOTEstado = new DAOTEstado();
	public DAOTCidade FDAOTCidade = new DAOTCidade();
	public DAOTDadosUser FDAOTDadosUser = new DAOTDadosUser();
	ArrayList<MTClinica> TListClinica = new ArrayList<>();
	ArrayList<MTEstado> TListEstado = new ArrayList<>();
	ArrayList<MTEndereco> TListEndereco = new ArrayList<>();
	ArrayList<MTCidade> TListCidade = new ArrayList<>();
	private VMenu menu = new VMenu();
	private JPanel contentPane;
	private JTextField edCidade;
	private CNPJTextFiel edCnpj;
	private JTextField edDescricao;
	private JTextField edNomeFan;
	private JTextField edNome;
	private CEPTextField edCep;
	private JTextField edBairro;
	private JTextField edSenha;
	private JComboBox cbUF;
	private RoundButton btnDelet;
	private RoundButton btnConf;
	private JOptionPane optionPane;
	private  JDialog dialog;
	private Timer timer;
	
	/**
	 * Create the frame.
	 */
	public VClinicaCad() {
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
					preencheCampos();
			}
		});
		setBounds(100, 100, 848, 524);
		
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		BufferedImage bg = null;
		;
		try {
			bg = ImageIO.read(new File("src/vision/images/BGuser.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		setTitle("Cadastro Clinica");
		setBounds(100, 100, 900, 592);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(158, 174, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(158, 174, 255));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[50px][800px,grow][50px]", "[50px][430px,grow][50px]"));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(158, 174, 255));
		panel.add(panel_1, "cell 1 1,alignx center");
		panel_1.setLayout(new MigLayout("", "[50px][50px][400px,grow][50px,grow][50px]", "[50px,grow][150px,grow][][50px]"));
		
		JPanel panel_2 = new PanelComBackgroundImage(bg);
		panel_2.setBackground(new Color(158, 174, 255));
		panel_1.add(panel_2, "cell 2 1,alignx center");
		panel_2.setLayout(new MigLayout("", "[100px][200px,grow][200px,grow][100px]", "[80px][][][][][][][50px][80px]"));
		
		JLabel lbNomeFan = new JLabel("Nome Fantasia:");
		panel_2.add(lbNomeFan, "flowy,cell 1 2");
		
		edNomeFan = new RoundJTextField();
		edNomeFan.setBorder(new EmptyBorder(3, 3, 3, 3));
		panel_2.add(edNomeFan, "cell 1 2,growx");
		edNomeFan.setColumns(10);
		
		JLabel lbDesc = new JLabel("Descricao:");
		panel_2.add(lbDesc, "flowy,cell 2 2");
		
		edDescricao = new RoundJTextField();
		edDescricao.setBorder(new EmptyBorder(3, 3, 3, 3));
		panel_2.add(edDescricao, "cell 2 2,growx");
		edDescricao.setColumns(10);
		
		JLabel lbNome = new JLabel("Nome:");
		panel_2.add(lbNome, "flowy,cell 1 3");
		
		edNome = new RoundJTextField();
		edNome.setBorder(new EmptyBorder(3, 3, 3, 3));
		panel_2.add(edNome, "cell 1 3,growx");
		edNome.setColumns(10);
		
		JLabel lbCnpj = new JLabel("CNPJ:");
		panel_2.add(lbCnpj, "flowy,cell 2 3");
		
		edCnpj = new CNPJTextFiel();
		panel_2.add(edCnpj, "cell 2 3,growx");
		edCnpj.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
		edCnpj.setColumns(10);
		
		JLabel lbCep = new JLabel("CEP:");
		panel_2.add(lbCep, "cell 1 5");
		
		JLabel lbUf = new JLabel("UF:     ");
		panel_2.add(lbUf, "flowx,cell 2 5");
		
		edCep = new CEPTextField();
		edCep.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
				
					if (edCep.getCEP() != null) {
						if(edCep.getCEPExiste(Integer.valueOf(edCep.getCEP()), edBairro, edCidade, edDescricao, cbUF) ) {

							edDescricao.setEditable(false);
					        edBairro.setEditable(false);
					        edCidade.setEditable(false);
					        cbUF.setEnabled(false);
						}
						else {
							edBairro.setText("");
							edCidade.setText("");
							edDescricao.setText("");
							cbUF.setSelectedIndex(0);
							
					        edDescricao.setEditable(true);
					        edBairro.setEditable(true);
					        edCidade.setEditable(true);
					        cbUF.setEnabled(true);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Cep invalido");
					
					}
				}
			});
		edCep.setColumns(10);
		edCep.setBounds(104, 11, 156, 20);
		edCep.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
		panel_2.add(edCep, "cell 1 6,growx");
		edCep.setColumns(10);
		
		JLabel lbBairro = new JLabel("Bairro:");
		panel_2.add(lbBairro, "flowy,cell 1 7,alignx left");
		
		edBairro = new RoundJTextField();
		edBairro.setBorder(new EmptyBorder(3, 3, 3, 3));
		panel_2.add(edBairro, "cell 1 7,growx");
		edBairro.setColumns(10);

		TListEstado = FDAOTEstado.ListTEstado(FDAOTEstado);
		cbUF = new JComboBox<MTEstado>();
		for (MTEstado mtEstado : TListEstado) {
			cbUF.addItem(mtEstado);
		}
		panel_2.add(cbUF, "flowx,cell 2 6");
		
		
		edCidade = new RoundJTextField();
		edCidade.setBorder(new EmptyBorder(3, 3, 3, 3));
		panel_2.add(edCidade, "cell 2 6,growx");
		edCidade.setColumns(10);
		
		JLabel lbCidade = new JLabel("Cidade:");
		panel_2.add(lbCidade, "cell 2 5");
		
		JLabel lbSenha = new JLabel("Senha:");
		panel_2.add(lbSenha, "flowy,cell 2 7");
		
		edSenha = new RoundJTextField();
		edSenha.setBorder(new EmptyBorder(3, 3, 3, 3));
		panel_2.add(edSenha, "cell 2 7,growx");
		edSenha.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(158, 174, 255));
		panel_1.add(panel_3, "cell 3 1,grow");
		panel_3.setLayout(new MigLayout("", "[10px][10px,grow][]", "[grow][grow][][][][][][grow][][]"));
		
		JPanel panel_4 = new PanelComBackgroundImage(bg);
		panel_4.setBackground(new Color(125, 137, 245));
		panel_3.add(panel_4, "cell 1 0,grow");
		panel_4.setLayout(new MigLayout("", "[][][][]", "[][][][]"));
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon(VClinicaCad.class.getResource("/vision/images/dogmal.png")));
		panel_4.add(lblNewLabel_8, "cell 1 2,alignx center");
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5, "cell 1 1,grow");
		panel_5.setLayout(new BorderLayout(0, 0));
		
		btnConf = new RoundButton("Confirmar");
		btnConf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (!edCep.getCEPExiste(Integer.valueOf(edCep.getCEP()), edBairro, edCidade,edDescricao, cbUF)){
						
					//validação do cep com o componente,
					FDAOTCidade.setBDIDCIDADE(FDAOTCidade.getChaveID("tcidades", "BDIDCIDADE"));
					FDAOTCidade.setBDNOMECID(edCidade.getText());
					FDAOTCidade.setBDDESCCID(edDescricao.getText());
					FDAOTCidade.setBDIDUF(edCep.achaIdUf(cbUF));
						
					FDAOTCidade.inserir(FDAOTCidade);
						
					FDAOTEndereco.setBDIDCIDADE(FDAOTCidade.getBDIDCIDADE());
					FDAOTEndereco.setBDBAIRRO(edBairro.getText());
					FDAOTEndereco.setBDCEP(Integer.valueOf(edCep.getCEP()));
						
					FDAOTEndereco.inserir(FDAOTEndereco);
						
				}
					
						
				if(edCnpj.existeCnpjClinica(FDAOTClinica)) {
					FDAOTClinica.setBDIDCLINICA(edCnpj.IdClinica());
				}
				else {
					FDAOTClinica.setBDIDCLINICA(FDAOTClinica.getChaveID("tclinica", "BDIDCLINICA"));
				}
					
				FDAOTClinica.setBDCNPJ(edCnpj.getText());
				FDAOTClinica.setBDNOME(edNome.getText());
				FDAOTClinica.setBDNOMEFANTASIA(edNomeFan.getText());
				FDAOTClinica.setBDSENHA(edSenha.getText());
				FDAOTClinica.setBDIDCEP(Integer.valueOf(edCep.getCEP()));
						
				if(edCnpj.existeCnpjClinica(FDAOTClinica)) {
					FDAOTClinica.alterar(FDAOTClinica);
					
					menu.AtualizaDadosLogin("", edNome.getText());
					
				}
				else {
					FDAOTClinica.inserir(FDAOTClinica);
					
					
					try {
						FDAOTDadosUser.ListTDadosUser(FDAOTDadosUser);
						
					} catch (Exception e2) {
						int resposta = JOptionPane.showConfirmDialog(null,
								"Nenhuma Usuario cadastrado\nDeseja Cadastrar um?", "ATENÇÃO!!",
								JOptionPane.YES_NO_OPTION);
						if (resposta == JOptionPane.YES_OPTION) {
							VUserCad user = new VUserCad();
							user.setVisible(true);
							dispose();	
						}
					}
					
				}
				
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
				
				if(!edCnpj.existeCnpjClinica(FDAOTClinica)) {
					
					VLogin clinica= new VLogin();
					clinica.setVisible(true);
					dispose();
				}
				
			}
			
		});
		panel_3.add(btnConf, "cell 1 3,growx");
		btnConf.setBackground((new Color(255, 199, 0)));
		

		btnDelet = new RoundButton("Deletar");
		btnDelet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int resposta = JOptionPane.showConfirmDialog(null,
						"Ao deletar a clinica, todos os Dados que estão ligadas a ela serão Excluidas\nDeseja confirmar essa ação?", "ATENÇÃO!!",
						JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
					FDAOTClinica.setBDIDCLINICA(edCnpj.IdClinica());
					FDAOTClinica.deletar(FDAOTClinica);
					
					optionPane = new JOptionPane("A Clinica foi Deletada", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
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
					optionPane = new JOptionPane("A Clinica não foi Deletada", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
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
		btnDelet.setBackground(new Color(255, 199, 0));
		panel_3.add(btnDelet, "cell 1 6,growx");
	
	}
	
	
	
	
	
	public void preencheCampos() {
		TListClinica = FDAOTClinica.ListTClinica(FDAOTClinica);
		TListEndereco = FDAOTEndereco.ListTEndereco(FDAOTEndereco);

		for (MTClinica mtClinica : TListClinica) {
			 
			if(!TListClinica.isEmpty()){
				
				edCnpj.setEditable(false);
				
				edCnpj.setText(mtClinica.getBDCNPJ());
				edNome.setText(mtClinica.getBDNOME());
				edNomeFan.setText(mtClinica.getBDNOMEFANTASIA());
				edSenha.setText(mtClinica.getBDSENHA());
				edCep.setText(String.valueOf(mtClinica.getBDIDCEP()));

				edCep.getCEPExiste(Integer.valueOf(edCep.getCEP()), edBairro, edCidade,edDescricao, cbUF);
				edDescricao.setEditable(false);
			    edBairro.setEditable(false);
			    edCidade.setEditable(false);
			    cbUF.setEnabled(false);
			}	
			else {
				JOptionPane.showMessageDialog(null, "Cadastre um clinica");
			}
		}
	}
}