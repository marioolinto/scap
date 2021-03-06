/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitrinidatasoft.service;

import br.com.vitrinidatasoft.controler.PedidoDao;
import br.com.vitrinidatasoft.model.Cliente;
import br.com.vitrinidatasoft.model.Pedido;
import br.com.vitrinidatasoft.model.PedidoItem;
import java.util.List;

/**
 *
 * @author mrhell
 */
public class PedidoService {
    
    private final PedidoDao pedidoDao;

    public PedidoService() {
        this.pedidoDao = new PedidoDao();
    }
    
    public void persist(Pedido pedido){
        pedidoDao.openTransaction();
        pedidoDao.persist(pedido);
        pedidoDao.closeTransaction();
    }
    
    public List<Pedido> findByClient(Cliente cliente){
        pedidoDao.openCurrentManager();
        List<Pedido> list = pedidoDao.findByClient(cliente);
        pedidoDao.closeCurrentManager();
        return list;
    }
          
    public List<PedidoItem> findItensByPedido(Pedido pedido){
        pedidoDao.openCurrentManager();
        List<PedidoItem> list = pedidoDao.findItensByPedido(pedido);
        pedidoDao.closeCurrentManager();
        return list;
    }
    
    public List<Pedido> findByNumero(String numero){
        pedidoDao.openCurrentManager();
        List<Pedido> pedidos = pedidoDao.findByNumero(numero);        
        pedidoDao.closeCurrentManager();
        return pedidos;
    }
}
