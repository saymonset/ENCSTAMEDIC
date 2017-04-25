/**
 * 
 */
package ve.org.bcv.dao.local;

import java.io.Serializable;
import java.util.List;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 14/06/2016 14:59:08
 * 2016
 * mail : oraclefedora@gmail.com
 */
public interface EntityRepository<E, PK extends Serializable> {

	E save(E entity);
	
	E update(E build) ;

	void remove(E entity);

	void refresh(E entity);

	void flush();

	E findByPK(PK primaryKey);

	List<E> findAll();
	
	 
	
	public E reatach(E entity);
 
}