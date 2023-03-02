package vision.cadastros;

import java.awt.EventQueue; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import control.DAOTClinica;
import padrao.RoundJTextField;
import vision.VMenu;

import javax.swing.JTabbedPane;
import javax.swing.JSeparator;

public class VCadClinica extends JFrame {

	public DAOTClinica FDAOTClinica = new DAOTClinica();
	private JPanel contentPane;
	private JTextField edUf;
	private JTextField edCidade;
	private JTextField edDescricao;
	private JTextField edCep;
	private JTextField edBairro;
	private JTextField edId;
	private JTextField edNome;
	private JTextField edCnpj;
	private JTextField edNomeFan;

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
		
		edUf = new JTextField();
		edUf.setColumns(10);
		edUf.setBounds(165, 56, 114, 19);
		pEndereco.add(edUf);
		
		JLabel lbCidade = new JLabel("Cidade :");
		lbCidade.setBounds(320, 53, 70, 15);
		pEndereco.add(lbCidade);
		
		edCidade = new JTextField();
		edCidade.setColumns(10);
		edCidade.setBounds(408, 51, 114, 19);
		pEndereco.add(edCidade);
		
		edDescricao = new JTextField();
		edDescricao.setColumns(10);
		edDescricao.setBounds(408, 89, 114, 19);
		pEndereco.add(edDescricao);
		
		JLabel lbDesc = new JLabel("Descrição : ");
		lbDesc.setBounds(320, 91, 87, 15);
		pEndereco.add(lbDesc);
		
		edCep = new JTextField();
		edCep.setColumns(10);
		edCep.setBounds(152, 87, 114, 19);
		pEndereco.add(edCep);
		
		JLabel lbCep = new JLabel("CEP :");
		lbCep.setBounds(64, 89, 70, 15);
		pEndereco.add(lbCep);
		
		JLabel lbBairro = new JLabel("Bairro :");
		lbBairro.setBounds(77, 118, 70, 15);
		pEndereco.add(lbBairro);
		
		edBairro = new JTextField();
		edBairro.setColumns(10);
		edBairro.setBounds(165, 116, 114, 19);
		pEndereco.add(edBairro);
		
		JLabel lbEndereo = new JLabel("Endereço");
		lbEndereo.setBounds(0, 0, 70, 15);
		pEndereco.add(lbEndereo);
		
		JButton btnCad = new JButton("Cadastrar");
		btnCad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FDAOTClinica.setBDCNPJ(edCnpj.getText());
				FDAOTClinica.setBDNOME(edNome.getText());
				FDAOTClinica.setBDNOMEFANTASIA(edNomeFan.getText());
				
				
		        FDAOTClinica.inserir(FDAOTClinica);
				JOptionPane.showMessageDialog(null, "foi hehe");
			}
		});
		btnCad.setBounds(419, 132, 89, 23);
		pEndereco.add(btnCad);
		
		JPanel pDados = new JPanel();
		pDados.setBounds(12, 0, 601, 158);
		contentPane.add(pDados);
		pDados.setLayout(null);
		
		JLabel lbCnpj = new JLabel("CNPJ :");
		lbCnpj.setBounds(104, 116, 70, 15);
		pDados.add(lbCnpj);
		
		edId = new JTextField();
		edId.setColumns(10);
		edId.setBounds(192, 25, 55, 19);
		pDados.add(edId);
		
		edNome = new JTextField();
		edNome.setColumns(10);
		edNome.setBounds(192, 52, 312, 19);
		pDados.add(edNome);
		
		edCnpj = new JTextField();
		edCnpj.setColumns(10);
		edCnpj.setBounds(192, 114, 312, 19);
		pDados.add(edCnpj);
		
		edNomeFan = new JTextField();
		edNomeFan.setColumns(10);
		edNomeFan.setBounds(192, 83, 312, 19);
		pDados.add(edNomeFan);
		
		JLabel lbNome = new JLabel("Nome :");
		lbNome.setBounds(121, 54, 61, 15);
		pDados.add(lbNome);
		
		JLabel lbNomeFan = new JLabel("Nome Fantasia :");
		lbNomeFan.setBounds(63, 83, 126, 15);
		pDados.add(lbNomeFan);
		
		JLabel lbID = new JLabel("ID :");
		lbID.setBounds(144, 27, 42, 15);
		pDados.add(lbID);
		
		JLabel lbDadosClinica = new JLabel("Dados Clinica");
		lbDadosClinica.setBounds(0, 0, 126, 15);
		pDados.add(lbDadosClinica);
		
	}
}
