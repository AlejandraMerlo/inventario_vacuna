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
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
@Getter
@Setter
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo_usuario")
	@JsonView({ Views.Read.class, Views.Write.class })
	private int codigoUsuario;

	@JsonView({ Views.Read.class, Views.Write.class })
	private Boolean estado;

	@JsonView({ Views.Read.class, Views.Write.class })
	private String password;

	@JsonView({ Views.Read.class, Views.Write.class })
	private String usuario;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_registro")
	@JsonView({ Views.Read.class, Views.Write.class })
	private Date fechaRegistro;

	// bi-directional many-to-one association to Empleado
	@ManyToOne
	@JoinColumn(name = "codigo_empleado")
	@JsonView({ Views.Read.class, Views.Write.class })
	private Empleado empleado;

	// bi-directional many-to-one association to UsuarioRol
	@OneToMany(mappedBy = "usuario")
	private List<UsuarioRol> usuarioRols;

	public Usuario() {
	}

	public UsuarioRol addUsuarioRol(UsuarioRol usuarioRol) {
		getUsuarioRols().add(usuarioRol);
		usuarioRol.setUsuario(this);

		return usuarioRol;
	}

	public UsuarioRol removeUsuarioRol(UsuarioRol usuarioRol) {
		getUsuarioRols().remove(usuarioRol);
		usuarioRol.setUsuario(null);

		return usuarioRol;
	}
}