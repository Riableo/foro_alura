package com.aluracursos.foroalura.domain.usuario;

public record DatosRespuestaUserUpdt(
        Long id,
        String nombre,
        String email,
        String psswd,
        String perfil
) {
    public DatosRespuestaUserUpdt(Usuario user){
        this(
                user.getId(),
                user.getNombre(),
                user.getEmail(),
                user.getPassword(),
                user.getPerfil().getNombre()
        );
    }
}
