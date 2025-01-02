package com.aluracursos.foroalura.domain.topico;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosActualizarTopic(
        @NotNull Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String status,
        Long idUsuario,
        Long idCurso
) {
}
