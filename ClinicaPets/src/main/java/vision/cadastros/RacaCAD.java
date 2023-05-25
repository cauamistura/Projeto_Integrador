package vision.cadastros;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import control.DAOEspecie;
import control.DAORaca;
import model.Especie;
import model.Raca;
import net.miginfocom.swing.MigLayout;
import vision.padrao.PanelComBackgroundImage;
import vision.padrao.RoundButton;
import vision.padrao.RoundJTextField;

public class RacaCAD extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DAORaca FDAOTRaca = new DAORaca();
	public DAOEspecie FDAOTEspecie = new DAOEspecie();
	private JPanel contentPane;
	ArrayList<Especie> TListEspecie = new ArrayList<>();
	private JPanel panel;
	private JPanel panel_1;
	private JTextField txtNomeRaca;
	private JButton btnNewButton;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JComboBox especieCb = new JComboBox<Especie>();

	public RacaCAD() {
		BufferedImage bg = null;
		;
		try {
			bg = ImageIO.read(new File("src\\main\\\\resources\\BGLogin.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setBackground(new Color(158, 174, 255));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setBackground(new Color(158, 174, 255));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[70px][320px,grow][70px]", "[70px][360px,grow][70px]"));
		
		panel_1 = new PanelComBackgroundImage(bg);
		panel_1.setBackground(new Color(158, 174, 255));
		panel.add(panel_1, "cell 1 1,alignx center,aligny center");
		panel_1.setLayout(new MigLayout("", "[60px][200px,grow][60px]", "[25px][50px][50px][50px][][50px][25px]"));
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\vinis\\eclipse-workspace\\ClinicaPets\\src\\main\\resources\\gatoAbo.png"));
		panel_1.add(lblNewLabel_2, "cell 1 1,alignx center");
		
		btnNewButton = new RoundButton("Login");
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
		
		lblNewLabel_1 = new JLabel("Nome:");
		panel_1.add(lblNewLabel_1, "flowy,cell 1 3,alignx left");
		
		btnNewButton.setText("Cadastrar");
		panel_1.add(btnNewButton, "cell 1 5,growx");
		
		txtNomeRaca =  new RoundJTextField();
		panel_1.add(txtNomeRaca, "cell 1 3,growx");
		txtNomeRaca.setColumns(10);
		
		lblNewLabel = new JLabel("Espécie:");
		panel_1.add(lblNewLabel, "flowy,cell 1 2");
		
		especieCb = new JComboBox();
		panel_1.add(especieCb, "cell 1 2,growx");
		
		TListEspecie = FDAOTEspecie.ListTEspecie(FDAOTEspecie);

		TListEspecie = FDAOTEspecie.ListTEspecie(FDAOTEspecie);

		especieCb.addItem(null);

		for (Especie mtEspecie : TListEspecie) {
			especieCb.addItem(mtEspecie);
		}
	}

	public Integer verificaRacas() {
		Integer idEspecie = 0;
		ArrayList<Especie> TListEspecie = new ArrayList<>();
		TListEspecie = FDAOTEspecie.ListTEspecie(FDAOTEspecie);

		for (Especie mtEspecie : TListEspecie) {

			if (mtEspecie.getBDNOMEESPECIE().equals(especieCb.getSelectedItem().toString())) {
				idEspecie = mtEspecie.getBDIDESPECIE();
			}

		}

		return idEspecie;
	}
	
	private boolean achaIdRaca() {
		ArrayList<Raca> TListRaca = new ArrayList<>();
		TListRaca = FDAOTRaca.ListTRaca(FDAOTRaca, 0);

		for (Raca mtRaca : TListRaca) {

			if (mtRaca.getBDNOMERACA().equalsIgnoreCase(txtNomeRaca.getText())) {
				JOptionPane.showMessageDialog(null, "Raça já existente");
			return false;
			}

		}

		return true;
	}
	
}
