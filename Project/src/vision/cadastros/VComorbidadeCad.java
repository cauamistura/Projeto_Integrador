package vision.cadastros;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import control.DAOTComorbidade;
import model.MTComorbidade;
import net.miginfocom.swing.MigLayout;
import vision.padrao.PanelComBackgroundImage;
import vision.padrao.RoundButton;
import vision.padrao.RoundJTextField;
import vision.padrao.TableSimples;

public class VComorbidadeCad extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DAOTComorbidade FDAOTComorbidade = new DAOTComorbidade();
	private ArrayList<MTComorbidade> TListComorbidade = new ArrayList<>();

	private boolean registroCadastro = true;
	private int row;
	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private RoundJTextField edDescCom;
	private RoundJTextField edNomeCom;
	private JLabel lbNome;
	private JLabel lbDescricao;
	private RoundButton btnDelete;
	private RoundButton btnlimpar;
	private RoundButton btnConf;
	private JLabel lblNewLabel_3;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel_4;
	private TableSimples table;
	private JLabel lbStatus;

	/**
	 * Create the frame.
	 */
	public VComorbidadeCad() {
		BufferedImage bg = null;
		;
		try {
			bg = ImageIO.read(new File("src/vision/images/BGLogin.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setTitle("Cadastro de Comorbidade");
		TListComorbidade = FDAOTComorbidade.ListTComorbidade(FDAOTComorbidade);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 710, 830);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setBackground(new Color(158, 174, 255));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[150px][700px,grow][150px]", "[50px][600px,grow][50px]"));
		
		panel_1 = new PanelComBackgroundImage(bg);
		panel_1.setBackground(new Color(158, 174, 255));
		panel.add(panel_1, "cell 1 1,alignx center");
		panel_1.setLayout(new MigLayout("", "[350px,grow]", "[600px,grow]"));
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(125, 137, 245));
		panel_1.add(panel_2, "cell 0 0,grow");
		panel_2.setLayout(new MigLayout("", "[grow]", "[][50px,grow][280px,grow][50px,grow]"));
		
		lblNewLabel_4 = new JLabel("Cadastro de Comorbidade");
		panel_2.add(lblNewLabel_4, "cell 0 0,alignx center");
		lblNewLabel_4.setForeground(new Color(0, 0, 0));
		lblNewLabel_4.setFont(new Font("Dialog", Font.BOLD, 20));
		
		panel_4 = new JPanel();
		panel_4.setBackground(new Color(125, 137, 245));
		panel_2.add(panel_4, "cell 0 1,growx,aligny top");
		panel_4.setLayout(new MigLayout("", "[100px][100px][100px]", "[10px][100px]"));
		
		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(VComorbidadeCad.class.getResource("/vision/images/simboloV.png")));
		panel_4.add(lblNewLabel_3, "cell 1 1,alignx center");
		
		panel_5 = new JPanel();
		panel_5.setBackground(new Color(125, 137, 245));
		panel_2.add(panel_5, "cell 0 2,grow");
		panel_5.setLayout(new MigLayout("", "[grow]", "[][][][][][350px][50px]"));
		
		lbNome = new JLabel("Nome:");
		lbNome.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		panel_5.add(lbNome, "flowy,cell 0 1");
		
		edNomeCom = new RoundJTextField();
		edNomeCom.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		edNomeCom.setColumns(10);
		panel_5.add(edNomeCom, "cell 0 1,growx");
		
		lbDescricao = new JLabel("Descrição:");
		lbDescricao.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		panel_5.add(lbDescricao, "flowy,cell 0 3");
		
		edDescCom = new RoundJTextField();
		edDescCom.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		edDescCom.setColumns(10);
		panel_5.add(edDescCom, "cell 0 3,growx");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_5.add(scrollPane_1, "cell 0 5,growy");
		
		table = new TableSimples(new Object[][] {}, new String[] { "Id", "Comorbidade", "Descrição" });
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
				table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						row = table.getSelectedRow();
						if (row >= 0) {
							String nome = table.getValueAt(row, 1).toString();
							String desc = table.getValueAt(row, 2).toString();
							edNomeCom.setText(nome);
							edDescCom.setText(desc);
		
							registroCadastro = false;
		
							FDAOTComorbidade.setBDIDCOMORBIDADE(Integer.valueOf(table.getValueAt(row, 0).toString()));
		
							lbStatus.setText("Status: Alterando comorbidade");
						}
					}
				});
				scrollPane_1.setViewportView(table);
		
		lbStatus = new JLabel("Status: Inserindo Comorbidade");
		lbStatus.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		panel_5.add(lbStatus, "cell 0 6");
		
		panel_6 = new JPanel();
		panel_6.setBackground(new Color(125, 137, 245));
		panel_2.add(panel_6, "cell 0 3,grow");
		panel_6.setLayout(new MigLayout("", "[100px][][100px][100px][100px][100px][100px]", "[][][][]"));
		
		btnConf = new RoundButton("Comfirmar"
				+ "");
		btnConf.setText("Confirmar");
		btnConf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventConfirmar();
				table.clearSelection();
			}
		});
		btnConf.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		panel_6.add(btnConf, "cell 1 1");
		
		btnlimpar = new RoundButton("Limpar");
		btnlimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparDados();
				table.clearSelection();
			}
		});
		btnlimpar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		panel_6.add(btnlimpar, "cell 3 1");
		
		btnDelete = new RoundButton("Deletar");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = FDAOTComorbidade.getBDIDCOMORBIDADE();
				eventExcluir(id);
				table.clearSelection();
			}
		});
		btnDelete.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		panel_6.add(btnDelete, "cell 5 1");

		atualizatabela();
	}

	private void atualizatabela() {
		TListComorbidade = FDAOTComorbidade.ListTComorbidade(FDAOTComorbidade);

		for (MTComorbidade com : TListComorbidade) {
			Object[][] rowData = {{com.getBDIDCOMORBIDADE(), com.getBDNOMECOMORBIDADE(), com.getBDDESCCOMORBIDADE() }};
			table.preencherTabela(rowData);	
		}	
		
	}

	private void limparDados() {
		edDescCom.setText("");
		edNomeCom.setText("");

		registroCadastro = true;

		edNomeCom.requestFocus();
		lbStatus.setText("Status: Inserindo medicamento");
	}

	public void eventConfirmar() {
		
		if (edNomeCom.getText() == null || edDescCom.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Campo vazio: Comobirdade", "Atenção", 0);
			edNomeCom.requestFocus();
			return;
		}
		
		FDAOTComorbidade.setBDDESCCOMORBIDADE(edDescCom.getText());
		FDAOTComorbidade.setBDNOMECOMORBIDADE(edNomeCom.getText());

		if (registroCadastro == true) {
			FDAOTComorbidade.setBDIDCOMORBIDADE(FDAOTComorbidade.getChaveID("tcomorbidade", "BDIDCOMORBIDADE"));
			FDAOTComorbidade.inserir(FDAOTComorbidade);
		} else {
			FDAOTComorbidade.setBDIDCOMORBIDADE(FDAOTComorbidade.getBDIDCOMORBIDADE());
			FDAOTComorbidade.alterar(FDAOTComorbidade);
		}
		table.limparTabela();
		atualizatabela();
		limparDados();
	}

	public void eventExcluir(Integer prID) {
		int resposta = JOptionPane.showConfirmDialog(null,
				"Ao Deletar esta comorbidade, você não vai mais pode utiliza-la.",
				"Atenção!", JOptionPane.YES_NO_OPTION);

		if (resposta == JOptionPane.YES_NO_OPTION) {
			FDAOTComorbidade.deletar(prID);

			table.limparTabela();
			atualizatabela();
			limparDados();

			JOptionPane.showInternalMessageDialog(null, "Excluido com sucesso!");
		}else {
			
			JOptionPane.showInternalMessageDialog(null, "Comorbidade não foi Excluida!");
			
		}
	}

}
