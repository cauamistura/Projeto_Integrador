package vision.atendimentos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import vision.cadastros.VReceitaCad;
import vision.padrao.lupaButton;

public class VSaidaATE extends JFrame {

	private JPanel contentPane;
	private JTextField edNumEntrada;
	private JTextField edNomePet;
	private JTextField edCpfUser;
	private JTextField edDataSaida;
	private JTextPane DescSaida;
	private JButton btnConf;
	private JButton btnLimpar;
	private JButton btnExcluir;
	private lupaButton btnEntrada;
	private lupaButton btReceita;
	private JTextField edNomeUser;
	

	public VSaidaATE() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btReceita = new lupaButton("Receita");
		btReceita.setText("");
		btReceita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VReceitaCad v = new VReceitaCad();
				v.setLocationRelativeTo(null);
				v.setVisible(true);
				
			}
			
		});
		btReceita.setBounds(353, -2, 49, 25);
		contentPane.add(btReceita);
		
		btnEntrada = new lupaButton("");
		btnEntrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(null, "Abre a tela de Cosulta de Atendimento Entrada");
			}
		});
		btnEntrada.setBounds(117, -2, 49, 25);
		contentPane.add(btnEntrada);
		
		DescSaida = new JTextPane();
		DescSaida.setBounds(255, 128, 166, 88);
		contentPane.add(DescSaida);
		
		btnConf = new JButton("Confirmar");
		btnConf.setBounds(68, 162, 117, 25);
		contentPane.add(btnConf);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(68, 199, 117, 25);
		contentPane.add(btnLimpar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(68, 236, 117, 25);
		contentPane.add(btnExcluir);
		
		edNumEntrada = new JTextField();
		edNumEntrada.setColumns(10);
		edNumEntrada.setBounds(117, 35, 114, 19);
		contentPane.add(edNumEntrada);
		
		edNomePet = new JTextField();
		edNomePet.setColumns(10);
		edNomePet.setBounds(117, 58, 114, 19);
		contentPane.add(edNomePet);
		
		edCpfUser = new JTextField();
		edCpfUser.setColumns(10);
		edCpfUser.setBounds(117, 89, 114, 19);
		contentPane.add(edCpfUser);
		
		edDataSaida = new JTextField();
		edDataSaida.setColumns(10);
		edDataSaida.setBounds(117, 128, 114, 19);
		contentPane.add(edDataSaida);
		
		JLabel lblNumEntrada = new JLabel("N° Entrada");
		lblNumEntrada.setBounds(29, 37, 70, 15);
		contentPane.add(lblNumEntrada);
		
		JLabel lblNomePet = new JLabel("Pet");
		lblNomePet.setBounds(29, 60, 70, 15);
		contentPane.add(lblNomePet);
		
		JLabel lblUser = new JLabel("Ususario");
		lblUser.setBounds(29, 91, 70, 15);
		contentPane.add(lblUser);
		
		JLabel lblSaida = new JLabel("Saida");
		lblSaida.setBounds(29, 128, 70, 15);
		contentPane.add(lblSaida);
		
		JLabel lblReceita = new JLabel("Receita");
		lblReceita.setBounds(265, -2, 70, 15);
		contentPane.add(lblReceita);
		
		JLabel lblEntrada = new JLabel("Entrada");
		lblEntrada.setBounds(29, -2, 70, 15);
		contentPane.add(lblEntrada);
		
		edNomeUser = new JTextField();
		edNomeUser.setColumns(10);
		edNomeUser.setBounds(255, 89, 114, 19);
		contentPane.add(edNomeUser);
	}
}
