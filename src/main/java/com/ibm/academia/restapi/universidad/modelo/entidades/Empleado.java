package com.ibm.academia.restapi.universidad.modelo.entidades;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.ibm.academia.restapi.universidad.enumeradoes.TipoEmpleado;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "empleado", schema = "universidad")
@PrimaryKeyJoinColumn(name = "persona_id")
public class Empleado extends Persona {

    @NotNull
    @Positive
    @Column(name = "sueldo")
    private BigDecimal sueldo;

    @NotNull
    @NotEmpty
    @Column(name = "empleado")
    @Enumerated(EnumType.STRING)
    private TipoEmpleado tipoEmpleado;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "pabellon_id", foreignKey = @ForeignKey(name = "FK_EMPLEADO_PABELLON_ID"))
    private Pabellon pabellon;

    public Empleado(Long id, String nombre, String apellido, String dni, Direccion direccion, BigDecimal sueldo,
            TipoEmpleado tipoEmpleado) {
        super(id, nombre, apellido, dni, direccion);
        this.sueldo = sueldo;
        this.tipoEmpleado = tipoEmpleado;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString());
        stringBuilder.append("Empleado [sueldo=" + sueldo + "]");
        return stringBuilder.toString();
    }

}
