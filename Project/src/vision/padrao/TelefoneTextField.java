package vision.padrao;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.*;

public class TelefoneTextField extends JTextField {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TelefoneTextField() {
        super();
        setColumns(14);
        setDocument(new TelefoneDocument());
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getText().length() > 0) {
                    setCaretPosition(getText().length());
                }
            }
        });
    }

    private static class TelefoneDocument extends PlainDocument {
        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            String texto = getText(0, getLength()).replaceAll("[^0-9]", "");
            if (texto.length() < 11) {
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < str.length(); i++) {
                    if (Character.isDigit(str.charAt(i))) {
                        builder.append(str.charAt(i));
                    }
                }
                super.insertString(offs, builder.toString(), a);
            }
        }

        @Override
        public void remove(int offs, int len) throws BadLocationException {
            super.remove(offs, len);
        }
    }
}
