package br.com.vitrinidatasoft.controler;

import br.com.vitrinidatasoft.model.Produto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

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
    }
    
    public void openCurrentManager(){
        setCurrentManager(DataBaseFactory.getInstance().createEntityManager());
    }
    
     public void closeCurrentManager(){
        getCurrentManager().close();
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
            System.out.println(e);
        }
    }

    @Override
    public void update(Produto produto) {
         try{
           currentManager.merge(produto);
        }catch(Exception e){
            System.out.println(e);
        }
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
        
        Query query = currentManager.createQuery(queryString);
        List<Produto> produtos = query.getResultList();        
        return produtos;
    }
    
    public List<Produto> findByFiter(String filter){
        String queryString = "FROM Produto produto "
                + "WHERE UPPER(produto.nome) like :filter OR "
                + "UPPER(produto.descricao) like :filter ";
        Query query = currentManager.createQuery(queryString);
        String keyword = "%" + filter.toUpperCase() + "%"; 
        query.setParameter("filter", keyword);
        List<Produto> produtos = query.getResultList();
        
        return produtos;
    }

    @Override
    public void delete(Produto entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
