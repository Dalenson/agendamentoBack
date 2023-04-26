package com.dale.autenticacao.monetarioController;

import com.dale.autenticacao.monetarioModel.*;
import com.dale.autenticacao.monetarioService.MonetarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/monetario")
public class MonetarioController {

    @Autowired
    private MonetarioService monetarioService;

    @GetMapping("/entradas")
    public List<EntradaMonetariaDTO> obterTodasEntradas(@RequestHeader("Authorization") String token){
        return monetarioService.obterTodasEntradas(token);
    }

    @PostMapping("/entradas/tipos")
    public TipoEntrada adicionarTipoEntrada(@RequestBody TipoEntrada tipoEntrada, @RequestHeader("Authorization") String token){
        return monetarioService.adicionarTipoEntrada(tipoEntrada, token);
    }

    @GetMapping("/entradas/tipos")
    public List<TipoEntrada> obterTodosTiposEntradas(@RequestHeader("Authorization") String token){
        return monetarioService.obterTodosTiposEntradas(token);
    }

    @PostMapping("/entradas")
    public EntradaMonetaria adicionarEntrada(@RequestBody EntradaMonetaria entradaMonetaria, @RequestHeader("Authorization") String token){
        return monetarioService.adicionarEntrada(entradaMonetaria, token);
    }

    @GetMapping("/saidas")
    public List<SaidaMonetariaDTO> obterTodasSaidas(@RequestHeader("Authorization") String token){
        return monetarioService.obterTodasSaidas(token);
    }

    @PostMapping("/saidas")
    public SaidaMonetaria adicionarSaida(@RequestBody SaidaMonetaria saidaMonetaria, @RequestHeader("Authorization") String token){
        return monetarioService.adicionarSaida(saidaMonetaria, token);
    }

    @DeleteMapping("/saida/{id}")
    public void excluirSaida(@PathVariable Long id){
        monetarioService.excluirSaida(id);
    }

    @DeleteMapping("/entrada/{id}")
    public void excluirEntrada(@PathVariable Long id){
        monetarioService.excluirEntrada(id);
    }
}
