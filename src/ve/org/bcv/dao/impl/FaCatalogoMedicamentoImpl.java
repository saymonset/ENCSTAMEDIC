package ve.org.bcv.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import ve.org.bcv.dao.BenefMedicamento;
import ve.org.bcv.dao.ParametroNomina;
import ve.org.bcv.dao.ResultBolean;
import ve.org.bcv.dao.UserEncuesta;
import ve.org.bcv.dao.local.FaCatalogoMedicamentoLocal;
import ve.org.bcv.dto.ParametroNominaDto;
import ve.org.bcv.util.ManejadorDB;

public class FaCatalogoMedicamentoImpl extends BaseDaoImpl<ParametroNomina, Long>
		implements FaCatalogoMedicamentoLocal {
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

		StringBuilder sql = new StringBuilder();
		sql.append(
				" select  NVL(f.co_proveedor, 0) ,F.CO_MEDICAMENTO ,F.MO_PRECIO,F.NB_MEDICAMENTO from salud.fa_catalogo_medicamento F ");
		sql.append(" where co_proveedor=10427531 ");
		sql.append(
				"  and TRUNC(fe_vigencia) =  (select TRUNC(max(fe_vigencia)) from SALUD.FA_CATALOGO_MEDICAMENTO b where f.co_medicamento= b.co_medicamento and f.co_proveedor=b.co_proveedor) ");
		sql.append(" and rownum <=100 ");
		sql.append("  ");
		sql.append(" order by 1 ");

		// select * from SALUD.FA_CATALOGO_MEDICAMENTO a
		// where co_proveedor=10427531
		// and fe_vigencia = (select max(fe_vigencia) from
		// SALUD.FA_CATALOGO_MEDICAMENTO b where a.co_medicamento=
		// b.co_medicamento and a.co_proveedor=b.co_proveedor)

		List<Object[]> objs = (List<Object[]>) em.createNativeQuery(sql.toString()).getResultList();

		//
		ParametroNomina parametroNomina = null;
		for (Object[] obj : objs) {
			parametroNomina = new ParametroNomina();

			parametroNomina.setCo_proceso_nom(null != obj[0] ? obj[0].toString() : "");
			parametroNomina.setCo_parametro_nom(null != obj[1] ? obj[1].toString() : "");
			parametroNomina.setVa_parametro_nom(null != obj[2] ? obj[2].toString() : "");
			parametroNomina.setTx_parametro_nom(null != obj[3] ? obj[3].toString() : "");
			list.add(parametroNomina);
		}

		return list;
	}

	public List<ParametroNomina> buscarMedicamento(String filtro) {
		if (filtro == null) {
			filtro = "";
		}
		List<ParametroNomina> list = new ArrayList<ParametroNomina>();
		StringBuilder sql = new StringBuilder();
		sql.append(
				" select  NVL(f.co_proveedor, 0) ,F.CO_MEDICAMENTO ,F.MO_PRECIO,F.NB_MEDICAMENTO from salud.fa_catalogo_medicamento F ");
		sql.append(" where co_proveedor= ").append("10427531");
		// sql.append(" and TRUNC(fe_vigencia) = TO_DATE('22022017','ddmmyyyy')
		// and rownum <=50");
		sql.append(
				"  and TRUNC(fe_vigencia) =  (select TRUNC(max(fe_vigencia)) from SALUD.FA_CATALOGO_MEDICAMENTO b where f.co_medicamento= b.co_medicamento and f.co_proveedor=b.co_proveedor) ");
		sql.append(" and rownum <=100 ");
		sql.append(" and lower(f.NB_MEDICAMENTO) like ('%").append(filtro.toLowerCase()).append("%')");
		sql.append(" order by 1 ");

		List<Object[]> objs = (List<Object[]>) em.createNativeQuery(sql.toString()).getResultList();

		//
		ParametroNomina parametroNomina = null;
		int defaultValor = 1;
		for (Object[] obj : objs) {
			parametroNomina = new ParametroNomina();
			parametroNomina.setCo_proceso_nom(null != obj[0] ? obj[0].toString() : "");
			parametroNomina.setCo_parametro_nom(null != obj[1] ? obj[1].toString() : "");
			parametroNomina.setVa_parametro_nom(null != obj[2] ? obj[2].toString() : "");
			parametroNomina.setTx_parametro_nom(null != obj[3] ? obj[3].toString() : "");
			parametroNomina.setCantSolicitada(defaultValor);
			list.add(parametroNomina);
		}

		return list;
	}

	public List<ParametroNomina> findByCoProcesoNomNo(String co_proceso_nom, String tx_parametro_nom) {

		// select * from PERSONAL.PARAMETRO_NOMINA t wHERE
		// t.CO_PROCESO_NOM='ENCU' and T.TX_PARAMETRO_NOM ='NO' order by
		// tx_parametro_nom asc
		List<ParametroNomina> list = new ArrayList<ParametroNomina>();
		List<Object[]> objs = (List<Object[]>) em.createNativeQuery(
				"select co_proceso_nom,co_parametro_nom,va_parametro_nom,tx_parametro_nom from  PERSONAL.PARAMETRO_NOMINA T wHERE  CO_PROCESO_NOM='ENCU' and T.TX_PARAMETRO_NOM ='"
						+ tx_parametro_nom + "' order by tx_parametro_nom asc")
				.getResultList();
		ParametroNomina parametroNomina = null;
		for (Object[] obj : objs) {
			parametroNomina = new ParametroNomina();

			parametroNomina.setCo_proceso_nom(null != obj[0] ? obj[0].toString() : "");
			parametroNomina.setCo_parametro_nom(null != obj[1] ? obj[1].toString() : "");
			parametroNomina.setVa_parametro_nom(null != obj[2] ? obj[2].toString() : "");
			parametroNomina.setTx_parametro_nom(null != obj[3] ? obj[3].toString() : "");
			list.add(parametroNomina);
		}

		return list;
	}

	public boolean save(List<ParametroNomina> lista, BenefMedicamento benefMedicamento) {
		boolean salida = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = manejadorDB.coneccionPool();
			/*** DELETE ***/
			StringBuilder sql = new StringBuilder();
			sql.append(" delete from SALUD.ENCUESTA_MEDICAMEN where NU_ID_BENEFICIARIO= ")
					.append(benefMedicamento.getNuIdBeneficiario());
			em.createNativeQuery(sql.toString()).executeUpdate();
			em.flush();
			/***** INSERT ******/

			ParametroNominaDto parametroNominaDto = new ParametroNominaDto();
			for (ParametroNomina nomina : lista) {
				em.clear();

				try {
					sql = new StringBuilder();
					sql.append(
							"INSERT INTO SALUD.ENCUESTA_MEDICAMEN (NU_ID_BENEFICIARIO,CO_PROVEEDOR,CO_MEDICAMENTO,FE_REG_PAGO,ST_RECIBIR_MEDICAM) ");
					sql.append(" VALUES (").append(benefMedicamento.getNuIdBeneficiario()).append(",'")
							.append(nomina.getCo_proceso_nom()).append("','").append(nomina.getCo_parametro_nom())
							.append("',SYSDATE,'").append(nomina.getStRecibirMedicam().toUpperCase()).append("')");
					em.createNativeQuery(sql.toString()).executeUpdate();
					salida = true;
					em.flush();
				} catch (Exception e) {

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

		return salida;
	}

	public List<UserEncuesta> familiares(String cedula) {
		List<UserEncuesta> userEncuestaLst = new ArrayList<UserEncuesta>();

		Connection con = null;
		PreparedStatement pstmt = null;
		StringBuilder sql = new StringBuilder();

		sql.append(
				" select TO_CHAR(es.fecha_nacim, 'dd-mm-yyyy') fecha_nacim,cedula, es.nombre1|| ' '||es.nombre2 ||' ' ||es.apellido1||' ' ||es.apellido2   nombre_beneficiario ");
		sql.append("                                              from personal.empleado_sobreviviente es ");
		sql.append("                                              where es.CEDULA =").append(cedula);
		sql.append("  union ");
		sql.append(
				"   select  TO_CHAR(FAM.FECHA_NACIMIENTO, 'dd-mm-yyyy') fecha_nacim,FAM.CEDULA_FAMILIAR cedula,  fam.nombre1|| ' '||fam.nombre2 ||' ' ||fam.apellido1||' ' ");
		sql.append("    ||fam.apellido2 nombre_beneficiario          from personal.familiares  fam ");
		sql.append("                                                        where FAM.NU_CEDULA =").append(cedula);
		sql.append("      and FAM.STATUS='A'            ");
		sql.append("                                                        order by fecha_nacim,cedula ");

		try {
			con = manejadorDB.coneccionPool();
			pstmt = con.prepareStatement(sql.toString());
			ResultSet result = pstmt.executeQuery();
			if (result != null) {

				UserEncuesta userEncuesta = null;
				while (result.next()) {

					userEncuesta = new UserEncuesta();

					userEncuesta.setCedula(result.getString("cedula"));
					;
					userEncuesta.setNombre(result.getString("nombre_beneficiario"));
					userEncuesta.setFechaNacimiento(result.getString("fecha_nacim"));

					userEncuestaLst.add(userEncuesta);

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

		return userEncuestaLst;
	}

	public UserEncuesta findUserEncuesta(String cedula) {
		UserEncuesta enc = null;
		List<Object[]> objs = (List<Object[]>) em.createNativeQuery(
				"select pers.CODIGO_UBICACION ,pers.NU_EXTENSION_1,INITCAP(pers.APELLIDO1||' '||pers.APELLIDO2 ||' '|| pers.NOMBRE1||' '||pers.NOMBRE2 ) AS trabajador, pers.tipo_emp   from PERSONAL.TODOS_EMPLEADOS pers where pers.CEDULA="
						+ cedula)
				.getResultList();
		for (Object[] obj : objs) {
			enc = new UserEncuesta();

			enc.setCodigoUbicacion(obj[0] != null ? obj[0].toString() : "");
			enc.setNuExtension_1(obj[1] != null ? obj[1].toString() : "");
			enc.setNombre(obj[2] != null ? obj[2].toString() : "");
			enc.setTipoEmp(obj[3] != null ? obj[3].toString() : "");
			enc.setCedula(cedula);
			break;
		}

		return enc;
	}

	public ResultBolean personaYaEncuestada(String cedula, String nuCedulaFamiliar) {
		ResultBolean resultBolean = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = manejadorDB.coneccionPool();
			pstmt = con.prepareStatement("select BM.NU_CEDULA from SALUD.BENEF_MEDICAMENTO bm where BM.NU_CEDULA ="
					+ cedula + " and BM.NU_CEDULA_FAMILIAR =" + nuCedulaFamiliar);
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

	public List<ParametroNomina> buscarCedulaMedicamentosFamiliar(String NU_ID_BENEFICIARIO) {
		List<ParametroNomina> list = new ArrayList<ParametroNomina>();
		StringBuilder sql = new StringBuilder();

		sql.append(
				" select  PN.CO_PROVEEDOR ,PN.CO_MEDICAMENTO ,PN.NB_MEDICAMENTO,TO_CHAR(SM.FE_REG_PAGO, 'dd-mm-yyyy') fe_reg_pago from  salud.fa_catalogo_medicamento pn ");
		sql.append("  inner join SALUD.ENCUESTA_MEDICAMEN sm on TO_CHAR(PN.CO_PROVEEDOR) =  SM.CO_PROVEEDOR ");
		sql.append(" where PN.CO_MEDICAMENTO  =SM.CO_MEDICAMENTO  ");
		sql.append(" and SM.NU_ID_BENEFICIARIO =  ").append(NU_ID_BENEFICIARIO);
		sql.append(" ORDER BY PN.NB_MEDICAMENTO ");

		List<Object[]> objs = (List<Object[]>) em.createNativeQuery(sql.toString()).getResultList();
		ParametroNomina parametroNomina = null;
		for (Object[] obj : objs) {
			parametroNomina = new ParametroNomina();
			parametroNomina.setCo_proceso_nom(obj[0].toString());
			parametroNomina.setCo_parametro_nom(obj[1].toString());
			parametroNomina.setVa_parametro_nom("");
			parametroNomina.setTx_parametro_nom(obj[2].toString());
			parametroNomina.setFeRegPagoStr(obj[3].toString());

			list.add(parametroNomina);
		}

		return list;
	}

	public List<UserEncuesta> buscarCedulaMedicamentosQuery(String feDesdeStr, String fechaHastaStr) {
		List<UserEncuesta> userEncuestaLst = new ArrayList<UserEncuesta>();

		Connection con = null;
		PreparedStatement pstmt = null;
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT bm.nu_cedula cedula_titular, ");
		sql.append(" es.nombre1|| ' '||es.nombre2 ||' ' ||es.apellido1||' ' ||es.apellido2 nombre_titular, ");
		sql.append(" bm.nu_cedula_familiar   cedula_beneficiario, ");
		sql.append("  fam.nombre1|| ' '||fam.nombre2 ||' ' ||fam.apellido1||' ' ||fam.apellido2 nombre_beneficiario, ");
		sql.append("     em.co_medicamento codigo_medicamento, ");
		sql.append(" fcm.nb_medicamento nombre_medicamento, ");
		sql.append(" null as cantidadSolicitada, ");
		sql.append(" null as cantidadEntregada, ");
		sql.append(
				"   null as precioUnitario,TO_CHAR(em.fe_reg_pago, 'dd-mm-yyyy') fe_reg_pago,TO_CHAR(em.fe_reg_pago, 'yyyy-mm-dd') fe_Ordenar   ");
		sql.append(" FROM salud.encuesta_medicamen em, ");
		sql.append("  salud.benef_medicamento bm, ");
		sql.append("  personal.empleado_sobreviviente es,");
		sql.append("  personal.familiares  fam, ");
		sql.append(" salud.fa_catalogo_medicamento fcm ");
		sql.append(" WHERE em.nu_id_beneficiario=bm.nu_id_beneficiario ");
		sql.append(" AND bm.nu_cedula = es.cedula ");

		sql.append(" AND   TO_CHAR(em.fe_reg_pago, 'yyyy-mm-dd') >= '" + feDesdeStr
				+ "' and TO_CHAR(em.fe_reg_pago, 'yyyy-mm-dd') <= '" + fechaHastaStr + "' ");

		sql.append(" AND bm.nu_cedula_familiar = fam.cedula_familiar ");
		sql.append(" AND em.co_medicamento =  fcm.co_medicamento ");
		sql.append(" AND fcm.co_proveedor =  em.co_proveedor  ");

		sql.append(" UNION ");

		sql.append(" SELECT bm.nu_cedula cedula_titular, ");
		sql.append(" es.nombre1 ");
		sql.append(" || ' ' ");
		sql.append(" || es.nombre2 ");
		sql.append(" || ' ' ");
		sql.append(" || es.apellido1 ");
		sql.append(" || ' ' ");
		sql.append(" || es.apellido2 ");
		sql.append(" nombre_titular, ");
		sql.append(" bm.nu_cedula cedula_beneficiario, ");
		sql.append(" es.nombre1 ");
		sql.append(" || ' ' ");
		sql.append(" || es.nombre2 ");
		sql.append(" || ' ' ");
		sql.append(" || es.apellido1 ");
		sql.append(" || ' ' ");
		sql.append(" || es.apellido2 ");
		sql.append(" nombre_beneficiario, ");
		sql.append(" em.co_medicamento codigo_medicamento, ");
		sql.append(" fcm.nb_medicamento nombre_medicamento, ");
		sql.append(" NULL AS cantidadSolicitada, ");
		sql.append(" NULL AS cantidadEntregada, ");
		sql.append("NULL AS precioUnitario, ");
		sql.append("      TO_CHAR (em.fe_reg_pago, 'dd-mm-yyyy') fe_reg_pago, ");
		sql.append(" TO_CHAR (em.fe_reg_pago, 'yyyy-mm-dd') fe_Ordenar ");
		sql.append(" FROM salud.encuesta_medicamen em, ");
		sql.append(" salud.benef_medicamento bm,  ");
		sql.append(" personal.empleado_sobreviviente es, ");
		sql.append(" salud.fa_catalogo_medicamento fcm ");
		sql.append(" WHERE     em.nu_id_beneficiario = bm.nu_id_beneficiario ");
		sql.append(" AND bm.nu_cedula = es.cedula ");
		sql.append(" AND   TO_CHAR(em.fe_reg_pago, 'yyyy-mm-dd') >= '" + feDesdeStr
				+ "' and TO_CHAR(em.fe_reg_pago, 'yyyy-mm-dd') <= '" + fechaHastaStr + "' ");
		sql.append(" AND em.co_medicamento = fcm.co_medicamento ");
		sql.append(" AND fcm.co_proveedor = em.co_proveedor ");
		sql.append(" AND bm.nu_cedula = bm.nu_cedula_familiar ");
		sql.append(" ORDER BY fe_Ordenar ASC, nombre_titular ASC , nombre_beneficiario asc, nombre_medicamento asc");

		try {
			con = manejadorDB.coneccionPool();
			pstmt = con.prepareStatement(sql.toString());
			ResultSet result = pstmt.executeQuery();
			if (result != null) {

				UserEncuesta userEncuesta = null;
				while (result.next()) {

					userEncuesta = new UserEncuesta();

					userEncuesta.setCedula(result.getString("cedula_titular"));
					;
					userEncuesta.setNombre(result.getString("nombre_titular"));

					List<BenefMedicamento> beneficiarios = userEncuesta.getBenefMedicamentos();
					if (beneficiarios == null) {
						beneficiarios = new ArrayList<BenefMedicamento>();
					}

					BenefMedicamento benefMedicamento = new BenefMedicamento();

					int cedulaTitu = 0;
					try {
						cedulaTitu = Integer.valueOf(userEncuesta.getCedula());
					} catch (Exception e) {
						cedulaTitu = 0;
					}
					benefMedicamento.setNuCedula(Integer.valueOf(cedulaTitu));

					benefMedicamento.setNombreFamiliar(result.getString("nombre_beneficiario"));

					int cedulaFam = 0;
					try {
						cedulaFam = Integer.valueOf(result.getString("cedula_beneficiario"));
					} catch (Exception e) {
						cedulaFam = 0;
					}
					benefMedicamento.setNuCedulaFamiliar(cedulaFam);

					List<ParametroNomina> medicinas = benefMedicamento.getMedicinas();
					if (null == medicinas) {
						medicinas = new ArrayList<ParametroNomina>();
					}

					ParametroNomina medicina = new ParametroNomina();

					medicina.setCo_parametro_nom(result.getString("codigo_medicamento"));
					medicina.setTx_parametro_nom(result.getString("nombre_medicamento"));

					int cantidadSolicitada = 0;
					try {
						cantidadSolicitada = Integer.valueOf(result.getString("cantidadSolicitada"));
					} catch (Exception e) {
						cantidadSolicitada = 0;
					}
					medicina.setCantSolicitada(cantidadSolicitada);

					int cantidadEntregada = 0;
					try {
						cantidadEntregada = Integer.valueOf(result.getString("cantidadEntregada"));
					} catch (Exception e) {
						cantidadEntregada = 0;
					}
					medicina.setCantidadEntregada(cantidadEntregada);

					BigDecimal precio = new BigDecimal(0);
					try {
						precio = new BigDecimal(result.getString("precioUnitario"));
						;
					} catch (Exception e) {
						precio = new BigDecimal(0);
					}
					medicina.setPrecioUnitario(precio);
					;
					medicina.setFeRegPagoStr(result.getString("fe_reg_pago"));

					medicinas.add(medicina);
					benefMedicamento.setMedicinas(medicinas);
					beneficiarios.add(benefMedicamento);
					userEncuesta.setBenefMedicamentos(beneficiarios);
					userEncuestaLst.add(userEncuesta);

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

		return userEncuestaLst;
	}

	public List<String> buscarCedulaMedicamentos(String feDesdeStr, String fechaHastaStr) {
		List<String> list = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();

		sql.append(" 	select unique(B.nu_cedula)  || '' from SALUD.BENEF_MEDICAMENTO b  ");
		sql.append(" inner join  SALUD.ENCUESTA_MEDICAMEN e  ");
		sql.append(" on E.NU_ID_BENEFICIARIO=B.NU_ID_BENEFICIARIO ");
		sql.append(" where   ");
		sql.append("   TO_CHAR(e.fe_reg_pago, 'yyyy-mm-dd') >= '").append(feDesdeStr).append("'");
		sql.append(" and TO_CHAR(e.fe_reg_pago, 'yyyy-mm-dd') <= '").append(fechaHastaStr).append("'");

		List<String[]> objs = (List<String[]>) em.createNativeQuery(sql.toString()).getResultList();
		if (objs != null && objs.size() > 0) {
			for (int i = 0; i < objs.size(); i++) {
				list.add("" + objs.get(i));
			}
		}

		Collections.sort(list);

		return list;
	}

	public List<ParametroNomina> encuesta(String interesdos, String feDesdeStr, String fechaHastaStr) {
		List<ParametroNomina> list = new ArrayList<ParametroNomina>();
		StringBuilder sql = new StringBuilder();

		sql.append(
				" select  PN.CO_PROVEEDOR ,PN.CO_MEDICAMENTO ,PN.NB_MEDICAMENTO from  salud.fa_catalogo_medicamento pn ");
		sql.append("  inner join SALUD.ENCUESTA_MEDICAMEN sm on TO_CHAR(PN.CO_PROVEEDOR) =  SM.CO_PROVEEDOR ");
		sql.append(" where PN.CO_MEDICAMENTO  =SM.CO_MEDICAMENTO and SM.ST_RECIBIR_MEDICAM ='SI' ");
		sql.append(" and TO_CHAR(sm.fe_reg_pago, 'yyyy-mm-dd') >= '").append(feDesdeStr).append("'");
		sql.append(" and TO_CHAR(sm.fe_reg_pago, 'yyyy-mm-dd') <= '").append(fechaHastaStr).append("'");
		sql.append(" ORDER BY PN.NB_MEDICAMENTO ");

		List<Object[]> objs = (List<Object[]>) em.createNativeQuery(sql.toString()).getResultList();
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
