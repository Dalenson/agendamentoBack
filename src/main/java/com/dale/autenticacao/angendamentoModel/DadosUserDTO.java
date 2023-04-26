package com.dale.autenticacao.angendamentoModel;

public class DadosUserDTO {

    private String nome;

    private DadosUserDTO(Builder builder) {
        setNome(builder.nome);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public static final class Builder {
        private String nome;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder nome(String val) {
            nome = val;
            return this;
        }

        public DadosUserDTO build() {
            return new DadosUserDTO(this);
        }
    }
}
