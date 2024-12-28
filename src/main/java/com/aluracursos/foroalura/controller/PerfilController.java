package com.aluracursos.foroalura.controller;

import com.aluracursos.foroalura.domain.perfil.DatosRegistroPerfil;
import com.aluracursos.foroalura.domain.perfil.DatosRespuestaPerfil;
import com.aluracursos.foroalura.domain.perfil.IPerfilRepository;
import com.aluracursos.foroalura.domain.perfil.Perfil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/perfil")
public class PerfilController {

    @Autowired
    private IPerfilRepository perfilRepo;

    @PostMapping
    public ResponseEntity<DatosRespuestaPerfil> registerPerfil(@RequestBody @Valid DatosRegistroPerfil dataPerfil){
        Perfil perfil = perfilRepo.save(new Perfil(dataPerfil));
        DatosRespuestaPerfil datosPerfil =
                new DatosRespuestaPerfil(
                        perfil.getId(),
                        perfil.getNombre()
                );
        return ResponseEntity.ok(datosPerfil);
    }
}
