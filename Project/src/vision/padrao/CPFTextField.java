package vision.padrao;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class CPFTextField extends JTextField {
    private static final long serialVersionUID = 1L;

    public CPFTextField(int columns) {
        super(columns);
        setDocument(new CPFDocument());
    }

    private static class CPFDocument extends PlainDocument {
        private static final long serialVersionUID = 1L;

        @Override
        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null) {
                return;
            }

            String oldText = getText(0, getLength());
            String newText = oldText.substring(0, offset) + str + oldText.substring(offset);

            if (!newText.matches("\\d{0,3}(\\.\\d{0,3}){0,2}-\\d{0,2}")) {
                return;
            }

            super.insertString(offset, str, attr);
        }
    }
}

