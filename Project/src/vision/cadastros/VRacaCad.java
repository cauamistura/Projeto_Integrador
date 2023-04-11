package vision.cadastros;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.DAOTEspecie;
import control.DAOTPet;
import control.DAOTRaca;
import model.MTEspecie;
import model.MTEstado;
import model.MTRaca;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class VRacaCad extends JFrame {

	public DAOTRaca FDAOTRaca = new DAOTRaca();
	public DAOTEspecie FDAOTEspecie = new DAOTEspecie();
	private JPanel contentPane;
	private JTextField txtNomeRaca;
	private JComboBox<MTEspecie> especieCb = new JComboBox<MTEspecie>();
	private JLabel lblNewLabel_1;
	ArrayList<MTEspecie> TListEspecie = new ArrayList<>();
	ArrayList<MTRaca> TListRaca = new ArrayList<>();

	public VRacaCad() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 311, 172);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtNomeRaca = new JTextField();
		txtNomeRaca.setBounds(111, 47, 121, 20);
		contentPane.add(txtNomeRaca);
		txtNomeRaca.setColumns(10);

		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(55, 50, 46, 14);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (especieCb.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "Campo vazio: Espécie");
					return;
				}
				
				if (txtNomeRaca.getText().isEmpty() || txtNomeRaca.getText() == null) {
					JOptionPane.showMessageDialog(null, "Campo vazio: Nome");
					return;
				}

				FDAOTRaca.setBDIDRACA(FDAOTRaca.getChaveID("TRaca", "BDIDRACA"));
				FDAOTRaca.setBDNOMERACA(txtNomeRaca.getText());
				FDAOTRaca.setBDIDESPECIE(verificaRacas());

				if (achaIdRaca()) {
					try {
						FDAOTRaca.inserir(FDAOTRaca);
						txtNomeRaca.setText("");
						especieCb.setSelectedIndex(0);
						JOptionPane.showMessageDialog(null, "Raça cadastrada com sucesso!");
					} catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Não foi possível cadastrar a raça.");
					}
				}
				
			}
		});
		btnNewButton.setBounds(71, 83, 143, 23);
		contentPane.add(btnNewButton);

		TListEspecie = FDAOTEspecie.ListTEspecie(FDAOTEspecie);

		especieCb.addItem(null);

		for (MTEspecie mtEspecie : TListEspecie) {
			especieCb.addItem(mtEspecie);
		}
		contentPane.add(especieCb);

		especieCb.setBounds(112, 13, 117, 22);
		contentPane.add(especieCb);

		lblNewLabel_1 = new JLabel("Espécie:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(55, 17, 46, 14);
		contentPane.add(lblNewLabel_1);
	}

	public Integer verificaRacas() {
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
	
	private boolean achaIdRaca() {
		ArrayList<MTRaca> TListRaca = new ArrayList<>();
		TListRaca = FDAOTRaca.ListTRaca(FDAOTRaca, 0);

		for (MTRaca mtRaca : TListRaca) {

			if (mtRaca.getBDNOMERACA().equalsIgnoreCase(txtNomeRaca.getText())) {
				JOptionPane.showMessageDialog(null, "Raça já existente");
			return false;
			}

		}

		return true;
	}
	
}
