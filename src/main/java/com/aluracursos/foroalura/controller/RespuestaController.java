package com.aluracursos.foroalura.controller;

import com.aluracursos.foroalura.domain.respuesta.*;
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
@RequestMapping("/respuesta")
public class RespuestaController {

    @Autowired
    private RespuestaService resService;

    @Autowired
    private IRespuestaRepository respuestaRepo;

    @PostMapping
    public ResponseEntity<DatosRespuesta> createRespuesta(@RequestBody @Valid DatosRegistroRespuesta dataReqRespuesta, UriComponentsBuilder uriComponentsBuilder){
        DatosRespuesta dataRespuesta = resService.createRespuesta(dataReqRespuesta);

        URI url = uriComponentsBuilder.path("/respuesta/{id}").buildAndExpand(dataRespuesta.id()).toUri();

        return ResponseEntity.created(url).body(dataRespuesta);

    }
}
