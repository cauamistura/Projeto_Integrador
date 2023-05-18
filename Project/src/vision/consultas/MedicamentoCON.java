package vision.consultas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import control.DAOMedicacao;
import model.Medicamento;
import model.interfaces.InterMedicamento;
import net.miginfocom.swing.MigLayout;
import vision.padrao.PanelComBackgroundImage;
import vision.padrao.RoundButton;
import vision.padrao.RoundJTextField;
import vision.padrao.TableSimples;

public class MedicamentoCON extends JFrame {

	private static final long serialVersionUID = 1L;

	public DAOMedicacao FDAOTMedicacao = new DAOMedicacao();
	private ArrayList<Medicamento> TListMedicacao = new ArrayList<>();
	private JPanel contentPane;
	private JPanel panelBackground;
	private JPanel panelBotoes;
	private JPanel panelTabela;
	private JPanel panelFiltro;
	private JLabel lblFiltro;
	private JButton btnFiltro;
	private RoundJTextField edFiltro;
	private RoundButton btnComfirmar;
	private RoundButton btnExcluir;
	private JLabel lblNewLabel_1;
	private TableSimples table;

	public MedicamentoCON(List<Medicamento> dados, InterMedicamento event) {
		BufferedImage bg = null;
		;
		try {
			bg = ImageIO.read(new File("src/vision/images/BGLogin.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 647, 454);
		setTitle("Consulta de Medicamento");
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(158, 174, 255));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[50px][480px,grow][50px]", "[50px][350px,grow][50px]"));

		panelBackground = new PanelComBackgroundImage(bg);
		panelBackground.setBackground(new Color(158, 174, 255));
		contentPane.add(panelBackground, "cell 1 1,alignx center");
		panelBackground.setLayout(new MigLayout("", "[grow]", "[250px,grow][grow][grow]"));

		panelTabela = new JPanel();
		panelTabela.setBackground(new Color(125, 137, 245));
		panelBackground.add(panelTabela, "cell 0 0,grow");
		panelTabela.setLayout(new MigLayout("", "[][grow][]", "[][450px,grow]"));

		lblNewLabel_1 = new JLabel("Consulta Medicamento");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		panelTabela.add(lblNewLabel_1, "cell 1 0,alignx center");

		JScrollPane scrollPane = new JScrollPane();
		panelTabela.add(scrollPane, "cell 1 1,grow");

		table = new TableSimples(new Object[][] {}, new String[] { "Id", "Medicamento", "Descrição" });
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		atualizarTabela(dados, false);
		scrollPane.setViewportView(table);

		panelFiltro = new JPanel();
		panelFiltro.setBackground(new Color(125, 137, 245));
		panelBackground.add(panelFiltro, "cell 0 1,grow");
		panelFiltro.setLayout(new MigLayout("", "[100px][][100px,grow][][][100px]", "[]"));

		lblFiltro = new JLabel("Nome:");
		lblFiltro.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panelFiltro.add(lblFiltro, "cell 1 0,alignx trailing");

		edFiltro = new RoundJTextField();
		edFiltro.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		panelFiltro.add(edFiltro, "cell 2 0,growx");
		edFiltro.setColumns(10);

		btnFiltro = new RoundButton("Filtro");
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
		btnFiltro.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		panelFiltro.add(btnFiltro, "cell 4 0");

		panelBotoes = new JPanel();
		panelBotoes.setBackground(new Color(125, 137, 245));
		panelBackground.add(panelBotoes, "cell 0 2,grow");
		panelBotoes.setLayout(new MigLayout("", "[160px][100px][40px][100px][100px]", "[]"));

		btnComfirmar = new RoundButton("Confirmar");
		btnComfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = table.getSelectedRows();
				for (int i = 0; i < selectedRows.length; i++) {
					int modelIndex = table.convertRowIndexToModel(selectedRows[i]);
					Medicamento dado = dados.get(modelIndex);
					event.preencheMedicamento(dado);
					dispose();
				}
				table.setCellSelectionEnabled(true);
			}
		});
		btnComfirmar.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		panelBotoes.add(btnComfirmar, "cell 1 0,growx");

		btnExcluir = new RoundButton("Excluir");
		btnExcluir.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		panelBotoes.add(btnExcluir, "cell 3 0,growx");
	}

	public void atualizarTabela(List<Medicamento> med, Boolean prFiltro) {
		table.limparTabela();

		for (Medicamento medicamento : med) {
			if (prFiltro
					&& !medicamento.getBDNOMEMEDICACAO().toLowerCase().contains(edFiltro.getText().toLowerCase())) {
				continue;
			}
			Object[][] rowData = { { medicamento.getBDIDMEDICACAO(), medicamento.getBDNOMEMEDICACAO(),
					medicamento.getBDDESCRICAO() } };
			table.preencherTabela(rowData);
		}
	}

}
