package ve.org.bcv.dto;

import java.util.ArrayList;
import java.util.List;

/** Beneficiarios de los medicamentos
 * @author Ing Simon Alberto Rodriguez Pacheco
   14 de dic. de 2016 2:08:41 p. m.
 *
 * mail: oraclefedora@gmail.com
 */
public class BenefMedicamentoDto {
	private List<ParametroNominaDto> parametroNominaDtos;
	 private long nuIdBeneficiario ;
	 private String txEmail;
	 private int nuCedula;
	 private int nuCedulaFamiliar;
	 private String fecha ;
	 private String nombreFamiliar;
	 private String parentesco;
	 /**Status recibir medicamentos*/
	 private String  stRecibirMedicam ;
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
	public List<ParametroNominaDto> getParametroNominaDtos() {
		return parametroNominaDtos;
	}
	public void setParametroNominaDtos(List<ParametroNominaDto> parametroNominaDtos) {
		this.parametroNominaDtos = parametroNominaDtos;
	}
	public String getStRecibirMedicam() {
		return stRecibirMedicam;
	}
	public void setStRecibirMedicam(String stRecibirMedicam) {
		this.stRecibirMedicam = stRecibirMedicam;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public static List<BenefMedicamentoDto> listAllBean(){
		List<BenefMedicamentoDto> list = new ArrayList<BenefMedicamentoDto>();
		return list;
		
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
}
