package control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.Permicao;

public class DAOPermicao extends Permicao{
	private String wSQL;
	
	public ArrayList<Permicao> ListTEstado(DAOPermicao prDAO) {
	    ArrayList<Permicao> listPermicao = new ArrayList<>();
	    Connection c = prDAO.append();
	    try {
	        wSQL = "SELECT `tpermicao`.`BDIDPERMICAO`, "
	            + "`tpermicao`.`BDPERMICAO`, "
	            + "`tpermicao`.`BDDESCRISSAO` "
	            + "FROM `dbpi`.`tpermicao`;";
	        Statement stm = c.prepareStatement(wSQL);
	        ResultSet rs = stm.executeQuery(wSQL);

	        while (rs.next()) {
	            Permicao le = new Permicao();

	            le.setBDIDPERMICAO(rs.getInt("BDIDPERMICAO"));
	            le.setBDPERMICAO(rs.getString("BDPERMICAO"));
	            le.setBDDESCRISSAO(rs.getString("BDDESCRISSAO"));

	            listPermicao.add(le);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    prDAO.post();
	    return listPermicao;
	}

	
}
