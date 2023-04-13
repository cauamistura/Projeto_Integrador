package control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
			stm.setString(2, prDAO.getBDNOMEUSER());
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
	        wSql = "SELECT u.BDIDUSER, u.BDIDPERMICAO, u.BDMAIL, u.BDCPF, u.BDSENHA, "
	             + "du.BDNOME, du.BDCEP, du.BDGENERO, du.BDTELEFONE, du.BDDATANASCIMENTO "
	             + "FROM `dbpi`.`tuser` u "
	             + "INNER JOIN `dbpi`.`tdadosuser` du ON (u.BDIDUSER = du.BDIDUSER AND u.BDIDCLINICA = du.BDIDCLINICA) "
	             + "WHERE u.BDIDCLINICA = " + String.valueOf(VMenu.FIDClinica);
	        Statement stm = c.createStatement();
	        ResultSet rs = stm.executeQuery(wSql);
	        
	        while (rs.next()) {
	            MTDadosUser lc = new MTDadosUser();
	            lc.setBDIDUSER(rs.getInt("BDIDUSER"));
	            lc.setBDIDPERMICAO(rs.getInt("BDIDPERMICAO"));
	            lc.setBDMAIL(rs.getString("BDMAIL"));
	            lc.setBDSENHA(rs.getString("BDSENHA"));
	            lc.setBDCPF(rs.getString("BDCPF"));
	            lc.setBDNOMEUSER(rs.getString("BDNOME"));
	            lc.setBDCEP(rs.getInt("BDCEP"));
	            lc.setBDGENERO(rs.getString("BDGENERO"));
	            lc.setBDTELEFONE(rs.getString("BDTELEFONE"));
	            lc.setBDDATANASCIMENTO(rs.getDate("BDDATANASCIMENTO").toLocalDate());
	            
	            ListaDadosUser.add(lc);
	        }
	        rs.close();
	        stm.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        prDAO.post();
	    }
	    
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
					
					lc.setBDNOMEUSER(rs.getString("BDNOME"));;
					lc.setBDIDUSER(rs.getInt("BDIDUSER"));
					
					ListaDadosUser.add(lc);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			prDAO.post();
			return ListaDadosUser;
		}
		
		public boolean alterar(DAOTDadosUser prDAO) {
		    Connection c = null;
		    PreparedStatement stm = null;
		    try {
		        c = prDAO.append();
		        String wSql = "UPDATE `dbpi`.`tdadosuser` SET `BDCEP` = ?, `BDNOME` = ?, `BDGENERO` = ?, `BDTELEFONE` = ?, `BDDATANASCIMENTO` = ? WHERE `BDIDUSER` = ? AND `BDIDCLINICA` = ?";
		        
		        stm = c.prepareStatement(wSql);
		        
		        stm.setInt(1, prDAO.getBDCEP());
		        stm.setString(2, prDAO.getBDNOMEUSER());
		        stm.setString(3, prDAO.getBDGENERO());
		        stm.setString(4, prDAO.getBDTELEFONE());
		        stm.setString(5, String.valueOf(prDAO.getBDDATANASCIMENTO()));
		        stm.setInt(6, prDAO.getBDIDUSER());
		        stm.setInt(7, VMenu.FIDClinica);
		        
		        int rowsAffected = stm.executeUpdate();
		        return rowsAffected > 0;
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        try { if (stm != null) stm.close(); } catch (SQLException e) { }
		        try { if (c != null) c.close(); } catch (SQLException e) { }
		    }
		    return false;
		}
		
		public ArrayList<MTDadosUser> listLogin(DAOTDadosUser prDAO){
			ArrayList<MTDadosUser> listaLogin = new ArrayList<>();
		    
			Connection c = prDAO.append();
			try {
				Statement stm = c.createStatement();
				wSql = " SELECT u.*, c.*, d.BDNOME as NOMEUSER FROM tuser u "
						+ "inner join tclinica c on (u.bdidclinica = c.bdidclinica) "
						+ "inner join tdadosuser d on (u.bdiduser = d.bdiduser)";
				ResultSet rs =  stm.executeQuery(wSql);
				
				while (rs.next()) {
					MTDadosUser lc = new MTDadosUser();
					
					lc.setBDIDUSER(rs.getInt("BDIDUSER"));
					lc.setBDIDCLINICA(rs.getInt("BDIDCLINICA"));
					lc.setBDIDCEP(rs.getInt("BDIDCEP"));
					lc.setBDIDPERMICAO(rs.getInt("BDIDPERMICAO"));
					
					lc.setBDCNPJ(rs.getString("BDCNPJ"));
					lc.setBDCPF(rs.getString("BDCPF"));
					lc.setBDMAIL(rs.getString("BDMAIL"));
					lc.setBDNOME(rs.getString("BDNOME"));
					lc.setBDSENHA(rs.getString("BDSENHA"));
					lc.setBDNOMEUSER(rs.getString("NOMEUSER"));
					
					listaLogin.add(lc);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			prDAO.post();
			return listaLogin;
		}
		
		// SELECT CONSULTA
	public MTDadosUser ListConsultaUserLOG(DAOTDadosUser prDAO) { 
	    Connection c = prDAO.append();
	    MTDadosUser lc = new MTDadosUser();
	    try {
	        wSql = "SELECT u.BDIDUSER, u.BDIDPERMICAO, u.BDMAIL, u.BDCPF, u.BDSENHA, "
	             + "du.BDNOME, du.BDCEP, du.BDGENERO, du.BDTELEFONE, du.BDDATANASCIMENTO "
	             + "FROM `dbpi`.`tuser` u "
	             + "INNER JOIN `dbpi`.`tdadosuser` du ON (u.BDIDUSER = du.BDIDUSER AND u.BDIDCLINICA = du.BDIDCLINICA) "
	             + "WHERE u.BDIDCLINICA = " + String.valueOf(VMenu.FIDClinica)
	             + " and u.bdiduser = " + String.valueOf(VMenu.FIDUSER);
	        Statement stm = c.createStatement();
	        ResultSet rs = stm.executeQuery(wSql);
	        
	        while (rs.next()) {
	            lc.setBDIDUSER(rs.getInt("BDIDUSER"));
	            lc.setBDIDPERMICAO(rs.getInt("BDIDPERMICAO"));
	            lc.setBDMAIL(rs.getString("BDMAIL"));
	            lc.setBDSENHA(rs.getString("BDSENHA"));
	            lc.setBDCPF(rs.getString("BDCPF"));
	            lc.setBDNOMEUSER(rs.getString("BDNOME"));
	            lc.setBDCEP(rs.getInt("BDCEP"));
	            lc.setBDGENERO(rs.getString("BDGENERO"));
	            lc.setBDTELEFONE(rs.getString("BDTELEFONE"));
	            lc.setBDDATANASCIMENTO(rs.getDate("BDDATANASCIMENTO").toLocalDate());
	        }
	        rs.close();
	        stm.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        prDAO.post();
	    }
	    return lc;
	}
}
