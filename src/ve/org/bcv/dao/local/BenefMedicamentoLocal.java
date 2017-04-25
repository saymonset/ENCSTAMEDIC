package ve.org.bcv.dao.local;

import java.util.List;

import ve.org.bcv.dao.BenefMedicamento;

public interface BenefMedicamentoLocal {
	List<BenefMedicamento> findAll();
	BenefMedicamento save(BenefMedicamento dao);
	List<BenefMedicamento> findAll(String cedula) ;
	List<BenefMedicamento> findAll(String cedula,String cedulaFamiliar);
	List<BenefMedicamento> findBenefMedicamentoByCedula(String cedula);
}
