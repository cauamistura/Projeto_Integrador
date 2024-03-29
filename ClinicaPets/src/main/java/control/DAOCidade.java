package control;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.Cidade;

public class DAOCidade extends Cidade{
	
	private String wSQL;
	
	// Insert 
	public Boolean inserir(DAOCidade prDAO) {
		Connection c = prDAO.append();
		try {
			wSQL = "INSERT INTO `dbpi`.`tcidades`(`BDIDCIDADE`,`BDNOMECID`,`BDDESCCID`,`BDIDUF`)VALUES (?,?,?,?);";
			PreparedStatement stm = c.prepareStatement(wSQL);
			
			stm.setInt(1, prDAO.getBDIDCIDADE());
			stm.setString(2, prDAO.getBDNOMECID());
			stm.setString(3, prDAO.getBDDESCCID());
			stm.setInt(4, prDAO.getBDIDUF());
			
		
			stm.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return false;	
	}
	// Update
		public Boolean alterar(DAOCidade prDAO) {
			Connection c = prDAO.append();
			try {
				wSQL = "UPDATE `dbpi`.`tcidades`SET`BDIDCIDADE` = ?,`BDNOMECID` = ?,`BDDESCCID` = ?,`BDIDUF` = ?WHERE `BDIDCIDADE` = ?;";
				PreparedStatement stm = c.prepareStatement(wSQL);
				
				stm.setInt(1, prDAO.getBDIDCIDADE());
				stm.setString(2, prDAO.getBDNOMECID());
				stm.setString(3, prDAO.getBDDESCCID());
				stm.setInt(4, prDAO.getBDIDUF());
		
				return true;	
			} catch (Exception e) {
				e.printStackTrace();
			}
			prDAO.post();
			return false;
		}

		// Delete
		public Boolean deletar(DAOCidade prDAO) {
			Connection c = prDAO.append();
			try {
				wSQL = "DELETE FROM `dbpi`.`tcidades`WHERE BDIDCIDADE;";
				PreparedStatement stm = c.prepareStatement(wSQL);
				
				stm.setInt(1, prDAO.getBDIDCIDADE());
				stm.setString(2, prDAO.getBDNOMECID());
				stm.setString(3, prDAO.getBDDESCCID());
				stm.setInt(4, prDAO.getBDIDUF());
				
				stm.execute();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			prDAO.post();
			return false;
		}	
		
		// Select
		public ArrayList<Cidade> ListTCidade (DAOCidade prDAO) {
			
			ArrayList<Cidade> ListTaCidade = new ArrayList<>();
			Connection c = prDAO.append();
			try {
				wSQL = "SELECT `tcidades`.* FROM `dbpi`.`tcidades`;";
				Statement stm = c.prepareStatement(wSQL);
				ResultSet rs = stm.executeQuery(wSQL);
				
				while (rs.next()) {
					Cidade lc = new Cidade();
					
					lc.setBDIDCIDADE(rs.getInt   ("BDIDCIDADE"));
					lc.setBDDESCCID(rs.getString("BDDESCCID"));
					lc.setBDIDUF	(rs.getInt   ("BDIDUF"));
					lc.setBDNOMECID (rs.getString("BDNOMECID"));
					
					ListTaCidade.add(lc);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			prDAO.post();
			return ListTaCidade;
		}	
}
