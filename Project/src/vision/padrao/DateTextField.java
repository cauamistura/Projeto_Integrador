package vision.padrao;

import javax.swing.text.MaskFormatter;
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
        setColumns(10); // define o tamanho do campo
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

