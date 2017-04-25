package ve.org.bcv.reporte;

import java.io.File;
import java.util.Collection;
import java.util.Map;

import javax.ejb.Local;
@Local
public interface ReporteService {
	File reporte(String cedula,String cedulaFamiliar,Collection collection,Map parameters) throws Exception ;
	File reporte(Collection collection,Map parameters) throws Exception ;
}
