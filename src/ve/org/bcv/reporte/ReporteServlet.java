package ve.org.bcv.reporte;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ve.org.bcv.dto.BenefMedicamentoDto;
import ve.org.bcv.dto.ParametroNominaDto;
import ve.org.bcv.dto.UserEncuestaDto;
import ve.org.bcv.dto.UserEncuestaReporteDto;
import ve.org.bcv.services.Encuesta_caService;
import ve.org.bcv.servicesImpl.Encuesta_caServiceType1;
import ve.org.bcv.servicesImpl.ReporteServiceType1;
import ve.org.bcv.util.Pdf;

/**
 * Servlet implementation class Reporte
 */
@WebServlet(description = "open file", urlPatterns = { "/reporte" })
public class ReporteServlet extends HttpServlet {
	@Inject
	@Encuesta_caServiceType1
	private Encuesta_caService encuesta_caService;
	@Inject
	@ReporteServiceType1
	private ReporteService reporteService ;
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

		
		
		String cedula = request.getParameter("cedula");
		int ced = cedula != null ? Integer.parseInt(cedula) : 0;
		String cedulaFamiliar = request.getParameter("cedulaFamiliar");
		int cedFam = cedulaFamiliar != null ? Integer.parseInt(cedulaFamiliar) : 0;
		
		 
		
		List<File> archivosPDF = new ArrayList<File>();
		List<UserEncuestaDto> lists = new ArrayList<UserEncuestaDto>();
		UserEncuestaDto userEncuestaDto = encuesta_caService.buscarCedulaMedicamentos(cedula, cedulaFamiliar);
		
		
		lists.add(userEncuestaDto);
		Collection collection = lists;
		Map parameters = null;
		parameters = new HashMap();

		try {
			archivosPDF.add(reporteService.reporte(collection, parameters));
		} catch (Exception e1) {
			System.out.println(e1.toString());
			e1.printStackTrace();
		}

		File f = null;
		try {
			f = Pdf.pegarArchivosPDF(archivosPDF, "encuesta");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		 
		
	 

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
