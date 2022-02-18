package com.ibm.academia.restapi.universidad.modelo.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pabellones", schema = "universidad")
public class Pabellon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive
    @Column(name = "metros_cuadrados")
    private Double metrosCuadrados;

    @NotNull
    @NotEmpty
    @Column(name = "nombre", nullable = false, length = 60)
    private String nombre;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "codigoPostal", column = @Column(name = "codigo_postal")),
            @AttributeOverride(name = "departamento", column = @Column(name = "departamento"))
    })
    private Direccion direccion;

    @NotNull
    @NotEmpty
    @Column(name = "usuario_creacion", nullable = false)
    private String usuarioCreacion;

    @Column(name = "fecha_creacion", nullable = false)
    private Date fechaCreacion;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @OneToMany(mappedBy = "pabellon", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({ "pabellon" })
    private Set<Aula> aulas;

    public Pabellon(Long id, Double metrosCuadrados, String nombre, Direccion direccion, String usuarioCreacion) {
        this.id = id;
        this.metrosCuadrados = metrosCuadrados;
        this.nombre = nombre;
        this.direccion = direccion;
        this.usuarioCreacion = usuarioCreacion;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pabellon other = (Pabellon) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Pabellon [direccion=" + direccion + ", fechaCreacion=" + fechaCreacion + ", fechaModificacion="
                + fechaModificacion + ", id=" + id + ", metrosCuadrados=" + metrosCuadrados + ", nombre=" + nombre
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
