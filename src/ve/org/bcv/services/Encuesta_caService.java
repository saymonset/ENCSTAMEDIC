package ve.org.bcv.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ve.org.bcv.dto.BenefMedicamentoDto;
import ve.org.bcv.dto.ChartDto;
import ve.org.bcv.dto.ParametroNominaDto;
import ve.org.bcv.dto.ResultBoleanDto;
import ve.org.bcv.dto.UserEncuestaDto;

@Local
public interface Encuesta_caService {
	List<ParametroNominaDto> findByCoProcesoNom(String co_proceso_nom);
	boolean save(BenefMedicamentoDto benefMedicamentoDto,List<ParametroNominaDto> lista);
	UserEncuestaDto findUserEncuesta(String cedula);
	ResultBoleanDto personaYaEncuestada( String cedula,String nuCedulaFamiliar);
	ChartDto graficar(String interesdos, String feDesdeStr, String feHastaStr);
	List<ParametroNominaDto> findByCoProcesoNomNo(String co_proceso_nom,String tx_parametro_nom) ;
	List<ParametroNominaDto> buscarMedicamento(String filtro) ;
	UserEncuestaDto buscarCedulaMedicamentos(String cedula) ;
	UserEncuestaDto buscarCedulaMedicamentos(String cedula,String cedulaFamiliar) ;
	 List<ParametroNominaDto>  buscarMedicamentosFamiliar(String cedula,String cedulaFamiliar) ;
	 List<UserEncuestaDto> buscarCedulaMedicamentos(Date feDesde,Date feHasta);
	List<String>  buscarCedulaByFechas(Date feDesde, Date feHasta) ;
	 List<UserEncuestaDto> buscarCedulaMedicamentosQuery (String feDesdeStr, String fechaHastaStr);
	 List<UserEncuestaDto> familiares(String cedula) ;
	
}
