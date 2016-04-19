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
import br.com.vitrinidatasoft.view.DialogListaCliente;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author mrhell
 */
public class FormClienteListener implements InterfaceFormListeners, 
        FocusListener{
    
    private final FormCliente form;   
    private Cliente cliente;
    private Telefone telefone;
    private DefaultListModel listModel;
    private List<Telefone> telefones;
            
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public FormClienteListener(FormCliente formCliente){
        this.form = formCliente;
        listModel = new DefaultListModel();
        form.getListaTelefones().setModel(listModel);  
        actionPerformedNovo();
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
        form.getTxtCPF().addFocusListener(this);
    }
    
    /**
     * Reseta o formulários para o estado inicial
     */
    @Override
    public void resetFields(){
        
        if (listModel != null && !listModel.isEmpty())
            listModel.clear();
        
        form.getTxtEndereco().setText("");
        form.getTabbedPane().setSelectedIndex(0);
        Component[] components = form.getPanelData().getComponents();
        for (Component component : components){
            if (component instanceof JTextField){
                JTextField text = (JTextField)component;
                text.setText("");
            }
        }
        
        components = form.getPanelEndereco().getComponents();
        for (Component component : components){
            if (component instanceof JTextField){
                JTextField text = (JTextField)component;
                text.setText("");
            }
        }
        
        components = form.getPanelContato().getComponents();
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
                actionPerfomedEditar();
                break;
            case (Constantes.SALVAR):
                actionPerformedSalvar();
                break;  
            case (Constantes.CANCELAR):
                actionPerformedCancelar();
                break;
        }
    }
    
    @Override
    public void disableEditTexts() {
        form.getTxtEndereco().setEditable(false);
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
        form.getTxtEndereco().setEditable(true);
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
            JOptionPane.showMessageDialog(form, 
                    "Campo Nome do Cliente obrigatório!", 
                    "Erro de Gravação", JOptionPane.ERROR_MESSAGE);
            return false;
        }                
        
        if (cpf.equals("")){
            JOptionPane.showMessageDialog(form, 
                    "Campo CPF ou CNPJ obrigatório!", 
                    "Erro de Gravação", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }    
    
    @Override
    public void actionPerformedNovo() {
        cliente = new Cliente();
        turnButtonsOn();
        //enableEditTexts();        
    }

    @Override
    public void actionPerformedSalvar() {
        String nome = form.getTxtNome().getText();
        String rg = form.getTxtRG().getText();
        String cpg = form.getTxtCPF().getText();
        String endereco = form.getTxtEndereco().getText();
        String email = form.getTxtEMail().getText();
        
        if (checkFieldLists(form.getTxtNome().getText(), 
           form.getTxtCPF().getText())){
           cliente.setNome(nome);
           cliente.setRg(rg);
           cliente.setCpf(cpg);
           cliente.setEndereco(endereco);
           cliente.setEmail(email);
           
           ClienteService clietService = new ClienteService();
           
           if (cliente.getId() == null)
                clietService.persist(cliente);
           else
               clietService.update(cliente);
           
           turnButtonsOf();
           resetFields();           
           disableEditTexts();
           cliente = null;
           
        }
    }

    @Override
    public void actionPerfomedEditar() {
        DialogListaCliente dialogListaCliente = new 
            DialogListaCliente(form, true);
        dialogListaCliente.setLocationRelativeTo(form);
        dialogListaCliente.setVisible(true);
        cliente = dialogListaCliente.getCliente();
        
        ClienteService clienteService = new ClienteService();
        telefones = clienteService.findAllContacts(cliente);
        
        fillData();
            
    }

    @Override
    public void actionPerformedCancelar() {
        resetFields();
        //disableEditTexts();
        turnButtonsOf();
        telefone = null;
        cliente = null;        
    }

    private void addTelefone() {
        String numero = form.getTxtNumeroTelefone().getText();        
        if (!numero.equals("")){ 
            listModel = (DefaultListModel) 
            form.getListaTelefones().getModel();

            listModel.addElement(numero);        

            telefone = new Telefone();
            telefone.setTelefone(numero);        
            cliente.addTelefone(telefone);

            form.getTxtNumeroTelefone().setText("");
            form.getTxtNumeroTelefone().requestFocus();
        }
    }

    private void fillData() {
        if (cliente != null){            
            form.getTxtNome().setText(cliente.getNome());
            form.getTxtRG().setText(cliente.getRg());
            form.getTxtCPF().setText(cliente.getCpf());
            form.getTxtEndereco().setText(cliente.getEndereco());
            form.getTxtEMail().setText(cliente.getEmail());
            
            
            listModel = (DefaultListModel)form.getListaTelefones().getModel();
            
            for (int i=0; i < telefones.size(); i++){
                telefone = telefones.get(i);
                listModel.addElement(telefone.getNumero());
            }                        
            enableEditTexts();
            turnButtonsOn();
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        //do nothing;
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getComponent().equals(form.getTxtCPF())){
            System.out.println("É o cpf");
        }
    }
}
