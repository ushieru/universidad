package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.enumeradoes.TipoPizarron;
import com.ibm.academia.restapi.universidad.modelo.entidades.Aula;

public interface AulaDAO extends GenericoDAO<Aula> {

    public Iterable<Aula> findAulasByTipoPizarron(TipoPizarron tipoPizarron);

    public Iterable<Aula> findAulasByPabellonNombre(String pabellonNombre);

    public Iterable<Aula> findAulasByNumAula(Integer numAula);

}
