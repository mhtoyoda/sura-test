package com.toyoda.sura.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "clientes")
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Cliente {

    @Id
    @Column(name = "idCliente")
    @SequenceGenerator(name = "clienteSeq", sequenceName = "cliente_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "clienteSeq", strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Include
    private Long id;

    private String nome;
    private String email;
    private String senha;
    private String rua;
    private String cidade;
    private String cep;
    private String bairro;
    private String estado;

}
