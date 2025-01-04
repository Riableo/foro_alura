package com.aluracursos.foroalura.controller;

import com.aluracursos.foroalura.domain.respuesta.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/respuesta")
@SecurityRequirement(name = "bearer-key")
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

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaList>> listRespuesta(Pageable paginacion){
        Page<DatosRespuestaList> dataResp = respuestaRepo.findByActivoTrue(paginacion).map(DatosRespuestaList::new);
        return ResponseEntity.ok(dataResp);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuesta> updtRespuesta(@RequestBody @Valid DataUpdtResp dataResp, @PathVariable Long id){
        DatosRespuesta updtResp = resService.updateRespuesta(dataResp, id);
        return ResponseEntity.ok(updtResp);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteRespuesta(@PathVariable Long id){
        Respuesta respuesta = respuestaRepo.getReferenceById(id);
        respuesta.inactiveRespuesta();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuesta> obtenerRespuesta(@PathVariable Long id){
        Respuesta respuesta = respuestaRepo.getReferenceById(id);
        DatosRespuesta dataResp =
                new DatosRespuesta(
                        respuesta.getId(),
                        respuesta.getTopico().getTitulo(),
                        respuesta.getTopico().getMensaje(),
                        respuesta.getMensaje(),
                        respuesta.getFechaCreacion(),
                        respuesta.getUsuario().getNombre(),
                        respuesta.isSolucion()
                );
        return ResponseEntity.ok(dataResp);
    }
}
