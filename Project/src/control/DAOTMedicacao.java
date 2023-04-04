package control;

import java.sql.Connection;  
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.MTMedicacao;

	public class DAOTMedicacao extends MTMedicacao {
		
		private String wSql;
		
		
		//Insert
		public Boolean inserir(DAOTMedicacao prDAO) {
			Connection c = prDAO.append();
			try {
				wSql = "INSERT INTO `dbpi`.`tmedicacao`(`BDIDMEDICACAO`,`BDNOMEMEDICACAO`,`BDDESCRICAO`)VALUES(?,?,?);";
				PreparedStatement stm = c.prepareStatement(wSql);

				stm.setInt   (1, prDAO.getBDIDMEDICACAO());
				stm.setString(2, prDAO.getBDNOMEMEDICACAO());
				stm.setString(3, prDAO.getBDDESCRICAO());
				
				stm.executeUpdate();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			prDAO.post();
			return false;
		}
		
		// UPDATE
		public Boolean alterar(DAOTMedicacao prDAO) {
			Connection c = null;
		    PreparedStatement stm = null;
			try {
				c = prDAO.append();
				
				wSql = "UPDATE `dbpi`.`tmedicacao` SET `BDNOMEMEDICACAO` = ?, `BDDESCRICAO` = ? WHERE `BDIDMEDICACAO` = ?;";
				
				stm = c.prepareStatement(wSql);
				
				stm.setString(2, prDAO.getBDNOMEMEDICACAO());
				stm.setString(3, prDAO.getBDDESCRICAO());
				stm.setInt   (1, prDAO.getBDIDMEDICACAO());
				
				 int rowsUpdated = stm.executeUpdate();
			        return rowsUpdated > 0;
			    } catch (Exception e) {
			        // Print the stack trace for any exceptions that occur
			        e.printStackTrace();
			    } finally {
			        try { if (stm != null) stm.close(); } catch (SQLException e) { }
			        try { if (c != null) c.close(); } catch (SQLException e) { }
			    }
			    return false;
			}
		
		// DELETE
		public Boolean deletar(DAOTClinica prDAO) {
			Connection c = prDAO.append();
			try {
				wSql = "DELETE FROM `dbpi`.`tmedicacao` WHERE BDIDMEDICACAO = ?;";
				PreparedStatement stm = c.prepareStatement(wSql);
				stm.setLong(1, prDAO.getBDIDCLINICA());
				stm.execute();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			prDAO.post();
			return false;
		}
			
		// SELECT
		public ArrayList<MTMedicacao> ListTMedicacao(DAOTMedicacao prDAO) {
			ArrayList<MTMedicacao> ListTaMedicacao = new ArrayList<>();
			    
			Connection c = prDAO.append();
			try {
				wSql = "SELECT * FROM tclinica";
				Statement stm = c.createStatement();
				ResultSet rs =  stm.executeQuery(wSql);
				
				while (rs.next()) {

					
					MTMedicacao lc = new MTMedicacao();
					
					lc.setBDIDMEDICACAO(rs.getInt(getBDIDMEDICACAO()));
					lc.setBDDESCRICAO(rs.getString(getBDDESCRICAO()));
					lc.setBDNOMEMEDICACAO(rs.getString(getBDNOMEMEDICACAO()));
				
					

					ListTaMedicacao.add(lc);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			prDAO.post();
			return ListTaMedicacao;
		}
	}

