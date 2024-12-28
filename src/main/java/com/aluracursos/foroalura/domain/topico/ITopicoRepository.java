package com.aluracursos.foroalura.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ITopicoRepository extends JpaRepository<Topico, Long> {
    Topico findByTitulo(String titulo);
}
