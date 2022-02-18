package com.ibm.academia.restapi.universidad.controladores;

import java.util.List;
import java.util.Optional;

import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;
import com.ibm.academia.restapi.universidad.modelo.entidades.Pabellon;
import com.ibm.academia.restapi.universidad.servicios.PabellonDAO;

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
public class PabellonControlador {

    @Autowired
    private PabellonDAO pabellonDAO;

    @GetMapping("/pabellones")
    public ResponseEntity<?> get() {
        List<Pabellon> pabellones = (List<Pabellon>) pabellonDAO.buscarTodos();
        if (pabellones.isEmpty())
            throw new NotFoundException("No existen pabellones");
        return new ResponseEntity<List<Pabellon>>(pabellones, HttpStatus.OK);
    }

    @GetMapping("/pabellones/localidad/{localidad}")
    public ResponseEntity<?> getPabellonesByDireccionLocalidad(@PathVariable String localidad) {
        List<Pabellon> pabellones = (List<Pabellon>) pabellonDAO.findPabellonesByDireccionLocalidad(localidad);
        if (pabellones.isEmpty())
            throw new NotFoundException("No existen pabellones");
        return new ResponseEntity<List<Pabellon>>(pabellones, HttpStatus.OK);
    }

    @GetMapping("/pabellones/nombre/{nombre}")
    public ResponseEntity<?> getPabellonesByNombre(@PathVariable String nombre) {
        List<Pabellon> pabellones = (List<Pabellon>) pabellonDAO.findPabellonesByNombre(nombre);
        if (pabellones.isEmpty())
            throw new NotFoundException("No existen pabellones");
        return new ResponseEntity<List<Pabellon>>(pabellones, HttpStatus.OK);
    }

    @GetMapping("/pabellon/{id}")
    public ResponseEntity<?> getByID(@PathVariable() Long id) {
        Optional<Pabellon> pabellon = pabellonDAO.buscarPorID(id);
        if (!pabellon.isPresent())
            throw new NotFoundException("Pabellon No encontrada");
        return new ResponseEntity<Pabellon>(pabellon.get(), HttpStatus.OK);
    }

    @PostMapping("/pabellon")
    public ResponseEntity<?> create(@RequestBody Pabellon pabellon) {
        Pabellon pabellon_ = pabellonDAO.guardar(pabellon);
        return new ResponseEntity<Pabellon>(pabellon_, HttpStatus.OK);
    }

    @DeleteMapping("/pabellon/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Pabellon> pabellon = pabellonDAO.buscarPorID(id);
        if (!pabellon.isPresent())
            throw new NotFoundException("Pabellon No encontrada");
        pabellonDAO.eliminarPorID(pabellon.get().getId());
        return new ResponseEntity<String>("Pabellon eliminada satisfactoriamente", HttpStatus.OK);
    }
}
