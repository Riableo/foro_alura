package com.aluracursos.foroalura.domain.respuesta;

import com.aluracursos.foroalura.domain.ValidacionException;
import com.aluracursos.foroalura.domain.respuesta.validaciones.IValidadorRespuestas;
import com.aluracursos.foroalura.domain.topico.ITopicoRepository;
import com.aluracursos.foroalura.domain.topico.Topico;
import com.aluracursos.foroalura.domain.usuario.IUsuarioRepository;
import com.aluracursos.foroalura.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RespuestaService {

    @Autowired
    private IUsuarioRepository userRepo;

    @Autowired
    private ITopicoRepository topicRepo;

    @Autowired
    private IRespuestaRepository resRepo;

    @Autowired
    private List<IValidadorRespuestas> validadores;

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
                        true,
                        dataReqRespuesta.mensaje(),
                        topico,
                        LocalDateTime.now(),
                        user,
                        false
                );

        resRepo.save(respuesta);

        return new DatosRespuesta(respuesta);
    }

    public DatosRespuesta updateRespuesta(DataUpdtResp dataResp, Long id) {

        if (!resRepo.existsById(id)) {
            throw new ValidacionException("La respuesta con el id ingresado no existe");
        }

        Respuesta respuesta = resRepo.findById(id).get();

        // Validators
        validadores.forEach(v -> v.validar(dataResp, respuesta.getTopico().getId()));

        String mensaje = dataResp.mensaje() != null ? dataResp.mensaje() : respuesta.getMensaje();
        boolean solucion = dataResp.solucion() != respuesta.isSolucion() ? dataResp.solucion() : respuesta.isSolucion();

        if (dataResp.solucion() != respuesta.isSolucion()){
            // Update/Delete respuesta_id
            updateTopicRes(respuesta, solucion);
        }

        Respuesta updtResp =
                new Respuesta(
                        respuesta.getId(),
                        respuesta.isActivo(),
                        mensaje,
                        respuesta.getTopico(),
                        respuesta.getFechaCreacion(),
                        respuesta.getUsuario(),
                        solucion
                );

        resRepo.save(updtResp);

        return new DatosRespuesta(updtResp);
    }

    private void updateTopicRes(Respuesta respuesta, boolean solucion) {

        Topico topico = topicRepo.getReferenceById(respuesta.getTopico().getId());

        Topico updteTopico =
                new Topico(
                        topico.getId(),
                        topico.getTitulo(),
                        topico.getMensaje(),
                        topico.getFechaCreacion(),
                        solucion ? "Solucionado" : "Activo",
                        topico.getUsuario(),
                        topico.getCurso(),
                        solucion ? respuesta : null
                );

        topicRepo.save(updteTopico);

    }
}
