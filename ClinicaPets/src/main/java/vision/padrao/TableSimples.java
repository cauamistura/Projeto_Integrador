package vision.padrao;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class TableSimples extends JTable {
	private static final long serialVersionUID = 1L;
	private DefaultTableCellRenderer headerRenderer;

	public TableSimples(Object[][] data, String[] columnNames) {
		super(new DefaultTableModel(data, columnNames) {});
		
		TableColumnModel columnModel = getColumnModel();
		for (int i = 0; i < columnModel.getColumnCount(); i++) {
		    TableColumn column = columnModel.getColumn(i);
		    column.setPreferredWidth(500);
		}
		
		definirLarguraColunas();
		definirCentralização();
        // Define o alinhamento e o estilo do cabeçalho
        headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        headerRenderer.setBackground(Color.BLACK);
        headerRenderer.setForeground(Color.WHITE);
        setHeaderRenderer(headerRenderer);
        
        setBackground(Color.WHITE);
        
        setDefaultRenderer(Object.class, new InteiroCentralizadoRenderer());

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
	 
	 public void definirLarguraColunas() {
		 TableColumnModel columnModel = getColumnModel();
		 for (int i = 0; i < columnModel.getColumnCount(); i++) {
			 TableColumn idColumn = getColumnModel().getColumn(0);
			 if (idColumn.getHeaderValue().equals("Id") ) {
				 idColumn.setPreferredWidth(100);
			 }
			 if (idColumn.getHeaderValue().equals("Número ate.") ) {
				 idColumn.setPreferredWidth(200);
			 }
		 }
	 }
	 
	 public void definirCentralização() {
		 
		 TableColumnModel columnModel = getColumnModel();
		 for (int i = 0; i < columnModel.getColumnCount(); i++) {
			 TableColumn idColumn = getColumnModel().getColumn(i);
			 if (idColumn.getHeaderValue().equals("CPF") || idColumn.getHeaderValue().equals("Data")) {
		 
				 getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRenderer() {
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
		}
		 
	 }
	 public void alterarTituloColuna(int columnIndex, String novoTitulo) {
	        TableColumnModel columnModel = getColumnModel();
	        TableColumn column = columnModel.getColumn(columnIndex);
	        column.setHeaderValue(novoTitulo);
	        headerRenderer.setText(novoTitulo);
	        getTableHeader().repaint();
	    }
	
	  // Renderer personalizado para centralizar as células que contêm inteiros
	    
	 private class InteiroCentralizadoRenderer extends DefaultTableCellRenderer {
		 
	        private static final long serialVersionUID = 1L;

			@Override
	        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
	                                                       boolean hasFocus, int row, int column) {
	            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

	            if (value instanceof Integer) {
	                setHorizontalAlignment(SwingConstants.CENTER);
	            } else {
	                setHorizontalAlignment(SwingConstants.LEFT);
	            }

	            return c;
	        }
	        
	        @Override
	        public void setValue(Object value) {
	            if (value instanceof Integer) {
	                setText(value.toString());
	            } else {
	                super.setValue(value);
	            }
	        }
	    }
	}