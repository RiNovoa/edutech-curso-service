package com.edutech.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edutech.curso.model.Curso;
import com.edutech.curso.service.CursoService;

@RestController
@RequestMapping("/api/v1/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<Curso>> listarCursos() {
        List<Curso> cursos = cursoService.getCursos();
        if (cursos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cursos);
    }

    @PostMapping
    public ResponseEntity<Curso> agregarCurso(@RequestBody Curso curso) {
        Curso c = cursoService.saveCurso(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(c);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarCurso(@PathVariable int id) {
        try {
            Curso c = cursoService.getCurso(id);
            return ResponseEntity.ok(c);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizarCurso(@PathVariable int id, @RequestBody Curso curso) {
        try {
            Curso c = cursoService.getCurso(id);
            c.setTitulo(curso.getTitulo());
            c.setDescripcion(curso.getDescripcion());
            c.setEstado(curso.getEstado());
            c.setFechaCreacion(curso.getFechaCreacion());
            c.setGerenteId(curso.getGerenteId());
            cursoService.saveCurso(c);
            return ResponseEntity.ok(c);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable int id) {
        try {
            cursoService.deleteCurso(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
