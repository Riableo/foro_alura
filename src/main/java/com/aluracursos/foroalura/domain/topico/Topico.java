package com.aluracursos.foroalura.domain.topico;

import com.aluracursos.foroalura.domain.curso.Curso;
import com.aluracursos.foroalura.domain.respuesta.Respuesta;
import com.aluracursos.foroalura.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
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
    Long id;
    String titulo;
    String mensaje;
    LocalDateTime fechaCreacion;
    //TODO: enum status (careful with DB to avoid errors)
    String status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    Curso curso;
}
