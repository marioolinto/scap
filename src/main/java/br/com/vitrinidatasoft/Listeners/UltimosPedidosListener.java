/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitrinidatasoft.Listeners;

import br.com.vitrinidatasoft.model.Cliente;
import br.com.vitrinidatasoft.view.DialogListaCliente;
import br.com.vitrinidatasoft.view.FormUltimosPedidos;
import java.awt.event.ActionEvent;

/**
 *
 * @author mrhell
 */
public class UltimosPedidosListener implements InterfaceFormListeners{
    
    private final FormUltimosPedidos formUltimosPedidos;
    private final String BUSCAR_CLIENTE = "BUSCAR_CLIENTE";
    private Cliente cliente;
    
    public UltimosPedidosListener(FormUltimosPedidos formUltimosPedidos){
        this.formUltimosPedidos = formUltimosPedidos;
    }

    @Override
    public void attachListener() {
        formUltimosPedidos.getBtnBuscarCliente().addActionListener(this);
    }

    @Override
    public void resetFields() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void turnButtonsOn() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void turnButtonsOf() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void disableEditTexts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void enableEditTexts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformedNovo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformedSalvar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerfomedEditar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformedCancelar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case (BUSCAR_CLIENTE):
                buscarCliente();
                break;
        }
    }

    private void buscarCliente() {
 DialogListaCliente dialogListaCliente = 
        new DialogListaCliente(formUltimosPedidos, true);
        dialogListaCliente.setLocationRelativeTo(formUltimosPedidos);
        dialogListaCliente.setVisible(true);
        cliente = dialogListaCliente.getCliente();
        
        fillDataCliente();
    }

    private void fillDataCliente() {
        if (cliente != null){
            formUltimosPedidos.getLblFieldNome().setText(cliente.getNome());
            formUltimosPedidos.getLblFieldCpf().setText(cliente.getCpf());            
        }
        
    }
    
}
