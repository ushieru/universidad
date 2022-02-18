package com.ibm.academia.restapi.universidad.controladores;

import java.util.List;
import java.util.Optional;

import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.servicios.AlumnoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AlumnosControlador {

    @Autowired
    private AlumnoDAO alumnoDAO;

    @GetMapping("/alumnos")
    public ResponseEntity<?> get() {
        List<Persona> alumnos = (List<Persona>) alumnoDAO.buscarTodos();
        if (alumnos.isEmpty())
            throw new NotFoundException("No existen alumnos");
        return new ResponseEntity<List<Persona>>(alumnos, HttpStatus.OK);
    }

    @GetMapping("/alumno/{id}")
    public ResponseEntity<?> getByID(@PathVariable Long id) {
        Optional<Persona> alumno_ = alumnoDAO.buscarPorID(id);
        if (!alumno_.isPresent())
            throw new NotFoundException("Alumno no Encontrada");
        return new ResponseEntity<Persona>(alumno_.get(), HttpStatus.OK);
    }

    @GetMapping("/alumnos/nombreCarrera/{nombreCarrera}")
    public ResponseEntity<?> getByNombreCarrera(@PathVariable String nombreCarrera) {
        List<Persona> alumnos = (List<Persona>) ((AlumnoDAO) alumnoDAO).buscarAlumnosPorNombreCarrera(nombreCarrera);
        if (alumnos.isEmpty())
            throw new NotFoundException("No existen alumnos");
        return new ResponseEntity<List<Persona>>(alumnos, HttpStatus.OK);
    }

    @PostMapping("/alumno")
    public ResponseEntity<?> create(@RequestBody Persona alumno) {
        Persona alumno_ = alumnoDAO.guardar(alumno);
        return new ResponseEntity<Persona>(alumno_, HttpStatus.CREATED);
    }

    @DeleteMapping("/alumno/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Persona> alumno_ = alumnoDAO.buscarPorID(id);
        if (!alumno_.isPresent())
            throw new NotFoundException("Alumno no Encontrada");
        alumnoDAO.eliminarPorID(alumno_.get().getId());
        return new ResponseEntity<String>("Alumno eliminado satisfactoriamente",
                HttpStatus.NO_CONTENT);
    }
}
