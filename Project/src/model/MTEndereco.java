package model;

public class MTEndereco extends MTCidade{
	
	String  BDBAIRRO;
	Integer BDCEP, 
			BDIDCIDADE;
	
	public String getBDBAIRRO() {
		return BDBAIRRO;
	}
	public void setBDBAIRRO(String bDBAIRRO) {
		BDBAIRRO = bDBAIRRO;
	}
	public Integer getBDCEP() {
		return BDCEP;
	}
	public void setBDCEP(Integer dBCEP) {
		BDCEP = dBCEP;
	}
	public Integer getBDIDCIDADE() {
		return BDIDCIDADE;
	}
	public void setBDIDCIDADE(Integer bDIDCIDADE) {
		BDIDCIDADE = bDIDCIDADE;
	}

}
