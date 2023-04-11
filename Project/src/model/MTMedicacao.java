package model;

import control.ObjectDAO;

public class MTMedicacao extends ObjectDAO {

	Integer BDIDMEDICACAO;
	String BDNOMEMEDICACAO;
	String BDDESCRICAO;
	
	public MTMedicacao(Integer bDIDMEDICACAO, String bDNOMEMEDICACAO, String bDDESCRICAO) {
		super();
		BDIDMEDICACAO = bDIDMEDICACAO;
		BDNOMEMEDICACAO = bDNOMEMEDICACAO;
		BDDESCRICAO = bDDESCRICAO;
	}
	
	public MTMedicacao() {
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
	
	
}
