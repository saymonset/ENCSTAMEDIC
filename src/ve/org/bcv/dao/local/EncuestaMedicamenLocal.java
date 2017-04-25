package ve.org.bcv.dao.local;

import java.util.List;

import ve.org.bcv.dao.EncuestaMedicamen;

public interface EncuestaMedicamenLocal {
	List<EncuestaMedicamen> findAll(String co_proceso_nom);
	void save(EncuestaMedicamen dao);
}
