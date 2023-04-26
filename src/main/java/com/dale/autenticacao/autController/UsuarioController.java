package com.dale.autenticacao.autController;

import com.dale.autenticacao.autModel.Usuario;
import com.dale.autenticacao.autModel.usuario.LoginRequest;
import com.dale.autenticacao.autModel.usuario.LoginResponse;
import com.dale.autenticacao.autService.UsuarioService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @CrossOrigin
    @GetMapping
    public List<Usuario> obterTodos(){
        return usuarioService.obterTodos();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Optional<Usuario> obter(@PathVariable("id") Long id){
         return usuarioService.obterId(id);
    }

    @CrossOrigin
    @PostMapping
    public LoginResponse adicionar(@RequestBody Usuario usuario){
        return usuarioService.adicionar(usuario);
    }

    @CrossOrigin
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){
        return usuarioService.logar(request.getEmail(), request.getSenha());
    }
}
