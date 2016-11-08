/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitrinidatasoft.service;

import br.com.vitrinidatasoft.controler.ProdutoDao;
import br.com.vitrinidatasoft.model.Produto;
import java.util.List;

/**
 *
 * @author mrhell
 */
public class ProdutoService {
    private final ProdutoDao produtoDao;
    
    public ProdutoService(){
        produtoDao = new ProdutoDao();
    }
    
    public void persist(Produto produto){
        produtoDao.openTransaction();
        produtoDao.persist(produto);
        produtoDao.closeTransaction();
    }
    
    public void update(Produto produto){
        produtoDao.openTransaction();
        produtoDao.update(produto);
        produtoDao.closeTransaction();
    }
    
    public List<Produto> findAll(){
        produtoDao.openCurrentManager();
        List<Produto> list = produtoDao.findAll();
        produtoDao.closeCurrentManager();
        
        return list;
    }
    
    public List<Produto> findByFilter(String filter){
        produtoDao.openCurrentManager();
        List<Produto> produtos = produtoDao.findByFiter(filter);
        produtoDao.closeCurrentManager();
        return produtos;
    }
            
}
