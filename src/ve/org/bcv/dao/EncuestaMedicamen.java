package ve.org.bcv.dao;

import java.util.Date;

public class EncuestaMedicamen {
	 private long nuIdBeneficiario ;
	 private String  coProcesoNom;
	 private String  coParametroNom;
	 private  Date feRegPago;
	 private String  stRecibirMedicam ;
	public long getNuIdBeneficiario() {
		return nuIdBeneficiario;
	}
	public void setNuIdBeneficiario(long nuIdBeneficiario) {
		this.nuIdBeneficiario = nuIdBeneficiario;
	}
	public String getCoProcesoNom() {
		return coProcesoNom;
	}
	public void setCoProcesoNom(String coProcesoNom) {
		this.coProcesoNom = coProcesoNom;
	}
	public String getCoParametroNom() {
		return coParametroNom;
	}
	public void setCoParametroNom(String coParametroNom) {
		this.coParametroNom = coParametroNom;
	}
	public Date getFeRegPago() {
		return feRegPago;
	}
	public void setFeRegPago(Date feRegPago) {
		this.feRegPago = feRegPago;
	}
	public String getStRecibirMedicam() {
		return stRecibirMedicam;
	}
	public void setStRecibirMedicam(String stRecibirMedicam) {
		this.stRecibirMedicam = stRecibirMedicam;
	}
}
