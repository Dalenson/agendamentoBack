package com.dale.autenticacao.angendamentoModel;

import javax.persistence.*;
import java.util.Date;

public class AgendaAdminDTO {

    private Long id;
    private Long user;
    private Date dataAgendamento;
    private Date dataCriacao;
    private Boolean confirmacao = false;

    private Boolean pagamento = false;

    private String nomeUser;

    private AgendaAdminDTO(Builder builder) {
        setId(builder.id);
        setUser(builder.user);
        setDataAgendamento(builder.dataAgendamento);
        setDataCriacao(builder.dataCriacao);
        setConfirmacao(builder.confirmacao);
        setPagamento(builder.pagamento);
        setNomeUser(builder.nomeUser);
    }

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

    public String getNomeUser() {
        return nomeUser;
    }

    public void setNomeUser(String nomeUser) {
        this.nomeUser = nomeUser;
    }


    public static final class Builder {
        private Long id;
        private Long user;
        private Date dataAgendamento;
        private Date dataCriacao;
        private Boolean confirmacao;
        private Boolean pagamento;
        private String nomeUser;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder user(Long val) {
            user = val;
            return this;
        }

        public Builder dataAgendamento(Date val) {
            dataAgendamento = val;
            return this;
        }

        public Builder dataCriacao(Date val) {
            dataCriacao = val;
            return this;
        }

        public Builder confirmacao(Boolean val) {
            confirmacao = val;
            return this;
        }

        public Builder pagamento(Boolean val) {
            pagamento = val;
            return this;
        }

        public Builder nomeUser(String val) {
            nomeUser = val;
            return this;
        }

        public AgendaAdminDTO build() {
            return new AgendaAdminDTO(this);
        }
    }
}
