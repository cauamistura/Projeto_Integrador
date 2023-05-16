package control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.MHistorico;
import vision.VMenu;

public class DAOHistorico extends MHistorico {

	private String wSql;

	// SELECT
	public ArrayList<MHistorico> List(DAOHistorico prDAO) {
		ArrayList<MHistorico> Lista = new ArrayList<>();

		Connection c = prDAO.append();
		try {
			wSql =    " SELECT m.*,"
					+ "        u.*,"
					+ "        p.BDNOMEPET,"
					+ "        r.BDNOMERACA"
					+ "    FROM ("
					+ "    SELECT e.BDIDENTRADA AS BDID,"
					+ "        e.BDIDPET,"
					+ "        e.BDCOMORBIDADE,"
					+ "        e.BDDATAENT AS BDDATA,"
					+ "        BDDESC,"
					+ "        \"Entrada\" AS BDTIPO,"
					+ "        null as BDRELACAO"
					+ "        FROM tatendimento_entrada e"
					+ "    UNION ALL"
					+ "    SELECT s.BDIDSAIDA,"
					+ "        s.BDIDPET,"
					+ "        s.BDIDCOMORBIDADE,"
					+ "        s.BDDATASAIDA,"
					+ "        s.BDDESC,"
					+ "        \"Saida\" AS BDTIPO,"
					+ "        BDIDENTRADA "
					+ "        FROM tatendimento_saida s "
					+ "        ) m"
					+ " INNER JOIN tpets p ON (m.BDIDPET = p.BDIDPET)"
					+ " INNER JOIN traca r ON (r.BDIDRACA = p.BDIDRACA)"
					+ " INNER JOIN tdadosuser u ON (u.BDIDUSER = p.BDIDUSER)";
			
			if (prDAO.getBDIDUSER() != null) {
				String User = String.valueOf(prDAO.getBDIDUSER());
				wSql = wSql + " WHERE u.BDIDUSER = " + User; 
						
				if (prDAO.getBDIDPET() != null) { 
					String Pet  = String.valueOf(prDAO.getBDIDPET());
					wSql = wSql + " AND m.BDIDPET = " + Pet; 
				}
			}
			if (VMenu.FPERMICAO == 3) {
				wSql = wSql + " WHERE u.BDIDUSER = " + String.valueOf(VMenu.FIDUSER); 
			}
			
			Statement stm = c.createStatement();
			ResultSet rs = stm.executeQuery(wSql);

			while (rs.next()) {
				MHistorico lc = new MHistorico();
				
				//Entrada
				if (rs.getString("BDRELACAO") == null) {
					lc.setBDIDENTRADA(rs.getInt("BDID"));
				} else {
					lc.setBDIDENTRADA(rs.getInt("BDRELACAO"));
				}
				
				lc.setBDIDPET(rs.getInt("BDIDPET"));
				lc.setBDIDCOMORBIDADE(rs.getInt("BDCOMORBIDADE"));
				lc.setBDDATAENT(rs.getDate("BDDATA").toLocalDate());
				lc.setTipo(rs.getString("BDTIPO"));
				
				//Saida
				lc.setBDCOMORBIDADE(rs.getInt("BDCOMORBIDADE"));
				lc.setBDIDSAIDA(rs.getInt("BDID"));
				lc.setBDIDPET(rs.getInt("BDIDPET"));
				lc.setBDCOMORBIDADE(getBDCOMORBIDADE());
				lc.setBDDATASAIDA(rs.getDate("BDDATA").toLocalDate());
				lc.setTipo(rs.getString("BDTIPO"));
				
				lc.setBDNOMEPET(rs.getString("BDNOMEPET"));
				lc.setBDNOMERACA(rs.getString("BDNOMERACA"));
				
				lc.setBDDESC(rs.getString("BDDESC"));
				
				lc.setBDNOMEUSER(rs.getString("BDNOME"));
				lc.setBDIDUSER(rs.getInt("BDIDUSER"));
				
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
