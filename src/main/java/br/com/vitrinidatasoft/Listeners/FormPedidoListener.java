/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitrinidatasoft.Listeners;

import br.com.vitrinidatasoft.model.Cliente;
import br.com.vitrinidatasoft.model.Pedido;
import br.com.vitrinidatasoft.utils.Constantes;
import br.com.vitrinidatasoft.view.DialogListaCliente;
import br.com.vitrinidatasoft.view.FormPedido;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

/**
 *
 * @author mrhell
 */
public class FormPedidoListener implements InterfaceFormListeners {
    
    private final FormPedido formPedido;
    private String numeroPedido;
    private String dataPedido;
    private final String BUSCAR_CLIENTE = "BUSCAR_CLIENTE";
    private final String BUSCAR_PRODUTO = "BUSCAR_PRODUTO";
    private final String INCLUIR_ITEM = "INCLUIR_ITEM";
    private final String EXCLUIR_ITEM = "EXCLUIR_ITEM";
    private Cliente cliente;
    
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public FormPedidoListener(FormPedido formPedido){
        this.formPedido = formPedido;
        attachListener();
    }

    @Override
    public void attachListener() {
        formPedido.getBtnBuscarCliente().addActionListener(this);
        formPedido.getBtnBuscarProduto().addActionListener(this);
        formPedido.getBtnCancelar().addActionListener(this);
        formPedido.getBtnEditar().addActionListener(this);
        formPedido.getBtnSalvar().addActionListener(this);
        formPedido.getBtnNovo().addActionListener(this);
        formPedido.getBtnIncluir().addActionListener(this);
        formPedido.getBtnExcluir().addActionListener(this);
    }

    @Override
    public void resetFields() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void turnButtonsOn() {
        formPedido.getBtnSalvar().setEnabled(true);
        formPedido.getBtnCancelar().setEnabled(true);
        formPedido.getBtnNovo().setEnabled(
                !formPedido.getBtnSalvar().isEnabled());
        formPedido.getBtnEditar().setEnabled( 
                !formPedido.getBtnCancelar().isEnabled());
    }

    @Override
    public void turnButtonsOf() {
        formPedido.getBtnNovo().setEnabled(true);
        formPedido.getBtnEditar().setEnabled(true);
        formPedido.getBtnSalvar().setEnabled(
                !formPedido.getBtnNovo().isEnabled());
        formPedido.getBtnCancelar().setEnabled(
                !formPedido.getBtnEditar().isEnabled());
    }

    @Override
    public void disableEditTexts() {
        Component[] components = formPedido.getPnlPedido().getComponents();        
        for (Component component : components){
            if (component instanceof JTextField){
                JTextField text = (JTextField)component;
                text.setEditable(false);
            }
            
            if (component instanceof JFormattedTextField){
                JFormattedTextField text = (JFormattedTextField)component;
                text.setEditable(false);
            }
        }
        
        formPedido.getChkRepetirEndereco().setEnabled(false);
        formPedido.getTxtEnderecoEntrega().setEditable(false);
        formPedido.getTxtObservacao().setEditable(false);
        formPedido.getTxtQuantidade().setEditable(false);
        formPedido.getTxtValor().setEditable(false);
        
    }

    @Override
    public void enableEditTexts() {
        Component[] components = formPedido.getPnlPedido().getComponents();        
        for (Component component : components){
            if (component instanceof JTextField){
                JTextField text = (JTextField)component;
                text.setEditable(true);
            }
            
            if (component instanceof JFormattedTextField){
                JFormattedTextField text = (JFormattedTextField)component;
                text.setEditable(true);
            }
        }
        
        formPedido.getChkRepetirEndereco().setEnabled(true);
        formPedido.getTxtEnderecoEntrega().setEditable(true);
        formPedido.getTxtObservacao().setEditable(true);
        formPedido.getTxtQuantidade().setEditable(true);
        formPedido.getTxtValor().setEditable(true);

    }

    @Override
    public void actionPerformedNovo() {
        Pedido pedido = new Pedido();
        numeroPedido = "54008";
        formPedido.getLblFieldNumeroPedido().setText(numeroPedido);
        dataPedido = getDate();
        formPedido.getLblFieldData().setText(dataPedido);
        formPedido.getTxtDataEntrega().setText(dataPedido);
        turnButtonsOn();
        enableEditTexts();
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
        switch (e.getActionCommand()){
            case (Constantes.NOVO):
                actionPerformedNovo();
                break;
            case (Constantes.EDITAR):
                actionPerfomedEditar();
                break;
            case (Constantes.SALVAR):
                actionPerformedSalvar();
                break;
            case (Constantes.CANCELAR):
                actionPerformedCancelar();
                break;
            case (BUSCAR_CLIENTE):
                break;
            case (BUSCAR_PRODUTO):
                break;
            case (INCLUIR_ITEM):
                break;
            case (EXCLUIR_ITEM):
                break;                
        }
            
    }
    
    public void buscarCliente(){       
        JDialog janela = new JDialog(formPedido);
        janela.setModal(true);
        janela.setVisible(true);
        
    }
    
    private String getDateTime() { 
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
        Date date = new Date(); 
        return dateFormat.format(date); 
    }
    
    private String getDate() { 
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
        Date date = new Date(); 
        return dateFormat.format(date); }
}
