package model;

import java.time.LocalDate;

import control.ObjectDAO;

public class MTAtendimentoSaida extends ObjectDAO{
	
	 Integer BDIDENTRADA,
	  		 BDIDPET,
	  		 BDIDCOMORBIDADE,
	  		 BDIDRECEITA;
	 
	 String BDDESC;
	 
	 LocalDate BDDATASAIDA;

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

	public Integer getBDIDCOMORBIDADE() {
		return BDIDCOMORBIDADE;
	}

	public void setBDIDCOMORBIDADE(Integer bDIDCOMORBIDADE) {
		BDIDCOMORBIDADE = bDIDCOMORBIDADE;
	}

	public Integer getBDIDRECEITA() {
		return BDIDRECEITA;
	}

	public void setBDIDRECEITA(Integer bDIDRECEITA) {
		BDIDRECEITA = bDIDRECEITA;
	}

	public String getBDDESC() {
		return BDDESC;
	}

	public void setBDDESC(String bDDESC) {
		BDDESC = bDDESC;
	}

	public LocalDate getBDDATASAIDA() {
		return BDDATASAIDA;
	}

	public void setBDDATASAIDA(LocalDate bDDATASAIDA) {
		BDDATASAIDA = bDDATASAIDA;
	}

	
	 
	 
}
