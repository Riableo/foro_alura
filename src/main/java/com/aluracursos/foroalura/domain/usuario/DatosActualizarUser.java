package com.aluracursos.foroalura.domain.usuario;

public record DatosActualizarUser(
        String nombre,
        String email,
        String psswd,
        Long perfil
) {
}
