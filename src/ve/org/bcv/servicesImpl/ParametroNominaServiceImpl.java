package ve.org.bcv.servicesImpl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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
import ve.org.bcv.dao.local.ParametroNominaLocal;
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
import ve.org.bcv.dto.YAxesChart;
import ve.org.bcv.services.ParametroNominaService;

@ParametroNominaServiceType1
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ParametroNominaServiceImpl implements ParametroNominaService {

	@Inject
	private ParametroNominaLocal parametroNominaLocal;
	@Inject
	private BenefMedicamentoLocal benefMedicamentoLocal;
	
	public List<ParametroNominaDto> findByCoProcesoNom(String co_proceso_nom) {
		ParametroNomina parametroNomina = new ParametroNomina();
		List<ParametroNomina> parametroNominas = parametroNominaLocal.findByCoProcesoNom(parametroNomina.getCo_proceso_nom());
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

	public List<ParametroNominaDto> findByCoProcesoNomNo(String co_proceso_nom, String tx_parametro_nom) {
		ParametroNomina parametroNomina = new ParametroNomina();
		List<ParametroNomina> parametroNominas = parametroNominaLocal.findByCoProcesoNomNo(parametroNomina.getCo_proceso_nom(),tx_parametro_nom);
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
		boolean salida=false;
		if (benefMedicamentoDto!=null){
			BenefMedicamento benefMedicamento = new BenefMedicamento();
			benefMedicamento.setNuCedula(benefMedicamentoDto.getNuCedula());
			benefMedicamento.setNuCedulaFamiliar(benefMedicamentoDto.getNuCedulaFamiliar());
			benefMedicamento.setTxEmail(benefMedicamentoDto.getTxEmail());
			benefMedicamento = benefMedicamentoLocal.save(benefMedicamento);
			List<ParametroNomina> listadaos = new ArrayList<ParametroNomina>();
			ParametroNomina parametroNomina = null;
			for (ParametroNominaDto parametroNominaDto:lista){
				parametroNomina = new ParametroNomina();
				parametroNomina.setCo_parametro_nom(parametroNominaDto.getCo_parametro_nom());
				parametroNomina.setCo_proceso_nom(parametroNominaDto.getCo_proceso_nom());
				parametroNomina.setStRecibirMedicam(parametroNominaDto.getStRecibirMedicam());
				listadaos.add(parametroNomina);
			}
			parametroNominaLocal.save(listadaos);
			

		}
		
		return salida;
	}

	public UserEncuestaDto findUserEncuesta(String cedula) {
		UserEncuestaDto userEncuestaDto = null;
		UserEncuesta userEncuesta= parametroNominaLocal.findUserEncuesta(cedula);
		if (userEncuesta!=null){
			userEncuestaDto = new UserEncuestaDto ();
			userEncuestaDto.setNombre(userEncuesta.getNombre());
			userEncuestaDto.setTipoEmp(userEncuesta.getTipoEmp());
		}
		return userEncuestaDto ;
	}

	public ResultBoleanDto personaYaEncuestada(String cedula, String nuCedulaFamiliar) {
		ResultBoleanDto resultBoleanDto= new ResultBoleanDto();
		ResultBolean resultBolean= parametroNominaLocal.personaYaEncuestada(cedula, nuCedulaFamiliar);
		if (null!=resultBolean){
			resultBoleanDto.setExiste(resultBolean.isExiste());
		}
		return resultBoleanDto;
	}

	 
	
	public ChartDto graficar(String interesdos) {
		ChartDto dto = new ChartDto();
		Map<String, Integer> cuantosPorCadaUno = new HashMap<String, Integer>();
		List<ParametroNomina> parametroNominas = parametroNominaLocal.encuesta(interesdos);
		for (ParametroNomina parametroNomina:parametroNominas){
			if (!cuantosPorCadaUno.containsKey(parametroNomina.getTx_parametro_nom())){
				cuantosPorCadaUno.put(parametroNomina.getTx_parametro_nom(), 0);
			}
			int cont=cuantosPorCadaUno.get(parametroNomina.getTx_parametro_nom());
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
			color.append("rgba(").append(255).append(",").append(rnd.nextInt(256)).append(",").append(rnd.nextInt(256)).append(",").append(rnd.nextInt(256)).append(")");
			backgroundColor.add(color.toString());

			color = new StringBuilder("");
			color.append("rgba(").append(255).append(",").append(rnd.nextInt(256)).append(",").append(rnd.nextInt(256)).append(",").append(rnd.nextInt(256)).append(")");

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
