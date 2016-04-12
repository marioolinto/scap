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
public class PedidoItensTableModel extends AbstractTableModel{
    
    private List<PedidoItem> itens;
    private final List<String> columns;
    
    public PedidoItensTableModel(List<PedidoItem> itens){
        columns = Arrays.asList("Produto", "Quantidade", "Valor");
        this.itens = itens;
    }

    @Override
    public String getColumnName(int column) {
        return columns.get(column); //To change body of generated methods, choose Tools | Templates.
    }        
    
    @Override
    public int getRowCount() {
       return itens.size();
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PedidoItem pedidoItem = itens.get(rowIndex);
        switch(columnIndex){
            case (0): return pedidoItem.getProduto().getDescricao();
            case (1): return pedidoItem.getQuantidade();
            case (2): return pedidoItem.getValor();
        }
        return null;
    }
    
}
