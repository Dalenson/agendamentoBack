package com.dale.autenticacao.agendamentoService;

import com.dale.autenticacao.agendamentoRepository.AgendaRepository;
import com.dale.autenticacao.angendamentoModel.Agenda;
import com.dale.autenticacao.angendamentoModel.AgendaAdminDTO;
import com.dale.autenticacao.autModel.Usuario;
import com.dale.autenticacao.autSecurity.JWTService;
import com.dale.autenticacao.autService.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AgendamentoService {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UsuarioService usuarioService;

    public Agenda adicionarAgendamento(Agenda agendamento, String token, Boolean DiasSemana){
        Agenda save = null;
        Optional<Usuario> usuario = usuarioService.obterId(jwtService.obterIdUsuario(token.substring(7)).get());

        if(!DiasSemana) {
            agendamento.setId((null));
            agendamento.setDataCriacao(new Date());
            agendamento.setUser(usuario.get().getId());
            save = agendaRepository.save(agendamento);
        }else{
            //Para melhorar essa parte, fazer com fila para não demorar no front
            List<Date> weeklyDates = getWeeklyDates(agendamento.getDataAgendamento());
            weeklyDates.forEach(data ->{
                Agenda agenda = new Agenda();
                agenda.setId(null);
                agenda.setDataCriacao(new Date());
                agenda.setUser(usuario.get().getId());
                agenda.setDataAgendamento(data);
                agendaRepository.save(agenda);
            });
        }
        return save;
    }

    public static List<Date> getWeeklyDates(Date startDate) {
        List<Date> weeklyDates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);

        // Define a data final como o último dia do ano
        Calendar endOfYear = Calendar.getInstance();
        endOfYear.set(Calendar.MONTH, Calendar.DECEMBER);
        endOfYear.set(Calendar.DAY_OF_MONTH, 31);

        // Adiciona a data inicial à lista de datas semanais
        weeklyDates.add(calendar.getTime());

        // Adiciona a data para todas as semanas até o final do ano
        while (calendar.before(endOfYear)) {
            calendar.add(Calendar.DATE, 7);
            weeklyDates.add(calendar.getTime());
        }

        return weeklyDates;
    }

    public void excluirAgendamento(Long id){
        agendaRepository.deleteById(id);
    }

    public List<Agenda> listaAgendamentosPorUsuario(String token){
        Optional<Long> usuario = jwtService.obterIdUsuario(token.substring(7));
        return agendaRepository.findByUser(usuario.get());
    }

    public List<AgendaAdminDTO> listaAgendamentosComUsuario(){
        List<Agenda> todos = agendaRepository.findAll();
        List<AgendaAdminDTO> dtos = new ArrayList<>();
        todos.forEach(agenda->{
            dtos.add(AgendaAdminDTO.Builder.newBuilder()
                    .user(agenda.getUser())
                    .nomeUser(usuarioService.obterId(agenda.getUser()).get().getNome())
                    .dataAgendamento(agenda.getDataAgendamento())
                    .dataCriacao(agenda.getDataCriacao())
                    .confirmacao(agenda.getConfirmacao())
                    .pagamento(agenda.getPagamento())
                    .id(agenda.getId())
                    .build());
        });
        return dtos;
    }

    public Agenda atualizaAgendamento(Long id){
        Optional<Agenda> agenda = agendaRepository.findById(id);
        Agenda agendaBusca = agenda.get();
        agendaBusca.setConfirmacao(true);
        return agendaRepository.save(agendaBusca);
    }
}
