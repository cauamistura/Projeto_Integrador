package control;

import java.sql.Connection;   
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Medicamento;

public class DAOMedicacao extends Medicamento{
    
    private String wSql;
    
    
    //Insert
    public Boolean inserir(Medicamento prDAO) {
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
    public Boolean alterar(Medicamento prDAO) {
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
    public Boolean deletar(Medicamento prDAO) {
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
    public ArrayList<Medicamento> ListTMedicacao(Medicamento prDAO) {
        ArrayList<Medicamento> ListTaMedicacao = new ArrayList<>();
            
        Connection c = prDAO.append();
        try {
            wSql = "SELECT * FROM tmedicacao";
            Statement stm = c.createStatement();
            ResultSet rs =  stm.executeQuery(wSql);
            
            while (rs.next()) {

                
                Medicamento lc = new Medicamento();
                
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
    
    public Integer existeMedicamento(String nome, DAOMedicacao prDAO) {
        Connection c = append();
        int idMedicacao = -1; // valor padrão caso não seja encontrado

        try {
            Statement stm = c.createStatement();
            String wSql = "SELECT BDIDMEDICACAO FROM `dbpi`.`tmedicacao` where BDNOMEMEDICACAO = '"+nome+"'";;
            ResultSet rs =  stm.executeQuery(wSql);

            if (rs.next()) { // se há resultados
                idMedicacao = rs.getInt("BDIDMEDICACAO"); // pegar o valor do ID
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            prDAO.post();
        }

        return idMedicacao;
    }
    	   
}