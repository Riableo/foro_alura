package com.aluracursos.foroalura.domain.topico;

import com.aluracursos.foroalura.domain.curso.Curso;
import com.aluracursos.foroalura.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    //TODO: enum status (careful with DB to avoid errors)
    private String status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    public void updateTopic(DatosActualizarTopic dataTopic, Curso curso){
        if (dataTopic.titulo() != null){
            this.titulo = dataTopic.titulo();
        }

        if (dataTopic.mensaje() != null){
            this.mensaje = dataTopic.mensaje();
        }

        if (dataTopic.status() != null){
            this.status = dataTopic.status();
        }

        if(dataTopic.idCurso() != null){
            this.curso = curso;
        }
    }

    public void inactiveTopic() {
        this.status = "Desactivado";
    }
}
