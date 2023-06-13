package com.dale.autenticacao.agendamentoController;

import com.dale.autenticacao.agendamentoService.AgendamentoService;
import com.dale.autenticacao.angendamentoModel.Agenda;
import com.dale.autenticacao.angendamentoModel.DadosUserDTO;
import com.dale.autenticacao.autService.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/agenda")
public class AgendaController {

    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping("/listaAgendamentos")
    public List<Agenda> listaAgendamentos(@RequestHeader("Authorization") String token){
        return agendamentoService.listaAgendamentosPorUsuario(token);
    }

    @PostMapping("/adicionarAgendamento")
    public Agenda adicionarAgendamento(@RequestBody Agenda agendamento, @RequestHeader("Authorization") String token, @RequestHeader("DiasSemana") Boolean DiasSemana){
        Agenda agenda = agendamentoService.adicionarAgendamento(agendamento, token, DiasSemana);
        messagingTemplate.convertAndSend("/topic/messages", agendamentoService.listaAgendamentosPorUsuario(token));
        return agenda;
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
