/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitrinidatasoft.model;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author mrhell
 */
public class PedidoTableModel extends AbstractTableModel {
    
    private List<Pedido> pedidos;
    private final List<String> columns;
    
    public PedidoTableModel(List<Pedido> pedidos){
        this.pedidos = pedidos;
        columns = Arrays.asList("Numero", "Data");
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

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pedido pedido = pedidos.get(rowIndex);
        switch (columnIndex){
            case 0: return pedido.getNumero();
            case 1: 
                try{
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");                    
                    Calendar c = pedido.getDataPedido();
                    String dataPedido = format.format(c.getTime());
                    return dataPedido;
                }catch(Exception e){
                    System.out.println("Erro "+e.getMessage() );
                }
        }
        return null;
    }
    
}
