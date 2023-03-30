package vision.cadastros;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import control.DAOTDadosUser;
import control.DAOTEspecie;
import control.DAOTPet;
import control.DAOTPetUser;
import control.DAOTRaca;
import control.DAOTUser;
import model.MTDadosUser;
import model.MTEspecie;
import model.MTEstado;
import model.MTRaca;
import model.MTUser;

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
import java.awt.event.ItemEvent;

public class VPetCad extends JFrame {

	/**
	 * 
	 */

	public DAOTPet FDAOTPet = new DAOTPet();
	public DAOTUser FDAOTUser = new DAOTUser();
	public DAOTDadosUser FDAOTDadosUser = new DAOTDadosUser();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtApelidoPet;
	private JTextField txtNomePet;
	private JTextField txtDataNasc;
	public DAOTEspecie FDAOTEspecie = new DAOTEspecie();
	public DAOTRaca FDAOTRaca = new DAOTRaca();
	JComboBox<MTEspecie> especieCb = new JComboBox<MTEspecie>();
	JComboBox<MTRaca> racaCb = new JComboBox<MTRaca>();
	ArrayList<MTRaca> TListRaca = new ArrayList<>();
	ArrayList<MTEspecie> TListEspecie = new ArrayList<>();

	/**
	 * Create the frame.
	 */
	public VPetCad() {
		setTitle("Cadastro de pets");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 319, 341);
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

		try {
			txtDataNasc = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e2) {
			JOptionPane.showMessageDialog(null, "Data inválida");
			e2.printStackTrace();
		}
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

		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

				if (txtNomePet.getText().isEmpty() || txtNomePet.getText() == null) {
					JOptionPane.showMessageDialog(null, "Campo vazio: Nome");
					return;
				}

				if (txtApelidoPet.getText().isEmpty() || txtApelidoPet.getText() == null) {
					JOptionPane.showMessageDialog(null, "Campo vazio: Apelido");
					return;
				}

				if (txtDataNasc.getText().isEmpty() || txtDataNasc.getText() == null) {
					JOptionPane.showMessageDialog(null, "Campo vazio: Data de nascimento");
					return;
				}

				FDAOTPet.setBDIDPET(FDAOTPet.getChaveID("TPets", "BDIDPET"));
				FDAOTPet.setBDIDRACA(achaIdRaca());
				FDAOTPet.setBDNOMEPET(txtNomePet.getText());
				FDAOTPet.setBDAPELIDO(txtApelidoPet.getText());

				try {
					FDAOTPet.setBDDATANASCIMENTO(LocalDate.parse(txtDataNasc.getText(), formatter));
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "Data inválida. Tente novamente.");
					return;
				}

				try {
					FDAOTPet.inserir(FDAOTPet);
					txtNomePet.setText("");
					txtApelidoPet.setText("");
					txtDataNasc.setText("");
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
				
				if (e.getStateChange() == ItemEvent.SELECTED) {
                    
                    TListRaca = FDAOTRaca.ListTRaca(FDAOTRaca, achaIdEspecie());

            		for (MTRaca mtRaca : TListRaca) {
            			racaCb.addItem(mtRaca);
            		}
                }
			}
		});

		especieCb.setBounds(162, 33, 87, 22);
		contentPane.add(especieCb);

		TListEspecie = FDAOTEspecie.ListTEspecie(FDAOTEspecie);

		for (MTEspecie mtEspecie : TListEspecie) {
			especieCb.addItem(mtEspecie);
		}
		contentPane.add(especieCb);

		JLabel lblNewLabel_1 = new JLabel("Espécie:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(24, 37, 128, 14);
		contentPane.add(lblNewLabel_1);

		///////////////////////////////////////////////////

		racaCb.setBounds(162, 75, 86, 22);
		contentPane.add(racaCb);

		contentPane.add(racaCb);
		
		JLabel lblNewLabel_1_1 = new JLabel("Raça:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setBounds(24, 79, 128, 14);
		contentPane.add(lblNewLabel_1_1);

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
	
	public Integer achaIdRaca() {
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

}
