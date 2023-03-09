package model;

import control.ObjectDAO;

public class MTClinica extends ObjectDAO{
	
	Integer BDIDCEP,
			BDIDCLINICA;
	
	String  BDCNPJ,
			BDNOME,
			BDNOMEFANTASIA,
			BDSENHA;
	
	
	public Integer getBDIDCEP() {
		return BDIDCEP;
	}
	public void setBDIDCEP(Integer bDIDCEP) {
		BDIDCEP = bDIDCEP;
	}
	public Integer getBDIDCLINICA() {
		return BDIDCLINICA;
	}
	public void setBDIDCLINICA(Integer bDIDCLINICA) {
		BDIDCLINICA = bDIDCLINICA;
	}
	public String getBDCNPJ() {
		return BDCNPJ;
	}
	public void setBDCNPJ(String bDCNPJ) {
		BDCNPJ = bDCNPJ;
	}
	public String getBDNOME() {
		return BDNOME;
	}
	public void setBDNOME(String bDNOME) {
		BDNOME = bDNOME;
	}
	public String getBDNOMEFANTASIA() {
		return BDNOMEFANTASIA;
	}
	public void setBDNOMEFANTASIA(String bDNOMEFANTASIA) {
		BDNOMEFANTASIA = bDNOMEFANTASIA;
	}
	public String getBDSENHA() {
		return BDSENHA;
	}
	public void setBDSENHA(String bDSENHA) {
		BDSENHA = bDSENHA;
	}
}
