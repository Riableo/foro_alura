package com.aluracursos.foroalura.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DataAuthUser (
        @NotBlank
        String email,
        @NotBlank
        String psswd
){
}
