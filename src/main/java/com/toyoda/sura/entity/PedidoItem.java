package com.toyoda.sura.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "pedidoitens")
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class PedidoItem {

    @Id
    @Column(name = "iditem")
    @SequenceGenerator(name = "pedidoItensSeq", sequenceName = "pedido_itens_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "pedidoItensSeq", strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "produto")
    private String nomeProduto;

    private Integer quantidade;
    private Double valor;
    private Double subtotal;

    @ManyToOne
    @JoinColumn(name = "idpedido")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "idproduto")
    private Produto produto;
}
