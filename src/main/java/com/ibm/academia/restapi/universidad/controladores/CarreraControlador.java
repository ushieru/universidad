package com.ibm.academia.restapi.universidad.controladores;

import java.util.List;
import java.util.Optional;

import com.ibm.academia.restapi.universidad.excepciones.BadRequestException;
import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;
import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;
import com.ibm.academia.restapi.universidad.servicios.CarreraDAO;

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
public class CarreraControlador {

    @Autowired
    private CarreraDAO carreraDAO;

    @GetMapping("/carreras/containsNombre/{nombre}")
    ResponseEntity<?> getCarrerasByNombreContains(@PathVariable String nombre) {
        List<Carrera> carreras = (List<Carrera>) carreraDAO.findCarrerasByNombreContains(nombre);
        if (carreras.isEmpty())
            throw new NotFoundException("No existen carreras");
        return new ResponseEntity<List<Carrera>>(carreras, HttpStatus.OK);
    }

    @GetMapping("/carreras/containsNombreIgnoreCase/{nombre}")
    ResponseEntity<?> getCarrerasByNombreContainsIgnoreCase(@PathVariable String nombre) {
        List<Carrera> carreras = (List<Carrera>) carreraDAO
                .findCarrerasByNombreContainsIgnoreCase(nombre);
        if (carreras.isEmpty())
            throw new NotFoundException("No existen carreras");
        return new ResponseEntity<List<Carrera>>(carreras, HttpStatus.OK);
    }

    @GetMapping("/carreras/cantidadAnios/{anios}")
    ResponseEntity<?> getCarrerasByCantidadAniosAfter(@PathVariable Integer anios) {
        List<Carrera> carreras = (List<Carrera>) carreraDAO.findCarrerasByCantidadAniosAfter(anios);
        if (carreras.isEmpty())
            throw new NotFoundException("No existen carreras");
        return new ResponseEntity<List<Carrera>>(carreras, HttpStatus.OK);
    }

    @GetMapping("/carreras/profesorNombreApellido/{name}/{lastname}")
    ResponseEntity<?> getCarrerasPorProfesorNombreYApellido(@PathVariable String name, @PathVariable String lastname) {
        List<Carrera> carreras = (List<Carrera>) carreraDAO.buscarCarrerasPorProfesorNombreYApellido(name, lastname);
        if (carreras.isEmpty())
            throw new NotFoundException("No existen carreras");
        return new ResponseEntity<List<Carrera>>(carreras, HttpStatus.OK);
    }

    @GetMapping("/carreras")
    ResponseEntity<?> get() {
        List<Carrera> carreras = (List<Carrera>) carreraDAO.buscarTodos();
        if (carreras.isEmpty())
            throw new NotFoundException("No existen carreras");
        return new ResponseEntity<List<Carrera>>(carreras, HttpStatus.OK);
    }

    @GetMapping("carrera/{id}")
    ResponseEntity<?> getByID(@PathVariable() Long id) {
        Optional<Carrera> oCarrera = carreraDAO.buscarPorID(id);
        if (!oCarrera.isPresent())
            throw new BadRequestException("Carrera no encontrada");
        return new ResponseEntity<Carrera>(oCarrera.get(), HttpStatus.OK);
    }

    @PostMapping("/carrera")
    ResponseEntity<?> create(@RequestBody Carrera carrera) {
        Carrera carrera_ = carreraDAO.guardar(carrera);
        return new ResponseEntity<Carrera>(carrera_, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/carrera/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Carrera> carrera = carreraDAO.buscarPorID(id);
        if (!carrera.isPresent())
            throw new NotFoundException("Carrera no encontrada");
        carreraDAO.eliminarPorID(carrera.get().getId());
        return new ResponseEntity<String>("Carrera eliminada de manera satisfactoria", HttpStatus.NO_CONTENT);
    }
}
