package com.toyoda.sura.com.toyoda.sura.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "produtos")
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Produto {

    @Id
    @Column(name = "idProduto")
    @SequenceGenerator(name = "produtosSeq", sequenceName = "produtos_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "produtosSeq", strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Include
    private Long id;
    private String produto;
    private String descricao;
    private String foto;
    private Integer quantidade;
    private Double preco;

    @ManyToOne
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;
}
