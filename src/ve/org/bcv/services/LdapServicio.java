/**
 * 
 */
package ve.org.bcv.services;

import java.io.Serializable;

import javax.ws.rs.PathParam;

import ve.org.bcv.dto.UserInfo;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 23/06/2016 08:59:42
 * 2016
 * mail : oraclefedora@gmail.com
 */
public interface LdapServicio extends Serializable {
	UserInfo login(@PathParam("login") String login,
			@PathParam("password") String password);
}
