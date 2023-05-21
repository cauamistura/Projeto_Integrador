package control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.AtendimentoSaida;

public class DAOAtendimentoSaida extends AtendimentoSaida {

	private String wSql;
	private Conexao FConexao;

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

	public ArrayList<AtendimentoSaida> ListTSaida (DAOAtendimentoSaida prDAO) {
		ArrayList<AtendimentoSaida> Lista = new ArrayList<>();

		Connection c = prDAO.append();
		try {
			wSql = "   SELECT a.*, pet.*,esp.BDNOMEESPECIE, raca.BDNOMERACA, us.BDNOME as BDNOMEUSER, u.BDIDUSER, u.BDCPF, e.BDDATAENT, r.*,c.BDIDCOMORBIDADE   \n"
					+ "					   										  					FROM tatendimento_saida a     \n"
					+ "					   										  					inner join tpets pet on (a.BDIDPET = pet.BDIDPET)     \n"
					+ "					   										  					inner join traca raca on (raca.BDIDRACA = pet.BDIDRACA) \n"
					+ "																				inner join tespecie esp on (raca.BDIDESPECIE = esp.BDIDESPECIE)\n"
					+ "					   										  					inner join tdadosuser us on (pet.BDIDUSER = us.BDIDUSER)      \n"
					+ "					   										  					inner join tuser u on (u.BDIDUSER = us.BDIDUSER)     \n"
					+ "					   										  					inner join tatendimento_entrada e on(e.BDIDENTRADA = a.BDIDENTRADA)   \n"
					+ "					   					                                        inner join tcomorbidade c on(c.BDIDCOMORBIDADE = a.BDIDCOMORBIDADE)  \n"
					+ "					   										  					inner join  treceita r on(r.BDIDRECEITA = a.BDIDRECEITA); ;";
				
			
			Statement stm = c.createStatement();
			ResultSet rs = stm.executeQuery(wSql);

			while (rs.next()) {

				AtendimentoSaida lc = new AtendimentoSaida();

				lc.setBDIDENTRADA(rs.getInt("BDIDENTRADA"));
				lc.setBDIDPET(rs.getInt("BDIDPET"));
				lc.setBDIDRACA(rs.getInt("BDIDRACA"));
				lc.setBDCOMORBIDADE(rs.getInt("BDIDCOMORBIDADE"));
				
				
				lc.setBDDESC(rs.getString("BDDESC"));
				lc.setBDNOMEPET(rs.getString("BDNOMEPET"));
				lc.setBDCPF(rs.getString("BDCPF"));
				lc.setBDNOMEUSER(rs.getString("BDNOMEUSER"));
				lc.setBDNOMERACA(rs.getString("BDNOMERACA"));
				lc.setBDNOMEESPECIE(rs.getString("BDNOMEESPECIE"));
				
				lc.setBDDATASAIDA(rs.getDate("BDDATASAIDA").toLocalDate());
				lc.setBDDATAENT(rs.getDate("BDDATAENT").toLocalDate());
				
				Lista.add(lc); // Adiciona objeto à lista
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			prDAO.post();
		}

		return Lista;
	}

	public boolean alterar(DAOAtendimentoSaida prDAO) {
		boolean success = false;
		
		try (Connection c = prDAO.append();
				PreparedStatement stm = c.prepareStatement("UPDATE `dbpi`.`tatendimento_saida` \n"
						+ "						 SET `BDIDPET` = ?, `BDIDCOMORBIDADE` = ?, `BDDATASAIDA` = ?, `BDDESC` = ?,`BDIDRECEITA` = ? \n"
						+ "						 WHERE `BDIDENTRADA` = ?; ")) {
						
			stm.setInt(1, prDAO.getBDIDPET());
			stm.setInt(2, prDAO.getBDCOMORBIDADE());
			stm.setDate(3, Date.valueOf(prDAO.getBDDATASAIDA()));
			stm.setString(4, prDAO.getBDDESC());
			stm.setInt(5, prDAO.getBDIDRECEITA());
			stm.setInt(6, prDAO.getBDIDENTRADA());

			int count = stm.executeUpdate();
			success = (count == 1);
		} catch (SQLException e) {
			System.err.println("Error updating user record: " + e.getMessage());
		} finally {
			prDAO.post();
		}
		return success;
	}
	
	public Boolean deletar(Integer prID) {
		
		FConexao = Conexao.getInstacia();
		Connection c = Conexao.conectar();

		try {
			wSql = "DELETE FROM `dbpi`.`tatendimento_saida` WHERE BDIDENTRADA = ?";
			PreparedStatement stm = c.prepareStatement(wSql);

			stm.setLong(1, prID);

			stm.execute();

			return true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			FConexao.fecharConnection();
		}

		return false;
	}
	
}

