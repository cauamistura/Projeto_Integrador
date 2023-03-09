package control;

import java.sql.Connection;  

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.MTClinica;

public class DAOTClinica extends MTClinica {
	
//	private static DAOTClinica FDAOTClinica;
	private String wSql;
	
	
	//Insert
	public Boolean inserir(DAOTClinica prDAO) {
		Connection c = prDAO.append();
		try {
			wSql = "INSERT INTO `DBPI`.`TClinica`(`BDIDCLINICA`,`BDIDCEP`,`BDCNPJ`,`BDNOME`,`BDNOMEFANTASIA`,`BDSENHA`)VALUES(?,?,?,?,?,?);";
			PreparedStatement stm = c.prepareStatement(wSql);
			
			stm.setInt   (1, prDAO.getBDIDCLINICA());
			stm.setInt(2, prDAO.getBDIDCEP());
			stm.setString(3, prDAO.getBDCNPJ());
			stm.setString(4, prDAO.getBDNOME());
			stm.setString(5, prDAO.getBDNOMEFANTASIA());
			stm.setString(6, prDAO.getBDSENHA());
			
			stm.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return false;
	}
	
	// UPDATE
	public Boolean alterar(DAOTClinica prDAO) {
		Connection c = prDAO.append();
		try {
			wSql = "UPDATE `dbpi`.`tclinica` SET `BDIDCEP` = ?, `BDCNPJ` = ?, `BDNOME` = ?, `BDNOMEFANTASIA` = ?, `BDSENHA` = ? WHERE `BDIDCLINICA` = ?;";
			PreparedStatement stm = c.prepareStatement(wSql);
			
			stm.setInt(1, prDAO.getBDIDCEP());
			stm.setString(2, prDAO.getBDCNPJ());
			stm.setString(3, prDAO.getBDNOME());
			stm.setString(4, prDAO.getBDNOMEFANTASIA());
			stm.setString(5, prDAO.getBDSENHA());
			stm.setInt   (6, prDAO.getBDIDCLINICA());
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return false;
	}
	
	// DELETE
	public Boolean deletar(DAOTClinica prDAO) {
		Connection c = prDAO.append();
		try {
			wSql = "DELETE FROM `dbpi`.`tclinica` WHERE BDIDCLINICA = ?;";
			PreparedStatement stm = c.prepareStatement(wSql);
			stm.setLong(1, prDAO.getBDIDCLINICA());
			stm.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return false;
	}
		
	// SELECT
	public ArrayList<MTClinica> ListTClinica(DAOTClinica prDAO) {
		ArrayList<MTClinica> ListTaClinica = new ArrayList<>();
		    
		Connection c = prDAO.append();
		try {
			wSql = "SELECT * FROM tclinica";
			Statement stm = c.createStatement();
			ResultSet rs =  stm.executeQuery(wSql);
			
			while (rs.next()) {
				MTClinica lc = new MTClinica();
				
				lc.setBDIDCLINICA	(rs.getInt("BDIDCLINICA"));
				lc.setBDCNPJ	 	(rs.getString("BDCNPJ"));	
				lc.setBDNOME	 	(rs.getString("BDNOME"));
				lc.setBDNOMEFANTASIA(rs.getString("BDNOMEFANTASIA"));
				lc.setBDSENHA	 	(rs.getString("BDSENHA"));
				lc.setBDIDCEP       (rs.getInt("BDIDCEP"));
				
				ListTaClinica.add(lc);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return ListTaClinica;
	}

}
