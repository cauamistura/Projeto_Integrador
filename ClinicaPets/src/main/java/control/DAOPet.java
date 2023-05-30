package control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Pet;

public class DAOPet extends Pet {

	private String wSql;

	// INSERT
	public Boolean inserir(DAOPet prDAO) {
		Connection c = prDAO.append();
		try {
			wSql = "insert into `dbpi`.`tpets`(`bdidpet`, `bdidraca`, `bdnomepet`, `bdapelido`, `bddatanascimento`, `bdiduser`)values(?,?,?,?,?,?)";
			PreparedStatement stm = c.prepareStatement(wSql);

			stm.setInt(1, prDAO.getBDIDPET());
			stm.setInt(2, prDAO.getBDIDRACA());
			stm.setString(3, prDAO.getBDNOMEPET());
			stm.setString(4, prDAO.getBDAPELIDO());
			stm.setDate(5, Date.valueOf(prDAO.getBDDATANASCIMENTO()));
			stm.setInt(6, prDAO.getBDIDUSER());

			stm.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return false;
	}

	// UPDATE
	public Boolean alterar(DAOPet prDAO) {
		Connection c = prDAO.append();
		boolean success = false;
		try {
			wSql = "UPDATE `dbpi`.`tpets` SET `BDIDRACA` = ?, `BDNOMEPET` = ?, `BDAPELIDO` = ?, `BDDATANASCIMENTO` = ?, `BDIDUSER` = ? WHERE `BDIDPET` = ?;";
			PreparedStatement stm = c.prepareStatement(wSql);

			stm.setInt(1, prDAO.getBDIDRACA());
			stm.setString(2, prDAO.getBDNOMEPET());
			stm.setString(3, prDAO.getBDAPELIDO());
			stm.setDate(4, Date.valueOf(prDAO.getBDDATANASCIMENTO()));
			stm.setInt(5, prDAO.getBDIDUSER());
			stm.setInt(6, prDAO.getBDIDPET());

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
	public Boolean deletar(Integer idPet) {
		// Instacia coenexão
		Conexao.getInstacia();
		// Conecta
		Connection c = Conexao.conectar();
		try {
			wSql = "DELETE FROM `dbpi`.`tpets` WHERE BDIDPET = ?;";
			PreparedStatement stm = c.prepareStatement(wSql);

			stm.setInt(1, idPet);

			stm.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexao.getInstacia().fecharConnection();
		}
		return false;
	}

	// SELECT
	public ArrayList<Pet> ListTPet(DAOPet prDAO) {

		ArrayList<Pet> ListaTePet = new ArrayList<>();
		Connection c = prDAO.append();
		try {
			wSql = " SELECT p.*,"
					+ "       d.bdnome AS BDNOMEUSER,"
					+ "       r.*,"
					+ "       e.*,"
					+ "       u.BDCPF"
					+ " FROM tpets p"
					+ " INNER JOIN traca r ON (r.BDIDRACA = p.BDIDRACA)"
					+ " INNER JOIN tdadosuser d ON (d.BDIDUSER = p.BDIDUSER)"
					+ " inner join tuser u on (d.BDIDUSER = u.BDIDUSER)"
					+ " INNER JOIN tespecie e ON (e.BDIDESPECIE = r.BDIDESPECIE) ";

			Statement stm = c.prepareStatement(wSql);
			ResultSet rs = stm.executeQuery(wSql);

			while (rs.next()) {
				Pet le = new Pet();

				le.setBDIDPET(rs.getInt("BDIDPET"));
				le.setBDIDRACA(rs.getInt("BDIDRACA"));
				le.setBDNOMEPET(rs.getString("BDNOMEPET"));
				le.setBDAPELIDO(rs.getString("BDAPELIDO"));
				le.setBDDATANASCIMENTO(rs.getDate("BDDATANASCIMENTO").toLocalDate());
				le.setBDIDUSER(rs.getInt("BDIDUSER"));
				le.setBDNOMEUSER(rs.getString("BDNOMEUSER"));
				le.setBDNOMERACA(rs.getString("BDNOMERACA"));
				le.setBDIDESPECIE(rs.getInt("BDIDESPECIE"));
				le.setBDNOMEESPECIE(rs.getString("BDNOMEESPECIE"));
				le.setBDCPF(rs.getString("BDCPF"));
				ListaTePet.add(le);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post();
		return ListaTePet;
	}

	// SELECT FILTRADO
	public ArrayList<Pet> listTPetFiltradoUser(DAOPet prDAO) {
		ArrayList<Pet> listaDePets = new ArrayList<>();
		Connection conexao = prDAO.append();
		try {
			wSql = "SELECT p.*, d.bdnome as BDNOMEUSER, r.BDNOMERACA FROM tpets p "
					+ "inner join traca r on (r.BDIDRACA = p.BDIDRACA) "
					+ "inner join tdadosuser d on (d.BDIDUSER = p.BDIDUSER) " + "where p.BDIDUSER = ?";
			PreparedStatement stm = conexao.prepareStatement(wSql);
			stm.setInt(1, prDAO.getBDIDUSER());

			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Pet pet = new Pet();
				pet.setBDIDPET(rs.getInt("BDIDPET"));
				pet.setBDIDRACA(rs.getInt("BDIDRACA"));
				pet.setBDNOMEPET(rs.getString("BDNOMEPET"));
				pet.setBDAPELIDO(rs.getString("BDAPELIDO"));
				pet.setBDDATANASCIMENTO(rs.getDate("BDDATANASCIMENTO").toLocalDate());
				pet.setBDIDUSER(rs.getInt("BDIDUSER"));
				pet.setBDNOMEUSER(rs.getString("BDNOMEUSER"));
				pet.setBDNOMERACA(rs.getString("BDNOMERACA"));
				listaDePets.add(pet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAO.post(); // Este método fecha a conexão com o banco de dados
		return listaDePets;
	}

	public Boolean existePet(DAOPet prDAOUser, Integer id) {
		Connection c = prDAOUser.append();
		try {
			Statement stm = c.createStatement();

			String wSql = "SELECT u.BDNOME as BDNOMEUSER, pet.*, raca.* FROM tpets pet "
					+ " INNER JOIN tdadosuser u ON (pet.BDIDUSER = u.BDIDUSER)"
					+ " INNER JOIN traca raca ON (pet.BDIDRACA = raca.BDIDRACA)"
					+ " WHERE pet.BDIDPET = " + id;

			ResultSet rs = stm.executeQuery(wSql);

			if (rs.next()) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		prDAOUser.post();
		return false;
	}

}
