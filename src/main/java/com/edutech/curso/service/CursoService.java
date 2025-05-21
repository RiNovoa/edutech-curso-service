package com.edutech.curso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.curso.model.Curso;
import com.edutech.curso.repository.CursoRepositoryJPA;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CursoService {

    @Autowired
    private CursoRepositoryJPA cursoRepositoryJPA;

    public List<Curso> getCursos() {
        return cursoRepositoryJPA.findAll();
    }

    public Curso saveCurso(Curso curso) {
        return cursoRepositoryJPA.save(curso);
    }

    public Curso getCurso(int id) throws Exception {
        return cursoRepositoryJPA.findById(id)
            .orElseThrow(() -> new Exception("Curso no encontrado con id " + id));
    }

    public void deleteCurso(int id) {
        cursoRepositoryJPA.deleteById(id);
    }
}
