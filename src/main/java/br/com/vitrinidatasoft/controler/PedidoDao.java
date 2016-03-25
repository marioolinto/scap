package br.com.vitrinidatasoft.controler;

import br.com.vitrinidatasoft.model.Pedido;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author mrhell
 */
public class PedidoDao {
    public void persist(Pedido pedido){
        final String persistenceUnitName = "SCAPUnit";
        
        EntityManagerFactory factory;
        factory = Persistence.createEntityManagerFactory(persistenceUnitName);
        
        EntityManager manager;
        manager = factory.createEntityManager();
        
        EntityTransaction transaction;
        transaction = manager.getTransaction();
        
        transaction.begin();
        
        try{
            manager.persist(pedido);
            transaction.commit();
        }catch(Exception e){
            System.out.println("controler.PedidoDao.persist() " 
                    + e.getMessage());
            transaction.rollback();            
        }finally{
            manager.close();
        }
        
        System.out.println("Pedido Cadastrado");
        System.out.println("Cliente " + pedido.getCliente().getNome());
        System.out.println("Pedido " + pedido.getNumero());
        
        
        factory.close();
        
    }
}
