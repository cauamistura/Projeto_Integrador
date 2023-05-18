package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.Especie;

public class DAOEspecie extends Especie {

private String wSQL;
	
	// Insert
	public Boolean inserir(DAOEspecie prDAO) {
		Connection c = prDAO.append();
		try {
			wSQL = "INSERT INTO `dbpi`.`tespecie`(`BDIDESPECIE`,`BDNOMEESPECIE`)VALUES(?,?);";
			PreparedStatement stm = c.prepareStatement(wSQL);
			
			stm.setInt(1, prDAO.getBDIDESPECIE());
			stm.setString(2, prDAO.getBDNOMEESPECIE());
			
			stm.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return false;
	}
	
	// Update
		public Boolean alterar(DAOEspecie prDAO) {
			Connection c = prDAO.append();
			try {
				wSQL = "UPDATE `dbpi`.`tespecie` SET `BDIDESPECIE` = ?,`BDNOMEESPECIE` = ? WHERE `BDIDESPECIE` = ?;";
				PreparedStatement stm = c.prepareStatement(wSQL);
				
				stm.setInt(1, prDAO.getBDIDESPECIE());
				stm.setString(2, prDAO.getBDNOMEESPECIE());
		
				return true;	
			} catch (Exception e) {
				e.printStackTrace();
			}
			prDAO.post();
			return false;
		}
		
		// Delete
		public Boolean deletar(DAOEspecie prDAO) {
			Connection c = prDAO.append();
			try {
				wSQL = "DELETE FROM `dbpi`.`tespecie` WHERE BDIDESPECIE = ?;";
				PreparedStatement stm = c.prepareStatement(wSQL);
				
				stm.setInt(1, prDAO.getBDIDESPECIE());
				stm.setString(2, prDAO.getBDNOMEESPECIE());
		
				stm.execute();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			prDAO.post();
			return false;
		}
		
		
		// Select
		public ArrayList<Especie> ListTEspecie(DAOEspecie prDAO) {
			
			ArrayList<Especie> ListTaEspecie = new ArrayList<>();
			Connection c = prDAO.append();
			try {
				wSQL = "SELECT * FROM tespecie";
				Statement stm = c.prepareStatement(wSQL);
				ResultSet rs = stm.executeQuery(wSQL);
				
				while (rs.next()) {
					Especie le = new Especie();

					le.setBDIDESPECIE(rs.getInt("BDIDESPECIE"));
					le.setBDNOMEESPECIE(rs.getString("BDNOMEESPECIE"));
					
					ListTaEspecie.add(le);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			prDAO.post();
			return ListTaEspecie;
		}	
		
	
}
