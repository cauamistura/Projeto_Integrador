package control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.MTDadosUser;
import model.MTUser;
import vision.VMenu;

public class DAOTDadosUser extends MTDadosUser {
	
	private String wSql;
	
	//Insert
	public Boolean inserir(DAOTDadosUser prDAO) {
		Connection c = prDAO.append();
		try {
			wSql = "INSERT INTO `dbpi`.`tdadosuser`(`BDCEP`,`BDNOME`,`BDGENERO`,`BDTELEFONE`,`BDDATANASCIMENTO`,`BDIDUSER`,`BDIDCLINICA`)VALUES(?,?,?,?,?,?,?);";
			PreparedStatement stm = c.prepareStatement(wSql);
			
			stm.setInt	 (1, prDAO.getBDCEP());
			stm.setString(2, prDAO.getBDNOME());
			stm.setString(3, prDAO.getBDGENERO());
			stm.setString(4, prDAO.getBDTELEFONE());
			stm.setDate	 (5, Date.valueOf(prDAO.getBDDATANASCIMENTO()));
			stm.setInt	 (6, prDAO.getBDIDUSER());
			stm.setInt	 (7, prDAO.getBDIDCLINICA());
			
			stm.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return false;
	}
	

		// SELECT CONSULTA
		public ArrayList<MTDadosUser> ListConsulta(DAOTDadosUser prDAO) {
			ArrayList<MTDadosUser> ListaDadosUser = new ArrayList<>();
			    
			Connection c = prDAO.append();
			try {
				Statement stm = c.createStatement();
				wSql = "SELECT u.BDCPF, du.BDNOME, u.BDMAIL FROM `dbpi`.`tuser` u inner join `dbpi`.`tdadosuser` du on (u.BDIDUSER = du.BDIDUSER and u.BDIDCLINICA = du.BDIDCLINICA) "
					 + "where u.BDIDCLINICA = " + String.valueOf(VMenu.FIDClinica)+"";
				ResultSet rs =  stm.executeQuery(wSql);
				
				while (rs.next()) {
					MTDadosUser lc = new MTDadosUser();
					
					lc.setBDCPF (rs.getString("BDCPF"));
					lc.setBDNOME(rs.getString("BDNOME"));
					lc.setBDMAIL(rs.getString("BDMAIL"));
					
					ListaDadosUser.add(lc);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			prDAO.post();
			return ListaDadosUser;
		}
		
		// SELECT  
		public ArrayList<MTDadosUser> ListTDadosUser(DAOTDadosUser prDAO) {
			ArrayList<MTDadosUser> ListaDadosUser = new ArrayList<>();
			    
			Connection c = prDAO.append();
			try {
				Statement stm = c.createStatement();
				wSql = "SELECT `tdadosuser`.* FROM `dbpi`.`tdadosuser` where BDIDCLINICA = " + String.valueOf(VMenu.FIDClinica)+" ;";
				ResultSet rs =  stm.executeQuery(wSql);
				
				while (rs.next()) {
					MTDadosUser lc = new MTDadosUser();
					
					lc.setBDNOME(rs.getString("BDNOME"));
					lc.setBDIDUSER(rs.getInt("BDIDUSER"));
					
					ListaDadosUser.add(lc);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			prDAO.post();
			return ListaDadosUser;
		}
}
