package com.aluracursos.foroalura.domain.respuesta;

import com.aluracursos.foroalura.domain.ValidacionException;
import com.aluracursos.foroalura.domain.topico.ITopicoRepository;
import com.aluracursos.foroalura.domain.topico.Topico;
import com.aluracursos.foroalura.domain.usuario.IUsuarioRepository;
import com.aluracursos.foroalura.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RespuestaService {

    @Autowired
    private IUsuarioRepository userRepo;

    @Autowired
    private ITopicoRepository topicRepo;

    @Autowired
    private IRespuestaRepository resRepo;

    public DatosRespuesta createRespuesta(DatosRegistroRespuesta dataReqRespuesta){

        if (!userRepo.existsById(dataReqRespuesta.idUsuario())){
            throw new ValidacionException("No existe un usuario con el id ingresado");
        }

        if (!topicRepo.existsById(dataReqRespuesta.idTopico())){
            throw new ValidacionException("No existe un topico con el id ingresado");
        }

        Usuario user = userRepo.findById(dataReqRespuesta.idUsuario()).get();

        Topico topico = topicRepo.findById(dataReqRespuesta.idTopico()).get();

        Respuesta respuesta =
                new Respuesta(
                        null,
                        dataReqRespuesta.mensaje(),
                        topico,
                        LocalDateTime.now(),
                        user,
                        true
                );

        resRepo.save(respuesta);

        return new DatosRespuesta(respuesta, topico);
    }
}
