package br.com.vitrinidatasoft.controler;

import br.com.vitrinidatasoft.model.Cliente;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


/**
 *
 * @author mrhell
 */
public class ClienteDao {
    
    public void persist(Cliente cliente){
        String persistenceUnitName = "SCAPUnit";
        
        EntityManagerFactory factory; 
        factory = Persistence.createEntityManagerFactory(persistenceUnitName);
        
        EntityManager manager;
        manager = factory.createEntityManager();
        
        EntityTransaction transaction;
        transaction = manager.getTransaction();
        
        transaction.begin();
        
        try{
            manager.persist(cliente);
            transaction.commit();
        }catch(Exception e){
            System.out.println("controler.ClienteDao.persist() " 
                    + e.getMessage());
            transaction.rollback();            
        }finally{
            manager.close();
        }        
    }    
}
