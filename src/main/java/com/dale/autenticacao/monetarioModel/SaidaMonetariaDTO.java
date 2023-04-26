package com.dale.autenticacao.monetarioModel;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SaidaMonetariaDTO {

    private Long id;

    private BigDecimal valor;

    private LocalDate dataSaida;

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

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

    public SaidaMonetariaDTO(Long id, BigDecimal valor, LocalDate dataSaida, String tipo){
        this.id = id;
        this.valor = valor;
        this.dataSaida = dataSaida;
        this.tipo = tipo;
    }

    public SaidaMonetariaDTO build() {
        return new SaidaMonetariaDTO(id,valor, dataSaida,tipo);
    }
}
