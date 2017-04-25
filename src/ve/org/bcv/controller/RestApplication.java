/**
 * 
 */
package ve.org.bcv.controller;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import ve.org.bcv.servicesImpl.EncuestaCtrlResource;
import ve.org.bcv.servicesImpl.Encuesta_caServiceResource;
import ve.org.bcv.servicesImpl.MandarMailResource;
/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 03/06/2016 14:50:32
 * 2016
 * mail : oraclefedora@gmail.com
 */
@ApplicationPath("/services")
public class RestApplication extends Application {
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> classes = new HashSet<Class<?>>();

	public RestApplication() {
 
		 
		classes.add(EncuestaCtrlResource.class);
		classes.add(Encuesta_caServiceResource.class);
		classes.add(MandarMailResource.class);
		
		

	}

	  public Set<Class<?>> getClasses() {
		    return classes;
		  }
	  
	public Set<Object> getSingletons() {
		return singletons;
	}
}
 