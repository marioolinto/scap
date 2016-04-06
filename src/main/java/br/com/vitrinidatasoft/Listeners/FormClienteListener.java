/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitrinidatasoft.Listeners;

import br.com.vitrinidatasoft.model.Cliente;
import br.com.vitrinidatasoft.model.Telefone;
import br.com.vitrinidatasoft.service.ClienteService;
import br.com.vitrinidatasoft.view.FormCliente;
import br.com.vitrinidatasoft.utils.Constantes;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author mrhell
 */
public class FormClienteListener implements InterfaceFormListeners{
    
    private final FormCliente form;
    private DefaultListModel model;  
    private Cliente cliente;
            
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public FormClienteListener(FormCliente formCliente){
        this.form = formCliente; 
        attachListener();
    }

    /**
     *
     */
    @Override
    public void attachListener() {
        form.getBtnSalvar().addActionListener(this);
        form.getBtnNovo().addActionListener(this);
        form.getBtnEditar().addActionListener(this);
        form.getBtnCancelar().addActionListener(this);
        form.getBtnADD().addActionListener(this);        
    }
    
    /**
     * Reseta o formulários para o estado inicial
     */
    @Override
    public void resetFields(){
        form.getCmbTipoDeVia().setSelectedIndex(0);
        
        if (model != null && !model.isEmpty())
            model.clear();
        
        form.getTabbedPane().setSelectedIndex(0);
        Component[] components = form.getPanelData().getComponents();
        for (Component component : components){
            if (component instanceof JTextField){
                JTextField text = (JTextField)component;
                text.setText("");
            }
        }
    }   
    
    /**
     * Ativa os botões Salvar e Cancelar
     * e desativa os botões novo e editar
     */
    @Override
    public void turnButtonsOn(){
        form.getBtnSalvar().setEnabled(true);
        form.getBtnCancelar().setEnabled(true);
        form.getBtnNovo().setEnabled(
                !form.getBtnSalvar().isEnabled());
        form.getBtnEditar().setEnabled( 
                !form.getBtnCancelar().isEnabled());
    }
    
    /**
     * Ativa os botões Novo e Editar 
     * e dastiva os botões salvar e cancelar
     */
    @Override
    public void turnButtonsOf(){
        form.getBtnNovo().setEnabled(true);
        form.getBtnEditar().setEnabled(true);
        form.getBtnSalvar().setEnabled(
                !form.getBtnNovo().isEnabled());
        form.getBtnCancelar().setEnabled(
                !form.getBtnEditar().isEnabled());
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case (Constantes.ADD_TELEFONE):
                addTelefone();
                break;
            case (Constantes.NOVO):
                actionPerformedNovo();
                break;   
            case (Constantes.EDITAR):
                turnButtonsOn();
                System.out.println("Abrir lista de clientes do banco");
                break;
            case (Constantes.SALVAR):
                actionPerformedSalvar();
                break;  
            case (Constantes.CANCELAR):
                actionPerformedCancelar();
                break;
        }
    }

    /**
     *
     */
    @Override
    public void cancelAction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void disableEditTexts() {       
        Component[] components = form.getPanelData().getComponents();
        for (Component component : components){
            if (component instanceof JTextField){
                JTextField text = (JTextField)component;
                text.setEditable(false);
            }
        }
        
        components = form.getPanelEndereco().getComponents();
        for (Component component : components){
            if (component instanceof JTextField){
                JTextField text = (JTextField)component;
                text.setEditable(false);
            }
        }
        
        components = form.getPanelContato().getComponents();
        for (Component component : components){
            if (component instanceof JTextField){
                JTextField text = (JTextField)component;
                text.setEditable(false);
            }
        }
    }
    
    /**
     * Habilita todos JTextFiels dos Panels panelData, panelEndereco e p
     * panelContato
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
        
        components = form.getPanelEndereco().getComponents();
        for (Component component : components){
            if (component instanceof JTextField){
                JTextField text = (JTextField)component;
                text.setEditable(true);
            }
        }
        
        components = form.getPanelContato().getComponents();
        for (Component component : components){
            if (component instanceof JTextField){
                JTextField text = (JTextField)component;
                text.setEditable(true);
            }
        }        
    }
    
    public boolean checkFieldLists(String nome, String cpf){
        if (nome.equals("")){
            JOptionPane.showMessageDialog(form, "Campo Nome do Cliente obrigatório!", 
                    "Erro de Gravação", JOptionPane.ERROR_MESSAGE);
            return false;
        }                
        
        if (cpf.equals("")){
            JOptionPane.showMessageDialog(form, "Campo CPF ou CNPJ obrigatório!", 
                    "Erro de Gravação", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }    
    
    @Override
    public void actionPerformedNovo() {
        cliente = new Cliente();
        turnButtonsOn();
        enableEditTexts();         
    }

    @Override
    public void actionPerformedSalvar() {
        String nome = form.getTxtNome().getText();
        String rg = form.getTxtRG().getText();
        String cpg = form.getTxtCPF().getText();
        String endereco = form.getCmbTipoDeVia().getSelectedItem().toString() +
               form.getTxtNomeVia().getText() + ",Número: " + 
               form.getTxtNumero().getText() + ", Bairro: " + 
               form.getTxtBairro().getText() + ", Complemento: " +
               form.getTxtComplemento() + ", CEP: " +
               form.getTxtCEP().getText();      
        String email = form.getTxtEMail().getText();
        
        if (checkFieldLists(form.getTxtNome().getText(), 
           form.getTxtCPF().getText())){
           cliente.setNome(nome);
           cliente.setRg(rg);
           cliente.setCpf(cpg);
           cliente.setEndereco(endereco);
           
           ClienteService clietService = new ClienteService();
           clietService.persist(cliente);
           
           turnButtonsOf();
           resetFields();           
           disableEditTexts();           
        }
    }

    @Override
    public void actionPerfomedEditar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformedCancelar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void addTelefone() {
        model = (DefaultListModel) 
        form.getListaTelefones().getModel();
        String numero = form.getTxtNumeroTelefone().getText();        
        model.addElement(numero);        
        
        Telefone telefone = new Telefone();
        telefone.setTelefone(numero);        
        cliente.addTelefone(telefone);
        
        form.getTxtNumeroTelefone().setText("");
        form.getTxtNumeroTelefone().requestFocus();
    }
      
}
