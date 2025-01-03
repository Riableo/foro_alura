package com.aluracursos.foroalura.domain.usuario;

public record DatosUserList(
        Long id,
        String nombre,
        String email,
        String perfil
) {
    public DatosUserList(Usuario user){
        this(
                user.getId(),
                user.getNombre(),
                user.getEmail(),
                user.getPerfil().getNombre()
        );
    }
}
