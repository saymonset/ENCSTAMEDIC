package ve.org.bcv.services;

import java.util.List;

import javax.ejb.Local;

import ve.org.bcv.dto.BenefMedicamentoDto;

@Local
public interface BenefMedicamentoService {
	List<BenefMedicamentoDto> findAll();
	void save(BenefMedicamentoDto dto);
}
