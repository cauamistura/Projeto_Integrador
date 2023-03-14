package vision.padrao;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public class TelefoneField extends JTextField {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TelefoneField() {
        try {
            MaskFormatter telefoneFormatter = new MaskFormatter("(##) #####-####");
            telefoneFormatter.install(null);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getTelefone() {
        return getText().replaceAll("[^\\d]", "");
    }
}
