package com.aluracursos.foroalura.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DatosRegistroUsuario(
        @NotBlank
        String nombre,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "^.{5,300}$", message = "Contraseña debería tener un minimo de 5 caracteres y maximo 300")
        String psswd,
        @NotNull
        Long idPerfil
) {
}
