package vision.consultas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import control.DAOComorbidade;
import model.Comorbidade;
import model.interfaces.InterComorbidade;
import vision.padrao.PanelComBackgroundImage;
import vision.padrao.RoundButton;
import vision.padrao.RoundJTextField;
import vision.padrao.TableSimples;
import vision.padrao.Util;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTable;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;

public class ComorbidadeCON extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public DAOComorbidade FDAOTComorbidade = new DAOComorbidade();
	private ArrayList<Comorbidade> TListComorbidade = new ArrayList<>();
	private JPanel contentPane;
	private TableSimples table;
	private RoundJTextField edFiltro;

	private RoundButton btnConfirmar;
	private RoundButton btnExcluir;
	private RoundButton btnFiltro;

	public ComorbidadeCON(List<Comorbidade> dados ,InterComorbidade event) {
setIconImage(Toolkit.getDefaultToolkit().getImage(Util.getCaminhoIMG("logo.png")));
		
		BufferedImage bg = null;
		;
		try {
			bg = ImageIO.read(new File("src\\main\\resources\\BGLogin.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Util.getCaminhoIMG("logo.png")));
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		setTitle("Consulta de Comorbidade");
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setBackground(new Color(158, 174, 255));
		contentPane.setLayout(new MigLayout("", "[50px][550px,grow][50px]", "[50px][300px,grow][50px]"));
		
		JPanel panelBackground = new PanelComBackgroundImage(bg);
		panelBackground.setBackground(new Color(158, 174, 255));
		contentPane.add(panelBackground, "cell 1 1,alignx center");
		panelBackground.setLayout(new MigLayout("", "[grow]", "[250px,grow][grow][grow]"));
		
		JPanel panelTabela = new JPanel();
		panelTabela.setBackground(new Color(125, 137, 245));
		panelBackground.add(panelTabela, "cell 0 0,grow");
		panelTabela.setLayout(new MigLayout("", "[grow]", "[][450px,grow]"));
		
		JLabel lblNewLabel = new JLabel("Consulta Comorbidade");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		panelTabela.add(lblNewLabel, "cell 0 0,alignx center");
		
		JScrollPane scrollPane = new JScrollPane();
		panelTabela.add(scrollPane, "cell 0 1,grow");
		
		table = new TableSimples(new Object[][] {}, new String[] { "Id", "Comorbidade", "Descrição" });
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		atualizarTabela(dados, false);
		scrollPane.setViewportView(table);
		
		JPanel panelFiltro = new JPanel();
		panelFiltro.setBackground(new Color(125, 137, 245));
		panelBackground.add(panelFiltro, "cell 0 1,grow");
		panelFiltro.setLayout(new MigLayout("", "[100px][][100px,grow][][][100px]", "[]"));
		
		JLabel lblFiltro = new JLabel("Nome:");
		lblFiltro.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panelFiltro.add(lblFiltro, "cell 1 0");
		
		edFiltro = new RoundJTextField();
		edFiltro.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		panelFiltro.add(edFiltro, "cell 2 0,growx");
		edFiltro.setColumns(10);
		
		JButton btnFiltro = new RoundButton("New button");
		btnFiltro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(edFiltro.getText().isEmpty()) {
					atualizarTabela(dados, false);
				}else {
					atualizarTabela(dados, true);	
				}
				table.setRowSelectionInterval(0, 0);
			}
		});
		btnFiltro.setText("Filtro");
		btnFiltro.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		panelFiltro.add(btnFiltro, "cell 4 0");
		
		JPanel panelBotoes = new JPanel();
		panelBotoes.setBackground(new Color(125, 137, 245));
		panelBackground.add(panelBotoes, "cell 0 2,grow");
		panelBotoes.setLayout(new MigLayout("", "[160px][100px][40px][100px][100px]", "[]"));
		
		JButton btnConfirmar = new RoundButton("Confirmar");
		btnConfirmar.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btnConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = table.getSelectedRows();
				for (int i = 0; i < selectedRows.length; i++) {
					int modelIndex = table.convertRowIndexToModel(selectedRows[i]);	
					Comorbidade dado = dados.get(modelIndex);
					event.preencheCom(dado);
					dispose();
				}
				table.setCellSelectionEnabled(true);
			}
		});
		panelBotoes.add(btnConfirmar, "cell 1 0,growx");
		
		JButton btnExcluir = new RoundButton("Excluir");
		btnExcluir.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		panelBotoes.add(btnExcluir, "cell 3 0,growx");
	}
	
	public void atualizarTabela(List<Comorbidade> com, Boolean prFiltro) {
		

		for (Comorbidade comorbidade: com) {
			if (prFiltro && !comorbidade.getBDNOMECOMORBIDADE().toLowerCase().contains(edFiltro.getText().toLowerCase())) {
				continue;
			}
			Object[][] rowData = {{ comorbidade.getBDIDCOMORBIDADE(), comorbidade.getBDNOMECOMORBIDADE(), comorbidade.getBDDESCCOMORBIDADE() }};
			table.preencherTabela(rowData);	
		}
	}
	
}
