package control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import vision.VMenu;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ObjectDAO {
	private Conexao FConexao;

	public Connection append(){
		try {
			// Instacia coenexão
			FConexao = Conexao.getInstacia();
			// Conecta
			Connection c = Conexao.conectar();
			return c;	
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void post(){
		try {
			//Fecha Conexão
			FConexao.fecharConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Integer getChaveID(String prTable, String prColumId) {
		Connection c = append();
		try {
			Statement stm = c.createStatement();
			String wSql = "SELECT max("+ prColumId +") as BDCHAVE FROM "+ prTable +";";
			ResultSet rs =  stm.executeQuery(wSql);

	        if(rs.next()){
	        	return (rs.getInt("BDCHAVE") + 1);
	        }
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		post();
		return 0;
	}
	
}
