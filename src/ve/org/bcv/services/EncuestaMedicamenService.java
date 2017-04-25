package ve.org.bcv.services;

import java.util.List;

import javax.ejb.Local;

import ve.org.bcv.dto.EncuestaMedicamenDto;

@Local
public interface EncuestaMedicamenService {
	List<EncuestaMedicamenDto> findAll(String co_proceso_nom);
	void save(EncuestaMedicamenDto dto);
}
