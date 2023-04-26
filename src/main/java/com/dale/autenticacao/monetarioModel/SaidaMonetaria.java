package com.dale.autenticacao.monetarioModel;

import com.dale.autenticacao.autModel.Usuario;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity(name = "saida_monetaria")
@SequenceGenerator(name = "generator_saida", sequenceName = "sequence_saida", initialValue = 1, allocationSize = 1)
public class SaidaMonetaria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_saida")
    private Long id;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private LocalDate dataSaida;

    @Column(nullable = false)
    private Long usuario;

    private String tipo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Long getUsuario() {
        return usuario;
    }

    public void setUsuario(Long usuario) {
        this.usuario = usuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
