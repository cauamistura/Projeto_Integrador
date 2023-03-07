package model;

import java.time.LocalDate;

public class MTDadosUser extends MTUser{
	
	Integer   BDCEP,
		 	  BDIDUSER,
			  BDIDCLINICA;
	String	  BDNOME,
		 	  BDGENERO,
			  BDTELEFONE;
	LocalDate BDDATANASCIMENTO;
	public Integer getBDCEP() {
		return BDCEP;
	}
	public void setBDCEP(Integer bDCEP) {
		BDCEP = bDCEP;
	}
	public Integer getBDIDUSER() {
		return BDIDUSER;
	}
	public void setBDIDUSER(Integer bDIDUSER) {
		BDIDUSER = bDIDUSER;
	}
	public Integer getBDIDCLINICA() {
		return BDIDCLINICA;
	}
	public void setBDIDCLINICA(Integer bDIDCLINICA) {
		BDIDCLINICA = bDIDCLINICA;
	}
	public String getBDNOME() {
		return BDNOME;
	}
	public void setBDNOME(String bDNOME) {
		BDNOME = bDNOME;
	}
	public String getBDGENERO() {
		return BDGENERO;
	}
	public void setBDGENERO(String bDGENERO) {
		BDGENERO = bDGENERO;
	}
	public String getBDTELEFONE() {
		return BDTELEFONE;
	}
	public void setBDTELEFONE(String bDTELEFONE) {
		BDTELEFONE = bDTELEFONE;
	}
	public LocalDate getBDDATANASCIMENTO() {
		return BDDATANASCIMENTO;
	}
	public void setBDDATANASCIMENTO(LocalDate bDDATANASCIMENTO) {
		BDDATANASCIMENTO = bDDATANASCIMENTO;
	}
}
