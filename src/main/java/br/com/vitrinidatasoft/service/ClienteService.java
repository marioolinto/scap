/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitrinidatasoft.service;

import br.com.vitrinidatasoft.controler.ClienteDao;
import br.com.vitrinidatasoft.model.Cliente;
import br.com.vitrinidatasoft.model.Telefone;
import java.util.List;

/**
 *
 * @author mrhell
 */
public class ClienteService {
    private final ClienteDao clienteDao;
    
    public ClienteService(){   
        clienteDao = new ClienteDao();
    }
    
    public void persist(Cliente cliente){
        clienteDao.openTransaction();
        clienteDao.persist(cliente);
        clienteDao.closeTransaction();
    }
    
    public void update(Cliente cliente){
        clienteDao.openTransaction();
        clienteDao.update(cliente);
        clienteDao.closeTransaction();
    }
    
    public List<Cliente> findAll(){
        clienteDao.openCurrentManager();
        List<Cliente> list = clienteDao.findAll();
        clienteDao.closeCurrentManager();
        return list;
    }
    
    public List<Telefone> findAllContacts(Cliente cliente){
        clienteDao.openCurrentManager();
        List<Telefone> list = clienteDao.findAllContacts(cliente);
        clienteDao.closeCurrentManager();
        return list;
    }
    
}
