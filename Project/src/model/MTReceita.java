package model;

import java.time.LocalDate;

import control.ObjectDAO;

public class MTReceita extends MTMedicacao {

	Integer BDIDRECEITA;
	
	LocalDate BDINICIORECEITA,
		      BDFINALRECEITA;
	
	String BDDESCRICAO;

	public Integer getBDIDRECEITA() {
		return BDIDRECEITA;
	}

	public void setBDIDRECEITA(Integer bDIDRECEITA) {
		BDIDRECEITA = bDIDRECEITA;
	}

	public LocalDate getBDINICIORECEITA() {
		return BDINICIORECEITA;
	}

	public void setBDINICIORECEITA(LocalDate bDINICIORECEITA) {
		BDINICIORECEITA = bDINICIORECEITA;
	}

	public LocalDate getBDFINALRECEITA() {
		return BDFINALRECEITA;
	}

	public void setBDFINALRECEITA(LocalDate bDFINALRECEITA) {
		BDFINALRECEITA = bDFINALRECEITA;
	}

	public String getBDDESCRICAO() {
		return BDDESCRICAO;
	}

	public void setBDDESCRICAO(String bDDESCRICAO) {
		BDDESCRICAO = bDDESCRICAO;
	}
}
