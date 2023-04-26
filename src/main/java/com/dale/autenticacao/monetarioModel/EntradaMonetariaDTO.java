package com.dale.autenticacao.monetarioModel;

import com.dale.autenticacao.autModel.Usuario;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class EntradaMonetariaDTO{

    private Long id;

    private BigDecimal valor;

    private LocalDate dataEntrada;

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }


    public EntradaMonetariaDTO(Long id, BigDecimal valor, LocalDate dataEntrada, String tipo){
        this.id = id;
        this.valor = valor;
        this.dataEntrada = dataEntrada;
        this.tipo = tipo;
    }

    public EntradaMonetariaDTO build() {
        return new EntradaMonetariaDTO(id,valor, dataEntrada,tipo);
    }
}
