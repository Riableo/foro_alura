package com.aluracursos.foroalura.domain.topico;

import java.time.LocalDateTime;

public record DatosRespuestaTopic(
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String status,
        Long idUsuario
) {
    public DatosRespuestaTopic(Topico topic) {
        this(
                topic.getTitulo(),
                topic.getMensaje(),
                topic.getFechaCreacion(),
                topic.getStatus(),
                topic.getUsuario().getId()
        );
    }
}
