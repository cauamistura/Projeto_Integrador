package control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Receita;

public class DAOReceita extends Receita{
	
private String wSql;
	
	//Insert
	public Boolean inserir(DAOReceita prDAO) {
		Connection c = prDAO.append();
		try {
			wSql = "INSERT INTO `dbpi`.`treceita`(`BDIDRECEITA`,`BDIDMEDICACAO`,`BDINICIORECEITA`,`BDFINALRECEITA`,`BDDESCRICAO`)VALUES(?,?,?,?,?);";
			PreparedStatement stm = c.prepareStatement(wSql);
			
			stm.setInt   (1, prDAO.getBDIDRECEITA());
			stm.setInt(2, prDAO.getBDIDMEDICACAO());
			stm.setDate(3,Date.valueOf(getBDINICIORECEITA()));
			stm.setDate(4,Date.valueOf(getBDFINALRECEITA()));
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
	public Boolean alterar(DAOReceita prDAO) {
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
	public Boolean deletar(DAOReceita prDAO) {
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
	public ArrayList<Receita> listTReceita(DAOReceita prDAO) {
		ArrayList<Receita> ListTReceita = new ArrayList<>();
		    
		Connection c = prDAO.append();
		try {
			wSql = "SELECT r.*, m.BDNOMEMEDICACAO FROM treceita r "
					+ "inner join tmedicacao m on (r.BDIDMEDICACAO = m.BDIDMEDICACAO)";
			
			Statement stm = c.createStatement();
			ResultSet rs =  stm.executeQuery(wSql);
			
			while (rs.next()) {
				Receita lr = new Receita();
				
				
				lr.setBDIDRECEITA(rs.getInt("BDIDRECEITA"));
				lr.setBDIDMEDICACAO(rs.getInt("BDIDMEDICACAO"));
				lr.setBDFINALRECEITA(rs.getDate("BDFINALRECEITA").toLocalDate());
				lr.setBDINICIORECEITA(rs.getDate("BDINICIORECEITA").toLocalDate());
				lr.setBDDESCRICAO(rs.getString("BDDESCRICAO"));
				lr.setBDNOMEMEDICACAO(rs.getString("BDNOMEMEDICACAO"));

				ListTReceita.add(lr);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return ListTReceita;
	}
}
