package vision.cadastros;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import control.DAOTCidade;
import control.DAOTClinica;
import control.DAOTEndereco;
import control.DAOTEstado;
import model.MTCidade;
import model.MTClinica;
import model.MTEndereco;
import model.MTEstado;
import vision.padrao.PanelComBackgroundImage;
import vision.padrao.RoundButton;
import vision.padrao.RoundJTextField;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.ImageIcon;
import java.awt.Color;

public class VClinicaCad extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DAOTClinica FDAOTClinica = new DAOTClinica();
	public DAOTEndereco FDAOTEndereco = new DAOTEndereco();
	public DAOTEstado FDAOTEstado = new DAOTEstado();
	public DAOTCidade FDAOTCidade = new DAOTCidade();
	private ArrayList<MTEstado> ListEstado;
	private JPanel contentPane;
	private JTextField edCidade;
	private JTextField edCnpj;
	private JTextField edDescricao;
	private JTextField edNomeFan;
	private JTextField edNome;
	private JTextField edCep;
	private JTextField edBairro;
	private JTextField edSenha;
	private JComboBox cbUF;

	/**
	 * Create the frame.
	 */
	public VClinicaCad() {
		BufferedImage bg = null;
		;
		try {
			bg = ImageIO.read(new File("src/vision/images/BG.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 592);
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
		
		edNomeFan = new RoundJTextField(8);
		panel_2.add(edNomeFan, "cell 1 2,growx");
		edNomeFan.setColumns(10);
		
		JLabel lbDesc = new JLabel("Descricao:");
		panel_2.add(lbDesc, "flowy,cell 2 2");
		
		edDescricao = new RoundJTextField(8);
		panel_2.add(edDescricao, "cell 2 2,growx");
		edDescricao.setColumns(10);
		
		JLabel lbNome = new JLabel("Nome:");
		panel_2.add(lbNome, "flowy,cell 1 3");
		
		edNome = new RoundJTextField(8);
		panel_2.add(edNome, "cell 1 3,growx");
		edNome.setColumns(10);
		
		JLabel lbCnpj = new JLabel("CNPJ:");
		panel_2.add(lbCnpj, "flowy,cell 2 3");
		
		edCnpj = new RoundJTextField(8);
		panel_2.add(edCnpj, "cell 2 3,growx");
		edCnpj.setColumns(10);
		
		JLabel lbCep = new JLabel("CEP:");
		panel_2.add(lbCep, "cell 1 5");
		
		JLabel lbUf = new JLabel("UF:     ");
		panel_2.add(lbUf, "flowx,cell 2 5");
		
		edCep = new RoundJTextField(8);
		panel_2.add(edCep, "cell 1 6,growx");
		edCep.setColumns(10);
		
		JLabel lbBairro = new JLabel("Bairro:");
		panel_2.add(lbBairro, "flowy,cell 1 7,alignx left");
		
		edBairro = new RoundJTextField(8);
		panel_2.add(edBairro, "cell 1 7,growx");
		edBairro.setColumns(10);
		
		ArrayList<MTEstado> TListEstado = new ArrayList<>();
		TListEstado = FDAOTEstado.ListTEstado(FDAOTEstado);
		
		cbUF = new JComboBox<MTEstado>();
		for (MTEstado mtEstado : TListEstado) {
			cbUF.addItem(mtEstado);
		}
		panel_2.add(cbUF, "flowx,cell 2 6");
		
		
		edCidade = new RoundJTextField(8);
		panel_2.add(edCidade, "cell 2 6,growx");
		edCidade.setColumns(10);
		
		JLabel lbCidade = new JLabel("Cidade:");
		panel_2.add(lbCidade, "cell 2 5");
		
		JLabel lbSenha = new JLabel("Senha:");
		panel_2.add(lbSenha, "flowy,cell 2 7");
		
		edSenha = new RoundJTextField(8);
		panel_2.add(edSenha, "cell 2 7,growx");
		edSenha.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(158, 174, 255));
		panel_1.add(panel_3, "cell 3 1,grow");
		panel_3.setLayout(new MigLayout("", "[10px][10px,grow][]", "[grow][grow][grow][]"));
		
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
		
		JButton btnCad = new JButton("Cadastrar");
		btnCad.setBackground((new Color(255, 199, 0)));
		btnCad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				try {
					if (!getCEPExiste(Integer.valueOf(edCep.getText()))){
						
						//validação do cep com o componente,
						FDAOTCidade.setBDIDCIDADE(FDAOTCidade.getChaveID("tcidades", "BDIDCIDADE"));
						FDAOTCidade.setBDNOMECID(edCidade.getText());
						FDAOTCidade.setBDDESCCID(edDescricao.getText());
						FDAOTCidade.setBDIDUF(achaIdUf());
						
						FDAOTCidade.inserir(FDAOTCidade);
						
						FDAOTEndereco.setBDIDCIDADE(FDAOTCidade.getBDIDCIDADE());
						FDAOTEndereco.setBDBAIRRO(edBairro.getText());
						FDAOTEndereco.setBDCEP(Integer.valueOf(edCep.getText()));
						
						FDAOTEndereco.inserir(FDAOTEndereco);
						
						
						//Fazer a validação para o CNPJ
						FDAOTClinica.setBDIDCLINICA(FDAOTClinica.getChaveID("tclinica", "BDIDCLINICA"));
						FDAOTClinica.setBDCNPJ(edCnpj.getText());
						FDAOTClinica.setBDNOME(edNome.getText());
						FDAOTClinica.setBDNOMEFANTASIA(edNomeFan.getText());
						FDAOTClinica.setBDSENHA(edSenha.getText());
						FDAOTClinica.setBDIDCEP(FDAOTEndereco.getBDCEP());
						
						FDAOTClinica.inserir(FDAOTClinica);
						
					
						
						JOptionPane.showMessageDialog(null, "Salvo com sucesso");
					}
						
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Erro ao cadastrar endereço, verifique se preencheu toadas as informações");
				}
				
			}
		});
		panel_5.add(btnCad);

		

	}
	public Integer achaIdUf() {
		
		Integer idUf = 0; 
		ArrayList<MTEstado> TListEstado = new ArrayList<>();
		TListEstado = FDAOTEstado.ListTEstado(FDAOTEstado);

		for (MTEstado mtEstado : TListEstado) {
			
		if (mtEstado.getBDSIGLAUF().equals(cbUF.getSelectedItem().toString())) {
			idUf = mtEstado.getBDIDUF();
			
		}		
		      
		}
		return idUf;
	}
	
	private Boolean getCEPExiste(int prCEP) {
		// Valida se existe CEP
		ArrayList<MTEndereco> lEndereco = new ArrayList<>();
		lEndereco = FDAOTEndereco.ListTEndereco(FDAOTEndereco);
		for (MTEndereco l : lEndereco) {

			if (l.getBDCEP() == prCEP) {
				edBairro.setText(l.getBDBAIRRO());

				// Procura Cidade Vinculada
				ArrayList<MTCidade> lCidade = new ArrayList<>();
				lCidade = FDAOTCidade.ListTCidade(FDAOTCidade);
				for (MTCidade lc : lCidade) {
					if (l.getBDIDCIDADE() == lc.getBDIDCIDADE()) {
						edCidade.setText(lc.getBDNOMECID());

						// Procura Estado vinculado
						for (MTEstado le : ListEstado) {
							if (lc.getBDIDUF() == le.getBDIDUF()) {
								cbUF.setSelectedIndex(lc.getBDIDUF()-1);
							}
						}
					}
				}
				return true;
			}
		}
		return false;
	}
}
