package ve.org.bcv.servicesImpl;

import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;
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
import ve.org.bcv.dto.ResultBoleanDto;
import ve.org.bcv.dto.UserEncuestaDto;
import ve.org.bcv.services.ParametroNominaService;

@Path("/encuestaCtrl")
@RequestScoped
@Named
public class EncuestaCtrlResource  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	@ParametroNominaServiceType1
	private ParametroNominaService parametroNominaService;;
	//public List<ParametroNominaDto> findByCoProcesoNom(String co_proceso_nom) {}
	
	@GET
	@Path("/{co_proceso_nom}")
	@Produces("application/json")
	public  BenefMedicamentoDto listar(@PathParam("co_proceso_nom") String co_proceso_nom)
			throws URISyntaxException {
		BenefMedicamentoDto benefMedicamentoDto = new BenefMedicamentoDto();
		List<ParametroNominaDto>  list=new ArrayList<ParametroNominaDto>();
		try {
			list=parametroNominaService.findByCoProcesoNom(co_proceso_nom);
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
	public  BenefMedicamentoDto listNo(@PathParam("co_proceso_nom") String co_proceso_nom,@PathParam("tx_parametro_nom") String tx_parametro_nom)
			throws URISyntaxException {
		BenefMedicamentoDto benefMedicamentoDto = new BenefMedicamentoDto();
		List<ParametroNominaDto>  list=new ArrayList<ParametroNominaDto>();
		try {
			list=parametroNominaService.findByCoProcesoNomNo(co_proceso_nom, tx_parametro_nom);
			benefMedicamentoDto.setParametroNominaDtos(list);
			
			
		  for (ParametroNominaDto l:benefMedicamentoDto.getParametroNominaDtos()){
			  System.out.println(l.getTx_parametro_nom());
		  }
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return benefMedicamentoDto;
	}
	
	
	@GET
	@Path("findUser/{cedula}")
	@Produces("application/json")
	public  UserEncuestaDto findUser(@PathParam("cedula") String cedula)
			throws URISyntaxException {
		 System.out.println("cedula="+cedula);
		 UserEncuestaDto userEncuestaDto=parametroNominaService.findUserEncuesta(cedula);
		return userEncuestaDto;
	}
	
	@GET
	@Path("personaYaEncuestada/{cedula}/{nuCedulaFamiliar}")
	@Produces("application/json")
	public  ResultBoleanDto personaYaEncuestada(@PathParam("cedula") String cedula,@PathParam("nuCedulaFamiliar") String nuCedulaFamiliar)
			throws URISyntaxException {
		 System.out.println("cedula="+cedula+",nuCedulaFamiliar="+nuCedulaFamiliar);
		 ResultBoleanDto resultBoleanDto=parametroNominaService.personaYaEncuestada( cedula,nuCedulaFamiliar);
		  
		return resultBoleanDto;
	}
	
	
	@PUT
	@Path("/save")
	@Consumes("application/json")
	@Produces("application/json")
	public List<ParametroNominaDto> save(List<ParametroNominaDto> lista)
			throws URISyntaxException {
		boolean salida=false;
		List<ParametroNominaDto> dtoSave= new ArrayList<ParametroNominaDto>();
		BenefMedicamentoDto benefMedicamentoDto = new BenefMedicamentoDto();
		List<ParametroNominaDto> listaCheck = new ArrayList<ParametroNominaDto>();
		boolean primeraVez=true;
		for (ParametroNominaDto dto: lista){
			 if (primeraVez){
					benefMedicamentoDto.setNuCedula(dto.getNuCedula());
					benefMedicamentoDto.setNuCedulaFamiliar(dto.getNuCedulaFamiliar());
					benefMedicamentoDto.setStRecibirMedicam(dto.getStRecibirMedicam());
					benefMedicamentoDto.setTxEmail(dto.getTxEmail());
					primeraVez=false;
			 }
			 if (dto.isChecked()){
				 listaCheck.add(dto);
			 }
			
			
		}
		salida=parametroNominaService.save(benefMedicamentoDto,listaCheck);
		if (!salida){
			dtoSave=null;
		}
		
		return dtoSave;
	}
	
	
	@GET
	@Path("graficar/{interesdos}")
	@Produces("application/json")
	public ChartDto graficar(@PathParam("interesdos")  String interesdos)
			throws URISyntaxException {
		ChartDto dto = new ChartDto();
		dto=	parametroNominaService.graficar(interesdos);
		if (dto==null){
			 dto = new ChartDto();
		}
		return dto;
	}
	
 
	
}
