package control;

import java.sql.Connection;
import java.sql.PreparedStatement;

import model.MTClinica;

public class DAOTClinica extends MTClinica {
	
	private static DAOTClinica FDAOTClinica;
	private String wSql;
	
	//Insert
	public Boolean inserir() {
		Connection c = FDAOTClinica.append();
		try {
			wSql = "INSERT INTO `DBPI`.`TClinica`(`BDIDCLINICA`,`BDIDCEP`,`BDCNPJ`,`BDNOME`,`BDNOMEFANTASIA`,`BDSENHA`)VALUES(?,?,?,?,?,?);";
			PreparedStatement stm = c.prepareStatement(wSql);
			
			stm.setInt   (1, FDAOTClinica.getBDIDCLINICA());
			stm.setInt   (2, FDAOTClinica.getBDIDCEP());
			stm.setString(3, FDAOTClinica.getBDCNPJ());
			stm.setString(4, FDAOTClinica.getBDNOME());
			stm.setString(5, FDAOTClinica.getBDNOMEFANTASIA());
			stm.setString(6, FDAOTClinica.getBDSENHA());
			
			stm.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		FDAOTClinica.post();
		return false;
	}
	
	//SELECT
	public Boolean select() {
		Connection c = FDAOTClinica.append();
		try {
			wSql = "INSERT INTO `DBPI`.`TClinica`(`BDIDCLINICA`,`BDIDCEP`,`BDCNPJ`,`BDNOME`,`BDNOMEFANTASIA`,`BDSENHA`)VALUES(?,?,?,?,?,?);";
			PreparedStatement stm = c.prepareStatement(wSql);
			
			stm.setInt   (1, FDAOTClinica.getBDIDCLINICA());
			stm.setInt   (2, FDAOTClinica.getBDIDCEP());
			stm.setString(3, FDAOTClinica.getBDCNPJ());
			stm.setString(4, FDAOTClinica.getBDNOME());
			stm.setString(5, FDAOTClinica.getBDNOMEFANTASIA());
			stm.setString(6, FDAOTClinica.getBDSENHA());
			
			stm.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		FDAOTClinica.post();
		return false;
	}

}
