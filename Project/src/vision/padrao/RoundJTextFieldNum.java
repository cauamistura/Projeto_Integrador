package vision.padrao;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class RoundJTextFieldNum extends RoundJTextField{
	
	private int maxCharacters;

    public RoundJTextFieldNum(int maxCharacters) {
        super();
        this.maxCharacters = maxCharacters;
        setDocument(new NumerosOnlyDocument());
    }

    private class NumerosOnlyDocument extends PlainDocument {
        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            if (str == null) {
                return;
            }

            if (getLength() + str.length() <= maxCharacters) { // Verifica o número máximo de caracteres
                char[] caracteres = str.toCharArray();
                boolean todosNumeros = true;
                for (char c : caracteres) {
                    if (!Character.isDigit(c)) {
                        todosNumeros = false;
                        break;
                    }
                }

                if (todosNumeros) {
                    super.insertString(offs, new String(caracteres), a);
                }
            }
        }
    }
}
