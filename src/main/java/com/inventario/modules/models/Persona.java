package com.inventario.modules.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonView;
import com.inventario.modules.views.Views;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the persona database table.
 * 
 */
@Entity
@NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p")
@Getter
@Setter
public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo_persona")
	@JsonView({ Views.Read.class, Views.Write.class })
	private int codigoPersona;

	@JsonView({ Views.Read.class, Views.Write.class })
	private String apellidos;

	@JsonView({ Views.Read.class, Views.Write.class })
	private String cedula;

	@JsonView({ Views.Read.class, Views.Write.class })
	private String correo;

	@JsonView({ Views.Read.class, Views.Write.class })
	private String direccion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_registro")
	@JsonView({ Views.Read.class, Views.Write.class })
	private Date fechaRegistro;

	@JsonView({ Views.Read.class, Views.Write.class })
	private String nombres;

	@JsonView({ Views.Read.class, Views.Write.class })
	private String telefono;

	@JsonView({ Views.Read.class, Views.Write.class })
	private Boolean vacunado;

	// bi-directional many-to-one association to Empleado
	@OneToMany(mappedBy = "persona")
	private List<Empleado> empleados;

	// bi-directional many-to-one association to VacunaPersona
	@OneToMany(mappedBy = "persona")
	private List<VacunaPersona> vacunaPersonas;

	public Persona() {
	}

	public Empleado addEmpleado(Empleado empleado) {
		getEmpleados().add(empleado);
		empleado.setPersona(this);

		return empleado;
	}

	public Empleado removeEmpleado(Empleado empleado) {
		getEmpleados().remove(empleado);
		empleado.setPersona(null);

		return empleado;
	}

	public VacunaPersona addVacunaPersona(VacunaPersona vacunaPersona) {
		getVacunaPersonas().add(vacunaPersona);
		vacunaPersona.setPersona(this);

		return vacunaPersona;
	}

	public VacunaPersona removeVacunaPersona(VacunaPersona vacunaPersona) {
		getVacunaPersonas().remove(vacunaPersona);
		vacunaPersona.setPersona(null);

		return vacunaPersona;
	}

	public String getNombrePersona() {
		String nombrePersona = "";
		nombrePersona = this.nombres + " " + this.apellidos;
		return nombrePersona;
	}
}