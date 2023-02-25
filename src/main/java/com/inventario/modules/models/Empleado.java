package com.inventario.modules.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonView;
import com.inventario.modules.views.Views;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the empleado database table.
 * 
 */
@Entity
@NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e")
@Getter
@Setter
public class Empleado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo_empleado")
	@JsonView({ Views.Read.class, Views.Write.class })
	private int codigoEmpleado;

	@JsonView({ Views.Read.class, Views.Write.class })
	private Boolean estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_registro")
	@JsonView({ Views.Read.class, Views.Write.class })
	private Date fechaRegistro;

	// bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name = "codigo_persona")
	@JsonView({ Views.Read.class, Views.Write.class })
	private Persona persona;

	// bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy = "empleado")
	private List<Usuario> usuarios;

	public Empleado() {
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setEmpleado(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setEmpleado(null);

		return usuario;
	}
}