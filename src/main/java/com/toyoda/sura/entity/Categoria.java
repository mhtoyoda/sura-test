package com.toyoda.sura.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categorias")
@Setter
@Getter
@ToString
public class Categoria {

    @Id
    @Column(name = "idCategoria")
    @EqualsAndHashCode.Include
    private Long id;

    private String categoria;

}
