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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VRacaCad frame = new VRacaCad();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VRacaCad() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
				
				if(txtNomeRaca.getText().isEmpty() || txtNomeRaca.getText() == null) {
					JOptionPane.showMessageDialog(null, "Campo vazio: Nome");
					return;
				}
				
				FDAOTRaca.setBDIDRACA(FDAOTRaca.getChaveID("TRaca", "BDIDRACA"));
				FDAOTRaca.setBDNOMERACA(txtNomeRaca.getText());
				FDAOTRaca.setBDIDESPECIE(achaIdEspecie());
	
				try {
					FDAOTRaca.inserir(FDAOTRaca);
					txtNomeRaca.setText("");
					JOptionPane.showMessageDialog(null, "Raça cadastrada com sucesso!");
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Não foi possível cadastrar a raça.");
				}
			}
		});
		btnNewButton.setBounds(71, 83, 143, 23);
		contentPane.add(btnNewButton);
		
		TListEspecie = FDAOTEspecie.ListTEspecie(FDAOTEspecie);

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
}
