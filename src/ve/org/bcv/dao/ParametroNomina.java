package ve.org.bcv.dao;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author sirodrig
 *
 */


 




public class ParametroNomina {
	 private long nuIdBeneficiario ;
	private String co_proceso_nom ; //Es el CO_PROVEEDOR
	private String co_parametro_nom; //CO_MEDICAMENTO
	private String va_parametro_nom; //
	private String tx_parametro_nom ; //NB_MEDICAMENTO
	private Date  feRegPago;
	private String  feRegPagoStr;
	private int cantSolicitada;
	private int  cantidadEntregada;
	private BigDecimal  precioUnitario ;	
	
	
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
	public long getNuIdBeneficiario() {
		return nuIdBeneficiario;
	}
	public void setNuIdBeneficiario(long nuIdBeneficiario) {
		this.nuIdBeneficiario = nuIdBeneficiario;
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
	public int getCantSolicitada() {
		return cantSolicitada;
	}
	public void setCantSolicitada(int cantSolicitada) {
		this.cantSolicitada = cantSolicitada;
	}
	public String getFeRegPagoStr() {
		return feRegPagoStr;
	}
	public void setFeRegPagoStr(String feRegPagoStr) {
		this.feRegPagoStr = feRegPagoStr;
	}
	 

}
