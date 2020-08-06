package com.toyoda.sura.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Pedido {

    @Id
    @Column(name = "idPedido")
    @SequenceGenerator(name = "pedidoSeq", sequenceName = "pedido_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "pedidoSeq", strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Include
    private Long id;
    private String sessao;

    @Column(name = "data", columnDefinition = "TIMESTAMP")
    private LocalDateTime data = LocalDateTime.now();

    private String status;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;
}
