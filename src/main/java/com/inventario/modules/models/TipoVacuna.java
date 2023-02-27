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
 * The persistent class for the tipo_vacuna database table.
 * 
 */
@Entity
@NamedQuery(name = "TipoVacuna.findAll", query = "SELECT t FROM TipoVacuna t")
@Getter
@Setter
public class TipoVacuna implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_tipo")
	@JsonView({ Views.Read.class, Views.Write.class })
	private int codigoTipo;

	@JsonView({ Views.Read.class, Views.Write.class })
	private Boolean estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_registro")
	@JsonView({ Views.Read.class, Views.Write.class })
	private Date fechaRegistro;

	@Column(name = "nombre_vacuna", nullable = false, length = 30)
	@JsonView({ Views.Read.class, Views.Write.class })
	private String nombreVacuna;

	// bi-directional many-to-one association to VacunaPersona
	@OneToMany(mappedBy = "tipoVacuna")
	private List<VacunaPersona> vacunaPersonas;

	public TipoVacuna() {
	}

	public VacunaPersona addVacunaPersona(VacunaPersona vacunaPersona) {
		getVacunaPersonas().add(vacunaPersona);
		vacunaPersona.setTipoVacuna(this);

		return vacunaPersona;
	}

	public VacunaPersona removeVacunaPersona(VacunaPersona vacunaPersona) {
		getVacunaPersonas().remove(vacunaPersona);
		vacunaPersona.setTipoVacuna(null);

		return vacunaPersona;
	}
}