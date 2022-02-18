package com.ibm.academia.restapi.universidad.modelo.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "carreras", schema = "universidad")
public class Carrera implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @NotNull
    @Column(name = "nombre", nullable = false, unique = true, length = 80)
    private String nombre;

    @NotNull
    @Positive
    @Column(name = "cantidad_materias")
    private Integer cantidadMaterias;

    @NotNull
    @Positive
    @Column(name = "cantidad_anios")
    private Integer cantidadAnios;

    @NotNull
    @NotEmpty
    @Column(name = "usuario_creacion", nullable = false)
    private String usuarioCreacion;

    @Column(name = "fecha_creacion", nullable = false)
    private Date fechaCreacion;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @OneToMany(mappedBy = "carrera", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({ "carrera" })
    private Set<Alumno> alumnos;

    @ManyToMany(mappedBy = "carreras", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({ "carreras" })
    private Set<Profesor> profesores;

    public Carrera(Long id, String nombre, Integer cantidadMaterias, Integer cantidadAnios, String usuarioCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.cantidadMaterias = cantidadMaterias;
        this.cantidadAnios = cantidadAnios;
        this.usuarioCreacion = usuarioCreacion;
    }

    @Override
    public String toString() {
        return "Carrera [cantidadAnios=" + cantidadAnios + ", cantidadMaterias=" + cantidadMaterias + ", fechaCreacion="
                + fechaCreacion + ", fechaModificacion=" + fechaModificacion + ", id=" + id + ", nombre=" + nombre
                + ", usuarioCreacion=" + usuarioCreacion + "]";
    }

    @PrePersist
    private void antesPersistir() {
        this.fechaCreacion = new Date();
    }

    @PreUpdate
    private void antesActualizar() {
        this.fechaModificacion = new Date();
    }
}
