package control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import vision.VMenu;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ObjectDAO {

	public Integer FIDEMPRESA;
	public String  FNOMEEMPRESA;
	public String  FCNPJEMPRESA;
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
	
	
	public String Dateconvert(String dateStr) {
		// cria um objeto DateTimeFormatter para fazer a conversão
	    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	    // converte a string para um objeto LocalDate usando o formatter de entrada
	    LocalDate date = LocalDate.parse(dateStr, inputFormatter);

	    // converte o objeto LocalDate de volta para uma string usando o formatter de saída
	    String output = date.format(outputFormatter);

	    return output;
	}
	
}
