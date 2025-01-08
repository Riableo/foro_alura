package com.aluracursos.foroalura.domain.respuesta.validaciones;

import com.aluracursos.foroalura.domain.respuesta.DataUpdtResp;

public interface IValidadorRespuestas {
    void validar(DataUpdtResp dataUpdt, Long idTopic);
}
