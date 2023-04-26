package com.dale.autenticacao.angendamentoModel;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "agenda")
@SequenceGenerator(name = "generator_agenda", sequenceName = "sequence_agenda", initialValue = 1, allocationSize = 1)
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_agenda")
    private Long id;
    @Column(nullable = false)
    private Long user;
    @Column(nullable = false)
    private Date dataAgendamento;
    @Column(nullable = false)
    private Date dataCriacao;
    @Column(nullable = false)
    private Boolean confirmacao = false;

    @Column(nullable = false)
    private Boolean pagamento = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Date getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(Date dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Boolean getConfirmacao() {
        return confirmacao;
    }

    public void setConfirmacao(Boolean confirmacao) {
        this.confirmacao = confirmacao;
    }

    public Boolean getPagamento() {
        return pagamento;
    }

    public void setPagamento(Boolean pagamento) {
        this.pagamento = pagamento;
    }
}
