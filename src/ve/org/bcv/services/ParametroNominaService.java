package ve.org.bcv.services;

import java.util.List;

import javax.ejb.Local;

import ve.org.bcv.dto.BenefMedicamentoDto;
import ve.org.bcv.dto.ChartDto;
import ve.org.bcv.dto.ParametroNominaDto;
import ve.org.bcv.dto.ResultBoleanDto;
import ve.org.bcv.dto.UserEncuestaDto;
@Local
public interface ParametroNominaService {
	List<ParametroNominaDto> findByCoProcesoNom(String co_proceso_nom);
	boolean save(BenefMedicamentoDto benefMedicamentoDto,List<ParametroNominaDto> lista);
	UserEncuestaDto findUserEncuesta(String cedula);
	ResultBoleanDto personaYaEncuestada( String cedula,String nuCedulaFamiliar);
	ChartDto graficar(String interesdos);
	List<ParametroNominaDto> findByCoProcesoNomNo(String co_proceso_nom,String tx_parametro_nom) ;
	
}
