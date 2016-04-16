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
public class PedidoTableModel extends AbstractTableModel {
    
    private final List<Pedido> pedidos;
    private final List<String> columns;
    
    public PedidoTableModel(List<Pedido> pedidos){
        this.pedidos = pedidos;
        columns = Arrays.asList("Numero", "Data", "Endereco");
    }

    @Override
    public String getColumnName(int column) {
        return columns.get(column); 
    }
            
    @Override
    public int getRowCount() {
        return pedidos.size();
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pedido pedido = pedidos.get(rowIndex);
        switch (columnIndex){
            case 0: return pedido.getNumero();
            case 1: return pedido.getDataPedido().toString();
            case 2: return pedido.getEndereco();            
        }
        return null;
    }
    
}
