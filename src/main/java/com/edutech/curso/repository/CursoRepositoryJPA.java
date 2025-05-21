package com.edutech.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.edutech.curso.model.Curso;

@Repository
public interface CursoRepositoryJPA extends JpaRepository<Curso, Integer> {
    // Puedes agregar métodos personalizados si quieres, por ejemplo:
    // List<Curso> findByEstadoTrue();
}
