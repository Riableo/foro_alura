package com.aluracursos.foroalura.controller;

import com.aluracursos.foroalura.domain.DataJWTtoken;
import com.aluracursos.foroalura.domain.usuario.DataAuthUser;
import com.aluracursos.foroalura.domain.usuario.Usuario;
import com.aluracursos.foroalura.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DataJWTtoken> authUser(@RequestBody @Valid DataAuthUser dataAuthUser){
        Authentication authToken = new UsernamePasswordAuthenticationToken(dataAuthUser.email(), dataAuthUser.psswd());
        Authentication userAuth = authenticationManager.authenticate(authToken);

        String jwtToken = tokenService.generateToken((Usuario) userAuth.getPrincipal());

        return ResponseEntity.ok(new DataJWTtoken(jwtToken));
    }
}