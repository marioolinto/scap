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
    
    public List<Produto> findAll(){
        
        List<Produto> list = produtoDao.findAll();
        
        return list;
    }
            
}
