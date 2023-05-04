package vision.padrao;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TableSimples extends JTable {
	private static final long serialVersionUID = 1L;
	private DefaultTableCellRenderer headerRenderer;

	public TableSimples(Object[][] data, String[] columnNames) {
        super(new DefaultTableModel(data, columnNames) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return Integer.class; // a coluna "Id" é do tipo Integer
                } else {
                    return super.getColumnClass(columnIndex);
                }
            }
        });
        
        // Define a largura das colunas
        getColumnModel().getColumn(0).setPreferredWidth(500);
        getColumnModel().getColumn(1).setPreferredWidth(500);
        getColumnModel().getColumn(2).setPreferredWidth(500);
        
        // Define o alinhamento e o estilo do cabeçalho
        headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        headerRenderer.setBackground(Color.BLACK);
        headerRenderer.setForeground(Color.WHITE);
        setHeaderRenderer(headerRenderer);
        
        setBackground(Color.WHITE);
        
        // Define o alinhamento do conteúdo das células da primeira coluna
       getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
           private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
               JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
                        column);
               label.setHorizontalAlignment(JLabel.CENTER); // centraliza o conteúdo da célula
                return label;
            }

       });
    }
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Border outerBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
        Border innerBorder = new EmptyBorder(new Insets(0, 5, 0, 5));
        Border compoundBorder = new CompoundBorder(outerBorder, innerBorder);
        setBorder(compoundBorder);
    }
	
    
	public void preencherTabela(Object[][] data) {
		DefaultTableModel model = (DefaultTableModel) getModel();
		for (Object[] rowData : data) {
			model.addRow(rowData);
		}
	}
	
	public void limparTabela() {
		DefaultTableModel model = (DefaultTableModel) getModel();
		model.setRowCount(0); // Remove todas as linhas da tabela
	}
	
	 public void setHeaderRenderer(DefaultTableCellRenderer renderer) {
	        getTableHeader().setDefaultRenderer(renderer);
	    }
	
}
