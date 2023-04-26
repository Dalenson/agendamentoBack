package com.dale.autenticacao.monetarioRepository;

import com.dale.autenticacao.monetarioModel.EntradaMonetaria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EntradaMonetarioRepository extends JpaRepository<EntradaMonetaria, Long> {
    List<EntradaMonetaria> findByUsuario(Long usuario);
}
