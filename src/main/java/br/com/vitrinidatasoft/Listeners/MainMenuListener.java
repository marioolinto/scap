package br.com.vitrinidatasoft.Listeners;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.vitrinidatasoft.view.FormCliente;
import br.com.vitrinidatasoft.view.FormPedido;
import br.com.vitrinidatasoft.view.FormProduto;
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
    private final String EXIT_SYSTEM = "EXIT_SYSTEM";
//    private FormCliente formCliente;
//    private FormProduto formProduto;
//    private FormPedido formPedido; 
   
    
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
    }
    
    private void showFormCliente(){       
       FormCliente.main(null);
          
    }
    
    private void showFormProduto(){
        FormProduto.main(null);
    }
    
    private void showFormPedido(){
       FormPedido.main(null);
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
            case EXIT_SYSTEM:
                shutDown();
                break;
        }    
        
        System.out.println(e);
    }
}
