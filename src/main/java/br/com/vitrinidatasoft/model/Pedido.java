package br.com.vitrinidatasoft.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author mrhell
 */
@Entity
public class Pedido implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String numero;
    
    @ManyToOne
    private Cliente cliente;
    //private Usuario usuario;    
    
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data;
    
    private Float valor;
    
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, 
            orphanRemoval = true)
    private final Set<ItemPedido> itens;

    public Pedido() {
        this.itens = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public void addItem(ItemPedido itemPedido) {
        itemPedido.setPedido(this);
        itens.add(itemPedido);
    }
    
    
}
