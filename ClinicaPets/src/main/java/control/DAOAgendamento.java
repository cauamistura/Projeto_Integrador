package control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import model.Agendamento;

public class DAOAgendamento extends Agendamento {

	private String wSql;
	private Conexao FConexao;

	// Select
	public ArrayList<Agendamento> List(DAOAgendamento prDAO) {
		ArrayList<Agendamento> Lista = new ArrayList<Agendamento>();

		Connection c = prDAO.append();
		try {
			String dataAgendamento = String.valueOf(prDAO.getDateAgendamento());

			wSql = " SELECT a.*, " + "	   p.bdnomepet," + "    u.bdnome" + " FROM tagendamento a"
					+ " inner join tpets p on (p.bdidpet = a.bdidpet)"
					+ " inner join tdadosuser u on (u.bdiduser = p.bdiduser)" + " where a.bddataagen = ?";

			PreparedStatement stm = c.prepareStatement(wSql);
			stm.setString(1, dataAgendamento);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				Agendamento lc = new Agendamento();

				lc.setDateAgendamento(rs.getDate("bddataagen").toLocalDate());
				lc.setHora(rs.getString("bdhora"));
				lc.setId(rs.getInt("bdidagendamento"));
				lc.setDisponivel(false);

				lc.setBDIDPET(rs.getInt("bdidpet"));
				lc.setBDNOMEPET(rs.getString("bdnomepet"));
				lc.setBDNOMEUSER("bdnome");

				Lista.add(lc);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			prDAO.post();
		}

		return Lista;
	}

	// Insert
	public Boolean inserir(DAOAgendamento prDAO) {
		Connection c = prDAO.append();
		try {
			wSql = "INSERT INTO `dbpi`.`tagendamento` (`BDIDAGENDAMENTO`,`BDIDPET`,`BDDATAAGEN`,`BDHORA`) VALUES (?,?,?,?)";
			PreparedStatement stm = c.prepareStatement(wSql);

			stm.setInt(1, prDAO.getId());
			stm.setInt(2, prDAO.getBDIDPET());
			stm.setDate(3, Date.valueOf(prDAO.getDateAgendamento()));
			stm.setString(4, prDAO.getHora());

			stm.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			prDAO.post();
		}
		return false;
	}

	// UPDATE
	public Boolean alterar(DAOAgendamento prDAO) {
		Connection c = null;
		PreparedStatement stm = null;
		try {
			c = prDAO.append();

			wSql = " UPDATE `dbpi`.`tagendamento`" + " SET" + " `BDIDPET` = ?," + " `BDDATAAGEN` = ?," + " `BDHORA` = ?"
					+ " WHERE `BDIDAGENDAMENTO` = ?";

			stm = c.prepareStatement(wSql);

			stm.setInt(1, prDAO.getBDIDPET());
			stm.setDate(2, Date.valueOf(prDAO.getDateAgendamento()));
			stm.setString(3, prDAO.getHora());
			stm.setInt(4, prDAO.getId());

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
		FConexao = Conexao.getInstacia();
		Connection c = Conexao.conectar();
		try {
			wSql = " DELETE FROM `dbpi`.`tagendamento`" + " WHERE BDIDAGENDAMENTO = ?";

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

	public Boolean existeAgendamento(Integer prId) {
		FConexao = Conexao.getInstacia();
		Connection c = Conexao.conectar();
		try {
			wSql = " SELECT * from tagendamento"
				 + " where BDIDAGENDAMENTO = ?";

			PreparedStatement stm = c.prepareStatement(wSql);
			stm.setInt(1, prId);

			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			FConexao.fecharConnection();
		}
		return false;
	}

	// Select
	public ArrayList<Agendamento> ListCon(LocalDate prDate, Boolean Filter) {
		ArrayList<Agendamento> Lista = new ArrayList<Agendamento>();

		FConexao = Conexao.getInstacia();
		Connection c = Conexao.conectar();

		try {
			String dataAgendamento = String.valueOf(prDate);

			wSql = " select * " + 
				   " from tagendamento a" + 
				   " inner join tpets p on (a.bdidpet = p.bdidpet) " +
				   " inner join traca r on (r.bdidraca = p.bdidraca)";
			
			if (Filter) {
				wSql += " where a.bddataagen = ?";
			}				   

			PreparedStatement stm = c.prepareStatement(wSql);
			
			if (Filter) {				
				stm.setString(1, dataAgendamento);
			}
			
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				Agendamento lc = new Agendamento();

				lc.setDateAgendamento(rs.getDate("bddataagen").toLocalDate());
				lc.setHora(rs.getString("bdhora"));
				lc.setId(rs.getInt("bdidagendamento"));
				lc.setDisponivel(false);

				lc.setBDIDPET(rs.getInt("bdidpet"));
				lc.setBDIDUSER(rs.getInt("bdiduser"));
				lc.setBDNOMEPET(rs.getString("BDNOMEPET"));
				lc.setBDNOMERACA(rs.getString("bdnomeraca"));

				Lista.add(lc);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			FConexao.fecharConnection();
		}

		return Lista;
	}
}
