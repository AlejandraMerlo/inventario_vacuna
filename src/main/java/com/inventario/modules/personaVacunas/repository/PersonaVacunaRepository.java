package com.inventario.modules.personaVacunas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inventario.modules.models.VacunaPersona;

public interface PersonaVacunaRepository extends JpaRepository<VacunaPersona, Integer> {

	@Query(value = "SELECT vacuna FROM VacunaPersona vacuna "
			+ "WHERE vacuna.persona.codigoPersona = :codigoPersona ORDER BY vacuna.dosis ASC")
	public List<VacunaPersona> getVacunasByPersona(@Param("codigoPersona") int codigoPersona);
}
