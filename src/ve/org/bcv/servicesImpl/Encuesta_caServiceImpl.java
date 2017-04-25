package ve.org.bcv.servicesImpl;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;

import ve.org.bcv.dao.BenefMedicamento;
import ve.org.bcv.dao.ParametroNomina;
import ve.org.bcv.dao.ResultBolean;
import ve.org.bcv.dao.UserEncuesta;
import ve.org.bcv.dao.local.BenefMedicamentoLocal;
import ve.org.bcv.dao.local.FaCatalogoMedicamentoLocal;
import ve.org.bcv.dto.BenefMedicamentoDto;
import ve.org.bcv.dto.ChartDto;
import ve.org.bcv.dto.DataChart;
import ve.org.bcv.dto.DatasetsChart;
import ve.org.bcv.dto.OptionsChart;
import ve.org.bcv.dto.ParametroNominaDto;
import ve.org.bcv.dto.ResultBoleanDto;
import ve.org.bcv.dto.ScalesChart;
import ve.org.bcv.dto.TicksChart;
import ve.org.bcv.dto.UserEncuestaDto;
import ve.org.bcv.dto.UserEncuestaReporteDto;
import ve.org.bcv.dto.YAxesChart;
import ve.org.bcv.services.Encuesta_caService;

@Encuesta_caServiceType1
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class Encuesta_caServiceImpl implements Encuesta_caService {
	@Inject
	private FaCatalogoMedicamentoLocal faCatalogoMedicamentoLocal;
	@Inject
	private BenefMedicamentoLocal benefMedicamentoLocal;

	public List<ParametroNominaDto> findByCoProcesoNom(String co_proceso_nom) {
		ParametroNomina parametroNomina = new ParametroNomina();
		List<ParametroNomina> parametroNominas = faCatalogoMedicamentoLocal
				.findByCoProcesoNom(parametroNomina.getCo_proceso_nom());
		List<ParametroNominaDto> dtos = new ArrayList<ParametroNominaDto>();
		if (parametroNominas != null && parametroNominas.size() > 0) {
			ParametroNominaDto dto = null;
			for (ParametroNomina dao : parametroNominas) {
				dto = new ParametroNominaDto();
				try {
					BeanUtils.copyProperties(dto, dao);
					dto.setCo_proceso_nom(dao.getCo_proceso_nom());
					dto.setCo_parametro_nom(dao.getCo_parametro_nom());
					dto.setVa_parametro_nom(dao.getVa_parametro_nom());
					dto.setTx_parametro_nom(dao.getTx_parametro_nom());
					dtos.add(dto);

				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return dtos;
	}

	public List<ParametroNominaDto> buscarMedicamento(String filtro) {
		List<ParametroNomina> parametroNominas = faCatalogoMedicamentoLocal.buscarMedicamento(filtro);
		List<ParametroNominaDto> dtos = new ArrayList<ParametroNominaDto>();
		if (parametroNominas != null && parametroNominas.size() > 0) {
			ParametroNominaDto dto = null;
			for (ParametroNomina dao : parametroNominas) {
				dto = new ParametroNominaDto();
				try {
					BeanUtils.copyProperties(dto, dao);
					dto.setCo_proceso_nom(dao.getCo_proceso_nom());
					dto.setCo_parametro_nom(dao.getCo_parametro_nom());
					dto.setVa_parametro_nom(dao.getVa_parametro_nom());
					dto.setTx_parametro_nom(dao.getTx_parametro_nom());
					dto.setCantSolicitada(dao.getCantSolicitada());
					dtos.add(dto);

				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return dtos;
	}

	public List<ParametroNominaDto> findByCoProcesoNomNo(String co_proceso_nom, String tx_parametro_nom) {
		ParametroNomina parametroNomina = new ParametroNomina();
		List<ParametroNomina> parametroNominas = faCatalogoMedicamentoLocal
				.findByCoProcesoNomNo(parametroNomina.getCo_proceso_nom(), tx_parametro_nom);
		List<ParametroNominaDto> dtos = new ArrayList<ParametroNominaDto>();
		if (parametroNominas != null && parametroNominas.size() > 0) {
			ParametroNominaDto dto = null;
			for (ParametroNomina dao : parametroNominas) {
				dto = new ParametroNominaDto();
				try {
					BeanUtils.copyProperties(dto, dao);
					dto.setCo_proceso_nom(dao.getCo_proceso_nom());
					dto.setCo_parametro_nom(dao.getCo_parametro_nom());
					dto.setVa_parametro_nom(dao.getVa_parametro_nom());
					dto.setTx_parametro_nom(dao.getTx_parametro_nom());
					dto.setChecked(true);
					dtos.add(dto);

				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return dtos;
	}

	public boolean save(BenefMedicamentoDto benefMedicamentoDto, List<ParametroNominaDto> lista) {
		boolean salida = false;
		if (benefMedicamentoDto != null) {
			BenefMedicamento benefMedicamento = new BenefMedicamento();
			benefMedicamento.setNuCedula(benefMedicamentoDto.getNuCedula());
			benefMedicamento.setNuCedulaFamiliar(benefMedicamentoDto.getNuCedulaFamiliar());
			benefMedicamento.setTxEmail(benefMedicamentoDto.getTxEmail());
			benefMedicamento = benefMedicamentoLocal.save(benefMedicamento);
			List<ParametroNomina> listadaos = new ArrayList<ParametroNomina>();
			ParametroNomina parametroNomina = null;
			for (ParametroNominaDto parametroNominaDto : lista) {
				parametroNomina = new ParametroNomina();
				parametroNomina.setCo_parametro_nom(parametroNominaDto.getCo_parametro_nom());
				parametroNomina.setCo_proceso_nom(parametroNominaDto.getCo_proceso_nom());
				parametroNomina.setStRecibirMedicam(parametroNominaDto.getStRecibirMedicam());
				listadaos.add(parametroNomina);
			}
			faCatalogoMedicamentoLocal.save(listadaos, benefMedicamento);

		}

		return salida;
	}

	public UserEncuestaDto findUserEncuesta(String cedula) {
		UserEncuestaDto userEncuestaDto = null;
		UserEncuesta userEncuesta = faCatalogoMedicamentoLocal.findUserEncuesta(cedula);
		if (userEncuesta != null) {
			userEncuestaDto = new UserEncuestaDto();
			userEncuestaDto.setNombre(userEncuesta.getNombre());
			userEncuestaDto.setTipoEmp(userEncuesta.getTipoEmp());
			userEncuestaDto.setCedula(userEncuesta.getCedula());
			userEncuestaDto.setCodigoUbicacion(userEncuesta.getCodigoUbicacion());
			/*** Buscamos por cada beneficiario ***/
			BenefMedicamentoDto benefMedicamentoDto = null;
			userEncuestaDto.setNuExtension_1(userEncuesta.getNuExtension_1());
			if (null != userEncuesta.getBenefMedicamentos()){
				List<BenefMedicamentoDto> benefMedicamentosDtos = new ArrayList<BenefMedicamentoDto>();
				for (BenefMedicamento BenefMedicamento : userEncuesta.getBenefMedicamentos()) {
					benefMedicamentoDto = new BenefMedicamentoDto();
					benefMedicamentoDto.setNuCedula(BenefMedicamento.getNuCedula());
					benefMedicamentoDto.setNuCedulaFamiliar(BenefMedicamento.getNuCedulaFamiliar());
					benefMedicamentoDto.setNuIdBeneficiario(BenefMedicamento.getNuIdBeneficiario());
					benefMedicamentoDto.setTxEmail(BenefMedicamento.getTxEmail());
					benefMedicamentoDto.setFecha(BenefMedicamento.getFecha());
					benefMedicamentoDto.setNombreFamiliar(BenefMedicamento.getNombreFamiliar());
					benefMedicamentoDto.setParentesco(BenefMedicamento.getParentesco());
					/****Por cada encuesta medicamentos, buscamos los beneficiarios***/
					/**Medicinas*/
	 				 List<ParametroNominaDto> dtos = getMedicinas(BenefMedicamento.getMedicinas());
	 				/**End Medicinas*/
					benefMedicamentoDto.setParametroNominaDtos(dtos);
					benefMedicamentosDtos.add(benefMedicamentoDto);
				}
				userEncuestaDto.setBenefMedicamentos(benefMedicamentosDtos);	
			}
		
			
			
			
			
			
			
		}
		return userEncuestaDto;
	}

	public List<ParametroNominaDto> buscarMedicamentosFamiliar(String cedula, String cedulaFamiliar) {
		UserEncuestaDto userEncuestaDto = buscarCedulaMedicamentos(cedula, cedulaFamiliar);
		List<ParametroNominaDto> lists = new ArrayList<ParametroNominaDto>();
		if (userEncuestaDto != null && userEncuestaDto.getBenefMedicamentos() != null
				&& userEncuestaDto.getBenefMedicamentos().size() > 0) {
			List<BenefMedicamentoDto> benefMedicamentos = userEncuestaDto.getBenefMedicamentos();
			if (null != benefMedicamentos && benefMedicamentos.size() > 0) {
				lists = benefMedicamentos.get(0).getParametroNominaDtos();
			}
		}
		return lists;
	}
	
	
	public List<UserEncuestaDto> familiares(String cedula) {
		
		List<UserEncuestaDto> familiaresDto = new ArrayList<UserEncuestaDto>();
		List<UserEncuesta> familiares = faCatalogoMedicamentoLocal.familiares(cedula);
		Map<String,UserEncuestaDto> unicoNombreFechaNac = new HashMap<String,UserEncuestaDto>();
		UserEncuestaDto userDto = null;
		for (UserEncuesta dao:familiares){
			userDto = new UserEncuestaDto();
			userDto.setCedula(dao.getCedula());
			userDto.setNombre(dao.getNombre());
			userDto.setFechaNacimiento(dao.getFechaNacimiento());
			/**Si es el mismo nombre y fecha de nacimiento, buscamos la cedula con mayor longitud y la dejamos*/
			if (!unicoNombreFechaNac.containsKey(userDto.getNombre()+userDto.getFechaNacimiento())){
				unicoNombreFechaNac.put(userDto.getNombre()+userDto.getFechaNacimiento(), userDto);
			}else{
				/** buscamos la cedula con mayor longitud y la dejamos*/
				UserEncuestaDto userDtoAux = unicoNombreFechaNac.get(userDto.getNombre()+userDto.getFechaNacimiento()); 
				if (userDtoAux.getCedula().length()>userDto.getCedula().length()){
					userDto.setCedula(userDtoAux.getCedula());
				}else{
					/**Actualizamos la cedula en el map porsi viene mas adelante una cedula de mayor longitud*/
					unicoNombreFechaNac.put(userDto.getNombre()+userDto.getFechaNacimiento(), userDto);
				}
			}
			familiaresDto.add(userDto);
		}
 
		return familiaresDto;
	}
	
	

	public UserEncuestaDto buscarCedulaMedicamentos(String cedula, String cedulaFamiliar) {
		
		return fromBeneficiarioMedicinas(cedula,cedulaFamiliar);
	}

	/* 
	 * Buscamos todas las cedulas por fecha
	 */
	public List<UserEncuestaDto> buscarCedulaMedicamentos(Date feDesde, Date feHasta) {

		List<UserEncuestaDto> lists = new ArrayList<UserEncuestaDto>();

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String feDesdeStr = sdf.format(feDesde);
		String feHastaStr = sdf.format(feHasta);

		/****Buscamos las cedulas que esten con ese rango***/
		List<String> cedulas = faCatalogoMedicamentoLocal.buscarCedulaMedicamentos(feDesdeStr, feHastaStr);
		UserEncuestaDto userEncuestaDto = null;
		for (String ced : cedulas) {
			/**Por cadacedula buscamos la encuesta medicamentos*/
			userEncuestaDto = buscarCedulaMedicamentos(ced);
			lists.add(userEncuestaDto);
		}

		return lists;
	}
	
	public List<String>  buscarCedulaByFechas(Date feDesde, Date feHasta) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String feDesdeStr = sdf.format(feDesde);
		String feHastaStr = sdf.format(feHasta);

		/****Buscamos las cedulas que esten con ese rango***/
		List<String> cedulas = faCatalogoMedicamentoLocal.buscarCedulaMedicamentos(feDesdeStr, feHastaStr);
	 

		return cedulas;
	}
	
	public List<UserEncuestaDto> buscarCedulaMedicamentosQuery (String feDesdeStr, String fechaHastaStr){
		List<UserEncuestaDto> userEncuestaDtos = new ArrayList<UserEncuestaDto>();
		List<BenefMedicamentoDto> benefMedicamentosDtos = null;
		List<UserEncuesta> lists = faCatalogoMedicamentoLocal.buscarCedulaMedicamentosQuery(feDesdeStr, fechaHastaStr);
		UserEncuestaDto userEncuestaDto = null;
		for (UserEncuesta userEncuesta:lists){
			userEncuestaDto = new UserEncuestaDto();
			userEncuestaDto.setNombre(userEncuesta.getNombre());
			userEncuestaDto.setTipoEmp(userEncuesta.getTipoEmp());
			userEncuestaDto.setCedula(userEncuesta.getCedula());
			userEncuestaDto.setCodigoUbicacion(userEncuesta.getCodigoUbicacion());
			/*** Buscamos por cada beneficiario ***/
			BenefMedicamentoDto benefMedicamentoDto = null;
			userEncuestaDto.setNuExtension_1(userEncuesta.getNuExtension_1());
			 benefMedicamentosDtos = new ArrayList<BenefMedicamentoDto>();
			for (BenefMedicamento BenefMedicamento : userEncuesta.getBenefMedicamentos()) {
				benefMedicamentoDto = new BenefMedicamentoDto();
				benefMedicamentoDto.setNuCedula(BenefMedicamento.getNuCedula());
				benefMedicamentoDto.setNuCedulaFamiliar(BenefMedicamento.getNuCedulaFamiliar());
				benefMedicamentoDto.setNuIdBeneficiario(BenefMedicamento.getNuIdBeneficiario());
				benefMedicamentoDto.setTxEmail(BenefMedicamento.getTxEmail());
				benefMedicamentoDto.setFecha(BenefMedicamento.getFecha());
				benefMedicamentoDto.setNombreFamiliar(BenefMedicamento.getNombreFamiliar());
				benefMedicamentoDto.setParentesco(BenefMedicamento.getParentesco());
				/****Por cada encuesta medicamentos, buscamos los beneficiarios***/
				/**Medicinas*/
 				 List<ParametroNominaDto> dtos = getMedicinas(BenefMedicamento.getMedicinas());
 				/**End Medicinas*/
				benefMedicamentoDto.setParametroNominaDtos(dtos);
				benefMedicamentosDtos.add(benefMedicamentoDto);
			}
			userEncuestaDto.setBenefMedicamentos(benefMedicamentosDtos);
			userEncuestaDtos.add(userEncuestaDto);
		}
		
	      return userEncuestaDtos;
	}
	
	 

	public UserEncuestaDto buscarCedulaMedicamentos(String cedula) {
	
		
		return  fromBeneficiarioMedicinas(cedula,null);
	}
	
	private UserEncuestaDto fromBeneficiarioMedicinas(String cedula,String cedulaFamiliar){
		UserEncuestaDto userEncuestaDto = new UserEncuestaDto();
		List<BenefMedicamentoDto> benefMedicamentosDtos = new ArrayList<BenefMedicamentoDto>();
		
		UserEncuesta userEncuesta = faCatalogoMedicamentoLocal.findUserEncuesta(cedula);
		if (userEncuesta != null) {
			userEncuestaDto.setNombre(userEncuesta.getNombre());
			userEncuestaDto.setTipoEmp(userEncuesta.getTipoEmp());
			userEncuestaDto.setCedula(userEncuesta.getCedula());
			userEncuestaDto.setCodigoUbicacion(userEncuesta.getCodigoUbicacion());
			userEncuestaDto.setNuExtension_1(userEncuesta.getNuExtension_1());
		}

		List<BenefMedicamento> lstBenefMedicamentos = null;
		if (cedulaFamiliar!=null && cedulaFamiliar.length()>0){
			lstBenefMedicamentos = benefMedicamentoLocal.findAll(cedula, cedulaFamiliar);	
		}else{
			lstBenefMedicamentos = benefMedicamentoLocal.findAll(cedula);	
		}
		
		
		if (lstBenefMedicamentos != null) {
			/*** Buscamos por cada beneficiario ***/
			BenefMedicamentoDto benefMedicamentoDto = null;
			/****Por cada encuesta medicamentos, buscamos los beneficiarios***/

			for (BenefMedicamento BenefMedicamento : lstBenefMedicamentos) {
				benefMedicamentoDto = new BenefMedicamentoDto();
				benefMedicamentoDto.setNuCedula(BenefMedicamento.getNuCedula());
				benefMedicamentoDto.setNuCedulaFamiliar(BenefMedicamento.getNuCedulaFamiliar());
				benefMedicamentoDto.setNuIdBeneficiario(BenefMedicamento.getNuIdBeneficiario());
				benefMedicamentoDto.setTxEmail(BenefMedicamento.getTxEmail());
				benefMedicamentoDto.setFecha(BenefMedicamento.getFecha());
				benefMedicamentoDto.setNombreFamiliar(BenefMedicamento.getNombreFamiliar());
				benefMedicamentoDto.setParentesco(BenefMedicamento.getParentesco());
 				 List<ParametroNominaDto> dtos = getMedicinas(BenefMedicamento.getNuIdBeneficiario());
				benefMedicamentoDto.setParametroNominaDtos(dtos);
				benefMedicamentosDtos.add(benefMedicamentoDto);
			}
			

		}
		if (null != benefMedicamentosDtos && benefMedicamentosDtos.size() > 0) {
			userEncuestaDto.setBenefMedicamentos(benefMedicamentosDtos);
		}
		
		return userEncuestaDto;
		
	}
	
	
	private List<ParametroNominaDto> getMedicinas(List<ParametroNomina> parametroNominas){
		/****Por cada beneficiarios buscamos los medicamentos***/
		List<ParametroNominaDto> dtos = new ArrayList<ParametroNominaDto>();
		if (parametroNominas != null && parametroNominas.size() > 0) {
			ParametroNominaDto dto = null;
			/*** cada beneficiario tiene sus medicamentos ***/
			for (ParametroNomina dao : parametroNominas) {
				dto = new ParametroNominaDto();
				try {
					BeanUtils.copyProperties(dto, dao);
					dto.setCo_proceso_nom(dao.getCo_proceso_nom());
					dto.setCo_parametro_nom(dao.getCo_parametro_nom());
					dto.setVa_parametro_nom(dao.getVa_parametro_nom());
					dto.setTx_parametro_nom(dao.getTx_parametro_nom());
					dto.setCantidadEntregada(dao.getCantidadEntregada());
					dto.setCantSolicitada(dao.getCantSolicitada());
					dto.setPrecioUnitario(dao.getPrecioUnitario());
					dto.setFeRegPagoStr(dao.getFeRegPagoStr());

					dtos.add(dto);

				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return dtos;
	}
	
	private List<ParametroNominaDto> getMedicinas(long nuIBenef){
		List<ParametroNomina> parametroNominas = faCatalogoMedicamentoLocal
				.buscarCedulaMedicamentosFamiliar(nuIBenef + "");
		/****Por cada beneficiarios buscamos los medicamentos***/
		List<ParametroNominaDto> dtos = new ArrayList<ParametroNominaDto>();
		if (parametroNominas != null && parametroNominas.size() > 0) {
			ParametroNominaDto dto = null;
			/*** cada beneficiario tiene sus medicamentos ***/
			for (ParametroNomina dao : parametroNominas) {
				dto = new ParametroNominaDto();
				try {
					BeanUtils.copyProperties(dto, dao);
					dto.setCo_proceso_nom(dao.getCo_proceso_nom());
					dto.setCo_parametro_nom(dao.getCo_parametro_nom());
					dto.setVa_parametro_nom(dao.getVa_parametro_nom());
					dto.setTx_parametro_nom(dao.getTx_parametro_nom());
					dto.setCantidadEntregada(dao.getCantidadEntregada());
					dto.setCantSolicitada(dao.getCantSolicitada());
					dto.setPrecioUnitario(dao.getPrecioUnitario());
					dto.setFeRegPagoStr(dao.getFeRegPagoStr());

					dtos.add(dto);

				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return dtos;
	}

	public ResultBoleanDto personaYaEncuestada(String cedula, String nuCedulaFamiliar) {
		ResultBoleanDto resultBoleanDto = new ResultBoleanDto();
		ResultBolean resultBolean = faCatalogoMedicamentoLocal.personaYaEncuestada(cedula, nuCedulaFamiliar);
		if (null != resultBolean) {
			resultBoleanDto.setExiste(resultBolean.isExiste());
		}
		return resultBoleanDto;
	}

	public ChartDto graficar(String interesdos, String feDesdeStr, String feHastaStr) {
		ChartDto dto = new ChartDto();
		Map<String, Integer> cuantosPorCadaUno = new HashMap<String, Integer>();

		List<ParametroNomina> parametroNominas =  new ArrayList<ParametroNomina>();
		
		
		List<UserEncuesta> lists = faCatalogoMedicamentoLocal.buscarCedulaMedicamentosQuery(feDesdeStr, feHastaStr);
		if (null != lists){
			for (UserEncuesta users:lists) {
				for (BenefMedicamento benefMedicamento:users.getBenefMedicamentos()){
					parametroNominas.addAll(benefMedicamento.getMedicinas());
				}
			}
		}
		
//		List<ParametroNomina> parametroNominas = faCatalogoMedicamentoLocal.encuesta(interesdos, feDesdeStr,
//				feHastaStr);
		for (ParametroNomina parametroNomina : parametroNominas) {
			if (!cuantosPorCadaUno.containsKey(parametroNomina.getTx_parametro_nom())) {
				cuantosPorCadaUno.put(parametroNomina.getTx_parametro_nom(), 0);
			}
			int cont = cuantosPorCadaUno.get(parametroNomina.getTx_parametro_nom());
			cuantosPorCadaUno.put(parametroNomina.getTx_parametro_nom(), ++cont);

		}

		DataChart data = new DataChart();
		List<String> labels = new ArrayList<String>();
		DatasetsChart datasetsChart = new DatasetsChart();
		List<DatasetsChart> datasetsCharts = new ArrayList<DatasetsChart>();
		datasetsChart.setLabel(interesdos);
		List<Integer> data1 = new ArrayList<Integer>();
		List<String> backgroundColor = new ArrayList<String>();
		StringBuilder color = null;
		List<String> borderColor = new ArrayList<String>();
		// Imprimimos el Map con un Iterador
		Iterator it = cuantosPorCadaUno.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			labels.add(key);
			data1.add(cuantosPorCadaUno.get(key));

			// to get rainbow, pastel colors
			Random random = new Random();
			final float hue = random.nextFloat();
			final float saturation = 0.9f;// 1.0 for brilliant, 0.0 for dull
			final float luminance = 1.0f; // 1.0 for brighter, 0.0 for black

			Random rnd = new Random();
			;

			color = new StringBuilder("");
			color.append("rgba(").append(255).append(",").append(rnd.nextInt(256)).append(",").append(rnd.nextInt(256))
					.append(",").append(rnd.nextInt(256)).append(")");
			backgroundColor.add(color.toString());

			color = new StringBuilder("");
			color.append("rgba(").append(255).append(",").append(rnd.nextInt(256)).append(",").append(rnd.nextInt(256))
					.append(",").append(rnd.nextInt(256)).append(")");

			borderColor.add(color.toString());

		}
		data.setLabels(labels);
		datasetsChart.setData(data1);
		datasetsChart.setBorderWidth(1);
		datasetsChart.setBackgroundColor(backgroundColor);
		datasetsChart.setBorderColor(borderColor);
		datasetsCharts.add(datasetsChart);
		data.setDatasets(datasetsCharts);
		OptionsChart options = new OptionsChart();
		ScalesChart scales = new ScalesChart();
		List<YAxesChart> yAxesChartLst = new ArrayList<YAxesChart>();
		YAxesChart yAxesCharts = new YAxesChart();
		TicksChart ticks = new TicksChart();
		ticks.setBeginAtZero(true);
		yAxesCharts.setTicks(ticks);
		yAxesChartLst.add(yAxesCharts);
		scales.setyAxes(yAxesChartLst);
		options.setScales(scales);
		dto.setType("bar");
		dto.setData(data);
		dto.setOptions(options);

		return dto;
	}

}
