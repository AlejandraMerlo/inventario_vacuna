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
 * The persistent class for the rol database table.
 * 
 */
@Entity
@NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r")
@Getter
@Setter
public class Rol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo_rol")
	@JsonView({ Views.Read.class, Views.Write.class })
	private int codigoRol;

	@JsonView({ Views.Read.class, Views.Write.class })
	private Boolean estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_registro")
	@JsonView({ Views.Read.class, Views.Write.class })
	private Date fechaRegistro;

	@Column(name = "nombre_rol")
	@JsonView({ Views.Read.class, Views.Write.class })
	private String nombreRol;

	// bi-directional many-to-one association to UsuarioRol
	@OneToMany(mappedBy = "rol")
	private List<UsuarioRol> usuarioRols;

	public Rol() {
	}

	public UsuarioRol addUsuarioRol(UsuarioRol usuarioRol) {
		getUsuarioRols().add(usuarioRol);
		usuarioRol.setRol(this);

		return usuarioRol;
	}

	public UsuarioRol removeUsuarioRol(UsuarioRol usuarioRol) {
		getUsuarioRols().remove(usuarioRol);
		usuarioRol.setRol(null);

		return usuarioRol;
	}

}