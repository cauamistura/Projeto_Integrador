package model;

import java.time.LocalDate;

import control.ObjectDAO;

public class MTPet extends MTEspecie {
	
	Integer BDIDPET,
			BDIDRACA,
			BDIDUSER;
	
	public Integer getBDIDUSER() {
		return BDIDUSER;
	}

	public void setBDIDUSER(Integer bDIDUSER) {
		BDIDUSER = bDIDUSER;
	}

	String BDNOMEPET,
		   BDAPELIDO;
	
	LocalDate BDDATANASCIMENTO;

	public Integer getBDIDPET() {
		return BDIDPET;
	}

	public void setBDIDPET(Integer bDIDPET) {
		BDIDPET = bDIDPET;
	}

	public Integer getBDIDRACA() {
		return BDIDRACA;
	}

	public void setBDIDRACA(Integer bDIDRACA) {
		BDIDRACA = bDIDRACA;
	}

	public String getBDNOMEPET() {
		return BDNOMEPET;
	}

	public void setBDNOMEPET(String bDNOMEPET) {
		BDNOMEPET = bDNOMEPET;
	}

	public String getBDAPELIDO() {
		return BDAPELIDO;
	}

	public void setBDAPELIDO(String bDAPELIDO) {
		BDAPELIDO = bDAPELIDO;
	}

	public LocalDate getBDDATANASCIMENTO() {
		return BDDATANASCIMENTO;
	}

	public void setBDDATANASCIMENTO(LocalDate bDDATANASCIMENTO) {
		BDDATANASCIMENTO = bDDATANASCIMENTO;
	}
	
}
