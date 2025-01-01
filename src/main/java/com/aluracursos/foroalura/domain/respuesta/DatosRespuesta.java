package com.aluracursos.foroalura.domain.respuesta;

import com.aluracursos.foroalura.domain.topico.Topico;

import java.time.LocalDateTime;

public record DatosRespuesta(
        Long id,
        String topico,
        String topicoMensaje,
        String mensaje,
        LocalDateTime fechaCreacion,
        String usuario
) {
    public DatosRespuesta(Respuesta respuesta, Topico topico) {
        this(
                respuesta.getId(),
                respuesta.getTopico().getTitulo(),
                topico.getMensaje(),
                respuesta.getMensaje(),
                respuesta.getFechaCreacion(),
                respuesta.getUsuario().getNombre()
        );
    }
}
