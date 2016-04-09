/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitrinidatasoft.model;

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
    
    public ClienteTableModel(List<Cliente> clientes){
        this.clientes = clientes;
        columns = Arrays.asList("ID", "Nome", "CPF", "e-mail");
    }

    @Override
    public String getColumnName(int column) {
        return columns.get(column); //To change body of generated methods, choose Tools | Templates.
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
            case(0): return cliente.getId();
            case(1): return cliente.getNome();
            case(2): return cliente.getCpf();
            case(3): return cliente.getEmail();
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
