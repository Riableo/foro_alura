package com.aluracursos.foroalura.domain.respuesta.validaciones;

import com.aluracursos.foroalura.domain.ValidacionException;
import com.aluracursos.foroalura.domain.respuesta.DataUpdtResp;
import com.aluracursos.foroalura.domain.topico.ITopicoRepository;
import com.aluracursos.foroalura.domain.topico.Topico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TemaRespondido implements IValidadorRespuestas{

    @Autowired
    private ITopicoRepository topicRepo;

    @Override
    public void validar(DataUpdtResp dataUpdt, Long idTopic) {
        boolean respuesta = dataUpdt.solucion();

        if (respuesta){
            Topico topico = topicRepo.getReferenceById(idTopic);

            if (topico.getStatus().equals("Solucionado") && topico.getRespuesta() != null){
                throw new ValidacionException("Topico ya tiene una respuesta vinculada");
            }
        }
    }
}
