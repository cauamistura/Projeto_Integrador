package vision.padrao;

import java.util.ArrayList; 

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import control.DAOCidade;
import control.DAOClinica;
import control.DAOEndereco;
import control.DAOEstado;
import model.Cidade;
import model.Endereco;
import model.Estado;

public class CEPTextField extends RoundJFormattedTextField {

	public DAOClinica FDAOTClinica = new DAOClinica();
	public DAOEndereco FDAOTEndereco = new DAOEndereco();
	public DAOEstado FDAOTEstado = new DAOEstado();
	public DAOCidade FDAOTCidade = new DAOCidade();
	ArrayList<Endereco> lEndereco = new ArrayList<>();
	ArrayList<Estado> TListEstado = new ArrayList<>();
	ArrayList<Cidade> lCidade = new ArrayList<>();

	private static final long serialVersionUID = 1L;

	public CEPTextField() {
		super(createFormatter());
		setDisabledTextColor(getForeground());
        setBackground(getBackground());
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

		for (Endereco l : lEndereco) {

			if (l.getBDCEP() == prCEP) {
				edBairro.setText(l.getBDBAIRRO());
				// Procura Cidade Vinculada
				for (Cidade lc : lCidade) {

					if (l.getBDIDCIDADE() == lc.getBDIDCIDADE()) {

						edCidade.setText(lc.getBDNOMECID());
						edDescricao.setText(lc.getBDDESCCID());
						
						// Procura Estado vinculado
						for (Estado le : TListEstado) {
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
		ArrayList<Estado> TListEstado = new ArrayList<>();
		TListEstado = FDAOTEstado.ListTEstado(FDAOTEstado);

		for (Estado mtEstado : TListEstado) {
			
		if (mtEstado.getBDSIGLAUF().equals(cbUF.getSelectedItem().toString())) {
			idUf = mtEstado.getBDIDUF();
			
		}		
		      
		}
		return idUf;
	}
}
