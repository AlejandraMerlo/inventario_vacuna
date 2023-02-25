package com.inventario.modules.personas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inventario.modules.models.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {

	@Query(value = "SELECT persona FROM Persona persona ORDER BY persona ASC")
	public List<Persona> getPersonas();

	@Query(value = "SELECT persona FROM Persona persona WHERE persona.codigoPersona = :codigoPersona")
	public Persona getPersonaByCodigo(@Param("codigoPersona") int codigoPersona);
}
