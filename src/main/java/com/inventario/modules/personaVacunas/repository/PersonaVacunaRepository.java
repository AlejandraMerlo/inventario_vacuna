package com.inventario.modules.personaVacunas.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inventario.modules.models.VacunaPersona;

public interface PersonaVacunaRepository extends JpaRepository<VacunaPersona, Integer> {

	@Query(value = "SELECT vacuna FROM VacunaPersona vacuna "
			+ "WHERE vacuna.persona.codigoPersona = :codigoPersona ORDER BY vacuna.dosis ASC")
	public List<VacunaPersona> getVacunasByPersona(@Param("codigoPersona") int codigoPersona);

	@Query(value = "SELECT vacuna FROM VacunaPersona vacuna "
			+ "WHERE vacuna.persona.codigoPersona = :codigoPersona AND vacuna.tipoVacuna.codigoTipo = :codigoTipo "
			+ "ORDER BY vacuna.dosis ASC")
	public List<VacunaPersona> getVacunasByPersonaTipoVacuna(@Param("codigoPersona") int codigoPersona,
			@Param("codigoTipo") int codigoTipo);

	@Query(value = "SELECT vacuna FROM VacunaPersona vacuna "
			+ "WHERE vacuna.persona.codigoPersona = :codigoPersona AND vacuna.fechaVacunacion BETWEEN :fechaInicio AND :fechaFin "
			+ "ORDER BY vacuna.dosis ASC")
	public List<VacunaPersona> getVacunasByPersonaFechaVacuna(@Param("codigoPersona") int codigoPersona,
			@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);
}
