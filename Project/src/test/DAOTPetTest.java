package test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.Test;

import control.DAOTPet;
import model.MTPet;

public class DAOTPetTest {

	@Test
	public void testInserir() {
		DAOTPet dao = new DAOTPet();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		dao.setBDIDESPECIE(dao.getChaveID("tespecie", "BDIDESPECIE"));
		dao.setBDIDPET(dao.getChaveID("tpets", "BDIDPET"));
		dao.setBDDATANASCIMENTO(LocalDate.parse("16/05/2005", formatter));
		dao.setBDIDRACA(1);
		dao.setBDNOMEPET("Enzo");
		dao.setBDAPELIDO("Enzinho");
		dao.setBDIDUSER(1);
		
		Boolean result = dao.inserir(dao);
		assertEquals(true, result);
	}
	
	@Test
	public void testAlterar() {
		DAOTPet dao = new DAOTPet();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		dao.setBDIDESPECIE(dao.getChaveID("tespecie", "BDIDESPECIE"));
		dao.setBDIDPET(4);
		dao.setBDDATANASCIMENTO(LocalDate.parse("16/05/2005", formatter));
		dao.setBDIDRACA(1);
		dao.setBDNOMEPET("Cauã");
		dao.setBDAPELIDO("Cauãzinho");
		dao.setBDIDUSER(1);
		
		Boolean result = dao.alterar(dao);
		assertEquals(true, result);
	}
	
	@Test
	public void testDeletar() {
		DAOTPet dao = new DAOTPet();
		
		Boolean result = dao.deletar(1);
		assertEquals(true, result);
	}
	
	
	@Test
	public void testExistePet() {
		DAOTPet dao = new DAOTPet();
		
		Boolean result = dao.existePet(dao, 4);
		assertEquals(true, result);
	}

	

}
