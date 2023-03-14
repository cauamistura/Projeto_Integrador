package model;

import control.ObjectDAO;

public class MTPetUser extends ObjectDAO {

	Integer BDIDPET,
			BDIDUSER,
			BDIDCLINICA;

	public Integer getBDIDPET() {
		return BDIDPET;
	}

	public void setBDIDPET(Integer bDIDPET) {
		BDIDPET = bDIDPET;
	}

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
	
}
