package com.dale.autenticacao.monetarioModel;

import javax.persistence.*;

@Entity(name = "tipo_entrada")
@SequenceGenerator(name = "generator_tipo_entrada", sequenceName = "sequence_tipo_entrada", initialValue = 1, allocationSize = 1)
public class TipoEntrada {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_tipo_entrada")
    @Column(name = "id")
    private Long id;

    private String nome;

    @Column(nullable = false)
    private Long usuario;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getUsuario() {
        return usuario;
    }

    public void setUsuario(Long usuario) {
        this.usuario = usuario;
    }
}
