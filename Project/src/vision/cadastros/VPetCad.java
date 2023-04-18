package vision.cadastros;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import control.DAOTDadosUser;
import control.DAOTEspecie;
import control.DAOTPet;
import control.DAOTRaca;
import control.DAOTUser;
import model.MTDadosUser;
import model.MTEspecie;
import model.MTEstado;
import model.MTPet;
import model.MTRaca;
import model.MTUser;
import vision.consultas.VPetCON;
import vision.consultas.VUserCON;
import vision.padrao.DateTextField;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemEvent;

public class VPetCad extends JFrame {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	
	public DAOTPet FDAOTPet = new DAOTPet();
	public DAOTUser FDAOTUser = new DAOTUser();
	public DAOTEspecie FDAOTEspecie = new DAOTEspecie();
	public DAOTDadosUser FDAOTDadosUser = new DAOTDadosUser();
	public DAOTRaca FDAOTRaca = new DAOTRaca();
	
	private JTextField txtApelidoPet;
	private JTextField txtNomePet;
	private DateTextField txtDataNasc;
	
	private JPanel contentPane;
	
	JComboBox<MTEspecie> especieCb = new JComboBox<MTEspecie>();
	JComboBox<MTRaca> racaCb = new JComboBox<MTRaca>();
	JComboBox<MTDadosUser> userCb = new JComboBox<MTDadosUser>();
	
	ArrayList<MTRaca> TListRaca = new ArrayList<>();
	ArrayList<MTEspecie> TListEspecie = new ArrayList<>();
	ArrayList<MTDadosUser> TListUser = new ArrayList<>();

	/**
	 * Create the frame.
	 */
	public VPetCad() {
		setTitle("Cadastro de pets");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 319, 512);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtNomePet = new JTextField();
		txtNomePet.setBounds(162, 108, 86, 20);
		contentPane.add(txtNomePet);

		txtApelidoPet = new JTextField();
		txtApelidoPet.setBounds(161, 142, 86, 20);
		contentPane.add(txtApelidoPet);
		txtApelidoPet.setColumns(10);
		txtApelidoPet.setColumns(10);

		
		txtDataNasc = new DateTextField();
		txtDataNasc.setBounds(162, 177, 86, 20);
		contentPane.add(txtDataNasc);
		txtDataNasc.setColumns(10);

		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(106, 111, 46, 14);
		contentPane.add(lblNewLabel);

		JLabel lblApelidoopcional = new JLabel("Apelido (opcional):");
		lblApelidoopcional.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApelidoopcional.setBounds(39, 145, 114, 14);
		contentPane.add(lblApelidoopcional);

		JLabel lblDataDeNasc = new JLabel("Data de nasc:");
		lblDataDeNasc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataDeNasc.setBounds(52, 180, 100, 14);
		contentPane.add(lblDataDeNasc);

		VPetCad local = this;
		txtNomePet.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F9) {
					abreConsulta(local);
				}
			}
		});
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				if (txtNomePet.getText().isEmpty() || txtNomePet.getText() == null) {
					JOptionPane.showMessageDialog(null, "Campo vazio: Nome");
					return;
				}

				if (txtDataNasc.getText().isEmpty() || txtDataNasc.getText() == null) {
					JOptionPane.showMessageDialog(null, "Campo vazio: Data de nascimento");
					return;
				}
				
				if (especieCb.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "Campo vazio: Espécie");
					return;
				}
				
				if (racaCb.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "Campo vazio: Raça");
					return;
				}
				
				if (userCb.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "Campo vazio: Dono");
					return;
				}
				
				FDAOTPet.setBDDATANASCIMENTO(txtDataNasc.getDate());
				FDAOTPet.setBDIDPET(FDAOTPet.getChaveID("TPets", "BDIDPET"));
				FDAOTPet.setBDIDRACA(achaIdRaca());
				FDAOTPet.setBDNOMEPET(txtNomePet.getText());
				FDAOTPet.setBDAPELIDO(txtApelidoPet.getText());
				FDAOTPet.setBDIDUSER(achaIdUser());

				if(!txtDataNasc.validaDate()) {
					JOptionPane.showMessageDialog(null, "Data inválida. Tente novamente.");
					return;
				}

				try {
					FDAOTPet.inserir(FDAOTPet);
					txtNomePet.setText("");
					txtApelidoPet.setText("");
					txtDataNasc.setText("");
					especieCb.setSelectedIndex(0);
					racaCb.setSelectedIndex(0);
					userCb.setSelectedIndex(0);
					JOptionPane.showMessageDialog(null, "Seu pet foi cadastrado com sucesso!");
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Não foi possível cadastrar o pet");
				}

			}
		});
		btnNewButton.setBounds(72, 234, 174, 31);
		contentPane.add(btnNewButton);
		especieCb.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				racaCb.removeAllItems();
				
				racaCb.addItem(null);
				
				if (e.getStateChange() == ItemEvent.SELECTED) {
                    
                    TListRaca = FDAOTRaca.ListTRaca(FDAOTRaca, achaIdEspecie());

            		for (MTRaca mtRaca : TListRaca) {
            			racaCb.addItem(mtRaca);
            		}
                }
			}
		});

		especieCb.setBounds(164, 6, 87, 22);
		contentPane.add(especieCb);

		TListEspecie = FDAOTEspecie.ListTEspecie(FDAOTEspecie);

		especieCb.addItem(null);
		
		for (MTEspecie mtEspecie : TListEspecie) {
			especieCb.addItem(mtEspecie);
		}
		contentPane.add(especieCb);
		
		TListUser = FDAOTDadosUser.ListTDadosUser(FDAOTDadosUser);

		userCb.addItem(null);
		
		for (MTDadosUser mtUser : TListUser) {
			userCb.addItem(mtUser);
		}
		contentPane.add(especieCb);

		JLabel lblNewLabel_1 = new JLabel("Espécie:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(26, 10, 128, 14);
		contentPane.add(lblNewLabel_1);

		racaCb.setBounds(164, 48, 86, 22);
		contentPane.add(racaCb);

		contentPane.add(racaCb);
		
		JLabel lblNewLabel_1_1 = new JLabel("Raça:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setBounds(26, 52, 128, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JButton btnNewButton_1 = new JButton("Consultar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<MTPet> lista = FDAOTPet.ListTPet(FDAOTPet);
				VPetCad prSelf = new VPetCad();
				VPetCON frame = new VPetCON(lista, prSelf);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(72, 276, 176, 23);
		contentPane.add(btnNewButton_1);
		
		
		userCb.setBounds(162, 75, 86, 22);
		contentPane.add(userCb);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Dono(a):");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1.setBounds(26, 77, 128, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtApelidoPet.setText("");
				txtDataNasc.setText("");
				txtNomePet.setText("");
				
				racaCb.setSelectedIndex(0);
				especieCb.setSelectedIndex(0);
				userCb.setSelectedIndex(0);
			}
		});
		btnLimpar.setBounds(82, 310, 166, 23);
		contentPane.add(btnLimpar);

	}

	public Integer achaIdEspecie() {
		Integer idEspecie = 0;
		ArrayList<MTEspecie> TListEspecie = new ArrayList<>();
		TListEspecie = FDAOTEspecie.ListTEspecie(FDAOTEspecie);

		for (MTEspecie mtEspecie : TListEspecie) {

			if (mtEspecie.getBDNOMEESPECIE().equals(especieCb.getSelectedItem().toString())) {
				idEspecie = mtEspecie.getBDIDESPECIE();

			}

		}

		return idEspecie;
	}
	
	private Integer achaIdRaca() {
		Integer idRaca = 0;
		ArrayList<MTRaca> TListRaca = new ArrayList<>();
		TListRaca = FDAOTRaca.ListTRaca(FDAOTRaca, 0);

		for (MTRaca mtRaca : TListRaca) {

			if (mtRaca.getBDNOMERACA().equals(racaCb.getSelectedItem().toString())) {
				idRaca = mtRaca.getBDIDRACA();

			}

		}

		return idRaca;
	}
	
	private Integer achaIdUser() {
		Integer idUser = 0;
		ArrayList<MTDadosUser> TListUser = new ArrayList<>();
		TListUser = FDAOTDadosUser.ListTDadosUser(FDAOTDadosUser);

		for (MTDadosUser mtUser : TListUser) {

			if (mtUser.getBDNOMEUSER().equals(userCb.getSelectedItem().toString())) {
				idUser = mtUser.getBDIDUSER();

			}

		}

		return idUser;
	}
	
	private void abreConsulta(VPetCad prSelf) {
		if (FDAOTPet != null) {
			List<MTPet> lista = FDAOTPet.ListTPet(FDAOTPet);
			VPetCON frame = new VPetCON(lista, prSelf);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}
	}
}
