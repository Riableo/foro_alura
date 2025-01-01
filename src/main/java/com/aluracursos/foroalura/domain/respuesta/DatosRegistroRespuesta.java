package com.aluracursos.foroalura.domain.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistroRespuesta(
        @NotBlank
        String mensaje,
        @NotNull
        Long idTopico,
        LocalDateTime fechaCreacion,
        @NotNull
        Long idUsuario,
        boolean solucion
) {
}
