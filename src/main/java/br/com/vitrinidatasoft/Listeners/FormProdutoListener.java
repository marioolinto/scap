/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitrinidatasoft.Listeners;

import br.com.vitrinidatasoft.model.Produto;
import br.com.vitrinidatasoft.service.ProdutoService;
import br.com.vitrinidatasoft.utils.Constantes;
import br.com.vitrinidatasoft.view.DialogListaProduto;
import br.com.vitrinidatasoft.view.FormProduto;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author mrhell
 */
public class FormProdutoListener implements InterfaceFormListeners {
    
    private final FormProduto form;
    private Produto produto;
    private String nome;
    private String descricao;
    private Float valor;
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public FormProdutoListener(FormProduto formProduto){
        this.form = formProduto; 
        attachListener();
    }
    
    /**
     * Vincula o listener aos botões
     *
     */
    @Override
    public void attachListener() {
        form.getBtnCancelar().addActionListener(this);        
        form.getBtnSalvar().addActionListener(this);
        form.getBtnEditar().addActionListener(this);
        form.getBtnNovo().addActionListener(this);
        form.getBtnCarregarImagem().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case (Constantes.NOVO) :                
                actionPerformedNovo();
                break;
            case (Constantes.EDITAR):
                actionPerfomedEditar();
                break;
            case (Constantes.SALVAR):
                actionPerformedSalvar();
                break;
            case (Constantes.CANCELAR):
                actionPerformedCancelar();
                break;
            case (Constantes.CARREGAR_IMAGEM):
                actionPerformedAddImagem();
                break;
    }   }
    
    public boolean chekFieldLists(String nome, String descricao, Float valor){
         if (nome.equals("")){
           JOptionPane.showMessageDialog(form,  
                   "Nome do produto obrigatório", "ERRO", 
                   JOptionPane.ERROR_MESSAGE);           
            form.getTxtNome().requestFocus();
            return false;
        }
        
        if (descricao.equals("")){
           JOptionPane.showMessageDialog(form,  
                   "Desscrição do produto obrigatório", "ERRO", 
                   JOptionPane.ERROR_MESSAGE);           
            form.getTxtNome().requestFocus();
            return false;
        }   
        
        return true;
    }
    
    @Override
    public void resetFields() {        
        Component[] components = form.getPanelData().getComponents();
        for (Component component : components){
            if (component instanceof JTextField){
                JTextField text = (JTextField)component;
                text.setText("");
            }
        }
    }

    /**
     * Habilita os botões salvar e cancelar
     * e desabilita os botões novo e editar
     */
    @Override
    public void turnButtonsOn() {
        form.getBtnSalvar().setEnabled(true);
        form.getBtnCancelar().setEnabled(true);
        form.getBtnNovo().setEnabled(
                !form.getBtnSalvar().isEnabled());
        form.getBtnEditar().setEnabled( 
                !form.getBtnCancelar().isEnabled());
    }

    /**
     * Habilita os botões novo e editar 
     * desablita os botões salvar e cancelar
     */
    @Override
    public void turnButtonsOf() {
        form.getBtnNovo().setEnabled(true);
        form.getBtnEditar().setEnabled(true);
        form.getBtnSalvar().setEnabled(
                !form.getBtnNovo().isEnabled());
        form.getBtnCancelar().setEnabled(
                !form.getBtnEditar().isEnabled());
    }

    /**
     * Disabilita todos os JTextFields para edição 
     */
    @Override
    public void disableEditTexts() {
        Component[] components = form.getPanelData().getComponents();
        for (Component component : components){
            if (component instanceof JTextField){
                JTextField text = (JTextField)component;
                text.setEditable(false);
            }
        }
    }
    
    /**
     * Habilita todos os JTextFields para edição
     */
    @Override
    public void enableEditTexts() {
        Component[] components = form.getPanelData().getComponents();
        for (Component component : components){
            if (component instanceof JTextField){
                JTextField text = (JTextField)component;
                text.setEditable(true);
            }
        }
    }
        
    @Override
    public void actionPerformedNovo() {
        produto = new Produto();  
        turnButtonsOn();
        enableEditTexts();        
        form.getTxtNome().requestFocus();
    }

    @Override
    public void actionPerformedSalvar() {
        nome = form.getTxtNome().getText();
        descricao = form.getTxtDescricao().getText();
        String valorString = form.getTxtValor().getText();
        valorString = valorString.replace(',', '.');
        valor = Float.parseFloat(valorString);
        
        if (chekFieldLists(nome, descricao, valor)){            
            produto.setNome(nome);
            produto.setDescricao(descricao);
            produto.setValor(valor);
            ProdutoService produtoService = new ProdutoService();
            produtoService.persist(produto);
            resetFields();
            disableEditTexts();
            turnButtonsOf();
            produto = null;
        }  
    }

    @Override
    public void actionPerfomedEditar() {
        DialogListaProduto.main(null);
    }

    @Override
    public void actionPerformedCancelar() {
        resetFields();
        disableEditTexts();
        turnButtonsOf();
        produto = null;
    }

    private void actionPerformedAddImagem() {
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(null);
        File file = fc.getSelectedFile();
        String filename = file.getAbsolutePath();
        ImageIcon picture = new ImageIcon(filename);                
        Image image = picture.getImage();            
        Image newImage = image.getScaledInstance(200, 200, 
                java.awt.Image.SCALE_SMOOTH);        
        picture = new ImageIcon(newImage); 
        form.getLblImagem().setIcon(picture);
    }
    
    
}
