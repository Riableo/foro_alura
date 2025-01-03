package com.aluracursos.foroalura.controller;

import com.aluracursos.foroalura.domain.usuario.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<Page<DatosUserList>> listUser(Pageable paginacion){
        //Page<DatosUserList> users = usuarioRepo.findAll(paginacion).map(DatosUserList::new);
        Page<DatosUserList> users = usuarioRepo.findByActivoTrue(paginacion).map(DatosUserList::new);
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaUser> updateUser(@RequestBody @Valid DatosActualizarUser dataUpdtUser, @PathVariable Long id){
        DatosRespuestaUser dataUser = userService.updateUser(dataUpdtUser, id);
        return ResponseEntity.ok(dataUser);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteUser(@PathVariable Long id){
        Usuario user = usuarioRepo.getReferenceById(id);
        user.inactiveUser();
        return ResponseEntity.noContent().build();
    }
}
