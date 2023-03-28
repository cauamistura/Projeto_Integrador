package vision.consultas;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import control.DAOTUser;
import model.MTDadosUser;
import model.MTUser;

public class VUserCON extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTable table;
    
    private DefaultTableModel model;
    
    public VUserCON(List<MTDadosUser> lista) {
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

        table = new JTable();
        scrollPane.setViewportView(table);

        model = new DefaultTableModel();
        model.addColumn("CPF");
        model.addColumn("Nome");
        model.addColumn("Email");

        for (MTDadosUser obj : lista) {
            Object[] rowData = { obj.getBDCPF(), obj.getBDNOME(), obj.getBDMAIL()};
            model.addRow(rowData);
        }

        table.setModel(model);
    }
}
