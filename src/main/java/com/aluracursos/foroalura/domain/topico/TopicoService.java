package com.aluracursos.foroalura.domain.topico;

import com.aluracursos.foroalura.domain.ValidacionException;
import com.aluracursos.foroalura.domain.curso.Curso;
import com.aluracursos.foroalura.domain.curso.ICursoRepository;
import com.aluracursos.foroalura.domain.usuario.IUsuarioRepository;
import com.aluracursos.foroalura.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TopicoService {


    @Autowired
    private IUsuarioRepository usuarioRepo;

    @Autowired
    private ICursoRepository cursoRepo;

    @Autowired
    private ITopicoRepository topicRepo;

    public DatosRespuestaTopic crearTopic(DatosRegistroTopic dataTopic){

        if (!usuarioRepo.existsById(dataTopic.idUsuario())){
            throw new ValidacionException("No existe un usuario con el id ingresado.");
        }

        if (!cursoRepo.existsById(dataTopic.idCurso())){
            throw new ValidacionException("No existe curso con el id ingresado");
        }

        Usuario user = usuarioRepo.findById(dataTopic.idUsuario()).get();
        Curso curso = cursoRepo.findById(dataTopic.idCurso()).get();

        Topico topic =
                new Topico(
                        null,
                        dataTopic.titulo(),
                        dataTopic.mensaje(),
                        LocalDateTime.now(),
                        "Activo",
                        user,
                        curso
                );

        topicRepo.save(topic);

        return new DatosRespuestaTopic(topic);
    }
}
