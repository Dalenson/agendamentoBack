package com.dale.autenticacao.monetarioRepository;

import com.dale.autenticacao.monetarioModel.SaidaMonetaria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaidaMonetarioRepository extends JpaRepository<SaidaMonetaria, Long> {
    List<SaidaMonetaria> findByUsuario(Long usuario);
}
