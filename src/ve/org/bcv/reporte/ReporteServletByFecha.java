package ve.org.bcv.reporte;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ve.org.bcv.dto.UserEncuestaDto;
import ve.org.bcv.services.Encuesta_caService;
import ve.org.bcv.servicesImpl.Encuesta_caServiceType1;
import ve.org.bcv.servicesImpl.ReporteServiceType1;
import ve.org.bcv.util.Pdf;

/**
 * Servlet implementation class ReporteServletByFecha
 */
@WebServlet(description = "open file reporteServletByFecha", urlPatterns = { "/reporteServletByFecha" })
public class ReporteServletByFecha extends HttpServlet {
	@Inject
	@Encuesta_caServiceType1
	private Encuesta_caService encuesta_caService;
	@Inject
	@ReporteServiceType1
	private ReporteService reporteService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doSame(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doSame(req, res);
	}

	public void doSame(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Date fechaDesdeR = request.getParameter("fechaDesdeR") != null ? new Date(request.getParameter("fechaDesdeR"))
				: null;
		Date fechaHastaR = request.getParameter("fechaHastaR") != null ? new Date(request.getParameter("fechaHastaR"))
				: null;

		List<File> archivosPDF = null;
		
		List<String> cedulas = encuesta_caService.buscarCedulaByFechas(fechaDesdeR, fechaHastaR);
//		UserEncuestaDto userEncuestaDto = null;
//		Map parameters = null;
//		
//
//		
//		lists = new ArrayList<UserEncuestaDto>();
//		for (String ced : cedulas) {
//			/** Por cadacedula buscamos la encuesta medicamentos */
//			userEncuestaDto = encuesta_caService.buscarCedulaMedicamentos(ced);
//			
//			if (null!=userEncuestaDto){
//				
//				lists.add(userEncuestaDto);
//				
//			}
//		
//		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String feDesdeStr = sdf.format(fechaDesdeR);
		String feHastaStr = sdf.format(fechaHastaR);
		List<UserEncuestaDto> lists =  encuesta_caService. buscarCedulaMedicamentosQuery (feDesdeStr, feHastaStr);
	

		archivosPDF = new ArrayList<File>();
		Collection collection = null;
		collection = lists;
		Map parameters = new HashMap();
		try {
			File file = reporteService.reporte(collection, parameters);
			if (null!=file){
				archivosPDF.add(file);	
			}
			
		} catch (Exception e1) {
			System.out.println(e1.toString());
			e1.printStackTrace();
		}
		
		File f = null;
		try {
			if (archivosPDF!=null && archivosPDF.size()>0){
						f = Pdf.pegarArchivosPDF(archivosPDF, "encuesta");
						if (null==f){
							System.out.println("");
						}else{
							OutputStream outStream = response.getOutputStream();
							String fileName = "reporteMedicamentos.pdf";
							response.setContentType("application/pdf");
							response.setContentLength((int) f.length());
							response.setHeader("Content-disposition", "attachment; filename=\"" + fileName + "\"");

							byte[] buf = new byte[8192];
							FileInputStream inStream = new FileInputStream(f);
							int sizeRead = 0;
							while ((sizeRead = inStream.read(buf, 0, buf.length)) > 0) {
								outStream.write(buf, 0, sizeRead);
							}
							inStream.close();
							outStream.close();

						}
						
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
