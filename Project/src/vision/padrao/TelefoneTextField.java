package vision.padrao;

import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public class TelefoneTextField extends JFormattedTextField {
    private static final long serialVersionUID = 1L;

    public TelefoneTextField() {
        super(createFormatter());
        setColumns(14); // define o tamanho do campo
    }

    private static MaskFormatter createFormatter() {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter("(##)####-####"); // define a máscara
            formatter.setPlaceholderCharacter('_');
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return formatter;
    }

    public String getTelefone() {
        String telefone = getText().replaceAll("[^0-9]", ""); // remove caracteres não numéricos
        return telefone;
    }
}
