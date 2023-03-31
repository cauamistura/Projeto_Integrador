package vision.padrao;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import control.DAOTCidade;
import control.DAOTClinica;
import control.DAOTEndereco;
import control.DAOTEstado;
import model.MTCidade;
import model.MTEndereco;
import model.MTEstado;

public class CEPTextField extends RoundJFormattedTextField{
	
	public DAOTClinica FDAOTClinica = new DAOTClinica();
	public DAOTEndereco FDAOTEndereco = new DAOTEndereco();
	public DAOTEstado FDAOTEstado = new DAOTEstado();
	public DAOTCidade FDAOTCidade = new DAOTCidade();
	
	private static final long serialVersionUID = 1L;
    public CEPTextField() {
		super(createFormatter());
		setColumns(9);
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
    
		public Boolean getCEPExiste(int prCEP, JTextField edBairro, JTextField edCidade, JComboBox cbUF) {
		// Valida se existe CEP
		ArrayList<MTEndereco> lEndereco = new ArrayList<>();
		ArrayList<MTEstado> TListEstado = new ArrayList<>();
		lEndereco = FDAOTEndereco.ListTEndereco(FDAOTEndereco);
		
		for (MTEndereco l : lEndereco) {

			if (l.getBDCEP() == prCEP) {
				edBairro.setText(l.getBDBAIRRO());

				// Procura Cidade Vinculada
				ArrayList<MTCidade> lCidade = new ArrayList<>();
				lCidade = FDAOTCidade.ListTCidade(FDAOTCidade);
				for (MTCidade lc : lCidade) {
					if (l.getBDIDCIDADE() == lc.getBDIDCIDADE()) {
						edCidade.setText(lc.getBDNOMECID());

						// Procura Estado vinculado
						for (MTEstado le : TListEstado) {
							if (lc.getBDIDUF() == le.getBDIDUF()) {
								cbUF.setSelectedIndex(lc.getBDIDUF()-1);
							}
						}
					}
				}
				return true;
			}
		}
		return false;
	}
}
