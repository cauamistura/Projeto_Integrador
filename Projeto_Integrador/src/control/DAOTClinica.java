package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.MTClinica;

public class DAOTClinica extends MTClinica {
	
	private static DAOTClinica FDAOTClinica;
	private String wSql;
	
	//Insert
	public Boolean inserir() {
		Connection c = FDAOTClinica.append();
		try {
			wSql = "INSERT INTO `DBPI`.`TClinica`(`BDIDCLINICA`,`BDIDCEP`,`BDCNPJ`,`BDNOME`,`BDNOMEFANTASIA`,`BDSENHA`)VALUES(?,?,?,?,?,?);";
			PreparedStatement stm = c.prepareStatement(wSql);
			
			stm.setInt   (1, FDAOTClinica.getBDIDCLINICA());
			stm.setInt   (2, FDAOTClinica.getBDIDCEP());
			stm.setString(3, FDAOTClinica.getBDCNPJ());
			stm.setString(4, FDAOTClinica.getBDNOME());
			stm.setString(5, FDAOTClinica.getBDNOMEFANTASIA());
			stm.setString(6, FDAOTClinica.getBDSENHA());
			
			stm.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		FDAOTClinica.post();
		return false;
	}
	
	// UPDATE
	public Boolean alterar() {
		Connection c = FDAOTClinica.append();
		try {
			wSql = "UPDATE `dbpi`.`tclinica` SET `BDIDCEP` = ?, `BDCNPJ` = ?, `BDNOME` = ?, `BDNOMEFANTASIA` = ?, `BDSENHA` = ? WHERE `BDIDCLINICA` = ?;";
			PreparedStatement stm = c.prepareStatement(wSql);
			
			stm.setInt   (6, FDAOTClinica.getBDIDCLINICA());
			stm.setInt   (1, FDAOTClinica.getBDIDCEP());
			stm.setString(2, FDAOTClinica.getBDCNPJ());
			stm.setString(3, FDAOTClinica.getBDNOME());
			stm.setString(4, FDAOTClinica.getBDNOMEFANTASIA());
			stm.setString(6, FDAOTClinica.getBDSENHA());
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		FDAOTClinica.post();
		return false;
	}
	
	// DELETE
	public Boolean deletar() {
		Connection c = FDAOTClinica.append();
		
		try {
			wSql = "DELETE FROM `dbpi`.`tclinica` WHERE BDIDCLINICA = ?;";
			PreparedStatement stm = c.prepareStatement(wSql);
			stm.setLong(1, FDAOTClinica.getBDIDCLINICA());
			stm.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		FDAOTClinica.post();
		return false;
	}
		
	// SELECT
	public ArrayList<MTClinica> ListTClinica() {
		ArrayList<MTClinica> ListTaClinica = new ArrayList<>();
		
	    DAOTClinica FDAOTClinica = new DAOTClinica();
	    
		Connection c = FDAOTClinica.append();
		
		try {
			Statement stm = c.createStatement();
			wSql = "SELECT * FROM tclinica";
			ResultSet rs =  stm.executeQuery(wSql);
			
			while (rs.next()) {
				MTClinica lc = new MTClinica();
				
				lc.setBDIDCLINICA	(rs.getInt("BDIDCLINICA"));
				lc.setBDIDCEP	 	(rs.getInt("BDIDCEP"));
				lc.setBDCNPJ	 	(rs.getString("BDCNPJ"));	
				lc.setBDNOME	 	(rs.getString("BDNOME"));
				lc.setBDNOMEFANTASIA(rs.getString("BDNOMEFANTASIA"));
				lc.setBDSENHA	 	(rs.getString("BDSENHA"));
				
				ListTaClinica.add(lc);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		FDAOTClinica.append();
		return ListTaClinica;
	}

}
