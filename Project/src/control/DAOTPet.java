package control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.MTPet;

public class DAOTPet extends MTPet {

	private String wSql;
	
	// INSERT
		public Boolean inserir(DAOTPet prDAO) {
			Connection c = prDAO.append();
			try {
				wSql = "INSERT INTO `DBPI`.`TPets`(`BDIDPET`, `BDIDRACA`, `BDNOMEPET`, `BDAPELIDO`, `BDDATANASCIMENTO`, `BDIDUSER`)VALUES(?,?,?,?,?,?);";
				PreparedStatement stm = c.prepareStatement(wSql);
				
				stm.setInt(1, prDAO.getBDIDPET());
				stm.setInt(2, prDAO.getBDIDRACA());
				stm.setString(3, prDAO.getBDNOMEPET());
				stm.setString(4, prDAO.getBDAPELIDO());
				stm.setDate	 (5, Date.valueOf(prDAO.getBDDATANASCIMENTO()));
				stm.setInt(6, prDAO.getBDIDUSER());
				
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
				wSql = "UPDATE `dbpi`.`tpets` SET `BDIDPET` = ?, `BDIDRACA` = ?, `BDNOMEPET` = ?, `BDAPELIDO` = ?, `BDDATANASCIMENTO` = ?, `BDIDUSER` = ? WHERE `BDIDPET` = ?;";
				PreparedStatement stm = c.prepareStatement(wSql);
				
				stm.setInt(1, prDAO.getBDIDPET());
				stm.setInt(2, prDAO.getBDIDRACA());
				stm.setString(3, prDAO.getBDNOMEPET());
				stm.setString(4, prDAO.getBDAPELIDO());
				stm.setDate	 (5, Date.valueOf(prDAO.getBDDATANASCIMENTO()));
				stm.setInt(6, prDAO.getBDIDUSER());
		
				return true;	
			} catch (Exception e) {
				e.printStackTrace();
			}
			prDAO.post();
			return false;
		}
		
		// DELETE
		public Boolean deletar(Integer idPet) {
			// Instacia coenexão
			Conexao.getInstacia();
			// Conecta
			Connection c = Conexao.conectar();
			try {
				wSql = "DELETE FROM `dbpi`.`tpets` WHERE BDIDPET = ?;";
				PreparedStatement stm = c.prepareStatement(wSql);
				
				stm.setInt(1, idPet);
		
				stm.execute();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				Conexao.getInstacia().fecharConnection();
			}
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
					le.setBDIDUSER(rs.getInt("BDIDUSER"));
					
					ListaTePet.add(le);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			prDAO.post();
			return ListaTePet;
		}
		
		// SELECT FILTRADO
		public ArrayList<MTPet> listTPetFiltradoUser(DAOTPet prDAO) {
		    ArrayList<MTPet> listaDePets = new ArrayList<>();
		    Connection conexao = prDAO.append();
		    try {
		        String sql = "SELECT `tpets`.* FROM `dbpi`.`tpets` where BDIDUSER = ?";
		        PreparedStatement stm = conexao.prepareStatement(sql);
		        stm.setInt(1, prDAO.getBDIDUSER());
		        ResultSet rs = stm.executeQuery();
		        while (rs.next()) {
		            MTPet pet = new MTPet();
		            pet.setBDIDPET(rs.getInt("BDIDPET"));
		            pet.setBDIDRACA(rs.getInt("BDIDRACA"));
		            pet.setBDNOMEPET(rs.getString("BDNOMEPET"));
		            pet.setBDAPELIDO(rs.getString("BDAPELIDO"));
		            pet.setBDDATANASCIMENTO(rs.getDate("BDDATANASCIMENTO").toLocalDate());
		            pet.setBDIDUSER(rs.getInt("BDIDUSER"));
		            listaDePets.add(pet);
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    prDAO.post(); // Este método fecha a conexão com o banco de dados
		    return listaDePets;
		}

}
