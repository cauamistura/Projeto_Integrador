package vision.padrao;

import javax.swing.text.MaskFormatter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;

import control.DAOPet;
import control.DAOUser;
import vision.Menu;

public class CPFTextField extends RoundJFormattedTextField{
	
    private static final long serialVersionUID = 1L;
    
    public CPFTextField() {
        super(createFormatter());
        setDisabledTextColor(getForeground());
        setBackground(getBackground());
        setColumns(14); // define o tamanho do campo
    }

    public static MaskFormatter createFormatter() {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter("###.###.###-##"); // define a máscara
            formatter.setPlaceholderCharacter('_');
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return formatter;
    }
    
    public String getCPF() {
        String wCPF = getText();
        wCPF = wCPF.replaceAll("[^0-9]", "");
        return  wCPF;
    }
    
    public Boolean validaCPF() {
        String wCPF = getText();
        wCPF = wCPF.replaceAll("[^0-9]", "");
        if (wCPF.length() != 11) {
            return false;
        }
//        // Calcula o primeiro dígito verificador
//        int sum = 0;
//        for (int i = 0; i < 9; i++) {
//            sum += (wCPF.charAt(i) - '0') * (10 - i);
//        }
//        int firstDigit = 11 - (sum % 11);
//        if (firstDigit > 9) {
//            firstDigit = 0; // se o resultado for maior que 9, o dígito verificador é 0
//        }
//
//        // Calcula o segundo dígito verificador
//        sum = 0;
//        for (int i = 0; i < 9; i++) {
//            sum += (wCPF.charAt(i) - '0') * (11 - i);
//        }
//        sum += firstDigit * 2;
//        int secondDigit = 11 - (sum % 11);
//        if (secondDigit > 9) {
//            secondDigit = 0; // se o resultado for maior que 9, o dígito verificador é 0
//        }
//
//        // Compara os dígitos verificadores calculados com os dígitos do CPF
//        return wCPF.charAt(9) - '0' == firstDigit && wCPF.charAt(10) - '0' == secondDigit;
        return true;
    }

	public Boolean existeCpfUsuario(DAOUser prDAOUser) {
		Connection c = prDAOUser.append();
		try {
			Statement stm = c.createStatement();
			
			String wSql = "SELECT BDCPF FROM `dbpi`.`tuser` t where t.BDIDCLINICA = "+String.valueOf(Menu.FIDClinica)+" and t.BDCPF = '"+getText()+"'";
			
			ResultSet rs =  stm.executeQuery(wSql);

	        if(rs.next()){
	        	if(rs.getString("BDCPF").equals(getText())){
	        		return true;
	        	}
	        }

		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAOUser.post();
		return false;
	}
    	
}
