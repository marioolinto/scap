/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitrinidatasoft.Listeners;

import br.com.vitrinidatasoft.model.Produto;
import br.com.vitrinidatasoft.model.ProdutoTableModel;
import br.com.vitrinidatasoft.service.ProdutoService;
import br.com.vitrinidatasoft.view.FormListaProduto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import sun.swing.table.DefaultTableCellHeaderRenderer;

/**
 *
 * @author mrhell
 */
public class ListaProdutoListener implements ActionListener{
    
    private final FormListaProduto formListaProduto;
    private final String FILTER = "FILTER";
    private ProdutoTableModel produtoTableModel;
    private ProdutoService produtoService;
    
    public ListaProdutoListener(FormListaProduto formListaProduto){
        this.formListaProduto = formListaProduto;
        attachListner();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void attachListner() {
        //formListaProduto.getBt
    }
    
    public void loadListaProduto(){
        produtoService = new ProdutoService();
        List<Produto> produtos = produtoService.findAll();
        produtoTableModel = new ProdutoTableModel(produtos);
        
        formListaProduto.getTblProduto().setModel(produtoTableModel);
        formListaProduto.getTblProduto().getColumnModel()
                .getColumn(0).setPreferredWidth(10);
        formListaProduto.getTblProduto().getColumnModel()
                .getColumn(1).setPreferredWidth(150);
        formListaProduto.getTblProduto().getColumnModel()
                .getColumn(2).setPreferredWidth(300);
        formListaProduto.getTblProduto().getColumnModel()
                .getColumn(3).setPreferredWidth(20);
        
        DefaultTableCellRenderer renderer = 
                new DefaultTableCellHeaderRenderer();
        renderer.setHorizontalAlignment(SwingConstants.RIGHT);
        formListaProduto.getTblProduto().getColumnModel()
                .getColumn(3).setCellRenderer(renderer);
                
    }
}
