package com.aluracursos.foroalura.domain.usuario;

import com.aluracursos.foroalura.domain.ValidacionException;
import com.aluracursos.foroalura.domain.perfil.IPerfilRepository;
import com.aluracursos.foroalura.domain.perfil.Perfil;
import jakarta.validation.Valid;
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

        Usuario usuario = new Usuario(null, dataUser.nombre(), dataUser.email(), dataUser.psswd(), perfil, true);

        usuarioRepo.save(usuario);

        return new DatosRespuestaUser(usuario);
    }

    public DatosRespuestaUser updateUser(DatosActualizarUser dataUpdtUser, Long id) {

        if (!usuarioRepo.existsById(id)){
            throw new ValidacionException("No existe usuario con el id ingresdo");
        }

        Usuario repoUser = usuarioRepo.getReferenceById(id);

        Perfil perfil;

        String nombre = dataUpdtUser.nombre() == null ? repoUser.getNombre() : dataUpdtUser.nombre();
        String email = dataUpdtUser.email() == null ? repoUser.getEmail() : dataUpdtUser.email();
        String psswd = dataUpdtUser.psswd() == null ? repoUser.getPsswd() : dataUpdtUser.psswd();

        if (dataUpdtUser.perfil() != null){

            if (!perfilRepo.existsById(dataUpdtUser.perfil())){
                throw new ValidacionException("No existe perfil con el id ingresado");
            }

            perfil = perfilRepo.findById(dataUpdtUser.perfil()).get();
        }else {
            perfil = repoUser.getPerfil();
        }


        Usuario usuario =
                new Usuario(
                        id,
                        nombre,
                        email,
                        psswd,
                        perfil,
                        true
                );

        return new DatosRespuestaUser(usuario);
    }
}
