package control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.MTAtendimenoEntrada;
import model.MTDadosUser;
import vision.VMenu;

public class DAOAtendimentoEntrada extends MTAtendimenoEntrada {

	private String wSql;
	private Conexao FConexao;

	// Insert
	public Boolean inserir(DAOAtendimentoEntrada prDAO) {
		Connection c = prDAO.append();
		try {
			wSql = "INSERT INTO `dbpi`.`tatendimento_entrada` (`BDIDENTRADA`,`BDIDPET`,`BDCOMORBIDADE`,`BDDATAENT`,`BDDESC`) VALUES (?,?,?,?,?)";
			PreparedStatement stm = c.prepareStatement(wSql);

			stm.setInt(1, prDAO.getBDIDENTRADA());
			stm.setInt(2, prDAO.getBDIDPET());

			if (prDAO.getBDCOMORBIDADE() == null) {
				stm.setNull(0, 0, wSql);
			} else {
				stm.setInt(3, prDAO.getBDCOMORBIDADE());
			}

			stm.setDate(4, Date.valueOf(prDAO.getBDDATAENT()));
			stm.setString(5, prDAO.getBDDESC());

			stm.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return false;
	}

	public Boolean existeAtendimento(DAOAtendimentoEntrada prDAO) {
		Connection c = prDAO.append();
		try {
			Statement stm = c.createStatement();
			wSql = "SELECT `tatendimento_entrada`.* FROM `dbpi`.`tatendimento_entrada` where BDIDENTRADA = "
					+ prDAO.getBDIDENTRADA() + "";

			ResultSet rs = stm.executeQuery(wSql);

			if (rs.next()) {
				if (rs.getInt("BDIDENTRADA") == prDAO.getBDIDENTRADA()) {
					return true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return false;
	}

	// UPDATE
	public boolean alterar(DAOAtendimentoEntrada prDAO) {
		boolean success = false;
		try (Connection c = prDAO.append();
				PreparedStatement stm = c.prepareStatement("UPDATE `dbpi`.`tatendimento_entrada` "
						+ "SET `BDIDPET` = ?, `BDCOMORBIDADE` = ?, `BDDATAENT` = ?, `BDDESC` = ? "
						+ "WHERE `BDIDENTRADA` = ? ")) {

			stm.setInt(1, prDAO.getBDIDPET());
			stm.setInt(2, prDAO.getBDCOMORBIDADE());
			stm.setDate(3, Date.valueOf(prDAO.getBDDATAENT()));
			stm.setString(4, prDAO.getBDDESC());
			stm.setInt(5, prDAO.getBDIDENTRADA());

			int count = stm.executeUpdate();
			success = (count == 1);
		} catch (SQLException e) {
			System.err.println("Error updating user record: " + e.getMessage());
		} finally {
			prDAO.post();
		}
		return success;
	}

	// DELETE
	public Boolean deletar(Integer prID) {
		FConexao = Conexao.getInstacia();
		Connection c = Conexao.conectar();

		try {
			wSql = "DELETE FROM `dbpi`.`tatendimento_entrada` WHERE BDIDENTRADA = ?";
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

	// SELECT CONSULTA
	public ArrayList<MTAtendimenoEntrada> ListConsulta(DAOAtendimentoEntrada prDAO) {
		ArrayList<MTAtendimenoEntrada> ListaAtendCons = new ArrayList<>();

		Connection c = prDAO.append();
		try {
			wSql = " SELECT a.*, pet.*, us.BDNOME as BDNOMEUSER, com.*"
					+ " FROM tatendimento_entrada a"
					+ " inner join tpets pet on (a.BDIDPET = pet.BDIDPET)"
					+ " inner join tdadosuser us on (pet.BDIDUSER = us.BDIDUSER)"
					+ " left outer join tcomorbidade com on (a.BDCOMORBIDADE = com.BDIDCOMORBIDADE)";
			Statement stm = c.createStatement();
			ResultSet rs = stm.executeQuery(wSql);

			while (rs.next()) {
				MTAtendimenoEntrada lc = new MTAtendimenoEntrada();
				
				
				
				ListaAtendCons.add(lc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			prDAO.post();
		}

		return ListaAtendCons;
	}
}
