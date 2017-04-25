package ve.org.bcv.dto;

import java.util.ArrayList;
import java.util.List;

public class UserEncuestaReporteDto {
	private String nombre;
	private String tipoEmp;
	private String txEmail;
	private int nuCedula;
	private int nuCedulaFamiliar;
	/** Status recibir medicamentos */
	private String stRecibirMedicam;
	private String codigoUbicacion;
	private String nuExtension_1;
	 private long nuIdBeneficiario ;
	
	/**Medicamentos**/
	private List<ParametroNominaDto> parametroNominaDtos;
	
	private String va_parametro_nom; // Es el valor del parametro
	private String tx_parametro_nom; // Es la descripcion del parametro
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipoEmp() {
		return tipoEmp;
	}
	public void setTipoEmp(String tipoEmp) {
		this.tipoEmp = tipoEmp;
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
	public String getStRecibirMedicam() {
		return stRecibirMedicam;
	}
	public void setStRecibirMedicam(String stRecibirMedicam) {
		this.stRecibirMedicam = stRecibirMedicam;
	}
	public String getVa_parametro_nom() {
		return va_parametro_nom;
	}
	public void setVa_parametro_nom(String va_parametro_nom) {
		this.va_parametro_nom = va_parametro_nom;
	}
	public String getTx_parametro_nom() {
		return tx_parametro_nom;
	}
	public void setTx_parametro_nom(String tx_parametro_nom) {
		this.tx_parametro_nom = tx_parametro_nom;
	}
	public static List<UserEncuestaReporteDto> listAllBean(){
		List<UserEncuestaReporteDto> list = new ArrayList<UserEncuestaReporteDto>();
		return list;
		
	}
	public String getCodigoUbicacion() {
		return codigoUbicacion;
	}
	public void setCodigoUbicacion(String codigoUbicacion) {
		this.codigoUbicacion = codigoUbicacion;
	}
	public String getNuExtension_1() {
		return nuExtension_1;
	}
	public void setNuExtension_1(String nuExtension_1) {
		this.nuExtension_1 = nuExtension_1;
	}
	public long getNuIdBeneficiario() {
		return nuIdBeneficiario;
	}
	public void setNuIdBeneficiario(long nuIdBeneficiario) {
		this.nuIdBeneficiario = nuIdBeneficiario;
	}
	public List<ParametroNominaDto> getParametroNominaDtos() {
		return parametroNominaDtos;
	}
	public void setParametroNominaDtos(List<ParametroNominaDto> parametroNominaDtos) {
		this.parametroNominaDtos = parametroNominaDtos;
	}
}
