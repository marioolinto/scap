package br.com.vitrinidatasoft.controler;

import br.com.vitrinidatasoft.model.Produto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.swing.JOptionPane;

/**
 *
 * @author mrhell
 */
public class ProdutoDao implements InterfaceDao<Produto, Long>{    
    private EntityManager currentManager;   
    private EntityTransaction currentTransaction;
            
    public void openTransaction(){                
        currentManager = DataBaseFactory.getInstance().createEntityManager();
        currentTransaction = currentManager.getTransaction();        
        currentTransaction.begin();
    }
    
    public void closeTransaction(){
        currentTransaction.commit();
        getCurrentManager().close();
        //getCurrentFactory().close();
    }
    
    public EntityManager getCurrentManager() {
        return currentManager;
    }

    public void setCurrentManager(EntityManager currentManager) {
        this.currentManager = currentManager;
    }        
    
    @Override
    public void persist(Produto produto) {
        try{
           currentManager.persist(produto);
        }catch(Exception e){
            JOptionPane optionPane = new JOptionPane(
                    e.getMessage(), JOptionPane.ERROR_MESSAGE);
            optionPane.setVisible(true);
        }
    }

    @Override
    public void update(Produto entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Produto findById(Long id) {
        Produto produto;        
        produto = getCurrentManager().find(Produto.class, id);
        return produto;
    }

    @Override
    public List<Produto> findAll() {
        String queryString = "From Produto";
        EntityManager entityManager = 
                DataBaseFactory.getInstance().createEntityManager();
        Query query = entityManager.createQuery(queryString);
        List<Produto> produtos = query.getResultList();
        entityManager.close();
        return produtos;
    }

    @Override
    public void delete(Produto entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
