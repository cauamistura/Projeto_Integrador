package control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.Agendamento;

public class DAOAgendamento extends Agendamento{
	
	private String wSql;
	
	//Select 
	public ArrayList<Agendamento> ListT(DAOAgendamento prDAO) {
		ArrayList<Agendamento> Lista = new ArrayList<>();

		Connection c = prDAO.append();
		try {
			wSql = "SELECT * FROM `dbpi`.`tcomorbidade`";
			Statement stm = c.createStatement();
			ResultSet rs = stm.executeQuery(wSql);

			while (rs.next()) {

				Agendamento lc = new Agendamento();

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
