package vision.cadastros;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import control.DAOAgendamento;
import control.DAODadosUser;
import control.DAOPet;
import control.DAOUser;
import model.Agendamento;
import model.DadosUser;
import model.Pet;
import model.interfaces.InterAgendamento;
import model.interfaces.InterPet;
import model.interfaces.InterUsuario;
import net.miginfocom.swing.MigLayout;
import vision.consultas.AgendamentoCON;
import vision.consultas.PetCON;
import vision.consultas.UserCON;
import vision.padrao.CPFTextField;
import vision.padrao.DateTextField;
import vision.padrao.PanelComBackgroundImage;
import vision.padrao.RoundButton;
import vision.padrao.RoundJTextField;
import vision.padrao.RoundJTextFieldNum;
import vision.padrao.TableSimples;
import vision.padrao.Util;
import vision.padrao.lupaButton;

public class AgendamentoCAD extends JFrame implements InterUsuario, InterPet, InterAgendamento {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DAOAgendamento fDAOAgendamento = new DAOAgendamento();
	private ArrayList<Agendamento> Lista = new ArrayList<>();

	private TableSimples table;
	private JPanel contentPane;
	private JPanel panel;
	private JPanel container_card;
	private JPanel card;
	private JPanel container_content;
	private JPanel container_buttons;
	private JLabel lbTitle;
	private JLabel lbUser;
	private RoundButton btnConfirmar;
	private CPFTextField edCpf;
	private lupaButton btnConUser;
	private JLabel lblPet;
	private RoundJTextField edNomePet;
	private RoundJTextField edNomeUser;
	private RoundJTextField edNomeRaca;
	private lupaButton btnConPet;
	private RoundJTextFieldNum edNumAtendimento;
	private JLabel lblNmeroAgendamento;
	private DateTextField edData;
	private JLabel lbData;

	// Objetos do usuario
	private DAOUser FDAOTUser = new DAOUser();
	private DAODadosUser FDAOUserDados = new DAODadosUser();
	private UserCON FVUserCON;

	// Obejtos do Pet
	private ArrayList<Pet> listPet = new ArrayList<>();
	private DAOPet FDAOTPet = new DAOPet();
	private PetCON FVPetCON;

	private ArrayList<Agendamento> dados = new ArrayList<>();
	private RoundButton btnExcluir;

	/**
	 */
	public AgendamentoCAD() {
		BufferedImage bg = null;
		;
		try {
			bg = ImageIO.read(new File(Util.getCaminhoIMG("BGLogin.png")));

		} catch (IOException e) {
			e.printStackTrace();
		}

		setTitle("Agendamento");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 479, 706);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		panel.setBackground(new Color(158, 174, 255));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[150px][700px,grow][150px]", "[50px][600px,grow][50px]"));

		container_card = new PanelComBackgroundImage(bg);
		container_card.setBackground(new Color(158, 174, 255));
		panel.add(container_card, "cell 1 1,alignx center");
		container_card.setLayout(new MigLayout("", "[750.00px,grow 600]", "[600px,grow]"));

		card = new JPanel();
		card.setBackground(new Color(125, 137, 245));
		container_card.add(card, "cell 0 0,grow");
		card.setLayout(new MigLayout("", "[grow]", "[][0px,grow 0][280px,grow][10px,grow 10]"));

		lbTitle = new JLabel("Agendamento");
		card.add(lbTitle, "cell 0 0,alignx center");
		lbTitle.setForeground(new Color(0, 0, 0));
		lbTitle.setFont(new Font("Dialog", Font.BOLD, 20));

		container_content = new JPanel();
		container_content.setBackground(new Color(125, 137, 245));
		card.add(container_content, "cell 0 2,grow");
		container_content.setLayout(new MigLayout("", "[grow 600]", "[][][][][][][][][][][350px][50px]"));

		lblNmeroAgendamento = new JLabel("Número agendamento:");
		lblNmeroAgendamento.setHorizontalAlignment(SwingConstants.RIGHT);
		container_content.add(lblNmeroAgendamento, "flowx,cell 0 1");

		edNumAtendimento = new RoundJTextFieldNum(8);
		edNumAtendimento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F9) {
					chamaConAgendamento();
				}
			}
		});
		edNumAtendimento.setToolTipText("Aperte F9 para consultar.");
		edNumAtendimento.setColumns(10);
		container_content.add(edNumAtendimento, "cell 0 1");

		lbData = new JLabel("Data:");
		lbData.setHorizontalAlignment(SwingConstants.RIGHT);
		container_content.add(lbData, "flowx,cell 0 2");

		edData = new DateTextField();
		edData.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!edData.validaDate()) {
					JOptionPane.showMessageDialog(null, "Data invalida!\nInforme uma data valida!");
					edData.requestFocus();
					return;
				}
				atualizatabela(edData.getDate());
			}
		});
		edData.setColumns(10);
		container_content.add(edData, "cell 0 2");

		JScrollPane scrollPane_1 = new JScrollPane();
		container_content.add(scrollPane_1, "cell 0 10,grow");

		table = new TableSimples(new Object[][] {}, new String[] { "Hora", "Disponibilidade" });
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getColumnModel().getColumn(1).setPreferredWidth(800);
		scrollPane_1.setViewportView(table);

		lbUser = new JLabel("Usuario:");
		lbUser.setHorizontalAlignment(SwingConstants.RIGHT);
		lbUser.setFont(new Font("Tahoma", Font.PLAIN, 12));
		container_content.add(lbUser, "flowx,cell 0 5");

		edCpf = new CPFTextField();
		edCpf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F9) {
					chamaConUser();
				}
			}
		});
		edCpf.setToolTipText("Aperte F9 para consultar.");
		edCpf.setColumns(10);
		container_content.add(edCpf, "cell 0 5");

		btnConUser = new lupaButton("");
		btnConUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chamaConUser();
			}
		});
		container_content.add(btnConUser, "cell 0 5");

		edNomeUser = new RoundJTextField();
		edNomeUser.setEnabled(false);
		edNomeUser.setToolTipText("Aperte F9 para consultar.");
		edNomeUser.setColumns(10);
		container_content.add(edNomeUser, "cell 0 5");

		lblPet = new JLabel("Pet:");
		lblPet.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPet.setFont(new Font("Tahoma", Font.PLAIN, 12));
		container_content.add(lblPet, "flowx,cell 0 6");

		edNomePet = new RoundJTextField();
		edNomePet.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F9) {
					chamaConPet();
				}
			
				if (e.getKeyCode() == KeyEvent.VK_F4) {
					PetCAD self = new PetCAD();
					self.setVisible(true);
				}
			}
		});
		edNomePet.setToolTipText("Aperte F9 para consultar.");
		edNomePet.setColumns(10);
		container_content.add(edNomePet, "cell 0 6");

		btnConPet = new lupaButton("");
		btnConPet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chamaConPet();
			}
		});
		container_content.add(btnConPet, "cell 0 6");

		edNomeRaca = new RoundJTextField();
		edNomeRaca.setToolTipText("Aperte F9 para consultar.");
		edNomeRaca.setEnabled(false);
		edNomeRaca.setColumns(10);
		container_content.add(edNomeRaca, "cell 0 6");

		container_buttons = new JPanel();
		container_buttons.setBackground(new Color(125, 137, 245));
		card.add(container_buttons, "cell 0 3,grow");
		container_buttons.setLayout(new MigLayout("", "[100px][][100px][][100px][][][100px][100px][100px]", "[][][][]"));
		
				btnConfirmar = new RoundButton("Limpar");
				btnConfirmar.setText("Confirmar");
				btnConfirmar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						actionConfirm();
					}
				});
				btnConfirmar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
				container_buttons.add(btnConfirmar, "cell 3 0");
		
		btnExcluir = new RoundButton("Limpar");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteAction(edNumAtendimento.getNum());
			}
		});
		btnExcluir.setText("Excluir");
		btnExcluir.setFont(new Font("Dialog", Font.BOLD, 14));
		container_buttons.add(btnExcluir, "cell 7 0");

	}

	private void atualizatabela(LocalDate date) {
		fDAOAgendamento.setDateAgendamento(date);
		Lista = fDAOAgendamento.List(fDAOAgendamento);
		
		dados.clear();
		table.limparTabela();
		
		Boolean valida = false;
		for (int hora = 8; hora < 19; hora++) {
			for (int minuto = 0; minuto < 60; minuto += 30) {
				valida = false;
				String horaAtual = "";
				if (hora < 10) {
					horaAtual = '0' + String.valueOf(hora) + ':' + String.valueOf(minuto);
				} else {
					horaAtual = String.valueOf(hora) + ':' + String.valueOf(minuto);
				}
				if (minuto < 30) {
					horaAtual += "0";
				}

				for (Agendamento agendamento : Lista) {
					if (agendamento.getHora().equals(horaAtual)) {
						Agendamento lc = new Agendamento();
						lc.setDisponivel(false);
						lc.setHora(horaAtual);
						dados.add(lc);
						valida = true;
					}
				}
				if (!valida) {
					Agendamento lc = new Agendamento();
					lc.setDisponivel(true);
					lc.setHora(horaAtual);
					dados.add(lc);
				}
			}
		}

		for (Agendamento lis : dados) {
			Object[][] rowData = { { lis.getHora(), descDisp(lis.getDisponivel()) } };
			table.preencherTabela(rowData);
		}		
	}

	private String descDisp(Boolean dis) {
		String result = dis ? "Disponível" : "Indisponível";
		return result;
	}

	private void chamaConUser() {
		ArrayList<DadosUser> list = new ArrayList<>();
		list = FDAOUserDados.ListConsulta(FDAOUserDados);

		FVUserCON = new UserCON(list, this);
		FVUserCON.desabilitaExcluir();
		FVUserCON.setVisible(true);
	}

	private void chamaConPet() {
		if (!edCpf.existeCpfUsuario(FDAOTUser)) {
			JOptionPane.showInternalMessageDialog(null,
					"Usuário informado não existe!\nInforme um usuário valido ou aperte F4 para cadastrar.");
			edCpf.requestFocus();
			return;
		}
		FDAOTUser.setBDCPF(edCpf.getText());
		FDAOTPet.setBDIDUSER(FDAOTUser.getIDUser(FDAOTUser));
		listPet = FDAOTPet.listTPetFiltradoUser(FDAOTPet);

		if (listPet.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Este usuario não tem Pet(s) cadastrados!\nAperte F4 para cadastrar.");
			return;
		}
		FVPetCON = new PetCON(listPet, this);

		FVPetCON.desExcluir();
		FVPetCON.setVisible(true);
	}
	
	private void chamaConAgendamento() {
		ArrayList<Agendamento> list = fDAOAgendamento.ListCon(edData.getDate(), false);
		AgendamentoCON self = new AgendamentoCON(list, this);
		self.setVisible(true);
	}

	private void actionConfirm() {
		Boolean editar = false;

		if (!edNumAtendimento.getText().isEmpty()) {
			if (fDAOAgendamento.existeAgendamento(Integer.valueOf(edNumAtendimento.getText()))) {
				fDAOAgendamento.setId(Integer.valueOf(edNumAtendimento.getText()));
				editar = true;
			}
		} else {
			fDAOAgendamento.setId(fDAOAgendamento.getChaveID("tagendamento", "BDIDAGENDAMENTO"));
		}

		if (!edData.validaDate()) {
			JOptionPane.showMessageDialog(null, "Data invalida\nPor favor corrija para concluir!");
			return;
		}

		if (fDAOAgendamento.getBDIDPET() == null) {
			JOptionPane.showMessageDialog(null, "Pet invalido, consulte e tente novamente!");
			return;
		}

		fDAOAgendamento.setDateAgendamento(edData.getDate());
		fDAOAgendamento.setBDIDPET(fDAOAgendamento.getBDIDPET());

		int[] selectedRows = table.getSelectedRows();
		for (int selectedRow : selectedRows) {
			int modelIndex = table.convertRowIndexToModel(selectedRow);
			Agendamento dado = dados.get(modelIndex);
			if(dado.getHora() == null) {
				JOptionPane.showMessageDialog(null, "Hora invalida, selecione e tente novamente");
				return;
			}
			fDAOAgendamento.setHora(dado.getHora());
			break;
		}
		
		if (editar) {
			if(fDAOAgendamento.alterar(fDAOAgendamento)) {
				JOptionPane.showMessageDialog(null, "Editado com sucesso!");
			} else {
				JOptionPane.showMessageDialog(null, "Erro ao salvar!");
			}	
		} else {
			if(fDAOAgendamento.inserir(fDAOAgendamento)) {
				JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
			} else {
				JOptionPane.showMessageDialog(null, "Erro ao salvar!");
			}	
		}	
		atualizatabela(edData.getDate());
	}
	
	private void deleteAction(Integer prID) {
		if(fDAOAgendamento.existeAgendamento(prID)) {
			if(fDAOAgendamento.deletar(prID)){
				JOptionPane.showMessageDialog(null, "Agendamento deletado!");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Informe um agendamento valido");
		}
	}

	@Override
	public void preencheDadosUser(DadosUser listUser) {
		edCpf.setText(listUser.getBDCPF());
		edNomeUser.setText(listUser.getBDNOMEUSER());

		edNomePet.setText("");
		edNomeRaca.setText("");
	}

	@Override
	public void exluiUser(Integer bdiduser) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preencheDadosPet(Pet dado) {
		edNomePet.setText(dado.getBDNOMEPET());
		edNomeRaca.setText(dado.getBDNOMERACA());
		fDAOAgendamento.setBDIDPET(dado.getBDIDPET());
	}

	@Override
	public void exluiPet(Integer IdPet) {
		// TODO Auto-generated method stub
	}

	@Override
	public void preencheAge(Agendamento dado) {
		//Campos
		edNumAtendimento.setText(String.valueOf(dado.getId()));
		// Adiocionar os demais campos
		
		//DAO
		fDAOAgendamento.setId(dado.getId());
		fDAOAgendamento.setHora(dado.getHora());
		fDAOAgendamento.setDateAgendamento(dado.getDateAgendamento());
		fDAOAgendamento.setBDIDPET(dado.getBDIDPET());
	}

	@Override
	public void excluiAge(Integer prId) {		
		deleteAction(prId);
	}

}
