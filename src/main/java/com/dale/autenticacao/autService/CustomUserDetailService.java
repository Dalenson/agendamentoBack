package com.dale.autenticacao.autService;

import com.dale.autenticacao.autModel.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    @Lazy
    private UsuarioService usuarioService;
    @Override
    public UserDetails loadUserByUsername(String email){
        return getUser(() -> usuarioService.obterEmail(email));
    }

    public UserDetails obterUsuarioPorId(Long id){
        return usuarioService.obterId(id).get();
    }

    private Usuario getUser(Supplier<Optional<Usuario>> supplier){
        return supplier.get().orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado!"));
    }
}
