package control;

import java.sql.Connection;   
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.MTEndereco;
 
public class DAOTEndereco extends MTEndereco{
	
	private String wSQL;
	
	// Insert
	public Boolean inserir(DAOTEndereco prDAO) {
		Connection c = prDAO.append();
		try {
			wSQL = "INSERT INTO `dbpi`.`tendereco`(`BDCEP`,`BDIDCIDADE`,`BDBAIRRO`)VALUES(?,?,?);";
			PreparedStatement stm = c.prepareStatement(wSQL);
			
			stm.setInt(1, prDAO.getBDCEP());
			stm.setString(3, prDAO.getBDBAIRRO());
			
			stm.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return false;
	}
	
	// Update
	public Boolean alterar(DAOTEndereco prDAO) {
		Connection c = prDAO.append();
		try {
			wSQL = "UPDATE `dbpi`.`tendereco`SET `BDCEP` = ?,`BDIDCIDADE` = ?,`BDBAIRRO` = ? WHERE `BDCEP` = ?;";
			PreparedStatement stm = c.prepareStatement(wSQL);
			
			stm.setInt(1, prDAO.getBDCEP());
			stm.setInt(2, prDAO.getBDIDCIDADE());
			stm.setString(3, prDAO.getBDBAIRRO());
	
			return true;	
		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return false;
	}
	
	// Delete
	public Boolean deletar(DAOTEndereco prDAO) {
		Connection c = prDAO.append();
		try {
			wSQL = "DELETE FROM `dbpi`.`tendereco` WHERE BDCEP = ?;";
			PreparedStatement stm = c.prepareStatement(wSQL);
			
			stm.setInt(1, prDAO.getBDCEP());
			stm.setInt(2, prDAO.getBDIDCIDADE());
			stm.setString(3, prDAO.getBDBAIRRO());
	
			stm.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return false;
	}	
	
	// Select
	public ArrayList<MTEndereco> ListTEndereco (DAOTEndereco prDAO) {
		
		ArrayList<MTEndereco> ListTaEndereco = new ArrayList<>();
		Connection c = prDAO.append();
		try {
			wSQL = "SELECT * FROM tendereco";
			Statement stm = c.prepareStatement(wSQL);
			ResultSet rs = stm.executeQuery(wSQL);
			
			while (rs.next()) {
				MTEndereco le = new MTEndereco();

				le.setBDBAIRRO(rs.getString("BDBAIRRO"));
				le.setBDCEP(rs.getInt("BDCEP"));
				le.setBDIDCIDADE(rs.getInt("BDIDCIDADE"));
				
				ListTaEndereco.add(le);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return ListTaEndereco;
	}	
		
	}

		
	

