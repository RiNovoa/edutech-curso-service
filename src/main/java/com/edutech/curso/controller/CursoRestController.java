package com.edutech.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.curso.model.Curso;
import com.edutech.curso.service.CursoService;

@RestController
@RequestMapping("/api/cursos")
public class CursoRestController {

    @Autowired
    private CursoService cursoService;

    // Obtener todos los cursos
    @GetMapping
    public List<Curso> listarCursos() {
        return cursoService.getCursos();
    }

    // Obtener un curso por ID
    @GetMapping("/{id}")
    public ResponseEntity<Curso> obtenerCursoPorId(@PathVariable Integer id) {
        try {
            Curso curso = cursoService.getCurso(id);
            return ResponseEntity.ok(curso);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo curso
    @PostMapping
    public Curso crearCurso(@RequestBody Curso curso) {
        return cursoService.saveCurso(curso);
    }

    // Actualizar un curso existente
    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizarCurso(@PathVariable Integer id, @RequestBody Curso cursoActualizado) {
        try {
            Curso curso = cursoService.getCurso(id);

            curso.setTitulo(cursoActualizado.getTitulo());
            curso.setDescripcion(cursoActualizado.getDescripcion());
            curso.setEstado(cursoActualizado.getEstado());
            curso.setFechaCreacion(cursoActualizado.getFechaCreacion());
            curso.setGerenteId(cursoActualizado.getGerenteId());

            Curso cursoGuardado = cursoService.saveCurso(curso);
            return ResponseEntity.ok(cursoGuardado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un curso
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Integer id) {
        try {
            cursoService.deleteCurso(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
