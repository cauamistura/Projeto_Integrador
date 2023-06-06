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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import control.DAOAtendimentoEntrada;
import control.DAOAtendimentoSaida;
import model.AtendimentoSaida;
import model.AtenimentoEntrada;
import model.interfaces.InterEntrada;
import model.interfaces.InterSaida;
import vision.padrao.PanelComBackgroundImage;
import vision.padrao.RoundButton;
import vision.padrao.RoundJTextField;
import vision.padrao.TableSimples;
import vision.padrao.Util;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.Font;

public class EntradaCON extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DAOAtendimentoEntrada FDAOEntrada = new DAOAtendimentoEntrada();
	private DAOAtendimentoSaida FDAOSaida = new DAOAtendimentoSaida();
	private JPanel panelBackground;
	private JPanel panelBotoes;
	private JPanel panelFiltro;
	private JPanel panelTabela;
	private JLabel lblNewLabel;
	private JScrollPane scrollPane;
	private TableSimples table;
	private JLabel lblFiltro;
	private RoundJTextField edFiltro;
	private RoundButton btnFiltro;
	private RoundButton btnConfirmar;
	private RoundButton btnExcluir;

	public EntradaCON(List<AtenimentoEntrada> dados, InterEntrada inter, Boolean saida, InterSaida interS ) {
		
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
		setBounds(100, 100, 700, 420);
		setTitle("Consulta Antendimento - Entrada");
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setBackground(new Color(158, 174, 255));
		contentPane.setLayout(new MigLayout("", "[50px][600px,grow][50px]", "[50px][300px,grow][50px]"));
		
		panelBackground = new PanelComBackgroundImage(bg);
		panelBackground.setBackground(new Color(158, 174, 255));
		contentPane.add(panelBackground, "cell 1 1,alignx center");
		panelBackground.setLayout(new MigLayout("", "[grow]", "[250px,grow][grow][grow]"));
		
		panelTabela = new JPanel();
		panelTabela.setBackground(new Color(125, 137, 245));
		panelBackground.add(panelTabela, "cell 0 0,grow");
		panelTabela.setLayout(new MigLayout("", "[grow]", "[][400px,grow]"));
		
		lblNewLabel = new JLabel("Consulta Atendimento");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		panelTabela.add(lblNewLabel, "cell 0 0,alignx center");
		
		scrollPane = new JScrollPane();
		panelTabela.add(scrollPane, "cell 0 1,grow");
		
		table = new TableSimples(new Object[][] {}, new String[] { "Número ate.", "CPF", "Nome", "Data", "Nome Pet", "Espécie","Raça"});
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		atualizarTabela(dados, false);
		
		scrollPane.setViewportView(table);
		
		panelFiltro = new JPanel();
		panelFiltro.setBackground(new Color(125, 137, 245));
		panelBackground.add(panelFiltro, "cell 0 1,grow");
		panelFiltro.setLayout(new MigLayout("", "[100px][100px][100px,grow][100px][100px]", "[]"));
		
		btnFiltro = new RoundButton("Filtrar");
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
		btnFiltro.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		panelFiltro.add(btnFiltro, "cell 3 0,growx");
		
		lblFiltro = new JLabel("Nome:");
		lblFiltro.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panelFiltro.add(lblFiltro, "cell 1 0,alignx right");
		
		edFiltro = new RoundJTextField();
		edFiltro.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		panelFiltro.add(edFiltro, "cell 2 0,growx");
		edFiltro.setColumns(10);
		
		panelBotoes = new JPanel();
		panelBotoes.setBackground(new Color(125, 137, 245));
		panelBackground.add(panelBotoes, "cell 0 2,grow");
		panelBotoes.setLayout(new MigLayout("", "[220px][100px][60px][100px][120px]", "[]"));
		
		btnConfirmar = new RoundButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = table.getSelectedRows();
				for (int i = 0; i < selectedRows.length; i++) {
					int modelIndex = table.convertRowIndexToModel(selectedRows[i]);
					AtenimentoEntrada dado = dados.get(modelIndex);
					
					
					if (saida == true) {
						if(FDAOEntrada.retornaIdReceita(dado.getBDIDENTRADA())) {
							int resposta = JOptionPane.showConfirmDialog(null,
									"Entrada já possui uma saida! Deseja consultar as saidas?.",
									"Confirmação", JOptionPane.YES_NO_OPTION);

							if (resposta == JOptionPane.YES_OPTION) {
								
								ArrayList<AtendimentoSaida> list = new ArrayList<>();
								list = FDAOSaida.ListTSaida(FDAOSaida);
								
								
								SaidaCON v = new SaidaCON(list,interS);
								v.setVisible(true);
							}
							}else {
								inter.preencheDadosEntrada(dado);
						}
					}else {
						inter.preencheDadosEntrada(dado);
					}
					dispose();
				}
			}
		});
		btnConfirmar.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		panelBotoes.add(btnConfirmar, "cell 1 0,growx");
		
		btnExcluir = new RoundButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = table.getSelectedRows();
				for (int i = 0; i < selectedRows.length; i++) {
					int modelIndex = table.convertRowIndexToModel(selectedRows[i]);
					AtenimentoEntrada dado = dados.get(modelIndex);
					inter.exluirAtendimento(dado.getBDIDENTRADA());
					dispose();
				}
			}
		});
		btnExcluir.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		panelBotoes.add(btnExcluir, "cell 3 0,growx");
		
	}

	public void atualizarTabela(List<AtenimentoEntrada> dados, Boolean prFiltro) {
		DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		for (AtenimentoEntrada dado : dados) {
			if (prFiltro && !dado.getBDNOMEUSER().toLowerCase().contains(btnFiltro.getText().toLowerCase())) {
				continue;
			}
			Object[][] rowData = {{ dado.getBDIDENTRADA(), dado.getBDCPF(), dado.getBDNOMEUSER(), dado.getBDDATAENT().format(FOMATTER), dado.getBDNOMEPET(), dado.getBDNOMEESPECIE(), dado.getBDNOMERACA()}};
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

