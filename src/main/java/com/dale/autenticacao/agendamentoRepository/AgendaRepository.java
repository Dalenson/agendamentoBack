package com.dale.autenticacao.agendamentoRepository;

import com.dale.autenticacao.angendamentoModel.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    List<Agenda> findByUser(Long user);
}
