package com.dale.autenticacao.autSecurity;

import com.dale.autenticacao.autModel.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class JWTService {
    private static final String chavePrivadaJWT = "qppr4343ppquidsfhosadfioi34u534u5098d7fg908sd7fg09sd8f7g0s9d8f7g0s9d8f7g0sd98f7g0s9d8f7g0sd98f7g0sd98f7g";

    public String gerarToken(Authentication authentication){
        //milisseconds
        int tempoExpiracao = 86400000;
        Date dataExpericao = new Date(new Date().getTime() + tempoExpiracao);
        Usuario usuario = (Usuario) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(usuario.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(dataExpericao)
                .signWith(SignatureAlgorithm.HS512, chavePrivadaJWT)
                .compact();
    }

    public Optional<Long> obterIdUsuario(String token){
        try{
            Claims claims = parse(token).getBody();
            return Optional.ofNullable(Long.parseLong(claims.getSubject()));
        }catch (Exception e){
            return Optional.empty();
        }
    }


    public Jws<Claims> parse(String token){
        return Jwts.parser().setSigningKey(chavePrivadaJWT).parseClaimsJws(token);
    }
}
