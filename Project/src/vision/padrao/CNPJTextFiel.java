package vision.padrao;

import java.sql.Connection; 
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.text.MaskFormatter;

import control.DAOClinica;
import model.Clinica;

public class CNPJTextFiel extends RoundJFormattedTextField {
	/**
	 * 
	 */
	public DAOClinica FDAOTClinica = new DAOClinica();
	private static final long serialVersionUID = 1L;
	
	public CNPJTextFiel() {
		super(createFormatter());
		setDisabledTextColor(getForeground());
        setBackground(getBackground());
	    setColumns(18); // define o tamanho do campo
	}

	public static MaskFormatter createFormatter() {
		MaskFormatter formatter = null;
	    try {
	    	formatter = new MaskFormatter("##.###.###/####-##"); // define a máscara
	        formatter.setPlaceholderCharacter('_');
	    } catch (ParseException ex) {
	         ex.printStackTrace();
	    }
	      return formatter;
	}
	    
	public Boolean existeCnpjClinica(DAOClinica prDAOClinica) {
		Connection c = prDAOClinica.append();
			try {
				Statement stm = c.createStatement();
					
				String wSql = "SELECT BDCNPJ FROM `dbpi`.`tclinica` t where t.BDCNPJ = '" + getText()+"'";
					
				ResultSet rs =  stm.executeQuery(wSql);
	
			    if(rs.next()){
			    	if(rs.getString("BDCNPJ").equals(getText())){
			    		return true;
			        }
			    }
	
			} catch (Exception e) {
				e.printStackTrace();
			}
		prDAOClinica.post();
		return false;
	}
	    
	public Integer IdClinica() {
	    	
		Integer idClinica = 0; 
		ArrayList<Clinica> TListClinica = new ArrayList<>();
		TListClinica = FDAOTClinica.ListTClinica(FDAOTClinica);

		for (Clinica mtClinica: TListClinica) {
				
			if (mtClinica.getBDCNPJ().equals(getText())) {
				idClinica = mtClinica.getBDIDCLINICA();		
			}		      
		}
		return idClinica;
	}

}
