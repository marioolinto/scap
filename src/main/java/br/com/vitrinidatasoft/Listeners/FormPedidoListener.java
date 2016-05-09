/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitrinidatasoft.Listeners;

import br.com.vitrinidatasoft.model.Cliente;
import br.com.vitrinidatasoft.model.Pedido;
import br.com.vitrinidatasoft.model.PedidoItem;
import br.com.vitrinidatasoft.model.PedidoItensTableModel;
import br.com.vitrinidatasoft.model.Produto;
import br.com.vitrinidatasoft.service.PedidoService;
import br.com.vitrinidatasoft.utils.Constantes;
import br.com.vitrinidatasoft.view.DialogListaCliente;
import br.com.vitrinidatasoft.view.DialogListaProduto;
import br.com.vitrinidatasoft.view.FormPedido;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import net.sf.jasperreports.engine.JasperPrint;


/**
 *
 * @author mrhell
 */
public class FormPedidoListener implements InterfaceFormListeners, FocusListener {
    
    private final FormPedido formPedido;
    private String numeroPedido;
    private String dataPedido;
    private final String BUSCAR_CLIENTE = "BUSCAR_CLIENTE";
    private final String BUSCAR_PRODUTO = "BUSCAR_PRODUTO";
    private final String INCLUIR_ITEM = "INCLUIR_ITEM";
    private final String EXCLUIR_ITEM = "EXCLUIR_ITEM";
    private final String REPETE_ENDERECO = "REPETE_ENDERECO";
    private Cliente cliente;
    private Produto produto;
    private Pedido pedido;
    private PedidoItem item;
    private PedidoService pedidoService;
    private List<PedidoItem> itens;
    private PedidoItensTableModel model;
    private Integer quantidadeTotal;
    private String valorItem;
    private Float valorPedido;
    private Float valorTotal;
    private Float desconto;
            
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public FormPedidoListener(FormPedido formPedido){
        this.formPedido = formPedido;
        itens = new ArrayList<>();
        quantidadeTotal = 0;
        valorTotal = 0f;
        valorPedido = 0f;
        desconto = 0f;
        attachListener();
    }

    @Override
    public void attachListener() {
        formPedido.getBtnBuscarCliente().addActionListener(this);
        formPedido.getBtnBuscarProduto().addActionListener(this);
        formPedido.getBtnCancelar().addActionListener(this);
        formPedido.getBtnEditar().addActionListener(this);
        formPedido.getBtnSalvar().addActionListener(this);
        formPedido.getBtnNovo().addActionListener(this);
        formPedido.getBtnIncluir().addActionListener(this);
        formPedido.getBtnExcluir().addActionListener(this);
        formPedido.getChkRepetirEndereco().addActionListener(this);
        formPedido.getTxtDesconto().addFocusListener(this);
    }

    @Override
    public void resetFields() {
        formPedido.getLblFieldNumeroPedido().setText("");
        formPedido.getLblFieldData().setText("__/__/____");
        formPedido.getTxtDataEntrega().setText("__/__/____");
        formPedido.getLblFieldNome().setText("--");        
        formPedido.getLblFieldEndereco().setText("--");
        formPedido.getLblFieldCpf().setText("--");
        formPedido.getLblQuantidadeTotal().setText("0,00");        
        formPedido.getLblTotalPedido().setText("0,00");
        formPedido.getLblValorTotal().setText("0,00");
        formPedido.getTxtQuantidade().setText("");
        formPedido.getTxtValor().setText("");
        formPedido.getTxtProduto().setText("");
        formPedido.getTxtEnderecoEntrega().setText("");
        formPedido.getTxtObservacao().setText("");
        formPedido.getTxtDesconto().setText("0,00");
                
        itens.clear();
        model = new PedidoItensTableModel(itens);
        formPedido.getTblItensPedido().setModel(model);
        
        Component[] components = formPedido.getPnlPedido().getComponents();
        for (Component component : components){           
            if (component instanceof JTextField){
                JTextField text = (JTextField)component;
                text.setText("");
            }
                                 
        }
    }

    @Override
    public void turnButtonsOn() {
        formPedido.getBtnSalvar().setEnabled(true);
        formPedido.getBtnCancelar().setEnabled(true);
        formPedido.getBtnNovo().setEnabled(
                !formPedido.getBtnSalvar().isEnabled());
        formPedido.getBtnEditar().setEnabled( 
                !formPedido.getBtnCancelar().isEnabled());
    }

    @Override
    public void turnButtonsOf() {
        formPedido.getBtnNovo().setEnabled(true);
        formPedido.getBtnEditar().setEnabled(true);
        formPedido.getBtnSalvar().setEnabled(
                !formPedido.getBtnNovo().isEnabled());
        formPedido.getBtnCancelar().setEnabled(
                !formPedido.getBtnEditar().isEnabled());
    }

    @Override
    public void disableEditTexts() {
        Component[] components = formPedido.getPnlPedido().getComponents();        
        for (Component component : components){
            if (component instanceof JTextField){
                JTextField text = (JTextField)component;
                text.setEditable(false);
            }
            
            if (component instanceof JFormattedTextField){
                JFormattedTextField text = (JFormattedTextField)component;
                text.setEditable(false);
            }
        }
        
        formPedido.getChkRepetirEndereco().setEnabled(false);
        formPedido.getTxtEnderecoEntrega().setEditable(false);
        formPedido.getTxtObservacao().setEditable(false);
        formPedido.getTxtQuantidade().setEditable(false);
        formPedido.getTxtValor().setEditable(false);
        
    }

    @Override
    public void enableEditTexts() {
        Component[] components = formPedido.getPnlPedido().getComponents();        
        for (Component component : components){
            if (component instanceof JTextField){
                JTextField text = (JTextField)component;
                text.setEditable(true);
            }
            
            if (component instanceof JFormattedTextField){
                JFormattedTextField text = (JFormattedTextField)component;
                text.setEditable(true);
            }
        }
        
        formPedido.getChkRepetirEndereco().setEnabled(true);
        formPedido.getTxtEnderecoEntrega().setEditable(true);
        formPedido.getTxtObservacao().setEditable(true);
        formPedido.getTxtQuantidade().setEditable(true);
        formPedido.getTxtValor().setEditable(true);

    }

    @Override
    public void actionPerformedNovo() {
        pedido = new Pedido();
        dataPedido = getDate();
        
        Double numeroRandom = Math.random();                                           
        String numeroInicial = numeroRandom.toString();
        numeroRandom = Math.random();                
        String numeroFinal = numeroRandom.toString();
        
        numeroPedido = numeroInicial.substring(2,4) + 
                dataPedido.substring(1, 2) + numeroFinal.substring(2,4);         
        formPedido.getLblFieldNumeroPedido().setText(numeroPedido);        
        formPedido.getLblFieldData().setText(dataPedido);
        formPedido.getTxtDataEntrega().setText(dataPedido);
        turnButtonsOn();
        enableEditTexts();
    }

    @Override
    public void actionPerformedSalvar() {
       pedido.setCliente(cliente);
       
       SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
       dataPedido = formPedido.getLblFieldData().getText();
       String dataEntrega = formPedido.getTxtDataEntrega().getText();       
       pedido.setNumero(numeroPedido);
       Calendar calendar = Calendar.getInstance();
       try{
          calendar.setTime(format.parse(dataPedido));
          pedido.setDataPedido(calendar); 
          calendar.setTime(format.parse(dataEntrega)); 
          pedido.setDataEntrega(calendar);                    
       }catch(Exception e){
           System.out.println(e);
       }        
       
       pedido.setEndereco(formPedido.getTxtEnderecoEntrega().getText());
       pedido.setObservacao(formPedido.getTxtObservacao().getText());
       
       if (!"".equals(formPedido.getTxtDesconto().getText())){
            //desconto = Float.parseFloat(formPedido.getTxtDesconto().getText());       
            pedido.setDesconto(desconto);
       }
       
       pedido.setValor(valorTotal);
       pedidoService = new PedidoService();
       pedidoService.persist(pedido);
       
       resetFields();
       turnButtonsOf();
    }

    @Override
    public void actionPerfomedEditar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformedCancelar() {
        resetFields();
        turnButtonsOf();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
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
            case (BUSCAR_CLIENTE):
                buscarCliente();
                break;
            case (BUSCAR_PRODUTO):
                buscarProduto();
                break;
            case (INCLUIR_ITEM):
                incluirItem();
                break;
            case (EXCLUIR_ITEM):
                excluirItem();
                break; 
            case (REPETE_ENDERECO):
                repeteEndereco();
                break;
        }
            
    }
    
    public void buscarCliente(){       
        DialogListaCliente dialogListaCliente = 
                new DialogListaCliente(formPedido, true);
        dialogListaCliente.setLocationRelativeTo(formPedido);
        dialogListaCliente.setVisible(true);
        cliente = dialogListaCliente.getCliente();
        
        fillDataCliente();
        
    }
    
    private String getDateTime() { 
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
        Date date = new Date(); 
        return dateFormat.format(date); 
    }
    
    private String getDate() { 
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
        Date date = new Date(); 
        return dateFormat.format(date); }

    private void fillDataCliente() {
        if (cliente != null){
            formPedido.getLblFieldNome().setText(cliente.getNome());
            formPedido.getLblFieldCpf().setText(cliente.getCpf());
            String endereco;
            if (cliente.getEndereco().length() > 38){
                endereco = cliente.getEndereco().substring(0, 38) + "...";                
            }else{
                endereco = cliente.getEndereco();
            }
            formPedido.getLblFieldEndereco().setText(endereco);
        }
    }

    private void buscarProduto() {
        DialogListaProduto dialogListaProduto = 
                new DialogListaProduto(formPedido, true);
        dialogListaProduto.setLocationRelativeTo(formPedido);
        dialogListaProduto.setVisible(true);
        produto = dialogListaProduto.getProduto();
        
        fillDataProduto();        
    }

    private void fillDataProduto() {
        if (produto != null){
            formPedido.getTxtProduto().setText(produto.getNome());                       
            DecimalFormat decimalFormat = new DecimalFormat();
            decimalFormat.setMaximumFractionDigits(2);
            decimalFormat.setMinimumFractionDigits(2);            
            valorItem = decimalFormat.format(produto.getValor());            
            formPedido.getTxtValor().setText(valorItem);
            formPedido.getTxtQuantidade().setText("");
            formPedido.getTxtQuantidade().requestFocus();
        }
    }

    private void incluirItem() {
        item = new PedidoItem();
        item.setProduto(produto);
        Integer quantidade = 
                Integer.parseInt(formPedido.getTxtQuantidade().getText());        
        item.setQuantidade(quantidade);
        quantidadeTotal = quantidadeTotal + quantidade;        
        Float valor = Float.parseFloat(valorItem.replace(",", "."));
        item.setValor(valor);
        valor = valor * quantidade;
        valorPedido = valorPedido + valor;
        valorTotal = valorPedido - desconto;
                
        formPedido.getLblQuantidadeTotal().
                setText(quantidadeTotal.toString().replace(".", ","));
        formPedido.getLblTotalPedido().
                setText(valorPedido.toString().replace(".", ","));
        formPedido.getLblValorTotal().
                setText(valorTotal.toString().replace(".", ","));
        
        itens.add(item);
        pedido.addItem(item);        
        model = new PedidoItensTableModel(itens);
        formPedido.getTblItensPedido().setModel(model);                
    }

    private void excluirItem() {
        Integer selectedRow = formPedido.getTblItensPedido().getSelectedRow();                
        if (selectedRow >= 0){
            item = itens.get(selectedRow);
            quantidadeTotal = quantidadeTotal - item.getQuantidade();
            valorTotal = valorTotal - item.getValor();
            valorTotal = valorTotal - desconto;
            formPedido.getLblQuantidadeTotal().
                    setText(quantidadeTotal.toString().replace(".", ","));
            formPedido.getLblTotalPedido().
                    setText(valorTotal.toString().replace(".", ","));
            formPedido.getLblValorTotal().
                    setText(valorTotal.toString().replace(".", ","));
            pedido.getItens().remove(item);
            itens.remove(item);
            model = null;
            model = new PedidoItensTableModel(itens);
            formPedido.getTblItensPedido().setModel(model);
        }
    }

    private void repeteEndereco() {
        formPedido.getTxtEnderecoEntrega().setText(cliente.getEndereco());
    }

    @Override
    public void focusGained(FocusEvent e) {
        //do nothing
    }

    @Override
    public void focusLost(FocusEvent e) {
        desconto = Float.parseFloat(
                formPedido.getTxtDesconto().getText().replace(",", "."));
        Float valorComDesconto = valorTotal - desconto;
        formPedido.getLblValorTotal().
                setText(valorComDesconto.toString().replace(".", ","));        
    }
}
