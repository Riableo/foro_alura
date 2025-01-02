package com.aluracursos.foroalura.domain.topico;

import java.time.LocalDateTime;

public record DatosTopicList(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String status,
        String Usuario,
        String Curso
) {
    public DatosTopicList(Topico topic) {
        this(
                topic.getId(),
                topic.getTitulo(),
                topic.getMensaje(),
                topic.getFechaCreacion(),
                topic.getStatus(),
                topic.getUsuario().getNombre(),
                topic.getCurso().getNombre()
        );
    }
}
