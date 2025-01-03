package com.aluracursos.foroalura.domain.usuario;


public record DatosRespuestaUser(
        Long id,
        String nombre,
        String email,
        String psswd,
        String perfil
) {
    public DatosRespuestaUser(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getPassword(),
                usuario.getPerfil().getNombre()
        );
    }
}
