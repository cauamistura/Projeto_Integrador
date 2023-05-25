package vision.consultas;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import model.Endereco;
import vision.padrao.TableSimples;

public class CepCON extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private TableSimples table;
    
    private DefaultTableModel model;
    
    public CepCON(List<Endereco> lista) {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 300);
        setTitle("Consulta de Usuario");
        setLocale(null);
        setLocationRelativeTo(null);;
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 464, 239);
        contentPane.add(scrollPane);

        table = new TableSimples(new Object[][] {}, new String[] { "CEP", "Cidade", "UF" });
        scrollPane.setViewportView(table);

        for (Endereco obj : lista) {
            Object[][] rowData = {{ obj.getBDCEP(), obj.getBDNOMECID(), obj.getBDSIGLAUF()}};
            table.preencherTabela(rowData);
        }

    }
}
