package vision.cadastros;

import java.awt.BorderLayout; 
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import control.DAOTComorbidade;
import model.MTComorbidade;
import net.miginfocom.swing.MigLayout;
import vision.padrao.PanelComBackgroundImage;
import vision.padrao.RoundButton;
import vision.padrao.TableSimples;
import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTable;

public class VComorbidadeCad extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Obejetos
	public DAOTComorbidade FDAOTComorbidade = new DAOTComorbidade();
	private ArrayList<MTComorbidade> TListComorbidade = new ArrayList<>();

	private boolean registroCadastro = true;
	private int row;
	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JLabel lblNewLabel_3;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel_4;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public VComorbidadeCad() {
		BufferedImage bg = null;
		;
		try {
			bg = ImageIO.read(new File("src/vision/images/BGV.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setTitle("Cadastro de Comorbidade");
		TListComorbidade = FDAOTComorbidade.ListTComorbidade(FDAOTComorbidade);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1300, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setBackground(new Color(158, 174, 255));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[100px][1100px,grow][100px]", "[100px][600px,grow][100px]"));
		
		panel_1 = new PanelComBackgroundImage(bg);
		panel_1.setBackground(new Color(158, 174, 255));
		panel.add(panel_1, "cell 1 1,alignx center");
		panel_1.setLayout(new MigLayout("", "[450px,grow][630px,grow]", "[580px,grow]"));
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(125, 137, 245));
		panel_1.add(panel_2, "cell 0 0,grow");
		panel_2.setLayout(new MigLayout("", "[grow]", "[80px,grow][110px,grow][80px,grow]"));
		
		panel_4 = new JPanel();
		panel_4.setBackground(new Color(125, 137, 245));
		panel_2.add(panel_4, "cell 0 0,grow");
		panel_4.setLayout(new MigLayout("", "[100px][100px][100px]", "[10px][100px]"));
		
		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(VComorbidadeCad.class.getResource("/vision/images/simboloV.png")));
		panel_4.add(lblNewLabel_3, "cell 1 1,alignx center");
		
		panel_5 = new JPanel();
		panel_5.setBackground(new Color(125, 137, 245));
		panel_2.add(panel_5, "cell 0 1,grow");
		panel_5.setLayout(new MigLayout("", "[grow]", "[][][][][100px]"));
		
		lblNewLabel = new JLabel("New label");
		panel_5.add(lblNewLabel, "flowy,cell 0 1");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		panel_5.add(textField_2, "cell 0 1,growx");
		
		lblNewLabel_1 = new JLabel("New label");
		panel_5.add(lblNewLabel_1, "flowy,cell 0 2");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		panel_5.add(textField_1, "cell 0 2,growx");
		
		lblNewLabel_2 = new JLabel("New label");
		panel_5.add(lblNewLabel_2, "flowy,cell 0 3");
		
		textField = new JTextField();
		panel_5.add(textField, "cell 0 3,growx");
		textField.setColumns(10);
		
		panel_6 = new JPanel();
		panel_6.setBackground(new Color(125, 137, 245));
		panel_2.add(panel_6, "cell 0 2,grow");
		panel_6.setLayout(new MigLayout("", "[100px][][100px][100px][100px][100px][100px]", "[][]"));
		
		btnNewButton_2 = new JButton("New button");
		panel_6.add(btnNewButton_2, "cell 1 1");
		
		btnNewButton_1 = new JButton("New button");
		panel_6.add(btnNewButton_1, "cell 3 1");
		
		btnNewButton = new JButton("New button");
		panel_6.add(btnNewButton, "cell 5 1");
		
		panel_3 = new JPanel();
		panel_3.setBackground(new Color(125, 137, 245));
		panel_1.add(panel_3, "cell 1 0,grow");
		panel_3.setLayout(new MigLayout("", "[][][][grow][][][]", "[40px][190px][100px][200px,grow][100px][100px]"));
		
		lblNewLabel_4 = new JLabel("Cadastro de Comorbidade");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_3.add(lblNewLabel_4, "cell 3 1,alignx center,aligny top");
		
		scrollPane = new JScrollPane();
		panel_3.add(scrollPane, "cell 3 3,grow");
		
		table = new JTable();
		scrollPane.setViewportView(table);

		atualizatabela();
	}

	private void atualizatabela() {
		TListComorbidade = FDAOTComorbidade.ListTComorbidade(FDAOTComorbidade);

//		for (MTComorbidade com : TListComorbidade) {
//			Object[][] rowData = {{com.getBDIDCOMORBIDADE(), com.getBDNOMECOMORBIDADE(), com.getBDDESCCOMORBIDADE() }};
//			((TableSimples) table).preencherTabela(rowData);	
//		}	


	}

//	private void limparDados() {
//		edDescCom.setText("");
//		edNomeCom.setText("");
//
//		registroCadastro = true;
//
//		edNomeCom.requestFocus();
//		lbStatus.setText("Status: Inserindo medicamento");
//	}

	public void eventConfirmar() {
		
//		if (edNomeCom.getText() == null || edDescCom.getText().isEmpty()){
//			JOptionPane.showMessageDialog(null, "Campo vazio: Comobirdade", "Atenção", 0);
//			edNomeCom.requestFocus();
//			return;
//		}
		
//		FDAOTComorbidade.setBDDESCCOMORBIDADE(edDescCom.getText());
//		FDAOTComorbidade.setBDNOMECOMORBIDADE(edNomeCom.getText());

		if (registroCadastro == true) {
			FDAOTComorbidade.setBDIDCOMORBIDADE(FDAOTComorbidade.getChaveID("tcomorbidade", "BDIDCOMORBIDADE"));
			FDAOTComorbidade.inserir(FDAOTComorbidade);
		} else {
			FDAOTComorbidade.setBDIDCOMORBIDADE(FDAOTComorbidade.getBDIDCOMORBIDADE());
			FDAOTComorbidade.alterar(FDAOTComorbidade);
		}
//		table.limparTabela();
//		atualizatabela();
//		limparDados();
	}

	public void eventExcluir(Integer prID) {
		int resposta = JOptionPane.showConfirmDialog(null,
				"Ao Deletar esta comorbidade, você não vai mais pode utiliza-la.",
				"Atenção!", JOptionPane.YES_NO_OPTION);

		if (resposta == JOptionPane.YES_NO_OPTION) {
			FDAOTComorbidade.deletar(prID);

//			table.limparTabela();
//			atualizatabela();
//			limparDados();

			JOptionPane.showInternalMessageDialog(null, "Excluido com sucesso!");
		}
	}

}
