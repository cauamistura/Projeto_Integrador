package vision.padrao;

import javax.swing.text.MaskFormatter;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTextField extends RoundJFormattedTextField {
	private static final long serialVersionUID = 1L;

	public DateTextField() {
		super(createFormatter());
		setDisabledTextColor(getForeground());
		setBackground(getBackground());
		setToolTipText("Aperte F2 para preencher com a data atual.");
		setColumns(10); // define o tamanho do campo
		
	    // adiciona um KeyListener para detectar a tecla F2
	    addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyPressed(KeyEvent e) {
	            if (e.getKeyCode() == KeyEvent.VK_F2) {
	                // define a data atual no campo de texto
	                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	                LocalDate today = LocalDate.now();
	                setText(formatter.format(today));
	            }
	        }
	    });
	}

	private static MaskFormatter createFormatter() {
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter("##/##/####"); // define a máscara
			formatter.setPlaceholderCharacter('_');
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return formatter;
	}

	public LocalDate getDate() {
		String inputDate = getText();
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate date = LocalDate.parse(inputDate, formatter);
			return date;
		} catch (DateTimeParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean validaDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		try {
			sdf.parse(getText());
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

}
