package br.com.vitrinidatasoft.controler;

import br.com.vitrinidatasoft.model.Cliente;
import br.com.vitrinidatasoft.model.Telefone;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
/**
 *
 * @author mrhell
 */
public class ClienteDao implements InterfaceDao<Cliente, Long>{   
    

    private EntityManager currentManager;
    private EntityTransaction currentTransaction;
    
    public void openCurrentManager(){        
        setCurrentManager(DataBaseFactory.getInstance().createEntityManager());         
    }
    
    public void closeCurrentManager(){
        getCurrentManager().close();
    }            
    
    public void openTransaction(){       
        currentManager = DataBaseFactory.getInstance().createEntityManager();
        currentTransaction = currentManager.getTransaction();
        currentTransaction.begin();
    }
    
    public void closeTransaction(){
        currentTransaction.commit();
        currentManager.close();
        //currentFactory.close();
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
    public void persist(Cliente cliente) {
        try{
            currentManager.persist(cliente);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }       
    }

    @Override
    public void update(Cliente cliente) {
        try{
            currentManager.merge(cliente);
        }catch(Exception e){
            System.out.println(e.getMessage());
        } 
    }

    @Override
    public Cliente findById(Long id) {
        Cliente cliente;
        cliente = currentManager.find(Cliente.class, id);
        return cliente;
    }

    @Override
    public List<Cliente> findAll() {
        String queryString = "From Cliente cliente ORDER BY cliente.nome";
        
        Query query; 
        query = currentManager.createQuery(queryString);
        List<Cliente> clientes = query.getResultList();
        
        return clientes;
    }
    
    public List<Telefone> findAllContacts(Cliente cliente){       
        String queryString = "Select telefone From Telefone telefone " + 
                "Where telefone.cliente.id = " + cliente.getId();
        Query query = currentManager.createQuery(queryString);
        List<Telefone> telefones = query.getResultList();
        return telefones;
    }
    
     public String findMainContact(Cliente cliente){       
        String queryString = "Select telefone.numero From Telefone telefone " + 
                "Where telefone.cliente.id = " + cliente.getId();
        Query query = currentManager.createQuery(queryString);
        String telefone = "";
        if (!query.getResultList().isEmpty())
            telefone = query.setMaxResults(1).getSingleResult().toString();
        
        return telefone;
    }
    
    public List<Cliente> findFullClient(){
        String queryString = "FROM Cliente cliente  "
                + "JOIN FETCH cliente.telefones";
                                     
        Query query = currentManager.createQuery(queryString);
        List<Cliente> clientes = query.getResultList();
        return clientes;
        
    }
     
    @Override
    public void delete(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
