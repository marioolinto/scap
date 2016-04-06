package br.com.vitrinidatasoft.controler;

import br.com.vitrinidatasoft.model.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.swing.JOptionPane;
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
           JOptionPane optionPane = new JOptionPane(
                    e.getMessage(), JOptionPane.ERROR_MESSAGE);
            optionPane.setVisible(true);
        }       
    }

    @Override
    public void update(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cliente findById(Long id) {
        Cliente cliente;
        cliente = currentManager.find(Cliente.class, id);
        return cliente;
    }

    @Override
    public List<Cliente> findAll() {
        String queryString = "From Cliente";
        
        Query query; 
        query = currentManager.createQuery(queryString);
        List<Cliente> clientes = query.getResultList();
        
        return clientes;
    }

    @Override
    public void delete(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
