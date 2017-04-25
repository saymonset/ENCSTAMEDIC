package ve.org.bcv.reporte;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import ve.org.bcv.servicesImpl.ReporteServiceType1;
import ve.org.bcv.util.Pdf;
@ReporteServiceType1
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ReporteServiceImpl implements ReporteService {


	public File reporte(String cedula,String cedulaFamiliar,Collection collection,Map parameters) throws Exception {
		// GENERAMOS EL REPORTE
		
		InputStream is = null;
		is = ReporteService.class.getResourceAsStream("logo_bcv.jpg");
		parameters.put("logo", is);
		/** Pagos y tributos */
		InputStream jrxml = null;
		jrxml = ReporteService.class.getResourceAsStream("reporteMedicamentos2.jrxml");

	
		InputStream inputStream = generar(jrxml, parameters,collection);
		File result = Pdf.inputStreamToFile(inputStream);
		/******** Fin Reporte memorando ************/

		return result;
	
	
	}
	
	
	public File reporte(Collection collection, Map parameters) throws Exception {
		// TODO Auto-generated method stub
		return reporte(null,null,collection,parameters);
	}

	
	
	public InputStream generar(InputStream jrxml, Map<String, Object> parameters,Collection collection) {
		InputStream out=null;
		try {
			
				JasperDesign jasperDesign = null;
				JasperReport jasperReport = null;
				JasperPrint jasperPrint = null;
//				/**Load the JRXML TO GENERATE JASPER REPORT*/
				jasperDesign = JRXmlLoader.load(jrxml);
//				/**Load the JRXML TO GENERATE JASPER REPORT*/
				/**put the datasource and the parameters en jasperreport*/
				jasperReport = JasperCompileManager.compileReport(jasperDesign);
				/**send to print or xls, pdf, csv etc*/
				/**Chequearemos que la factura tenga un nuemro de control por lo menos..*/
				try {
					/**Buscaremos todos los objetos*****/
					
					
						JRDataSource dataSource = new JREmptyDataSource();
						jasperPrint = JasperFillManager.fillReport(jasperReport,
								parameters,
						new JRBeanCollectionDataSource(
										collection));
						
						 byte[]bytes=JasperExportManager.exportReportToPdf( jasperPrint);
						 
						 
						 out = new ByteArrayInputStream(bytes);
						 
						 
						 
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.toString());
				}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("EXCEPTION: " + ex.toString());
		}
		return out;
		
	}
	
	public List<?> searchObjects() {
		
		return null;
	}



}
