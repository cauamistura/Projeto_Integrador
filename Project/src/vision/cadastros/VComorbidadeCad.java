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
import model.MTMedicacao;
import net.miginfocom.swing.MigLayout;
import vision.padrao.PanelComBackgroundImage;
import vision.padrao.RoundButton;
import vision.padrao.RoundJTextField;
import vision.padrao.TableSimples;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

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
	private JPanel pai;
	private JPanel area;
	private JPanel card;
	private JPanel img;
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
		setBounds(100, 100, 511, 799);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		pai = new JPanel();
		pai.setBackground(new Color(158, 174, 255));
		contentPane.add(pai, BorderLayout.CENTER);
		pai.setLayout(new MigLayout("", "[100px]", "[100px][600px,grow][100px]"));
																																																																																																																																																																																																																																		
																																																																																																																																																																																																																																				area = new PanelComBackgroundImage(bg);
																																																																																																																																																																																																																																				area.setBackground(new Color(158, 174, 255));
																																																																																																																																																																																																																																				pai.add(area, "cell 0 1,alignx center");
																																																																																																																																																																																																																																				GridBagLayout gbl_area = new GridBagLayout();
																																																																																																																																																																																																																																				gbl_area.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
																																																																																																																																																																																																																																				gbl_area.rowHeights = new int[] { 562, 0 };
																																																																																																																																																																																																																																				gbl_area.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
																																																																																																																																																																																																																																				gbl_area.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
																																																																																																																																																																																																																																				area.setLayout(gbl_area);
																																																																																																																																																																																																																																				
																																																																																																																																																																																																																																						card = new JPanel();
																																																																																																																																																																																																																																						card.setBackground(new Color(125, 137, 245));
																																																																																																																																																																																																																																						GridBagConstraints gbc_card = new GridBagConstraints();
																																																																																																																																																																																																																																						gbc_card.insets = new Insets(0, 0, 0, 5);
																																																																																																																																																																																																																																						gbc_card.fill = GridBagConstraints.VERTICAL;
																																																																																																																																																																																																																																						gbc_card.gridx = 3;
																																																																																																																																																																																																																																						gbc_card.gridy = 0;
																																																																																																																																																																																																																																						area.add(card, gbc_card);
																																																																																																																																																																																																																																						card.setLayout(new MigLayout("", "[grow]", "[][80px,grow][110px,grow][][80px,grow]"));
																																																																																																																																																																																																																																						
																																																																																																																																																																																																																																								img = new JPanel();
																																																																																																																																																																																																																																								img.setBackground(new Color(125, 137, 245));
																																																																																																																																																																																																																																								card.add(img, "cell 0 0,grow");
																																																																																																																																																																																																																																								img.setLayout(new MigLayout("", "[100px][100px][100px]", "[10px][100px]"));
																																																																																																																																																																																																																																								
																																																																																																																																																																																																																																										lblNewLabel_3 = new JLabel("");
																																																																																																																																																																																																																																										lblNewLabel_3.setIcon(new ImageIcon(VComorbidadeCad.class.getResource("/vision/images/simboloV.png")));
																																																																																																																																																																																																																																										img.add(lblNewLabel_3, "cell 1 1,alignx center");
																																																																																																																																																																																																																																										
																																																																																																																																																																																																																																												panel_5 = new JPanel();
																																																																																																																																																																																																																																												panel_5.setBackground(new Color(125, 137, 245));
																																																																																																																																																																																																																																												card.add(panel_5, "cell 0 2,grow");
																																																																																																																																																																																																																																												panel_5.setLayout(new MigLayout("", "[grow]", "[][][][][][50px]"));
																																																																																																																																																																																																																																												
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
																																																																																																																																																																																																																																																				
																																																																																																																																																																																																																																																						lbStatus = new JLabel("Status: Inserindo Comorbidade");
																																																																																																																																																																																																																																																						lbStatus.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
																																																																																																																																																																																																																																																						panel_5.add(lbStatus, "cell 0 5");
																																																																																																																																																																																																																																																						
																																																																																																																																																																																																																																																								JScrollPane scrollPane_1 = new JScrollPane();
																																																																																																																																																																																																																																																								card.add(scrollPane_1, "cell 0 3");
																																																																																																																																																																																																																																																								
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
																																																																																																																																																																																																																																																												
																																																																																																																																																																																																																																																														panel_6 = new JPanel();
																																																																																																																																																																																																																																																														panel_6.setBackground(new Color(125, 137, 245));
																																																																																																																																																																																																																																																														card.add(panel_6, "cell 0 4,grow");
																																																																																																																																																																																																																																																														panel_6.setLayout(new MigLayout("", "[100px][][100px][100px][100px][100px][100px]", "[][][][]"));
																																																																																																																																																																																																																																																														
																																																																																																																																																																																																																																																																btnConf = new RoundButton("Comfirmar" + "");
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
			Object[][] rowData = {
					{ com.getBDIDCOMORBIDADE(), com.getBDNOMECOMORBIDADE(), com.getBDDESCCOMORBIDADE() } };
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

		if (edNomeCom.getText() == null || edDescCom.getText().isEmpty()) {
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
				"Ao Deletar esta comorbidade, você não vai mais pode utiliza-la.", "Atenção!",
				JOptionPane.YES_NO_OPTION);

		if (resposta == JOptionPane.YES_NO_OPTION) {
			FDAOTComorbidade.deletar(prID);

			table.limparTabela();
			atualizatabela();
			limparDados();

			JOptionPane.showInternalMessageDialog(null, "Excluido com sucesso!");
		} else {

			JOptionPane.showInternalMessageDialog(null, "Comorbidade não foi Excluida!");

		}
	}

}
