package model;

public class MTEndereco extends MTCidade{
	
	String BDBAIRRO,
		   BDCEP;

	public MTEndereco() {
		super();
	}

	
	public String getBDCEP() {
		return BDCEP;
	}
	public void setBDCEP(String bDCEP) {
		BDCEP = bDCEP;
	}



	public String getBDBAIRRO() {
		return BDBAIRRO;
	}
	public void setBDBAIRRO(String bDBAIRRO) {
		BDBAIRRO = bDBAIRRO;
	}
	
	
	

}
