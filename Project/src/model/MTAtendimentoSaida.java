package model;

import java.time.LocalDate;

import control.ObjectDAO;

public class MTAtendimentoSaida extends MTAtendimenoEntrada{
	

	 Integer BDIDRECEITA;
	 
	 String BDDESC;
	 
	 LocalDate BDDATASAIDA;

	
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
