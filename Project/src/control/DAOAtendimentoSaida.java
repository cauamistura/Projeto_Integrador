package control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import model.MTAtendimentoSaida;

public class DAOAtendimentoSaida extends MTAtendimentoSaida{
	
		private String wSql;
		
		//Insert
		public Boolean inserir(DAOAtendimentoSaida prDAO) {
			Connection c = prDAO.append();
			try {
				wSql = "INSERT INTO `dbpi`.`tatendimento_saida` (`BDIDENTRADA`,`BDIDPET`,`BDDATAENT`,`BDDESC`) VALUES (?,?,?,?,?)";
				PreparedStatement stm = c.prepareStatement(wSql);
				
				stm.setInt	 (1, prDAO.getBDIDENTRADA());
				stm.setInt	 (2, prDAO.getBDIDPET());
				
				stm.setInt	 (3, prDAO.getBDIDRECEITA());
				stm.setDate	 (4, Date.valueOf(prDAO.getBDDATASAIDA()));
				stm.setString(5, prDAO.getBDDESC());
		
				stm.executeUpdate();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			prDAO.post();
			return false;
		}

	}
		


