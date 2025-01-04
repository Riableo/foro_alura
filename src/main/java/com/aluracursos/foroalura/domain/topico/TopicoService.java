package com.aluracursos.foroalura.domain.topico;

import com.aluracursos.foroalura.domain.ValidacionException;
import com.aluracursos.foroalura.domain.curso.Curso;
import com.aluracursos.foroalura.domain.curso.ICursoRepository;
import com.aluracursos.foroalura.domain.usuario.IUsuarioRepository;
import com.aluracursos.foroalura.domain.usuario.Usuario;
import jakarta.validation.constraints.NotNull;
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
                        curso,
                        null
                );

        topicRepo.save(topic);

        return new DatosRespuestaTopic(topic);
    }

    public DatosRespuestaTopic updateTopic(DatosActualizarTopic dataUpdTopic, Long id){

        Topico topico = topicRepo.getReferenceById(id);

        Curso curso;

        if (dataUpdTopic.idCurso() != null){
            if (!cursoRepo.existsById(dataUpdTopic.idCurso())){
                throw new ValidacionException("Curso no existe con el id ingresado");
            }
            curso = cursoRepo.getReferenceById(dataUpdTopic.idCurso());
        }else {
            curso = topico.getCurso();
        }

        String titulo =  dataUpdTopic.titulo() != null ? dataUpdTopic.titulo() : topico.getTitulo();
        String mensaje = dataUpdTopic.mensaje() != null ? dataUpdTopic.mensaje() : topico.getMensaje();
        String status = dataUpdTopic.status() != null ? dataUpdTopic.status() : topico.getStatus();

        Topico topic =
                new Topico(
                        id,
                        titulo,
                        mensaje,
                        topico.getFechaCreacion(),
                        status,
                        topico.getUsuario(),
                        curso,
                        null
                );

        return new DatosRespuestaTopic(topic);
    }
}
