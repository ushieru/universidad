package com.ibm.academia.restapi.universidad.controladores;

import java.util.List;
import java.util.Optional;

import com.ibm.academia.restapi.universidad.enumeradoes.TipoEmpleado;
import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.servicios.EmpleadoDAO;

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
public class EmpleadoControlador {

    @Autowired
    private EmpleadoDAO empleadoDAO;

    @GetMapping("/empleados")
    public ResponseEntity<?> get() {
        List<Persona> empleados = (List<Persona>) empleadoDAO.buscarTodos();
        if (empleados.isEmpty())
            throw new NotFoundException("No existen empleados");
        return new ResponseEntity<List<Persona>>(empleados, HttpStatus.OK);
    }

    @GetMapping("/empleados/tipoEmpleado/{tipoEmpleado}")
    public ResponseEntity<?> getTipoEmpleado(@PathVariable TipoEmpleado tipoEmpleado) {
        List<Persona> empleados = (List<Persona>) empleadoDAO.findEmpleadoByTipoEmpleado(tipoEmpleado);
        if (empleados.isEmpty())
            throw new NotFoundException("No existen empleados");
        return new ResponseEntity<List<Persona>>(empleados, HttpStatus.OK);
    }

    @GetMapping("/empleado/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Persona> empleado_ = empleadoDAO.buscarPorID(id);
        if (!empleado_.isPresent())
            throw new NotFoundException("Empleado no Encontrada");
        return new ResponseEntity<Persona>(empleado_.get(), HttpStatus.OK);
    }

    @PostMapping("/empleado")
    public ResponseEntity<?> create(@RequestBody Persona empleado) {
        Persona empleado_ = empleadoDAO.guardar(empleado);
        return new ResponseEntity<Persona>(empleado_, HttpStatus.CREATED);
    }

    @DeleteMapping("/empleado/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Persona> empleado_ = empleadoDAO.buscarPorID(id);
        if (!empleado_.isPresent())
            throw new NotFoundException("Empleado no Encontrada");
        empleadoDAO.eliminarPorID(empleado_.get().getId());
        return new ResponseEntity<String>("Empleado eliminado satisfactoriamente",
                HttpStatus.NO_CONTENT);
    }
}
