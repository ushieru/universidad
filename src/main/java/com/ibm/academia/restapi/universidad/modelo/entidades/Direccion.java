package com.ibm.academia.restapi.universidad.modelo.entidades;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Embeddable
public class Direccion implements Serializable {
    @NotNull
    @NotEmpty
    private String calle;
    @NotNull
    @NotEmpty
    private String numero;
    @NotNull
    @NotEmpty
    private String codigoPostal;
    @NotNull
    @NotEmpty
    private String departamento;
    @NotNull
    @NotEmpty
    private String piso;
    @NotNull
    @NotEmpty
    private String localidad;
}
