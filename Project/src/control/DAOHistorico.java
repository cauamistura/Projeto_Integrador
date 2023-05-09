package control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.MHistorico;

public class DAOHistorico extends MHistorico {

	private String wSql;

	// SELECT
	public ArrayList<MHistorico> List(DAOHistorico prDAO) {
		ArrayList<MHistorico> Lista = new ArrayList<>();

		Connection c = prDAO.append();
		try {
			wSql =    " select e.BDIDENTRADA as BDID, e.BDIDPET, e.BDCOMORBIDADE, e.BDDATAENT as BDDATA, BDDESC, 'Entrada' as BDTIPO"
					+ " from tatendimento_entrada e "
					+ " union all"
					+ " select s.BDIDSAIDA, s.BDIDPET, s.BDIDCOMORBIDADE, s.BDDATASAIDA, s.BDDESC, 'Saida' as BDTIPO"
					+ " from tatendimento_saida s";
			Statement stm = c.createStatement();
			ResultSet rs = stm.executeQuery(wSql);

			while (rs.next()) {
				MHistorico lc = new MHistorico();
				
//				Entrada
				lc.setBDIDENTRADA(rs.getInt("BDID"));
				lc.setBDIDPET(rs.getInt("BDIDPET"));
				lc.setBDIDCOMORBIDADE(rs.getInt("BDCOMORBIDADE"));
				lc.setBDDATAENT(rs.getDate("BDDATA").toLocalDate());
				lc.setTipo(rs.getString("BDTIPO"));
				
//				Entrada
				lc.setBDIDSAIDA(rs.getInt("BDID"));
				lc.setBDIDPET(rs.getInt("BDIDPET"));
				lc.setBDCOMORBIDADE(getBDCOMORBIDADE());
				lc.setBDDATASAIDA(rs.getDate("BDDATA").toLocalDate());
				lc.setTipo(rs.getString("BDTIPO"));
				
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
