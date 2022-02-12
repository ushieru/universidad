package com.ibm.academia.restapi.universidad.repositorios;

import com.ibm.academia.restapi.universidad.enumeradoes.TipoPizarron;
import com.ibm.academia.restapi.universidad.modelo.entidades.Aula;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AulaRepositorio extends CrudRepository<Aula, Long> {

    public Iterable<Aula> findAulasByTipoPizarron(TipoPizarron tipoPizarron);

    public Iterable<Aula> findAulasByPabellonNombre(String pabellonNombre);

    public Iterable<Aula> findAulasByNumAula(Integer numAula);
    
}
