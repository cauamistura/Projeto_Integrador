package vision.consultas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.MTDadosUser;
import vision.cadastros.VUserCad;

public class VUserCON extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private JButton btnConfirmar;
    private JButton btnExcluir;

    public VUserCON(List<MTDadosUser> dados, VUserCad local) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 300);
        setTitle("Consulta de Usuario");
        setLocale(null);
        setLocationRelativeTo(null);;
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        scrollPane.setViewportView(table);

        model = new DefaultTableModel();
        model.addColumn("CPF");
        model.addColumn("Nome");
        model.addColumn("Email");

        for (MTDadosUser dado : dados) {
            Object[] rowData = { dado.getBDCPF(), dado.getBDNOME(), dado.getBDMAIL() };
            model.addRow(rowData);
        }

        table.setModel(model);

        btnConfirmar = new JButton("Confirmar");
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedRows = table.getSelectedRows();
                for (int i = 0; i < selectedRows.length; i++) {
                    int modelIndex = table.convertRowIndexToModel(selectedRows[i]);
                    MTDadosUser dado = dados.get(modelIndex);
                    local.preencheCampos(dado);
                    dispose();
                }
            }
        });

        btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedRows = table.getSelectedRows();
                for (int i = 0; i < selectedRows.length; i++) {
                    int modelIndex = table.convertRowIndexToModel(selectedRows[i]);
                    MTDadosUser dado = dados.get(modelIndex);
                    local.exluiUser(dado.getBDIDUSER());
                    dispose();
                }
            }
        });

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(btnConfirmar);
        buttonsPanel.add(btnExcluir);
        contentPane.add(buttonsPanel, BorderLayout.SOUTH);
    }
}
