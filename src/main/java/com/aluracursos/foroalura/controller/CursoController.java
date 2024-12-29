package com.aluracursos.foroalura.controller;

import com.aluracursos.foroalura.domain.curso.Curso;
import com.aluracursos.foroalura.domain.curso.DatosRegistroCurso;
import com.aluracursos.foroalura.domain.curso.DatosRespuestaCurso;
import com.aluracursos.foroalura.domain.curso.ICursoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    
    @Autowired
    private ICursoRepository cursoRepo;

    @PostMapping
    public ResponseEntity<DatosRespuestaCurso> createCurso(@RequestBody @Valid DatosRegistroCurso dataRegCurso, UriComponentsBuilder uriComponentsBuilder){
        Curso curso = cursoRepo.save(new Curso(dataRegCurso));
        DatosRespuestaCurso dataCurso =
                new DatosRespuestaCurso(
                  curso.getId(),
                  curso.getNombre(),
                  curso.getCategoria()
                );

        URI url = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(url).body(dataCurso);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaCurso> getCurso(@PathVariable Long id){
        Curso curso = cursoRepo.getReferenceById(id);

        DatosRespuestaCurso dataCurso =
                new DatosRespuestaCurso(
                        curso.getId(),
                        curso.getNombre(),
                        curso.getCategoria()
                );
        return ResponseEntity.ok(dataCurso);
    }
}
