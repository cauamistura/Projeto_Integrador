package model;

public class Raca extends DadosUser{
	
	Integer BDIDRACA,
			BDIDESPECIE;
	
	String BDNOMERACA;

	public Integer getBDIDRACA() {
		return BDIDRACA;
	}

	public void setBDIDRACA(Integer bDIDRACA) {
		BDIDRACA = bDIDRACA;
	}

	public Integer getBDIDESPECIE() {
		return BDIDESPECIE;
	}

	public void setBDIDESPECIE(Integer bDIDESPECIE) {
		BDIDESPECIE = bDIDESPECIE;
	}

	public String getBDNOMERACA() {
		return BDNOMERACA;
	}

	public void setBDNOMERACA(String bDNOMERACA) {
		BDNOMERACA = bDNOMERACA;
	}
	
	@Override
	public String toString() {
		return this.BDNOMERACA;
	}
}
