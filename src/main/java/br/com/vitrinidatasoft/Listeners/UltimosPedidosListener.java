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
import br.com.vitrinidatasoft.relatorios.RelatorioPedido;
import br.com.vitrinidatasoft.service.PedidoService;
import br.com.vitrinidatasoft.view.DialogListaCliente;
import br.com.vitrinidatasoft.view.FormUltimosPedidos;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author mrhell
 */
public class UltimosPedidosListener implements InterfaceFormListeners, 
        ListSelectionListener, MouseListener{
    
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
        formUltimosPedidos.getTblPedidos().addMouseListener(this);
    }

    @Override
    public void resetFields() {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void turnButtonsOn() {
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void turnButtonsOf() {
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void disableEditTexts() {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void enableEditTexts() {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformedNovo() {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformedSalvar() {
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerfomedEditar() {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformedCancelar() {
        //To change body of generated methods, choose Tools | Templates.
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

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2){
            RelatorioPedido relatorioPedido = new RelatorioPedido();
            PedidoService pedidoService = new PedidoService();                      
            List<Pedido> pedidos = pedidoService.findByNumero(pedido.getNumero());    
            try{
                if (pedidos.size() > 0)
                    relatorioPedido.gerarRelatorio(pedidos);
                else 
                    JOptionPane.showMessageDialog(formUltimosPedidos, 
                            "Pedido não encontrado");
            }catch(JRException exception){
                JOptionPane.showMessageDialog(formUltimosPedidos, 
                        "Erro " + exception.getMessage());                 
            }   
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
         //To change body of generated methods, choose Tools | Templates.
    }
}
