package com.inventario.modules.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonView;
import com.inventario.modules.views.Views;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the vacuna_persona database table.
 * 
 */
@Entity
@NamedQuery(name = "VacunaPersona.findAll", query = "SELECT v FROM VacunaPersona v")
@Getter
@Setter
public class VacunaPersona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vacuna_persona")
	@JsonView({ Views.Read.class, Views.Write.class })
	private int vacunaPersona;

	@Column(nullable = false)
	@JsonView({ Views.Read.class, Views.Write.class })
	private int dosis;

	@Column(name = "fecha_vacunacion")
	@JsonView({ Views.Read.class, Views.Write.class })
	private Date fechaVacunacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_registro")
	@JsonView({ Views.Read.class, Views.Write.class })
	private Date fechaRegistro;

	// bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name = "codigo_persona")
	@JsonView({ Views.Read.class, Views.Write.class })
	private Persona persona;

	// bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name = "codigo_tipo")
	@JsonView({ Views.Read.class, Views.Write.class })
	private TipoVacuna tipoVacuna;

	public VacunaPersona() {
	}

}