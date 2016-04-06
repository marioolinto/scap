package br.com.vitrinidatasoft.controler;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author mrhell
 * @param <T>
 * @param <Id>
 */
public interface InterfaceDao <T, Id extends Serializable> {
    
    public void persist(T entity);
    public void update(T entity);
    public T findById(Id id);
    public List<T> findAll();
    public void  delete(T entity);
}
