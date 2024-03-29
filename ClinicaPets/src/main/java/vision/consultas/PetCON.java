package vision.consultas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
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
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import model.Pet;
import model.interfaces.InterPet;
import net.miginfocom.swing.MigLayout;
import vision.padrao.PanelComBackgroundImage;
import vision.padrao.RoundButton;
import vision.padrao.RoundJTextField;
import vision.padrao.TableSimples;
import vision.padrao.Util;

public class PetCON extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelBackground;
	private JPanel panelBotoes;
	private JPanel panelFiltro;
	private JPanel panelTabela;
	private JLabel lblTable;
	private TableSimples table;
	private JLabel lblNome;
	private JTextField edFiltro;
	private RoundButton btnFiltro;
	private RoundButton bntExc;
	private RoundButton btnConf;

	/**
	 * Create the frame.
	 */
	public PetCON(List<Pet> dados, InterPet ev) {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Util.getCaminhoIMG("logo.png")));
		
		setTitle("Consulta de Pet");
		BufferedImage bg = null;
		;
		try {
			bg = ImageIO.read(new File(Util.getCaminhoIMG("BGLogin.png")));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 623, 488);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(158, 174, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[50px][480px,grow][50px]", "[50px][370px,grow][50px]"));

		panelBackground = new PanelComBackgroundImage(bg);
		panelBackground.setBackground(new Color(158, 174, 255));
		contentPane.add(panelBackground, "cell 1 1,alignx center");
		panelBackground.setLayout(new MigLayout("", "[grow]", "[310px,grow][grow][grow]"));

		panelTabela = new JPanel();
		panelTabela.setBackground(new Color(125, 137, 245));
		panelBackground.add(panelTabela, "cell 0 0,grow");
		panelTabela.setLayout(new MigLayout("", "[20px][250px,grow][20px]", "[][250px,grow]"));

		lblTable = new JLabel("Consulta Pets");
		lblTable.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		panelTabela.add(lblTable, "cell 1 0,alignx center");

		JScrollPane scrollPane_1 = new JScrollPane();
		panelTabela.add(scrollPane_1, "cell 1 1");
		scrollPane_1.setBounds(0, 0, 434, 189);

		table = new TableSimples(new Object[][] {}, new String[] { "Id", "Dono(a)", "Raça", "Nome", "Apelido" });
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(table);
		atualizarTabela(dados, false);

		panelFiltro = new JPanel();
		panelFiltro.setBackground(new Color(125, 137, 245));
		panelBackground.add(panelFiltro, "cell 0 1,grow");
		panelFiltro.setLayout(new MigLayout("", "[100px][][100px,grow][][][100px]", "[][]"));

		btnFiltro = new RoundButton("Filtro");
		btnFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (edFiltro.getText().isEmpty()) {
					atualizarTabela(dados, false);
				} else {
					atualizarTabela(dados, true);
				}
			}
		});
		btnFiltro.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		panelFiltro.add(btnFiltro, "cell 4 0,growx");

		lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panelFiltro.add(lblNome, "cell 1 0,alignx trailing");

		edFiltro = new RoundJTextField();
		edFiltro.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		panelFiltro.add(edFiltro, "cell 2 0,growx");
		edFiltro.setColumns(10);

		panelBotoes = new JPanel();
		panelBotoes.setBackground(new Color(125, 137, 245));
		panelBackground.add(panelBotoes, "cell 0 2,grow");
		panelBotoes.setLayout(new MigLayout("", "[160px][100px][40px][100px][100px]", "[][]"));

		bntExc = new RoundButton("Excluir");
		bntExc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = table.getSelectedRows();
				for (int i = 0; i < selectedRows.length; i++) {
					int modelIndex = table.convertRowIndexToModel(selectedRows[i]);
					Pet dado = dados.get(modelIndex);
					ev.exluiPet(dado.getBDIDPET());
				}
			}
		});
		bntExc.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		panelBotoes.add(bntExc, "cell 3 0,growx");

		btnConf = new RoundButton("Confirmar");
		btnConf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = table.getSelectedRows();
				for (int i = 0; i < selectedRows.length; i++) {
					int id = Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString());
					int modelIndex = table.convertRowIndexToModel(selectedRows[i]);
					Pet dado = dados.get(modelIndex);
					ev.preencheDadosPet(dado);
					dispose();
				}
			}
		});
		btnConf.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		panelBotoes.add(btnConf, "cell 1 0,growx");

	}

	private String achaApelido(String apelido) {
		if (apelido == null || apelido.length() == 0) {
			return "(Sem apelido)";
		}
		return apelido;
	}

	public void desBotoes() {
		btnConf.setVisible(false);
		bntExc.setVisible(false);
	}

	public void desExcluir() {
		bntExc.setVisible(false);
	}

	public void atualizarTabela(List<Pet> pets, Boolean prFiltro) {

		table.limparTabela();

		for (Pet pet : pets) {
			if (prFiltro && !pet.getBDNOMEUSER().toLowerCase().contains(edFiltro.getText().toLowerCase())) {
				continue;
			}
			Object[][] rowData = { { pet.getBDIDPET(), pet.getBDNOMEUSER(), pet.getBDNOMERACA(), pet.getBDNOMEPET(),
					achaApelido(pet.getBDAPELIDO()) } };
			table.preencherTabela(rowData);
		}
	}

}
