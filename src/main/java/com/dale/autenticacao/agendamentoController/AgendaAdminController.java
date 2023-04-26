package com.dale.autenticacao.agendamentoController;

import com.dale.autenticacao.agendamentoService.AgendamentoService;
import com.dale.autenticacao.angendamentoModel.Agenda;
import com.dale.autenticacao.angendamentoModel.AgendaAdminDTO;
import com.dale.autenticacao.angendamentoModel.DadosUserDTO;
import com.dale.autenticacao.autService.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/admin")
public class AgendaAdminController {

    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listaAgendamentos")
    public List<AgendaAdminDTO> listaAgendamentos(@RequestHeader("Authorization") String token){
        return agendamentoService.listaAgendamentosComUsuario();

    }

    @PostMapping("/adicionarAgendamento")
    public Agenda adicionarAgendamento(@RequestBody Agenda agendamento, @RequestHeader("Authorization") String token, @RequestHeader("DiasSemana") Boolean DiasSemana){
        return agendamentoService.adicionarAgendamento(agendamento, token, DiasSemana);
    }

    @PostMapping("/atualizaAgendamento/{id}")
    public Agenda atualizaAgendamento(@PathVariable Long id){
        return agendamentoService.atualizaAgendamento(id);
    }

    @DeleteMapping("/excluirAgendamento/{id}")
    public void excluirAgenda(@PathVariable Long id){
        agendamentoService.excluirAgendamento(id);
    }

    @GetMapping("/buscaDadosUsuario")
    public DadosUserDTO buscaDadosUsuario(@RequestHeader("Authorization") String token){
        return usuarioService.buscaDadosUsuario(token);
    }
}
