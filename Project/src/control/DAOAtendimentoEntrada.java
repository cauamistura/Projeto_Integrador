package control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import model.MTAtendimenoEntrada;

public class DAOAtendimentoEntrada extends MTAtendimenoEntrada {
	private String wSql;
	
	//Insert
	public Boolean inserir(DAOAtendimentoEntrada prDAO) {
		Connection c = prDAO.append();
		try {
			wSql = "INSERT INTO `dbpi`.`tatendimento_entrada` (`BDIDENTRADA`,`BDIDPET`,`BDCOMORBIDADE`,`BDDATAENT`,`BDDESC`) VALUES (?,?,?,?,?)";
			PreparedStatement stm = c.prepareStatement(wSql);
			
			stm.setInt	 (1, prDAO.getBDIDENTRADA());
			stm.setInt	 (2, prDAO.getBDIDPET());
			
			if (prDAO.getBDCOMORBIDADE() == null) {
				stm.setNull(0, 0, wSql);
			} else {
				stm.setInt(3, prDAO.getBDCOMORBIDADE());
			}
			
			stm.setDate	 (4, Date.valueOf(prDAO.getBDDATAENT()));
			stm.setString(5, prDAO.getBDDESC());
	
			stm.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return false;
	}

}
