package vision.padrao;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

public class HourTextField extends RoundJFormattedTextField {
	private static final long serialVersionUID = 1L;

	public HourTextField() {
		super(createFormatter());
		setDisabledTextColor(getForeground());
		setBackground(getBackground());
		setColumns(5); // define o tamanho do campo
	}

	private static MaskFormatter createFormatter() {
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter("##:##"); // define a máscara
			formatter.setPlaceholderCharacter('_');
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return formatter;
	}

	public boolean validarHora() {
		try {
			if (getText().matches("\\d{2}:\\d{2}")) {
				String[] partes = getText().split(":");
				int horas = Integer.parseInt(partes[0]);
				int minutos = Integer.parseInt(partes[1]);

				if (horas >= 0 && horas <= 23 && minutos >= 0 && minutos <= 59) {
					return true; 
				}
			}
		} catch (NumberFormatException e) {
			return false; 
		}
		return false; 
	}
	
	public Integer getInteger() {
		String Hour = getText().replace(":", "");
		return Integer.valueOf(Hour);
	}
}
