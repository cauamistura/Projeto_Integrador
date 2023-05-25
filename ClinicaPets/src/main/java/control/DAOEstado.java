package control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.Estado;

public class DAOEstado extends ObjectDAO{
	
	private String wSQL;
	
	public ArrayList<Estado> ListTEstado (DAOEstado prDAO) {
		
		ArrayList<Estado> ListTaEstado = new ArrayList<>();
		Connection c = prDAO.append();
		try {
			wSQL = "Select * from testados";
			Statement stm = c.prepareStatement(wSQL);
			ResultSet rs = stm.executeQuery(wSQL);
			
			while (rs.next()) {
				Estado le = new Estado();
				
				le.setBDNOMEUF(rs.getString("BDNOMEUF"));
				le.setBDIDUF(rs.getInt("BDIDUF"));
				le.setBDSIGLAUF(rs.getString("BDSIGLAUF"));
				
				ListTaEstado.add(le);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return ListTaEstado;
	}	
}
