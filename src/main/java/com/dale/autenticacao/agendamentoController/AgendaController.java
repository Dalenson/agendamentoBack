package com.dale.autenticacao.agendamentoController;

import com.dale.autenticacao.agendamentoService.AgendamentoService;
import com.dale.autenticacao.angendamentoModel.Agenda;
import com.dale.autenticacao.angendamentoModel.DadosUserDTO;
import com.dale.autenticacao.autService.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/agenda")
public class AgendaController {

    private static final Logger LOG = LoggerFactory.getLogger(AgendaController.class);

    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping("/listaAgendamentos")
    public List<Agenda> listaAgendamentos(@RequestHeader("Authorization") String token){
        LOG.info("Buscando lista de agendamentos");
        return agendamentoService.listaAgendamentosPorUsuario(token);
    }

    @PostMapping("/adicionarAgendamento")
    public Agenda adicionarAgendamento(@RequestBody Agenda agendamento, @RequestHeader("Authorization") String token, @RequestHeader("DiasSemana") Boolean DiasSemana){
        LOG.info("Criando agendamento");
        Agenda agenda = agendamentoService.adicionarAgendamento(agendamento, token, DiasSemana);
        messagingTemplate.convertAndSend("/topic/messages", agendamentoService.listaAgendamentosPorUsuario(token));
        return agenda;
    }

    @DeleteMapping("/excluirAgendamento/{id}")
    public void excluirAgenda(@PathVariable Long id){
        LOG.info("Excluindo agendamento");
        agendamentoService.excluirAgendamento(id);
    }

    @GetMapping("/buscaDadosUsuario")
    public DadosUserDTO buscaDadosUsuario(@RequestHeader("Authorization") String token){
        LOG.info("Buscando dados do usuario");
        return usuarioService.buscaDadosUsuario(token);
    }
}
