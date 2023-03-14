package control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import model.MTDadosUser;

public class DAOTDadosUser extends MTDadosUser {
	
	private String wSql;
	
	//Insert
	public Boolean inserir(DAOTDadosUser prDAO) {
		Connection c = prDAO.append();
		try {
			wSql = "INSERT INTO `dbpi`.`tdadosuser`(`BDCEP`,`BDNOME`,`BDGENERO`,`BDTELEFONE`,`BDDATANASCIMENTO`,`BDIDUSER`,`BDIDCLINICA`)VALUES(?,?,?,?,?,?,?);";
			PreparedStatement stm = c.prepareStatement(wSql);
			
			stm.setInt	 (1, prDAO.getBDCEP());
			stm.setString(2, prDAO.getBDNOME());
			stm.setString(3, prDAO.getBDGENERO());
			stm.setString(4, prDAO.getBDTELEFONE());
			stm.setDate	 (5, Date.valueOf(prDAO.getBDDATANASCIMENTO()));
			stm.setInt	 (6, prDAO.getBDIDUSER());
			stm.setInt	 (7, prDAO.getBDIDCLINICA());
			
			stm.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return false;
	}
	

}
