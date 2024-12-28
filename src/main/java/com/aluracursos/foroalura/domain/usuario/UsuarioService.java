package com.aluracursos.foroalura.domain.usuario;

import com.aluracursos.foroalura.domain.ValidacionException;
import com.aluracursos.foroalura.domain.perfil.IPerfilRepository;
import com.aluracursos.foroalura.domain.perfil.Perfil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private IPerfilRepository perfilRepo;

    @Autowired
    private IUsuarioRepository usuarioRepo;
    public DatosRespuestaUser crearUsuario(DatosRegistroUsuario dataUser){

        if (!perfilRepo.existsById(dataUser.idPerfil())){
            throw new ValidacionException("No existe un perfil con el id ingresado.");
        }

        Perfil perfil = perfilRepo.findById(dataUser.idPerfil()).get();

        Usuario usuario = new Usuario(null, dataUser.nombre(), dataUser.email(), dataUser.psswd(), perfil);

        usuarioRepo.save(usuario);

        return new DatosRespuestaUser(usuario);
    }
}
