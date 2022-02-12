package com.ibm.academia.restapi.universidad.repositorios;

import com.ibm.academia.restapi.universidad.modelo.entidades.Pabellon;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PabellonRepository extends CrudRepository<Pabellon, Long> {

    public Iterable<Pabellon> findPabellonesByDireccionLocalidad(String localidad);

    public Iterable<Pabellon> findPabellonesByNombre(String nombre);

}
