/**
 * 
 */
package ve.org.bcv.autenticador.ldap.connection;

import java.io.InputStream;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import ve.org.bcv.configuration.PropertiesUrl;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 02/06/2016 14:52:15
 * 2016
 * mail : oraclefedora@gmail.com
 */
public class LDAPUtilities 
{
	  private static Logger log = Logger.getLogger(LDAPUtilities.class);
	  
	 
	  private static String PATRON_USUARIO_EXTERNO = "bcv.ldap.regex.externalClient";
	  
	  public static Properties cargarPropiedades()
	    throws Exception
	  {
	    Properties property = new Properties();
	    
	  
	    PropertiesUrl propertiesUrl = new PropertiesUrl();
	   // InputStream inputStream = new FileInputStream(nameProperties);
	    InputStream inputStream =propertiesUrl.getClass().getResourceAsStream("autenticador.properties");
	    if (inputStream == null) {
	      throw new Exception("Archivo'" +  "' no puede ser encontrado o no existe");
	    }
	    property.load(inputStream);
	    log.info("ARCHIVO CARGADO");
	    log.debug(property);
	    
	    return property;
	  }
	  
	  public  boolean validarUsuarioExt(String usuario)
	    throws Exception
	  {
	    boolean valid = false;
	    
	    String patron = cargarPropiedades().getProperty(PATRON_USUARIO_EXTERNO);
	    if ((usuario.equalsIgnoreCase("")) || (usuario == null) || (patron.equalsIgnoreCase("")) || (patron == null)) {
	      throw new Exception("LO PARAMETROS PARA LA VALIDACION NO PUEDEN SER NULOS O ESTAR VACIOS");
	    }
	    Pattern p = Pattern.compile(patron);
	    Matcher m = p.matcher(usuario);
	    valid = m.matches();
	    
	    log.info("USUARIO VALIDADO");
	    return valid;
	  }
	  
	  public static boolean expiredPassword(String fecha)
	    throws Exception
	  {
	    int dia = Integer.parseInt(fecha.substring(6, 8));
	    int mes = Integer.parseInt(fecha.substring(4, 6));
	    int anio = Integer.parseInt(fecha.substring(0, 4));
	    Date fechaD = new GregorianCalendar(anio, mes - 1, dia).getTime();
	    boolean resp;
	    if (fechaD.getTime() < new Date().getTime()) {
	      resp = true;
	    } else {
	      resp = false;
	    }
	    log.info("CLAVE VERIFICADA");
	    return resp;
	  }
	}

