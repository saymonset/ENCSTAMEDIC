package ve.org.bcv.servicesImpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import ve.org.bcv.dao.local.EncuestaMedicamenLocal;
import ve.org.bcv.dto.EncuestaMedicamenDto;
import ve.org.bcv.services.EncuestaMedicamenService;
@EncuestaMedicamenServiceType1
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class EncuestaMedicamenServiceImpl implements EncuestaMedicamenService {
	@Inject
	private EncuestaMedicamenLocal EncuestaMedicamenLocal;

	public List<EncuestaMedicamenDto> findAll(String co_proceso_nom) {
		// TODO Auto-generated method stub
		return null;
	}

	public void save(EncuestaMedicamenDto dto) {
		// TODO Auto-generated method stub

	}

}
