package com.aluracursos.foroalura.domain.usuario;


public record DatosRespuestaUser(
        String nombre,
        String email
) {
    public DatosRespuestaUser(Usuario usuario) {
        this(
                usuario.getNombre(),
                usuario.getEmail()
        );
    }
}
