package ve.org.bcv.servicesImpl;

import java.io.File;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ve.org.bcv.dto.BenefMedicamentoDto;
import ve.org.bcv.dto.ParametroNominaDto;
import ve.org.bcv.dto.UserEncuestaDto;
import ve.org.bcv.dto.UserEncuestaReporteDto;
import ve.org.bcv.mail.MandarMail;
import ve.org.bcv.reporte.ReporteService;
import ve.org.bcv.services.Encuesta_caService;
import ve.org.bcv.util.Pdf;

@Path("/mandarMailResource")
@RequestScoped
@Named
public class MandarMailResource  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	@Encuesta_caServiceType1
	private Encuesta_caService encuesta_caService;
	@Inject
	@ReporteServiceType1
	private ReporteService reporteService;
	@Inject
	@MandarMailType1
	private MandarMail mandarMail;

	@POST
	@Path("/sendMail/{to}/{cedula}/{cedulaFamiliar}/{smtpUser}/{smtpPassword}/{message}/{title}")
	@Produces("application/json")
	public BenefMedicamentoDto sendMail(@PathParam("to") String to, @PathParam("cedula") int cedula,
			@PathParam("cedulaFamiliar") int cedulaFamiliar, @PathParam("smtpUser") String smtpUser,
			@PathParam("smtpPassword") String smtpPassword, @PathParam("message") String message,
			@PathParam("title") String title) throws URISyntaxException {
		BenefMedicamentoDto benefMedicamentoDto = new BenefMedicamentoDto();
		benefMedicamentoDto.setStRecibirMedicam("no");
		if (null != smtpUser && smtpUser.length() > 0) {
		 
			
			UserEncuestaDto userEncuestaDto = encuesta_caService.buscarCedulaMedicamentos(cedula+"", cedulaFamiliar+"");
			
			List<UserEncuestaReporteDto> data = new ArrayList<UserEncuestaReporteDto>();
			UserEncuestaReporteDto userEncuestaReporteDto = new UserEncuestaReporteDto();
			userEncuestaReporteDto.setNombre(userEncuestaDto.getNombre());
			userEncuestaReporteDto.setNuCedula(cedula);
			userEncuestaReporteDto.setNuCedulaFamiliar(cedulaFamiliar);
			userEncuestaReporteDto.setTipoEmp(userEncuestaDto.getTipoEmp());

			List<BenefMedicamentoDto> benefMedicamentos = userEncuestaDto.getBenefMedicamentos();
			if (null != benefMedicamentos && benefMedicamentos.size() > 0) {
				userEncuestaReporteDto.setStRecibirMedicam(benefMedicamentos.get(0).getStRecibirMedicam());
				userEncuestaReporteDto.setTxEmail(benefMedicamentos.get(0).getTxEmail());
				List<ParametroNominaDto> medicamentos = benefMedicamentos.get(0).getParametroNominaDtos();
				if (null != medicamentos && medicamentos.size() > 0) {
					boolean primeraVez = true;
					StringBuilder result = new StringBuilder("");
					for (ParametroNominaDto med : medicamentos) {
						if (!primeraVez) {
							result.append(",");
						}
						primeraVez = false;
						result.append(med.getTx_parametro_nom()).append(" ").append(med.getVa_parametro_nom());
					}

					userEncuestaReporteDto.setTx_parametro_nom(result.toString());
				}
			}
			
			List<File> archivosPDF = new ArrayList<File>();
			List<UserEncuestaDto> lists = new ArrayList<UserEncuestaDto>();
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
			
			
			 

			try {
				benefMedicamentoDto.setStRecibirMedicam("si");
				String from = smtpUser;
				smtpUser = smtpUser.substring(0, smtpUser.indexOf("@") );
				mandarMail.send(from, to, title, message, f.getAbsolutePath(), smtpUser, smtpPassword);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				f.delete();
			}
		}
		return benefMedicamentoDto;
	}

}
