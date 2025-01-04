package com.aluracursos.foroalura.domain.respuesta;

import java.time.LocalDateTime;

public record DatosRespuesta(
        Long id,
        String topico,
        String topicoMensaje,
        String mensaje,
        LocalDateTime fechaCreacion,
        String usuario,
        boolean solucion
) {
    public DatosRespuesta(Respuesta respuesta) {
        this(
                respuesta.getId(),
                respuesta.getTopico().getTitulo(),
                respuesta.getTopico().getMensaje(),
                respuesta.getMensaje(),
                respuesta.getFechaCreacion(),
                respuesta.getUsuario().getNombre(),
                respuesta.isSolucion()
        );
    }
}
