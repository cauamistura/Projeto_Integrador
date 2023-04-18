package model;

public class MTAtendimenoEntrada extends MTPet{
	
	private Integer BDIDENTRADA, BDIDPET, BDCOMORBIDADE;
	private String BDDATAENT, BDDESC;
	
	public Integer getBDIDENTRADA() {
		return BDIDENTRADA;
	}
	public void setBDIDENTRADA(Integer bDIDENTRADA) {
		BDIDENTRADA = bDIDENTRADA;
	}
	public Integer getBDIDPET() {
		return BDIDPET;
	}
	public void setBDIDPET(Integer bDIDPET) {
		BDIDPET = bDIDPET;
	}
	public Integer getBDCOMORBIDADE() {
		return BDCOMORBIDADE;
	}
	public void setBDCOMORBIDADE(Integer bDCOMORBIDADE) {
		BDCOMORBIDADE = bDCOMORBIDADE;
	}
	public String getBDDATAENT() {
		return BDDATAENT;
	}
	public void setBDDATAENT(String bDDATAENT) {
		BDDATAENT = bDDATAENT;
	}
	public String getBDDESC() {
		return BDDESC;
	}
	public void setBDDESC(String bDDESC) {
		BDDESC = bDDESC;
	}
}
