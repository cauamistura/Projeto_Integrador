package model;

import java.time.LocalDate;

import control.ObjectDAO;

public class MTReceita extends ObjectDAO{

	Integer BDIDRECEITA,
			BDIDMEDICACAO;
	
	LocalDate BDINICIORECEITA,
		      BDFINALRECEITA;
	
	String BDDESCRICAO;

	public Integer getBDIDRECEITA() {
		return BDIDRECEITA;
	}

	public void setBDIDRECEITA(Integer bDIDRECEITA) {
		BDIDRECEITA = bDIDRECEITA;
	}

	public Integer getBDIDMEDICACAO() {
		return BDIDMEDICACAO;
	}

	public void setBDIDMEDICACAO(Integer bDIDMEDICACAO) {
		BDIDMEDICACAO = bDIDMEDICACAO;
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
