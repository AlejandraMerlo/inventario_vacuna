package com.inventario.modules.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonView;
import com.inventario.modules.views.Views;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the usuario_rol database table.
 * 
 */
@Entity
@NamedQuery(name = "UsuarioRol.findAll", query = "SELECT u FROM UsuarioRol u")
@Getter
@Setter
public class UsuarioRol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usuario_rol")
	@JsonView({ Views.Read.class, Views.Write.class })
	private int usuarioRol;

	// bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name = "codigo_usuario")
	@JsonView({ Views.Read.class, Views.Write.class })
	private Usuario usuario;

	// bi-directional many-to-one association to Rol
	@ManyToOne
	@JoinColumn(name = "codigo_rol")
	@JsonView({ Views.Read.class, Views.Write.class })
	private Rol rol;

	public UsuarioRol() {
	}
}