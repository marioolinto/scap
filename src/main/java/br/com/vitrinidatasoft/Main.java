/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitrinidatasoft;

import br.com.vitrinidatasoft.model.Cliente;
import br.com.vitrinidatasoft.model.ItemPedido;
import br.com.vitrinidatasoft.model.Pedido;
import br.com.vitrinidatasoft.model.Produto;
import br.com.vitrinidatasoft.model.Telefone;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author mrhell
 */
public class Main {
    public static void main(String[] args){
        EntityManagerFactory factory = 
                Persistence.createEntityManagerFactory("SCAPUnit");
        
        Cliente cliente = new Cliente();
        cliente.setId(6l);
        cliente.setNome("Mario Olinto");
        cliente.setCpf("6142868272");
        cliente.setRg("11709456");
        cliente.setEmail("mario@mario.com");
        
        //Telefone telefone = new Telefone();
        //telefone.setTelefone("92984131579");
        
        //cliente.addTelefone(telefone);
        
        
        
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setNumero("00001");
        Calendar c = Calendar.getInstance();
        pedido.setData(c);
         
        Produto produto = new Produto();
        produto.setId(1l);
        produto.setNome("Jogo de Mesas");
        produto.setDescricao("Jogo de mesa mais 4 cadeiras");
        produto.setValor(25.00f);
        produto.setImagem("caminho da imagem");
        
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(10);
        itemPedido.setValorUnitario(25.00f);
        pedido.addItem(itemPedido);
        pedido.setValor(250f);
        
        EntityManager entityManager = factory.createEntityManager();        
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();                      
        entityManager.persist(pedido);
        transaction.commit();
        
        entityManager.close();
        factory.close();
        
    }
    
}
