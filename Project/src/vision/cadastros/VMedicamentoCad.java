package vision.cadastros;

import java.awt.EventQueue; 

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import control.DAOTMedicacao;
import model.MTMedicacao;
import model.interfaces.InterfaceConsMed;

import java.awt.BorderLayout;
import java.awt.Color;

import net.miginfocom.swing.MigLayout;
import vision.padrao.PanelComBackgroundImage;
import vision.padrao.RoundButton;
import vision.padrao.RoundJTextField;
import vision.padrao.TableSimples;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;  
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import control.DAOTMedicacao;
import model.MTMedicacao;
import model.interfaces.InterfaceConsMed;
import net.miginfocom.swing.MigLayout;
import vision.padrao.RoundButton;
import vision.padrao.TableSimples;
import javax.swing.JButton;

public class VMedicamentoCad extends JFrame {

	private static final long serialVersionUID = 1L;
	public DAOTMedicacao FDAOTMedicacao = new DAOTMedicacao();
	private ArrayList<MTMedicacao> TListMedicacao = new ArrayList<>();
	private JPanel contentPane;
	private JTextField edNomeMed;
	private JTextField edDescMed;
	private RoundButton btnlimpar;
	private RoundButton btnConf;
	private RoundButton btnDelete;
	private TableSimples table;
	private boolean registroCadastro = true;
	private int row;
	private JOptionPane optionPane;
	private JDialog dialog;
	private Timer timer;
	private JLabel lbStatus;
	private JLabel lbNome;
	private JLabel lbDesc;
	private JLabel title;
	private RoundButton btnReceita;
	private String nome;
	private String desc;
	private Integer id;


	public VMedicamentoCad()  {
		BufferedImage bg = null;
		;
		try {
			bg = ImageIO.read(new File("src/vision/images/BGLogin.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TListMedicacao = FDAOTMedicacao.ListTMedicacao(FDAOTMedicacao);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(158, 174, 255));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[80px][1000px,grow][80px]", "[100px][600px,grow][100px]"));
		
		JPanel panel_1 = new PanelComBackgroundImage(bg);
		panel_1.setBackground(new Color(158, 174, 255));
		panel.add(panel_1, "cell 1 1,alignx center");
		panel_1.setLayout(new MigLayout("", "[350px,grow][520px,grow]", "[500px,grow]"));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(125, 137, 245));
		panel_1.add(panel_3, "cell 0 0,grow");
		panel_3.setLayout(new MigLayout("", "[grow]", "[80px,grow][110px,grow][70px,grow]"));
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(125, 137, 245));
		panel_3.add(panel_6, "cell 0 0,grow");
		panel_6.setLayout(new MigLayout("", "[100px][][100px]", "[]"));
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(VMedicamentoCad.class.getResource("/vision/images/med.png")));
		panel_6.add(lblNewLabel_2, "cell 1 0,alignx center,aligny center");
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(125, 137, 245));
		panel_3.add(panel_5, "cell 0 1,grow");
		panel_5.setLayout(new MigLayout("", "[grow]", "[][][][][][50px]"));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(125, 137, 245));
		panel_1.add(panel_2, "cell 1 0,grow");
		panel_2.setLayout(new MigLayout("", "[][grow][]", "[40px][180px][100px][250px,grow][100px][100px]"));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane, "cell 1 3,grow");
		
		table = new TableSimples(new Object[][] {}, new String[] { "Id", "Medicamento", "Descrição" });
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		atualizatabela();

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				row = table.getSelectedRow();
				if (row >= 0) {

					// Preencha os campos correspondentes com os dados da linha selecionada
					nome = table.getValueAt(row, 1).toString();
					desc = table.getValueAt(row, 2).toString();
					id = Integer.valueOf(table.getValueAt(row, 0).toString());
					edNomeMed.setText(nome);
					edDescMed.setText(desc);

					registroCadastro = false;

					FDAOTMedicacao.setBDIDMEDICACAO(id);
					
					lbStatus.setText("Status: Alterando medicamento");
					
					
				}
			}
		});
		scrollPane.setViewportView(table);
		
		lbNome = new JLabel("Nome: ");
		lbNome.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		panel_5.add(lbNome, "flowy,cell 0 1");
		
		
		lbDesc = new JLabel("Descrição:");
		lbDesc.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		panel_5.add(lbDesc, "flowy,cell 0 2");
		
		lbStatus= new JLabel("Status: Inserindo Medicamento");
		lbStatus.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		panel_5.add(lbStatus, "cell 0 5");
		
		title = new JLabel("Cadastro Medicamento");
		title.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		panel_2.add(title, "cell 1 1,alignx center,aligny top");
		
		edNomeMed = new JTextField();
		edNomeMed.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panel_5.add(edNomeMed, "cell 0 2,growx");
		edNomeMed.setColumns(10);
		
		edDescMed = new JTextField();
		edDescMed.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		edDescMed.setColumns(10);
		panel_5.add(edDescMed, "cell 0 1,growx");
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(125, 137, 245));
		panel_3.add(panel_4, "cell 0 2,grow");
		panel_4.setLayout(new MigLayout("", "[100px][][100px][][100px][][100px]", "[][]"));
		
		btnConf = new RoundButton("Confirmar");
		btnConf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.clearSelection();
				eventConfirmar();
			}
		});
		btnConf.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		panel_4.add(btnConf, "cell 1 1");
		
		btnlimpar = new RoundButton("Limpar");
		btnlimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparDados();
				table.clearSelection();
			}
		});
		btnlimpar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		panel_4.add(btnlimpar, "cell 3 1");
		
		btnDelete = new RoundButton("Deletar");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventDeletar();
				table.clearSelection();
			}
		});
		btnDelete.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		panel_4.add(btnDelete, "cell 5 1");
	
		
	}
	private void atualizatabela(){
		TListMedicacao = FDAOTMedicacao.ListTMedicacao(FDAOTMedicacao);
		
		for (MTMedicacao mtMed : TListMedicacao) {
			Object[][] rowData = {{ mtMed.getBDIDMEDICACAO(), mtMed.getBDNOMEMEDICACAO(), mtMed.getBDDESCRICAO() }};
			table.preencherTabela(rowData);	
		}		
	}

	public void limparDados() {
		edDescMed.setText("");
		edNomeMed.setText("");

		registroCadastro = true;

		edNomeMed.requestFocus();

		lbStatus.setText("Status: Inserindo medicamento");
	}
	
	public void eventConfirmar() {
		
		if (edNomeMed.getText() == null || edDescMed.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Campo vazio: Medicamento", "Atenção", 0);
			edNomeMed.requestFocus();
			return;
		}
		
		FDAOTMedicacao.setBDDESCRICAO(edDescMed.getText());
		FDAOTMedicacao.setBDNOMEMEDICACAO(edNomeMed.getText());
		
		if (registroCadastro == true) {
			FDAOTMedicacao.setBDIDMEDICACAO(FDAOTMedicacao.getChaveID("tmedicacao", "BDIDMEDICACAO"));
			FDAOTMedicacao.inserir(FDAOTMedicacao);
			
		} else {
			FDAOTMedicacao.setBDIDMEDICACAO(FDAOTMedicacao.getBDIDMEDICACAO());
			FDAOTMedicacao.alterar(FDAOTMedicacao);
		}
	
		table.limparTabela();
		atualizatabela();
		limparDados();
	}
	
	public void eventDeletar() {
		int resposta = JOptionPane.showConfirmDialog(null,
				"Ao Deletar este medicamento, você não vai mais pode utiliza-lo.",
				"Atenção!", JOptionPane.YES_NO_OPTION);
		if (resposta == JOptionPane.YES_NO_OPTION) {

			FDAOTMedicacao.deletar(FDAOTMedicacao);
			table.limparTabela();
			atualizatabela();
			limparDados();
			
			JOptionPane.showInternalMessageDialog(null, "Excluido com sucesso!");
		
		} else {
			
			JOptionPane.showInternalMessageDialog(null, "Medicamento não foi Excluido!");
			
		}

	}

}
