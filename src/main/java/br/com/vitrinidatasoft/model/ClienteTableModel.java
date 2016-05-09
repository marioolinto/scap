/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitrinidatasoft.model;

import br.com.vitrinidatasoft.service.ClienteService;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author mrhell
 */
public class ClienteTableModel extends AbstractTableModel{
    
    List<Cliente> clientes;
    List<String> columns;
    ClienteService service; 
    
    
    public ClienteTableModel(List<Cliente> clientes){
        this.clientes = clientes;        
        columns = Arrays.asList("Nome", "Telefone", "e-mail");
        service = new ClienteService(); 
    }

    @Override
    public String getColumnName(int column) {
        return columns.get(column); 
    }        

    @Override
    public int getRowCount() {
        return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return columns.size();                
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente cliente = clientes.get(rowIndex);
        switch(columnIndex){
            case(0): return cliente.getNome();
            case(1): String telefone = service.findMainContact(cliente); 
                     return telefone;
            case(2): return cliente.getEmail();
        }
                
        return null;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }            
}
