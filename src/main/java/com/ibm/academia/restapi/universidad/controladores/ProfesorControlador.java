package com.ibm.academia.restapi.universidad.controladores;

import java.util.List;
import java.util.Optional;

import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.servicios.ProfesorDAO;

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
public class ProfesorControlador {

    @Autowired
    private ProfesorDAO profesorDAO;

    @GetMapping("/profesor")
    public ResponseEntity<?> get() {
        List<Persona> profesores = (List<Persona>) profesorDAO.buscarTodos();
        if (profesores.isEmpty())
            throw new NotFoundException("No existen profesores");
        return new ResponseEntity<List<Persona>>(profesores, HttpStatus.OK);
    }

    @GetMapping("/profesor/carrera/{carrera}")
    public ResponseEntity<?> getProfesoresByCarrera(@PathVariable String carrera) {
        List<Persona> profesores = (List<Persona>) profesorDAO.findProfesoresByCarrera(carrera);
        if (profesores.isEmpty())
            throw new NotFoundException("No existen profesores");
        return new ResponseEntity<List<Persona>>(profesores, HttpStatus.OK);
    }

    @GetMapping("/profesor/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Persona> profesor_ = profesorDAO.buscarPorID(id);
        if (!profesor_.isPresent())
            throw new NotFoundException("Profesor no encontrado");
        return new ResponseEntity<Persona>(profesor_.get(), HttpStatus.OK);
    }

    @PostMapping("/profesor")
    public ResponseEntity<?> create(@RequestBody Persona profesor) {
        Persona profesor_ = profesorDAO.guardar(profesor);
        return new ResponseEntity<Persona>(profesor_, HttpStatus.CREATED);
    }

    @DeleteMapping("/profesor/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Persona> profesor_ = profesorDAO.buscarPorID(id);
        if (!profesor_.isPresent())
            throw new NotFoundException("Profesor no encontrado");
        profesorDAO.eliminarPorID(profesor_.get().getId());
        return new ResponseEntity<String>("Profesor eliminado satisfactoriamente",
                HttpStatus.NO_CONTENT);
    }
}
