package vision.padrao;

import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

public class CEPTextField extends JFormattedTextField {
    private static final long serialVersionUID = 1L;

    public CEPTextField() {
        super(createFormatter());
        setColumns(9); // define o tamanho do campo
    }

    private static MaskFormatter createFormatter() {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter("#####-###"); // define a máscara
            formatter.setPlaceholderCharacter('_');
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        return formatter;
    }

    public String getCEP() {
        String cep = getText().replaceAll("[^0-9]", ""); // remove caracteres não numéricos
        return cep.length() == 8 ? cep : null; // retorna o CEP ou null se não tiver 8 dígitos
    }
}
