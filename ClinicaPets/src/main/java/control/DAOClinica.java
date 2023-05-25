package control;

import java.sql.Connection;  

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Clinica;

public class DAOClinica extends Clinica {
	
//	private static DAOTClinica FDAOTClinica;
	private String wSql;
	
	
	//Insert
	public Boolean inserir(DAOClinica prDAO) {
		Connection c = prDAO.append();
		try {
			wSql = "INSERT INTO `dbpi`.`tclinica`(`BDIDCLINICA`,`BDIDCEP`,`BDCNPJ`,`BDNOME`,`BDNOMEFANTASIA`,`BDSENHA`)VALUES(?,?,?,?,?,?);";
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
	public Boolean alterar(DAOClinica prDAO) {
		Connection c = null;
	    PreparedStatement stm = null;
		try {
			c = prDAO.append();
			
			wSql = "UPDATE `dbpi`.`tclinica` SET `BDIDCEP` = ?, `BDCNPJ` = ?, `BDNOME` = ?, `BDNOMEFANTASIA` = ?, `BDSENHA` = ? WHERE `BDIDCLINICA` = ?;";
			
			stm = c.prepareStatement(wSql);
			
			stm.setInt(1, prDAO.getBDIDCEP());
			stm.setString(2, prDAO.getBDCNPJ());
			stm.setString(3, prDAO.getBDNOME());
			stm.setString(4, prDAO.getBDNOMEFANTASIA());
			stm.setString(5, prDAO.getBDSENHA());
			stm.setInt   (6, prDAO.getBDIDCLINICA());
			
			 int rowsUpdated = stm.executeUpdate();
		        return rowsUpdated > 0;
		    } catch (Exception e) {
		        // Print the stack trace for any exceptions that occur
		        e.printStackTrace();
		    } finally {
		        try { if (stm != null) stm.close(); } catch (SQLException e) { }
		        try { if (c != null) c.close(); } catch (SQLException e) { }
		    }
		    return false;
		}
	
	// DELETE
	public Boolean deletar(DAOClinica prDAO) {
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
	public ArrayList<Clinica> ListTClinica(DAOClinica prDAO) {
		ArrayList<Clinica> ListTaClinica = new ArrayList<>();
		    
		Connection c = prDAO.append();
		try {
			wSql = "SELECT * FROM tclinica";
			Statement stm = c.createStatement();
			ResultSet rs =  stm.executeQuery(wSql);
			
			while (rs.next()) {
				Clinica lc = new Clinica();
				
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
