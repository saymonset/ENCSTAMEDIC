package ve.org.bcv.dao;

import java.util.List;

public class BenefMedicamento {
	
 
	 private long nuIdBeneficiario ;
	 private String txEmail;
	 private int nuCedula;
	 private int nuCedulaFamiliar;
	 private String fecha;
	 private String nombreFamiliar;
	 private String parentesco;
	 private List<ParametroNomina> medicinas;
	public long getNuIdBeneficiario() {
		return nuIdBeneficiario;
	}
	public void setNuIdBeneficiario(long nuIdBeneficiario) {
		this.nuIdBeneficiario = nuIdBeneficiario;
	}
	public String getTxEmail() {
		return txEmail;
	}
	public void setTxEmail(String txEmail) {
		this.txEmail = txEmail;
	}
	public int getNuCedula() {
		return nuCedula;
	}
	public void setNuCedula(int nuCedula) {
		this.nuCedula = nuCedula;
	}
	public int getNuCedulaFamiliar() {
		return nuCedulaFamiliar;
	}
	public void setNuCedulaFamiliar(int nuCedulaFamiliar) {
		this.nuCedulaFamiliar = nuCedulaFamiliar;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getNombreFamiliar() {
		return nombreFamiliar;
	}
	public void setNombreFamiliar(String nombreFamiliar) {
		this.nombreFamiliar = nombreFamiliar;
	}
	public String getParentesco() {
		return parentesco;
	}
	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}
	 
	public List<ParametroNomina> getMedicinas() {
		return medicinas;
	}
	public void setMedicinas(List<ParametroNomina> medicinas) {
		this.medicinas = medicinas;
	}
}
