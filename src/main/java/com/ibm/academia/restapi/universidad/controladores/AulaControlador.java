package com.ibm.academia.restapi.universidad.controladores;

import java.util.List;
import java.util.Optional;

import com.ibm.academia.restapi.universidad.enumeradoes.TipoPizarron;
import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;
import com.ibm.academia.restapi.universidad.modelo.entidades.Aula;
import com.ibm.academia.restapi.universidad.servicios.AulaDAO;

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
public class AulaControlador {

    @Autowired
    private AulaDAO aulaDAO;

    @GetMapping("/aulas")
    public ResponseEntity<?> get() {
        List<Aula> aulas = (List<Aula>) aulaDAO.buscarTodos();
        if (aulas.isEmpty())
            throw new NotFoundException("No existen Aulas");
        return new ResponseEntity<List<Aula>>(aulas, HttpStatus.OK);
    }

    @GetMapping("/aulas/tipoPizzaron/{tipoPizarron}")
    public ResponseEntity<?> getAulasByTipoPizarron(@PathVariable TipoPizarron tipoPizarron) {
        List<Aula> aulas = (List<Aula>) aulaDAO.findAulasByTipoPizarron(tipoPizarron);
        if (aulas.isEmpty())
            throw new NotFoundException("No existen Aulas");
        return new ResponseEntity<List<Aula>>(aulas, HttpStatus.OK);
    }

    @GetMapping("/aulas/nombrePabellon/{nombrePabellon}")
    public ResponseEntity<?> getAulasByPabellonNombre(@PathVariable String nombrePabellon) {
        List<Aula> aulas = (List<Aula>) aulaDAO.findAulasByPabellonNombre(nombrePabellon);
        if (aulas.isEmpty())
            throw new NotFoundException("No existen Aulas");
        return new ResponseEntity<List<Aula>>(aulas, HttpStatus.OK);
    }

    @GetMapping("/aulas/numeroAula/{numeroAula}")
    public ResponseEntity<?> getAulasByNumAula(@PathVariable Integer numeroAula) {
        List<Aula> aulas = (List<Aula>) aulaDAO.findAulasByNumAula(numeroAula);
        if (aulas.isEmpty())
            throw new NotFoundException("No existen Aulas");
        return new ResponseEntity<List<Aula>>(aulas, HttpStatus.OK);
    }

    @GetMapping("/aula/{id}")
    public ResponseEntity<?> getByID(@PathVariable() Long id) {
        Optional<Aula> aula = aulaDAO.buscarPorID(id);
        if (!aula.isPresent())
            throw new NotFoundException("Aula No encontrada");
        return new ResponseEntity<Aula>(aula.get(), HttpStatus.OK);
    }

    @PostMapping("/aula")
    public ResponseEntity<?> create(@RequestBody Aula aula) {
        Aula aula_ = aulaDAO.guardar(aula);
        return new ResponseEntity<Aula>(aula_, HttpStatus.OK);
    }

    @DeleteMapping("/aula/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Aula> aula = aulaDAO.buscarPorID(id);
        if (!aula.isPresent())
            throw new NotFoundException("Aula No encontrada");
        aulaDAO.eliminarPorID(aula.get().getId());
        return new ResponseEntity<String>("Aula eliminada satisfactoriamente", HttpStatus.OK);
    }
}
