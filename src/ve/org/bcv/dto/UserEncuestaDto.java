package ve.org.bcv.dto;

import java.util.ArrayList;
import java.util.List;
public class UserEncuestaDto {
	private String nombre;
	private String tipoEmp;
	private String codigoUbicacion;
	private String nuExtension_1;
	private String cedula;
	private String fechaNacimiento;
	private List<BenefMedicamentoDto> benefMedicamentos;
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
	public List<BenefMedicamentoDto> getBenefMedicamentos() {
		return benefMedicamentos;
	}
	public void setBenefMedicamentos(List<BenefMedicamentoDto> benefMedicamentos) {
		this.benefMedicamentos = benefMedicamentos;
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
	public UserEncuestaDto() {
		super();
	}

 
	public static List<UserEncuestaDto> listAllBean(){
		List<UserEncuestaDto> list = new ArrayList<UserEncuestaDto>();
		return list;
		
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
}
