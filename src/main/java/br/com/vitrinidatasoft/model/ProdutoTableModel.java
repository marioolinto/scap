/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitrinidatasoft.model;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author mrhell
 */
public class ProdutoTableModel extends AbstractTableModel {
    private List<Produto> produtos;
    private final List<String> columns;
    
    public ProdutoTableModel(List<Produto> produtos){
        this.produtos = produtos;
        columns = Arrays.asList("ID", "Produto", "Descricao", "Valor");
    }

    @Override
    public String getColumnName(int column) {
        return columns.get(column);
    }        
    
    @Override
    public int getRowCount() {
        return produtos.size();
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Produto produto = produtos.get(rowIndex);
        switch (columnIndex){
            case (0): return produto.getId();
            case (1): return produto.getNome();
            case (2): return produto.getDescricao();
            case (3): 
                String valor = 
                NumberFormat.getCurrencyInstance().format(produto.getValor());
                return valor;                
        }
        return null;
    }  
    
    public  List<Produto> getProdutos(){
        return produtos;
    } 
    
    public void setProdutos (List<Produto> produtos){
        this.produtos = produtos;
    }
}
