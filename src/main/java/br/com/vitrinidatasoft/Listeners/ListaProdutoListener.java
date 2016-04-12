/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitrinidatasoft.Listeners;

import br.com.vitrinidatasoft.model.Produto;
import br.com.vitrinidatasoft.model.ProdutoTableModel;
import br.com.vitrinidatasoft.service.ProdutoService;
import br.com.vitrinidatasoft.view.DialogListaProduto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
//import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author mrhell
 */
public class ListaProdutoListener implements ActionListener,
        ListSelectionListener, MouseListener{
    
    private final DialogListaProduto formListaProduto;
    private final String FILTER = "FILTER";
    private ProdutoTableModel produtoTableModel;
    private ProdutoService produtoService;
    private Produto produto;
    
    public ListaProdutoListener(DialogListaProduto formListaProduto){
        this.formListaProduto = formListaProduto;
        attachListner();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void attachListner() {
        formListaProduto.getTblProduto().getSelectionModel().
                addListSelectionListener(this);
        formListaProduto.getTblProduto().addMouseListener(this);
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
                new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.RIGHT);
        formListaProduto.getTblProduto().getColumnModel()
                .getColumn(3).setCellRenderer(renderer);
                
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedRow = formListaProduto.getTblProduto().getSelectedRow();
        produto = produtoTableModel.getProdutos().get(selectedRow);
        formListaProduto.setProduto(produto);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2){
           formListaProduto.dispose();
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
}
