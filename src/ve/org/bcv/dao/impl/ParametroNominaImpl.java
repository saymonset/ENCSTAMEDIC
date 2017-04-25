package ve.org.bcv.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ve.org.bcv.dao.ParametroNomina;
import ve.org.bcv.dao.ResultBolean;
import ve.org.bcv.dao.UserEncuesta;
import ve.org.bcv.dao.local.ParametroNominaLocal;
import ve.org.bcv.util.ManejadorDB;

public class ParametroNominaImpl extends BaseDaoImpl<ParametroNomina, Long> implements ParametroNominaLocal {
	@Inject
	ManejadorDB manejadorDB;

	public ParametroNomina reatach(ParametroNomina entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public Class<ParametroNomina> getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ParametroNomina> findByCoProcesoNom(String co_proceso_nom) {

		List<ParametroNomina> list = new ArrayList<ParametroNomina>();
		List<Object[]> objs = (List<Object[]>) em
				.createNativeQuery(
						"select co_proceso_nom,co_parametro_nom,va_parametro_nom,tx_parametro_nom from  PERSONAL.PARAMETRO_NOMINA T  wHERE T.TX_PARAMETRO_NOM <>'NO' and  CO_PROCESO_NOM='ENCU' order by tx_parametro_nom asc")
				.getResultList();
		ParametroNomina parametroNomina = null;
		for (Object[] obj : objs) {
			parametroNomina = new ParametroNomina();
			parametroNomina.setCo_proceso_nom(obj[0].toString());
			;
			parametroNomina.setCo_parametro_nom(obj[1].toString());
			parametroNomina.setVa_parametro_nom(obj[2].toString());
			parametroNomina.setTx_parametro_nom(obj[3].toString());
			list.add(parametroNomina);
		}

		return list;
	}
	
	
	public List<ParametroNomina> findByCoProcesoNomNo(String co_proceso_nom,String tx_parametro_nom) {

		//select * from  PERSONAL.PARAMETRO_NOMINA t  wHERE  t.CO_PROCESO_NOM='ENCU' and T.TX_PARAMETRO_NOM ='NO' order by tx_parametro_nom asc
		List<ParametroNomina> list = new ArrayList<ParametroNomina>();
		List<Object[]> objs = (List<Object[]>) em
				.createNativeQuery(
						"select co_proceso_nom,co_parametro_nom,va_parametro_nom,tx_parametro_nom from  PERSONAL.PARAMETRO_NOMINA T wHERE  CO_PROCESO_NOM='ENCU' and T.TX_PARAMETRO_NOM ='"+tx_parametro_nom+"' order by tx_parametro_nom asc")
				.getResultList();
		ParametroNomina parametroNomina = null;
		for (Object[] obj : objs) {
			parametroNomina = new ParametroNomina();
			parametroNomina.setCo_proceso_nom(obj[0].toString());
			;
			parametroNomina.setCo_parametro_nom(obj[1].toString());
			parametroNomina.setVa_parametro_nom(obj[2].toString());
			parametroNomina.setTx_parametro_nom(obj[3].toString());
			list.add(parametroNomina);
		}

		return list;
	}
	
	

	public boolean save(List<ParametroNomina> lista) {
		boolean salida = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = manejadorDB.coneccionPool();

			pstmt = con.prepareStatement("SELECT salud.SEQ_NU_ID_BENEFICIARIO.CURRVAL AS valor from dual");
			ResultSet result = pstmt.executeQuery();
			int nuSolicitud = 0;
			if (result != null) {
				while (result.next()) {
					nuSolicitud = result.getInt("valor");
				}
			}

			for (ParametroNomina nomina : lista) {
				StringBuilder sql = new StringBuilder();
				sql.append(
						"INSERT INTO SALUD.ENCUESTA_MEDICAMEN (NU_ID_BENEFICIARIO,CO_PROVEEDOR,CO_MEDICAMENTO,FE_REG_PAGO,ST_RECIBIR_MEDICAM) ");
				sql.append(" VALUES (").append(nuSolicitud).append(",'").append(nomina.getCo_proceso_nom())
						.append("','").append(nomina.getCo_parametro_nom()).append("',SYSDATE,'")
						.append(nomina.getStRecibirMedicam().toUpperCase()).append("')");
				em.createNativeQuery(sql.toString()).executeUpdate();
				salida = true;
//				parametroNomina.setCo_proceso_nom(null!=obj[0]?obj[0].toString():"");
//				parametroNomina.setCo_parametro_nom(null!=obj[1]?obj[1].toString():"");
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

		return salida;
	}

	public UserEncuesta findUserEncuesta(String cedula) {
		UserEncuesta enc = null;
		List<Object[]> objs = (List<Object[]>) em.createNativeQuery(
				"select apellido1 || ' ' || apellido2 || ' ' ||   nombre1 || ' ' || nombre2, tipo_emp  from PERSONAL.TODOS_EMPLEADOS t where T.CEDULA="
						+ cedula)
				.getResultList();
		for (Object[] obj : objs) {
			enc = new UserEncuesta();
			enc.setNombre(obj[0].toString());
			enc.setTipoEmp(obj[1].toString());
			break;
		}

		return enc;
	}

	public ResultBolean personaYaEncuestada(String cedula, String nuCedulaFamiliar) {
	ResultBolean resultBolean=null;
				Connection con = null;
				PreparedStatement pstmt = null;
				try {
					con = manejadorDB.coneccionPool();
					pstmt = con.prepareStatement("select BM.NU_CEDULA from SALUD.BENEF_MEDICAMENTO bm where BM.NU_CEDULA ="+cedula+" and BM.NU_CEDULA_FAMILIAR ="+nuCedulaFamiliar);
					ResultSet result = pstmt.executeQuery();
					if (result != null) {
						while (result.next()) {
							   resultBolean = new ResultBolean();
		                	   resultBolean.setExiste(true);
						}
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

				
		return resultBolean;
	}
	
	public List<ParametroNomina> encuesta (String interesdos){
		List<ParametroNomina> list = new ArrayList<ParametroNomina>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select  PN.CO_PARAMETRO_NOM,PN.VA_PARAMETRO_NOM,PN.TX_PARAMETRO_NOM from  PERSONAL.PARAMETRO_NOMINA pn ");
		sql.append(" inner join SALUD.ENCUESTA_MEDICAMEN sm on PN.CO_PROCESO_NOM=SM.CO_PROCESO_NOM ");
		sql.append(" where PN.CO_PROCESO_NOM='ENCU' AND SM.CO_PARAMETRO_NOM=PN.CO_PARAMETRO_NOM ");
	//	sql.append(" and SM.ST_RECIBIR_MEDICAM ='").append(interesdos).append("'");
		sql.append(" ORDER BY PN.TX_PARAMETRO_NOM ");
		
		List<Object[]> objs = (List<Object[]>) em
				.createNativeQuery(
						sql.toString())
				.getResultList();
		ParametroNomina parametroNomina = null;
		for (Object[] obj : objs) {
			parametroNomina = new ParametroNomina();
			parametroNomina.setCo_parametro_nom(obj[0].toString());
			parametroNomina.setVa_parametro_nom(obj[1].toString());
			parametroNomina.setTx_parametro_nom(obj[2].toString());
			list.add(parametroNomina);
		}

		return list;
		
		 
        
        
         
	}

}
