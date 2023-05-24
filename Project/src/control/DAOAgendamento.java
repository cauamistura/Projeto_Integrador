package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import model.Agendamento;

public class DAOAgendamento extends Agendamento{
	
	private String wSql;
	
	//Select 
	//Select 
	public ArrayList<Agendamento> List(DAOAgendamento prDAO) {
	    ArrayList<Agendamento> Lista = new ArrayList<>();

	    Connection c = prDAO.append();
	    try {
	        String dataAgendamento = String.valueOf(prDAO.getDateAgendamento());

	        wSql =    " SELECT a.*, "
	        		+ "	   p.bdnomepet,"
	        		+ "    u.bdnome"
	        		+ " FROM tagendamento a"
	        		+ " inner join tpets p on (p.bdidpet = a.bdidpet)"
	        		+ " inner join tdadosuser u on (u.bdiduser = p.bdiduser)"
	        		+ " where a.bddataagen = ?";
	        
	        PreparedStatement stm = c.prepareStatement(wSql);
	        stm.setString(1, dataAgendamento);
	        ResultSet rs = stm.executeQuery();

	        while (rs.next()) {
	            Agendamento lc = new Agendamento();
	            
	            lc.setDateAgendamento(rs.getDate("bddataagen").toLocalDate());
	            lc.setHora(rs.getString("bdhora"));
	            lc.setId(rs.getInt("bdidagendamento"));
	            lc.setDisponivel(false);
	            
	            lc.setBDIDPET(rs.getInt("bdidpet"));
	            lc.setBDNOMEPET(rs.getString("bdnomepet"));
	            lc.setBDNOMEUSER("bdnome");
	           
	            Lista.add(lc); 
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        prDAO.post();
	    }

	    return Lista;
	}




}
