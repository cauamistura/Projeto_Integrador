package vision.cadastros;

import java.awt.EventQueue;  
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import control.DAOTClinica;
import control.DAOTEndereco;
import control.DAOTEstado;
import model.MTClinica;
import model.MTEndereco;
import model.MTEstado;
import padrao.RoundButton;
import padrao.RoundJTextField;
import vision.VMenu;

import javax.swing.JTabbedPane;
import javax.swing.JSeparator;
import javax.swing.JComboBox;

public class VCadClinica extends JFrame {

	public DAOTClinica FDAOTClinica = new DAOTClinica();
	public DAOTEndereco FDAOTEndereco = new DAOTEndereco();
	private JPanel contentPane;
	private JTextField edCidade;
	private JTextField edDescricao;
	private JTextField edCep;
	private JTextField edBairro;
	private JTextField edNome;
	private JTextField edCnpj;
	private JTextField edNomeFan;
	private JTextField edSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VCadClinica frame = new VCadClinica();
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
	public VCadClinica() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 635, 378);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pEndereco = new JPanel();
		pEndereco.setBounds(12, 170, 601, 166);
		contentPane.add(pEndereco);
		pEndereco.setLayout(null);
		
		JLabel lbUf = new JLabel("UF : ");
		lbUf.setBounds(77, 58, 70, 15);
		pEndereco.add(lbUf);
		
		JLabel lbCidade = new JLabel("Cidade :");
		lbCidade.setBounds(320, 53, 70, 15);
		pEndereco.add(lbCidade);
		
		edCidade = new RoundJTextField(15);
		edCidade.setColumns(10);
		edCidade.setBounds(408, 51, 114, 19);
		pEndereco.add(edCidade);
		
		edDescricao = new RoundJTextField(15);
		edDescricao.setColumns(10);
		edDescricao.setBounds(408, 89, 114, 19);
		pEndereco.add(edDescricao);
		
		JLabel lbDesc = new JLabel("Descrição : ");
		lbDesc.setBounds(320, 91, 87, 15);
		pEndereco.add(lbDesc);
		
		edCep = new RoundJTextField(15);
		edCep.setColumns(10);
		edCep.setBounds(152, 87, 114, 19);
		pEndereco.add(edCep);
		
		JLabel lbCep = new JLabel("CEP :");
		lbCep.setBounds(64, 89, 70, 15);
		pEndereco.add(lbCep);
		
		JLabel lbBairro = new JLabel("Bairro :");
		lbBairro.setBounds(77, 118, 70, 15);
		pEndereco.add(lbBairro);
		
		edBairro = new RoundJTextField(15);
		edBairro.setColumns(10);
		edBairro.setBounds(165, 116, 114, 19);
		pEndereco.add(edBairro);
		
		JLabel lbEndereo = new JLabel("Endereço");
		lbEndereo.setBounds(0, 0, 70, 15);
		pEndereco.add(lbEndereo);
		
		JButton btnCad = new RoundButton("Cadastrar");
		btnCad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FDAOTClinica.setBDIDCLINICA(FDAOTClinica.getChaveID("TClinica","BDIDCLINICA"));
				FDAOTClinica.setBDCNPJ(edCnpj.getText());
				FDAOTClinica.setBDNOME(edNome.getText());
				FDAOTClinica.setBDNOMEFANTASIA(edNomeFan.getText());
				FDAOTClinica.setBDSENHA(edSenha.getText()) ;
				
				FDAOTEndereco.setBDIDCIDADE(FDAOTEndereco.getChaveID("TCidade", "BDIDCIDADE"));
				FDAOTEndereco.setBDNOMECID(edCidade.getText());
				FDAOTEndereco.setBDDESCCID(edDescricao.getText());
				
				FDAOTEndereco.setBDBAIRRO(edBairro.getText());
				FDAOTEndereco.setBDBAIRRO(edCep.getText());
				
				FDAOTClinica.inserir(FDAOTClinica);
				FDAOTEndereco.inserir(FDAOTEndereco);
		        FDAOTClinica.inserir(FDAOTClinica);
			}
		});
		btnCad.setBounds(419, 132, 125, 23);
		pEndereco.add(btnCad);
		
		ArrayList<MTEndereco> TListEndereco = new ArrayList<>();
		TListEndereco = FDAOTEndereco.ListTEndereco(FDAOTEndereco);
		
		JComboBox cbUF = new JComboBox();
		for (MTEndereco mtEndereco : TListEndereco) {
			cbUF.addItem(mtEndereco.getBDSIGLAUF());
		}
		cbUF.setBounds(152, 53, 114, 24);
		pEndereco.add(cbUF);
		
		JPanel pDados = new JPanel();
		pDados.setBounds(12, 0, 601, 158);
		contentPane.add(pDados);
		pDados.setLayout(null);
		
		JLabel lbCnpj = new JLabel("CNPJ :");
		lbCnpj.setBounds(116, 91, 70, 15);
		pDados.add(lbCnpj);
		
		edNome = new RoundJTextField(15);
		edNome.setColumns(10);
		edNome.setBounds(204, 27, 312, 19);
		pDados.add(edNome);
		
		edCnpj = new RoundJTextField(15);
		edCnpj.setColumns(10);
		edCnpj.setBounds(204, 89, 312, 19);
		pDados.add(edCnpj);
		
		edNomeFan = new RoundJTextField(15);
		edNomeFan.setColumns(10);
		edNomeFan.setBounds(204, 58, 312, 19);
		pDados.add(edNomeFan);
		
		JLabel lbNome = new JLabel("Nome :");
		lbNome.setBounds(133, 29, 61, 15);
		pDados.add(lbNome);
		
		JLabel lbNomeFan = new JLabel("Nome Fantasia :");
		lbNomeFan.setBounds(75, 58, 126, 15);
		pDados.add(lbNomeFan);
		
		JLabel lbDadosClinica = new JLabel("Dados Clinica");
		lbDadosClinica.setBounds(12, 0, 126, 15);
		pDados.add(lbDadosClinica);
		
		edSenha = new RoundJTextField(15);
		edSenha.setBounds(204, 120, 312, 19);
		pDados.add(edSenha);
		edSenha.setColumns(10);
		
		JLabel lbSenha = new JLabel("Senha:");
		lbSenha.setBounds(116, 118, 70, 15);
		pDados.add(lbSenha);
		
	}
}
