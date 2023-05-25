package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import control.DAOPet;
import model.Pet;

public class DAOTPetTest {

	@Test
	public void testInserir() {
		DAOPet dao = new DAOPet();
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
		assertNotNull(dao.existePet(dao, dao.getBDIDPET()));

	}

	@Test
	public void testAlterar() {
		DAOPet dao = new DAOPet();
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
		DAOPet dao = new DAOPet();

		Boolean result = dao.deletar(1);
		assertEquals(true, result);
		assertNotNull(dao.existePet(dao, dao.getBDIDPET()));
	}

	@Test
	public void testExistePet() {
		DAOPet dao = new DAOPet();

		Boolean result = dao.existePet(dao, 4);
		assertEquals(true, result);

	}

}
