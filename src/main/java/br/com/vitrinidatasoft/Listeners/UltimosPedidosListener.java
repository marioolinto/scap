/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitrinidatasoft.Listeners;

import br.com.vitrinidatasoft.model.Cliente;
import br.com.vitrinidatasoft.model.Pedido;
import br.com.vitrinidatasoft.model.PedidoItem;
import br.com.vitrinidatasoft.model.PedidoItensTableModel;
import br.com.vitrinidatasoft.model.PedidoTableModel;
import br.com.vitrinidatasoft.service.PedidoService;
import br.com.vitrinidatasoft.view.DialogListaCliente;
import br.com.vitrinidatasoft.view.FormUltimosPedidos;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author mrhell
 */
public class UltimosPedidosListener implements InterfaceFormListeners, 
        ListSelectionListener{
    
    private final FormUltimosPedidos formUltimosPedidos;
    private PedidoTableModel pedidoTableModel;
    private PedidoItensTableModel itensTableModel;
    private final String BUSCAR_CLIENTE = "BUSCAR_CLIENTE";
    private Cliente cliente;
    private Pedido pedido;
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public UltimosPedidosListener(FormUltimosPedidos formUltimosPedidos){
        this.formUltimosPedidos = formUltimosPedidos;
        attachListener();
    }

    @Override
    public void attachListener() {
        formUltimosPedidos.getBtnBuscarCliente().addActionListener(this);
        formUltimosPedidos.getTblPedidos().getSelectionModel().
                addListSelectionListener(this);
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
        System.out.println(e);
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
            loadPedidos(cliente);
        }
        
    }
    
    public void loadPedidos(Cliente cliente){
        PedidoService pedidoService = new PedidoService();
        List<Pedido> pedidos = pedidoService.findByClient(cliente);
        pedidoTableModel = new PedidoTableModel(pedidos);
        formUltimosPedidos.getTblPedidos().setModel(pedidoTableModel);
        
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {                
        int selectedrRow = formUltimosPedidos.getTblPedidos().getSelectedRow();
        pedido = (Pedido)pedidoTableModel.getPedidos().get(selectedrRow); 
        loadItensPedido(pedido);
    }

    private void loadItensPedido(Pedido pedido) {
        PedidoService pedidoService = new PedidoService();
        List<PedidoItem> itens = pedidoService.findItensByPedido(pedido);
        itensTableModel = new PedidoItensTableModel(itens);
        formUltimosPedidos.getTblPedidoItens().setModel(itensTableModel);
        formUltimosPedidos.getTblPedidoItens().getColumnModel().getColumn(0).
                setPreferredWidth(280);
        
         DefaultTableCellRenderer renderer = 
                new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.RIGHT);
        formUltimosPedidos.getTblPedidoItens().getColumnModel().getColumn(1).
                setCellRenderer(renderer);
        formUltimosPedidos.getTblPedidoItens().getColumnModel().getColumn(1).
                setPreferredWidth(35);
        formUltimosPedidos.getTblPedidoItens().getColumnModel().getColumn(2).
                setCellRenderer(renderer);
        formUltimosPedidos.getTblPedidoItens().getColumnModel().getColumn(2).
                setPreferredWidth(25);
    }
}
