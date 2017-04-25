package ve.org.bcv.excel;

import java.io.Serializable;

import javax.persistence.EntityManager;

public interface WriteBD extends Serializable{
	 
	
		/**
		 * Guardamos en bd tdoos los registros leidos de excel
		 * @param rowFound
		 * @param manager
		 */
		void createRecords( EntityManager manager)throws Exception;
	}
