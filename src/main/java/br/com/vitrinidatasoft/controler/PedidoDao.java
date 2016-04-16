package br.com.vitrinidatasoft.controler;

import br.com.vitrinidatasoft.model.Cliente;
import br.com.vitrinidatasoft.model.Pedido;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author mrhell
 */
public class PedidoDao implements InterfaceDao<Pedido, Long>{
   
    private EntityManager currentManager;   
    private EntityTransaction currentTransaction;
            
    public void openCurrentManager(){        
        setCurrentManager(DataBaseFactory.getInstance().createEntityManager());         
    }
    
    public void closeCurrentManager(){
        getCurrentManager().close();        
    }    
    
    public void openTransaction(){                 
        setCurrentManager(DataBaseFactory.getInstance().createEntityManager());        
        currentTransaction = currentManager.getTransaction();
        currentTransaction.begin();
    }
    
    public void closeTransaction(){
        currentTransaction.commit();
        getCurrentManager().close();       
    }
    
    public EntityManager getCurrentManager() {
        return currentManager;
    }

    public void setCurrentManager(EntityManager currentManager) {
        this.currentManager = currentManager;
    }

    public EntityTransaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(EntityTransaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }     
        
    @Override
    public void persist(Pedido pedido) {
        try{
            currentManager.persist(pedido);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override   
    public void update(Pedido entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pedido findById(Long id) {
        Pedido pedido;
        pedido = currentManager.find(Pedido.class, id);
        return pedido;
    }

    @Override
    public List<Pedido> findAll() {
        String queryString = "From Pedido";
        Query query = currentManager.createQuery(queryString);
        List<Pedido> pedidos = query.getResultList();
        return pedidos;
    }
    
    public List<Pedido> findByClient(Cliente cliente) {
        String queryString = "Select pedido From Pedido pedido where " + 
                "pedido.cliente.id = " + cliente.getId();
        Query query = currentManager.createQuery(queryString);
        List<Pedido> pedidos = query.getResultList();
        return pedidos;
    }

    @Override
    public void delete(Pedido entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}
