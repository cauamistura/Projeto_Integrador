package control;

import java.sql.Connection;

public class ObjectDAO {

	private Conexao FConexao;

	private static ObjectDAO FObjectDAO;
	
	public Connection append(){
		try {
			// Instacia coenexão
			FConexao = Conexao.getInstacia();
			// Conecta
			Connection c = Conexao.conectar();
			return c;	
		} catch (Exception e) {
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
	
	
}
