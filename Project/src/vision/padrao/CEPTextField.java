package vision.padrao;

import java.util.ArrayList; 

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import control.DAOTCidade;
import control.DAOTClinica;
import control.DAOTEndereco;
import control.DAOTEstado;
import model.MTCidade;
import model.MTEndereco;
import model.MTEstado;

public class CEPTextField extends RoundJFormattedTextField {

	public DAOTClinica FDAOTClinica = new DAOTClinica();
	public DAOTEndereco FDAOTEndereco = new DAOTEndereco();
	public DAOTEstado FDAOTEstado = new DAOTEstado();
	public DAOTCidade FDAOTCidade = new DAOTCidade();
	ArrayList<MTEndereco> lEndereco = new ArrayList<>();
	ArrayList<MTEstado> TListEstado = new ArrayList<>();
	ArrayList<MTCidade> lCidade = new ArrayList<>();

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

	public Boolean getCEPExiste(int prCEP, JTextField edBairro, JTextField edCidade, JTextField edDescricao, JComboBox cbUF) {
		// Valida se existe CEP
		
		lEndereco = FDAOTEndereco.ListTEndereco(FDAOTEndereco);
		TListEstado = FDAOTEstado.ListTEstado(FDAOTEstado);
		lCidade = FDAOTCidade.ListTCidade(FDAOTCidade);

		for (MTEndereco l : lEndereco) {

			if (l.getBDCEP() == prCEP) {
				edBairro.setText(l.getBDBAIRRO());
				// Procura Cidade Vinculada
				for (MTCidade lc : lCidade) {

					if (l.getBDIDCIDADE() == lc.getBDIDCIDADE()) {

						edCidade.setText(lc.getBDNOMECID());
						edDescricao.setText(lc.getBDDESCCID());
						
						// Procura Estado vinculado
						for (MTEstado le : TListEstado) {
							if (lc.getBDIDUF() == le.getBDIDUF()) {
								cbUF.setSelectedIndex(lc.getBDIDUF());
							}
						}
					}
				}
				return true;
			}
		}
		return false;
	}
	
	public Integer achaIdUf(JComboBox cbUF) {
		
		Integer idUf = 0; 
		ArrayList<MTEstado> TListEstado = new ArrayList<>();
		TListEstado = FDAOTEstado.ListTEstado(FDAOTEstado);

		for (MTEstado mtEstado : TListEstado) {
			
		if (mtEstado.getBDSIGLAUF().equals(cbUF.getSelectedItem().toString())) {
			idUf = mtEstado.getBDIDUF();
			
		}		
		      
		}
		return idUf;
	}
}
