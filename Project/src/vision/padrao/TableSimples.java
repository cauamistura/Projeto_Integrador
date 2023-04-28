package vision.padrao;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TableSimples extends JTable {
	private static final long serialVersionUID = 1L;

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
		getColumnModel().getColumn(0).setPreferredWidth(50);
		getColumnModel().getColumn(1).setPreferredWidth(500);
		getColumnModel().getColumn(2).setPreferredWidth(500);
		 // define a largura da coluna 
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
	
}
