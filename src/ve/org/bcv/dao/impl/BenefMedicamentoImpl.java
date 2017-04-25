package ve.org.bcv.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import ve.org.bcv.dao.BenefMedicamento;
import ve.org.bcv.dao.local.BenefMedicamentoLocal;
import ve.org.bcv.util.ManejadorDB;

public class BenefMedicamentoImpl extends BaseDaoImpl<BenefMedicamento, Long> implements BenefMedicamentoLocal {

	@Inject
	ManejadorDB manejadorDB;

	public List<BenefMedicamento> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<BenefMedicamento> findAll(String cedula, String cedulaFamiliar) {
		List<BenefMedicamento> lists = new ArrayList<BenefMedicamento>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = manejadorDB.coneccionPool();
			StringBuilder sql = new StringBuilder("");

			sql.append(" select B.NU_ID_BENEFICIARIO,B.TX_EMAIL,B.NU_CEDULA,fam.CEDULA_FAMILIAR ");
			sql.append(
					" , INITCAP(fam.APELLIDO1||' '||fam.APELLIDO2 ||' '|| fam.NOMBRE1||' '||fam.NOMBRE2 ) AS nombreFamiliar,fam.parentesco from SALUD.BENEF_MEDICAMENTO b ");
			sql.append("  INNER JOIN PERSONAL.TODOS_EMPLEADOS PERS    ON PERS.CEDULA =B.NU_CEDULA ");
			sql.append("   INNER JOIN PERSONAL.FAMILIARES    fam on FAM.NU_CEDULA  = PERS.CEDULA ");
			sql.append("     and B.NU_CEDULA_FAMILIAR = FAM.CEDULA_FAMILIAR ");
			sql.append("  where B.NU_CEDULA=").append(cedula);
			if (null != cedulaFamiliar && cedulaFamiliar.length() > 0) {
				sql.append("  and FAM.CEDULA_FAMILIAR=").append(cedulaFamiliar);
			}

			sql.append("  order by B.NU_CEDULA_FAMILIAR  ");

			pstmt = con.prepareStatement(sql.toString());
			ResultSet result = pstmt.executeQuery();
			/** if not have familiar in the familiar cedula */
			if (result == null || result.next()==false) {
				sql = new StringBuilder("");
				sql.append(
						"select B.NU_ID_BENEFICIARIO,B.TX_EMAIL,B.NU_CEDULA,B.NU_CEDULA_FAMILIAR from SALUD.BENEF_MEDICAMENTO b ");
				sql.append(" where B.NU_CEDULA=").append(cedula);
				if (null != cedulaFamiliar && cedulaFamiliar.length() > 0) {
					sql.append(" and b.NU_CEDULA_FAMILIAR=").append(cedulaFamiliar);
				}
				sql.append(" order by B.NU_CEDULA_FAMILIAR");
				pstmt = con.prepareStatement(sql.toString());
				result = pstmt.executeQuery();
				if (result != null) {
					BenefMedicamento benefMedicamento = null;
					Map<Long, String> benefUnico = new HashMap<Long, String>();
					while (result.next()) {
						benefMedicamento = new BenefMedicamento();
						benefMedicamento.setNuIdBeneficiario(result.getInt("NU_ID_BENEFICIARIO"));
						if (!benefUnico.containsKey(benefMedicamento.getNuIdBeneficiario())) {
							benefUnico.put(benefMedicamento.getNuIdBeneficiario(),
									benefMedicamento.getNuIdBeneficiario() + "");
							benefMedicamento.setTxEmail(result.getString("TX_EMAIL"));
							benefMedicamento.setNuCedula(result.getInt("NU_CEDULA"));
							benefMedicamento.setNuCedulaFamiliar(result.getInt("NU_CEDULA_FAMILIAR"));
							lists.add(benefMedicamento);

						}
					}
				}
			} else {
				/** if has familiar cedula */
				BenefMedicamento benefMedicamento = null;
				Map<Long, String> benefUnico = new HashMap<Long, String>();
				do {

					benefMedicamento = new BenefMedicamento();
					benefMedicamento.setNuIdBeneficiario(result.getInt("NU_ID_BENEFICIARIO"));
					if (!benefUnico.containsKey(benefMedicamento.getNuIdBeneficiario())) {
						benefUnico.put(benefMedicamento.getNuIdBeneficiario(),
								benefMedicamento.getNuIdBeneficiario() + "");
						benefMedicamento.setTxEmail(result.getString("TX_EMAIL"));
						benefMedicamento.setNuCedula(result.getInt("NU_CEDULA"));
						benefMedicamento.setNuCedulaFamiliar(result.getInt("CEDULA_FAMILIAR"));
						benefMedicamento.setNombreFamiliar(result.getString("nombreFamiliar"));
						benefMedicamento.setParentesco(result.getString("parentesco"));
						lists.add(benefMedicamento);

					}
				}while (result.next());
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return lists;
	}

	public List<BenefMedicamento> findBenefMedicamentoByCedula(String cedula) {
		List<BenefMedicamento> lists = new ArrayList<BenefMedicamento>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = manejadorDB.coneccionPool();
			StringBuilder sql = new StringBuilder(
					"select NU_CEDULA,NU_CEDULA_FAMILIAR from   SALUD.BENEF_MEDICAMENTO b where B.NU_CEDULA = ");
			sql.append(cedula);

			pstmt = con.prepareStatement(sql.toString());
			ResultSet result = pstmt.executeQuery();
			/** if not have familiar in the familiar cedula */

			/** if has familiar cedula */
			BenefMedicamento benefMedicamento = null;
			while (result.next()) {
				benefMedicamento = new BenefMedicamento();
				benefMedicamento.setNuCedula(result.getInt("NU_CEDULA"));
				benefMedicamento.setNuCedulaFamiliar(result.getInt("NU_CEDULA_FAMILIAR"));
				lists.add(benefMedicamento);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return lists;
	}

	public List<BenefMedicamento> findAll(String cedula) {

		/** Buscamos cedula y cedula familiar primero */
		List<BenefMedicamento> listsCedulAndCedulaFamiliar = findBenefMedicamentoByCedula(cedula);
		List<BenefMedicamento> all = new ArrayList<BenefMedicamento>();
		/** Buscamos la info completa con cedula y cedula familiar */
		for (BenefMedicamento bm : listsCedulAndCedulaFamiliar) {
			all.addAll(findAll(bm.getNuCedula() + "", bm.getNuCedulaFamiliar() + ""));
		}
		return all;
	}

	public BenefMedicamento save(BenefMedicamento dao) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = manejadorDB.coneccionPool();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT nu_id_beneficiario as valor from salud.BENEF_MEDICAMENTO b where b.NU_CEDULA = ")
					.append(dao.getNuCedula());
			sql.append(" AND b.NU_CEDULA_FAMILIAR = ").append(dao.getNuCedulaFamiliar());
			pstmt = con.prepareStatement(sql.toString());
			ResultSet result = pstmt.executeQuery();
			/** INSERTAMOS **/
			if (result == null || !result.next()) {
				pstmt = con.prepareStatement("SELECT salud.SEQ_NU_ID_BENEFICIARIO.NEXTVAL AS valor from dual");
				result = pstmt.executeQuery();
				if (result != null) {
					while (result.next()) {
						dao.setNuIdBeneficiario(result.getInt("valor"));
					}
				}

				sql = new StringBuilder();
				sql.append(
						"INSERT INTO SALUD.BENEF_MEDICAMENTO (NU_ID_BENEFICIARIO,TX_EMAIL,NU_CEDULA,NU_CEDULA_FAMILIAR) ");
				sql.append(" VALUES (").append(dao.getNuIdBeneficiario()).append(",'").append(dao.getTxEmail())
						.append("',").append(dao.getNuCedula()).append(",").append(dao.getNuCedulaFamiliar())
						.append(")");
				em.createNativeQuery(sql.toString()).executeUpdate();
			} else {
				sql = new StringBuilder();
				sql.append("SELECT nu_id_beneficiario as valor from salud.BENEF_MEDICAMENTO b where b.NU_CEDULA = ")
						.append(dao.getNuCedula());
				sql.append(" AND b.NU_CEDULA_FAMILIAR = ").append(dao.getNuCedulaFamiliar());
				pstmt = con.prepareStatement(sql.toString());
				result = pstmt.executeQuery();

				while (result.next()) {
					dao.setNuIdBeneficiario(result.getInt("valor"));
				}
				/** UPDATE **/
				sql = new StringBuilder();
				sql.append("UPDATE SALUD.BENEF_MEDICAMENTO SET TX_EMAIL = '").append(dao.getTxEmail()).append("'");
				sql.append(" WHERE nu_id_beneficiario = ").append(dao.getNuIdBeneficiario());
				em.createNativeQuery(sql.toString()).executeUpdate();

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return dao;
	}

	@Override
	public BenefMedicamento reatach(BenefMedicamento entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<BenefMedicamento> getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}

}
