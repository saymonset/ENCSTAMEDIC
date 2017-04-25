package ve.org.bcv.dao.local;

import java.util.List;

import ve.org.bcv.dao.ParametroNomina;
import ve.org.bcv.dao.ResultBolean;
import ve.org.bcv.dao.UserEncuesta;

public interface ParametroNominaLocal {
	List<ParametroNomina> findByCoProcesoNom(String co_proceso_nom);
	boolean  save(List<ParametroNomina> lista);
	UserEncuesta findUserEncuesta(String cedula);
	ResultBolean personaYaEncuestada( String cedula,String nuCedulaFamiliar);
	List<ParametroNomina> encuesta (String interesdos);
	List<ParametroNomina> findByCoProcesoNomNo(String co_proceso_nom,String tx_parametro_nom) ;
}
