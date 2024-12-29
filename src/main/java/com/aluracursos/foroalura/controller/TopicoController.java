package com.aluracursos.foroalura.controller;

import com.aluracursos.foroalura.domain.curso.Curso;
import com.aluracursos.foroalura.domain.curso.DatosRespuestaCurso;
import com.aluracursos.foroalura.domain.topico.TopicoService;
import com.aluracursos.foroalura.domain.topico.DatosRegistroTopic;
import com.aluracursos.foroalura.domain.topico.DatosRespuestaTopic;
import com.aluracursos.foroalura.domain.topico.ITopicoRepository;
import com.aluracursos.foroalura.domain.topico.Topico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private ITopicoRepository topicRepo;

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
}
