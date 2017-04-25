package ve.org.bcv.reporte;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ve.org.bcv.dto.UserEncuestaDto;
import ve.org.bcv.excel.Main;
import ve.org.bcv.excel.ReadExcelProcess;
import ve.org.bcv.excel.WorkWithSheet;
import ve.org.bcv.services.Encuesta_caService;
import ve.org.bcv.servicesImpl.Encuesta_caServiceType1;
import ve.org.bcv.servicesImpl.ReporteServiceType1;
import ve.org.bcv.util.Pdf;

@WebServlet(description = "open file reporteServletByFecha", urlPatterns = { "/reporteServletExcelByFecha" })
public class ReporteServletExcelByFecha extends HttpServlet {
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

	 

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String feDesdeStr = sdf.format(fechaDesdeR);
		String feHastaStr = sdf.format(fechaHastaR);
		System.out.println("-----------2017/04/11 start--------***--------------------------------------");
		System.out.println("feDesdeStr = "+feDesdeStr);
		System.out.println("feHastaStr = "+feHastaStr);
		System.out.println("-----------2017/04/11 end----------------------------------------------");
		List<UserEncuestaDto> lists =  encuesta_caService. buscarCedulaMedicamentosQuery (feDesdeStr, feHastaStr);
		 

	 
		InputStream is = null;
		is = Main.class.getResourceAsStream("FORMATO_EXCEL_ENCUESTAS_MEDICAS.xls");
		File result = null;
		try {
			result = Pdf.inputStreamToFile(is, "xls");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (null != result) {
			String path = result.getAbsolutePath();
			/** Obtenemos todas las filas de la hoja en excel */
			// (sheet,rowEnd, col, path);
			WorkWithSheet readSheet = new ReadExcelProcess(path, 1);
			File f = new File(path);
			try {

				FileInputStream fileInputStream = readSheet.writeRow(lists);
				
				try {
						if (null == f) {
							System.out.println("");
						} else {
							OutputStream outStream = response.getOutputStream();
							String fileName = "reporteMedicamentos.xls";
							response.setContentType("application/vnd.ms-excel");
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
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if (null != f){
					f.delete();
				}
			}
		}

	

	}

}
