package com.aluracursos.foroalura.domain.perfil;

import com.aluracursos.foroalura.domain.ValidacionException;
import com.aluracursos.foroalura.domain.topico.DatosRegistroTopic;
import com.aluracursos.foroalura.domain.topico.DatosRespuestaTopic;
import com.aluracursos.foroalura.domain.topico.ITopicoRepository;
import com.aluracursos.foroalura.domain.topico.Topico;
import com.aluracursos.foroalura.domain.usuario.IUsuarioRepository;
import com.aluracursos.foroalura.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {


    @Autowired
    private IUsuarioRepository usuarioRepo;

    @Autowired
    private ITopicoRepository topicRepo;

    public DatosRespuestaTopic crearTopic(DatosRegistroTopic dataTopic){

        if (!usuarioRepo.existsById(dataTopic.idUsuario())){
            throw new ValidacionException("No existe un usuario con el id ingresado.");
        }

        Usuario user = usuarioRepo.findById(dataTopic.idUsuario()).get();

        Topico topic =
                new Topico(
                        null,
                        dataTopic.titulo(),
                        dataTopic.mensaje(),
                        dataTopic.fechaCreacion(),
                        dataTopic.status(),
                        user
                );

        topicRepo.save(topic);

        return new DatosRespuestaTopic(topic);
    }
}
