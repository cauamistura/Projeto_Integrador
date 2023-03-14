package control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import model.MTPetUser;

public class DAOTPetUser extends MTPetUser {
private String wSql;
	
	// INSERT
	public Boolean inserir(DAOTPetUser prDAO) {
		Connection c = prDAO.append();
		try {
			wSql = "INSERT INTO `dbpi`.`tpets_tuser`(`BDIDPET`,`BDIDUSER`,`BDIDCLINICA``)VALUES(?,?,?);";
			PreparedStatement stm = c.prepareStatement(wSql);
			
			stm.setInt   (1, prDAO.getBDIDPET());
			stm.setInt   (2, prDAO.getBDIDUSER());
			stm.setInt   (3, prDAO.getBDIDCLINICA());
			
			stm.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return false;
	}
}
