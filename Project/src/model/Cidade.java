package model;

public class Cidade extends Estado{
	
	Integer BDIDCIDADE;
	
	String BDNOMECID,
		   BDDESCCID;
	
	public Cidade() {
		super();
	}
	
	public Integer getBDIDCIDADE() {
		return BDIDCIDADE;
	}
	public void setBDIDCIDADE(Integer bDIDCIDADE) {
		BDIDCIDADE = bDIDCIDADE;
	}
	
	public String getBDNOMECID() {
		return BDNOMECID;
	}
	public void setBDNOMECID(String bDNOMECID) {
		BDNOMECID = bDNOMECID;
	}
	public String getBDDESCCID() {
		return BDDESCCID;
	}
	public void setBDDESCCID(String bDDESCCID) {
		BDDESCCID = bDDESCCID;
	}
	
	
			

}
