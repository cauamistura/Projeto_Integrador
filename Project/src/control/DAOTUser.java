package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.MTClinica;
import model.MTUser;
import vision.VMenu;

public class DAOTUser extends MTUser{
	
	private String wSql;
	
	//Insert
	public Boolean inserir(DAOTUser prDAO) {
		Connection c = prDAO.append();
		try {
			wSql = "INSERT INTO `dbpi`.`tuser` (`BDIDUSER`,`BDIDCLINICA`,`BDCPF`,`BDMAIL`,`BDSENHA`,`BDIDPERMICAO`) VALUES(?,?,?,?,?,?);";
			PreparedStatement stm = c.prepareStatement(wSql);
			
			stm.setInt   (1, prDAO.getBDIDUSER());
			stm.setInt   (2, prDAO.getBDIDCLINICA());
			stm.setString(3, prDAO.getBDCPF());
			stm.setString(4, prDAO.getBDMAIL());
			stm.setString(5, prDAO.getBDSENHA());
			stm.setInt   (6, prDAO.getBDIDPERMICAO());
			
			stm.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return false;
	}
	
	// UPDATE
	public Boolean alterar(DAOTUser prDAO) {
//		Connection c = prDAO.append();
//		try {
//			wSql = "UPDATE `dbpi`.`tclinica` SET `BDIDCEP` = ?, `BDCNPJ` = ?, `BDNOME` = ?, `BDNOMEFANTASIA` = ?, `BDSENHA` = ? WHERE `BDIDCLINICA` = ?;";
//			PreparedStatement stm = c.prepareStatement(wSql);
//			
//			stm.setInt   (6, prDAO.getBDIDCLINICA());
//			stm.setInt   (1, prDAO.getBDIDCEP());
//			stm.setString(2, prDAO.getBDCNPJ());
//			stm.setString(3, prDAO.getBDNOME());
//			stm.setString(4, prDAO.getBDNOMEFANTASIA());
//			stm.setString(5, prDAO.getBDSENHA());
//			
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		prDAO.post();
		return false;
	}
	
	// DELETE
	public Boolean deletar(DAOTUser prDAO) {
		Connection c = prDAO.append();
		try {
			wSql = "DELETE FROM `dbpi`.`tuser` WHERE BDIDUSER = ?;";
			PreparedStatement stm = c.prepareStatement(wSql);
			stm.setLong(1, prDAO.getBDIDUSER());
			stm.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return false;
	}
		
	// SELECT
	public ArrayList<MTUser> ListTUser(DAOTUser prDAO) {
		ArrayList<MTUser> ListaUser = new ArrayList<>();
		    
		Connection c = prDAO.append();
		try {
			Statement stm = c.createStatement();
			wSql = "SELECT * FROM tuser";
			ResultSet rs =  stm.executeQuery(wSql);
			
			while (rs.next()) {
				MTUser lc = new MTUser();
				
				lc.setBDIDUSER	  (rs.getInt("BDIDUSER"));
				lc.setBDIDCLINICA (rs.getInt("BDIDCLINICA"));
				lc.setBDCPF		  (rs.getString("BDCPF"));
				lc.setBDMAIL	  (rs.getString("BDMAIL"));
				lc.setBDSENHA	  (rs.getString("BDSENHA"));
				lc.setBDIDPERMICAO(rs.getInt("BDIDPERMICAO"));
			
				ListaUser.add(lc);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return ListaUser;
	}
	
	public Boolean getExsisteUSER(DAOTUser prDAO, String prCPF) {
		Connection c = prDAO.append();
		try {
			Statement stm = c.createStatement();
			
			String wSql = "SELECT BDCPF FROM `dbpi`.`tuser` t where t.BDIDCLINICA = "+String.valueOf(VMenu.FIDClinica)+" and t.BDCPF = '"+prCPF+"'";
			
			ResultSet rs =  stm.executeQuery(wSql);

	        if(rs.next()){
	        	if(rs.getString("BDCPF").equals(prCPF)){
	        		return true;
	        	}
	        }

		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return false;
	}
	
}

