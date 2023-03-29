package control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.MTEstado;

public class DAOTEstado extends ObjectDAO{
	
	private String wSQL;
	
	public ArrayList<MTEstado> ListTEstado (DAOTEstado prDAO) {
		
		ArrayList<MTEstado> ListTaEstado = new ArrayList<>();
		Connection c = prDAO.append();
		try {
			wSQL = "Select * from testados";
			Statement stm = c.prepareStatement(wSQL);
			ResultSet rs = stm.executeQuery(wSQL);
			
			while (rs.next()) {
				MTEstado le = new MTEstado();
				
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
	
	public ArrayList<MTEstado> ListTEstadoCON (DAOTEstado prDAO) {
		
		ArrayList<MTEstado> ListTEstadoCON = new ArrayList<>();
		Connection c = prDAO.append();
		try {
			wSQL = "select e.BDCEP, c.BDNOMECID, uf.BDSIGLAUF from tendereco e"
					+ "inner join tcidades c on (e.BDIDCIDADE = c.BDIDCIDADE)"
					+ "inner join testados uf on (c.BDIDUF = uf.BDIDUF)";
			Statement stm = c.prepareStatement(wSQL);
			ResultSet rs = stm.executeQuery(wSQL);
			
			while (rs.next()) {
				MTEstado le = new MTEstado();
				
				le.setBDNOMEUF(rs.getString("BDNOMEUF"));
				le.setBDIDUF(rs.getInt("BDIDUF"));
				le.setBDSIGLAUF(rs.getString("BDSIGLAUF"));
				
				ListTEstadoCON.add(le);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return ListTEstadoCON;
	}	
}
