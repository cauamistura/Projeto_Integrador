package model;

import java.time.LocalDate;

public class MTAtendimenoEntrada extends MTPet{
	
	private Integer BDIDENTRADA, BDIDPET, BDCOMORBIDADE;
	private String  BDDESC;
	private LocalDate BDDATAENT;
	
	public LocalDate getBDDATAENT() {
		return BDDATAENT;
	}
	public void setBDDATAENT(LocalDate bDDATAENT) {
		BDDATAENT = bDDATAENT;
	}
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
	public String getBDDESC() {
		return BDDESC;
	}
	public void setBDDESC(String bDDESC) {
		BDDESC = bDDESC;
	}
}
