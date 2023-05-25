package model;

import java.time.LocalDate;

public class DadosUser extends User{
	
	Integer   BDCEP,
		 	  BDIDUSER,
			  BDIDCLINICA;
	String	  BDNOMEUSER,
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
	public String getBDNOMEUSER() {
		return BDNOMEUSER;
	}
	public void setBDNOMEUSER(String bDNOMEUSER) {
		BDNOMEUSER = bDNOMEUSER;
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
	
	@Override
	public String toString() {
		return this.BDNOMEUSER;
	}
}
