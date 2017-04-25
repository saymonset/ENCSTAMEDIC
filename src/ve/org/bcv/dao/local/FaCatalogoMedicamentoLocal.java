package ve.org.bcv.dao.local;

import java.util.List;

import ve.org.bcv.dao.BenefMedicamento;
import ve.org.bcv.dao.ParametroNomina;
import ve.org.bcv.dao.ResultBolean;
import ve.org.bcv.dao.UserEncuesta;

public interface FaCatalogoMedicamentoLocal {
	List<ParametroNomina> findByCoProcesoNom(String co_proceso_nom);
	boolean  save(List<ParametroNomina> lista,BenefMedicamento benefMedicamento);
	UserEncuesta findUserEncuesta(String cedula);
	ResultBolean personaYaEncuestada( String cedula,String nuCedulaFamiliar);
	List<ParametroNomina> encuesta (String interesdos,String feDesdeStr, String fechaHastaStr);
	List<ParametroNomina> findByCoProcesoNomNo(String co_proceso_nom,String tx_parametro_nom) ;
	List<ParametroNomina> buscarMedicamento(String filtro) ;
	List<ParametroNomina> buscarCedulaMedicamentosFamiliar (String NU_ID_BENEFICIARIO);
	List<String> buscarCedulaMedicamentos (String feDesdeStr, String fechaHastaStr);
	List<UserEncuesta> buscarCedulaMedicamentosQuery (String feDesdeStr, String fechaHastaStr);
	List<UserEncuesta> familiares (String cedula);
}
