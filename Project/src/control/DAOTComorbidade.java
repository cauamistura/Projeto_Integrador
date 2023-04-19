package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.MTComorbidade;

public class DAOTComorbidade extends MTComorbidade {

	private String wSql;
	private Conexao FConexao;

	// Insert
	public Boolean inserir(DAOTComorbidade prDAO) {
		Connection c = prDAO.append();
		try {
			wSql = "INSERT INTO `dbpi`.`tcomorbidade` (`BDIDCOMORBIDADE`, `BDNOMECOMORBIDADE`, `BDDESCCOMORBIDADE`) VALUES (?,?,?)";
			PreparedStatement stm = c.prepareStatement(wSql);

			stm.setInt(1, prDAO.getBDIDCOMORBIDADE());
			stm.setString(2, prDAO.getBDNOMECOMORBIDADE());
			stm.setString(3, prDAO.getBDDESCCOMORBIDADE());

			stm.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prDAO.post();
		return false;
	}

	// UPDATE
	public Boolean alterar(DAOTComorbidade prDAO) {
		Connection c = null;
		PreparedStatement stm = null;
		try {
			c = prDAO.append();

			wSql = "UPDATE `dbpi`.`tcomorbidade` SET `BDNOMECOMORBIDADE` = ?, `BDDESCCOMORBIDADE` = ? WHERE `BDIDCOMORBIDADE` = ?";

			stm = c.prepareStatement(wSql);

			stm.setString(1, prDAO.getBDNOMECOMORBIDADE());
			stm.setString(2, prDAO.getBDDESCCOMORBIDADE());
			stm.setInt(3, prDAO.getBDIDCOMORBIDADE());

			int rowsUpdated = stm.executeUpdate();
			return rowsUpdated > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			prDAO.post();
		}
		return false;
	}

	// DELETE
	public Boolean deletar(Integer prID) {
		// Instacia coenexão
		FConexao = Conexao.getInstacia();
		// Conecta
		Connection c = Conexao.conectar();
		try {
			wSql = "DELETE FROM `dbpi`.`tcomorbidade` WHERE BDIDCOMORBIDADE = ?";
			PreparedStatement stm = c.prepareStatement(wSql);
			stm.setInt(1, prID);
			stm.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			FConexao.fecharConnection();
		}
		return false;
	}

	// SELECT
	public ArrayList<MTComorbidade> ListTComorbidade(DAOTComorbidade prDAO) {
		ArrayList<MTComorbidade> ListaTComorbidade = new ArrayList<>();

		Connection c = prDAO.append();
		try {
			wSql = "SELECT * FROM `dbpi`.`tcomorbidade`";
			Statement stm = c.createStatement();
			ResultSet rs = stm.executeQuery(wSql);

			while (rs.next()) {

				MTComorbidade lc = new MTComorbidade();

				lc.setBDIDCOMORBIDADE(rs.getInt("BDIDCOMORBIDADE"));
				lc.setBDNOMECOMORBIDADE(rs.getString("BDNOMECOMORBIDADE"));
				lc.setBDDESCCOMORBIDADE(rs.getString("BDDESCCOMORBIDADE"));

				ListaTComorbidade.add(lc); // Adiciona objeto à lista
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			prDAO.post();
		}

		return ListaTComorbidade;
	}

}
