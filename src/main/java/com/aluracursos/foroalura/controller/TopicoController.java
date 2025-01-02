package com.aluracursos.foroalura.controller;

import com.aluracursos.foroalura.domain.curso.Curso;
import com.aluracursos.foroalura.domain.curso.DatosRespuestaCurso;
import com.aluracursos.foroalura.domain.curso.ICursoRepository;
import com.aluracursos.foroalura.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private ITopicoRepository topicRepo;

    @Autowired
    private ICursoRepository cursoRepo;

    @Autowired
    private TopicoService topicService;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopic> registerTopic(@RequestBody @Valid DatosRegistroTopic dataTopic, UriComponentsBuilder uriComponentsBuilder){
        //TODO: Devolver id en ResponseEntity
        DatosRespuestaTopic dataResponseTopic = topicService.crearTopic(dataTopic);

        Topico topico = topicRepo.findByTitulo(dataResponseTopic.titulo());

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(url).body(dataResponseTopic);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopic> getTopico(@PathVariable Long id){
        Topico topico = topicRepo.getReferenceById(id);

        DatosRespuestaTopic dataTopico =
                new DatosRespuestaTopic(
                        topico.getTitulo(),
                        topico.getMensaje(),
                        topico.getFechaCreacion(),
                        topico.getStatus(),
                        topico.getUsuario().getNombre(),
                        topico.getCurso().getNombre()
                );
        return ResponseEntity.ok(dataTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaTopic>> listTopic(@PageableDefault(size = 10, sort = "fechaCreacion") Pageable paginacion, @RequestParam Optional<String> courseName, @RequestParam Optional<Long> year){

        if (courseName.isPresent() && year.isPresent()){
            return ResponseEntity.ok(
                    topicRepo.findTopics(courseName.get(), year.get(), paginacion)
                            .map(DatosRespuestaTopic::new)
            );
        }
        return ResponseEntity.ok(topicRepo.findAll(paginacion).map(DatosRespuestaTopic::new));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaTopic> topicUpdate(@RequestBody @Valid DatosActualizarTopic dataTopic, @PathVariable Long id){
        Curso curso = null;

        Topico topico = topicRepo.getReferenceById(id);

        if (dataTopic.idCurso() != null){
            curso = cursoRepo.getReferenceById(dataTopic.idCurso());
        }

        topico.updateTopic(dataTopic, curso);
        DatosRespuestaTopic dataResTopic =
                new DatosRespuestaTopic(
                        topico.getTitulo(),
                        topico.getMensaje(),
                        topico.getFechaCreacion(),
                        topico.getStatus(),
                        topico.getUsuario().getNombre(),
                        topico.getCurso().getNombre()
                );

        return ResponseEntity.ok(dataResTopic);
    }
}
