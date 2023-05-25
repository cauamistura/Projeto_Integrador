package vision.consultas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import model.DadosUser;
import model.interfaces.InterUsuario;
import net.miginfocom.swing.MigLayout;
import vision.padrao.PanelComBackgroundImage;
import vision.padrao.RoundButton;
import vision.padrao.RoundJTextField;
import vision.padrao.TableSimples;

public class UserCON extends JFrame {

	private static final long serialVersionUID = 1L;
//	private DefaultTableModel model;
	private JPanel contentPane;
	private JPanel panelBackgroud;
	private JPanel panelFiltro;
	private JPanel panelBotoes;
	private JPanel panelTabela;
	private JLabel lbFiltro;
	private TableSimples table;
	private RoundJTextField edFiltro;
	private RoundButton btnFiltro;
	private RoundButton btnConfirmar;
	private RoundButton btnExcluir;
	private JLabel lblNewLabel_1;
//	private JScrollPane scrollPane;
	public UserCON(List<DadosUser> dados, InterUsuario inter) {
		BufferedImage bg = null;
		;
		try {
			bg = ImageIO.read(new File("src\\main\\resources\\BGLogin.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 643, 477);
		setTitle("Consulta de Usuario");
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(158, 174, 255));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[50px][480px,grow][50px]", "[50px][350px,grow][50px]"));
		
		panelBackgroud = new PanelComBackgroundImage(bg);
		panelBackgroud.setBackground(new Color(158, 174, 255));
		contentPane.add(panelBackgroud, "cell 1 1,alignx center");
		panelBackgroud.setLayout(new MigLayout("", "[480px,grow]", "[300px,grow][grow][]"));
		
		panelTabela = new JPanel();
		panelTabela.setBackground(new Color(125, 137, 245));
		panelBackgroud.add(panelTabela, "cell 0 0,grow");
		panelTabela.setLayout(new MigLayout("", "[][250px,grow][]", "[][200px,grow]"));
		
		lblNewLabel_1 = new JLabel("Consulta Usuario");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		panelTabela.add(lblNewLabel_1, "cell 1 0,alignx center");
		
		JScrollPane scrollPane = new JScrollPane();

		table = new TableSimples(new Object[][] {}, new String[] { "CPF", "Nome", "Email" });
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		atualizarTabela(dados, false);
		scrollPane.setViewportView(table);
		panelTabela.add(scrollPane, "cell 1 1,grow");

		panelFiltro = new JPanel();
		panelFiltro.setBackground(new Color(125, 137, 245));
		panelBackgroud.add(panelFiltro, "cell 0 1,grow");
		panelFiltro.setLayout(new MigLayout("", "[100px][][100px,grow][][][100px]", "[]"));
		
		btnFiltro =  new RoundButton("Filtro");
		btnFiltro.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btnFiltro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (edFiltro.getText().isEmpty()) {
					atualizarTabela(dados, false);
				} else {
					atualizarTabela(dados, true);
				}
				
				table.setRowSelectionInterval(0, 0);
			}
		});
		panelFiltro.add(btnFiltro, "cell 4 0,growx");
		
		lbFiltro = new JLabel("Nome:");
		lbFiltro.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panelFiltro.add(lbFiltro, "cell 1 0,alignx trailing");
		
		edFiltro = new RoundJTextField();
		edFiltro.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		panelFiltro.add(edFiltro, "cell 2 0,growx");
		edFiltro.setColumns(10);
		
		panelBotoes = new JPanel();
		panelBotoes.setBackground(new Color(125, 137, 245));
		panelBackgroud.add(panelBotoes, "cell 0 2,grow");
		panelBotoes.setLayout(new MigLayout("", "[160px][100px][40px][100px][100px]", "[]"));
		
		btnConfirmar =  new RoundButton("Confirmar");
		btnConfirmar.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btnConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = table.getSelectedRows();
				for (int i = 0; i < selectedRows.length; i++) {
					int modelIndex = table.convertRowIndexToModel(selectedRows[i]);
					DadosUser dado = dados.get(modelIndex);
					inter.preencheDadosUser(dado);
					dispose();
				}
			}
		});
		panelBotoes.add(btnConfirmar, "cell 1 0,growx");
		
		btnExcluir =  new RoundButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = table.getSelectedRows();
				for (int i = 0; i < selectedRows.length; i++) {
					int modelIndex = table.convertRowIndexToModel(selectedRows[i]);
					DadosUser dado = dados.get(modelIndex);
					inter.exluiUser(dado.getBDIDUSER());
					dispose();
				}
			}
		});
		btnExcluir.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		panelBotoes.add(btnExcluir, "cell 3 0,growx");

	}

	public void atualizarTabela(List<DadosUser> usuarios, Boolean prFiltro) {

		table.limparTabela();
		
		for (DadosUser usuario : usuarios) {
			if (prFiltro && !usuario.getBDNOMEUSER().toLowerCase().contains(edFiltro.getText().toLowerCase())) {
				continue;
			}
			Object[][] rowData = {{ usuario.getBDCPF(), usuario.getBDNOMEUSER(), usuario.getBDMAIL()} };
			table.preencherTabela(rowData);
		}
	}
	
	public void desabilitaExcluir() {
		btnExcluir.setVisible(false);
	}
	
	public void desBotoes() {
		btnConfirmar.setVisible(false);
		btnExcluir.setVisible(false);
	}

}
