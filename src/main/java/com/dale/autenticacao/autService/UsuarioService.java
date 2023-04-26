package com.dale.autenticacao.autService;

import com.dale.autenticacao.angendamentoModel.DadosUserDTO;
import com.dale.autenticacao.autModel.Usuario;
import com.dale.autenticacao.autModel.usuario.LoginResponse;
import com.dale.autenticacao.autRepository.UsuarioRepository;
import com.dale.autenticacao.autSecurity.JWTService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private static final String hederPrefix = "Bearer ";
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public List<Usuario> obterTodos(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obterId(Long id){
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> obterEmail(String email){
        return usuarioRepository.findByEmail(email);
    }

    public LoginResponse adicionar(Usuario usuario){
        usuario.setId(null);
        String pass = usuario.getPassword();
        if(obterEmail(usuario.getEmail()).isPresent()){
            throw new InputMismatchException("Email de usuario já existe!");
        }
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuarioRepository.save(usuario);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuario.getEmail(), pass, Collections.emptyList()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = hederPrefix + jwtService.gerarToken(authentication);

        return new LoginResponse(token, usuario);
    }

    public LoginResponse logar(String email, String senha){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, senha, Collections.emptyList()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = hederPrefix + jwtService.gerarToken(authentication);
        Usuario usuario = usuarioRepository.findByEmail(email).get();
        return new LoginResponse(token, usuario);
    }

    public Claims validarToken(String token){
        return (Claims) jwtService.parse(token);
    }

    public DadosUserDTO buscaDadosUsuario(String token){
        Optional<Long> usuario = jwtService.obterIdUsuario(token.substring(7));
        Optional<Usuario> user = usuarioRepository.findById(usuario.get().longValue());
        DadosUserDTO build = DadosUserDTO.Builder.newBuilder().nome(user.get().getNome()).build();
        return build;
    }
}
