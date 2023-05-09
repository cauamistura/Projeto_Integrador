package control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.MTReceita;

public class DAOTReceita extends MTReceita{
	
private String wSql;
	
	//Insert
	public Boolean inserir(DAOTReceita prDAO) {
		Connection c = prDAO.append();
		try {
			wSql = "INSERT INTO `dbpi`.`treceita`(`BDIDRECEITA`,`BDIDMEDICACAO`,`BDINICIORECEITA`,`BDFINALRECEITA`,`BDDESCRICAO`)VALUES(?,?,?,?,?);";
			PreparedStatement stm = c.prepareStatement(wSql);
			
			stm.setInt   (1, prDAO.getBDIDRECEITA());
			stm.setInt(2, prDAO.getBDIDMEDICACAO());
			stm.setDate(3,Date.valueOf(getBDFINALRECEITA()));
			stm.setDate(4,Date.valueOf(getBDINICIORECEITA()));
			stm.setString(5, prDAO.getBDDESCRICAO());
			
			stm.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return false;
	}
	
	// UPDATE
	public Boolean alterar(DAOTReceita prDAO) {
		Connection c = null;
	    PreparedStatement stm = null;
		try {
			c = prDAO.append();
			
			wSql = "UPDATE dbpi.treceita SET BDIDMEDICACAO = ?, BDINICIORECEITA = ?, BDFINALRECEITA = ?, BDDESCRICAO = ? WHERE BDIDRECEITA = ?";
	       
			stm = c.prepareStatement(wSql);
			
			stm.setInt(1, prDAO.getBDIDMEDICACAO());
	        stm.setDate(2, Date.valueOf(prDAO.getBDINICIORECEITA()));
	        stm.setDate(3, Date.valueOf(prDAO.getBDFINALRECEITA()));
	        stm.setString(4, prDAO.getBDDESCRICAO());
	        stm.setInt(5, prDAO.getBDIDRECEITA());
	        
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
	public Boolean deletar(DAOTReceita prDAO) {
		Connection c = prDAO.append();
		try {
			wSql = "DELETE FROM `dbpi`.`dbpi`.`treceita` WHERE `BDIDRECEITA` = ?;";
			PreparedStatement stm = c.prepareStatement(wSql);
			stm.setLong(1, prDAO.getBDIDRECEITA());
			stm.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return false;
	}
		
	// SELECT
	public ArrayList<MTReceita> listTReceita(DAOTReceita prDAO) {
		ArrayList<MTReceita> ListTReceita = new ArrayList<>();
		    
		Connection c = prDAO.append();
		try {
			wSql = "SELECT * FROM treceita";
			Statement stm = c.createStatement();
			ResultSet rs =  stm.executeQuery(wSql);
			
			while (rs.next()) {
				MTReceita lr = new MTReceita();
				
				
				lr.setBDIDRECEITA(rs.getInt("BDIDRECEITA"));
				lr.setBDIDMEDICACAO(rs.getInt("BDIDMEDICACAO"));
				lr.setBDFINALRECEITA(rs.getDate("BDFINALRECEITA").toLocalDate());
				lr.setBDINICIORECEITA(rs.getDate("BDINICIORECEITA").toLocalDate());
				lr.setBDDESCRICAO(rs.getString("BDDESCRICAO"));
				

				ListTReceita.add(lr);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return ListTReceita;
	}
}
