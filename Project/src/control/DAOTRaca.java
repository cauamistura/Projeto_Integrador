package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.MTEndereco;
import model.MTRaca;

public class DAOTRaca extends MTRaca{
private String wSQL;
	
	// Insert
	public Boolean inserir(DAOTRaca prDAO) {
		Connection c = prDAO.append();
		try {
			wSQL = "INSERT INTO `dbpi`.`traca`(`BDIDRACA`,`BDNOMERACA`,`BDIDESPECIE`)VALUES(?,?,?);";
			PreparedStatement stm = c.prepareStatement(wSQL);
			
			stm.setInt(1, prDAO.getBDIDRACA());
			stm.setString(2, prDAO.getBDNOMERACA());
			stm.setInt(3, prDAO.getBDIDESPECIE());
			
			stm.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return false;
	}
	
	// Update
		public Boolean alterar(DAOTRaca prDAO) {
			Connection c = prDAO.append();
			try {
				wSQL = "UPDATE `dbpi`.`traca` SET `BDNOMERACA` = ?,`BDIDESPECIE` = ? WHERE `BDIDRACA` = ?;";
				PreparedStatement stm = c.prepareStatement(wSQL);
				
				stm.setInt(1, prDAO.getBDIDRACA());
				stm.setString(2, prDAO.getBDNOMERACA());
				stm.setInt(3, prDAO.getBDIDESPECIE());
		
				return true;	
			} catch (Exception e) {
				e.printStackTrace();
			}
			prDAO.post();
			return false;
		}
		
		// Delete
		public Boolean deletar(DAOTRaca prDAO) {
			Connection c = prDAO.append();
			try {
				wSQL = "DELETE FROM `dbpi`.`traca` WHERE BDIDRACA = ?;";
				PreparedStatement stm = c.prepareStatement(wSQL);
				
				stm.setInt(1, prDAO.getBDIDRACA());
				stm.setString(2, prDAO.getBDNOMERACA());
				stm.setInt(3, prDAO.getBDIDESPECIE());
		
				stm.execute();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			prDAO.post();
			return false;
		}
		
		// Select
		public ArrayList<MTRaca> ListTRaca (DAOTRaca prDAO, int idEspecie) {
			
			ArrayList<MTRaca> ListTaRaca = new ArrayList<>();
			Connection c = prDAO.append();
			try {
				
				if (idEspecie == 0) {
					wSQL = "SELECT * FROM traca";
				} else {
					wSQL = "SELECT * FROM traca WHERE BDIDESPECIE = " + idEspecie;
				}
		
				Statement stm = c.prepareStatement(wSQL);
				
				ResultSet rs = stm.executeQuery(wSQL);
				
				while (rs.next()) {
					MTRaca le = new MTRaca();
					
					le.setBDIDESPECIE(rs.getInt("BDIDESPECIE"));
					le.setBDNOMERACA(rs.getString("BDNOMERACA"));
					le.setBDIDRACA(rs.getInt("BDIDRACA"));
					
					ListTaRaca.add(le);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			prDAO.post();
			return ListTaRaca;
		}
}
