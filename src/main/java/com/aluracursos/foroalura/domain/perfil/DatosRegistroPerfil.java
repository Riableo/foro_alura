package com.aluracursos.foroalura.domain.perfil;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroPerfil(
        @NotBlank
        String nombre
) {
}
