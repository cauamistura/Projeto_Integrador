package model;

import control.ObjectDAO;

public class Medicamento extends ObjectDAO {

	Integer BDIDMEDICACAO;
	String BDNOMEMEDICACAO;
	String BDDESCRICAO;
	
	public Medicamento(Integer bDIDMEDICACAO, String bDNOMEMEDICACAO, String bDDESCRICAO) {
		super();
		BDIDMEDICACAO = bDIDMEDICACAO;
		BDNOMEMEDICACAO = bDNOMEMEDICACAO;
		BDDESCRICAO = bDDESCRICAO;
	}
	
	public Medicamento() {
		super();
	}
	
	public Integer getBDIDMEDICACAO() {
		return BDIDMEDICACAO;
	}

	public void setBDIDMEDICACAO(Integer bDIDMEDICACAO) {
		BDIDMEDICACAO = bDIDMEDICACAO;
	}

	public String getBDNOMEMEDICACAO() {
		return BDNOMEMEDICACAO;
	}

	public void setBDNOMEMEDICACAO(String bDNOMEMEDICACAO) {
		BDNOMEMEDICACAO = bDNOMEMEDICACAO;
	}

	public String getBDDESCRICAO() {
		return BDDESCRICAO;
	}

	public void setBDDESCRICAO(String bDDESCRICAO) {
		BDDESCRICAO = bDDESCRICAO;
	}

	@Override
	public String toString() {
		return BDNOMEMEDICACAO;
	}
	
	
	
}
