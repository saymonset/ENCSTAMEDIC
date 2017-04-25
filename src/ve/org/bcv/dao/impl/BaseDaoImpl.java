/**
 * 
 */
package ve.org.bcv.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ve.org.bcv.dao.local.EntityRepository;

/**
 * @author Ing Simon Alberto Rodriguez Pacheco
 * 17/06/2016 14:45:06
 * 2016
 * mail : oraclefedora@gmail.com
 */
public abstract class BaseDaoImpl<E, PK extends Serializable> implements EntityRepository <E, PK> {

    @PersistenceContext(unitName = "primary")
    protected EntityManager em;

    public abstract Class<E> getEntityClass();

 
    public E save(E entity) {
        em.persist(entity);
        return entity;
    }

    public E update(E build) {
    	try {
    		build=em.merge(build);
		} catch (Exception e) {
			 e.printStackTrace();
		}
        return build;
    }

  
    public void remove(E entity) {
        em.remove(entity);
    }

    
    public void refresh(E entity) {

    }

   
    public void flush() {

    }

    
    public E findByPK(PK primaryKey) {
        return em.find(getEntityClass(), primaryKey);
    }

   
    public List<E> findAll() {
        return em.createQuery("Select t from " + getEntityClass().getSimpleName() + " t").getResultList();
    }
}
