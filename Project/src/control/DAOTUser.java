package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.MTUser;
import vision.VMenu;

public class DAOTUser extends MTUser {

	private String wSql;
	private Conexao FConexao;

	// Insert
	public Boolean inserir(DAOTUser prDAO) {
		Connection c = prDAO.append();
		try {
			wSql = "INSERT INTO `dbpi`.`tuser` (`BDIDUSER`,`BDIDCLINICA`,`BDCPF`,`BDMAIL`,`BDSENHA`,`BDIDPERMICAO`) VALUES(?,?,?,?,?,?);";
			PreparedStatement stm = c.prepareStatement(wSql);

			stm.setInt(1, prDAO.getBDIDUSER());
			stm.setInt(2, prDAO.getBDIDCLINICA());
			stm.setString(3, prDAO.getBDCPF());
			stm.setString(4, prDAO.getBDMAIL());
			stm.setString(5, prDAO.getBDSENHA());
			stm.setInt(6, prDAO.getBDIDPERMICAO());

			stm.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return false;
	}

	// UPDATE
	public boolean alterar(DAOTUser prDAO) {
		boolean success = false;
		try (Connection c = prDAO.append();
				PreparedStatement stm = c
						.prepareStatement("UPDATE dbpi.tuser SET BDMAIL = ?, BDSENHA = ?, BDIDPERMICAO = ? "
								+ "WHERE BDIDUSER = ? AND BDIDCLINICA = ?")) {
			stm.setString(1, prDAO.getBDMAIL());
			stm.setString(2, prDAO.getBDSENHA());
			stm.setInt(3, prDAO.getBDIDPERMICAO());
			stm.setInt(4, prDAO.getBDIDUSER());
			stm.setInt(5, VMenu.FIDClinica);

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
	public Boolean deletar(Integer prIDUSER) {
		// Instacia coenexão
		FConexao = Conexao.getInstacia();
		// Conecta
		Connection c = Conexao.conectar();
		try {
			wSql = "DELETE FROM dbpi.tuser WHERE BDIDUSER = ? AND bdidclinica = ?";
			PreparedStatement stm = c.prepareStatement(wSql);
			stm.setLong(1, prIDUSER);
			stm.setInt(2, VMenu.FIDClinica);
			stm.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			FConexao.fecharConnection();
		}
		return false;
	}

	// SELECT
	public ArrayList<MTUser> ListTUser(DAOTUser prDAO) {
		ArrayList<MTUser> ListaUser = new ArrayList<>();

		Connection c = prDAO.append();
		try {
			Statement stm = c.createStatement();
			wSql = "SELECT * FROM tuser";
			ResultSet rs = stm.executeQuery(wSql);

			while (rs.next()) {
				MTUser lc = new MTUser();

				lc.setBDIDUSER(rs.getInt("BDIDUSER"));
				lc.setBDIDCLINICA(rs.getInt("BDIDCLINICA"));
				lc.setBDCPF(rs.getString("BDCPF"));
				lc.setBDMAIL(rs.getString("BDMAIL"));
				lc.setBDSENHA(rs.getString("BDSENHA"));
				lc.setBDIDPERMICAO(rs.getInt("BDIDPERMICAO"));

				ListaUser.add(lc);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return ListaUser;
	}

	public Integer getIDUser(DAOTUser prDAO) {
		Connection c = prDAO.append();
		try {
			Statement stm = c.createStatement();

			String wSql = "SELECT BDIDUSER FROM `dbpi`.`tuser` where BDCPF = '" + prDAO.getBDCPF()
					+ "' and BDIDCLINICA =" + String.valueOf(VMenu.FIDClinica);

			ResultSet rs = stm.executeQuery(wSql);

			if (rs.next()) {
				return rs.getInt("BDIDUSER");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return null;
	}
}
