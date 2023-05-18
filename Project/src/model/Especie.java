package model;

public class Especie extends Raca {

	Integer BDIDESPECIE;

	String BDNOMEESPECIE;

	public Integer getBDIDESPECIE() {
		return BDIDESPECIE;
	}

	public void setBDIDESPECIE(Integer bDIDESPECIE) {
		BDIDESPECIE = bDIDESPECIE;
	}

	public String getBDNOMEESPECIE() {
		return BDNOMEESPECIE;
	}

	public void setBDNOMEESPECIE(String bDNOMEESPECIE) {
		BDNOMEESPECIE = bDNOMEESPECIE;
	}

	@Override
	public String toString() {
		return this.BDNOMEESPECIE;
	}

}
