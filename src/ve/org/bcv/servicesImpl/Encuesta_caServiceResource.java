package ve.org.bcv.servicesImpl;

import java.io.Serializable;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ve.org.bcv.dto.BenefMedicamentoDto;
import ve.org.bcv.dto.ChartDto;
import ve.org.bcv.dto.ParametroNominaDto;
import ve.org.bcv.dto.UserEncuestaDto;
import ve.org.bcv.mail.MandarMail;
import ve.org.bcv.reporte.ReporteService;
import ve.org.bcv.services.Encuesta_caService;

@Path("/encuesta_caService")
@RequestScoped
@Named
public class Encuesta_caServiceResource implements Serializable {

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
	// public List<ParametroNominaDto> findByCoProcesoNom(String co_proceso_nom)
	// {}

	@GET
	@Path("/{co_proceso_nom}")
	@Produces("application/json")
	public BenefMedicamentoDto listar(@PathParam("co_proceso_nom") String co_proceso_nom) throws URISyntaxException {
		BenefMedicamentoDto benefMedicamentoDto = new BenefMedicamentoDto();
		List<ParametroNominaDto> list = new ArrayList<ParametroNominaDto>();
		try {
			// list=encuesta_caService.findByCoProcesoNom(co_proceso_nom);
			benefMedicamentoDto.setParametroNominaDtos(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return benefMedicamentoDto;
	}

	@GET
	@Path("/buscarMedicamento/{filtro}")
	@Produces("application/json")
	public BenefMedicamentoDto buscarMedicamento(@PathParam("filtro") String filtro) throws URISyntaxException {
		BenefMedicamentoDto benefMedicamentoDto = new BenefMedicamentoDto();
		List<ParametroNominaDto> list = new ArrayList<ParametroNominaDto>();
		try {
			list = encuesta_caService.buscarMedicamento(filtro);
			benefMedicamentoDto.setParametroNominaDtos(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return benefMedicamentoDto;
	}

	
	@GET
	@Path("listNo/{co_proceso_nom}/{tx_parametro_nom}")
	@Produces("application/json")
	public BenefMedicamentoDto listNo(@PathParam("co_proceso_nom") String co_proceso_nom,
			@PathParam("tx_parametro_nom") String tx_parametro_nom) throws URISyntaxException {
		BenefMedicamentoDto benefMedicamentoDto = new BenefMedicamentoDto();
		List<ParametroNominaDto> list = new ArrayList<ParametroNominaDto>();
		try {
			list = encuesta_caService.findByCoProcesoNomNo(co_proceso_nom, tx_parametro_nom);
			benefMedicamentoDto.setParametroNominaDtos(list);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return benefMedicamentoDto;
	}

	@GET
	@Path("findUser/{cedula}")
	@Produces("application/json")
	public UserEncuestaDto findUser(@PathParam("cedula") String cedula) throws URISyntaxException {
		UserEncuestaDto userEncuestaDto = encuesta_caService.findUserEncuesta(cedula);
		return userEncuestaDto;
	}

	@GET
	@Path("parametroNominaDtos/{cedula}/{nuCedulaFamiliar}")
	@Produces("application/json")
	public  List<ParametroNominaDto>  parametroNominaDtos(@PathParam("cedula") String cedula,
			@PathParam("nuCedulaFamiliar") String nuCedulaFamiliar) throws URISyntaxException {
		 List<ParametroNominaDto>  lists = encuesta_caService.buscarMedicamentosFamiliar(cedula, nuCedulaFamiliar);

		return lists;
	}
	
	
	@GET
	@Path("familiares/{cedula}")
	@Produces("application/json")
	public  List<UserEncuestaDto>  familiares(@PathParam("cedula") String cedula) throws URISyntaxException {
		 List<UserEncuestaDto>  lists = encuesta_caService.familiares(cedula);
		return lists;
	}
	
	

	@PUT
	@Path("/save")
	@Consumes("application/json")
	@Produces("application/json")
	public List<ParametroNominaDto> save(List<ParametroNominaDto> lista) throws URISyntaxException {
		boolean salida = false;
		List<ParametroNominaDto> dtoSave = new ArrayList<ParametroNominaDto>();
		BenefMedicamentoDto benefMedicamentoDto = new BenefMedicamentoDto();
		List<ParametroNominaDto> listaCheck = new ArrayList<ParametroNominaDto>();
		boolean primeraVez = true;
		for (ParametroNominaDto dto : lista) {
			if (primeraVez) {
				benefMedicamentoDto.setNuCedula(dto.getNuCedula());
				benefMedicamentoDto.setNuCedulaFamiliar(dto.getNuCedulaFamiliar());
				benefMedicamentoDto.setStRecibirMedicam(dto.getStRecibirMedicam());
				benefMedicamentoDto.setTxEmail(dto.getTxEmail());
				primeraVez = false;
			}
			if (dto.isChecked()) {
				listaCheck.add(dto);
			}

		}
		salida = encuesta_caService.save(benefMedicamentoDto, listaCheck);
		if (!salida) {
			dtoSave = null;
		}

		return dtoSave;
	}

	@GET
	@Path("/graficar/{interesdos}/{feDesde}/{feHasta}")
	@Produces("application/json")
	public ChartDto graficar(@PathParam("interesdos") String interesdos,@PathParam("feDesde") Date feDesde,@PathParam("feHasta") Date feHasta) throws URISyntaxException {
		ChartDto dto = new ChartDto();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String feDesdeStr=sdf.format(feDesde);
		String feHastaStr=sdf.format(feHasta);
		dto = encuesta_caService.graficar(interesdos,  feDesdeStr,  feHastaStr);
		if (dto == null) {
			dto = new ChartDto();
		}
		return dto;
	}
	
	@GET
	@Path("buscarCedulaMedicamento/{cedula}")
	@Produces("application/json")
	public UserEncuestaDto buscarCedulaMedicamentos(@PathParam("cedula") String cedula) throws URISyntaxException {
		UserEncuestaDto userEncuestaDto = encuesta_caService.buscarCedulaMedicamentos(cedula);
		return userEncuestaDto;
	}

	@GET
	@Path("buscarCedulaMedicamentos/{feDesde}/{feHasta}")
	@Produces("application/json")
	public List<UserEncuestaDto>  buscarCedulaMedicamentos(@PathParam("feDesde") Date feDesde,@PathParam("feHasta") Date feHasta) throws URISyntaxException {
		List<UserEncuestaDto>  lists = encuesta_caService.buscarCedulaMedicamentos(feDesde,feHasta);
	 

		return lists;
	}



}
