package vision.cadastros;

import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import model.MTEstado;

public class ModelEstadoCombobox extends AbstractListModel<MTEstado> implements ComboBoxModel<MTEstado> {

	
	private ArrayList<MTEstado> tListEstado;
	private MTEstado item;
	public ModelEstadoCombobox(ArrayList<MTEstado> tListEstado) {
		this.tListEstado = tListEstado;
	}

	@Override
	public int getSize() {
		
		return this.tListEstado.size();
	}

	@Override
	public MTEstado getElementAt(int index) {
		return this.tListEstado.get(index);
	}

	@Override
	public void setSelectedItem(Object anItem) {
		this.item = (MTEstado) anItem;
		
	}

	@Override
	public MTEstado getSelectedItem() {
		
		return this.item;
	}

}
