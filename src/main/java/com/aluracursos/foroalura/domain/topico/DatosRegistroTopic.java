package com.aluracursos.foroalura.domain.topico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistroTopic(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        @Future
        LocalDateTime fechaCreacion,
        @NotNull
        String status,
        @NotNull
        Long idUsuario
) {
}
