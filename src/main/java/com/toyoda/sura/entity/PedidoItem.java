package com.toyoda.sura.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "pedidoItens")
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class PedidoItem {

    @Id
    @Column(name = "idItem")
    @SequenceGenerator(name = "pedidoItensSeq", sequenceName = "pedidoItens_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "pedidoItensSeq", strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "produto")
    private String nomeProduto;

    private Integer quantidade;
    private Double valor;
    private Double subtotal;

    @ManyToOne
    @JoinColumn(name = "idPedido")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "idProduto")
    private Produto produto;
}
