package br.com.vitrinidatasoft.controler;

import br.com.vitrinidatasoft.model.Pedido;
import br.com.vitrinidatasoft.utils.Constantes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

/**
 *
 * @author mrhell
 */
public class PedidoDao implements InterfaceDao<Pedido, Long>{
    private EntityManagerFactory currentFactory;
    private EntityManager currentManager;   
    private EntityTransaction currentTransaction;
    
    public EntityManagerFactory openCurrentFactory(){
        currentFactory = Persistence.createEntityManagerFactory(
                Constantes.PERSISTENCE_UNIT_NAME);
        return currentFactory;
    }
    
    public void closeCurrentFactory(){
        if (currentFactory.isOpen())
            currentFactory.close();
    }
    
    public void openCurrentManager(){        
        setCurrentManager(openCurrentFactory().createEntityManager());         
    }
    
    public void closeCurrentManager(){
        getCurrentManager().close();
        closeCurrentFactory();
    }    
    
    public void openTransaction(){
        currentFactory = Persistence.createEntityManagerFactory(
                Constantes.PERSISTENCE_UNIT_NAME);
        currentManager = currentFactory.createEntityManager();
        currentTransaction = currentManager.getTransaction();
        currentTransaction.begin();
    }
    
    public void closeTransaction(){
        currentTransaction.commit();
        getCurrentManager().close();
        getCurrentFactory().close();
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
    
     public EntityManagerFactory getCurrentFactory() {
        return currentFactory;
    }

    public void setCurrentFactory(EntityManagerFactory currentFactory) {
        this.currentFactory = currentFactory;
    }
        
    @Override
    public void persist(Pedido pedido) {
        try{
            currentManager.persist(pedido);
        }catch(Exception e){
            JOptionPane optionPane = new JOptionPane(
                    e.getMessage(), JOptionPane.ERROR_MESSAGE);
            optionPane.setVisible(true);
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

    @Override
    public void delete(Pedido entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}
