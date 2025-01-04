package com.aluracursos.foroalura.domain.respuesta;

import java.time.LocalDateTime;

public record DatosRespuestaList(
        Long id,
        String titulo,
        String problema,
        String mensaje,
        LocalDateTime fechaCreacion,
        String usuario
) {
    public DatosRespuestaList(Respuesta respuesta){
        this(
                respuesta.getId(),
                respuesta.getTopico().getTitulo(),
                respuesta.getTopico().getMensaje(),
                respuesta.getMensaje(),
                respuesta.getFechaCreacion(),
                respuesta.getUsuario().getNombre()
        );
    }
}
