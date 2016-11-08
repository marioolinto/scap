/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitrinidatasoft.Listeners;

import br.com.vitrinidatasoft.model.Cliente;
import br.com.vitrinidatasoft.model.ClienteTableModel;
import br.com.vitrinidatasoft.service.ClienteService;
import br.com.vitrinidatasoft.view.DialogListaCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author mrhell
 */
public class ListaClienteListener implements ActionListener, 
        ListSelectionListener, MouseListener{
    
    private final DialogListaCliente dialogListaCliente;
    private final String FILTER = "FILTER";
    private Cliente cliente;
    private ClienteTableModel clienteTableModel;
    private ClienteService clienteService;
    
    public ListaClienteListener(DialogListaCliente dialogListaCliente){
        this.dialogListaCliente = dialogListaCliente;        
        attachListener();
    }
            
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(FILTER)){
            actionPerformedFilter();
        }
    }
    
    public void loadListaCliente(){
        clienteService = new ClienteService();
        List<Cliente> clientes = clienteService.findAll();
        clienteTableModel = new ClienteTableModel(clientes);       
        
        dialogListaCliente.getTblCliente().setModel(clienteTableModel);
        dialogListaCliente.getTblCliente().getColumnModel().
                getColumn(0).setPreferredWidth(300);
        dialogListaCliente.getTblCliente().getColumnModel().
                getColumn(1).setPreferredWidth(100);           
        dialogListaCliente.getTblCliente().getColumnModel().
                getColumn(2).setPreferredWidth(300);
    }
    
    private void attachListener() {
        dialogListaCliente.getBtnFilter().addActionListener(this);
        dialogListaCliente.getTblCliente().getSelectionModel()
                .addListSelectionListener(this);  
        dialogListaCliente.getTblCliente().addMouseListener(this);
    }

    private void actionPerformedFilter() {
        Integer column = 0;
        
        if (dialogListaCliente.getRdbNome().isSelected())
            column = 0;
        else if(dialogListaCliente.getRdbTelefone().isSelected())             
            column = 1;
        else
            column = 2;
        
        String search = dialogListaCliente.getTxtSearch().getText();                
        filter(search, column);
        
        
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
       int selectedRow = dialogListaCliente.getTblCliente().getSelectedRow();
       cliente = clienteTableModel.getClientes().get(selectedRow);
       dialogListaCliente.setCliente(cliente);       
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       if (e.getClickCount() == 2){
           dialogListaCliente.dispose();
       }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        //do nothing
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //do nothing
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //do nothing
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //do nothing
    }

    private void filter(String search, Integer column) {
        RowFilter filter = RowFilter.regexFilter(search, column);
        TableRowSorter<ClienteTableModel> sorter = 
                new TableRowSorter(clienteTableModel);
        sorter.setRowFilter(filter);
        dialogListaCliente.getTblCliente().setRowSorter(sorter);
    }
    
    
}
