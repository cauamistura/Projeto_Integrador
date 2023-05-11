package control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.MTAtendimentoSaida;

public class DAOAtendimentoSaida extends MTAtendimentoSaida {

	private String wSql;

	// Insert
	public Boolean inserir(DAOAtendimentoSaida prDAO) {
		Connection c = prDAO.append();
		try {
			wSql = "INSERT INTO `dbpi`.`tatendimento_saida` (`BDIDENTRADA`,`BDIDPET`,`BDIDCOMORBIDADE`,`BDIDRECEITA`,`BDDESC`,`BDDATASAIDA`) VALUES (?,?,?,?,?,?)";
			PreparedStatement stm = c.prepareStatement(wSql);

			stm.setInt(1, prDAO.getBDIDENTRADA());
			stm.setInt(2, prDAO.getBDIDPET());
			stm.setInt(3, prDAO.getBDCOMORBIDADE());
			stm.setInt(4, prDAO.getBDIDRECEITA());
			stm.setString(5, prDAO.getBDDESC());
			stm.setDate(6, Date.valueOf(prDAO.getBDDATASAIDA()));

			stm.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return false;
	}

	public Boolean retornaIdReceita(Integer idEntrada) {
		Connection c = append();
		try {
			Statement stm = c.createStatement();
			wSql = "SELECT `BDIDRECEITA` FROM `dbpi`.`tatendimento_saida` where `BDIDENTRADA` = " + idEntrada + ";";

			ResultSet rs = stm.executeQuery(wSql);

			if (rs.next()) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		post();
		return false;
	}
	
	// SELECT
	public ArrayList<MTAtendimentoSaida> ListT (DAOAtendimentoSaida prDAO) {
		ArrayList<MTAtendimentoSaida> Lista = new ArrayList<>();

		Connection c = prDAO.append();
		try {
			wSql = "SELECT BDIDENTRADA,BDIDPET FROM `dbpi`.`tatendimento_saida`;";
			Statement stm = c.createStatement();
			ResultSet rs = stm.executeQuery(wSql);

			while (rs.next()) {

				MTAtendimentoSaida lc = new MTAtendimentoSaida();

				lc.setBDIDENTRADA(rs.getInt("BDIDENTRADA"));
				lc.setBDIDPET(rs.getInt("BDIDPET"));

				Lista.add(lc); // Adiciona objeto à lista
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			prDAO.post();
		}

		return Lista;
	}

}
