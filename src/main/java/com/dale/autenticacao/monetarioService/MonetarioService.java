package com.dale.autenticacao.monetarioService;

import com.dale.autenticacao.autModel.Usuario;
import com.dale.autenticacao.autSecurity.JWTService;
import com.dale.autenticacao.autService.UsuarioService;
import com.dale.autenticacao.monetarioModel.*;
import com.dale.autenticacao.monetarioRepository.EntradaMonetarioRepository;
import com.dale.autenticacao.monetarioRepository.SaidaMonetarioRepository;
import com.dale.autenticacao.monetarioRepository.TipoEntradaRepository;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MonetarioService {

    @Autowired
    private EntradaMonetarioRepository entradaMonetarioRepository;

    @Autowired
    private SaidaMonetarioRepository saidaMonetarioRepository;

    @Autowired
    private TipoEntradaRepository tipoEntradaRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UsuarioService usuarioService;

    public List<SaidaMonetariaDTO> obterTodasSaidas(String token){
        Optional<Long> usuario = jwtService.obterIdUsuario(token.substring(7));
        List<SaidaMonetaria> saidas = saidaMonetarioRepository.findByUsuario(usuario.get());
        return saidas
                .stream()
                .map(saida -> {
                    return new SaidaMonetariaDTO(saida.getId(), saida.getValor(), saida.getDataSaida(), saida.getTipo()).build();
                })
                .collect(Collectors.toList());
    }

    public List<EntradaMonetariaDTO> obterTodasEntradas(String token){
        Optional<Long> usuario = jwtService.obterIdUsuario(token.substring(7));
        List<EntradaMonetaria> entradas = entradaMonetarioRepository.findByUsuario(usuario.get());
        return entradas
                .stream()
                .map(entrada -> {
                    return new EntradaMonetariaDTO(entrada.getId(), entrada.getValor(), entrada.getDataEntrada(), entrada.getTipo()).build();
                })
                .collect(Collectors.toList());
    }



    public EntradaMonetaria adicionarEntrada(EntradaMonetaria entradaMonetaria, String token){
        entradaMonetaria.setId((null));
        Optional<Usuario> usuario = usuarioService.obterId(jwtService.obterIdUsuario(token.substring(7)).get());
        entradaMonetaria.setUsuario(usuario.get().getId());
        return entradaMonetarioRepository.save(entradaMonetaria);
    }

    public SaidaMonetaria adicionarSaida(SaidaMonetaria saidaMonetaria, String token){
        saidaMonetaria.setId((null));
        Optional<Usuario> usuario = usuarioService.obterId(jwtService.obterIdUsuario(token.substring(7)).get());
        saidaMonetaria.setUsuario(usuario.get().getId());
        return saidaMonetarioRepository.save(saidaMonetaria);
    }

    public List<TipoEntrada> obterTodosTiposEntradas(String token){
        Optional<Usuario> usuario = usuarioService.obterId(jwtService.obterIdUsuario(token.substring(7)).get());
        return tipoEntradaRepository.findByUsuario(usuario.get().getId());
    }

    public TipoEntrada adicionarTipoEntrada(TipoEntrada tipoEntrada, String token){
        tipoEntrada.setId((null));
        Optional<Usuario> usuario = usuarioService.obterId(jwtService.obterIdUsuario(token.substring(7)).get());
        tipoEntrada.setUsuario(usuario.get().getId());
        return tipoEntradaRepository.save(tipoEntrada);
    }

    public void excluirSaida(Long id){
        saidaMonetarioRepository.deleteById(id);
    }

    public void excluirEntrada(Long id){
        entradaMonetarioRepository.deleteById(id);
    }
}
