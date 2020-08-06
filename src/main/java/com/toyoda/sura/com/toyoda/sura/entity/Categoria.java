package com.toyoda.sura.com.toyoda.sura.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "categorias")
@Setter
@Getter
@ToString
public class Categoria {

    @Id
    @Column(name = "idCategoria")
    @SequenceGenerator(name = "categoriasSeq", sequenceName = "categorias_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "categoriasSeq", strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Include
    private Long id;

    private String categoria;

}
