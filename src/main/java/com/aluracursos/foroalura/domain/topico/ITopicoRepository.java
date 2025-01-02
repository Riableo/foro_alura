package com.aluracursos.foroalura.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ITopicoRepository extends JpaRepository<Topico, Long> {
    Topico findByTitulo(String titulo);

    @Query("SELECT t FROM Topico t JOIN t.curso c WHERE c.nombre = :courseName AND YEAR(t.fechaCreacion) = :year")
    Page<Topico> findTopics(String courseName, Long year, Pageable paginacion);
}
