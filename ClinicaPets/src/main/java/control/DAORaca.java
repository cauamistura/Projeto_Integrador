package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.Raca;

public class DAORaca extends Raca {

	private String wSQL;

	// Insert
	public Boolean inserir(DAORaca prDAO) {
		Connection c = prDAO.append();
		try {
			wSQL = "INSERT INTO `dbpi`.`traca`(`BDIDRACA`,`BDNOMERACA`,`BDIDESPECIE`)VALUES(?,?,?);";
			PreparedStatement stm = c.prepareStatement(wSQL);

			stm.setInt(1, prDAO.getBDIDRACA());
			stm.setString(2, prDAO.getBDNOMERACA());
			stm.setInt(3, prDAO.getBDIDESPECIE());

			stm.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return false;
	}

	// Update
	public Boolean alterar(DAORaca prDAO) {
		Connection c = prDAO.append();
		try {
			wSQL = "UPDATE `dbpi`.`traca` SET `BDNOMERACA` = ?,`BDIDESPECIE` = ? WHERE `BDIDRACA` = ?;";
			PreparedStatement stm = c.prepareStatement(wSQL);

			stm.setInt(1, prDAO.getBDIDRACA());
			stm.setString(2, prDAO.getBDNOMERACA());
			stm.setInt(3, prDAO.getBDIDESPECIE());

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return false;
	}

	// Delete
	public Boolean deletar(DAORaca prDAO) {
		Connection c = prDAO.append();
		try {
			wSQL = "DELETE FROM `dbpi`.`traca` WHERE BDIDRACA = ?;";
			PreparedStatement stm = c.prepareStatement(wSQL);

			stm.setInt(1, prDAO.getBDIDRACA());
			stm.setString(2, prDAO.getBDNOMERACA());
			stm.setInt(3, prDAO.getBDIDESPECIE());

			stm.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return false;
	}

	// Select
	public ArrayList<Raca> ListTRaca(DAORaca prDAO, int idEspecie) {

		ArrayList<Raca> ListTaRaca = new ArrayList<>();
		Connection c = prDAO.append();
		try {

			if (idEspecie == 0) {
				wSQL = "SELECT * FROM traca";
			} else {
				wSQL = "SELECT * FROM traca WHERE BDIDESPECIE = " + idEspecie;
			}

			Statement stm = c.prepareStatement(wSQL);

			ResultSet rs = stm.executeQuery(wSQL);

			while (rs.next()) {
				Raca le = new Raca();

				le.setBDIDESPECIE(rs.getInt("BDIDESPECIE"));
				le.setBDNOMERACA(rs.getString("BDNOMERACA"));
				le.setBDIDRACA(rs.getInt("BDIDRACA"));

				ListTaRaca.add(le);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return ListTaRaca;
	}
}
