package model;

import control.ObjectDAO;

public class MTUser extends MTClinica {
			
	Integer BDIDUSER,
			BDIDCLINICA;
	String  BDCPF,
			BDMAIL,
			BDSENHA,
			BDIDPERMICAO;
	
	public Integer getBDIDUSER() {
		return BDIDUSER;
	}
	public void setBDIDUSER(Integer bDIDUSER) {
		BDIDUSER = bDIDUSER;
	}
	public Integer getBDIDCLINICA() {
		return BDIDCLINICA;
	}
	public void setBDIDCLINICA(Integer bDIDCLINICA) {
		BDIDCLINICA = bDIDCLINICA;
	}
	public String getBDCPF() {
		return BDCPF;
	}
	public void setBDCPF(String bDCPF) {
		BDCPF = bDCPF;
	}
	public String getBDMAIL() {
		return BDMAIL;
	}
	public void setBDMAIL(String bDMAIL) {
		BDMAIL = bDMAIL;
	}
	public String getBDSENHA() {
		return BDSENHA;
	}
	public void setBDSENHA(String bDSENHA) {
		BDSENHA = bDSENHA;
	}
	public String getBDIDPERMICAO() {
		return BDIDPERMICAO;
	}
	public void setBDIDPERMICAO(String bDIDPERMICAO) {
		BDIDPERMICAO = bDIDPERMICAO;
	}


}
