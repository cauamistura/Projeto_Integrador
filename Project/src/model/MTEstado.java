package model;

import control.ObjectDAO;

public class MTEstado extends ObjectDAO {

	Integer BDIDUF;

	String BDNOMEUF, BDSIGLAUF;

	public MTEstado() {
		super();
	}

	public Integer getBDIDUF() {
		return BDIDUF;
	}

	public void setBDIDUF(Integer bDIDUF) {
		BDIDUF = bDIDUF;
	}

	public String getBDNOMEUF() {
		return BDNOMEUF;
	}

	public void setBDNOMEUF(String bDNOMEUF) {
		BDNOMEUF = bDNOMEUF;
	}

	public String getBDSIGLAUF() {
		return BDSIGLAUF;
	}

	public void setBDSIGLAUF(String bDSIGLAUF) {
		BDSIGLAUF = bDSIGLAUF;
	}

	@Override
	public String toString() {
		return this.BDSIGLAUF;
	}

}
