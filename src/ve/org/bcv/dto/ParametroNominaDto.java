package ve.org.bcv.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ParametroNominaDto {
	
	private String co_proceso_nom ; //Es el CO_PROVEEDOR
	private String co_parametro_nom; //CO_MEDICAMENTO
	private String va_parametro_nom; //
	private String tx_parametro_nom ; //NB_MEDICAMENTO
	private int  cantidadEntregada;
	private BigDecimal  precioUnitario ;	
	private int cantSolicitada = 1;
	private String  feRegPagoStr;
	
	private boolean isChecked ; 
	
	 
	/****Datos del beneficiario************/
	
	 private String txEmail;
	 private int nuCedula;
	 private int nuCedulaFamiliar;
	 /**Status recibir medicamentos*/
	 private String  stRecibirMedicam ;
	 
	
	public String getCo_proceso_nom() {
		return co_proceso_nom;
	}
	public void setCo_proceso_nom(String co_proceso_nom) {
		this.co_proceso_nom = co_proceso_nom;
	}
	public String getCo_parametro_nom() {
		return co_parametro_nom;
	}
	public void setCo_parametro_nom(String co_parametro_nom) {
		this.co_parametro_nom = co_parametro_nom;
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
	public boolean isChecked() {
		return isChecked;
	}
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
	 
	public String getStRecibirMedicam() {
		return stRecibirMedicam;
	}
	public void setStRecibirMedicam(String stRecibirMedicam) {
		this.stRecibirMedicam = stRecibirMedicam;
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
 
	public static List<ParametroNominaDto> listAllBean(){
		List<ParametroNominaDto> list = new ArrayList<ParametroNominaDto>();
		return list;
		
	}
	public int getCantSolicitada() {
		return cantSolicitada;
	}
	public void setCantSolicitada(int cantSolicitada) {
		this.cantSolicitada = cantSolicitada;
	}

	public int getCantidadEntregada() {
		return cantidadEntregada;
	}
	public void setCantidadEntregada(int cantidadEntregada) {
		this.cantidadEntregada = cantidadEntregada;
	}
	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public String getFeRegPagoStr() {
		return feRegPagoStr;
	}
	public void setFeRegPagoStr(String feRegPagoStr) {
		this.feRegPagoStr = feRegPagoStr;
	}
}
