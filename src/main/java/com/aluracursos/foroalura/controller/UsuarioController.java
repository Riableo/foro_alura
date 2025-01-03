package com.aluracursos.foroalura.controller;

import com.aluracursos.foroalura.domain.usuario.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private IUsuarioRepository usuarioRepo;

    @Autowired
    private UsuarioService userService;

    @PostMapping
    public ResponseEntity<DatosRespuestaUser> registrarUsuario(@RequestBody @Valid DatosRegistroUsuario dataUser){
        DatosRespuestaUser datosUser = userService.crearUsuario(dataUser);
        return ResponseEntity.ok(datosUser);
    }
}
