package com.aluracursos.foroalura.controller;

import com.aluracursos.foroalura.domain.perfil.TopicoService;
import com.aluracursos.foroalura.domain.topico.DatosRegistroTopic;
import com.aluracursos.foroalura.domain.topico.DatosRespuestaTopic;
import com.aluracursos.foroalura.domain.topico.ITopicoRepository;
import com.aluracursos.foroalura.domain.topico.Topico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicService;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopic> registerTopic(@RequestBody @Valid DatosRegistroTopic dataTopic, UriComponentsBuilder uriComponentsBuilder){

        DatosRespuestaTopic dataResponseTopic = topicService.crearTopic(dataTopic);

        return ResponseEntity.ok(dataResponseTopic);
    }
}
