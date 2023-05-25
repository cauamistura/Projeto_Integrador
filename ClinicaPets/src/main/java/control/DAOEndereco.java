package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.Endereco;
 
public class DAOEndereco extends Endereco{
	
	private String wSQL;
	
	// Insert
	public Boolean inserir(DAOEndereco prDAO) {
		Connection c = prDAO.append();
		try {
			wSQL = "INSERT INTO `dbpi`.`tendereco`(`BDCEP`,`BDIDCIDADE`,`BDBAIRRO`)VALUES(?,?,?);";
			PreparedStatement stm = c.prepareStatement(wSQL);
			
			stm.setInt(1, prDAO.getBDCEP());
			stm.setInt(2, prDAO.getBDIDCIDADE());
			stm.setString(3, prDAO.getBDBAIRRO());
			
			stm.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return false;
	}
	
	// Update
	public Boolean alterar(DAOEndereco prDAO) {
		Connection c = prDAO.append();
		try {
			wSQL = "UPDATE `dbpi`.`tendereco`SET `BDCEP` = ?,`BDIDCIDADE` = ?,`BDBAIRRO` = ? WHERE `BDCEP` = ?;";
			PreparedStatement stm = c.prepareStatement(wSQL);
			
			stm.setInt(1, prDAO.getBDCEP());
			stm.setInt(2, prDAO.getBDIDCIDADE());
			stm.setString(3, prDAO.getBDBAIRRO());
	
			return true;	
		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return false;
	}
	
	// Delete
	public Boolean deletar(DAOEndereco prDAO) {
		Connection c = prDAO.append();
		try {
			wSQL = "DELETE FROM `dbpi`.`tendereco` WHERE BDCEP = ?;";
			PreparedStatement stm = c.prepareStatement(wSQL);
			
			stm.setInt(1, prDAO.getBDCEP());
			stm.setInt(2, prDAO.getBDIDCIDADE());
			stm.setString(3, prDAO.getBDBAIRRO());
	
			stm.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return false;
	}	
	
	// Select
	public ArrayList<Endereco> ListTEndereco (DAOEndereco prDAO) {
		
		ArrayList<Endereco> ListTaEndereco = new ArrayList<>();
		Connection c = prDAO.append();
		try {
			wSQL = "SELECT * FROM tendereco";
			Statement stm = c.prepareStatement(wSQL);
			ResultSet rs = stm.executeQuery(wSQL);
			
			while (rs.next()) {
				Endereco le = new Endereco();

				le.setBDBAIRRO(rs.getString("BDBAIRRO"));
				le.setBDCEP(rs.getInt("BDCEP"));
				le.setBDIDCIDADE(rs.getInt("BDIDCIDADE"));
				
				ListTaEndereco.add(le);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return ListTaEndereco;
	}	
	
	public ArrayList<Endereco> ListTEnderecoCON (DAOEndereco prDAO) {
		
		ArrayList<Endereco> ListTEnderecoCON = new ArrayList<>();
		Connection c = prDAO.append();
		try {
			wSQL = "select e.BDCEP, e.BDBAIRRO, c.BDNOMECID, uf.BDIDUF, uf.BDSIGLAUF from tendereco e "
					+ "join tcidades c on (e.BDIDCIDADE = c.BDIDCIDADE) "
					+ "join testados uf on (c.BDIDUF = uf.BDIDUF)";
			Statement stm = c.prepareStatement(wSQL);
			ResultSet rs = stm.executeQuery(wSQL);
			
			while (rs.next()) {
				Endereco le = new Endereco();
				
				le.setBDCEP(rs.getInt("BDCEP"));
				le.setBDBAIRRO(rs.getString("BDBAIRRO"));
				le.setBDNOMECID(rs.getString("BDNOMECID"));
				le.setBDIDUF(rs.getInt("BDIDUF"));
				le.setBDSIGLAUF(rs.getString("BDSIGLAUF"));
				
				ListTEnderecoCON.add(le);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return ListTEnderecoCON;
	}		
		
	}

		
	

