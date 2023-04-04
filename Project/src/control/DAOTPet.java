package control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import model.MTPet;

public class DAOTPet extends MTPet {

	private String wSql;
	
	// INSERT
		public Boolean inserir(DAOTPet prDAO) {
			Connection c = prDAO.append();
			try {
				wSql = "INSERT INTO `DBPI`.`TPets`(`BDIDPET`, `BDIDRACA`, `BDNOMEPET`, `BDAPELIDO`, `BDDATANASCIMENTO`)VALUES(?,?,?,?,?);";
				PreparedStatement stm = c.prepareStatement(wSql);
				
				stm.setInt(1, prDAO.getBDIDPET());
				stm.setInt(2, prDAO.getBDIDRACA());
				stm.setString(3, prDAO.getBDNOMEPET());
				stm.setString(4, prDAO.getBDAPELIDO());
				stm.setDate	 (5, Date.valueOf(prDAO.getBDDATANASCIMENTO()));
				
				stm.executeUpdate();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			prDAO.post();
			return false;
		}
		
		
		// UPDATE
		public Boolean alterar(DAOTPet prDAO) {
			Connection c = prDAO.append();
			try {
				wSql = "UPDATE `dbpi`.`tpets` SET `BDIDPET` = ?, `BDIDRACA` = ?, `BDNOMEPET` = ?, `BDAPELIDO` = ?, `BDDATANASCIMENTO` = ? WHERE `BDIDPET` = ?;";
				PreparedStatement stm = c.prepareStatement(wSql);
				
				stm.setInt(1, prDAO.getBDIDPET());
				stm.setInt(2, prDAO.getBDIDRACA());
				stm.setString(3, prDAO.getBDNOMEPET());
				stm.setString(4, prDAO.getBDAPELIDO());
				stm.setDate	 (5, Date.valueOf(prDAO.getBDDATANASCIMENTO()));
		
				return true;	
			} catch (Exception e) {
				e.printStackTrace();
			}
			prDAO.post();
			return false;
		}
		
		// DELETE
		public Boolean deletar(DAOTPet prDAO) {
			Connection c = prDAO.append();
			try {
				wSql = "DELETE FROM `dbpi`.`tpets` WHERE BDIDPET = ?;";
				PreparedStatement stm = c.prepareStatement(wSql);
				
				stm.setInt(1, prDAO.getBDIDPET());
				stm.setInt(2, prDAO.getBDIDRACA());
				stm.setString(3, prDAO.getBDNOMEPET());
				stm.setString(4, prDAO.getBDAPELIDO());
				stm.setDate	 (5, Date.valueOf(prDAO.getBDDATANASCIMENTO()));
		
				stm.execute();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			prDAO.post();
			return false;
		}
		
		// SELECT
		public ArrayList<MTPet> ListTPet (DAOTPet prDAO) {
			
			ArrayList<MTPet> ListaTePet = new ArrayList<>();
			Connection c = prDAO.append();
			try {
				wSql = "SELECT * FROM TPets";
				Statement stm = c.prepareStatement(wSql);
				ResultSet rs = stm.executeQuery(wSql);
				
				while (rs.next()) {
					MTPet le = new MTPet();
					
					le.setBDIDPET(rs.getInt("BDIDPET"));
					le.setBDIDRACA(rs.getInt("BDIDRACA"));
					le.setBDNOMEPET(rs.getString("BDNOMEPET"));
					le.setBDAPELIDO(rs.getString("BDAPELIDO"));
					le.setBDDATANASCIMENTO(rs.getDate("BDDATANASCIMENTO").toLocalDate());
					
					ListaTePet.add(le);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			prDAO.post();
			return ListaTePet;
		}
}
