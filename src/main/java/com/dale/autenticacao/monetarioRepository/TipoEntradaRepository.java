package com.dale.autenticacao.monetarioRepository;

import com.dale.autenticacao.monetarioModel.SaidaMonetaria;
import com.dale.autenticacao.monetarioModel.TipoEntrada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoEntradaRepository  extends JpaRepository<TipoEntrada, Long> {
    List<TipoEntrada> findByUsuario(Long usuario);
}
