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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import control.DAOAgendamento;
import control.DAODadosUser;
import model.Agendamento;
import model.DadosUser;
import model.interfaces.InterAgendamento;
import vision.padrao.DateTextField;
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

public class AgendamentoCON extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public DAOAgendamento FDAOTAgendamento = new DAOAgendamento();
	private JPanel contentPane;
	private ArrayList<Agendamento> dados;
	
	private DAODadosUser user = new DAODadosUser();
	private ArrayList<DadosUser> listUser = user.ListTDadosUser(user);
	private TableSimples table;
	private RoundButton btnConfirmar;
	private RoundButton btnExcluir;
	private RoundButton btnFiltro;
	private DateTextField edData;
	
	public AgendamentoCON(ArrayList<Agendamento> dadosConstr, InterAgendamento event) {
		
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(Util.getCaminhoIMG("logo.png")));
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 451);
		setTitle("Consulta de Agendamento");
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(158, 174, 255));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[50px][525px,grow][50px]", "[50px][350px,grow][50px]"));
		
		JPanel panelBackground = new PanelComBackgroundImage(bg);
		panelBackground.setBackground(new Color(158, 174, 255));
		contentPane.add(panelBackground, "cell 1 1,alignx center");
		panelBackground.setLayout(new MigLayout("", "[grow]", "[250px,grow][grow][grow]"));
		
		JPanel panelTabela = new JPanel();
		panelTabela.setBackground(new Color(125, 137, 245));
		panelBackground.add(panelTabela, "cell 0 0,growx");
		panelTabela.setLayout(new MigLayout("", "[][grow][]", "[][450px,grow]"));
		
		JLabel lblNewLabel = new JLabel("Consulta Agendamento");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		panelTabela.add(lblNewLabel, "cell 1 0,alignx center");
		
		JScrollPane scrollPane = new JScrollPane();
		panelTabela.add(scrollPane, "cell 1 1,growx");
		
		table = new TableSimples(new Object[][] {}, new String[] { "Número", "Dono", "Pet", "Data", "Hora" });
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		JPanel panelFiltro = new JPanel();
		panelFiltro.setBackground(new Color(125, 137, 245));
		panelBackground.add(panelFiltro, "cell 0 1,grow");
		panelFiltro.setLayout(new MigLayout("", "[100px][][100px,grow][][][100px]", "[]"));
		
		JLabel lbFiltro = new JLabel("Data:");
		lbFiltro.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panelFiltro.add(lbFiltro, "cell 1 0");
		
		edData = new DateTextField();
		edData.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		panelFiltro.add(edData, "cell 2 0,growx");
		edData.setColumns(10);
		
		JButton btnFiltro = new RoundButton("Filtro");
		btnFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(edData.validaDate()) {
					dados = FDAOTAgendamento.ListCon(edData.getDate(), true);
				} else {
					dados = FDAOTAgendamento.ListCon(null, false);
				}
				
				atualizarTabela(dados);
			
				table.setRowSelectionInterval(0, 0);
			}	
		});
		btnFiltro.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		panelFiltro.add(btnFiltro, "cell 4 0");
		
		JPanel panelBotoes = new JPanel();
		panelBotoes.setBackground(new Color(125, 137, 245));
		panelBackground.add(panelBotoes, "cell 0 2,grow");
		panelBotoes.setLayout(new MigLayout("", "[160px][100px][40px][100px][100px]", "[]"));
		
		JButton btnConfirmar = new RoundButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = table.getSelectedRows();
				for (int i = 0; i < selectedRows.length; i++) {
					int modelIndex = table.convertRowIndexToModel(selectedRows[i]);	
					Agendamento dado = dados.get(modelIndex);
					event.preencheAge(dado);
					dispose();
				}
				table.setCellSelectionEnabled(true);
			}
		});
		btnConfirmar.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		panelBotoes.add(btnConfirmar, "cell 1 0,growx");
		
		JButton btnExcluir = new RoundButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = table.getSelectedRows();
				for (int i = 0; i < selectedRows.length; i++) {
					int modelIndex = table.convertRowIndexToModel(selectedRows[i]);	
					Agendamento dado = dados.get(modelIndex);
					event.excluiAge(dado.getId());
					dispose();
				}
			}
			
		});
		btnExcluir.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		panelBotoes.add(btnExcluir, "cell 3 0,growx");
		
		dados = dadosConstr;
		atualizarTabela(dados);
	}
	
	public void atualizarTabela(List<Agendamento> com) {
		DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		for (Agendamento agendamento: com) {			
			Object[][] rowData = {{ agendamento.getId(), getUser(agendamento.getBDIDUSER()), agendamento.getBDNOMEPET(), agendamento.getDateAgendamento().format(FOMATTER), agendamento.getHora() }};
			table.preencherTabela(rowData); 
		}
	}
	
	private String getUser(Integer prId) {
		for (DadosUser user : listUser) {
			if(user.getBDIDUSER() == prId) {
				return user.getBDNOMEUSER();
			}
		}
		return "Não encontrado";
	}
	
}
