package br.com.vitrinidatasoft.Listeners;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.vitrinidatasoft.view.FormCliente;
import br.com.vitrinidatasoft.view.FormPedido;
import br.com.vitrinidatasoft.view.FormProduto;
import br.com.vitrinidatasoft.view.FormUltimosPedidos;
import br.com.vitrinidatasoft.view.MainMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *
 * @author mrhell
 */
public class MainMenuListener implements ActionListener {
    
    private final MainMenu mainMenu;
    private final String OPEN_FORM_CLIENTE = "OPEN_FORM_CLIENTE";
    private final String OPEN_FORM_PRODUTO = "OPEN_FORM_PRODUTO";
    private final String OPEN_FORM_PEDIDO = "OPEN_FORM_PEDIDO";
    private final String OPEN_FORM_ULTIMOS_PEDIDOS = "OPEN_FORM_ULTIMOS_PEDIDOS"; 
    private final String EXIT_SYSTEM = "EXIT_SYSTEM";   
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MainMenuListener(MainMenu mainMenu){
       this.mainMenu = mainMenu;
       attachListener();
    }
    
    @SuppressWarnings("override")
    public void attachListener(){
        mainMenu.getBtnCliente().addActionListener(this);
        mainMenu.getBtnPedido().addActionListener(this);
        mainMenu.getBtnProduto().addActionListener(this);
        mainMenu.getBtnSair().addActionListener(this);
        mainMenu.getMenuItemCliente().addActionListener(this);
        mainMenu.getMenuItemProduto().addActionListener(this);
        mainMenu.getMenuItemPedido().addActionListener(this);
        mainMenu.getBtnUltimosPedidos().addActionListener(this);
    }
    
    private void showFormCliente(){               
        if (FormCliente.getInstance() == null){
            FormCliente.main(null);
        } else {
            System.out.println("Instancia Formulário de Clientes já aberto");            
        }   
          
    }
    
    private void showFormProduto(){
        if (FormProduto.getInstance() == null){
            FormProduto.main(null);
        } else {
            System.out.println("Instancia Formulário de Produtos já aberto");
        }
    }
    
    private void showFormPedido(){
        if (FormPedido.getInstance() == null){
            FormPedido.main(null);
        } else {
            System.out.println("Instancia Formulário de Pedidos já aberto");
        }
    }
    
    private void shutDown(){
        System.exit(0);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case OPEN_FORM_CLIENTE: 
                showFormCliente();
                break;
            case OPEN_FORM_PRODUTO:
                showFormProduto();
                break;
            case OPEN_FORM_PEDIDO:
                showFormPedido();
                break;
            case OPEN_FORM_ULTIMOS_PEDIDOS:
                showFormUltimosPedidos();
                break;
            case EXIT_SYSTEM:
                shutDown();
                break;
        }                    
    }

    private void showFormUltimosPedidos() {
        if (FormUltimosPedidos.getInstance() == null){
            FormUltimosPedidos.main(null);
        }
    }
}
