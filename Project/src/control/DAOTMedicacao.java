package control;

import java.sql.Connection;   
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.MTMedicacao;

public class DAOTMedicacao extends MTMedicacao{
    
    private String wSql;
    
    
    //Insert
    public Boolean inserir(MTMedicacao prDAO) {
        Connection c = prDAO.append();
        try {
            wSql = "INSERT INTO `dbpi`.`tmedicacao`(`BDIDMEDICACAO`,`BDNOMEMEDICACAO`,`BDDESCRICAO`)VALUES(?,?,?);";
            PreparedStatement stm = c.prepareStatement(wSql);

            stm.setInt   (1, prDAO.getBDIDMEDICACAO());
            stm.setString(2, prDAO.getBDNOMEMEDICACAO());
            stm.setString(3, prDAO.getBDDESCRICAO());
            
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        prDAO.post();
        return false;
    }
    
    // UPDATE
    public Boolean alterar(MTMedicacao prDAO) {
        Connection c = null;
        PreparedStatement stm = null;
        try {
            c = prDAO.append();
            
            wSql = "UPDATE `dbpi`.`tmedicacao` SET `BDNOMEMEDICACAO` = ?, `BDDESCRICAO` = ? WHERE `BDIDMEDICACAO` = ?;";
            
            stm = c.prepareStatement(wSql);
            
            stm.setString(1, prDAO.getBDNOMEMEDICACAO());
            stm.setString(2, prDAO.getBDDESCRICAO());
            stm.setInt   (3, prDAO.getBDIDMEDICACAO());
            
            int rowsUpdated = stm.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            // Print the stack trace for any exceptions that occur
            e.printStackTrace();
        } finally {
        	prDAO.post();
        }
        return false;
    }
    
    // DELETE
    public Boolean deletar(MTMedicacao prDAO) {
        Connection c = prDAO.append();
        try {
            wSql = "DELETE FROM `dbpi`.`tmedicacao` WHERE BDIDMEDICACAO = ?;";
            PreparedStatement stm = c.prepareStatement(wSql);
            stm.setInt(1, prDAO.getBDIDMEDICACAO());
            stm.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        prDAO.post();
        return false;
    }
        
    // SELECT
    public ArrayList<MTMedicacao> ListTMedicacao(MTMedicacao prDAO) {
        ArrayList<MTMedicacao> ListTaMedicacao = new ArrayList<>();
            
        Connection c = prDAO.append();
        try {
            wSql = "SELECT * FROM tmedicacao";
            Statement stm = c.createStatement();
            ResultSet rs =  stm.executeQuery(wSql);
            
            while (rs.next()) {

                
                MTMedicacao lc = new MTMedicacao();
                
                lc.setBDIDMEDICACAO(rs.getInt("BDIDMEDICACAO"));
                lc.setBDDESCRICAO(rs.getString("BDDESCRICAO"));
                lc.setBDNOMEMEDICACAO(rs.getString("BDNOMEMEDICACAO"));
            
                ListTaMedicacao.add(lc); // Adiciona objeto à lista
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            prDAO.post();
        }

        return ListTaMedicacao;
    }
    
		public boolean existeMedicamento(Integer id) {
    	    Connection c = append();
    	    Boolean existe = null;
    	    try {
    	        Statement stm = c.createStatement();
    	        String wSql = "SELECT BDIDMEDICACAO FROM `dbpi`.`tmedicacao` where BDIDMEDICACAO = "+id;
    	        ResultSet rs =  stm.executeQuery(wSql);

    	     
    	        if(rs.next()) {
    	        	existe = true;
    	        }else {
    	        	existe = false;
    	        }
	    	   
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    } finally {
    	        post();
    	    }
    	    return existe;
    	}
    	   
}