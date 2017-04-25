/**
 * 
 */
package ve.org.bcv.autenticador.ldap.connection;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 02/06/2016 14:49:37
 * 2016
 * mail : oraclefedora@gmail.com
 */
public class AtributoDTO
{
	  private String nombre;
	  private Collection<String> valor = new ArrayList();
	  
	  public void setNombre(String nombre)
	  {
	    this.nombre = nombre;
	  }
	  
	  public String getNombre()
	  {
	    return this.nombre;
	  }
	  
	  public void setValor(String valor)
	  {
	    this.valor.add(valor);
	  }
	  
	  public void setValor(String[] valorArray)
	  {
	    String[] arrayOfString;
	    int j = (arrayOfString = valorArray).length;
	    for (int i = 0; i < j; i++)
	    {
	      String string = arrayOfString[i];
	      this.valor.add(string);
	    }
	  }
	  
	  public Collection<String> getValor()
	  {
	    return this.valor;
	  }
	  
	  public AtributoDTO(String DN)
	  {
	    this.nombre = "DN";
	    setValor(DN);
	  }
	  
	  public AtributoDTO() {}
	}
