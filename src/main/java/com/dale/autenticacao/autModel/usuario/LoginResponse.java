package com.dale.autenticacao.autModel.usuario;

import com.dale.autenticacao.autModel.Usuario;

public class LoginResponse {
    private String token;
    private Long idusuario;

    public LoginResponse() {
    }

    public LoginResponse(String token, Usuario usuario) {
        this.token = token;
        this.idusuario = usuario.getId();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Long idusuario) {
        this.idusuario = idusuario;
    }
}
