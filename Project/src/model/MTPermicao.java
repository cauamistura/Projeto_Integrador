package model;

import control.ObjectDAO;

public class MTPermicao extends ObjectDAO {
    private int BDIDPERMICAO;
    private String BDPERMICAO;
    private String BDDESCRISSAO;
    
	public int getBDIDPERMICAO() {
		return BDIDPERMICAO;
	}
	public void setBDIDPERMICAO(int bDIDPERMICAO) {
		BDIDPERMICAO = bDIDPERMICAO;
	}
	public String getBDPERMICAO() {
		return BDPERMICAO;
	}
	public void setBDPERMICAO(String bDPERMICAO) {
		BDPERMICAO = bDPERMICAO;
	}
	public String getBDDESCRISSAO() {
		return BDDESCRISSAO;
	}
	public void setBDDESCRISSAO(String bDDESCRISSAO) {
		BDDESCRISSAO = bDDESCRISSAO;
	}

}
